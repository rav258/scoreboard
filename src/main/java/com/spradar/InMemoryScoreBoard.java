package com.spradar;

import com.spradar.api.ScoreBoard;
import com.spradar.domain.Match;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InMemoryScoreBoard implements ScoreBoard {

    private final List<Match> matches = new ArrayList<>();
    private long sequence = 0;

    @Override
    public Match startGame(String homeTeam, String awayTeam) {
        Match match = new Match(homeTeam, awayTeam, ++sequence);
        matches.add(match);
        return match;
    }

    @Override
    public void finishGame(String homeTeam, String awayTeam) {
        matches.removeIf(match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam));
    }

    @Override
    public List<Match> getSummary() {
        return matches.stream()
                .sorted(Comparator.comparingInt(Match::getTotalScore).reversed()
                        .thenComparing(Comparator.comparingLong(Match::getSequence).reversed()))
                .toList();
    }

    @Override
    public Match updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        Match match = findMatch(homeTeam, awayTeam);
        match.updateScore(homeScore, awayScore);
        return match;
    }

    private Match findMatch(String homeTeam, String awayTeam) {
        return matches.stream()
                .filter(match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam))
                .findFirst()
                .orElseThrow();
    }
}
