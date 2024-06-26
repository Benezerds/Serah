package com.neztech.serah.restaurant;

import static androidx.fragment.app.FragmentManager.TAG;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.neztech.serah.R;
import com.neztech.serah.interfaceutil.OnUserFetched;
import com.neztech.serah.model.Reservation;
import com.neztech.serah.model.Restaurant;
import com.neztech.serah.model.User;
import com.neztech.serah.utils.RestaurantUtils;
import com.neztech.serah.utils.UserUtils;
import com.squareup.picasso.Picasso;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Locale;

public class RestaurantReservationActivity extends AppCompatActivity {
    Restaurant restoData;
    Button button;
    TextView restoName;
    TextInputEditText pax;
    TextView dateAndTime;
    ImageView dateIcon;
    ImageView restoImage;
    int hour, minute;
    String dateText;
    User currentUser;
    Reservation reservationData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restaurant_reservation);

        //  Initiate Variables
        if (getIntent().getExtras() != null) {
            restoData = (Restaurant) getIntent().getSerializableExtra("restodata");
        }

        variableInitiation();
        setContent();

        //  Find a table button listener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createReservation(new Callback() {
                    @Override
                    public void onSuccess() {
                        Intent intent = new Intent(RestaurantReservationActivity.this, RestaurantConfirmReserveActivity.class);
                        intent.putExtra("restodata", restoData);
                        intent.putExtra("reservationdata", reservationData);
                        intent.putExtra("userdata", currentUser);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure() {
                        // Handle reservation creation failure
                    }
                });
            }
        });
    }


    public void variableInitiation() {
        button = findViewById(R.id.button_makereservation);
        restoName = findViewById(R.id.text_view_reserve_restoname);
        pax = findViewById(R.id.edit_text_reserve_pax);
        dateAndTime = findViewById(R.id.text_view_reserve_date);
        dateIcon = findViewById(R.id.image_view_dateicon);
        restoImage = findViewById(R.id.image_view_reserve);
    }

    public void setContent() {
        restoName.setText(restoData.getRestoName());

        String imageUrl = restoData.getRestoImageUrl();
        Picasso.get().load(imageUrl).into(restoImage);
    }

    private void showTimePicker(int year, int month, int day) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = (timePicker, selectedHour, selectedMinute) -> {
            hour = selectedHour;
            minute = selectedMinute;

            // Update the dateAndTime TextView with the selected date and time
            dateAndTime.setText(String.format(Locale.getDefault(), "%02d.%02d.%04d %02d:%02d", day, month + 1, year, hour, minute));
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute, true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    public void popDateTimePicker(View view) {
        // Show the date picker
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (datePicker, year, month, day) -> {
            // Date selected, now show the time picker
            showTimePicker(year, month, day);
        }, 2024, 5, 2); // Initial date (you can adjust this)

        datePickerDialog.show();
    }

    public void createReservation(Callback callback) {
        int length = 15;

        // Generate a random byte array
        byte[] randomBytes = new byte[length];
        new SecureRandom().nextBytes(randomBytes);

        // Encode the byte array to a base64 string
        String reservationId = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);

        String partySize = pax.getText().toString();

        UserUtils.fetchCurrentUserDetails(new OnUserFetched() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onFetched(User user) {
                // Handle the fetched user object
                currentUser = user;
                Log.d(TAG, "User data: " + user.toString());

                // Create the reservation data object
                reservationData = new Reservation(reservationId, restoData, partySize, dateAndTime.getText().toString(), "PENDING", currentUser);

                // Create the reservation document
                RestaurantUtils.createReservationDocument(reservationId, restoData, partySize, dateAndTime.getText().toString(), "PENDING", currentUser);

                // Invoke the callback (you can pass the reservation data or any other relevant information)
                callback.onSuccess();
            }

            @SuppressLint("RestrictedApi")
            @Override
            public void onError(Exception e) {
                // Handle the error here
                Log.e(TAG, "Error fetching user details", e);
                callback.onFailure();
            }
        });
    }

    // Callback interface
    interface Callback {
        void onSuccess();
        void onFailure();
    }

}

