package com.sywastech.rightjobservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {

    public static final long splashTime = 2000;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    @Override
    protected void onResume(){
        super.onResume();
        init();
    }

    private void init(){
        handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){
                signIn();
            }
        }, splashTime);
    }

    private void signIn() {
        startActivity(new Intent(this, SignInActivity.class));
        finish();
    }
}