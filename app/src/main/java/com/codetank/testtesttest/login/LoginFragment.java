package com.codetank.testtesttest.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.codetank.testtesttest.LoginActivity;
import com.codetank.testtesttest.R;
import com.codetank.testtesttest.data.User;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bryan on 6/6/16.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{

    public static final String FIRE_BASE_URL = "https://bptestlog.firebaseio.com/";
    public static final String USERS_ROUTE = "users/";

    Firebase myFirebaseRef;

    private Button loginBtn;
    private Button signUpBtn;
    private EditText userName;
    private EditText password;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myFirebaseRef = new Firebase(FIRE_BASE_URL + USERS_ROUTE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.login_fragment_main, container, false);

        loginBtn = (Button) view.findViewById(R.id.login_btn);
        signUpBtn = (Button) view.findViewById(R.id.sign_up_btn);
        userName = (EditText) view.findViewById(R.id.username_et);
        password = (EditText) view.findViewById(R.id.password_et);

        signUpBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);

        return view;
    }

    private void login() {

        if(userName.length() == 0 || password.length() == 0) {
            return;
        }

        myFirebaseRef.authWithPassword(userName.getText().toString(), password.getText().toString(), new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                //Inform Activity
                Log.d("flow", "User Authenticated. UID: " + authData.getUid() + " Token: " + authData.getToken()
                        + " expires: " + authData.getExpires());


            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                //Inform User to try Again
                Log.d("flow", "User Authentication failed: " + firebaseError.getMessage());
            }
        });
    }

    public void signUp() {

        if(!loginCredentialsValidated()) {
            return;
        }

        myFirebaseRef.createUser(userName.getText().toString(), password.getText().toString(), new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                Log.d("flow", "Successfully created user account with uid: " + result.get("uid"));

                User user = new User(userName.getText().toString());
                String uid = (String) result.get("uid");

                myFirebaseRef.child(uid).setValue(user);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                // there was an error
                Log.d("flow", "ERROR: " + firebaseError.toString() + " " + firebaseError.getMessage() + " " + firebaseError.getDetails());
            }
        });
    }

    public boolean loginCredentialsValidated() {
        boolean isValid = true;

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(userName.getText().toString()).matches()){
            isValid = false;
            //send error
        }

        if(password.length() < 5){
            isValid = false;
            //send error
        }

        return isValid;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                login();
                break;
            case R.id.sign_up_btn:
                Log.d("flow", "sign up clicked");
                signUp();
                break;
        }
    }


}
