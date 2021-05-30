package com.imnotdb.Mapper;

import com.imnotdb.Entity.Title;
import com.imnotdb.Utils.NutDaoUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;

import java.util.List;
import java.util.Map;

public class TitleMapper {
    private static final NutDao dao = NutDaoUtils.getNutDao();
    static private final String MOVIENAME = "MOVIENAME";
    static private final String DIRECTOR = "DIRECTOR ";
    static private final String WRITER = "WRITER ";
    static private final String ACTOR = "ACTOR ";
    static private final String RELEASE_YEAR_BEGIN = "RELEASE_YEAR_BEGIN ";
    static private final String RELEASE_YEAR_END = "RELEASE_YEAR_END ";
    static private final String GENRES = "GENRES ";
    static private final String ISADULT = "ISADULT ";
    static private final String TITLETYPE = "TITLETYPE ";
    static private final String RATINGMORETHAN = "RATINGMORETHAN ";
    static private final String RATINGLESSTHAN = "RATINGLESSTHAN ";
    static private final String LENGTHMORETHAN = "LENGTHMORETHAN ";
    static private final String LENGTHLESSTHAN = "LENGTHLESSTHAN ";

    public QueryResult getTitleByCnds(Map<String, Object> conditions, int pageNumber, int pageSize, boolean setTotal) {
        Pager pager = dao.createPager(pageNumber, pageSize);
        Criteria cri = Cnd.cri();
        if (conditions.containsKey(MOVIENAME)){
//            cri.where().and();
        }
        return null;
    }

    public Title getTitleByTconst(String tconst) {
        return dao.fetch(Title.class, tconst);
    }
    public List<Title> getTitleByAdult(Boolean adult, int pageNumber, int pageSize, boolean setTotal) {
        Pager pager = dao.createPager(pageNumber, pageSize);
        dao.query(Title.class,
                Cnd.where("adult", "=", adult),
                pager);
        if (setTotal) {
            pager.setRecordCount(dao.count(Title.class,
                    Cnd.where("adult", "=", adult)));
        }
        return null;
    }

    public QueryResult getAllTitle(int pageNumber, int pageSize, String sortBy) {
        Pager pager = dao.createPager(pageNumber, pageSize);
        List<Title> titleList = dao.query(Title.class, Cnd.wrap("1 = 1"), pager);
        return new QueryResult(titleList, pager);
    }

    public List<Title> getTitleByGenres(String genres) {
        return null;
    }

    public List<Title> getTitleByType(String type) {
        return null;
    }

    public List<Title> getTitleByLen(Integer from, Integer to) {
        return null;
    }

    public QueryResult getTitleByRating(Double from, Double to, int pageNumber, int pageSize) {
        Pager pager = dao.createPager(pageNumber, pageSize);
        List<Title> titleList = dao.query(Title.class,
                Cnd.where("averageRating", ">=", from)
                        .and("averageRating", "<=", to),
                pager);
        return new QueryResult(titleList, pager);
    }
}
