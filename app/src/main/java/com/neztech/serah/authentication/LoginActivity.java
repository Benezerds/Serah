package com.neztech.serah.authentication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;
import com.neztech.serah.R;
import com.neztech.serah.animation.CustomAnimation;

public class LoginActivity extends AppCompatActivity {
    MaterialCardView googleCardView;
    MaterialCardView facebookCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //  Initiate Animation on Google and Facebook Material CardViews
        googleCardView = findViewById(R.id.card_view_google);
        facebookCardView = findViewById(R.id.card_view_facebook);

        CustomAnimation.cardViewClickAnimation(LoginActivity.this, googleCardView);
        CustomAnimation.cardViewClickAnimation(LoginActivity.this, facebookCardView);

        //  Implement Business Logic on Buttons



    }
}