package com.example.sushant_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.*;
import com.google.zxing.Result;

public class CodeScanner extends AppCompatActivity {
    private com.budiyev.android.codescanner.CodeScanner mCodeScanner;
    TextView resultText;
    Button btn;
    boolean pay_qr = false;
    String upi_id = "";
    String upi_name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_scanner);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},101);
        }
        resultText = findViewById(R.id.resultLink);
        btn = findViewById(R.id.gotobtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!pay_qr){
                    String link = resultText.getText().toString();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(link));
                    startActivity(i);
                }else {
                    Intent i = new Intent(CodeScanner.this,FakePayment.class);
                    i.putExtra("upi_id",upi_id);
                    i.putExtra("upi_name",upi_name);
                    startActivity(i);
                }
            }
        });

        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new com.budiyev.android.codescanner.CodeScanner(this,scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (result.getText().contains("http")){
//                            Toast.makeText(CodeScanner.this, result.getText(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(CodeScanner.this, "Link Detected", Toast.LENGTH_SHORT).show();
                            resultText.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
                            resultText.setText(result.getText());
                            btn.setVisibility(View.VISIBLE);
                            onResume();
                        }else if (result.getText().contains("upi://")){
                            Toast.makeText(CodeScanner.this, "Payment Code Detected", Toast.LENGTH_SHORT).show();
                            resultText.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
                            resultText.setText("UPI Payment QR Code Detected");
                            btn.setText("Pay to This Code");
                            btn.setVisibility(View.VISIBLE);
                            pay_qr = true;
                            String scanned_text = result.getText();
                            int index1 = scanned_text.indexOf("=");
                            int i1 = scanned_text.indexOf("=");
                            int i2 = scanned_text.indexOf("&");
                            upi_id = scanned_text.substring(i1+1,i2);
                            if (scanned_text.contains("&pn")) {
                                int j1 = scanned_text.indexOf("&pn");
                                int j2 = 0;
                                if (scanned_text.contains("&mc")){
                                    j2 = scanned_text.indexOf("&mc");
                                }

                                if(j2!=0){
                                    upi_name = scanned_text.substring(j1+4,j2);
                                }else{
                                    upi_name = scanned_text.substring(j1+4);
                                }

//                                if (scanned_text.contains("%20")){
                                    upi_name = upi_name.replace("%20"," ");
                                    upi_name = scanned_text.substring(j1+1,j2);
//                                }

                            }
                            onResume();
                        } else {
                            resultText.setText(result.getText());
                        }
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }
}