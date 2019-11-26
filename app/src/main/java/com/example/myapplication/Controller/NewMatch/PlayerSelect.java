package com.example.myapplication.Controller.NewMatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Controller.ScoreBoard2;
import com.example.myapplication.Model.Player;
import com.example.myapplication.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerSelect extends AppCompatActivity {

    TextView p1Name, p2Name, p1Surname, p2Surname;
    Button play;

    Intent intent;

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private String currentPhotoPath;
    ImageView p1Photo;
    ImageView p2Photo;
    Button btp2Photo, btp1Photo;
    int selected;
    Bitmap BitP1, BitP2;


    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_select);

        p1Name = findViewById(R.id.p1Name);
        p2Name = findViewById(R.id.p2Name);

        p1Surname = findViewById(R.id.p1Surname);
        p2Surname = findViewById(R.id.p2Surname);

        intent = new Intent(this, ScoreBoard2.class);

        play = findViewById(R.id.start);

        p1Photo = findViewById(R.id.p1Photo);
        p2Photo = findViewById(R.id.p2Photo);

        btp1Photo = findViewById(R.id.btp1Photo);
        btp2Photo = findViewById(R.id.btp2Photo);

        btp1Photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = 1;
                onClickPhoto(view);
            }
        });

        btp2Photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = 2;
                onClickPhoto(view);
            }
        });

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
                intent.putExtra("p1Photo",BitP1);
                intent.putExtra("p2Photo",BitP2);


                startActivity(intent);

            }
        });
    }


    public void onClickPhoto(View view) {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                System.out.println("FILE NOT CREATED");
                ex.printStackTrace(System.out);
                // Error occurred while creating the File
                Toast.makeText(this, "File problem", Toast.LENGTH_LONG).show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoFile);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);


            }


        }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            if (selected == 1){
                BitP1 = imageBitmap;
                p1Photo.setImageBitmap(imageBitmap);
            }
            else if (selected == 2){
                BitP2 = imageBitmap;
                p2Photo.setImageBitmap(imageBitmap);
            }
        }
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        String root = getFilesDir().toString().concat("myPhotos");
        //String root = getExternalStoragePublicDirectory(null).toString();
        Toast.makeText(this, root, Toast.LENGTH_LONG).show();
        File storageDir = new File(root); //getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        storageDir.mkdirs();
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir       /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        Toast.makeText(this, currentPhotoPath, Toast.LENGTH_LONG).show();
        return image;
    }


}
