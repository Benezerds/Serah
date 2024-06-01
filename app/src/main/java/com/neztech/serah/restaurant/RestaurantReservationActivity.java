package com.neztech.serah.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.neztech.serah.R;
import com.neztech.serah.model.Restaurant;

public class RestaurantReservationActivity extends AppCompatActivity {
    Restaurant restoData;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restaurant_reservation);

        if (getIntent().getExtras() != null) {
            restoData = (Restaurant) getIntent().getSerializableExtra("restodata");
        }

        button = findViewById(R.id.button_makereservation);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to navigate to RestaurantDetailsActivity
                Intent intent = new Intent(RestaurantReservationActivity.this, RestaurantConfirmReserveActivity.class);
                // Pass the clicked restaurant data using serialization
                intent.putExtra("restodata", restoData);
                RestaurantReservationActivity.this.startActivity(intent);
            }
        });
    }
}