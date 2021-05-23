package com.imnotdb.Entity;

import org.nutz.dao.entity.annotation.*;
import org.nutz.dao.entity.annotation.Name;

import java.sql.Date;
import java.util.List;

@Table("title_basics")
public class Title {
    @Name
    private String tconst;
    @Column("primaryTitle")
    private String primaryTitle;
    @Column("originalTitle")
    private String originalTitle;
    @Column("isAdult")
    private boolean isAdult;
    @Column("startYear")
    private Date startYear;
    @Column("endYear")
    private Date endYear;
    @Column("runtimeMinutes")
    private Integer runtimeMinutes;
    @Column("genres")
    private String genres;
    @Many(field = "tconst")
    List<Principals> principals;
    @Many(field = "tconst")
    List<Akas> akas;
    @One(field = "tconst")
    Crew crew;
    @One(field = "tconst")
    Ratings ratings;

    public Title() {
    }

    public Title(String tconst, String primaryTitle, String originalTitle,
                 boolean isAdult, Date startYear, Date endYear,
                 Integer runtimeMinutes, Title basic, String genres) {
        this.tconst = tconst;
        this.primaryTitle = primaryTitle;
        this.originalTitle = originalTitle;
        this.isAdult = isAdult;
        this.startYear = startYear;
        this.endYear = endYear;
        this.runtimeMinutes = runtimeMinutes;
        this.genres = genres;
    }

    public String getTconst() {
        return tconst;
    }

    public void setTconst(String tconst) {
        this.tconst = tconst;
    }

    public String getPrimaryTitle() {
        return primaryTitle;
    }

    public void setPrimaryTitle(String primaryTitle) {
        this.primaryTitle = primaryTitle;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public boolean getAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public Date getStartYear() {
        return startYear;
    }

    public void setStartYear(Date startYear) {
        this.startYear = startYear;
    }

    public Date getEndYear() {
        return endYear;
    }

    public void setEndYear(Date endYear) {
        this.endYear = endYear;
    }

    public Integer getRuntimeMinutes() {
        return runtimeMinutes;
    }

    public void setRuntimeMinutes(Integer runtimeMinutes) {
        this.runtimeMinutes = runtimeMinutes;
    }



    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Title{" +
                "tconst='" + tconst + '\'' +
                ", primaryTitle='" + primaryTitle + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", isAdult=" + isAdult +
                ", startYear=" + startYear +
                ", endYear=" + endYear +
                ", runtimeMinutes=" + runtimeMinutes +
                ", genres=" + genres +
                '}';
    }
}
