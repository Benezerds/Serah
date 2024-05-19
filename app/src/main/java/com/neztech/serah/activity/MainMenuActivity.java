package com.neztech.serah.activity;

import static android.content.ContentValues.TAG;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.neztech.serah.R;
import com.neztech.serah.authentication.LoginActivity;
import com.neztech.serah.interfaceutil.OnUserFetched;
import com.neztech.serah.model.User;
import com.neztech.serah.profile.ProfileActivity;
import com.neztech.serah.utils.UserUtils;

import java.util.Random;

public class MainMenuActivity extends AppCompatActivity {
    LinearLayout button;
    ImageView profileIcon;

    TextView userWelcomeText;
    User currentLoggedInUserDetails;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_menu);

        mAuth = FirebaseAuth.getInstance();
        profileIcon = findViewById(R.id.image_view_profile);
        userWelcomeText = findViewById(R.id.text_view_user_welcome);

        //  Fetch current logged in user data
        UserUtils.fetchCurrentUserDetails(new OnUserFetched() {
            @Override
            public void onFetched(User user) {
                userWelcomeText.setText("What do you crave, " + user.getFull_name() + "?");
            }

            @Override
            public void onError(Exception e) {
                // Handle the error here
            }
        });


        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

//        button = findViewById(R.id.ll_done);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                Intent intent = new Intent(MainMenuActivity.this, LoginActivity.class);
//                startActivity(intent);
//
//                Log.d(TAG, "Succesfully Signed Out!");
//                finish();
//            }
//        });
    }
}