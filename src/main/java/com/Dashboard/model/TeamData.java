package com.Dashboard.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class TeamData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int teamID;
    private String teamName;
    private int totalMatches;
    private int totalWins;

    @Transient
    List<Match> matches;

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTotalMatches() {
        return totalMatches;
    }

    public void setTotalMatches(int totalMatches) {
        this.totalMatches = totalMatches;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(int totalWins) {
        this.totalWins = totalWins;
    }

    @Override
    public String toString() {
        return "TeamData [teamID=" + teamID + ", teamName=" + teamName + ", totalMatches=" + totalMatches
                + ", totalWins=" + totalWins + "]";
    }

    public TeamData(String teamName, int totalMatches) {
        this.teamName = teamName;
        this.totalMatches = totalMatches;
    }

    public TeamData() {
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

}
