package com.neztech.serah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.neztech.serah.activity.MainMenuActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startOtherActivity();
    }

    public void startOtherActivity() {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}