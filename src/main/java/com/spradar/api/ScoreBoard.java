package com.spradar.api;

import com.spradar.domain.Match;

public interface ScoreBoard {

    Match startGame(String homeTeam, String awayTeam);

}