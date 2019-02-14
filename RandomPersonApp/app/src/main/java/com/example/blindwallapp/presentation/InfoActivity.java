package com.example.blindwallapp.presentation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.blindwallapp.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {

    //Assert private variables
    private final String TAG = InfoActivity.class.getSimpleName();
    private String mAuthor;      //Profiles/mAuthor
    private ArrayList<String> mImgWallUrl;
    private String mDescription;
    private String mPhotographer;
    private String mAddress;
    private String mMaterial;
    private double mLatitude;
    private double mLongitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Log Activity change
        Log.d(TAG, "Changed activity to InfoActivity");

        //Set content view
        setContentView(R.layout.activity_info);

        //Unpack variables
        Intent unpack = getIntent();                                                                //Set unpack region
        mAuthor = unpack.getStringExtra("author");                                             //Unpack mAuthor
        mImgWallUrl = unpack.getStringArrayListExtra("imgWallUrl");                            //Unpack image arrayList
        mDescription = unpack.getStringExtra("description");                                   //Unpack mDescription
        mPhotographer = unpack.getStringExtra("photographer");                                 //Unpack mPhotographer
        mAddress = unpack.getStringExtra("address");                                           //Unpack mAddress
        mMaterial = unpack.getStringExtra("material");                                        //Unpack material
        mLatitude = unpack.getDoubleExtra("latitude", 0);                          //Unpack mLatitude
        mLongitude = unpack.getDoubleExtra("longitude", 0);                        //Unpack mLongitude
        //Log unpacking
        Log.d(TAG, "Unpacked profile '" + mAuthor + "'");

        //Find id's for each layOut element.
        TextView tvAuthor           = findViewById(R.id.tv_name);                                   //Set tvAuthor to view
        ImageView imgOfImgWallUrl   = findViewById(R.id.img_profile);                               //Set imOfImgWallUrl to view
        TextView tvDescription      = findViewById(R.id.tv_description);                            //Set tvDescription to view
        TextView tvPhotographer = findViewById(R.id.tv_photographer);                               //Set tvPhotographer to view
        TextView tvAddress            = findViewById(R.id.tv_address);                              //Set tvAddress to view
        TextView tvMaterial        = findViewById(R.id.tv_material);                                //Set tvMaterial to view
        Button btnGeoLocation = findViewById(R.id.btn_geolocation);

        tvAuthor.setText(mAuthor);                                                                               //Put mAuthor name in textview
        tvDescription.setText(mDescription);                                                                     //Put desc in textview
        tvPhotographer.setText(getString(R.string.photographer) + ": " + mPhotographer);                         //Put mPhotographer in textview
        tvAddress.setText(getString(R.string.profile_address) + ":\n" + mAddress);                               //Put mAddress in textview
        tvMaterial.setText(getString(R.string.profile_material) + ":\n" + mMaterial);                           //Put material in textview
        Picasso.get().load("https://api.blindwalls.gallery/" + mImgWallUrl.get(0)).into(imgOfImgWallUrl);  //Put first image of Arraylist into imageview

        imgOfImgWallUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "OnClickListener Active, img");
                Context context = v.getContext();   //getContext
                Intent intent = new Intent(context, InfoImageActivity.class); //Create intent with context and destination class.
                intent.putExtra("ImageList", mImgWallUrl);               //Send extra information, arraylist of image url's.

                //Toast showing the image count
                Toast.makeText(context,  getString(R.string.toast_images_amount) + " 1 " + getString(R.string.toast_images_of) + " " + mImgWallUrl.size(), Toast.LENGTH_SHORT).show();

                //Start activity InfoImageActivity when someone clicks an image inside of the InfoActivity.
                context.startActivity(intent);
            }
        });


        //GeoLocation on click Listener
        btnGeoLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            //Onclick event for GeoLocation button
            public void onClick(View v) {
                Log.d(TAG, "onClickListener Active, GeoLocation button");
                Context context = v.getContext();
                    Intent intent = new Intent(context, GeoActivity.class);
                    intent.putExtra("mAddress", mAddress);
                    intent.putExtra("mLatitude", mLatitude);
                    intent.putExtra("mLongitude", mLongitude);
                    context.startActivity(intent);
            }
        });
    }
    //Configuration change when screen is rotated.
}
//TODO: Activity_Main.xml moet text overlay hebben op afbeeldingen.
