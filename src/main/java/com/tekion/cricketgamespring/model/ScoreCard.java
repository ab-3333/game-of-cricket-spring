package com.tekion.cricketgamespring.model;

import jakarta.persistence.*;

@Entity
public class ScoreCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Team currTeam;
    private String runsCurrBall;
    private String overs;
    private int wickets;
    private int score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getCurrTeam() {
        return currTeam;
    }

    public void setCurrTeam(Team currTeam) {
        this.currTeam = currTeam;
    }

    public String getRunsCurrBall() {
        return runsCurrBall;
    }

    public void setRunsCurrBall(String runsCurrBall) {
        this.runsCurrBall = runsCurrBall;
    }

    public String getOvers() {
        return overs;
    }

    public void setOvers(String overs) {
        this.overs = overs;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
