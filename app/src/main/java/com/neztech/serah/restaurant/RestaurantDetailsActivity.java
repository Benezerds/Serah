package com.neztech.serah.restaurant;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.neztech.serah.R;
import com.neztech.serah.model.Restaurant;

public class RestaurantDetailsActivity extends AppCompatActivity {
    TextView restoName;
    TextView restoRating;
    TextView restoTables;
    TextView restoLocation;
    TextView description;

    Restaurant restaurant;

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
    }


    public void variableInitiation() {
        restoName = findViewById(R.id.text_view_restodetails_title);
        restoRating = findViewById(R.id.text_view_restodetails_rating);
        restoTables = findViewById(R.id.text_view_restodetails_tables);
        restoLocation = findViewById(R.id.text_view_restodetails_location);
        description = findViewById(R.id.text_view_restodetails_description);
    }

}