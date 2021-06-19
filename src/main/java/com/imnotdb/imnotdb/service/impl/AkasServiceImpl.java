package com.imnotdb.imnotdb.service.impl;

import com.imnotdb.imnotdb.pojo.Akas;
import com.imnotdb.imnotdb.service.AkasService;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AkasServiceImpl implements AkasService {
    @Autowired
    private NutDao nutDao;
    @Override
    public List<Akas> getAkasByTconst(String tconst) {
        return nutDao.query(Akas.class, Cnd.where("tconst", "=", tconst));
    }
}
