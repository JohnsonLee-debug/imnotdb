package com.imnotdb.Entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;

public class Ratings {
    @Name
    private String tconst;
    @Column("averageRation")
    private Double averageRation;
    @Column("numVotes")
    private Integer numVotes;

    public Ratings() {
    }

    public Ratings(String tconst, Double averageRation, Integer numVotes) {
        this.tconst = tconst;
        this.averageRation = averageRation;
        this.numVotes = numVotes;
    }

    public String getTconst() {
        return tconst;
    }

    public void setTconst(String tconst) {
        this.tconst = tconst;
    }

    public Double getAverageRation() {
        return averageRation;
    }

    public void setAverageRation(Double averageRation) {
        this.averageRation = averageRation;
    }

    public Integer getNumVotes() {
        return numVotes;
    }

    public void setNumVotes(Integer numVotes) {
        this.numVotes = numVotes;
    }

    @Override
    public String toString() {
        return "Ratings{" +
                "tconst='" + tconst + '\'' +
                ", averageRation=" + averageRation +
                ", numVotes=" + numVotes +
                '}';
    }
}

