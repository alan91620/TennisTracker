package com.example.myapplication.Model;
import java.util.ArrayList;



public class HistoryMatch {
    private String joueur1FirstName;
    private String joueur2FirstName;
    private String joueur1LastName;
    private String joueur2LastName;
    private String adresseMatch;
    ArrayList <String> GameHist;



    public HistoryMatch(String joueur1FirstName, String joueur2FirstName, String joueur1LastName, String joueur2LastName, ArrayList <String> Score, String streetName) {

        this.joueur1FirstName = joueur1FirstName;
        this.joueur2FirstName = joueur2FirstName;
        this.joueur1LastName = joueur1LastName;
        this.joueur2LastName = joueur2LastName;
        this.adresseMatch = streetName;
        GameHist = new ArrayList<>();
        GameHist.set(0,Score.get(0));
        GameHist.set(1,Score.get(1));
        GameHist.set(2,Score.get(2));
        GameHist.set(3,Score.get(3));
        GameHist.set(4,Score.get(4));
        GameHist.set(5,Score.get(5));
        GameHist.set(6,Score.get(6));
        GameHist.set(7,Score.get(7));
        GameHist.set(8,Score.get(8));
        GameHist.set(9,Score.get(9));
    }
}
