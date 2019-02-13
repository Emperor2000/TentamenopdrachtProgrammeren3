package com.example.randomuserapp.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.randomuserapp.R;

public class InfoActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private int profileID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        recyclerView = (RecyclerView) findViewById(R.id.rv_profile_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //profileList.add(new Profile("mr", "Bob", "faggot"));


    }
}
//TODO: Activity_Main.xml moet text overlay hebben op afbeeldingen.
