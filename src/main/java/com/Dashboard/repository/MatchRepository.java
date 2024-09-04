package com.Dashboard.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Dashboard.model.Match;

@Repository
public interface MatchRepository extends CrudRepository<Match, Integer> {

    List<Match> findByhomeTeamNameOrAwayTeamNameOrderByDatetimeDesc(String homeTeamName, String awayTeamName,
            Pageable pageable);

    default List<Match> findLatestMatchesByTeam(String teamName, int count) {
        return findByhomeTeamNameOrAwayTeamNameOrderByDatetimeDesc(teamName, teamName, PageRequest.of(0, count));
    }
}
