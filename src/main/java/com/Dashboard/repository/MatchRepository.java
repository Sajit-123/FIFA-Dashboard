package com.Dashboard.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Dashboard.model.Match;

@Repository
public interface MatchRepository extends CrudRepository<Match, Integer> {

        List<Match> findByhomeTeamNameOrAwayTeamNameOrderByDateTimeDesc(String homeTeamName, String awayTeamName,
                        Pageable pageable);

        @Query("select u from Match u where (u.homeTeamName=:h or u.awayTeamName=:h) and u.dateTime between :s and :e order by u.dateTime desc")
        List<Match> getTeamByDate(@Param("h") String TeamName,
                        @Param("s") LocalDateTime startDate, @Param("e") LocalDateTime endDate);

        // @Query("select u from Match u where u.year between :s and :e and u.homeTeamName=:h order by u.dateTime desc")
        // List<Match> getTeamByYear1(@Param("h") String TeamName,
        //                 @Param("s") int startDate, @Param("e") int endDate);

        // @Query("select u from Match u where u.year between :s and :e and u.awayTeamName=:h order by u.dateTime desc")
        // List<Match> getTeamByYear2(@Param("h") String TeamName,
        //                 @Param("s") int startDate, @Param("e") int endDate);

        default List<Match> findLatestMatchesByTeam(String teamName, int count) {
                return findByhomeTeamNameOrAwayTeamNameOrderByDateTimeDesc(teamName, teamName,
                                PageRequest.of(0, count));
        }
}
