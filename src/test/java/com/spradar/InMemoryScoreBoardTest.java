package com.spradar;

import com.spradar.api.ScoreBoard;
import com.spradar.domain.Match;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InMemoryScoreBoardTest {

    @Test
    void shouldStartGameWithInitialScoreZeroZero() {
        ScoreBoard scoreBoard = new InMemoryScoreBoard();

        Match match = scoreBoard.startGame("Mexico", "Canada");

        Assertions.assertEquals("Mexico", match.getHomeTeam());
        Assertions.assertEquals("Canada", match.getAwayTeam());
        Assertions.assertEquals(0, match.getHomeScore());
        Assertions.assertEquals(0, match.getAwayScore());
    }

    @Test
    void shouldFinishGame() {
        ScoreBoard scoreBoard = new InMemoryScoreBoard();

        scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.finishGame("Mexico", "Canada");

        Assertions.assertTrue(scoreBoard.getGames().isEmpty());
    }
}