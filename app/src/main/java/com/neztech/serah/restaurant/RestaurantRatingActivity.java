package com.neztech.serah.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.neztech.serah.R;
import com.neztech.serah.model.Reservation;
import com.neztech.serah.model.Restaurant;
import com.neztech.serah.model.Review;
import com.neztech.serah.model.User;
import com.neztech.serah.utils.RestaurantUtils;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class RestaurantRatingActivity extends AppCompatActivity {
    TextView skipText;
    RatingBar ratingBar;
    TextView ratingDesc;
    TextInputEditText reviewInput;
    Button postButton;

    Restaurant restoData;
    User userData;
    Reservation reservationData;
    Review review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restaurant_rating);

        variableInitiation();

        if (getIntent().getExtras() != null) {
            restoData = (Restaurant) getIntent().getSerializableExtra("restodata");
            userData = (User) getIntent().getSerializableExtra("userdata");
            reservationData = (Reservation) getIntent().getSerializableExtra("reservationdata");
        }

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createReview();

                Intent intent = new Intent(RestaurantRatingActivity.this, RestaurantReviewPostedActivity.class);
                RestaurantRatingActivity.this.startActivity(intent);
            }
        });
    }




    public void variableInitiation() {
        skipText =findViewById(R.id.text_view_skip);
        ratingBar = findViewById(R.id.resto_review_ratingbar);
        ratingDesc = findViewById(R.id.text_view_ratingdescription);
        reviewInput = findViewById(R.id.edit_text_review);
        postButton = findViewById(R.id.button_restaurantrating_post);
    }

    public void createReview() {
        int length = 15;

        // Generate a random byte array
        byte[] randomBytes = new byte[length];
        new SecureRandom().nextBytes(randomBytes);

        // Encode the byte array to a base64 string
        String reviewId = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);

        // Get Current Date
        Date currentDate = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE dd MM yyyy HH:mm");
        String formattedDate = dateFormat.format(currentDate);

        review = new Review(restoData, reviewId, reviewInput.getText().toString(), formattedDate, (int) ratingBar.getRating(), userData);

        RestaurantUtils.createReviewDocument(review);
    }


}