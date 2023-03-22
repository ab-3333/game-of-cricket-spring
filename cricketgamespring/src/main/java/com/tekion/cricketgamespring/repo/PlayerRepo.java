package com.tekion.cricketgamespring.repo;

import com.tekion.cricketgamespring.model.Player;
import com.tekion.cricketgamespring.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PlayerRepo extends JpaRepository<Player,Integer> {
    @Modifying
    @Transactional
    @Query(value="delete from Player p where p.team.id=:id")
    void deleteByTeamId(@Param("id") int id);


    @Query("select p from Player p where p.team.id=:id")
    List<Player> getPlayersByTeamId(@Param("id") int id);


    @Modifying
    @Transactional
    @Query("update Player p set p.score=:score where p.id=:id")
    void addScore(@Param("score")int score,@Param("id")int id);
}
