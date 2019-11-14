package com.example.myapplication.Model;


import java.util.ArrayList;

public class TennisGame {

    private int playerOnePoints = 0;
    private int playerOneGames = 0;
    private int playerOneSets = 0;
    private int playerTwoPoints = 0;
    private int playerTwoGames = 0;
    private int playerTwoSets = 0;
    private Player playerOne;
    private Player playerTwo;

    public TennisGame(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public void PlayerOneScore(){
        playerOnePoints ++;
        checkForGame();
    }

    public void PlayerTwoScore(){
        playerTwoPoints ++;
        checkForGame();
    }

    public String convertPoints(int point){
        String cvtPoint = "0";
        if (point == 0){
            cvtPoint = "0";
        }
        else if (point == 1){
            cvtPoint = "15";
        }
        else if (point == 2){
            cvtPoint = "30";
        }
        else if (point == 3){
            cvtPoint = "40";
        }
        else if (point == 4){
            cvtPoint = "AV";
        }
        return cvtPoint;
    }

    public ArrayList<String> getScore(){
        ArrayList<String> scores = new ArrayList<>();
        scores.add(0,convertPoints(playerOnePoints));
        scores.add(1,String.valueOf(playerOneGames));
        scores.add(2,String.valueOf(playerOneSets));
        scores.add(3,convertPoints(playerTwoPoints));
        scores.add(4,String.valueOf(playerTwoGames));
        scores.add(5,String.valueOf(playerTwoSets));

        return  scores;
    }

    public void resetPoints(){
        playerOnePoints = 0;
        playerTwoPoints = 0;
    }


    public void checkForGame() {
        if ((playerOnePoints == 4 && playerTwoPoints <= 2) || (playerTwoPoints == 4 && playerOnePoints <= 2)) {
            if (playerOnePoints == 4) {
                resetPoints();
                playerOneGames++;
            } else if ((playerTwoPoints == 4)) {
                resetPoints();
                playerTwoGames++;
            }
        }
        if (playerOnePoints == 4 && playerTwoPoints == 4) {
            playerOnePoints = 3;
            playerTwoPoints = 3;
        }
        if ((playerOnePoints == 5 && playerTwoPoints <= 3) || (playerTwoPoints == 5 && playerOnePoints <= 5)) {
            if (playerOnePoints == 5) {
                resetPoints();
                playerOneGames++;
            } else if ((playerTwoPoints == 5)) {
                resetPoints();
                playerTwoGames++;
            }
        }
    }














    public int getPlayerOnePoints() {
        return playerOnePoints;
    }

    public void setPlayerOnePoints(int playerOnePoints) {
        this.playerOnePoints = playerOnePoints;
    }

    public int getPlayerOneGames() {
        return playerOneGames;
    }

    public void setPlayerOneGames(int playerOneGames) {
        this.playerOneGames = playerOneGames;
    }

    public int getPlayerOneSets() {
        return playerOneSets;
    }

    public void setPlayerOneSets(int playerOneSets) {
        this.playerOneSets = playerOneSets;
    }

    public int getPlayerTwoPoints() {
        return playerTwoPoints;
    }

    public void setPlayerTwoPoints(int playerTwoPoints) {
        this.playerTwoPoints = playerTwoPoints;
    }

    public int getPlayerTwoGames() {
        return playerTwoGames;
    }

    public void setPlayerTwoGames(int playerTwoGames) {
        this.playerTwoGames = playerTwoGames;
    }

    public int getPlayerTwoSets() {
        return playerTwoSets;
    }

    public void setPlayerTwoSets(int playerTwoSets) {
        this.playerTwoSets = playerTwoSets;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }
}
