package com.example.myapplication.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.Model.Player;
import com.example.myapplication.Model.TennisGame;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ScoreBoard extends AppCompatActivity {
    TextView pt1;
    TextView pt2;
    TextView jeu1;
    TextView jeu2;
    TextView set1;
    TextView set2;
    Button p1;
    Button p2;
    private Handler handler;
    TennisGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        pt1 = findViewById(R.id.points_player1);
        pt2 = findViewById(R.id.points_player2);
        jeu1 = findViewById(R.id.jeu_player1);
        jeu2 = findViewById(R.id.jeu_player2);
        set1 = findViewById(R.id.set_player1);
        set2 = findViewById(R.id.set_player2);
        p1 = findViewById(R.id.BtAdd_points1);
        p2 = findViewById(R.id.BtAdd_points2);

        handler = new Handler();

        final Player player1 = new Player("test","test",13,13,13,"frace");
        Player player2 = new Player("2","2",13,13,13,"frace");

        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player1_score();
            }
        });

        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player2_score();
            }
        });

        game = new TennisGame(player1, player2);
        final ArrayList<String> score = game.getScore();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        pt1.setText(score.get(0));
                        pt2.setText(score.get(3));
                        jeu1.setText(score.get(1));
                        jeu2.setText(score.get(4));
                        set1.setText(score.get(2));
                        set2.setText(score.get(5));
                    }
                });
            }
        };

        new Thread(runnable).start();

    }

    public void updateUI_Scores(){
        final ArrayList<String> score = game.getScore();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        pt1.setText(score.get(0));
                        pt2.setText(score.get(3));
                        jeu1.setText(score.get(1));
                        jeu2.setText(score.get(4));
                        set1.setText(score.get(2));
                        set2.setText(score.get(5));
                    }
                });
            }
        };

        new Thread(runnable).start();
    }

    public void player1_score(){
        game.PlayerOneScore();
        updateUI_Scores();
    }

    public void player2_score(){
        game.PlayerTwoScore();
        updateUI_Scores();
    }
}
