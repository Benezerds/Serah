package com.neztech.serah.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.neztech.serah.R;
import com.neztech.serah.adapter.MyRecyclerViewAdapter;
import com.neztech.serah.adapter.PoppingRestoRecyclerViewAdapter;
import com.neztech.serah.interfaceutil.OnUserFetched;
import com.neztech.serah.model.Restaurant;
import com.neztech.serah.model.User;
import com.neztech.serah.profile.ProfileActivity;
import com.neztech.serah.utils.RestaurantUtils;
import com.neztech.serah.utils.UserUtils;

import java.util.ArrayList;
import java.util.List;

public class MainMenuActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {
    LinearLayout button;
    ImageView profileIcon;

    TextView userWelcomeText;
    User currentLoggedInUserDetails;
    FirebaseAuth mAuth;

    RecyclerView recyclerView;

    MyRecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_menu);

        mAuth = FirebaseAuth.getInstance();
        profileIcon = findViewById(R.id.image_view_profile);
        userWelcomeText = findViewById(R.id.text_view_user_welcome);

        // data to populate the RecyclerView with
        ArrayList<Integer> imageResources = new ArrayList<>();
        imageResources.add(R.drawable.thank_you);
        imageResources.add(R.drawable.thank_you);
        imageResources.add(R.drawable.thank_you);
        imageResources.add(R.drawable.thank_you);
        imageResources.add(R.drawable.thank_you);

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(MainMenuActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        adapter = new MyRecyclerViewAdapter(this, imageResources);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        getPoppingRestoData();

        //  Fetch current logged in user data
        UserUtils.fetchCurrentUserDetails(new OnUserFetched() {
            @Override
            public void onFetched(User user) {
                userWelcomeText.setText("What do you crave, " + user.getFull_name() + "?");
            }

            @Override
            public void onError(Exception e) {
                // Handle the error here
            }
        });

        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on item position " + position, Toast.LENGTH_SHORT).show();
    }

    public void getPoppingRestoData(){
        RecyclerView poppingRecyclerView = findViewById(R.id.recycler_view_poppin_resto);

        LinearLayoutManager poppingLayoutManager = new LinearLayoutManager(this);
        poppingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        poppingRecyclerView.setLayoutManager(poppingLayoutManager);

        // Assume you have a method getRestaurants() that returns a List of Restaurant objects
        List<Restaurant> restaurants = RestaurantUtils.getRestaurants();

        // Create an instance of the adapter and pass the data to it
        PoppingRestoRecyclerViewAdapter adapter = new PoppingRestoRecyclerViewAdapter(this, restaurants);

        // Set the adapter to the RecyclerView
        poppingRecyclerView.setAdapter(adapter);
    }



}