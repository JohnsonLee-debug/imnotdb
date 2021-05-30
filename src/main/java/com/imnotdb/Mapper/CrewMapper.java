package com.imnotdb.Mapper;

import com.imnotdb.Entity.Crew;
import com.imnotdb.Entity.Crew;
import com.imnotdb.Utils.NutDaoUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;

import java.util.List;

public class CrewMapper {
    private static final NutDao dao = NutDaoUtils.getNutDao();
    public QueryResult getCrewByDirectorsId(String name, int pageNumber, int pageSize, boolean setTotal){
        Pager pager = dao.createPager(pageNumber, pageSize);
        Sql sql = NutDaoUtils.matchAgainst(Crew.class, "directors", name, false);
        sql.setPager(pager);
        dao.execute(sql);
        List<Crew> crewList = sql.getList(Crew.class);

        if (setTotal){
            Sql sqlCount = NutDaoUtils.matchAgainst(Crew.class, "title", name, true);
            dao.execute(sqlCount);
            pager.setRecordCount(sqlCount.getInt());
        }
        return new QueryResult(crewList, pager);
    }
    public QueryResult getCrewByTconst(String tconst, int pageNumber, int pageSize, boolean setTotal) {
        Pager pager = dao.createPager(pageNumber, pageSize);
        List<Crew> crewList = dao.query(Crew.class,
                Cnd.where("tconst", "=", tconst),
                pager);
        if(setTotal){
            pager.setRecordCount(dao.count(Crew.class, Cnd.where("tconst", "=", tconst)));
        }
        return new QueryResult(crewList, pager);
    }
}
