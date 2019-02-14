//Dit project is gemaakt door Jay Hagendoorn en Vincent Hendriks.


package com.example.blindwallapp.presentation;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.blindwallapp.R;
import com.example.blindwallapp.model.Profile;
import com.example.blindwallapp.model.WallProfileAdapter;
import com.example.blindwallapp.services.JSONService;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements JSONService.JSONServiceListener {

    //Assert private variables
    private final String mCycleTag = "LifeCycleEvents";
    private final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private ArrayList<Profile> mProfileList = new ArrayList<>();
    private WallProfileAdapter mWallProfileAdapter = new WallProfileAdapter(mProfileList);
    private ImageView clickableImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate of MainActivity was called");
        //Set content view
        setContentView(R.layout.activity_main_recyclerview);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_profile_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mWallProfileAdapter = new WallProfileAdapter(mProfileList);
        mRecyclerView.setAdapter(mWallProfileAdapter);
        JSONService JSONService = new JSONService(this);
        JSONService.execute();
        //Toast showing the data has been read
        Toast.makeText(getApplicationContext(), R.string.toast_read_data, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onWallProfileAvailable(ArrayList<Profile> profiles){
        this.mProfileList.clear();
        this.mProfileList.addAll(profiles);
        this.mWallProfileAdapter.notifyDataSetChanged();

        //Log profile amount
        Log.d(TAG, mProfileList.size() + " profiles added");
    }

    //Configuration change when screen is rotated.
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Toast.makeText(this, "Changed orientation", Toast.LENGTH_LONG).show();
    }


    //LifeCycle events
    public void onStart()
    {
        super.onStart();
        Log.d(mCycleTag, "In the onStart() event");
    }
    public void onRestart()
    {
        super.onRestart();
        Log.d(mCycleTag, "In the onRestart() event");
    }
    public void onResume()
    {
        super.onResume();
        Log.d(mCycleTag, "In the onResume() event");
    }
    public void onPause()
    {
        super.onPause();
        Log.d(mCycleTag, "In the onPause() event");
    }
    public void onStop()
    {
        super.onStop();
        Log.d(mCycleTag, "In the onStop() event");
    }
    public void onDestroy()
    {
        super.onDestroy();
        Log.d(mCycleTag, "In the onDestroy() event");
    }
}


//-----------------------------------------------------------------------------
/*LIFECYCLE EVENT DOCUMENTATION AND FUNCTIONALITY, RETRIEVED FROM STACKOVERFLOW
//-----------------------------------------------------------------------------
*
* onCreate():

Called when the activity is first created. This is where you should do all of your normal static set up: create views, bind data to lists, etc. This method also provides you with a Bundle containing the activity's previously frozen state, if there was one. Always followed by onStart().

onRestart():

Called after your activity has been stopped, prior to it being started again. Always followed by onStart()

onStart():

Called when the activity is becoming visible to the user. Followed by onResume() if the activity comes to the foreground.

onResume():

Called when the activity will start interacting with the user. At this point your activity is at the top of the activity stack, with user input going to it. Always followed by onPause().

onPause ():

Called as part of the activity lifecycle when an activity is going into the background, but has not (yet) been killed. The counterpart to onResume(). When activity B is launched in front of activity A, this callback will be invoked on A. B will not be created until A's onPause() returns, so be sure to not do anything lengthy here.

onStop():

Called when you are no longer visible to the user. You will next receive either onRestart(), onDestroy(), or nothing, depending on later user activity. Note that this method may never be called, in low memory situations where the system does not have enough memory to keep your activity's process running after its onPause() method is called.

onDestroy():

The final call you receive before your activity is destroyed. This can happen either because the activity is finishing (someone called finish() on it, or because the system is temporarily destroying this instance of the activity to save space. You can distinguish between> these two scenarios with the isFinishing() method.*/