package com.example.randomuserapp.presentation;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.randomuserapp.R;
import com.example.randomuserapp.model.Profile;
import com.example.randomuserapp.model.WallProfileAdapter;
import com.example.randomuserapp.services.JSONService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements JSONService.JSONServiceListener {

    private final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private ArrayList<Profile> profileList = new ArrayList<>();
    private WallProfileAdapter wallProfileAdapter = new WallProfileAdapter(profileList);
    private ImageView clickableImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recyclerview);

        recyclerView = (RecyclerView) findViewById(R.id.rv_profile_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        wallProfileAdapter = new WallProfileAdapter(profileList);
        recyclerView.setAdapter(wallProfileAdapter);

        JSONService JSONService = new JSONService(this);
        JSONService.execute();


        /*clickableImage = (ImageView) findViewById(R.id.img_wall_picture);
        clickableImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showProfile();
                Context context = MainActivity.this;
                Toast.makeText(context, "clicked", Toast.LENGTH_LONG);
            }
        });*/
    }

    public void showProfile() {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }

    @Override
    public void onWallProfileAvailable(ArrayList<Profile> profiles){
        this.profileList.clear();
        this.profileList.addAll(profiles);
        this.wallProfileAdapter.notifyDataSetChanged();

        Log.d(TAG, "We have " + profileList.size() + " profiles");
    }
}
