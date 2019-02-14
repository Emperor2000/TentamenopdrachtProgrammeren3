package com.example.randomuserapp.presentation;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.randomuserapp.R;

import java.util.ArrayList;

public class InfoImageActivity extends AppCompatActivity {
    private ArrayList mImgWallUrl;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_image);       //Set content view
        Intent unpack = getIntent();                        //Unpack intent
        mImgWallUrl = unpack.getStringArrayListExtra("imgWallUrl"); //getArrayList of images
        ImageView portfolioImage = findViewById(R.id.img_portfolio);            //Define ImageView portfolioImage, this will show our images, when clicked the next image will be shown.




        portfolioImage.setOnClickListener(new View.OnClickListener() {          //Create onClickListener for the large image that takes up the entire screen because god knows why.
            @Override
            public void onClick(View v) {
            Log.d("TAG", "Image was clicked!");
            }
        });
    }



}
