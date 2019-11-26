package com.example.myapplication.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.*;

import com.example.myapplication.Model.Player;
import com.example.myapplication.Model.TennisGame;
import com.example.myapplication.R;

import java.util.ArrayList;


public class ScoreBoard2 extends AppCompatActivity {
    ConstraintLayout linearLayout;

    Button p1Score;
    Button p2Score;
    TextView p1Sets;
    TextView p1s1;
    TextView p1s2;
    TextView p1s3;
    TextView p1s4;
    TextView p1s5;
    TextView p2Sets;
    TextView p2s1;
    TextView p2s2;
    TextView p2s3;
    TextView p2s4;
    TextView p2s5;
    TextView p1Points;
    TextView p2Points;
    TextView p1Games;
    TextView p2Games;

    TextView p1Name;
    TextView p2Name;

    Button endMatch;


    TennisGame game;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_score_board);

        p1Score = findViewById(R.id.p1Score);
        p2Score = findViewById(R.id.p2Score);

        p1Sets = findViewById(R.id.p1Set);
        p1s1 = findViewById(R.id.p1s1);
        p1s2 = findViewById(R.id.p1s2);
        p1s3 = findViewById(R.id.p1s3);
        p1s4 = findViewById(R.id.p1s4);
        p1s5 = findViewById(R.id.p1s5);
        p2Sets = findViewById(R.id.p2Set);
        p2s1 = findViewById(R.id.p2s1);
        p2s2 = findViewById(R.id.p2s2);
        p2s3 = findViewById(R.id.p2s3);
        p2s4 = findViewById(R.id.p2s4);
        p2s5 = findViewById(R.id.p2s5);
        p1Name = findViewById(R.id.p1Name);
        p2Name = findViewById(R.id.p2Name);

        p1Games = findViewById(R.id.p1Games);
        p2Games = findViewById(R.id.p2Games);

        p1Points = findViewById(R.id.p1points);
        p2Points = findViewById(R.id.p2points);
        endMatch = findViewById(R.id.EndMatch);

        handler = new Handler();

        final Player player1 = new Player("test","test",13,13,13,"frace");
        final Player player2 = new Player("2","2",13,13,13,"frace");

        game = new TennisGame(player1, player2);
        final ArrayList<String> score = game.getScore();

        p1Score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player1_score();
            }
        });

        p2Score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player2_score();
            }
        });

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        p1Name.setText(player1.toString());
                        p2Name.setText(player2.toString());

                    }
                });
            }
        };

        new Thread(runnable).start();

        updateUI_Scores();

    }

    public void updateUI_Scores(){
        final ArrayList<String> score = game.getScore();
        final ArrayList<String> memGames = game.getGameHist();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        p1Points.setText(score.get(0));
                        p2Points.setText(score.get(3));
                        p1Games.setText(score.get(1));
                        p2Games.setText(score.get(4));
                        p1Sets.setText(score.get(2));
                        p2Sets.setText(score.get(5));

                        p1s1.setText(memGames.get(0).toString());
                        p1s2.setText(memGames.get(2).toString());
                        p1s3.setText(memGames.get(4).toString());
                        p1s4.setText(memGames.get(6).toString());
                        p1s5.setText(memGames.get(8).toString());

                        p2s1.setText(memGames.get(1).toString());
                        p2s2.setText(memGames.get(3).toString());
                        p2s3.setText(memGames.get(5).toString());
                        p2s4.setText(memGames.get(7).toString());
                        p2s5.setText(memGames.get(9).toString());

                    }
                });
            }
        };

        new Thread(runnable).start();
    }

    public void player1_score(){
        game.PlayerOneScore();
        updateUI_Scores();
        chckEndMatch();
    }

    public void player2_score(){
        game.PlayerTwoScore();
        updateUI_Scores();
        chckEndMatch();
    }

    public void chckEndMatch(){
        final ArrayList<String> score = game.getScore();
        if (Integer.parseInt(score.get(2)) == 3 || Integer.parseInt(score.get(5)) == 3){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            endMatch.setVisibility(View.VISIBLE);
                            p1Score.setVisibility(View.INVISIBLE);
                            p2Score.setVisibility(View.INVISIBLE);
                        }
                    });
                }
            };

            new Thread(runnable).start();
        }
    }
}
