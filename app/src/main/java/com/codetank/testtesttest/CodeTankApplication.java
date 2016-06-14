package com.codetank.testtesttest;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by bryan on 6/14/16.
 */
public class CodeTankApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
