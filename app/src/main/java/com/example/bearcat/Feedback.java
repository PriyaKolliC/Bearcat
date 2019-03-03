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
                AlertDialog ad = new AlertDialog.Builder(Feedback.this).create();
                String value_inSeries = "" + building + "-" + roomNumber + "-" + roomType;

                ArrayList<Boolean> flags = new ArrayList<Boolean>();

                final int childCount = ll.getChildCount();
                for(int i=0;i<childCount;i++){
                    View each_child = ll.getChildAt(i);
                    if(each_child instanceof CheckBox){
                        if(((CheckBox) each_child).isChecked()) {
                            value_inSeries += "-" + "true";
                            flags.add(true);
                        }
                        else {
                            value_inSeries += "-" + "false";
                            flags.add(false);
                        }
                    }

                    if(each_child instanceof EditText)
                        value_inSeries += "-" + ((EditText) each_child).getText();
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
        Intent intent = new Intent(Feedback.this, HomePage.class);
        startActivity(intent);
    }

    private void requestSuccessfull() {
        Toast.makeText(getApplicationContext(), "Your response has been successfully noted.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Feedback.this, HomePage.class);
        startActivity(intent);
    }
}
