package com.example.piumsudhara.application;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.widget.ProgressBar;

public class Splash extends AppCompatActivity {

    public static int SPLASH_TIME_OUT=5000;
    private int progressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent HomeIntent = new Intent(Splash.this,Login.class);
                startActivity(HomeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);

    }
}

