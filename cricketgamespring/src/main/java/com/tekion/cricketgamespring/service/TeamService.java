package com.tekion.cricketgamespring.service;

import com.tekion.cricketgamespring.model.Team;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TeamService {
    Team  addTeams(Team team);

    List<Team> getTeams();

//    void updateTeam(Team teamDel, Team teamAdd);
    void deleteTeamById(int id);
    void updateTeam(Team team);

    Team findByName(String name);

//    List<Team> getWinningTeams(List<String> winners);
}
