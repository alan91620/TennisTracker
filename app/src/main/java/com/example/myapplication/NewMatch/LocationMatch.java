package com.example.myapplication.NewMatch;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.Manifest;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.TextView;


public class LocationMatch extends FragmentActivity implements LocationListener,OnMapReadyCallback {
    ConstraintLayout linearLayout;
    private GoogleMap mMap;
    private TextView t;

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

    double latitude;
    double longitude;

    public LocationManager locationManager;
    public Criteria criteria;
    public String bestProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_match);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        //mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


    }

    @Override
    protected void onPause() {
        super.onPause();

        if (canAccessLocation())
            locationManager.removeUpdates(this);

        return;
    }


    public void getLocation(View v) {

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getLocation();
        }*/
    }

    @SuppressLint("MissingPermission")
    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void getcoord() {
        if (canAccessLocation()) {
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
            @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(bestProvider);
            if (location != null) {
                Log.e("TAG", "GPS is on");
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                Log.d("GPS", "getLocation: latitude:" + latitude + " longitude:" + longitude);

            } else {
                //This is what you need:
                locationManager.requestLocationUpdates(bestProvider, 400, 1, this);

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

    /*@Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in the last known location and move the camera
        if (locationManager != null) {
            @SuppressLint("MissingPermission") Location l = locationManager.getLastKnownLocation(bestProvider);

            if (l != null) {
                LatLng coord = new LatLng(l.getLatitude(), l.getLongitude());
                mMap.addMarker(new MarkerOptions().position(coord).title("Last position"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coord, 15));

                t.setText("Latitude = " + l.getLatitude() + "\n" + "Longitude =" + l.getLongitude());
            }
        }
    }*/

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

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
