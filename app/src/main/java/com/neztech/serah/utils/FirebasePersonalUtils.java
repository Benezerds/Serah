package com.neztech.serah.utils;

import static android.content.ContentValues.TAG;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.neztech.serah.activity.MainMenuActivity;

public class FirebasePersonalUtils {
    private FirebaseAuth mAuth;
    private Activity activity; // Add this line

    // Modify the constructor to accept an Activity instance
    public FirebasePersonalUtils(Activity activity) {
        this.activity = activity;
    }

    public Task<Boolean> signInRequest(String email, String password){
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

    public boolean checkCurrentUser(FirebaseAuth mAuth){
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            return true;
        }
        return false;
    }
}

