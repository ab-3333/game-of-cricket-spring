package com.tekion.cricketgamespring.controller;

import com.tekion.cricketgamespring.model.Team;
import com.tekion.cricketgamespring.service.PlayerService;
import com.tekion.cricketgamespring.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/team")
public class TeamController {
    private TeamService teamService;
    private PlayerService playerService;

    @Autowired
    public TeamController(TeamService teamService,PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @PostMapping
    public Team addTeam(@RequestBody Team team) {
        try {
            return teamService.addTeams(team);
        } catch(Exception e) {
            throw e;
        }
    }

    @GetMapping("/all")
    public List<Team> getTeams() {
        return teamService.getTeams();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTeamById(@PathVariable int id) {
        playerService.deletePlayers(id);
        teamService.deleteTeamById(id);
    }

    @PutMapping("/update")
    public void updateTeam(@RequestBody Team team){
        teamService.addTeams(team);
    }

    @GetMapping("/find")
    public Team findTeam(@RequestBody String name) {
        return teamService.findByName(name);
    }

//    @PutMapping("/team/update")
//    public void updateTeam(@RequestBody Team teamDel, Team teamAdd){
//        teamService.updateTeam(teamDel,teamAdd);
//    }
}
