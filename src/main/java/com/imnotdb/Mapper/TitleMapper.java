package com.imnotdb.Mapper;

import com.imnotdb.Entity.Title;
import com.imnotdb.Utils.NutDaoUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;

import java.util.List;
import java.util.Map;

public class TitleMapper {
    private static final NutDao dao = NutDaoUtils.getNutDao();
    static public final String MOVIENAME = "MOVIENAME";
    static public final String DIRECTOR = "DIRECTOR ";
    static public final String WRITER = "WRITER ";
    static public final String ACTOR = "ACTOR ";
    static public final String RELEASE_YEAR_BEGIN = "RELEASE_YEAR_BEGIN ";
    static public final String RELEASE_YEAR_END = "RELEASE_YEAR_END ";
    static public final String GENRES = "GENRES ";
    static public final String ISADULT = "ISADULT ";
    static public final String TITLETYPE = "TITLETYPE ";
    static public final String RATINGMORETHAN = "RATINGMORETHAN ";
    static public final String RATINGLESSTHAN = "RATINGLESSTHAN ";
    static public final String LENGTHMORETHAN = "LENGTHMORETHAN ";
    static public final String LENGTHLESSTHAN = "LENGTHLESSTHAN ";

    public QueryResult getTitleByCnds(Map<String, Object> conditions, int pageNumber, int pageSize, boolean setTotal) {
        Pager pager = dao.createPager(pageNumber, pageSize);
        Sql sql = Sqls.create("SELECT DISTINCT tconst FROM BigTable $condition");
        Criteria cri = Cnd.cri();
        if (conditions.containsKey(MOVIENAME)){
            cri.where().andInBySql("tconst",
                    "SELECT DISTINCT tconst FROM title_akas WHERE MATCH(title) AGAINST(%s)",
                    conditions.get(MOVIENAME));
        }
        if (conditions.containsKey(DIRECTOR)){
            // TODO: 换成Match
            cri.where().andInBySql("directors",
                    "SELECT DISTINCT nconst FROM name WHERE MATCH(primaryName) AGAINST(%s) AND primaryProfession LIKE 'director'",
                    conditions.get(DIRECTOR));
        }
        if (conditions.containsKey(WRITER)){
            // TODO: 换成Match
            cri.where().andInBySql("writers",
                    "SELECT DISTINCT nconst FROM name WHERE MATCH(primaryName) AGAINST(%s) AND primaryProfession LIKE 'writer'",
                    conditions.get(WRITER));
        }
        if (conditions.containsKey(ACTOR)){
            cri.where().andEquals("category", "actor").orEquals("category", "self");
            cri.where().andInBySql("principal_nconst",
                    "SELECT DISTINCT nconst FROM name WHERE MATCH(primaryName) AGAINST(%s) AND primaryProfession LIKE 'writer'",
                    conditions.get(WRITER));
        }
        if (conditions.containsKey(RELEASE_YEAR_BEGIN)){
            cri.where().andGT("", (Long) conditions.get(LENGTHMORETHAN));
        }
        if (conditions.containsKey(RELEASE_YEAR_END)){
            cri.where().andGT("runtimeMinute", (Long) conditions.get(LENGTHMORETHAN));
        }
        if (conditions.containsKey(GENRES)){

        }
        if (conditions.containsKey(ISADULT)){

        }
        if (conditions.containsKey(TITLETYPE)){

        }
        if (conditions.containsKey(RATINGMORETHAN)){
            cri.where().and(Cnd.exp("ratings", ">", conditions.get(RATINGMORETHAN)));
        }
        if (conditions.containsKey(RATINGLESSTHAN)){
            cri.where().and(Cnd.exp("ratings", "<", conditions.get(RATINGLESSTHAN)));
        }
        if (conditions.containsKey(LENGTHMORETHAN)){
            cri.where().andGT("runtimeMinute", (Long) conditions.get(LENGTHMORETHAN));
        }
        if (conditions.containsKey(LENGTHLESSTHAN)){
            cri.where().andLT("runtimeMinute", (Long) conditions.get(LENGTHLESSTHAN));
        }
        sql.setCondition(cri);
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

    public QueryResult getTitleByGenres(String genres, int pageNumber, int pageSize) {
        Pager pager = dao.createPager(pageNumber, pageSize);
        List<Title> titleList = dao.query(Title.class, Cnd.wrap("1 = 1"), pager);
        return new QueryResult(titleList, pager);
    }

    public QueryResult getTitleByType(String type, int pageNumber, int pageSize, boolean setTotal) {
        Pager pager = dao.createPager(pageNumber, pageSize);
        List<Title> titleList = dao.query(Title.class, Cnd.where("titleType","=", type), pager);
        if(setTotal) {
            pager.setRecordCount(
                    dao.count(Title.class,
                            Cnd.where("titleType", "=", type)));
        }
        return new QueryResult(titleList, pager);
    }

    public QueryResult getTitleByLen(Integer from, Integer to, int pageNumber, int pageSize, boolean setTotal) {
        Pager pager = dao.createPager(pageNumber, pageSize);
        Criteria cri = Cnd.cri();
        if(from != null){
            cri.where().and("runtimeMinutes", ">=", from);
        }
        if (to != null){
            cri.where().and("runtimeMinutes", "<=", to);
        }
        List<Title> titleList = dao.query(Title.class, cri, pager);
        if(setTotal){
            pager.setRecordCount(dao.count(Title.class, cri));
        }
        return new QueryResult(titleList, pager);
    }
    public QueryResult getAllTitle(int pageNumber, int pageSize){
        Pager pager = dao.createPager(pageNumber, pageSize);
        List<Title> titles = dao.query(Title.class,
                Cnd.wrap("1=1"),
                pager);
        return new QueryResult(titles, pager);
    }
}
