package com.spradar;

import com.spradar.api.ScoreBoard;
import com.spradar.domain.GameKey;
import com.spradar.domain.Match;
import com.spradar.exception.ScoreBoardException;
import com.spradar.util.ValidationUtil;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryScoreBoard implements ScoreBoard {

    private final Map<GameKey, Match> matches = new HashMap<>();
    private long sequence = 0;

    @Override
    public Match startGame(String homeTeam, String awayTeam) {
        ValidationUtil.validateTeams(homeTeam, awayTeam);

        GameKey key = new GameKey(homeTeam, awayTeam);

        if (matches.containsKey(key)) {
            throw new ScoreBoardException(String.format("Game %s vs %s already exists!", homeTeam, awayTeam));
        }

        Match match = new Match(homeTeam, awayTeam, ++sequence);
        matches.put(key, match);
        return match;
    }

    @Override
    public void finishGame(String homeTeam, String awayTeam) {
        GameKey key = new GameKey(homeTeam, awayTeam);
        Match removed = matches.remove(key);

        if (removed == null) {
            throw new ScoreBoardException(String.format("Game %s vs %s not found", homeTeam, awayTeam));
        }
    }

    @Override
    public List<Match> getSummary() {
        return matches.values().stream()
                .sorted(Comparator.comparingInt(Match::getTotalScore).reversed()
                        .thenComparing(Comparator.comparingLong(Match::getSequence).reversed()))
                .toList();
    }

    @Override
    public Match updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        ValidationUtil.validateScore(homeScore, awayScore);
        GameKey key = new GameKey(homeTeam, awayTeam);

        Match match = matches.get(key);

        if (match == null) {
            throw new ScoreBoardException(String.format("Game %s vs %s not found", homeTeam, awayTeam));
        }

        match.updateScore(homeScore, awayScore);
        return match;
    }
}
