package com.example.myapplication.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Controller.MySQLiteOpenHelper;

import java.util.ArrayList;

public class AccesLocal {

    private String nomBase = "bdFlashTenni.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;

    public AccesLocal(Context contexte){
        accesBD = new MySQLiteOpenHelper(contexte, nomBase, null, versionBase);
    }

    /*ajout objet bdd*/

    public void ajout(TennisGame Game){

        bd = accesBD.getWritableDatabase();

        String req = "insert into matchs (joueur1FirstName, joueur2FirstName, joueur1LastName,joueur2LastName,set1ScoreJoueur1,set1ScoreJoueur2,set2ScoreJoueur1,set2ScoreJoueur2,set3ScoreJoueur1,set3ScoreJoueur2,set4ScoreJoueur1,set4ScoreJoueur2,set5ScoreJoueur1, set5ScoreJoueur2, streetName) ";
        req+= "values('"+Game.getPlayer1FirstName()+"','"+Game.getPlayer2FirstName()+"','"+Game.getPlayer1LastName()+"','"+Game.getPlayer2LastName()+"','"+ Game.getGameHist().get(0)+"','"+Game.getGameHist().get(1)+"','"+Game.getGameHist().get(2)+"','"+Game.getGameHist().get(3)+"','"+Game.getGameHist().get(4)+"','"+Game.getGameHist().get(5)+"','"+Game.getGameHist().get(6)+"','"+Game.getGameHist().get(7)+"','"+Game.getGameHist().get(8)+"','"+Game.getGameHist().get(9)+"','"+Game.getAdresseMatch()+"')";

        bd.execSQL(req);
    }


    public HistoryMatch recupDernier(){

        bd = accesBD.getReadableDatabase();
        HistoryMatch historique = null;
        String req = "select* from matchs";
        Cursor curseur = bd.rawQuery(req,null);
        curseur.moveToLast();

        if(!curseur.isAfterLast()){
            ArrayList<String> GameHist = new ArrayList<>();;

            String FirstNameJ1 = curseur.getString(0);
            String FirstNameJ2 = curseur.getString(1);
            String LastNameJ1 = curseur.getString(2);
            String LastNameJ2 = curseur.getString(3);
            GameHist.add(0,curseur.getString(4));
            GameHist.add(1,curseur.getString(5));
            GameHist.add(2,curseur.getString(6));
            GameHist.add(3,curseur.getString(7));
            GameHist.add(4,curseur.getString(8));
            GameHist.add(5,curseur.getString(9));
            GameHist.add(6,curseur.getString(10));
            GameHist.add(7,curseur.getString(11));
            GameHist.add(8,curseur.getString(12));
            GameHist.add(9,curseur.getString(13));
            String AdresseMatch = curseur.getString(14);

            historique = new HistoryMatch(FirstNameJ1,FirstNameJ2,LastNameJ1,LastNameJ2,GameHist,AdresseMatch);
        }

        curseur.close();
        return historique;

    }
}
