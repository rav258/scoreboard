package com.spradar.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Match {

    private final String homeTeam;
    private final String awayTeam;
    private int homeScore;
    private int awayScore;

    private final long sequence;

    public Match(String homeTeam, String awayTeam, long sequence) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;

        this.sequence = sequence;
    }

    public void updateScore(int homeScore, int awayScore) {
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public int getTotalScore() {
        return homeScore + awayScore;
    }
}
