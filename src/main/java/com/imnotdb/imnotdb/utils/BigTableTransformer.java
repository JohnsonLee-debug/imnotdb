package com.imnotdb.imnotdb.utils;

import com.imnotdb.imnotdb.pojo.*;
import org.nutz.dao.impl.NutDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class BigTableTransformer {
    @Autowired
    private NutDao nutDao;

    public TitleFull titleToFull(Title title){
        TitleFull titleFull = new TitleFull();
        titleFull.setTconst(title.getTconst());
        titleFull.setTitleType(title.getTitleType());
        titleFull.setPrimaryTitle(title.getPrimaryTitle());
        titleFull.setIsAdult(title.getIsAdult());
        titleFull.setStartYear(title.getStartYear());
        titleFull.setEndYear(title.getEndYear());
        titleFull.setRuntimeMinutes(title.getRuntimeMinutes());
        titleFull.setGenres(title.getGenres());
        nutDao.fetchLinks(title, "akas");
        nutDao.fetchLinks(title, "crew");
        nutDao.fetchLinks(title, "principals");
        nutDao.fetchLinks(title, "ratings");
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
        if (title.getRating() != null){
            titleFull.setAverageRating(title.getRating().getAverageRating());
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
    public String getNameList(String nconstStr){
        if (nconstStr != null && !nconstStr.equals("")){
            String[] split = nconstStr.split(",");
            StringBuilder sb = new StringBuilder();
            for (String s : split) {
                Name name = nutDao.fetch(Name.class, s);
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
