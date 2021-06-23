package com.imnotdb.imnotdb.mapper;

import com.imnotdb.imnotdb.pojo.*;
import com.imnotdb.imnotdb.utils.BigTableTransformer;
import com.imnotdb.imnotdb.utils.SnowFlake;
import org.jetbrains.annotations.NotNull;
import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
        nutDao.fetchLinks(title, "rating");
    }
    public void insertTitle(@NotNull Title title){
        if(title == null){
            return;
        }
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
        if(title == null){
            return;
        }
        String tconst = title.getTconst();
        if(title.getAkas() != null && title.getAkas().size() > 0){
            List<Akas> akasList = title.getAkas().stream().filter(x -> !x.getTitle().isEmpty()).collect(Collectors.toList());
            if(akasList.size() > 0){
                nutDao.clear(Akas.class, Cnd.where("tconst","=", tconst));
                for (Akas aka : akasList) {
                    aka.setTconst(tconst);
                    nutDao.insert(aka);
                }
            }
        }
        if(title.getCrew() != null){
            title.getCrew().setTconst(tconst);
            nutDao.update(title.getCrew(), Cnd.where("tconst", "=", tconst));
        }
        if (title.getRating() != null) {
            title.getRating().setTconst(tconst);
            nutDao.update(title.getRating(), Cnd.where("tconst", "=", tconst));
        }
        if (title.getPrincipals() != null && title.getPrincipals().size() > 0) {
            for (Principals principal : title.getPrincipals()) {
                principal.setTconst(tconst);
                nutDao.update(principal, Cnd.where("tconst", "=", tconst).and("nconst", "=", principal.getNconst()));
            }
        } else {
            nutDao.clear(Principals.class, Cnd.where("tconst", "=", tconst));
        }
        nutDao.update(title, Cnd.where("tconst","=", title.getTconst()));
        TitleFull titleFull = bigTableTransformer.titleToFull(title);
        titleFullMapper.updateTitleFullInES(titleFull);
    }
    public void deleteTitle(String tconst){
        if(tconst == null){
            return;
        }
        nutDao.clear(Title.class, Cnd.where("tconst", "=", tconst));
        nutDao.clear(TitleFull.class, Cnd.where("tconst", "=", tconst));
        nutDao.clear(Rating.class, Cnd.where("tconst", "=", tconst));
        nutDao.clear(Crew.class, Cnd.where("tconst", "=", tconst));
        nutDao.clear(Principals.class, Cnd.where("tconst", "=", tconst));
        nutDao.clear(Akas.class, Cnd.where("tconst", "=", tconst));
        titleFullMapper.deleteTitleFullInES(tconst);
    }
}
