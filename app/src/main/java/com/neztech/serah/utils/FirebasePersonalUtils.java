package com.neztech.serah.utils;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;

import java.util.HashMap;
import java.util.Map;

public class FirebasePersonalUtils {
    private FirebaseAuth mAuth;
    private Activity activity; // Add this line

    // Modify the constructor to accept an Activity instance
    public FirebasePersonalUtils(Activity activity) {
        this.activity = activity;
    }

    public Task<Boolean> createAccountWithEmail(String email, String password, String fullName, String phoneNumber){
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Create a new TaskCompletionSource
        TaskCompletionSource<Boolean> taskCompletionSource = new TaskCompletionSource<>();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            // Get an instance of Firestore
                            FirebaseFirestore db = FirebaseFirestore.getInstance();

                            // Create a new user with a first and last name
                            Map<String, Object> userMap = new HashMap<>();
                            userMap.put("email", email);
                            userMap.put("full_Name", fullName);
                            userMap.put("phoneNumber", phoneNumber);
                            userMap.put("created_time", Timestamp.now());
                            userMap.put("location", "");
                            userMap.put("username", fullName);


                            // Add a new document with the user ID
                            db.collection("User").document(user.getUid())
                                    .set(userMap)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d(TAG, "DocumentSnapshot successfully written!");
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w(TAG, "Error writing document", e);
                                        }
                                    });

                            // Set the result of the TaskCompletionSource to true
                            taskCompletionSource.setResult(true);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(activity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                            // Set the result of the TaskCompletionSource to false
                            taskCompletionSource.setResult(false);
                        }
                    }
                });

        // Return the Task from the TaskCompletionSource
        return taskCompletionSource.getTask();
    }


}


