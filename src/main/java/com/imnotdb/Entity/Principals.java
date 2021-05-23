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

    public String getTconst() {
        return tconst;
    }

    public void setTconst(String tconst) {
        this.tconst = tconst;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }

    public String getNconst() {
        return nconst;
    }

    public void setNconst(String nconst) {
        this.nconst = nconst;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    @Override
    public String toString() {
        return "Principals{" +
                "tconst='" + tconst + '\'' +
                ", ordering=" + ordering +
                ", nconst='" + nconst + '\'' +
                ", category='" + category + '\'' +
                ", job='" + job + '\'' +
                ", characters='" + characters + '\'' +
                '}';
    }
}
