package com.neztech.serah.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.neztech.serah.R;
import com.neztech.serah.model.Restaurant;
import com.neztech.serah.model.Review;
import com.neztech.serah.utils.RestaurantUtils;

import java.util.List;

public class RestaurantDetailsActivity extends AppCompatActivity {
    TextView restoName;
    TextView restoRating;
    TextView restoTables;
    TextView restoLocation;
    TextView description;

    Restaurant restaurant;
    Button reservationButton;
    CardView cardViewRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        variableInitiation();

        if (getIntent().getExtras() != null) {
            restaurant = (Restaurant) getIntent().getSerializableExtra("selectedRestaurant");
        }

        restoName.setText(restaurant.getRestoName());
        restoRating.setText("⭐ " + String.valueOf(restaurant.getRating()));
        restoTables.setText(String.valueOf(restaurant.getTables()));

        // Assuming restaurant.getLocation() returns the entire template (e.g., "Seattle, 47.6062, -122.3321")
        String locationTemplate = restaurant.getLocation();
        String[] parts = locationTemplate.split(",");
        String location = parts[0]; // Get the first part (location)

        // Set the location text
        restoLocation.setText(location);
        description.setText(restaurant.getDescription());

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
    }


    public void variableInitiation() {
        restoName = findViewById(R.id.text_view_restodetails_title);
        restoRating = findViewById(R.id.text_view_restodetails_rating);
        restoTables = findViewById(R.id.text_view_restodetails_tables);
        restoLocation = findViewById(R.id.text_view_restodetails_location);
        description = findViewById(R.id.text_view_restodetails_description);


        //  Card View & Button
        cardViewRating = findViewById(R.id.card_view_rating);
        reservationButton = findViewById(R.id.button_restaurantdetails_reservation);
    }

}