package com.thellog.laravelapiandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                isFirstTime();
            }
        }, 1500);

    }

    private void isFirstTime() {
        //for checking if the app is running for the very first time
        //ve need to save a value to shared preferense
        SharedPreferences preferences = getApplication().getSharedPreferences("onBorad" , Context.MODE_PRIVATE);
        boolean isFirsTime = preferences.getBoolean("isFirstTime", true);
        //default value true
        if(isFirsTime){
            //if its true then its first time and ve vill change is false
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstTime" , false);
            editor.apply();
            //start Onboard activity
            startActivity(new Intent( MainActivity.this , OnboardActivity.class));
            finish();
        }
        else {
            //start Auth Activity
            startActivity(new Intent( MainActivity.this , AuthActivity.class));
            finish();
        }
    }















}