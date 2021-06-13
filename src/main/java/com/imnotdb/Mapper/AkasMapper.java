package com.imnotdb.Mapper;

import com.imnotdb.Entity.Akas;
import com.imnotdb.Utils.NutDaoUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;

import java.util.List;

public class AkasMapper {
    private static final NutDao dao = NutDaoUtils.getNutDao();
    public QueryResult getAkasByName(String name, int pageNumber, int pageSize, boolean setTotal) {
        Pager pager = dao.createPager(pageNumber, pageSize);
        Sql sql = NutDaoUtils.matchAgainst(Akas.class, "title", name, false);
        sql.setPager(pager);
        dao.execute(sql);
        List<Akas> akasList = sql.getList(Akas.class);

        if (setTotal){
            Sql sqlCount = NutDaoUtils.matchAgainst(Akas.class, "title", name, true);
            dao.execute(sqlCount);
            pager.setRecordCount(sqlCount.getInt());
        }
        return new QueryResult(akasList, pager);
    }
    public QueryResult getAkasByTconst(String tconst,
                                       int pageNumber,
                                       int pageSize,
                                       boolean setTotal) {
        Pager pager = dao.createPager(pageNumber, pageSize);
        List<Akas> akasList = dao.query(Akas.class,
                Cnd.where("tconst", "=", tconst),
                pager);
        if(setTotal){
            pager.setRecordCount(dao.count(Akas.class,
                    Cnd.where("tconst", "=", tconst)));
        }
        return new QueryResult(akasList, pager);
    }

    public QueryResult getAkasByRegion(String region,
                                       int pageNumber,
                                       int pageSize,
                                       boolean setTotal) {
        Pager pager = dao.createPager(pageNumber, pageSize);
        List<Akas> akasList = dao.query(Akas.class,
                Cnd.where("region", "=", region),
                pager);
        if(setTotal){
            pager.setRecordCount(dao.count(Akas.class,
                    Cnd.where("region", "=", region)));
        }
        return new QueryResult(akasList, pager);
    }

    public QueryResult getAkasByRegionAndName(String region,
                                       String name,
                                       int pageNumber,
                                       int pageSize,
                                       boolean setTotal) {
        Pager pager = dao.createPager(pageNumber, pageSize);
        Sql sql = Sqls.create("select * from title_akas where Match(title) AGAINST(@name) and region = @region");
        sql.params().set("name", name);
        sql.params().set("region", region);
        sql.setCallback(Sqls.callback.entities());
        sql.setEntity(dao.getEntity(Akas.class));
        sql.setPager(pager);
        dao.execute(sql);
        List<Akas> akasList = sql.getList(Akas.class);
        if(setTotal){
            Sql sqlcount = Sqls.create("select count(*) from title_akas where Match(title) AGAINST(@name) and region = @region");
            sqlcount.params().set("name", name);
            sqlcount.params().set("region", region);
            sqlcount.setCallback(Sqls.callback.integer());
            dao.execute(sqlcount);
            pager.setRecordCount(sqlcount.getInt());
        }
        return new QueryResult(akasList, pager);
    }
}