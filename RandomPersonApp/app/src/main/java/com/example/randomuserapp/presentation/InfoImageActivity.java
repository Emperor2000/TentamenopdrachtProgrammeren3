package com.example.randomuserapp.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.randomuserapp.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class InfoImageActivity extends AppCompatActivity {

    //Assert private variables
    private final String TAG = InfoImageActivity.class.getSimpleName();
    private ArrayList mImgWallUrl;
    private int mCountImages = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Log Activity change
        Log.d(TAG, "Changed activity to InfoImageActivity");
        //Log show image number
        Log.d(TAG, "Showing image number " + mCountImages);

        //Set content view
        setContentView(R.layout.activity_info_image);

        //Unpack intent
        Intent unpack = getIntent();
        //getArrayList of images
        mImgWallUrl = unpack.getStringArrayListExtra("ImageList");
        //Define ImageView portfolioImage, this will show our images, when clicked the next image will be shown.
        ImageView portfolioImage = findViewById(R.id.img_portfolio);
        Picasso.get().load("https://api.blindwalls.gallery/" + mImgWallUrl.get(0)).into(portfolioImage);

        //Create onClickListener for the large image that takes up the entire screen
        portfolioImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                //Check if the image id requested doesn't overshoot the size
                if (mCountImages < mImgWallUrl.size()) {
                    ImageView portfolioSwitchImage = findViewById(R.id.img_portfolio);
                    Picasso.get().load("https://api.blindwalls.gallery/" + mImgWallUrl.get(mCountImages)).into(portfolioSwitchImage);
                    mCountImages++;

                    //Toast showing the image count
                    Toast.makeText(context,getString(R.string.toast_images_amount) + " " + mCountImages + " " + getString(R.string.toast_images_of) + " " + mImgWallUrl.size(), Toast.LENGTH_SHORT).show();

                    //Log show image number
                    Log.d(TAG, "Showing image number " + mCountImages);
                } else {

                    //Toast showing there are no more images
                    Toast.makeText(context, R.string.toast_images_none, Toast.LENGTH_LONG).show();

                    //Log exit activity
                    Log.d(TAG, "No more images found");
                    Log.d(TAG, "Exited InfoImageActivity");
                    finish();   //Go back to infoActivity.
                }
            }
        });
    }
}
