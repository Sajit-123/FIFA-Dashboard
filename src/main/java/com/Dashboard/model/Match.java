package com.Dashboard.model;

import java.time.LocalDateTime;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Match {

    @Id
    @Column(name = "match_id")
    private int matchID;
    @Column(unique = false)
    private int year;
    @Column(name = "date_time", unique = false)
    private LocalDateTime datetime;
    @Column(unique = false)
    private String stage;

    @Column(unique = false)
    private String stadium;
    @Column(unique = false)
    private String city;
    @Column(unique = false)
    private String homeTeamName;
    @Column(unique = false)
    private int homeTeamGoals;
    @Column(unique = false)
    private int awayTeamGoals;
    @Column(unique = false)
    private String awayTeamName;
    @Nullable
    @Column(unique = false)
    private String winConditions;
    @Column(unique = false)
    private String attendance;
    @Column(unique = false)
    private int halfTimeHomeGoals;
    @Column(unique = false)
    private int halfTimeAwayGoals;
    @Column(unique = false)
    private String referee;
    @Column(unique = false)
    private String assistant1;
    @Column(unique = false)
    private String assistant2;
    @Column(unique = false)
    private String homeTeamInitials;
    @Column(unique = false)
    private String awayTeamInitials;
    @Column(name = "round_id", unique = false)
    private int roundID;
    // winning_team
    @Column(name = "winning_team", unique = false)
    private String winningTeam;

    public int getRoundID() {
        return roundID;
    }

    public Match() {
    }

    public void setRoundID(int roundID) {
        this.roundID = roundID;
    }

    public long getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public int getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(int homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public int getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(int awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public String getWinConditions() {
        return winConditions;
    }

    public void setWinConditions(String winConditions) {
        this.winConditions = winConditions;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public int getHalfTimeHomeGoals() {
        return halfTimeHomeGoals;
    }

    public void setHalfTimeHomeGoals(int halfTimeHomeGoals) {
        this.halfTimeHomeGoals = halfTimeHomeGoals;
    }

    public int getHalfTimeAwayGoals() {
        return halfTimeAwayGoals;
    }

    public void setHalfTimeAwayGoals(int halfTimeAwayGoals) {
        this.halfTimeAwayGoals = halfTimeAwayGoals;
    }

    public String getReferee() {
        return referee;
    }

    public void setReferee(String referee) {
        this.referee = referee;
    }

    public String getAssistant1() {
        return assistant1;
    }

    public void setAssistant1(String assistant1) {
        this.assistant1 = assistant1;
    }

    public String getAssistant2() {
        return assistant2;
    }

    public void setAssistant2(String assistant2) {
        this.assistant2 = assistant2;
    }

    public String getHomeTeamInitials() {
        return homeTeamInitials;
    }

    public void setHomeTeamInitials(String homeTeamInitials) {
        this.homeTeamInitials = homeTeamInitials;
    }

    public String getAwayTeamInitials() {
        return awayTeamInitials;
    }

    public void setAwayTeamInitials(String awayTeamInitials) {
        this.awayTeamInitials = awayTeamInitials;
    }

    public String getWinningTeam() {
        return winningTeam;
    }

    public void setWinningTeam(String winningTeam) {
        this.winningTeam = winningTeam;
    }

}
