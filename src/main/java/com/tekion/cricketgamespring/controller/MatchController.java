package com.tekion.cricketgamespring.controller;
import com.tekion.cricketgamespring.model.Team;
import com.tekion.cricketgamespring.service.MatchService;
import com.tekion.cricketgamespring.service.impl.MatchServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/match")
public class MatchController {
    private MatchService matchService;
    private Logger logger = LogManager.getLogger(MatchController.class);
    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/play")
    public void play(@RequestBody List<Team> teamList){
//        logger.info(teamList);
        matchService.matchStart(teamList);
    }
}
