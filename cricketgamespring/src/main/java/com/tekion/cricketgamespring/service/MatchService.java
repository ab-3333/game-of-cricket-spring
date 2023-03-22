package com.tekion.cricketgamespring.service;

import com.tekion.cricketgamespring.model.Match;
import com.tekion.cricketgamespring.model.ScoreCard;
import com.tekion.cricketgamespring.model.Team;

import java.util.List;


public interface MatchService {
    int definedOvers = 20;
    int definedWickets = 10;

    void matchStart(List<Team> teams);

//    List<String> findWinners(int id);

    int batting(Match match, Team team, int target, ScoreCard scoreCard, List<ScoreCard> scoreCardList);

}
