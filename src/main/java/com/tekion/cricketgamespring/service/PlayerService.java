package com.tekion.cricketgamespring.service;

import com.tekion.cricketgamespring.model.Player;
import com.tekion.cricketgamespring.model.Team;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public interface PlayerService {
    void addPlayers(Player player);
    List<Player> getPlayersByTeamId(int id);
    void updatePlayers(int id);
    void deletePlayers(int id);

    void addIndvPlayers(Team team);
    void addScore(int score,int id);
}
