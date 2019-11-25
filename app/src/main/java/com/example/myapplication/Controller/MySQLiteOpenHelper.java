package com.example.myapplication.Controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    //properties

    private String creation = "create table matchs("
            +"id INTEGER PRIMARY KEY,"
            +"joueur1 TEXT NOT NULL,"
            +"joueur2 TEXT NOT NULL,"
            +"set1ScoreJoueur1 INTEGER NOT NULL,"
            +"set1ScoreJoueur2 INTEGER NOT NULL,"
            +"set2ScoreJoueur2 INTEGER NOT NULL,"
            +"set2ScoreJoueur2 INTEGER NOT NULL,"
            +"set3ScoreJoueur2 INTEGER NOT NULL,"
            +"set3ScoreJoueur2 INTEGER NOT NULL,"
            +"set4ScoreJoueur2 INTEGER NOT NULL,"
            +"set4ScoreJoueur2 INTEGER NOT NULL,"
            +"set5ScoreJoueur2 INTEGER NOT NULL,"
            +"set5ScoreJoueur2 INTEGER NOT NULL,"
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
