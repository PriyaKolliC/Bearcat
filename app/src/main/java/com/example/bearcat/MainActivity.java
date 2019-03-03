package com.example.bearcat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.bearcat.server.Api;
import com.example.bearcat.server.AsyncResponse;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        Log.d("Main", "trying");
//        Api api = new Api();
//        try {
//            Log.d("Main", "trying");
//            api.authenticate("username", "password", new AsyncResponse<Boolean>() {
//                @Override
//                public Boolean processFinish(Boolean aBoolean) {
//                    Log.d("AsyncResponse", aBoolean.toString());
//                    return null;
//                }
//            });
//
//            api.postData(
//                    "building",
//                    "111",
//                    "library",
//                    false,
//                    true,
//                    false,
//                    false,
//                    true,
//                    new AsyncResponse<Boolean>() {
//                @Override
//                public Boolean processFinish(Boolean aBoolean) {
//                    Log.d("PostDataResponse", aBoolean.toString());
//                    return null;
//                }
//            });
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//        Log.i("Something", "**********************8888");

        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        /*GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        */
        Button login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("button_clicks","Report Issue Clicked");
                Intent i=new Intent(MainActivity.this, HomePage.class);
                startActivity(i);
                Log.i("button_clicks","1");

            }
        });
    }


}
