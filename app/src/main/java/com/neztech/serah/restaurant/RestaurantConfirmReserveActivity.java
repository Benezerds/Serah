package com.neztech.serah.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.neztech.serah.R;
import com.neztech.serah.model.Reservation;
import com.neztech.serah.model.Restaurant;
import com.neztech.serah.model.User;
import com.squareup.picasso.Picasso;

public class RestaurantConfirmReserveActivity extends AppCompatActivity {

    Restaurant restoData;
    User userData;
    Reservation reservationData;
    Button confirmButton;
    TextView restoName;
    TextView dateAndTime;
    TextView partySize;
    TextView name;
    TextView phoneNum;
    TextView email;
    ImageView imageResto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restaurant_confirm_reserve);

        if (getIntent().getExtras() != null) {
            restoData = (Restaurant) getIntent().getSerializableExtra("restodata");
            userData = (User) getIntent().getSerializableExtra("userdata");
            reservationData = (Reservation) getIntent().getSerializableExtra("reservationdata");
        }

        //  Invoke Variable Initiation & Set Contents
        variableInitiation();
        setContent();


        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestaurantConfirmReserveActivity.this, RestaurantRatingActivity.class);
                intent.putExtra("restodata", restoData);
                intent.putExtra("reservationdata", reservationData);
                intent.putExtra("userdata", userData);
                RestaurantConfirmReserveActivity.this.startActivity(intent);
            }
        });
    }


    public void variableInitiation() {
        restoName = findViewById(R.id.text_view_confirm_restoname);
        dateAndTime = findViewById(R.id.text_view_confirm_date);
        partySize = findViewById(R.id.text_view_confirm_partysize);
        name = findViewById(R.id.text_view_confirm_username);
        phoneNum = findViewById(R.id.text_view_confirm_phonenumber);
        email = findViewById(R.id.text_view_confirm_email);

        confirmButton = findViewById(R.id.button_confirm_reserve);
        imageResto = findViewById(R.id.image_view_confirm);
    }

    public void setContent(){
        restoName.setText(restoData.getRestoName());
        dateAndTime.setText(reservationData.getReservationDate());
        partySize.setText(reservationData.getPartySize());
        name.setText(userData.getFull_name());
        phoneNum.setText(userData.getPhoneNumber());
        email.setText(userData.getEmail());

        //  Set Image
        String imageUrl = restoData.getRestoImageUrl();
        Picasso.get().load(imageUrl).into(imageResto);
    }
}