package com.tekion.cricketgamespring.service.impl;

import com.tekion.cricketgamespring.model.*;
import com.tekion.cricketgamespring.repo.MatchRepo;
import com.tekion.cricketgamespring.repo.PlayerRepo;
import com.tekion.cricketgamespring.repo.ScoreCardRepo;
import com.tekion.cricketgamespring.repo.TournamentRepo;
import com.tekion.cricketgamespring.service.MatchService;
import com.tekion.cricketgamespring.service.PlayerService;
import com.tekion.cricketgamespring.service.TeamService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class MatchServiceImpl implements MatchService {
    private TeamService teamService;
    private PlayerRepo playerRepo;
    private MatchRepo matchRepo;
    private PlayerService playerService;
    private ScoreCardRepo scoreCardRepo;
    private TournamentRepo tournamentRepo;
    private ScoreListServiceImpl scoreListService;
    private int overs = 0;
    private int wickets = 0;
    private int ballsCount = 0;
    private int totalRuns = 0;
    private Logger logger = LogManager.getLogger(MatchServiceImpl.class);

    @Autowired
    public MatchServiceImpl(TeamService teamService, PlayerRepo playerRepo, MatchRepo matchRepo, PlayerService playerService, ScoreCardRepo scoreCardRepo, TournamentRepo tournamentRepo, ScoreListServiceImpl scoreListService) {
        this.teamService = teamService;
        this.playerRepo = playerRepo;
        this.matchRepo = matchRepo;
        this.playerService = playerService;
        this.scoreCardRepo = scoreCardRepo;
        this.tournamentRepo = tournamentRepo;
        this.scoreListService = scoreListService;
    }

    public void matchStart(List<Team> teams) {
        Tournament tournament = new Tournament();
        List<Team> losingTeams = new ArrayList<>();
        List<Match> matches = new ArrayList<>();
        while (teams.size() > 1) {
            for(int i=0;i<teams.size()-1;i+=2) {
                Team firstTeam = teams.get(i);
                Team secondTeam = teams.get(i + 1);
                Match match = new Match();
                List<ScoreCard> scoreList = new ArrayList<>();
                List<ScoreList> list = new ArrayList<>();
                ScoreCard firstTeamScoreCard = new ScoreCard();
                ScoreCard secondTeamScoreCard = new ScoreCard();
                int resA = batting(match,firstTeam, Integer.MAX_VALUE, firstTeamScoreCard, scoreList, list, 0);
                nextTeam();
                int resB = batting(match,secondTeam, resA, secondTeamScoreCard, scoreList, list, scoreList.size());
                nextTeam();

                Team loser = losing(match, firstTeam, secondTeam, resA, resB);
                losingTeams.add(loser);
                matchRepo.save(match);
                matches.add(match);
//                logger.info(teams.size());
            }
            logger.info(losingTeams.size());
            teams.removeAll(losingTeams);
//            logger.info(teams.size());
        }
        tournament.setWinner(teams.get(0).getName());
        addTournament(tournament, matches);
    }

//    private void teamAlter(List<Team> teams) {
////        List<Team> losingTeams = teamService.getWinningTeams(findWinners());
////        int j=0;
////        for (int i = 0; i < teams.size(); i++) {
////            while(j<losingTeams.size())
////                if(losingTeams.get(i).getName().equals(teams.get(j++).getName()))
////                    teams.remove(j);
////        }
//
//
////            logger.info(losingTeams.toString());
////            logger.info(teams.toString());
////        teams.removeAll(losingTeams);
//    }

    public int batting(Match match,Team team, int target, ScoreCard scoreCard, List<ScoreCard> scoreCards, List<ScoreList> list, int length) {
        int playerNum = 0;
        List<Player> players = playerRepo.getPlayersByTeamId(team.getId());
        int totalRunsbyPerson = 0;
        while (overs < definedOvers && wickets < definedWickets) {
            scoreCard = new ScoreCard();
            scoreCard.setCurrTeam(team);
            int runs = (int) (Math.random() * 8);
            if (ballsCount != 0 && ballsCount % 12 == 0) {
                List<ScoreCard> sc = new ArrayList<>();

                for (int k = (ballsCount + length) - (ballsCount % 12 == 0 ? 12 : ballsCount % 12); k < (ballsCount + length); k++) {
                    sc.add(scoreCards.get(k));
                }
                logger.info(sc.size());
                ScoreList scl = new ScoreList();
                scl.setScorecardList(sc);
                scoreListService.addScoreCardList(scl);
                list.add(scl);
            }
            ballsCount++;
            String strOvers = ballsCount / 6 + "." + ballsCount % 6;
            scoreCard.setRunsCurrBall(Integer.toString(runs));
            if (runs == 7) {
                scoreCard.setRunsCurrBall("W");
                wickets++;
                players.get(playerNum).setScore(totalRunsbyPerson);
                players.get(playerNum).setStatus(true);
                playerService.addScore(players.get(playerNum).getScore(), players.get(playerNum).getId());
                totalRunsbyPerson = 0;
                playerNum++;
            } else {
                totalRunsbyPerson += runs;
                totalRuns += runs;
            }

            if (totalRuns > target) {
                players.get(playerNum).setScore(totalRunsbyPerson);
                players.get(playerNum).setStatus(true);
                playerService.addScore(players.get(playerNum).getScore(), players.get(playerNum).getId());
                scoreCard.setOvers(strOvers);
                scoreCard.setScore(totalRuns);
                scoreCard.setWickets(wickets);
                scoreCardRepo.save(scoreCard);
                scoreCards.add(scoreCard);
                break;
            }
            scoreCard.setOvers(strOvers);
            scoreCard.setScore(totalRuns);
            scoreCard.setWickets(wickets);
            scoreCardRepo.save(scoreCard);
            scoreCards.add(scoreCard);
        }
        match.setScoreCard(list);
        List<ScoreCard> sc = new ArrayList<>();

        for (int k = (ballsCount + length) - (ballsCount % 12 == 0 ? 12 : ballsCount % 12); k < (ballsCount + length); k++) {
            sc.add(scoreCards.get(k));
        }
        logger.info(sc.size());
        ScoreList scl = new ScoreList();
        scl.setScorecardList(sc);
        list.add(scl);
        scoreListService.addScoreCardList(scl);
        return totalRuns;
    }


    void nextTeam() {
        overs = 0;
        wickets = 0;
        ballsCount = 0;
        totalRuns = 0;
    }

    Team losing(Match match, Team firstTeam, Team secondTeam, int firstTeamScore, int secondTeamScore) {
        if (firstTeamScore > secondTeamScore) {
            match.setWinner(firstTeam.getName());
            match.setLoser(secondTeam.getName());
            return secondTeam;
        }
            match.setLoser(firstTeam.getName());
            match.setWinner(secondTeam.getName());
            return firstTeam;
    }

//    public void addMatch(Match match) {
//        matchRepo.save(match);
//    }

    public void addTournament(Tournament tournament, List<Match> matches) {
        tournament.setMatch(matches);
        tournamentRepo.save(tournament);
    }
//    @Override
//    public List<String> findWinners(int id) {
//        return matchRepo.findWinners(id);
//    }

//    void teamAlter(List<Team> teams){
//        List<String> losers = matchRepo.findLosers();
//        int size = losers.size();
//        int j=0;
//            for(int i=0;i<size;i++){
//                if(teams.get(i).getName() == losers.get(j++))
//                    teams.remove();
//        teams = tea
//            }
//    }


//    @Override
//    public void matchIdByTeam(int id) {
//        matchRepo.matchIdByTeam(id);
//    }


}
