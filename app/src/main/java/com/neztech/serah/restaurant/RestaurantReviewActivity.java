package com.neztech.serah.restaurant;

import static androidx.fragment.app.FragmentManager.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.neztech.serah.R;
import com.neztech.serah.model.Restaurant;
import com.neztech.serah.model.Review;
import com.neztech.serah.utils.RestaurantUtils;

import java.util.List;

public class RestaurantReviewActivity extends AppCompatActivity {
    TextView title;
    TextView rating;
    RatingBar ratingBar;
    TextView totalReview;
    ProgressBar progressBar5;
    ProgressBar progressBar4;
    ProgressBar progressBar3;
    ProgressBar progressBar2;
    ProgressBar progressBar1;
    TextView percentage5;
    TextView percentage4;
    TextView percentage3;
    TextView percentage2;
    TextView percentage1;

    Restaurant restaurant;
    List<Review> reviews;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restaurant_review);

        variableInitiation();

        if (getIntent().getExtras() != null) {
            restaurant = (Restaurant) getIntent().getSerializableExtra("restodata");
        }


        RestaurantUtils.fetchRestaurantReviews(restaurant.getRestaurantId())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Task completed successfully
                        reviews = task.getResult();
                        // Now you can use the reviews data
                    } else {
                        // Handle the error (e.g., log or display an error message)
                        Log.d(TAG, "Error fetching restaurant reviews", task.getException());
                    }
                });
    }




    public void variableInitiation() {
        title = findViewById(R.id.text_view_restoreview_title);
        rating = findViewById(R.id.text_view_restoreview_rating);
        ratingBar = findViewById(R.id.resto_review_ratingbar);
        totalReview = findViewById(R.id.text_view_restoreview_reviewstotal);
        progressBar5 = findViewById(R.id.progress_bar_rating5);
        progressBar4 = findViewById(R.id.progress_bar_rating4);
        progressBar3 = findViewById(R.id.progress_bar_rating3);
        progressBar2 = findViewById(R.id.progress_bar_rating2);
        progressBar1 = findViewById(R.id.progress_bar_rating1);
        percentage5 = findViewById(R.id.text_view_rating_percentage5);
        percentage4 = findViewById(R.id.text_view_rating_percentage4);
        percentage3 = findViewById(R.id.text_view_rating_percentage3);
        percentage2 = findViewById(R.id.text_view_rating_percentage2);
        percentage1 = findViewById(R.id.text_view_rating_percentage1);
    }
}