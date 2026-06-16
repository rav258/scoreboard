package com.spradar;

import com.spradar.api.ScoreBoard;
import com.spradar.domain.Match;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryScoreBoardTest {

    @Test
    void shouldStartGameWithInitialScoreZeroZero() {
        ScoreBoard scoreBoard = new InMemoryScoreBoard();

        Match match = scoreBoard.startGame("Mexico", "Canada");

        assertEquals("Mexico", match.getHomeTeam());
        assertEquals("Canada", match.getAwayTeam());
        assertEquals(0, match.getHomeScore());
        assertEquals(0, match.getAwayScore());
    }

    @Test
    void shouldFinishGame() {
        ScoreBoard scoreBoard = new InMemoryScoreBoard();

        scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.finishGame("Mexico", "Canada");

        assertTrue(scoreBoard.getSummary().isEmpty());
    }

    @Test
    void shouldUpdateScore() {
        ScoreBoard scoreBoard = new InMemoryScoreBoard();

        scoreBoard.startGame("Mexico", "Canada");

        Match match = scoreBoard.updateScore("Mexico", "Canada", 0, 5);

        assertEquals(0, match.getHomeScore());
        assertEquals(5, match.getAwayScore());
    }

    @Test
    void shouldReturnCurrentGames() {

        ScoreBoard scoreBoard = new InMemoryScoreBoard();

        scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.startGame("Spain", "Brazil");

        List<Match> summary = scoreBoard.getSummary();

        assertEquals(2, summary.size());
    }

    @Test
    void shouldRemoveFinishedGameFromSummary() {

        ScoreBoard scoreBoard = new InMemoryScoreBoard();

        scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.finishGame("Mexico", "Canada");

        assertTrue(scoreBoard.getSummary().isEmpty());
    }
}