package com.neztech.serah.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.neztech.serah.R;
import com.neztech.serah.authentication.LoginActivity;
import com.neztech.serah.utils.FirebasePersonalUtils;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        mAuth = FirebaseAuth.getInstance();
        // Check if the user is logged in
        boolean loggedIn = FirebasePersonalUtils.checkCurrentUser(mAuth);

        if (loggedIn = true) {
            startMainMenuActivity();
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

//    private boolean isLoggedIn() {
//        // Implement your logic here to check if the user is logged in
//        // This could involve checking shared preferences, a database, etc.
//        return false;
//    }
}
