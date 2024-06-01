package com.neztech.serah.utils;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.neztech.serah.model.Restaurant;
import com.neztech.serah.model.Review;
import com.neztech.serah.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantUtils {
    public static List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Restaurant")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Restaurant restaurant = document.toObject(Restaurant.class);
                            restaurants.add(restaurant);
                        }
                    } else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                });

        return restaurants;
    }

    public interface RestaurantCallback {
        void onRestaurantsFetched(List<Restaurant> restaurants);
    }

    public static void getFeaturedRestaurants(RestaurantCallback callback) {
        List<Restaurant> restaurants = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("FeaturedRestaurant")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot featuredDoc : task.getResult()) {
                            // Get the RestaurantId (document reference)
                            DocumentReference restaurantRef = featuredDoc.getDocumentReference("RestaurantId");

                            // Fetch the actual restaurant document
                            restaurantRef.get().addOnCompleteListener(restaurantTask -> {
                                if (restaurantTask.isSuccessful()) {
                                    DocumentSnapshot restaurantSnapshot = restaurantTask.getResult();
                                    if (restaurantSnapshot.exists()) {
                                        Restaurant restaurant = restaurantSnapshot.toObject(Restaurant.class);
                                        restaurants.add(restaurant);
                                    } else {
                                        Log.w(TAG, "Restaurant document does not exist.");
                                    }
                                } else {
                                    Log.w(TAG, "Error getting restaurant document.", restaurantTask.getException());
                                }

                                // Check if all restaurants have been fetched
                                if (restaurants.size() == task.getResult().size()) {
                                    // Call the callback with the fetched restaurants
                                    callback.onRestaurantsFetched(restaurants);
                                }
                            });
                        }
                    } else {
                        Log.w(TAG, "Error getting featured restaurants.", task.getException());
                    }
                });
    }


    public static Task<List<Review>> fetchRestaurantReviews(String restaurantId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference reviewsRef = db.collection("Review");

        // Query reviews for the specified restaurant
        return reviewsRef.whereEqualTo("RestaurantId", restaurantId).get()
                .continueWith(task -> {
                    if (task.isSuccessful()) {
                        // Convert QuerySnapshot to a list of Review objects
                        List<Review> reviews = new ArrayList<>();
                        for (DocumentSnapshot doc : task.getResult()) {

                            // Fetch user data for this review
                            DocumentReference createdByRef = doc.getDocumentReference("uid");

                            User user = UserUtils.fetchUsersDataByReference(createdByRef).getResult(); // Assuming you have a fetchUserDataByUid function
                            Review review = doc.toObject(Review.class);
                            review.setUser(user);
                            reviews.add(review);
                        }
                        return reviews;
                    } else {
                        // Handle the error (e.g., no reviews found)
                        return null;
                    }
                });
    }

    public static void createReservationDocument(String reservationId, Restaurant restaurant, String partySize, String reservationDate, String reservationStatus, User user) {
        // Reference to your Firestore collection for reservations
        CollectionReference reservationsRef = FirebaseFirestore.getInstance().collection("Reservation");

        // Create a new reservation document
        DocumentReference newReservationRef = reservationsRef.document(reservationId);

        // Create a map to store reservation data
        Map<String, Object> reservationData = new HashMap<>();
        reservationData.put("ReservationId", reservationId);
        reservationData.put("RestaurantId", restaurant.getDocumentReference()); // Assuming you have a method to get the restaurant reference
        reservationData.put("partySize", partySize);
        reservationData.put("reservationDate", reservationDate);
        reservationData.put("reservationStatus", reservationStatus);
        reservationData.put("uid", user.getDocumentReference()); // Assuming you have a method to get the user reference

        // Set the fields for the reservation document
        newReservationRef.set(reservationData)
                .addOnSuccessListener(aVoid -> {
                    // Reservation created successfully
                    // Handle any additional logic here
                })
                .addOnFailureListener(e -> {
                    // Handle reservation creation failure
                });
    }

    public static void createReviewDocument(Review review) {
        // Reference to your Firestore collection for reviews
        CollectionReference reviewsRef = FirebaseFirestore.getInstance().collection("Review");

        // Create a new review document
        DocumentReference newReviewRef = reviewsRef.document(review.getReviewId());

        // Create a map to store review data
        Map<String, Object> reviewData = new HashMap<>();
        reviewData.put("ReviewId", review.getReviewId());
        reviewData.put("RestaurantId", review.getRestaurant().getDocumentReference());
        reviewData.put("rating", review.getRating());
        reviewData.put("date", review.getDate());
        reviewData.put("uid", review.getUser().getDocumentReference());
        reviewData.put("comment", review.getComment());

        // Set the fields for the review document
        newReviewRef.set(reviewData)
                .addOnSuccessListener(aVoid -> {
                    // Review created successfully
                    // Handle any additional logic here
                })
                .addOnFailureListener(e -> {
                    // Handle review creation failure
                });
    }


}
