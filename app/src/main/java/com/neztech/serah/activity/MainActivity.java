package com.neztech.serah.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.neztech.serah.R;
import com.neztech.serah.authentication.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        // Check if the user is logged in
//        if (isLoggedIn()) {
//            // If the user is logged in, redirect them to the MainMenuActivity
//
//        } else {
//            // If the user is not logged in, redirect them to the LoginActivity
//            startLoginActivity();
//        }
//
//        // Close this activity so it's removed from the activity stack
//        finish();
    }

//    public void startLoginActivity() {
//        Intent intent = new Intent(this, LoginActivity.class);
//        startActivity(intent);
//    }

    public void startMainMenuActivity() {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

//    private boolean isLoggedIn() {
//        // Implement your logic here to check if the user is logged in
//        // This could involve checking shared preferences, a database, etc.
//        return false;
//    }
}
