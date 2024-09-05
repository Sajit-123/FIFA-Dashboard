package com.Dashboard.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
