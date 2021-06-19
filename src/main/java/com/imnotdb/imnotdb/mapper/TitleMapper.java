package com.imnotdb.imnotdb.mapper;

import com.imnotdb.imnotdb.pojo.*;
import com.imnotdb.imnotdb.utils.BigTableTransformer;
import com.imnotdb.imnotdb.utils.SnowFlake;
import com.imnotdb.imnotdb.utils.SymbolTable;
import org.jetbrains.annotations.NotNull;
import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TitleMapper {

    @Autowired
    private NutDao nutDao;
    @Autowired
    private SnowFlake snowFlake;
    @Autowired
    private BigTableTransformer bigTableTransformer;
    @Autowired
    private TitleFullMapper titleFullMapper;

    public Title getTitleByTconst(String tconst) {
        return nutDao.fetch(Title.class, tconst);
    }
//    public List<Title> getTitleByAdult(Boolean adult, int pageNumber, int pageSize, boolean setTotal) {
//        Pager pager = dao.createPager(pageNumber, pageSize);
//        dao.query(Title.class,
//                Cnd.where("adult", "=", adult),
//                pager);
//        if (setTotal) {
//            pager.setRecordCount(dao.count(Title.class,
//                    Cnd.where("adult", "=", adult)));
//        }
//        return null;
//    }
//    public QueryResult getAllTitle(int pageNumber, int pageSize, String sortBy) {
//        Pager pager = dao.createPager(pageNumber, pageSize);
//        List<Title> titleList = dao.query(Title.class, Cnd.wrap("1 = 1"), pager);
//        return new QueryResult(titleList, pager);
//    }
//    public QueryResult getTitleByGenres(String genres, int pageNumber, int pageSize) {
//        Pager pager = dao.createPager(pageNumber, pageSize);
//        List<Title> titleList = dao.query(Title.class, Cnd.wrap("1 = 1"), pager);
//        return new QueryResult(titleList, pager);
//    }
//    public QueryResult getTitleByType(String type, int pageNumber, int pageSize, boolean setTotal) {
//        Pager pager = dao.createPager(pageNumber, pageSize);
//        List<Title> titleList = dao.query(Title.class, Cnd.where("titleType","=", type), pager);
//        if(setTotal) {
//            pager.setRecordCount(
//                    dao.count(Title.class,
//                            Cnd.where("titleType", "=", type)));
//        }
//        return new QueryResult(titleList, pager);
//    }
//    public QueryResult getTitleByLen(Integer from, Integer to, int pageNumber, int pageSize, boolean setTotal) {
//        Pager pager = dao.createPager(pageNumber, pageSize);
//        Criteria cri = Cnd.cri();
//        if(from != null){
//            cri.where().and("runtimeMinutes", ">=", from);
//        }
//        if (to != null){
//            cri.where().and("runtimeMinutes", "<=", to);
//        }
//        List<Title> titleList = dao.query(Title.class, cri, pager);
//        if(setTotal){
//            pager.setRecordCount(dao.count(Title.class, cri));
//        }
//        return new QueryResult(titleList, pager);
//    }
    public QueryResult getAllTitle(int pageNumber, int pageSize){
        Pager pager = nutDao.createPager(pageNumber, pageSize);
        List<Title> titles = nutDao.query(Title.class,
                Cnd.wrap("1=1"),
                pager);
        return new QueryResult(titles, pager);
    }
    public void fetchAllLinks(Title title){
        nutDao.fetchLinks(title, "akas");
        nutDao.fetchLinks(title, "crew");
        nutDao.fetchLinks(title, "principals");
        nutDao.fetchLinks(title, "ratings");
    }
    public void insertTitle(@NotNull Title title){
        String tconst = Long.toString(snowFlake.nextId());
        title.setTconst(tconst);
        if(title.getAkas() != null && title.getAkas().size() > 0){
            for (Akas aka : title.getAkas()) {
                aka.setTconst(tconst);
                nutDao.insert(aka);
            }
        }
        if(title.getCrew() != null){
            title.getCrew().setTconst(tconst);
            nutDao.insert(title.getCrew());
        }
        if(title.getRating() != null){
            title.getRating().setTconst(tconst);
            nutDao.insert(title.getRating());
        }
        if(title.getPrincipals() != null && title.getPrincipals().size() > 0){
            for (Principals principal : title.getPrincipals()) {
                principal.setTconst(tconst);
                nutDao.insert(principal);
            }
        }
        nutDao.insert(title);
        TitleFull titleFull = bigTableTransformer.titleToFull(title);
        nutDao.insert(titleFull);
        titleFullMapper.insertTitleFullInES(titleFull);
    }
    public void updateTitle(Title title){
        String tconst = title.getTconst();
        if(title.getAkas() != null && title.getAkas().size() > 0){
            for (Akas aka : title.getAkas()) {
                nutDao.update(aka, Cnd.where("tconst","=",tconst));
            }
        }
        if(title.getCrew() != null){
            nutDao.update(title.getCrew(), Cnd.where("tconst","=",tconst));
        }
        if(title.getRating() != null){
            nutDao.update(title.getRating(), Cnd.where("tconst","=",tconst));
        }
        if(title.getPrincipals() != null && title.getPrincipals().size() > 0){
            for (Principals principal : title.getPrincipals()) {
                nutDao.update(principal, Cnd.where("tconst", "=", tconst));
            }
        }
        nutDao.update(title, Cnd.where("tconst","=", title.getTconst()));
        TitleFull titleFull = bigTableTransformer.titleToFull(title);
        titleFullMapper.updateTitleFullInES(titleFull);
    }
    public void deleteTitle(String tconst){
        nutDao.clear(Title.class, Cnd.where("tconst", "=", tconst));
        nutDao.clear(TitleFull.class, Cnd.where("tconst", "=", tconst));
        nutDao.clear(Rating.class, Cnd.where("tconst", "=", tconst));
        nutDao.clear(Crew.class, Cnd.where("tconst", "=", tconst));
        nutDao.clear(Principals.class, Cnd.where("tconst", "=", tconst));
        nutDao.clear(Akas.class, Cnd.where("tconst", "=", tconst));
        titleFullMapper.deleteTitleFullInES(tconst);
    }
}
