package com.neztech.serah.restaurant;

import static androidx.fragment.app.FragmentManager.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.neztech.serah.R;
import com.neztech.serah.adapter.RestoReviewAdapter;
import com.neztech.serah.model.Restaurant;
import com.neztech.serah.model.Review;
import com.neztech.serah.utils.RestaurantUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RestaurantDetailsActivity extends AppCompatActivity {
    TextView restoName;
    TextView restoRating;
    TextView restoTables;
    TextView restoLocation;
    TextView description;
    ImageView restoImage;
    String averageRating;

    Restaurant restaurant;
    Button reservationButton;
    CardView cardViewRating;
    CardView restoLocationCardView;

    //  List of Reviews to get the rating
    List<Review> reviewList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        variableInitiation();

        if (getIntent().getExtras() != null) {
            restaurant = (Restaurant) getIntent().getSerializableExtra("selectedRestaurant");
        }

        RestaurantUtils.getAllReviewsByReference(restaurant.getDocumentReference(), restaurant, new RestaurantUtils.ReviewsCallback() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onReviewsLoaded(List<Review> reviews) {
                reviewList = reviews;
                averageRating = getTotalAverageRating();
                restoRating.setText("⭐ " + averageRating);
            }
        });



        restoName.setText(restaurant.getRestoName());

        restoTables.setText(String.valueOf(restaurant.getTables()));


        // Assuming restaurant.getLocation() returns the entire template (e.g., "Seattle, 47.6062, -122.3321")
        String locationTemplate = restaurant.getLocation();
        String[] parts = locationTemplate.split(",");
        String location = parts[0]; // Get the first part (location)

        // Set the location text
        restoLocation.setText(location);
        description.setText(restaurant.getDescription());

        //  Set Image
        String imageUrl = restaurant.getRestoImageUrl();
        Picasso.get().load(imageUrl).into(restoImage);

        //  OnClick Listeners
        cardViewRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to navigate to RestaurantDetailsActivity
                Intent intent = new Intent(RestaurantDetailsActivity.this, RestaurantReviewActivity.class);
                // Pass the clicked restaurant data using serialization
                intent.putExtra("restodata", restaurant);
                RestaurantDetailsActivity.this.startActivity(intent);
            }
        });

        reservationButton = findViewById(R.id.button_restaurantdetails_reservation);
        reservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to navigate to RestaurantDetailsActivity
                Intent intent = new Intent(RestaurantDetailsActivity.this, RestaurantReservationActivity.class);
                // Pass the clicked restaurant data using serialization
                intent.putExtra("restodata", restaurant);
                RestaurantDetailsActivity.this.startActivity(intent);
            }
        });

        restoLocationCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Assuming that restaurant.getLocation() returns a string in the format "locationname,lat,lng"
                String location = restaurant.getLocation();
                String[] locationParts = location.split(",");
                // Include the location name in the query parameter
                String uri = "geo:" + locationParts[1] + "," + locationParts[2] + "?q=" + Uri.encode(locationParts[0]);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });
    }


    public void variableInitiation() {
        restoName = findViewById(R.id.text_view_restodetails_title);
        restoRating = findViewById(R.id.text_view_restodetails_rating);
        restoTables = findViewById(R.id.text_view_restodetails_tables);
        restoLocation = findViewById(R.id.text_view_restodetails_location);
        description = findViewById(R.id.text_view_restodetails_description);
        restoImage = findViewById(R.id.image_view_restodetailsimg);
        restoLocationCardView = findViewById(R.id.resto_details_location);


        //  Card View & Button
        cardViewRating = findViewById(R.id.card_view_rating);
        reservationButton = findViewById(R.id.button_restaurantdetails_reservation);
    }

    public String getTotalAverageRating() {
        int totalRatingSum = 0;
        int numberOfReviews = reviewList.size();

        for (Review review : reviewList) {
            totalRatingSum += review.getRating();
        }

        double averageRating = (double) totalRatingSum / numberOfReviews;

        return String.format("%.1f", averageRating);
    }

}