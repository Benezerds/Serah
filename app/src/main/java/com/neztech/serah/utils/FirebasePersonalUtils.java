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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
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
        Log.d(TAG, "Intiaiting creating account with Email");
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

                            Exception exception = task.getException();
                            if (exception instanceof FirebaseAuthWeakPasswordException) {
                                Toast.makeText(activity, "The password is too weak.", Toast.LENGTH_SHORT).show();
                            } else if (exception instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(activity, "The email address is malformed.", Toast.LENGTH_SHORT).show();
                            } else if (exception instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(activity, "An account already exists with this email address.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(activity, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                            Log.w(TAG, "createUserWithEmail:failure", exception);

                            // Set the result of the TaskCompletionSource to false
                            taskCompletionSource.setResult(false);

                        }
                    }
                });

        // Return the Task from the TaskCompletionSource
        return taskCompletionSource.getTask();
    }


    //  RESET ACCOUNT WITH EMAIL
    @NonNull
    public static Task<Void> resetPassword(String email){
        FirebaseAuth auth = FirebaseAuth.getInstance();

        return auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Email sent.");
                        }
                    }
                });
    }

    //  LOGIN WITH EMAIL
    @NonNull
    public static Task<Boolean> loginWithEmail(String email, String password){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        TaskCompletionSource<Boolean> taskCompletionSource = new TaskCompletionSource<>();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            taskCompletionSource.setResult(true);
                        } else {
                            taskCompletionSource.setResult(false);
                        }
                    }
                });

        return taskCompletionSource.getTask();
    }



    public static boolean checkCurrentUser(FirebaseAuth mAuth){
        // Check if user is signed in (non-null) and update UI accordingly.
        boolean result;

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            result = true;
        } else {
            result = false;
        }
        return result;
    }
}


