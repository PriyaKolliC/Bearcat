package com.example.bearcat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.Result;

import java.util.Calendar;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanQrCode extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr_code);

        ActivityCompat.requestPermissions(ScanQrCode.this,
                new String[]{Manifest.permission.CAMERA},
                1);

        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.content_frame);
        mScannerView = new ZXingScannerView(this);
        contentFrame.addView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(final Result rawResult) {

        // adding result to history
        String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        String qr_string = rawResult.getText();

        Log.d("handleResult", rawResult.getText());
        Intent i = new Intent(ScanQrCode.this,Feedback.class);
        i.putExtra("qrcodelabel",qr_string);
        startActivity(i);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(ScanQrCode.this, "Permission denied to camera", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}
