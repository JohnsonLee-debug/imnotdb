package com.imnotdb.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("title_principals")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Principals {
    @Column("tconst")
    private String tconst;
    @Column("nconst")
    private Integer ordering;
    @Column("nconst")
    private String nconst;
    @Column("category")
    private String category;
    @Column("job")
    private String job;
    @Column("characters")
    private String characters;
}
