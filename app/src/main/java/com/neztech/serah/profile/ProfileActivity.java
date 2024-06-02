package com.neztech.serah.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.neztech.serah.R;
import com.neztech.serah.activity.MainActivity;
import com.neztech.serah.activity.MainMenuActivity;
import com.neztech.serah.authentication.LoginActivity;
import com.neztech.serah.model.Restaurant;
import com.neztech.serah.model.User;
import com.neztech.serah.utils.FirebasePersonalUtils;

import java.io.Serializable;

public class ProfileActivity extends AppCompatActivity {
    ImageView backArrow;
    MaterialButton logoutButton;

    ImageView profileImage;
    TextView fullName;
    TextView email;
    TextView phoneNumber;
    User currentUser;
    LinearLayout reservationOption;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //  Initialize variable data
        variableInitiation();


        if (getIntent().getExtras() != null) {
            currentUser = (User) getIntent().getSerializableExtra("passUser");

            if (currentUser != null) {
                //  Set Variables based on the serialized user data
                setVariables();
            }
        }





        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();

                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        reservationOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ReservationHistoryActivity.class);
                intent.putExtra("userData", currentUser);
                ProfileActivity.this.startActivity(intent);
            }
        });
    }


    public void variableInitiation() {
        //  Variable instantiation
        backArrow = findViewById(R.id.backArrow);
        logoutButton = findViewById(R.id.button_logout);
        profileImage = findViewById(R.id.profileImage);
        fullName = findViewById(R.id.text_view_fullName);
        email = findViewById(R.id.text_view_email);
        phoneNumber = findViewById(R.id.text_view_phoneNumber);
        reservationOption = findViewById(R.id.linear_layout_reservation);
    }

    public void setVariables() {
        fullName.setText(currentUser.getFull_name());
        email.setText(currentUser.getEmail());
        phoneNumber.setText(currentUser.getPhoneNumber());
    }
}