package com.example.sushant_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

public class PaymentSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);
        MediaPlayer mp =MediaPlayer.create(PaymentSuccess.this,R.raw.gpaysound);
        mp.start();
        (new Handler()).postDelayed(this::startNewAc, 3000);
    }
    public void startNewAc(){
        Intent intent = getIntent();
        String s_name = intent.getStringExtra("name");
        String s_upi = intent.getStringExtra("upi");
        int s_amt = intent.getIntExtra("amt",0);
        Intent i = new Intent(PaymentSuccess.this,PaymentResult.class);
        i.putExtra("name",s_name);
        i.putExtra("upi",s_upi);
        i.putExtra("amt",s_amt);
        startActivity(i);
    }
}