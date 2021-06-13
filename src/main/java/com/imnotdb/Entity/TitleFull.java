package com.imnotdb.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("title_full")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TitleFull {
    @Name
    private String tconst;
    @Column("titleType")
    private String titleType;
    @Column("primaryTitle")
    private String primaryTitle;
    @Column("akaTitles")
    private String akaTitles;
    @Column("isAdult")
    private boolean isAdult;
    @Column("startYear")
    private Integer startYear;
    @Column("endYear")
    private Integer endYear;
    @Column("runtimeMinutes")
    private Integer runtimeMinutes;
    @Column("genres")
    private String genres;
    @Column("actors")
    private String actors;
    @Column("directors")
    private String directors;
    @Column("writers")
    private String writers;
    @Column("averageRating")
    private Double averageRating;
}
