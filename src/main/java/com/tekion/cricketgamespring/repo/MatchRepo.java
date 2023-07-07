package com.tekion.cricketgamespring.repo;

import com.tekion.cricketgamespring.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepo extends JpaRepository<Match,Integer> {

//    @Query("select m.winner from Match m")
//    List<String> findWinners();

//    @Query("select m.winner from Match m where m.matchId =: id")
//    List<String> findWinners(@Param("id") int id);
}
