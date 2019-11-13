package com.example.myapplication.Model;


public class TennisGame {

    private int playerOneScore = 0;
    private int playerTwoScore = 0;
    private Player playerOne;
    private Player playerTwo;

    public TennisGame(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public String getScore() {

        if (hasWinner()) {
            return playerWithHighestScore() + " wins";
        }

        if (hasAdvantage()) {
            return "Advantage " + playerWithHighestScore();
        }

        if (isDeuce())
            return "Deuce";

        if(playerOneScore == playerTwoScore) {
            return translateScore(playerOneScore) + " all";
        }

        return translateScore(playerOneScore) + "," + translateScore(playerTwoScore);
    }

    private boolean isDeuce() {
        return playerOneScore >= 3 && playerTwoScore == playerOneScore;
    }

    private String playerWithHighestScore() {
        if (playerOneScore > playerTwoScore) {
            return playerOne.toString();
        } else {
            return playerTwo.toString();
        }
    }

    private boolean hasWinner() {
        if(playerTwoScore >= 4 && playerTwoScore >= playerOneScore + 2 )
            return true;
        if(playerOneScore >= 4 && playerOneScore >= playerTwoScore + 2)
            return true;
        return false;
    }

    private boolean hasAdvantage() {
        if (playerTwoScore >= 4 && playerTwoScore == playerOneScore + 1)
            return true;
        if (playerOneScore >= 4 && playerOneScore == playerTwoScore + 1)
            return true;

        return false;

    }

    public void playerOneScores() {
        playerOneScore++;
    }

    public void playerTwoScores() {
        playerTwoScore++;
    }

    private String translateScore(int score) {
        switch (score) {
            case 3:
                return "Forty";
            case 2:
                return "Thirty";
            case 1:
                return "Fifteen";
            case 0:
                return "Love";
        }
        throw new IllegalArgumentException("Illegal score: " + score);
    }
}
