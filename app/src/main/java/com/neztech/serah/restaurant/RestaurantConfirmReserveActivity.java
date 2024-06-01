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

import com.neztech.serah.R;
import com.neztech.serah.model.Restaurant;
import com.neztech.serah.model.User;

public class RestaurantConfirmReserveActivity extends AppCompatActivity {

    Restaurant restoData;
    Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restaurant_confirm_reserve);

        if (getIntent().getExtras() != null) {
            restoData = (Restaurant) getIntent().getSerializableExtra("restodata");
        }

        confirmButton = findViewById(R.id.button_confirm_reserve);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestaurantConfirmReserveActivity.this, RestaurantReviewPostedActivity.class);
                RestaurantConfirmReserveActivity.this.startActivity(intent);
            }
        });


    }
}