package com.tekion.cricketgamespring.service.impl;

import com.tekion.cricketgamespring.repo.TeamRepo;
import com.tekion.cricketgamespring.model.Team;
import com.tekion.cricketgamespring.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements com.tekion.cricketgamespring.service.TeamService {
    private TeamRepo teamRepo;
    private PlayerService playerService;

    @Autowired
    public TeamServiceImpl(TeamRepo teamRepo , PlayerService playerService) {
        this.teamRepo = teamRepo;
        this.playerService = playerService;
    }


    @Override
    public Team addTeams(Team team) {
        Team checkTeam = teamRepo.findByName(team.getName());
        if(checkTeam != null) throw new Error("A team with this name already exists");
        Team newTeam = teamRepo.save(team);
        playerService.addIndvPlayers(team);
        return newTeam;
    }

    @Override
    public List<Team> getTeams() {
        return teamRepo.findAll();
    }

    @Override
    public void deleteTeamById(int id) {
        teamRepo.deleteTeamById(id);
    }

    @Override
    public Team findByName(String name) {
        return teamRepo.findByName(name);
    }

//    @Override
//    public List<Team> getWinningTeams(List<String> winners) {
//        return teamRepo.getWinningTeams(winners);
//    }

    public void updateTeam(Team team){
        addTeams(team);
    }



//    @Override
//    public void updateTeam(Team teamDel, Team teamAdd) {
//        teamRepo.deleteTeam(teamDel);
//        addTeams(teamAdd);
//    }
}
