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

    }

    public String getJoueur1FirstName() {
        return joueur1FirstName;
    }

    public String getJoueur2FirstName() {
        return joueur2FirstName;
    }

    public String getJoueur1LastName() {
        return joueur1LastName;
    }

    public String getJoueur2LastName() {
        return joueur2LastName;
    }

    public String getAdresseMatch() {
        return adresseMatch;
    }

    public ArrayList<String> getGameHist() {
        return GameHist;
    }
}


