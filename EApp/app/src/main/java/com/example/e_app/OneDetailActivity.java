package com.example.e_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_app.Model.Data;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class OneDetailActivity extends AppCompatActivity {



    private ImageView imageView;
    private TextView title;
    private TextView description;
    private Button buttonCart;
    private DatabaseReference mCartDatabase;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_detail);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        imageView = findViewById(R.id.image_detail);
        title = findViewById(R.id.title_detail);
        description = findViewById(R.id.description_detail);
        buttonCart = findViewById(R.id.button_buy);



        Intent intent = getIntent();

        String mTitle = intent.getStringExtra("title");
        String mDescription = intent.getStringExtra("description");
        String mImage = intent.getStringExtra("image");

        title.setText(mTitle);
        description.setText(mDescription);

        Picasso.get().load(mImage).networkPolicy(NetworkPolicy.OFFLINE).into(imageView,
                new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {

                        Picasso.get().load(mImage).into(imageView);
                    }
                });





        buttonCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userId = user.getUid();
                mCartDatabase = FirebaseDatabase.getInstance().getReference().child("Cart");
                Toast.makeText(OneDetailActivity.this, "Add to cart", Toast.LENGTH_SHORT).show();

                Map<String, Data> add1 = new HashMap<>();
                add1.put(userId, new Data(mImage,mTitle, mDescription));

                mCartDatabase.push().setValue(add1);


            }
        });




    }
}