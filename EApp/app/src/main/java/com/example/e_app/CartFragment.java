package com.example.e_app;

import android.app.Activity;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


public class CartFragment extends Fragment {



    private RecyclerView recyclerViewCart;


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    //Firebase

    private DatabaseReference mCartDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview = inflater.inflate(R.layout.fragment_cart, container, false);


        mCartDatabase = FirebaseDatabase.getInstance().getReference().child("Cart");





        recyclerViewCart = myview.findViewById(R.id.recycler_cart);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerViewCart.setHasFixedSize(true);
        recyclerViewCart.setLayoutManager(layoutManager);

        String userId = user.getUid();
        mCartDatabase = FirebaseDatabase.getInstance().getReference().child("Cart");


        return myview;
    }


    @Override
    public void onStart() {

        super.onStart();

        FirebaseRecyclerAdapter<Data, CartViewHolder> adapterCart = new FirebaseRecyclerAdapter<Data, CartViewHolder>(
                Data.class, R.layout.cus_item, CartViewHolder.class,mCartDatabase

        ) {
            @Override
            protected void populateViewHolder(CartViewHolder cartViewHolder, Data data, int i) {



                /*cartViewHolder.setTitle(data.getTitle());
                cartViewHolder.setDescription(data.getDescription());
                cartViewHolder.setImage(data.getImage());*/

            }
        };
        recyclerViewCart.setAdapter(adapterCart);







    }

    ///ONEVIEWHOLDER


    public static class CartViewHolder extends RecyclerView.ViewHolder{


        View myview;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            myview=itemView;
        }
        public void setUser (String user){

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




}