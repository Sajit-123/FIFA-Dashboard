package com.Dashboard.data;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.Dashboard.model.Match;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

    private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

    @Override
    public Match process(final MatchInput matchInput) {
        Match match = new Match();
        final String winTeam;
        match.setYear((Integer.parseInt(matchInput.getYear())));
        match.setDateTime(LocalDateTime.parse(matchInput.getDatetime()));
        match.setStage(matchInput.getStage());
        match.setStadium(matchInput.getStadium());
        match.setCity(matchInput.getCity());
        match.setAwayTeamName(matchInput.getAway_team_name());
        match.setHomeTeamGoals(Integer.parseInt(matchInput.getHome_team_goals()));
        match.setAwayTeamGoals(Integer.parseInt(matchInput.getAway_team_goals()));
        match.setHomeTeamName(matchInput.getHome_team_name());
        match.setWinConditions(matchInput.getWin_conditions());
        if (match.getHomeTeamGoals() - match.getAwayTeamGoals() == 0) {
            winTeam = match.getWinConditions();
        } else {
            winTeam = match.getHomeTeamGoals() - match.getAwayTeamGoals() > 0 ? matchInput.getHome_team_name()
                    : matchInput.getAway_team_name();
        }
        match.setAttendance(matchInput.getAttendance());
        match.setHalfTimeHomeGoals(Integer.parseInt(matchInput.getHalf_time_home_goals()));
        match.setHalfTimeAwayGoals(Integer.parseInt(matchInput.getHalf_time_away_goals()));
        match.setReferee(matchInput.getReferee());
        match.setAssistant1(matchInput.getAssistant1());
        match.setAssistant2(matchInput.getAssistant2());
        match.setMatchID(Integer.parseInt(matchInput.getMatch_id()));
        match.setRoundID(Integer.parseInt(matchInput.getRound_id()));
        match.setHomeTeamInitials(matchInput.getHome_team_initials());
        match.setAwayTeamInitials(matchInput.getAway_team_initials());
        match.setWinningTeam(winTeam);

        log.info("object created " + " " + match.getWinningTeam());

        return match;
    }
}
