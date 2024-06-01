package com.neztech.serah.utils;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;
import com.neztech.serah.interfaceutil.OnUserFetched;
import com.neztech.serah.model.User;

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


    public static User fetchUserDataByReference(DocumentReference userRef) {
        CountDownLatch latch = new CountDownLatch(1);
        final User[] user = {null};

        userRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    user[0] = document.toObject(User.class);
                } else {
                    System.out.println("No such document!");
                }
            } else {
                System.err.println("Error fetching document: " + task.getException());
            }
            latch.countDown();
        });

        try {
            latch.await();  // Wait for the asynchronous task to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return user[0];
    }



}
