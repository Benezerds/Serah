package com.neztech.serah.activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.installations.interop.BuildConfig;
import com.neztech.serah.R;
import com.neztech.serah.authentication.LoginActivity;
import com.neztech.serah.utils.FirebasePersonalUtils;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);

        finishActivity(10);
        String apiKey = "AIzaSyCo8H5XjCJbhNAlFoBVzaPI5MLrYOogAU0";

        // Initialize the SDK
        Places.initialize(getApplicationContext(), apiKey);

        // Create a new PlacesClient instance
        PlacesClient placesClient = Places.createClient(this);

        mAuth = FirebaseAuth.getInstance();
        // Check if the user is logged in
        boolean loggedIn = FirebasePersonalUtils.checkCurrentUser(mAuth);

        if (loggedIn) {
            FirebaseUser user = mAuth.getCurrentUser();
            if (user != null) {
                String uid = user.getUid();
                Log.d(TAG, "User is  logged in with UID: " + uid);
                startMainMenuActivity();
            }
        } else {
            startLoginActivity();
        }

    }

    public void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void startMainMenuActivity() {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    public void initiatePlacesSDK() {


    }
}
