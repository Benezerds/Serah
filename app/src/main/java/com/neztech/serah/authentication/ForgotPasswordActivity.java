package com.neztech.serah.authentication;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.neztech.serah.R;
import com.neztech.serah.utils.FirebasePersonalUtils;

public class ForgotPasswordActivity extends AppCompatActivity {
    TextInputEditText emailInput;
    MaterialButton forgotButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgot_password);

        //  Variable Initiation
        emailInput = findViewById(R.id.text_field_email);
        forgotButton = findViewById(R.id.button_forgot);

        forgotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebasePersonalUtils.resetPassword(emailInput.getText().toString());
            }
        });
    }
}