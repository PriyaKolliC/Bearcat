package com.example.bearcat;

import android.content.Intent;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.app.AlertDialog;

public class Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Intent intent = getIntent();
        String qr_string = intent.getExtras().getString("qrcodelabel");

        String[] split_labels = qr_string.split("-");
        final String building = split_labels[2];
        final String roomnumber = split_labels[1];
        final String roomtype = split_labels[0];

        if(roomtype.equals("class"))
            setContentView(R.layout.activity_feedback);
        else
            setContentView(R.layout.activity_feedback__washroom);

        TextView Title = (TextView)findViewById(R.id.Title);
        Title.setText(building + " " + roomnumber + " " + roomtype);

        Button sub = findViewById(R.id.submit_button);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog ad = new AlertDialog.Builder(Feedback.this).create();
                ad.setTitle("Feedback Received");
                ad.setMessage("Feedback Received for " + building + " " + roomnumber );
                ad.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                ad.show();
            }
        });
    }
}
