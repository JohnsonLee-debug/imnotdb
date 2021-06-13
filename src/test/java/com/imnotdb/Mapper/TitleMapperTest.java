package com.imnotdb.Mapper;

import com.imnotdb.Entity.*;
import com.imnotdb.Mapper.TitleMapper;
import com.imnotdb.Utils.NutDaoUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
class TitleMapperTest {
    static private final NutDao nutDao = NutDaoUtils.getNutDao();
    @Test
    void getTitleByTconst() {
        TitleMapper titleMapper = new TitleMapper();
        String tconst = "tt0000006";
        Title title = titleMapper.getTitleByTconst(tconst);
        log.info("{}:{}", tconst, nutDao.fetchLinks(title, "ratings"));
    }

    @Test
    void getTitleByAdult() {
    }

    @Test
    void getTitleByGenres() {
    }

    @Test
    void getTitleByType() {
    }

    @Test
    void getTitleByLen() {
        TitleMapper titleMapper = new TitleMapper();
        QueryResult titleByLen = titleMapper.getTitleByLen(null, null, 1, 20, true);
        for (Title title : titleByLen.getList(Title.class)) {
            log.info(title.toString());
        }
    }

    @Test
    void getAllTitle() {
        Dao dao = NutDaoUtils.getNutDao();
        TitleMapper titleMapper = new TitleMapper();
        QueryResult allTitle = titleMapper.getAllTitle(1, 10);
        while (allTitle.getList().size() > 0){
            List<Title> allTitleList = (List<Title>) allTitle.getList();
            Stream<TitleFull> titleFullStream = allTitleList.stream().map((t -> titleToFull(t)));
            dao.insert(titleFullStream.toArray());
            Pager pager = allTitle.getPager();
            allTitle = titleMapper.getAllTitle(pager.getPageNumber()+1, pager.getPageSize());
        }
    }
    TitleFull titleToFull(Title title){
        TitleFull titleFull = new TitleFull();
        titleFull.setTconst(title.getTconst());
        titleFull.setTitleType(title.getTitleType());
        titleFull.setPrimaryTitle(title.getPrimaryTitle());
        titleFull.setAdult(title.isAdult());
        titleFull.setStartYear(title.getStartYear());
        titleFull.setEndYear(title.getEndYear());
        titleFull.setRuntimeMinutes(title.getRuntimeMinutes());
        titleFull.setGenres(title.getGenres());
        Dao dao = NutDaoUtils.getNutDao();
        dao.fetchLinks(title, "akas");
        dao.fetchLinks(title, "crew");
        dao.fetchLinks(title, "principals");
        dao.fetchLinks(title, "ratings");
        if (title.getAkas() != null){
            StringBuilder akaTitles = new StringBuilder();
            for(Akas akas: title.getAkas()){
                if(akaTitles.length() == 0){
                    akaTitles.append(akas.getTitle());
                }else {
                    akaTitles.append(",")
                            .append(akas.getTitle());
                }
            }
            titleFull.setAkaTitles(akaTitles.toString());
        }
        if (title.getCrew() != null){
            Crew crew = title.getCrew();
            String directorID = crew.getDirectors();
            String writerID = crew.getWriters();
            titleFull.setWriters(getNameList(writerID));
            titleFull.setDirectors(getNameList(directorID));
        }
        if (title.getRatings() != null){
            titleFull.setAverageRating(title.getRatings().getAverageRating());
        }
        if (title.getPrincipals() != null){
            StringBuilder principalID = new StringBuilder();
            for (Principals principal : title.getPrincipals()) {
                if(principal.getCategory() != null && (principal.getCategory().equals("self") || principal.getCategory().equals("actor"))){
                    if(principalID.length() == 0){
                        principalID.append(principal.getNconst());
                    }else {
                        principalID.append(",").append(principal.getNconst());
                    }
                }
            }
            String principalNames = getNameList(principalID.toString());
            titleFull.setActors(principalNames);
        }
        return titleFull;
    }
    String getNameList(String nconstStr){
        NutDao dao = NutDaoUtils.getNutDao();
        if (nconstStr != null && !nconstStr.equals("")){
            String[] split = nconstStr.split(",");
            StringBuilder sb = new StringBuilder();
            for (String s : split) {
                Name name = dao.fetch(Name.class, s);
                if (name == null){
                    continue;
                }
                if(sb.length() == 0){
                    sb.append(name.getPrimaryName());
                }else {
                    sb.append(",").append(name.getPrimaryName());
                }
            }
            return sb.toString();
        }
        return "";
    }

}