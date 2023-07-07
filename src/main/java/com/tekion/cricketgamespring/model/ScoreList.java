package com.tekion.cricketgamespring.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ScoreList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany
    private List<ScoreCard> scorecardList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ScoreCard> getScorecardList() {
        return scorecardList;
    }

    public void setScorecardList(List<ScoreCard> scorecardList) {
        this.scorecardList = scorecardList;
    }

    @Override
    public String toString() {
        return "ScoreCardList{" +
                "id=" + id +
                ", scorecardList=" + scorecardList +
                '}';
    }
}


