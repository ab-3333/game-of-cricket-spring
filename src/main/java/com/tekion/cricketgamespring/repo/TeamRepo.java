package com.tekion.cricketgamespring.repo;

import com.tekion.cricketgamespring.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TeamRepo extends JpaRepository<Team,Integer> {
    Team findByName(String name);
    @Transactional
    @Modifying
    void deleteTeamById(int id);



    @Query("select t from Team t where t.name in (:winners)")
    List<Team> getWinningTeams(List<String> winners);

//    void updateTeam(Team team);
//    @Transactional
//    @Modifying
//    @Query(value="update Team t where ")
//    void updateTeam(Team team);
}
