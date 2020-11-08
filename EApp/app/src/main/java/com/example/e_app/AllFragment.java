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
import com.example.e_app.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


public class AllFragment extends Fragment {


    private RecyclerView recyclerViewOne;
    private RecyclerView recyclerViewTwo;


    //Firebase

    private DatabaseReference mOneDatabase;
    private DatabaseReference mTwoDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview = inflater.inflate(R.layout.fragment_all, container, false);


        mOneDatabase = FirebaseDatabase.getInstance().getReference().child("OneDatabase");
        mTwoDatabase = FirebaseDatabase.getInstance().getReference().child("TwoDatabase");


        //ONE

        recyclerViewOne = myview.findViewById(R.id.recycler_one);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerViewOne.setHasFixedSize(true);
        recyclerViewOne.setLayoutManager(layoutManager);

        //TWO

        recyclerViewTwo = myview.findViewById(R.id.recycler_two);
        LinearLayoutManager layoutManagerTwo = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        layoutManagerTwo.setReverseLayout(true);
        layoutManagerTwo.setStackFromEnd(true);
        recyclerViewTwo.setHasFixedSize(true);
        recyclerViewTwo.setLayoutManager(layoutManagerTwo);


        return myview;
    }

    @Override
    public void onStart() {

        super.onStart();

        FirebaseRecyclerAdapter<Data, OneViewHolder>  adapterOne = new FirebaseRecyclerAdapter<Data, OneViewHolder>(
                Data.class, R.layout.item_data, OneViewHolder.class, mOneDatabase) {
            @Override
            protected void populateViewHolder(OneViewHolder oneViewHolder, Data data, int i) {

                oneViewHolder.setTitle(data.getTitle());
                oneViewHolder.setDescription(data.getDescription());
                oneViewHolder.setImage(data.getImage());

                oneViewHolder.myview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getActivity(), OneDetailActivity.class);

                        intent.putExtra("title", data.getTitle());
                        intent.putExtra("description", data.getDescription());
                        intent.putExtra("image", data.getImage());

                        startActivity(intent);

                    }
                });

            }
        };



        recyclerViewOne .setAdapter(adapterOne);



        /////////////////////////////////


        FirebaseRecyclerAdapter<Data, TwoViewHolder> adapterTwo = new FirebaseRecyclerAdapter<Data, TwoViewHolder>(Data.class, R.layout.item_data,TwoViewHolder.class ,mTwoDatabase) {
            @Override
            protected void populateViewHolder(TwoViewHolder twoViewHolder, Data data, int i) {
                twoViewHolder.msetTitle(data.getTitle());
                twoViewHolder.msetDescription(data.getDescription());
                twoViewHolder.msetImage(data.getImage());

                twoViewHolder.myview.setOnClickListener(new View.OnClickListener() {
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


        recyclerViewTwo.setAdapter(adapterTwo);


    }

    ///ONEVIEWHOLDER


    public static class OneViewHolder extends RecyclerView.ViewHolder{


        View myview;

        public OneViewHolder(@NonNull View itemView) {
            super(itemView);
            myview=itemView;
        }
        public void setTitle (String title){
            TextView mTitle = myview.findViewById(R.id.title);
            mTitle.setText(title);

        }
        public void setDescription(String description){
            TextView mDescription = myview.findViewById(R.id.description);
            mDescription.setText(description);
        }
        public  void setImage(String image){

            ImageView mImage = myview.findViewById(R.id.imageview);
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



    ///TWOVIEWHOLDER


    public static class TwoViewHolder extends RecyclerView.ViewHolder{


        View myview;

        public TwoViewHolder(@NonNull View itemView) {
            super(itemView);
            myview=itemView;
        }
        public void msetTitle (String title){
            TextView mTitle = myview.findViewById(R.id.title);
            mTitle.setText(title);

        }
        public void msetDescription(String description){
            TextView mDescription = myview.findViewById(R.id.description);
            mDescription.setText(description);
        }
        public  void msetImage(String image){

            ImageView mImage = myview.findViewById(R.id.imageview);
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