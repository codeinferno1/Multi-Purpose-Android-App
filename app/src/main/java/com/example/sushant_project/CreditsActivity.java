package com.example.sushant_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class CreditsActivity extends AppCompatActivity {
    TextView tv;
    Animation animation;
    MediaPlayer mp;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.creditsscreem));
        }
        tv = findViewById(R.id.creditsText);
        animation = AnimationUtils.loadAnimation(this,R.anim.cresitsanimation);

        tv.setText("Credits 🔥\n\nLead Developer - Sushant Patil\nGraphics Designer - Sushant Patil\nAudio Manager - Sushant Patil\nVideo Editing - Sushant Patil "+
                "\n\n\nSupportive Source 📘\n\nGeneral Help - stackoverflow.com\nDependancies Provider - github.com\nProfessional Help - My Pro Logical Thinking\n\n\nHonarable Mentions 👀\n\nYouTube\nGeeksForGeeks\nAlt + Enter button in Android Studio\nMy Family\nNone of my friends😏\n\n\n");
        mp = MediaPlayer.create(this,R.raw.bbracing);
        mp.start();
        mp.setLooping(true);
        tv.setAnimation(animation);
    }

    @Override
    public void onBackPressed() {
        if (false){
            super.onBackPressed();
        }else {
            startActivity(new Intent(CreditsActivity.this,MainActivity.class));
            mp.stop();
        }
    }

    public void goBack(View view) {
        startActivity(new Intent(CreditsActivity.this,MainActivity.class));
        mp.stop();
    }
}