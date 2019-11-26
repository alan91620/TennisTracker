package com.example.myapplication.Controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    //properties

    private String creation = "create table matchs("
            +"id INTEGER PRIMARY KEY,"
            +"joueur1FirstName TEXT NOT NULL,"
            +"joueur2FirstName TEXT NOT NULL,"
            +"joueur1LastName TEXT NOT NULL,"
            +"joueur2LastName TEXT NOT NULL,"
            +"set1ScoreJoueur1 TEXT NOT NULL,"
            +"set1ScoreJoueur2 TEXT NOT NULL,"
            +"set2ScoreJoueur1 TEXT NOT NULL,"
            +"set2ScoreJoueur2 TEXT NOT NULL,"
            +"set3ScoreJoueur1 TEXT NOT NULL,"
            +"set3ScoreJoueur2 TEXT NOT NULL,"
            +"set4ScoreJoueur1 TEXT NOT NULL,"
            +"set4ScoreJoueur2 TEXT NOT NULL,"
            +"set5ScoreJoueur1 TEXT NOT NULL,"
            +"set5ScoreJoueur2 TEXT NOT NULL,"
            +"streetName TEXT NOT NULL);";

    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(creation);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
