package com.imnotdb.Mapper;

import com.imnotdb.Entity.Name;
import com.imnotdb.Entity.Title;
import com.imnotdb.Utils.NutDaoUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.QueryResult;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;

import java.util.List;

public class NameMapper {
    public static final NutDao dao = NutDaoUtils.getNutDao();
    public Name getNameByNconst(String nconst) {
        return dao.fetch(Name.class, nconst);
    }
    public QueryResult getDirectorByName(String name,
                                         int pageNumber,
                                         int pageSize,
                                         boolean setTotal){
        return getNameByJobAndName(name, "director", pageNumber, pageSize, setTotal);
    }
    public QueryResult getActorByName(String name,
                                         int pageNumber,
                                         int pageSize,
                                         boolean setTotal){
        return getNameByJobAndName(name, "actor", pageNumber, pageSize, setTotal);
    }
    public QueryResult getWriterByName(String name,
                                         int pageNumber,
                                         int pageSize,
                                         boolean setTotal){
        return getNameByJobAndName(name, "writer", pageNumber, pageSize, setTotal);
    }
    public QueryResult getNameByJobAndName(String name,
                                          String job,
                                          int pageNumber,
                                          int pageSize,
                                          boolean setTotal) {

        Pager pager = dao.createPager(pageNumber, pageSize);
        Sql sql = Sqls.create("SELECT * FROM name WHERE Match(primaryName) AGAINST(@name IN BOOLEAN MODE) AND primaryProfession LIKE '%$job%'");
        sql.params().set("name", name);
        sql.vars().set("job", job);
        sql.setCallback(Sqls.callback.entities());
        sql.setEntity(dao.getEntity(Name.class));
        sql.setPager(pager);
        dao.execute(sql);
        List<Name> nameList = sql.getList(Name.class);
        if(setTotal){
            Sql sqlcount = Sqls.create("SELECT * FROM name WHERE Match(primaryName) AGAINST(@name IN BOOLEAN MODE) and primaryProfession LIKE '%$job%'");
            sqlcount.params().set("name", name);
            sqlcount.vars().set("job", job);
            sqlcount.setCallback(Sqls.callback.integer());
            dao.execute(sqlcount);
            pager.setRecordCount(sqlcount.getInt());
        }
        return new QueryResult(nameList, pager);
    }
    public List<Title> getKnownForTitlesOfAPerson(String nconst){
        Name nameByNconst = getNameByNconst(nconst);
        String knownForTitles = nameByNconst.getKnownForTitles();
        String[] split = knownForTitles.split(",");
        StringBuilder sb = new StringBuilder();
        sb.append("tconst in (");
        for (String s : split) {
            //noinspection StringEquality
            if(s != split[0]) sb.append(",");
            sb.append("'");
            sb.append(s);
            sb.append("'");
        }
        sb.append(')');
        Condition wrap = Cnd.wrap(sb.toString());
        return dao.query(Title.class, wrap);
    }
}