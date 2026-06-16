package com.spradar;

import com.spradar.api.ScoreBoard;
import com.spradar.domain.Match;
import com.spradar.exception.ScoreBoardException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Test
    void shouldReturnSummaryOrderedByTotalScoreDescending() {
        ScoreBoard scoreBoard = new InMemoryScoreBoard();

        scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.updateScore("Mexico", "Canada", 0, 5);

        scoreBoard.startGame("Spain", "Brazil");
        scoreBoard.updateScore("Spain", "Brazil", 10, 2);

        scoreBoard.startGame("Germany", "France");
        scoreBoard.updateScore("Germany", "France", 2, 2);

        List<Match> summary = scoreBoard.getSummary();

        assertEquals("Spain", summary.get(0).getHomeTeam());
        assertEquals("Mexico", summary.get(1).getHomeTeam());
        assertEquals("Germany", summary.get(2).getHomeTeam());
    }

    @Test
    void shouldOrderGamesWithSameTotalScoreByMostRecentlyStarted() {

        ScoreBoard scoreBoard = new InMemoryScoreBoard();

        scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.updateScore("Mexico", "Canada", 1, 1);

        scoreBoard.startGame("Spain", "Brazil");
        scoreBoard.updateScore("Spain", "Brazil", 1, 1);

        List<Match> summary = scoreBoard.getSummary();

        assertEquals("Spain", summary.get(0).getHomeTeam());
        assertEquals("Mexico", summary.get(1).getHomeTeam());
    }

    @Test
    void shouldRejectBlankHomeTeam() {
        ScoreBoard scoreBoard = new InMemoryScoreBoard();

        assertThrows(ScoreBoardException.class, () -> scoreBoard.startGame(" ", "Canada"));
    }

    @Test
    void shouldRejectBlankAwayTeam() {
        ScoreBoard scoreBoard = new InMemoryScoreBoard();

        assertThrows(ScoreBoardException.class, () -> scoreBoard.startGame("Mexico", " "));
    }

    @Test
    void shouldRejectSameTeams() {
        ScoreBoard scoreBoard = new InMemoryScoreBoard();

        assertThrows(ScoreBoardException.class, () -> scoreBoard.startGame("Mexico", "Mexico"));
    }

    @Test
    void shouldRejectNegativeScores() {
        ScoreBoard scoreBoard = new InMemoryScoreBoard();

        scoreBoard.startGame("Mexico", "Canada");

        assertThrows(ScoreBoardException.class, () -> scoreBoard.updateScore("Mexico", "Canada", -1, 0));
    }

    @Test
    void shouldRejectUpdatingUnknownGame() {
        ScoreBoard scoreBoard = new InMemoryScoreBoard();

        assertThrows(ScoreBoardException.class, () -> scoreBoard.updateScore("Mexico", "Canada", 1, 0));
    }

    @Test
    void shouldRejectDuplicateActiveGame() {
        ScoreBoard board = new InMemoryScoreBoard();

        board.startGame("Mexico", "Canada");

        assertThrows(ScoreBoardException.class, () -> board.startGame("Mexico", "Canada"));
    }
}