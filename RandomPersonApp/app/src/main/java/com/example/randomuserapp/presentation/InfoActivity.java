package com.example.randomuserapp.presentation;

import android.app.Person;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.randomuserapp.R;
import com.example.randomuserapp.model.Profile;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    private String author;      //Profiel/persoon
    private ArrayList<String> imgWallUrl;
    private String description;
    private String fotograaf;
    private String adres;
    private String materiaal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "I'm in the code mama");
        setContentView(R.layout.activity_info);

        Intent unpack = getIntent();
        author = unpack.getStringExtra("author");
        imgWallUrl = unpack.getStringArrayListExtra("imgWallUrl");
        description = unpack.getStringExtra("description");
        fotograaf = unpack.getStringExtra("fotograaf");
        adres = unpack.getStringExtra("adres");
        materiaal = unpack.getStringExtra("materiaal");

        TextView tvAuthor           = findViewById(R.id.tv_name);
        ImageView imgOfImgWallUrl   = findViewById(R.id.img_wall_picture);
        TextView tvDescription      = findViewById(R.id.tv_description);
        TextView tvFotograaf        = findViewById(R.id.tv_photographer);
        TextView tvAdres            = findViewById(R.id.tv_address);
        TextView tvMateriaal        = findViewById(R.id.tv_material);

        tvAuthor.setText(author);
        /*String imgUrl = "https://api.blindwalls.gallery/" + imgWallUrl.get(0);
        Picasso.get().load(imgUrl.into(imgOfImgWallUrl));
        imgOfImgWallUrl.setImageURI("https://api.blindwalls.gallery/" + imgWallUrl.get(0));*/
        tvDescription.setText(description);
        tvFotograaf.setText(fotograaf);
        tvAdres.setText(adres);
        tvMateriaal.setText(materiaal);

        //Picasso.get().load(profileItemList.get(0)).into(imageView.findViewById(R.id.img_profile));

//        recyclerView = (RecyclerView) findViewById(R.id.rv_profile_list);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //profileList.add(new Profile("mr", "Bob", "faggot"));


    }
}
//TODO: Activity_Main.xml moet text overlay hebben op afbeeldingen.
