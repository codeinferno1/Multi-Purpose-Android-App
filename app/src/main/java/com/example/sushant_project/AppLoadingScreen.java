package com.example.sushant_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Random;

public class AppLoadingScreen extends AppCompatActivity {
    LottieAnimationView loading;
//    String[] s = new String[]{"loading","coding","cycle","square","codingblue"};
    int[] s = new int[]{R.raw.loading,R.raw.coding,R.raw.cycle,R.raw.square,R.raw.codingblue};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_loading_screen);
        loading = findViewById(R.id.animationView);

        Random rand = new Random();
        int n = rand.nextInt(4);
        int no = s[n];
        loading.setAnimation(no);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        (new Handler()).postDelayed(this::startMA, 1500);
//        (new Handler()).postDelayed(this::startMA, 6500);
    }
    public void startMA(){
        Intent i = new Intent(AppLoadingScreen.this,MainActivity.class);
        startActivity(i);
    }

}