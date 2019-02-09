package com.example.randomuserapp.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.randomuserapp.R;
import com.example.randomuserapp.presentation.MainActivity;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder>{
    private final String TAG = PersonAdapter.class.getSimpleName();
    private ArrayList<Person> personList;

    public PersonAdapter(ArrayList<Person> personList) {
        this.personList = personList;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View listRow = inflater.inflate(R.layout.person_list_row, viewGroup, false);

        PersonViewHolder personViewHolder = new PersonViewHolder(listRow);

        return personViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder personViewHolder, int position) {

        Person person = personList.get(position);

        personViewHolder.tvPersonName.setText(person.getFullName());
        Picasso.get().load(person.getImgUrl()).into(personViewHolder.imgPersonPictures);

    }

    @Override
    public int getItemCount() {
        return this.personList.size();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int i = getAdapterPosition();
            Log.d(TAG, "ID = " + i);
        }

        public ImageView imgPersonPictures;
        public TextView tvPersonName;
        public TextView tvPersonEmail;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPersonPictures = itemView.findViewById(R.id.img_person_pictures);
            tvPersonName = itemView.findViewById(R.id.tv_person_name);
            tvPersonEmail = itemView.findViewById(R.id.tv_person_email);
            itemView.setOnClickListener(this);
        }


    }



}
