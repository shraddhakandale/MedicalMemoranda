package com.sk.miniprojecttrial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class Scanqrcode extends AppCompatActivity {
    CodeScanner codeScanner;
    CodeScannerView scannView;
    TextView resultdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanqrcode);

        scannView = findViewById(R.id.scannerview);
        codeScanner = new CodeScanner(this, scannView);
        resultdata = findViewById(R.id.resulttextView);

        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        resultdata.setText(result.getText());
                        Intent homescreen = new Intent(Scanqrcode.this, Home.class);
                        homescreen.putExtra("adhaarnum", resultdata.getText().toString());
                        startActivity(homescreen);
                    }
                });
            }
        });
    }


    @Override
    protected void onResume() {

        super.onResume();
        codeScanner.startPreview();
    }
}