package com.tekion.cricketgamespring.service.impl;

import com.tekion.cricketgamespring.model.ScoreList;
import com.tekion.cricketgamespring.repo.ScoreListRepo;
import com.tekion.cricketgamespring.service.ScoreListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreListServiceImpl implements ScoreListService {
    private ScoreListRepo scoreListRepo;

    @Autowired
    public ScoreListServiceImpl(ScoreListRepo scoreListRepo) {
        this.scoreListRepo = scoreListRepo;
    }

    @Override
    public void addScoreCardList(ScoreList scl) {
        scoreListRepo.save(scl);
    }
}
