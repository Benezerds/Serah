package com.neztech.serah.restaurant;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.neztech.serah.R;

public class RestaurantDetailsActivity extends AppCompatActivity {
    TextView restoName;
    TextView restoRating;
    TextView restoTables;
    TextView restoLocation;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        variableInitiation();
    }





    public void variableInitiation() {
        restoName = findViewById(R.id.text_view_restodetails_title);
        restoRating = findViewById(R.id.text_view_restodetails_rating);
        restoTables = findViewById(R.id.text_view_restodetails_tables);
        restoLocation = findViewById(R.id.text_view_restodetails_location);
        description = findViewById(R.id.text_view_restodetails_description);
    }

}