package com.example.randomuserapp.presentation;

import android.app.AppComponentFactory;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.randomuserapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class InfoImageActivity extends AppCompatActivity {
    private ArrayList mImgWallUrl;
    private int countImages = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_image);       //Set content view
        Intent unpack = getIntent();                        //Unpack intent
        mImgWallUrl = unpack.getStringArrayListExtra("ImageList"); //getArrayList of images
        ImageView portfolioImage = findViewById(R.id.img_portfolio);            //Define ImageView portfolioImage, this will show our images, when clicked the next image will be shown.
        Picasso.get().load("https://api.blindwalls.gallery/" + mImgWallUrl.get(0)).into(portfolioImage);


        portfolioImage.setOnClickListener(new View.OnClickListener() {          //Create onClickListener for the large image that takes up the entire screen because god knows why.
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                if (countImages < mImgWallUrl.size()-1) {
                    ImageView portfolioSwitchImage = findViewById(R.id.img_portfolio);
                    countImages++;
                    Picasso.get().load("https://api.blindwalls.gallery/" + mImgWallUrl.get(countImages)).into(portfolioSwitchImage);
                    Toast toast = Toast.makeText(context, "There are " + mImgWallUrl.size() + " images in this portfolio", Toast.LENGTH_LONG);
                    toast.show();
                    Log.d("TAG", "Image was clicked!");
                } else {
                    Toast toast = Toast.makeText(context, "There are no more images to show", Toast.LENGTH_LONG);
                    toast.show();
                    finish();   //Go back to infoActivity.
                }

            }
        });
    }




}
