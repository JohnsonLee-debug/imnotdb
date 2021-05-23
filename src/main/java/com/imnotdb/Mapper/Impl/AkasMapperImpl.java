package com.imnotdb.Mapper.Impl;

import com.imnotdb.Entity.Akas;
import com.imnotdb.Mapper.AkasMapper;
import com.imnotdb.utils.NutDaoUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;

import java.util.List;

public class AkasMapperImpl implements AkasMapper {

    @Override
    public QueryResult getAkasByName(String name, int pageNumber, int pageSize) {
        NutDao dao = NutDaoUtils.getNutDao();
        Pager pager = dao.createPager(pageNumber, pageSize);
        Sql sql = Sqls.create("select * from title_akas where Match(title) AGAINST(@name)");
        sql.setPager(pager);
        sql.params().set("name", name);
        sql.setCallback(Sqls.callback.entities());
        sql.setEntity(dao.getEntity(Akas.class));
        dao.execute(sql);
        List<Akas> akasList = sql.getList(Akas.class);
        return new QueryResult(akasList, pager);
    }

    @Override
    public List<Akas> getAkasByTconst(String tconst) {
        NutDao dao = NutDaoUtils.getNutDao();
        return dao.query(Akas.class, Cnd.where("tconst","=", tconst));
    }
}
