package com.tekion.cricketgamespring.service.impl;

import com.tekion.cricketgamespring.repo.PlayerRepo;
import com.tekion.cricketgamespring.model.Player;
import com.tekion.cricketgamespring.model.Team;
import com.tekion.cricketgamespring.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    private PlayerRepo playerRepo;
    @Autowired
    public PlayerServiceImpl(PlayerRepo playerRepo){
        this.playerRepo = playerRepo;
    }

    @Override
    public void addPlayers(Player player) {
        playerRepo.save(player);
    }

    @Override
    public List<Player> getPlayersByTeamId(int id) {
        return playerRepo.getPlayersByTeamId(id);
    }

    @Override
    public void updatePlayers(int id) {

    }

    @Override
    public void deletePlayers(int id) {
        playerRepo.deleteByTeamId(id);
    }

    public void addIndvPlayers(Team team){
        for(int i=1;i<12;i++){
            Player player = new Player();
            if(i<8){
                player.setName(team.getName()+i);
                player.setType("Batsman");
                player.setTeam(team);
            }
            else {
                player.setName(team.getName()+i);
                player.setType("Bowler");
                player.setTeam(team);
            }
            addPlayers(player);
        }
    }

    @Override
    public void addScore(int score, int id) {
        playerRepo.addScore(score,id);
    }

}
