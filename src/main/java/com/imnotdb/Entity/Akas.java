package com.imnotdb.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("title_akas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Akas {
    @Column("tconst")
    private String tconst;
    @Column("ordering")
    private Integer ordering;
    @Column("title")
    private String title;
    @Column("region")
    private String region;
    @Column("language")
    private String language;
    @Column("types")
    private String types;
    @Column("attributes")
    private String attributes;
    @Column("isOrgionalTitle")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean isOriginalTitle;
}
