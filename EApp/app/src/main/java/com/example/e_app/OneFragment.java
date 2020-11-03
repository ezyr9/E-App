package com.example.e_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.e_app.Model.Data;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


public class OneFragment extends Fragment {





    private RecyclerView recyclerView;

    //Firebase

    private DatabaseReference mOneDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview =  inflater.inflate(R.layout.fragment_one, container, false);

        mOneDatabase = FirebaseDatabase.getInstance().getReference().child("OneDatabase");
        mOneDatabase.keepSynced(true);


        recyclerView = myview.findViewById(R.id.recycler_frag_one);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        return  myview;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Data, OneViewHolder>  adapter = new FirebaseRecyclerAdapter<Data, OneViewHolder>(
                Data.class, R.layout.cus_item, OneViewHolder.class,mOneDatabase

        ) {
            @Override
            protected void populateViewHolder(OneViewHolder oneViewHolder, Data data, int i) {

                oneViewHolder.setTitle(data.getTitle());
                oneViewHolder.setDescription(data.getDescription());
                oneViewHolder.setImage(data.getImage());


                oneViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), TwoDetailActivity.class);

                        intent.putExtra("title", data.getTitle());
                        intent.putExtra("description", data.getDescription());
                        intent.putExtra("image", data.getImage());

                        startActivity(intent);

                    }
                });



            }
        };

        recyclerView.setAdapter(adapter);
    }
    public static class OneViewHolder extends RecyclerView.ViewHolder{


        View mView;
        public OneViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setTitle (String title){
            TextView mTitle = mView.findViewById(R.id.post_title);
            mTitle.setText(title);
        }
        public void setDescription (String description){
            TextView mDescription = mView.findViewById(R.id.post_description);
            mDescription.setText(description);
        }
        public void setImage (String image){
            ImageView mImage = mView.findViewById(R.id.post_image);

            Picasso.get().load(image).networkPolicy(NetworkPolicy.OFFLINE).into(mImage, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                    Picasso.get().load(image).into(mImage);
                }
            });

        }
    }

}