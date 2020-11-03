package com.example.e_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView;
    private AllFragment allFragment;
    private OneFragment oneFragment;
    private TwoFragment twoFragment;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottombar);
        allFragment = new AllFragment();
        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();


        setFragment(allFragment);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.all:
                        bottomNavigationView.setItemBackgroundResource(R.color.c1);
                        setFragment(allFragment);
                        return true;
                    case R.id.one:
                        bottomNavigationView.setItemBackgroundResource(R.color.c2);
                        setFragment(oneFragment);
                        return true;
                    case R.id.two:
                        bottomNavigationView.setItemBackgroundResource(R.color.c3);
                        setFragment(twoFragment);
                        return true;
                    default:
                        return false;
                }


            }
        });

    }



    public void setFragment (Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainframe, fragment);
        fragmentTransaction.commit();
    }
}