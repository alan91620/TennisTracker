package com.example.myapplication.Controller.NewMatch;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;

import com.example.myapplication.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class LocationMatch extends FragmentActivity implements LocationListener,OnMapReadyCallback {
    private GoogleMap mMap;

    private static final String[] LOCATION_PERMS = {
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private static final int INITIAL_REQUEST = 1337;
    private static final int CAMERA_REQUEST = INITIAL_REQUEST + 1;
    private static final int CONTACTS_REQUEST = INITIAL_REQUEST + 2;
    private static final int LOCATION_REQUEST = INITIAL_REQUEST + 3;

    double latitude=0;
    double longitude=0;

    public LocationManager locationManager;
    public String bestProvider;

    private EditText Adresse;
    ConstraintLayout linearLayout;
    Geocoder geocoder;
    List<Address> addresses;

    private Date dateMesure;

    public static String adresseMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_match);
        // In Activity's onCreate() for instance
        //No title and notification bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        Adresse = findViewById(R.id.editPostalText);
        Adresse.setText("Appuyez sur 'Localiser' pour situer le match :");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        choseProvider();

        linearLayout = (ConstraintLayout) findViewById(R.id.layoutLocation);

        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();


    }

    @Override
    protected void onPause() {
        super.onPause();

        if (canAccessLocation())
            locationManager.removeUpdates(this);

        return;
    }

public void choseProvider(){

    //criteria = new Criteria();

    // Pour indiquer la précision voulue
// On peut mettre ACCURACY_FINE pour une haute précision ou ACCURACY_COARSE pour une moins bonne précision
   // criteria.setAccuracy(Criteria.ACCURACY_FINE);

// Est-ce que le fournisseur doit être capable de donner une altitude ?
   // criteria.setAltitudeRequired(false);

// Est-ce que le fournisseur doit être capable de donner une direction ?
   // criteria.setBearingRequired(false);

// Est-ce que le fournisseur peut être payant ?
   // criteria.setCostAllowed(false);

// Pour indiquer la consommation d'énergie demandée
// Criteria.POWER_HIGH pour une haute consommation, Criteria.POWER_MEDIUM pour une consommation moyenne et Criteria.POWER_LOW pour une basse consommation
  //  criteria.setPowerRequirement(Criteria.POWER_HIGH);

// Est-ce que le fournisseur doit être capable de donner une vitesse ?
   // criteria.setSpeedRequired(false);

    //bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true));
    bestProvider = LocationManager.GPS_PROVIDER;
}

    public void getLocation(View v) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getcoord();
        }
    }

    @SuppressLint("MissingPermission")
    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void getcoord()  {
        if (canAccessLocation()) {


            //You can still do this if you like, you might get lucky:
            @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(bestProvider);
            if (location != null) {

                locationManager.requestLocationUpdates(bestProvider, 400, 1, this);
                Log.e("TAG", "GPS is on");
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                Log.d("GPS", "getLocation: latitude:" + latitude + " longitude:" + longitude);
                geocoder = new Geocoder(this, Locale.getDefault());

                try {
                    addresses = geocoder.getFromLocation(latitude, longitude, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Adresse.setText(addresses.get(0).getAddressLine(0));
                adresseMatch = addresses.get(0).getAddressLine(0);

            } else {
                //This is what you need:
                locationManager.requestLocationUpdates(bestProvider, 400, 1, this);


            }

            onMapReady(mMap);



        }
        else
        {
            //prompt user to enable location....
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(LOCATION_PERMS, LOCATION_REQUEST);
            }
        }
    }


    public void newPictureMatch (View v) {
        Intent intent = new Intent(this, PhotoMatch.class);

        startActivity(intent);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        LatLng coord = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(coord).title("Last position"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coord, 15));
    }

    public static String getAdresseMatch() {
        return adresseMatch;
    }

    private boolean canAccessLocation() {
        return(hasPermission(Manifest.permission.ACCESS_FINE_LOCATION));
    }

    public Date getDateMesure() {
        return dateMesure;
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

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
