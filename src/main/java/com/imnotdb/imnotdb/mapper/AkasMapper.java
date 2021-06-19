package com.imnotdb.imnotdb.mapper;

import com.imnotdb.imnotdb.pojo.Akas;
import com.imnotdb.imnotdb.utils.SnowFlake;
import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AkasMapper {
    @Autowired
    private NutDao nutDao;
    public List<Akas> getAkasByTconst(String tconst){
        List<Akas> akasList = nutDao.query(Akas.class,
                Cnd.where("tconst", "=", tconst));
        return akasList;
    }
    public void deleteAkasByTconst(String tconst){
        nutDao.clear(Akas.class, Cnd.where("tconst", "=", tconst));
    }
    public void updateAkas(Akas akas){
    }
}