package com.imnotdb.imnotdb.service.impl;

import com.imnotdb.imnotdb.pojo.Principals;
import com.imnotdb.imnotdb.service.PrincipalsService;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrincipalsServiceImpl implements PrincipalsService {
    @Autowired
    private NutDao nutDao;
    @Override
    public List<Principals> getPrincipalsByTconst(String tconst) {
        return nutDao.query(Principals.class, Cnd.where("tconst", "=", tconst));
    }
}
