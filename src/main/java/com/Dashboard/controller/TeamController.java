package com.Dashboard.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Dashboard.model.Match;
import com.Dashboard.model.TeamData;
import com.Dashboard.repository.MatchRepository;
import com.Dashboard.repository.TeamRepository;

@RestController
@CrossOrigin
public class TeamController {

    private TeamRepository teamRepository;
    private MatchRepository matchRepository;

    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/Teams/{teamName}")
    public TeamData getTeam(@PathVariable String teamName) {
        TeamData team = teamRepository.findByTeamName(teamName);
        team.setMatches(
                matchRepository.findLatestMatchesByTeam(teamName, 4));
        return team;
    }

    @GetMapping("/Teams/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year) {
        LocalDateTime startDate = LocalDateTime.of(year, 1, 1, 12, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(year + 1, 1, 1, 12, 0, 0);
        // List<Match> resultOfMathes = new ArrayList<>();
        // matchRepository.getTeamByYear1(teamName, startDate.getYear(),
        // endDate.getYear())
        // .forEach(str -> resultOfMathes.add(str));
        // matchRepository.getTeamByYear2(teamName, startDate.getYear(),
        // endDate.getYear())
        // .forEach(str -> resultOfMathes.add(str));
        // return resultOfMathes;
        return matchRepository.getTeamByDate(teamName, startDate, endDate);

    }
}
