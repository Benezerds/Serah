package com.neztech.serah.utils;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;
import com.neztech.serah.interfaceutil.OnUserFetched;
import com.neztech.serah.model.User;

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



    public static Task<User> fetchUsersDataByReference (DocumentReference userRef){
            // Fetch the user data from Firestore
            return userRef.get().continueWith(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    if (doc.exists()) {
                        // DocumentSnapshot contains the user data
                        User user = doc.toObject(User.class);
                        return user;
                    } else {
                        Log.d(TAG, "Document does not exist");
                        return null;
                    }
                } else {
                    // Handle the error (e.g., network issues)
                    Log.e(TAG, "Error fetching user data", task.getException());
                    return null;
                }
            });
        }


    }
