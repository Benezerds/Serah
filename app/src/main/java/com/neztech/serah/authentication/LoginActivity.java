package com.neztech.serah.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.neztech.serah.R;
import com.neztech.serah.activity.MainMenuActivity;
import com.neztech.serah.animation.CustomAnimation;
import com.neztech.serah.utils.FirebasePersonalUtils;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText emailInput;
    TextInputEditText passInput;

    MaterialCardView googleCardView;
    MaterialCardView facebookCardView;
    MaterialButton buttonLogin;
    ImageView backButton;
    TextView signUpToggle;

    //  Firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //  Initiate Animation on Google and Facebook Material CardViews
        googleCardView = findViewById(R.id.card_view_google);
        facebookCardView = findViewById(R.id.card_view_facebook);

        emailInput = findViewById(R.id.text_field_email);
        passInput = findViewById(R.id.text_field_password);

        buttonLogin = findViewById(R.id.button_login);

        signUpToggle = findViewById(R.id.text_view_signup);

        CustomAnimation.cardViewClickAnimation(LoginActivity.this, googleCardView);
        CustomAnimation.cardViewClickAnimation(LoginActivity.this, facebookCardView);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebasePersonalUtils mUtils = new FirebasePersonalUtils(LoginActivity.this);

                mUtils.signInRequest(emailInput.getText().toString(), passInput.getText().toString()).addOnCompleteListener(new OnCompleteListener<Boolean>() {
                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
                            startActivity(intent);
                    } else {

                        }
                    }
                });
            }
        });


        //  Sign Up on-click listener
        signUpToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}