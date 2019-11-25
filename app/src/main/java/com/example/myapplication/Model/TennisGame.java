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
    Boolean p1isServing = false;
    Boolean p2isServing = false;
    Boolean p1StartServing = false;
    Boolean p2StartServing = false;
    int setNum;
    ArrayList <String> GameHist;

    public TennisGame(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        randServingPlayer();
        this.setNum = 1;
        GameHist = new ArrayList<>();

        GameHist.add(0,"0");
        GameHist.add(1,"0");
        GameHist.add(2,"0");
        GameHist.add(3,"0");
        GameHist.add(4,"0");
        GameHist.add(5,"0");
        GameHist.add(6,"0");
        GameHist.add(7,"0");
        GameHist.add(8,"0");
        GameHist.add(9,"0");
    }

    public void randServingPlayer(){
        try {
            int gen = (Math.random() <= 0.5) ? 1 : 2;
            if (gen == 1){
                p1isServing = true;
                p1StartServing = true;
            }
            else if (gen == 2){
                p2isServing = true;
                p2StartServing = true;
            }
            else {
                throw (new Exception("Error"));
            }
        }
        catch (Exception e){
        }
    }

    public void swapService(){
        Boolean mem;
        mem = p1isServing;
        p1isServing = p2isServing;
        p2isServing = mem;
    }

    public void PlayerOneScore(){
        playerOnePoints ++;
        checkForGame();
    }

    public void PlayerTwoScore(){
        playerTwoPoints ++;
        checkForGame();
    }

    public void PlayerOneScoreGame(){
        playerOneGames ++;
        swapService();
        checkForSet();
    }

    public void PlayerTwoScoreGame(){
        playerTwoGames ++;
        swapService();
        checkForSet();
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

    public void resetGames(){
        playerOneGames = 0;
        playerTwoGames = 0;
    }


    public void checkForGame() {
        if ((playerOnePoints == 4 && playerTwoPoints <= 2) || (playerTwoPoints == 4 && playerOnePoints <= 2)) {
            if (playerOnePoints == 4) {
                resetPoints();
                PlayerOneScoreGame();
            } else if ((playerTwoPoints == 4)) {
                resetPoints();
                PlayerTwoScoreGame();
            }
        }
        if (playerOnePoints == 4 && playerTwoPoints == 4) {
            playerOnePoints = 3;
            playerTwoPoints = 3;
        }
        if ((playerOnePoints == 5 && playerTwoPoints <= 3) || (playerTwoPoints == 5 && playerOnePoints <= 5)) {
            if (playerOnePoints == 5) {
                resetPoints();
                PlayerOneScoreGame();
            } else if ((playerTwoPoints == 5)) {
                resetPoints();
                PlayerTwoScoreGame();
            }
        }
    }

    public void addGamesHistory(){
        switch (setNum){
            case 1 :
                GameHist.add(0,String.valueOf(playerOneGames));
                GameHist.add(1,String.valueOf(playerTwoGames));
                break;
            case 2 :
                GameHist.add(2,String.valueOf(playerOneGames));
                GameHist.add(3,String.valueOf(playerTwoGames));
                break;
            case 3 :
                GameHist.add(4,String.valueOf(playerOneGames));
                GameHist.add(5,String.valueOf(playerTwoGames));
                break;
            case 4 :
                GameHist.add(6,String.valueOf(playerOneGames));
                GameHist.add(7,String.valueOf(playerTwoGames));
                break;
            case 5 :
                GameHist.add(8,String.valueOf(playerOneGames));
                GameHist.add(9,String.valueOf(playerTwoGames));
                break;
        }
    }


    public void checkForSet(){
        //if (p1isServing == true){
            if (playerOneGames > playerTwoGames+1 && playerOneGames >= 6){
                playerOneSets ++;
                addGamesHistory();
                setNum++;
                resetGames();
            }
            else if (playerTwoGames > playerOneGames+1 && playerTwoGames >= 6){
                playerTwoSets ++;
                addGamesHistory();
                setNum++;
                resetGames();
            }
        /**}
        else if (p2isServing == true){
            if (playerOneGames > playerTwoGames+1 && playerOneGames >= 6){
                playerOneSets ++;
                resetGames();
            }
            else if (playerTwoGames > playerOneGames+1 && playerTwoGames >= 6){
                playerTwoSets ++;
                resetGames();
            }
        }
    }**/
    }


    public ArrayList<String> getGameHist() {
        return GameHist;
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
