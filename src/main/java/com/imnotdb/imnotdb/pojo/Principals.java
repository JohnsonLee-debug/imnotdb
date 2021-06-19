package com.imnotdb.imnotdb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("title_principals")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Principals {
    @Column("tconst")
    private String tconst;
    @Column("nconst")
    private String nconst;
    @Column("ordering")
    private Integer ordering;
    @Column("category")
    private String category;
    @Column("job")
    private String job;
    @Column("characters")
    private String characters;
}
