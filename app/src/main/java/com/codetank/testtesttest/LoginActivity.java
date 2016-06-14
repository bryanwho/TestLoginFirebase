package com.codetank.testtesttest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.codetank.testtesttest.login.LoginFragment;
import com.firebase.client.Firebase;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginFragment loginFragment = new LoginFragment();

        //Handle Fragment Transaction
        getSupportFragmentManager().beginTransaction().
                add(R.id.container, loginFragment, null).commit();

    }

}
