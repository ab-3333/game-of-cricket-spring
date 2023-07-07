package com.tekion.cricketgamespring.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matchId;
    @OneToMany
    private List<ScoreList> ScoreCard;
    private String winner;
    private String loser;



//    private int teamId;
//
//    public int getTeamId() {
//        return teamId;
//    }
//
//    public void setTeamId(int teamId) {
//        this.teamId = teamId;
//    }

    public List<ScoreList> getScoreCard() {
        return ScoreCard;
    }

    public void setScoreCard(List<ScoreList> scoreCard) {
        ScoreCard = scoreCard;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getLoser() {
        return loser;
    }

    public void setLoser(String loser) {
        this.loser = loser;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }


}
