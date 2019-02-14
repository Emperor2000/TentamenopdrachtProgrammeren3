package com.example.randomuserapp.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.randomuserapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GeoActivity extends FragmentActivity implements OnMapReadyCallback {
    private double latitude;
    private double longitude;
    private String address;
    private GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set content view
        setContentView(R.layout.activity_geolocate);

        //Unpack intent, latitude, longitude
        Intent unpack = getIntent();
        address = unpack.getStringExtra("address");
        latitude = unpack.getDoubleExtra("latitude", 0);                          //Unpack latitude
        longitude = unpack.getDoubleExtra("longitude", 0);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng geoLocateBlindWall = new LatLng(latitude, longitude);
        map.addMarker(new MarkerOptions().position(geoLocateBlindWall).title(address).title(address));
        map.moveCamera(CameraUpdateFactory.newLatLng(geoLocateBlindWall));
    }
}
