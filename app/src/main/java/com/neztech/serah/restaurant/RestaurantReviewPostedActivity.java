package com.neztech.serah.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.neztech.serah.R;
import com.neztech.serah.activity.MainMenuActivity;

public class RestaurantReviewPostedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restaurant_review_posted);

        // Create a Handler
        Handler handler = new Handler();

        // Delayed action: Redirect to MainMenuActivity after 5 seconds
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(RestaurantReviewPostedActivity.this, MainMenuActivity.class);
                startActivity(intent);
            }
        }, 2000); // 2 Seconds
    }
}