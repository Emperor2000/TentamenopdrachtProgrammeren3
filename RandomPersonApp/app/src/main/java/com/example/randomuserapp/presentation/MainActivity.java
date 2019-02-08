package com.example.randomuserapp.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.randomuserapp.R;
import com.example.randomuserapp.model.Person;
import com.example.randomuserapp.model.PersonAdapter;
import com.example.randomuserapp.services.RandomUserTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements RandomUserTask.RandomUserListerner {

    private final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private ArrayList<Person> personList = new ArrayList<>();
    private PersonAdapter personAdapter = new PersonAdapter(personList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recyclerview);

        recyclerView = (RecyclerView) findViewById(R.id.rv_person_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //personList.add(new Person("mr", "Bob", "faggot"));

        personAdapter = new PersonAdapter(personList);
        recyclerView.setAdapter(personAdapter);

        RandomUserTask randomUserTask = new RandomUserTask(this);
        randomUserTask.execute();
    }

    @Override
    public void onUserNameAvailable(ArrayList<Person> persons){
        Log.d(TAG, "We have " + personList.size() + " people");

        this.personList.clear();
        this.personList.addAll(persons);
        this.personAdapter.notifyDataSetChanged();
    }
}
