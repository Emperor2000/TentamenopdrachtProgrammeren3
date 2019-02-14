//Dit project is gemaakt door Jay Hagendoorn en Vincent Hendriks.


package com.example.randomuserapp.presentation;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.randomuserapp.R;
import com.example.randomuserapp.model.Profile;
import com.example.randomuserapp.model.WallProfileAdapter;
import com.example.randomuserapp.services.JSONService;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements JSONService.JSONServiceListener {

    //Assert private variables
    private final String cycleTag = "LifeCycleEvents";
    private final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private ArrayList<Profile> profileList = new ArrayList<>();
    private WallProfileAdapter wallProfileAdapter = new WallProfileAdapter(profileList);
    private ImageView clickableImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set content view
        setContentView(R.layout.activity_main_recyclerview);

        recyclerView = (RecyclerView) findViewById(R.id.rv_profile_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        wallProfileAdapter = new WallProfileAdapter(profileList);
        recyclerView.setAdapter(wallProfileAdapter);
        JSONService JSONService = new JSONService(this);
        JSONService.execute();
        //Toast showing the data has been read
        Toast.makeText(getApplicationContext(), R.string.toast_read_data, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onWallProfileAvailable(ArrayList<Profile> profiles){
        this.profileList.clear();
        this.profileList.addAll(profiles);
        this.wallProfileAdapter.notifyDataSetChanged();

        //Log profile amount
        Log.d(TAG, profileList.size() + " profiles added");
    }

    //Configuration change when screen is rotated.
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Toast.makeText(this, "Changed orientation", Toast.LENGTH_LONG).show();
    }

    public void onStart()
    {
        super.onStart();
        Log.d(cycleTag, "In the onStart() event");
    }
    public void onRestart()
    {
        super.onRestart();
        Log.d(cycleTag, "In the onRestart() event");
    }
    public void onResume()
    {
        super.onResume();
        Log.d(cycleTag, "In the onResume() event");
    }
    public void onPause()
    {
        super.onPause();
        Log.d(cycleTag, "In the onPause() event");
    }
    public void onStop()
    {
        super.onStop();
        Log.d(cycleTag, "In the onStop() event");
    }
    public void onDestroy()
    {
        super.onDestroy();
        Log.d(cycleTag, "In the onDestroy() event");
    }
}
