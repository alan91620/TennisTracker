package com.example.myapplication.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Controller.MySQLiteOpenHelper;
import com.example.myapplication.Controller.NewMatch.LocationMatch;

public class AccesLocal {

    private String nomBase = "bdFlashTenni.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;

    public AccesLocal(Context contexte){
        accesBD = new MySQLiteOpenHelper(contexte, nomBase, null, versionBase);
    }

    /*ajout objet bdd*/

    public void ajout(){

        bd = accesBD.getWritableDatabase();

        //String req = "insert into matchs (joueur1,joueur2,set1ScoreJoueur1,set1ScoreJoueur2,set2ScoreJoueur1,set2ScoreJoueur2,set3ScoreJoueur1,set3ScoreJoueur2,set4ScoreJoueur1,set4ScoreJoueur2,set5ScoreJoueur1, set5ScoreJoueur2, streetName) ";
        //req+= "values("++","++","++","++","++","++","++","++","++","++","++","++","++")";
        String req = "insert into matchs (id, streetName) ";
        req+= "values("+1+","+ LocationMatch.getAdresseMatch()+")";
        bd.execSQL(req);
    }


    public Integer recupDernier(){

        bd = accesBD.getReadableDatabase();
        Integer x= null;
        String req = "select* from matchs";
        Cursor curseur = bd.rawQuery(req,null);
        curseur.moveToLast();
        if(!curseur.isAfterLast()){
            x = curseur.getInt(1);
        }
        curseur.close();
        return x;
    }
}
