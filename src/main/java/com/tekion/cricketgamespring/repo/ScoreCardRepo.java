package com.tekion.cricketgamespring.repo;

import com.tekion.cricketgamespring.model.ScoreCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ScoreCardRepo extends JpaRepository<ScoreCard,Integer> {
    @Transactional
    @Modifying
    @Query("delete from ScoreCard sc where sc.currTeam.id=:id")
    void deleteScoreByTeam(int id);
}
