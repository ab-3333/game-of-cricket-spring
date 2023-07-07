package com.tekion.cricketgamespring.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Team team;
    private String name;
    private String type;
    private int score;
    private boolean status;
//private int scoreId;

    public int getId() {
        return id;
    }

    public void setId(int playerId) {
        this.id = playerId;
    }

//    public int getScoreId() {
//        return scoreId;
//    }

//    public void setScoreId(int scoreId) {
//        this.scoreId = scoreId;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int playerScore) {
        this.score = playerScore;
    }
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
