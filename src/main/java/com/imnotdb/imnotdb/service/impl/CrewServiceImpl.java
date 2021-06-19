package com.imnotdb.imnotdb.service.impl;

import com.imnotdb.imnotdb.pojo.Crew;
import com.imnotdb.imnotdb.service.CrewService;
import org.nutz.dao.impl.NutDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

@Service
public class CrewServiceImpl implements CrewService {
    @Autowired
    private NutDao nutDao;
    @Override
    public Crew getCrewByTconst(String tconst) {
        return nutDao.fetch(Crew.class, tconst);
    }
}
