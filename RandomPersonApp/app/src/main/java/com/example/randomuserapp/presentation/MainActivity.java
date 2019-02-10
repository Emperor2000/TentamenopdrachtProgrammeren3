package com.example.randomuserapp.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.randomuserapp.R;
import com.example.randomuserapp.model.Profile;
import com.example.randomuserapp.model.PersonAdapter;
import com.example.randomuserapp.services.RandomUserTask;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements RandomUserTask.RandomUserListerner {

    private final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private ArrayList<Profile> profileList = new ArrayList<>();
    private PersonAdapter personAdapter = new PersonAdapter(profileList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recyclerview);

        recyclerView = (RecyclerView) findViewById(R.id.rv_person_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //profileList.add(new Profile("mr", "Bob", "faggot"));

        personAdapter = new PersonAdapter(profileList);
        recyclerView.setAdapter(personAdapter);

        RandomUserTask randomUserTask = new RandomUserTask(this);
        randomUserTask.execute();
    }

    @Override
    public void onUserNameAvailable(ArrayList<Profile> profiles){
        Log.d(TAG, "We have " + profileList.size() + " people");

        this.profileList.clear();
        this.profileList.addAll(profiles);
        this.personAdapter.notifyDataSetChanged();
    }
}
//TODO: Activity_Main.xml moet text overlay hebben op afbeeldingen.
