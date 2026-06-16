package com.spradar.api;

import com.spradar.domain.Match;

import java.util.List;

public interface ScoreBoard {

    Match startGame(String homeTeam, String awayTeam);

    void finishGame(String homeTeam, String awayTeam);

    List<Match> getGames();

    Match updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore);
}