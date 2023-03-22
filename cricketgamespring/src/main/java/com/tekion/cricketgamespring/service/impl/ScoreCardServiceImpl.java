package com.tekion.cricketgamespring.service.impl;

import com.tekion.cricketgamespring.repo.ScoreCardRepo;
import com.tekion.cricketgamespring.service.ScoreCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreCardServiceImpl implements ScoreCardService {
    private ScoreCardRepo scoreCardRepo;

    @Autowired
    public ScoreCardServiceImpl(ScoreCardRepo scoreCardRepo) {
        this.scoreCardRepo = scoreCardRepo;
    }

    @Override
    public void deleteScoreByTeam(int id) {
        scoreCardRepo.deleteScoreByTeam(id);
    }
}
