package com.neztech.serah.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.neztech.serah.R;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WheelOfFoodActivity extends AppCompatActivity {
    EditText restoInputField;
    Button pickButton;
    TextView choosenRestoText;
    List<String> restaurantInputs;
    ImageView backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel_of_food);

        variableInitiation();


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        pickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text from the EditText field and split it into lines
                String[] lines = restoInputField.getText().toString().split("\\n");

                // Convert the array to a List
                restaurantInputs = Arrays.asList(lines);

                // Create a Random object
                Random random = new Random();

                // Generate a random index
                int randomIndex = random.nextInt(restaurantInputs.size());

                // Get the restaurant at the random index
                String chosenRestaurant = restaurantInputs.get(randomIndex);

                // Set the chosen restaurant as the text for choosenRestoText
                choosenRestoText.setText(chosenRestaurant);
            }
        });



    }


    public void variableInitiation(){
        backButton = findViewById(R.id.backArrow_navigation);
        restoInputField = findViewById(R.id.edit_text_restolist);
        pickButton = findViewById(R.id.button_foodies_customize);
        choosenRestoText = findViewById(R.id.text_view_pickchoice);
    }
}