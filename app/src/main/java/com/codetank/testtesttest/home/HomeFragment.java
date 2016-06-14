package com.codetank.testtesttest.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codetank.testtesttest.LoginActivity;
import com.codetank.testtesttest.R;
import com.codetank.testtesttest.data.User;
import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by bryan on 6/14/16.
 */
public class HomeFragment extends Fragment {

    TextView textView;
    User user;
    Firebase myFirebaseRef;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myFirebaseRef = new Firebase(LoginActivity.FIRE_BASE_URL + LoginActivity.USERS_ROUTE);

        AuthData auth = myFirebaseRef.getAuth();
        myFirebaseRef.child(auth.getUid()).addValueEventListener(valueEventListener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment_main, container, false);

        textView = (TextView) view.findViewById(R.id.textview);

        return view;
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

           user = dataSnapshot.getValue(User.class);
            Log.d("flow", "user info: " + user.getEmail());

        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }
    };
}
