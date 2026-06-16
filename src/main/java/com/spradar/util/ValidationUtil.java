package com.spradar.util;

import com.spradar.exception.ScoreBoardException;

public class ValidationUtil {

    public static void validateTeams(String homeTeam, String awayTeam) {
        if (homeTeam == null || homeTeam.trim().isEmpty()) {
            throw new ScoreBoardException("Home team is required");
        }

        if (awayTeam == null || awayTeam.trim().isEmpty()) {
            throw new ScoreBoardException("Away team is required");
        }

        if (homeTeam.equals(awayTeam)) {
            throw new ScoreBoardException("Home team and away team must be different");
        }
    }

    public static void validateScore(int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new ScoreBoardException("Score cannot be negative");
        }
    }
}
