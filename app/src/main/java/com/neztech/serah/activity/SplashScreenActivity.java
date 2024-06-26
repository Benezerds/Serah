package com.neztech.serah.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.neztech.serah.R;

public class SplashScreenActivity extends AppCompatActivity {
    ImageView splashImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //  Instantiate Image Variable
        splashImage = findViewById(R.id.image_view_splashscreen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivityForResult(intent,10);
                finish();
            }
        }, 1000);
    }
}