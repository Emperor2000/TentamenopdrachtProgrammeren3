package com.example.randomuserapp.presentation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.randomuserapp.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {

    //Assert private variables
    private final String TAG = InfoActivity.class.getSimpleName();
    private String author;      //Profiles/author
    private ArrayList<String> imgWallUrl;
    private String description;
    private String photographer;
    private String address;
    private String materiaal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Log Activity change
        Log.d(TAG, "Changed activity to InfoActivity");

        //Set content view
        setContentView(R.layout.activity_info);

        //Unpack variables
        Intent unpack = getIntent();                                                                //Set unpack region
        author = unpack.getStringExtra("author");                                             //Unpack author
        imgWallUrl = unpack.getStringArrayListExtra("imgWallUrl");                            //Unpack image arrayList
        description = unpack.getStringExtra("description");                                   //Unpack description
        photographer = unpack.getStringExtra("photographer");                                 //Unpack photographer
        address = unpack.getStringExtra("address");                                           //Unpack address
        materiaal = unpack.getStringExtra("material");                                        //Unpack material

        //Log unpacking
        Log.d(TAG, "Unpacked profile '" + author + "'");

        //Find id's for each layOut element.
        TextView tvAuthor           = findViewById(R.id.tv_name);                                   //Set tvAuthor to view
        ImageView imgOfImgWallUrl   = findViewById(R.id.img_profile);                               //Set imOfImgWallUrl to view
        TextView tvDescription      = findViewById(R.id.tv_description);                            //Set tvDescription to view
        TextView tvPhotographer = findViewById(R.id.tv_photographer);                               //Set tvPhotographer to view
        TextView tvAddress            = findViewById(R.id.tv_address);                              //Set tvAddress to view
        TextView tvMaterial        = findViewById(R.id.tv_material);                                //Set tvMaterial to view


        tvAuthor.setText(author);                                                                               //Put author name in textview
        tvDescription.setText(description);                                                                     //Put desc in textview
        tvPhotographer.setText(getString(R.string.photographer) + ": " + photographer);                         //Put photographer in textview
        tvAddress.setText(getString(R.string.profile_address) + ":\n" + address);                               //Put address in textview
        tvMaterial.setText(getString(R.string.profile_material) + ":\n" + materiaal);                           //Put material in textview
        Picasso.get().load("https://api.blindwalls.gallery/" + imgWallUrl.get(0)).into(imgOfImgWallUrl);  //Put first image of Arraylist into imageview




        imgOfImgWallUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();   //getContext
                Intent intent = new Intent(context, InfoImageActivity.class); //Create intent with context and destination class.
                intent.putExtra("ImageList", imgWallUrl);               //Send extra information, arraylist of image url's.
                Toast.makeText(context,  getString(R.string.toast_images_amount) + " 1 " + getString(R.string.toast_images_of) + " " + imgWallUrl.size(), Toast.LENGTH_SHORT).show();
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


    //Configuration change when screen is rotated.
}
//TODO: Activity_Main.xml moet text overlay hebben op afbeeldingen.
