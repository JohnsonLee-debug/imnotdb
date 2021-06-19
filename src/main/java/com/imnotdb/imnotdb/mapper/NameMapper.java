package com.imnotdb.imnotdb.mapper;

import com.imnotdb.imnotdb.pojo.Name;
import com.imnotdb.imnotdb.pojo.Principals;
import com.imnotdb.imnotdb.pojo.Title;
import com.imnotdb.imnotdb.utils.SnowFlake;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.QueryResult;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NameMapper {
    @Autowired
    private NutDao nutDao;
    @Autowired
    private TitleFullMapper titleFullMapper;
    @Autowired
    private SnowFlake snowFlake;
    public void deleteNameByNconst(String nconst){
        List<Principals> principals = nutDao.query(Principals.class, Cnd.where("nconst", "=", nconst));
        nutDao.clear(Name.class, Cnd.where("nconst", "=", nconst));
        nutDao.clear(Principals.class, Cnd.where("nconst", "=", nconst));
        for (Principals principal : principals) {
            titleFullMapper.syncDataWithMySQL(principal.getTconst());
        }
    }
    public QueryResult getNameByJobAndName(String name,
                                           int pageNumber,
                                           int pageSize) {

        Pager pager = nutDao.createPager(pageNumber, pageSize);
        Sql sql = Sqls.create("SELECT * FROM name WHERE Match(primaryName) AGAINST(@name IN BOOLEAN MODE)");
        sql.params().set("name", name);
        sql.setCallback(Sqls.callback.entities());
        sql.setEntity(nutDao.getEntity(Name.class));
        sql.setPager(pager);
        nutDao.execute(sql);
        List<Name> nameList = sql.getList(Name.class);
        return new QueryResult(nameList, pager);
    }
    public void updateName(Name name){
        Name nameOld = nutDao.fetch(Name.class, name.getNconst());
        nutDao.update(name, Cnd.where("nconst", "=", name.getNconst()));
        if(!nameOld.getPrimaryName().equals(nameOld.getPrimaryName())){
            List<Principals> principals = nutDao.query(Principals.class, Cnd.where("nconst", "=", name.getNconst()));
            for (Principals principal : principals) {
                titleFullMapper.syncDataWithMySQL(principal.getTconst());
            }
        }
    }
    public void insertName(Name name){
        String nconst = Long.toString(snowFlake.nextId());
        name.setNconst(nconst);
        nutDao.insert(name);
    }
    public List<Title> getKnownForTitlesOfAPerson(String nconst){
        Name nameByNconst = nutDao.fetch(Name.class, nconst);
        String knownForTitles = nameByNconst.getKnownForTitles();
        String[] split = knownForTitles.split(",");
        String collect = Arrays.stream(split)
                .map(x -> String.format("'%s'", x))
                .collect(Collectors.joining(","));
        String query = String.format("tconst in (%s)", collect);
        Condition wrap = Cnd.wrap(query);
        return nutDao.query(Title.class, wrap);
    }
}