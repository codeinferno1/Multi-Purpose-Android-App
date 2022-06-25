package com.example.sushant_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class PaymentResult extends AppCompatActivity {
    TextView name,upi,amt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_result);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.prnotification));
        }
        name = findViewById(R.id.name);
        upi = findViewById(R.id.upi);
        amt = findViewById(R.id.paymentAmount);

        Intent i = getIntent();
        String s_name = i.getStringExtra("name");
        String s_upi = i.getStringExtra("upi");
        int s_amt = i.getIntExtra("amt",0);

        name.setText(s_name);
        upi.setText(s_upi);
        amt.setText("₹ "+s_amt+" paid");
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        // Not calling **super**, disables back button in current screen.
    }

    public void done(View view) {
        Intent i = new Intent(PaymentResult.this,FakePayment.class);
        startActivity(i);
    }
}