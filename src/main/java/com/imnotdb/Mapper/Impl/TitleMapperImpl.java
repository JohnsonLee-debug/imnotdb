package com.imnotdb.Mapper.Impl;

import com.imnotdb.Entity.Title;
import com.imnotdb.Mapper.TitleMapper;

import java.util.List;

public class TitleMapperImpl implements TitleMapper {
    @Override
    public Title getTitleByTconst(String tconst) {
        return null;
    }

    @Override
    public List<Title> getTitleByAdult(Boolean adult) {
        return null;
    }

    @Override
    public List<Title> getTitleByGenres(String genres) {
        return null;
    }

    @Override
    public List<Title> getTitleByType(String type) {
        return null;
    }

    @Override
    public List<Title> getTitleByLen(Integer from, Integer to) {
        return null;
    }

    @Override
    public List<Title> getTitleByRating(Double from, Double to) {
        return null;
    }
}
