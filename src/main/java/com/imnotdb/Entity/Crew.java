package com.imnotdb.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("title_crew")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Crew {
    @Name
    String tconst;
    @Column("directors")
    String directors;
    @Column("writers")
    String writers;
}
