# Football World Cup Score Board

## Overview

This project is a simple in-memory implementation of a Football World
Cup Score Board.

It supports the following operations:

-   Start a game
-   Finish a game
-   Update score
-   Retrieve a summary of games ordered by:
    1.  Total score (descending)
    2.  Most recently started game when total scores are equal

The implementation focuses on:

-   Clean Code
-   SOLID principles
-   Test-Driven Development (TDD)
-   Simplicity
-   In-memory storage

------------------------------------------------------------------------

## Technologies

-   Java 21
-   Maven
-   JUnit 5

------------------------------------------------------------------------

## Public API

``` java
Match startGame(String homeTeam, String awayTeam);

void finishGame(String homeTeam, String awayTeam);

List<Match> getSummary();

Match updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore);
```

------------------------------------------------------------------------

## Example

``` java
ScoreBoard scoreBoard = new InMemoryScoreBoard();

scoreBoard.startGame("Mexico", "Canada");
scoreBoard.updateScore("Mexico", "Canada", 0, 5);

scoreBoard.startGame("Spain", "Brazil");
scoreBoard.updateScore("Spain", "Brazil", 10, 2);

List<Match> summary = scoreBoard.getSummary();
```

------------------------------------------------------------------------

## Assumptions

### Active games must be unique

Only one active game between the same home and away teams is allowed.

Starting the same game twice will result in an exception.

### Team names

-   Team names cannot be null.
-   Team names cannot be blank.
-   Home and away teams must be different.

### Scores

-   Scores cannot be negative.

### Unknown games

Attempting to update or finish a game that does not exist results in an
exception.

### Finished games

Once a game is finished and removed from the scoreboard, another game
with the same teams may be started again.

------------------------------------------------------------------------

## Ordering Rules

Games are ordered by:

1.  Total score descending.
2.  If total scores are equal, the game started most recently is
    displayed first.

Example:

1.  Uruguay 6 - Italy 6
2.  Spain 10 - Brazil 2
3.  Mexico 0 - Canada 5
4.  Argentina 3 - Australia 1
5.  Germany 2 - France 2

------------------------------------------------------------------------

## Internal Design

The scoreboard uses:

-   HashMap\<GameKey, Match\> for storing active games.
-   GameKey record as a value object.
-   Sequence number to preserve insertion order for games with equal
    total scores.

------------------------------------------------------------------------

## Running Tests

``` bash
mvn test
```

------------------------------------------------------------------------
