package com.example.bearcat;

import com.example.bearcat.server.Api;
import com.example.bearcat.server.AsyncResponse;

import android.content.Intent;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.app.AlertDialog;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.toUpperCase;

public class Feedback extends AppCompatActivity {

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

                if(roomType.equals("class"))
                    Api.postDataClass(building,roomNumber,roomType,flags);//,AsyncResponse<Boolean>);
                else
                    Api.postDataWash(building,roomNumber,roomType,flags);//,AsyncResponse<Boolean>);

                ad.setTitle("Feedback Sent");

                ad.setMessage(value_inSeries);
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
