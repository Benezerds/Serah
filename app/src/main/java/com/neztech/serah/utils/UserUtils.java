package com.neztech.serah.utils;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.neztech.serah.interfaceutil.OnUserFetched;
import com.neztech.serah.model.Reservation;
import com.neztech.serah.model.Restaurant;
import com.neztech.serah.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class UserUtils {

    private static final String TAG = "UserUtils";
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static FirebaseAuth auth = FirebaseAuth.getInstance();

    public static void fetchCurrentUserDetails(OnUserFetched callback) {
        String uid = auth.getCurrentUser().getUid();

        // Get the document reference
        DocumentReference userRef = db.collection("User").document(uid);

        userRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    User currentUserDetails = document.toObject(User.class);
                    callback.onFetched(currentUserDetails);
                } else {
                    callback.onError(new Exception("No such document"));
                }
            } else {
                callback.onError(task.getException());
            }
        });
    }

    public interface ReservationsCallback {
        void onReservationsLoaded(List<Reservation> reservations);
    }

    public static void getAllReservationsByReference(
            DocumentReference userId,
            User userData,
            ReservationsCallback callback) {
        List<Reservation> reservations = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference reservationsCollection = db.collection("Reservation");
        Query query = reservationsCollection.whereEqualTo("uid", userId);

        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                int expectedReservationCount = task.getResult().size(); // Get the expected reservation count
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Reservation reservation = document.toObject(Reservation.class);
                    reservation.setUser(userData);

                    // Retrieve the restaurant's ID reference field from the reservation document
                    DocumentReference restaurantIdRef = document.getDocumentReference("restaurantId");

                    // Fetch restaurant document based on the ID reference
                    getRestaurantDocument(restaurantIdRef, reservation, reservations, callback, expectedReservationCount);
                }
            } else {
                Log.e(TAG, "Error getting reservations: ", task.getException());
            }
        });
    }

    private static void getRestaurantDocument(
            DocumentReference restaurantIdRef,
            Reservation reservation,
            List<Reservation> reservations,
            ReservationsCallback callback,
            int expectedReservationCount) {
        restaurantIdRef.get().addOnCompleteListener(restaurantTask -> {
            if (restaurantTask.isSuccessful()) {
                DocumentSnapshot restaurantDoc = restaurantTask.getResult();
                if (restaurantDoc.exists()) {
                    Restaurant restaurant = restaurantDoc.toObject(Restaurant.class);
                    reservation.setRestaurant(restaurant);
                    Log.d(TAG, "Restaurant added to reservation: " + reservation.getRestaurant().toString());
                } else {
                    Log.e(TAG, "Restaurant document not found for ID: " + restaurantIdRef.getId());
                }
            } else {
                Log.e(TAG, "Error getting restaurant document: ", restaurantTask.getException());
            }

            // Add the reservation to the list
            reservations.add(reservation);

            // Check if all reservations are processed
            if (reservations.size() == expectedReservationCount) {
                Log.d(TAG, "RETURNING LIST OF RESERVATIONS");
                callback.onReservationsLoaded(reservations);
            }
        });
    }



}
