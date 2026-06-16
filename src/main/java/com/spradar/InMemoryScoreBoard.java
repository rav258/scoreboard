package com.spradar;

import com.spradar.api.ScoreBoard;
import com.spradar.domain.Match;

public class InMemoryScoreBoard implements ScoreBoard {

    @Override
    public Match startGame(String homeTeam, String awayTeam) {
        return new Match(homeTeam, awayTeam);
    }

}
