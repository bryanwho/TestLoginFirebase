package com.codetank.testtesttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codetank.testtesttest.home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomeFragment homeFragment = new HomeFragment();

        //Handle Fragment Transaction
        getSupportFragmentManager().beginTransaction().
                add(R.id.container, homeFragment, null).commit();
    }
}
