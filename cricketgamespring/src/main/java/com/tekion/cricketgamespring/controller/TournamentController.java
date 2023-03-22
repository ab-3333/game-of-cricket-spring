package com.tekion.cricketgamespring.controller;

import com.tekion.cricketgamespring.model.Tournament;
import com.tekion.cricketgamespring.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/tournament")
public class TournamentController {
    private TournamentService tournamentService;
    @Autowired
    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }
    @GetMapping
    public List<Tournament> getTournaments(){
        return tournamentService.getTournaments();
    }
}
