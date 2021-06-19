package com.imnotdb.imnotdb.service.impl;

import com.imnotdb.imnotdb.pojo.Rating;
import com.imnotdb.imnotdb.service.RatingService;
import org.nutz.dao.impl.NutDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private NutDao nutDao;
    @Override
    public Rating getRatingsByTconst(String tconst) {
        return nutDao.fetch(Rating.class, tconst);
    }
}
