package com.neztech.serah.profile;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.neztech.serah.R;
import com.neztech.serah.adapter.ReservationAdapter;
import com.neztech.serah.adapter.RestoReviewAdapter;
import com.neztech.serah.model.Reservation;
import com.neztech.serah.model.Review;
import com.neztech.serah.model.User;
import com.neztech.serah.restaurant.RestaurantReviewActivity;
import com.neztech.serah.utils.RestaurantUtils;
import com.neztech.serah.utils.UserUtils;

import java.util.List;

public class ReservationHistoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    User user;
    List<Reservation> reservationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reservation_history);

        recyclerView = findViewById(R.id.recycler_view_resto_reservatiion);

        if (getIntent().getExtras() != null) {
            user = (User) getIntent().getSerializableExtra("passUser");


            UserUtils.getAllReservationsByReference(user.getDocumentReference(), user, new UserUtils.ReservationsCallback() {
                @SuppressLint("RestrictedApi")
                @Override
                public void onReservationsLoaded(List<Reservation> reservations) {
                    reservationList = reservations;
                    Log.d(FragmentManager.TAG, "All Reviews Fetched: " + reservationList.toString());

                    //  Populate recycler view
                    ReservationAdapter reservationAdapter = new ReservationAdapter(reservationList);
                    recyclerView.setAdapter(reservationAdapter);

                    LinearLayoutManager layoutManager = new LinearLayoutManager(ReservationHistoryActivity.this);
                    recyclerView.setLayoutManager(layoutManager);

                }
            });
        }
    }
}