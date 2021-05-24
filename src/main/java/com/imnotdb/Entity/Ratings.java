package com.imnotdb.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("ratings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ratings {
    @Name
    private String tconst;
    @Column("averageRation")
    private Double averageRation;
    @Column("numVotes")
    private Integer numVotes;
}

