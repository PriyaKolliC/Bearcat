package com.example.bearcat;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button issue = findViewById(R.id.issue);
        issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("button_clicks","Report Issue Clicked");

                Intent i=new Intent(HomePage.this, ScanQrCode.class);
                startActivity(i);

                Log.i("button_clicks","1");

            }
        });


        Button contribute = findViewById(R.id.contribute);
        final String link = "https://www.google.co.in/";
        contribute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW).setData(Uri.parse(link));
                startActivity(i);
            }
        });

    }
}
