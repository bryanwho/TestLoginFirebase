package com.codetank.testtesttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.codetank.testtesttest.login.LoginFragment;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

public class LoginActivity extends AppCompatActivity {

    public static final String FIRE_BASE_URL = "https://bptestlog.firebaseio.com/";
    public static final String USERS_ROUTE = "users/";
    public Firebase mFirebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseRef = new Firebase(FIRE_BASE_URL + USERS_ROUTE);

        AuthData authData = mFirebaseRef.getAuth();
        if (authData != null) {
            // user authenticated
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            mFirebaseRef.unauth();
            return;
        }
            // no user authenticated


        LoginFragment loginFragment = new LoginFragment();

        //Handle Fragment Transaction
        getSupportFragmentManager().beginTransaction().
                add(R.id.container, loginFragment, null).commit();
    }

}
