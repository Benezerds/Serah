package com.neztech.serah.authentication;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.neztech.serah.R;
import com.neztech.serah.activity.MainMenuActivity;
import com.neztech.serah.utils.FirebasePersonalUtils;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    TextInputEditText emailInput;
    TextInputEditText nameInput;
    TextInputEditText phoneInput;
    TextInputEditText passInput;

    MaterialButton registerButton;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //  Initiate Variables
        emailInput = findViewById(R.id.text_field_email);
        nameInput = findViewById(R.id.text_field_fullname);
        phoneInput = findViewById(R.id.text_field_phonenum);
        passInput = findViewById(R.id.text_field_password);

        //  Button
        registerButton = findViewById(R.id.button_register);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebasePersonalUtils mUtils = new FirebasePersonalUtils(SignupActivity.this);

                mUtils.createAccountWithEmail(emailInput.getText().toString(), passInput.getText().toString(), nameInput.getText().toString(), phoneInput.getText().toString()).addOnCompleteListener(new OnCompleteListener<Boolean>() {
                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(SignupActivity.this, MainMenuActivity.class);
                            startActivity(intent);
                        } else {
                            // Handle failure here
                        }
                    }
                });
            }
        });

    }
}