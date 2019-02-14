package com.example.randomuserapp.presentation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.randomuserapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {

    private final String TAG = InfoActivity.class.getSimpleName();

    private String author;      //Profiel/persoon
    private ArrayList<String> imgWallUrl;
    private String description;
    private String photographer;
    private String address;
    private String materiaal;
    private String lang = "en";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "I'm in the code.");
        setContentView(R.layout.activity_info);

        Intent unpack = getIntent();
        author = unpack.getStringExtra("author");
        imgWallUrl = unpack.getStringArrayListExtra("imgWallUrl");
        description = unpack.getStringExtra("description");
        photographer = unpack.getStringExtra("photographer");
        address = unpack.getStringExtra("address");
        materiaal = unpack.getStringExtra("material");


        //Find id's for each layOut element.
        TextView tvAuthor           = findViewById(R.id.tv_name);
        ImageView imgOfImgWallUrl   = findViewById(R.id.img_profile);
        TextView tvDescription      = findViewById(R.id.tv_description);
        TextView tvPhotographer = findViewById(R.id.tv_photographer);
        TextView tvAddress            = findViewById(R.id.tv_address);
        TextView tvMaterial        = findViewById(R.id.tv_material);


        for (String images : imgWallUrl) {

        }
       // imgOfImgWallUrl.setImageURI(imgWallUrl.get());
        tvAuthor.setText(author);       //Put author name in textview
        tvDescription.setText(description); //Put desc in textview
        tvPhotographer.setText(getString(R.string.photographer) + ": " + photographer);   //put photographer in textview
        tvAddress.setText(getString(R.string.profile_address) + ":\n" + address);     //put address in textview
        tvMaterial.setText(getString(R.string.profile_material) + ":\n" + materiaal);  //put material in textview
        Picasso.get().load("https://api.blindwalls.gallery/" + imgWallUrl.get(0)).into(imgOfImgWallUrl);                    //Put first image of Arraylist into imageview




        imgOfImgWallUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();   //getContext
                Intent intent = new Intent(context, InfoImageActivity.class); //Create intent with context and destination class.
                intent.putExtra("ImageList", imgWallUrl);               //Send extra information, arraylist of image url's.
                context.startActivity(intent);      //Start activity InfoImageActivity when someone clicks an image inside of the InfoActivity.
            }
        });




















        //If language is set to en, default yes.
      /*  if (lang.equals("en")) {
            tvAuthor.setText("Author: " + author);
        String imgUrl = "https://api.blindwalls.gallery/" + imgWallUrl.get(0);
      //  Picasso.get().load(imgUrl.into(imgOfImgWallUrl));
       // imgOfImgWallUrl.setImageURI("https://api.blindwalls.gallery/" + imgWallUrl.get(0).toString());

            tvDescription.setText(description);
            tvPhotographer.setText("Photographer: \n" + fotograaf);
            tvAddress.setText("Address: \n" + address);
            tvMaterial.setText("Material: " + materiaal);
        }
        //If language is set to nl, default no.
        if (lang.equals("nl")) {
            tvAuthor.setText("Naam: " + author);
            tvDescription.setText(description);
            tvPhotographer.setText("Fotograaf: \n" + fotograaf);
            tvAddress.setText("Adres: \n" + address);
            tvMaterial.setText("Materiaal: " + materiaal);
        }*/

        //Picasso.get().load(profileItemList.get(0)).into(imageView.findViewById(R.id.img_profile));

//        recyclerView = (RecyclerView) findViewById(R.id.rv_profile_list);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
}
//TODO: Activity_Main.xml moet text overlay hebben op afbeeldingen.
