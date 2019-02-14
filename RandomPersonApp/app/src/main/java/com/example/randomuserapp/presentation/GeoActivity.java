package com.example.randomuserapp.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.randomuserapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GeoActivity extends FragmentActivity implements OnMapReadyCallback {
    private double mLatitude;
    private double mLongitude;
    private String mAddress;
    private GoogleMap mMap;
    private final String TAG = GeoActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Changed activity to GeoActivity");
        //Set content view
        setContentView(R.layout.activity_geolocate);

        //Unpack intent, mLatitude, mLongitude
        Intent unpack = getIntent();
        mAddress = unpack.getStringExtra("mAddress");
        mLatitude = unpack.getDoubleExtra("mLatitude", 0);                          //Unpack mLatitude
        mLongitude = unpack.getDoubleExtra("mLongitude", 0);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "On mMap ready was called.");
        mMap = googleMap;
        LatLng geoLocateBlindWall = new LatLng(mLatitude, mLongitude);
        mMap.addMarker(new MarkerOptions().position(geoLocateBlindWall).title(mAddress).title(mAddress));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(geoLocateBlindWall));
    }
}
