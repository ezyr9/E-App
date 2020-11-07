package com.example.e_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class OneDetailActivity extends AppCompatActivity {



    private ImageView imageView;
    private TextView title;
    private TextView description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_detail);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        imageView = findViewById(R.id.image_detail);
        title = findViewById(R.id.title_detail);
        description = findViewById(R.id.description_detail);

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

    }
}