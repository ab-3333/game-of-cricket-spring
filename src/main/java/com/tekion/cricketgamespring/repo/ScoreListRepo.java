package com.tekion.cricketgamespring.repo;

import com.tekion.cricketgamespring.model.ScoreList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreListRepo extends JpaRepository<ScoreList, Integer> {
}
