package com.neztech.serah.restaurant;

import static androidx.fragment.app.FragmentManager.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.neztech.serah.R;
import com.neztech.serah.adapter.RestoReviewAdapter;
import com.neztech.serah.model.Restaurant;
import com.neztech.serah.model.Review;
import com.neztech.serah.utils.RestaurantUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    List<Review> restoReviews;
    ImageView forwardArrow;
    RecyclerView restoReviewsRecycler;


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


        Log.d(TAG, "Running All Reviews");


        RestaurantUtils.getAllReviewsByReference(restaurant.getDocumentReference(), restaurant, new RestaurantUtils.ReviewsCallback() {
            @Override
            public void onReviewsLoaded(List<Review> reviews) {
                restoReviews = reviews;
                Log.d(TAG, "All Reviews Fetched: " + restoReviews.toString());

                // Populate recycler view
                RestoReviewAdapter reviewAdapter = new RestoReviewAdapter(RestaurantReviewActivity.this, restoReviews);
                restoReviewsRecycler.setAdapter(reviewAdapter);
                LinearLayoutManager layoutManager = new LinearLayoutManager(RestaurantReviewActivity.this);
                restoReviewsRecycler.setLayoutManager(layoutManager);

                //  Set Content
                setContent();
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
        forwardArrow = findViewById(R.id.forwardArrow);
        restoReviewsRecycler = findViewById(R.id.recycler_view_resto_reviews);
    }

    public void setContent() {
        // Left Side Content
        rating.setText(getTotalRating());
        ratingBar.setRating((float) getTotalRatingAsDouble());
        totalReview.setText(String.valueOf(restoReviews.size()) + " reviews");

        //  Rating bar
        Map<Integer, Double> ratingPercentages = calculateRatingPercentages(restoReviews);

        // Set progress for each rating's progress bar
        progressBar1.setProgress((int) Math.round(ratingPercentages.getOrDefault(1, 0.0)));
        progressBar2.setProgress((int) Math.round(ratingPercentages.getOrDefault(2, 0.0)));
        progressBar3.setProgress((int) Math.round(ratingPercentages.getOrDefault(3, 0.0)));
        progressBar4.setProgress((int) Math.round(ratingPercentages.getOrDefault(4, 0.0)));
        progressBar5.setProgress((int) Math.round(ratingPercentages.getOrDefault(5, 0.0)));

        // Set the text for percentage5 TextView
        double percentageForRating5 = ratingPercentages.getOrDefault(5, 0.0);
        percentage1.setText(formatPercentage(ratingPercentages.getOrDefault(1, 0.0)));
        percentage2.setText(formatPercentage(ratingPercentages.getOrDefault(2, 0.0)));
        percentage3.setText(formatPercentage(ratingPercentages.getOrDefault(3, 0.0)));
        percentage4.setText(formatPercentage(ratingPercentages.getOrDefault(4, 0.0)));
        percentage5.setText(formatPercentage(ratingPercentages.getOrDefault(5, 0.0)));
    }

    public String getTotalRating() {
        int totalRatingSum = 0;
        int numberOfReviews = restoReviews.size();

        for (Review review : restoReviews) {
            totalRatingSum += review.getRating();
        }

        double averageRating = (double) totalRatingSum / numberOfReviews;

        return String.valueOf(averageRating);
    }

    public double getTotalRatingAsDouble() {
        int totalRatingSum = 0;
        int numberOfReviews = restoReviews.size();

        for (Review review : restoReviews) {
            totalRatingSum += review.getRating();
        }

        return (double) totalRatingSum / numberOfReviews;
    }

    public Map<Integer, Double> calculateRatingPercentages(List<Review> reviews) {
        Map<Integer, Integer> ratingCounts = new HashMap<>();
        int totalReviews = reviews.size();

        // Count occurrences of each rating
        for (Review review : reviews) {
            int rating = review.getRating();
            ratingCounts.put(rating, ratingCounts.getOrDefault(rating, 0) + 1);
        }

        // Calculate percentages
        Map<Integer, Double> ratingPercentages = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : ratingCounts.entrySet()) {
            int rating = entry.getKey();
            int count = entry.getValue();
            double percentage = (double) count / totalReviews * 100;
            ratingPercentages.put(rating, percentage);
        }

        return ratingPercentages;
    }

    private String formatPercentage(double percentage) {
        int roundedPercentage = (int) Math.round(percentage);
        return roundedPercentage + "%";
    }



}