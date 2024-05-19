package com.neztech.serah.utils;

import android.util.Log;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;
import com.neztech.serah.interfaceutil.OnUserFetched;
import com.neztech.serah.model.User;

public class UserUtils {

    private static final String TAG = "UserUtils";
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static FirebaseAuth auth = FirebaseAuth.getInstance();
    private static User currentUserDetails;

    public static void fetchCurrentUserDetails(OnUserFetched callback) {
        String uid = auth.getCurrentUser().getUid();

        db.collection("User").document(uid).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d(TAG, "Taking Document Snapshot on uid : " + auth.getCurrentUser().getUid());
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    User currentUserDetails = document.toObject(User.class);
                    // Now you can use the user object
                    // For example, you can print the user's first name
                    Log.d(TAG, "User's first name: " + currentUserDetails.getFull_name());
                    callback.onFetched(currentUserDetails);
                } else {
                    Log.d(TAG, "No such document");
                    callback.onError(new Exception("No such document"));
                }
            } else {
                Log.e(TAG, "get failed with ", task.getException());
                callback.onError(task.getException());
            }
        });
    }

}
