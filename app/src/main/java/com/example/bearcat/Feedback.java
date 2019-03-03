package com.example.bearcat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Intent intent = getIntent();
        String qr_string = intent.getExtras().getString("qrcodelabel");
        TextView qrlabel = (TextView)findViewById(R.id.qrlabel);
        qrlabel.setText(qr_string);


    }
}
