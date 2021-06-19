package com.imnotdb.imnotdb.service;

import com.imnotdb.imnotdb.pojo.Rating;
import org.springframework.stereotype.Service;

@Service
public interface RatingService {
    public Rating getRatingsByTconst(String tconst);
}
