package com.tekion.cricketgamespring.service.impl;

import com.tekion.cricketgamespring.model.Tournament;
import com.tekion.cricketgamespring.repo.TournamentRepo;
import com.tekion.cricketgamespring.service.TournamentService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TournamentServiceImpl implements TournamentService {
    private TournamentRepo tournamentRepo;

    public TournamentServiceImpl(TournamentRepo tournamentRepo) {
        this.tournamentRepo = tournamentRepo;
    }

    @Override
    public List<Tournament> getTournaments() {
        return tournamentRepo.findAll();
    }
}
