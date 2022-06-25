package com.example.sushant_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FakePayment extends AppCompatActivity {
    EditText name,upi,amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_payment);
        name = findViewById(R.id.editText1);
        upi = findViewById(R.id.editText2);
        amount = findViewById(R.id.editText3);
    }

    public void submit(View view) {
        String s_name = name.getText().toString();
        String s_upi = upi.getText().toString();
        int s_amt=0;
        try {
            s_amt = Integer.parseInt(amount.getText().toString());
        }catch (Exception e){
            if (!s_name.equals("") && !s_upi.equals("") && !amount.getText().toString().equals("")) {
                Toast.makeText(FakePayment.this, "Please Enter Valid Amount", Toast.LENGTH_SHORT).show();
            }
            amount.setText("");
        }
        if (s_name.equals("") || s_upi.equals("") || amount.getText().toString().equals("")){
            Toast.makeText(FakePayment.this, "Please Fill Up All The Fields and Click Submit !", Toast.LENGTH_LONG).show();
        }else {
            Intent i = new Intent(FakePayment.this,PaymentSuccess.class);
            i.putExtra("name",s_name);
            i.putExtra("upi",s_upi);
            i.putExtra("amt",s_amt);
            startActivity(i);
        }
    }

    public void back(View view) {
        Intent i = new Intent(FakePayment.this,MainActivity.class);
        startActivity(i);
    }
}