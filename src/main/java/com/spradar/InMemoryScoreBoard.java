package com.spradar;

import com.spradar.api.ScoreBoard;
import com.spradar.domain.Match;

import java.util.ArrayList;
import java.util.List;

public class InMemoryScoreBoard implements ScoreBoard {

    private final List<Match> matches = new ArrayList<>();

    @Override
    public Match startGame(String homeTeam, String awayTeam) {
        Match match = new Match(homeTeam, awayTeam);
        matches.add(match);
        return match;
    }

    @Override
    public void finishGame(String homeTeam, String awayTeam) {
        matches.removeIf(match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam));
    }

    @Override
    public List<Match> getGames() {
        return new ArrayList<>(matches);
    }
}
