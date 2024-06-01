package com.neztech.serah.restaurant;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
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
import com.neztech.serah.model.Restaurant;

import java.util.Locale;

public class RestaurantReservationActivity extends AppCompatActivity {
    Restaurant restoData;
    Button button;
    TextView restoName;
    TextInputEditText pax;
    TextView dateAndTime;
    ImageView dateIcon;
    int hour, minute;
    String dateText;


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



        //  Find a table button listener
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


    public void variableInitiation() {
        button = findViewById(R.id.button_makereservation);
        restoName = findViewById(R.id.text_view_reserve_restoname);
        pax = findViewById(R.id.edit_text_reserve_pax);
        dateAndTime = findViewById(R.id.text_view_reserve_date);
        dateIcon = findViewById(R.id.image_view_dateicon);
    }

    public void setContent() {
        restoName.setText(restoData.getRestoName());
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
}