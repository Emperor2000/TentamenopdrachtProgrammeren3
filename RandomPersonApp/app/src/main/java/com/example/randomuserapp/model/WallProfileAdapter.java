package com.example.randomuserapp.model;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.randomuserapp.R;
import com.example.randomuserapp.presentation.InfoActivity;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class WallProfileAdapter extends RecyclerView.Adapter<WallProfileAdapter.BlindWallViewHolder>{

    //Assert private variables
    private final String TAG = WallProfileAdapter.class.getSimpleName();
    private ArrayList<Profile> mProfileList;

    public WallProfileAdapter(ArrayList<Profile> profileList) {
        this.mProfileList = profileList;
    }


    @NonNull
    @Override
    public BlindWallViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View listRow = inflater.inflate(R.layout.wall_list_row, viewGroup, false);

        BlindWallViewHolder blindWallViewHolder = new BlindWallViewHolder(listRow);

        return blindWallViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BlindWallViewHolder blindWallProfileViewHolder, final int position) {

        Profile profile = mProfileList.get(position);
        blindWallProfileViewHolder.tvTextInsideImage.setText(profile.getmAuthor());

        Picasso.get().load(profile.getmImgUrl()).into(blindWallProfileViewHolder.imgProfilePictures);
        blindWallProfileViewHolder.imgProfilePictures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log image clicked with ID
                Log.d(TAG, "Image has been clicked");
                Log.d(TAG, "ID = " + position);

                Context context = v.getContext(); //this.itemView.getContext();
                Intent intent = new Intent(context, InfoActivity.class);
                for (Profile check : mProfileList){

                    if (check.getmId()-1 == position){                                                   //Check if ID is correct
                        intent.putExtra("author", check.getmAuthor());                             //Adds the author to intent
                        intent.putExtra("imgWallUrl", check.getmImgWallUrl());                     //Adds the image arrayList to intent
                        intent.putExtra("description", check.getmDescription());                   //Adds the description to intent
                        intent.putExtra("photographer", check.getPhotographer());                 //Adds the photographer to intent
                        intent.putExtra("address", check.getmAddress());                           //Adds the address to intent
                        intent.putExtra("material", check.getmMaterial());                         //Adds the material to intent
                        intent.putExtra("latitude", check.getmLatitude());                         //Adds the latitude to intent
                        intent.putExtra("longitude", check.getLongitude());                       //Adds the longitude to intent
                        //Log profile added
                        Log.d(TAG, "Profile '" + check.getmAuthor() + "' put in intent");
                    }
                }
                //Start activity InfoActivity when someone clicks an image inside WallProfileAdapter
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mProfileList.size();
    }

    public class BlindWallViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private View itemView;

        @Override
        public void onClick(View v) {

        }
        public TextView tvTextInsideImage;
        public ImageView imgProfilePictures;

        public BlindWallViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            Log.d(TAG, "Entered BlindWallViewHolder");

            tvTextInsideImage = itemView.findViewById(R.id.tv_inside_img);
            imgProfilePictures = itemView.findViewById(R.id.img_wall_picture);
            itemView.setOnClickListener(this);
        }
    }
}
