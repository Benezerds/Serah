package com.neztech.serah.activity;

import static androidx.fragment.app.FragmentManager.TAG;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.neztech.serah.R;
import com.neztech.serah.adapter.MyRecyclerViewAdapter;
import com.neztech.serah.adapter.PoppingRestoRecyclerViewAdapter;
import com.neztech.serah.interfaceutil.OnUserFetched;
import com.neztech.serah.model.Restaurant;
import com.neztech.serah.model.User;
import com.neztech.serah.profile.ProfileActivity;
import com.neztech.serah.restaurant.RestaurantDetailsActivity;
import com.neztech.serah.utils.RestaurantUtils;
import com.neztech.serah.utils.UserUtils;

import java.util.ArrayList;
import java.util.List;

public class MainMenuActivity extends Activity implements LocationListener {
    LinearLayout button;
    ImageView profileIcon;

    TextView userWelcomeText;
    User currentLoggedInUserDetails;
    FirebaseAuth mAuth;

    RecyclerView recyclerView;

    MyRecyclerViewAdapter adapter;
    User currentUser;

    private FusedLocationProviderClient fusedLocationClient;
    double latitude;
    double longitude;
    LocationManager locationManager;
    CardView cardViewSpin;

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mAuth = FirebaseAuth.getInstance();
        profileIcon = findViewById(R.id.image_view_profile);
        userWelcomeText = findViewById(R.id.text_view_user_welcome);
        cardViewSpin = findViewById(R.id.card_view_serah);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        //  Location Permission
        // Initialize locationManager
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Check if location permissions are granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Request location permissions
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        } else {
            // Permission already granted
            // You can proceed with location-related tasks here
            startLocationUpdates();
        }

        // Check if location permissions are granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            // Request location updates
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, location -> {
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();

                            // Create a Snackbar with the latitude and longitude
                            String message = "Latitude: " + latitude + ", Longitude: " + longitude;
                            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();
                        }
                    });
        } else {
            // Request location permissions (show a dialog or request permission)
        }

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
        adapter = new

                MyRecyclerViewAdapter(this, imageResources);
        recyclerView.setAdapter(adapter);

        getPoppingRestoData();

        //  Fetch current logged in user data
        UserUtils.fetchCurrentUserDetails(new OnUserFetched() {
          @Override
          public void onFetched(User user) {
              // Handle the fetched user object
              // For example, update UI elements
              userWelcomeText.setText("What do you crave, " + user.getFull_name() + "?");
              currentUser = user;
              Log.d(TAG, "User data: " + user.toString());
          }

          @Override
          public void onError(Exception e) {
              // Handle the error here
              Log.e(TAG, "Error fetching user details", e);
          }
      });


        //  Image View
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to navigate to RestaurantDetailsActivity
                Intent intent = new Intent(MainMenuActivity.this, ProfileActivity.class);
                // Pass the clicked restaurant data using serialization
                intent.putExtra("passUser", currentUser);
                startActivity(intent);
            }
        });

        cardViewSpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(MainMenuActivity.this, WheelOfFoodActivity.class);
               MainMenuActivity.this.startActivity(intent);
            }
        });
    }


    public void getPoppingRestoData() {
        RecyclerView poppingRecyclerView = findViewById(R.id.recycler_view_poppin_resto);

        LinearLayoutManager poppingLayoutManager = new LinearLayoutManager(this);
        poppingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        poppingRecyclerView.setLayoutManager(poppingLayoutManager);

        // Assume you have a method getFeaturedRestaurants that fetches the data asynchronously
        RestaurantUtils.getFeaturedRestaurants(fetchedRestaurants -> {
            // Create an instance of the adapter and pass the fetched data to it
            PoppingRestoRecyclerViewAdapter adapter = new PoppingRestoRecyclerViewAdapter(this, fetchedRestaurants);

            // Set the adapter to the RecyclerView
            poppingRecyclerView.setAdapter(adapter);
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, start location updates
                startLocationUpdates();
            } else {
                // Permission denied, handle functionality that depends on this permission
                // For example, show a message to the user
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void startLocationUpdates() {
        // Choose the provider based on availability
        String provider;
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            // Handle the case where no location provider is enabled
            // For example, show a message to the user
            return;
        }

        // Request location updates
        locationManager.requestLocationUpdates(provider, 400L, 1F, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        // Handle location updates here
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        // Update UI or perform other tasks
    }
    // Other LocationListener methods (onStatusChanged, onProviderEnabled, onProviderDisabled)
}
