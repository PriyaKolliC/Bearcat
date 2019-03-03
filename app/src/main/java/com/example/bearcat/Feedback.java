package com.example.bearcat;

import com.example.bearcat.server.Api;
import com.example.bearcat.server.AsyncResponse;

import android.content.Intent;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.app.AlertDialog;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.toUpperCase;

public class Feedback extends AppCompatActivity {

    private static final Api api = new Api();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        Intent intent = getIntent();
        String qr_string = intent.getExtras().getString("qrcodelabel");

        String[] split_labels = qr_string.split("-");

        Log.d("SplitLabels", Integer.valueOf(split_labels.length).toString());
        if (split_labels.length != 3) {
            Toast.makeText(this, "Invalid QR Code.", Toast.LENGTH_LONG).show();
            goToHomePage();
            return;
        }
        final String building = split_labels[2];
        final String roomNumber = split_labels[1];
        final String roomType = split_labels[0];

        if(roomType.equals("class"))
            setContentView(R.layout.activity_feedback);
        else
            setContentView(R.layout.activity_feedback__washroom);

        TextView Title = (TextView)findViewById(R.id.Title);
        Title.setText(toUpperCase(building + " " + roomNumber));

        TextView Type = (TextView) findViewById(R.id.Type);
            if(roomType.equals("class"))
                Type.setText(toUpperCase("classroom"));
            else
                Type.setText(toUpperCase("washroom"));

            final LinearLayout ll = (LinearLayout)findViewById(R.id.Room);

        Button sub = findViewById(R.id.submit_button);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Feedback", "onClick");

                ArrayList<Boolean> flags = new ArrayList<>();

                final int childCount = ll.getChildCount();
                for(int i=0;i<childCount;i++){
                    View each_child = ll.getChildAt(i);
                    if(each_child instanceof CheckBox){
                        flags.add(((CheckBox) each_child).isChecked());
                    }
                }

                try {
                    if (roomType.equals("class"))
                        api.postDataClass(building, roomNumber, roomType, flags, new AsyncResponse<Boolean>() {
                            @Override
                            public void processFinish(Boolean aBoolean) {
                                if (aBoolean) {
                                    requestSuccessfull();
                                } else requestUnsuccessfull();
                            }
                        });
                    else
                        api.postDataWash(building, roomNumber, roomType, flags, new AsyncResponse<Boolean>() {
                            @Override
                            public void processFinish(Boolean aBoolean) {
                                if (aBoolean) {
                                    requestSuccessfull();
                                } else requestUnsuccessfull();
                            }
                        });
                } catch (JSONException e) {
                    requestUnsuccessfull();
                }
            }
        });

    }

    private void requestUnsuccessfull() {
        Toast.makeText(getApplicationContext(), "Error making request to the server.", Toast.LENGTH_LONG).show();
        goToHomePage();
    }

    private void requestSuccessfull() {
        Toast.makeText(getApplicationContext(), "Your response has been successfully noted.", Toast.LENGTH_LONG).show();
        goToHomePage();
    }

    private void goToHomePage() {
        Intent intent = new Intent(Feedback.this, HomePage.class);
        startActivity(intent);
    }
}
