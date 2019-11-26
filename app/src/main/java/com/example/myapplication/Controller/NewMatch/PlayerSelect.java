package com.example.myapplication.Controller.NewMatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Controller.ScoreBoard2;
import com.example.myapplication.Model.Player;
import com.example.myapplication.R;

public class PlayerSelect extends AppCompatActivity {
    private ImageView imageView;

    TextView p1Name, p2Name, p1Surname, p2Surname;
    Button play;

    Intent intent;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_select);
        
        imageView = findViewById(R.id.image);

        p1Name = findViewById(R.id.p1Name);
        p2Name = findViewById(R.id.p2Name);

        p1Surname = findViewById(R.id.p1Surname);
        p2Surname = findViewById(R.id.p2Surname);

        intent = new Intent(this, ScoreBoard2.class);

        play = findViewById(R.id.start);

        //No title and notification bar


        ConstraintLayout linearLayout = (ConstraintLayout) findViewById(R.id.layoutPlayerSelect);

        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Player p1 = new Player(p1Name.getText().toString(), p1Surname.getText().toString(),1,1,1,"null");
                Player p2 = new Player(p2Name.getText().toString(), p2Surname.getText().toString(),1,1,1,"null");

                intent.putExtra("p1",p1);
                intent.putExtra("p2",p2);

                startActivity(intent);

            }
        });
    }


}
