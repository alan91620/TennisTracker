package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.gms.location.LocationListener;

public class MainActivity extends AppCompatActivity implements LocationListener {

    ConstraintLayout linearLayout;
    double latitude;
    double longitude;
    private Handler handler;
    private static final String[] INITIAL_PERMS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_CONTACTS
    };
    private static final String[] CAMERA_PERMS = {
            Manifest.permission.CAMERA
    };
    private static final String[] CONTACTS_PERMS = {
            Manifest.permission.READ_CONTACTS
    };
    private static final String[] LOCATION_PERMS = {
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private static final int INITIAL_REQUEST = 1337;
    private static final int CAMERA_REQUEST = INITIAL_REQUEST + 1;
    private static final int CONTACTS_REQUEST = INITIAL_REQUEST + 2;
    private static final int LOCATION_REQUEST = INITIAL_REQUEST + 3;


    public LocationManager locationManager;
    public Criteria criteria;
    public String bestProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.getWindow().setUiOptions(WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES);

        setContentView(R.layout.activity_main);


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_menu, R.id.navigation_progress, R.id.navigation_historique)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

        linearLayout = (ConstraintLayout) findViewById(R.id.layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();


    }


    public void newGame(View v) {
        //Intent intent = new Intent(this, NewMatchActivity.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getLocation();
        }
        //startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void getLocation() {
        if (canAccessLocation()) {
            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            criteria = new Criteria();
            bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true)).toString();

            // Pour indiquer la précision voulue
// On peut mettre ACCURACY_FINE pour une haute précision ou ACCURACY_COARSE pour une moins bonne précision
            criteria.setAccuracy(Criteria.ACCURACY_FINE);

// Est-ce que le fournisseur doit être capable de donner une altitude ?
            criteria.setAltitudeRequired(false);

// Est-ce que le fournisseur doit être capable de donner une direction ?
            criteria.setBearingRequired(false);

// Est-ce que le fournisseur peut être payant ?
            criteria.setCostAllowed(false);

// Pour indiquer la consommation d'énergie demandée
// Criteria.POWER_HIGH pour une haute consommation, Criteria.POWER_MEDIUM pour une consommation moyenne et Criteria.POWER_LOW pour une basse consommation
            criteria.setPowerRequirement(Criteria.POWER_HIGH);

// Est-ce que le fournisseur doit être capable de donner une vitesse ?
            criteria.setSpeedRequired(false);

            //You can still do this if you like, you might get lucky:
            @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                Log.e("TAG", "GPS is on");
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                Log.d("GPS", "getLocation: latitude:" + latitude + " longitude:" + longitude);
            } else {
                //This is what you need:
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
                locationManager.requestLocationUpdates(bestProvider, 1000, 0, this);
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                Log.d("GPS", "getLocation: latitude:" + latitude + " longitude:" + longitude);
            }
        }
        else
        {
            //prompt user to enable location....
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(LOCATION_PERMS, LOCATION_REQUEST);
            }
        }
    }

    private boolean canAccessLocation() {
        return(hasPermission(Manifest.permission.ACCESS_FINE_LOCATION));
    }

    private boolean canAccessCamera() {
        return(hasPermission(Manifest.permission.CAMERA));
    }

    private boolean hasPermission(String perm) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return(PackageManager.PERMISSION_GRANTED==checkSelfPermission(perm));
        }else{
            return false;
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }
}

