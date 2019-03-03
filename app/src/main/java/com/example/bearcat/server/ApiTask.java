package com.example.bearcat.server;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiTask extends AsyncTask<Void, Void, Boolean> {
    private final OkHttpClient client;
    private final AsyncResponse delegate;
    private final Request request;

    public ApiTask(Request request, AsyncResponse<Boolean> delegate) {
        this.delegate = delegate;
        this.request = request;
        client = new OkHttpClient();
    }

    @Override
    public Boolean doInBackground(Void... params) {


        try {
            Response response = client.newCall(request).execute();
            Log.d("response code", Integer.valueOf(response.code()).toString());
            return response.code() == 200;

        } catch (IOException e) {
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean bool) {
        delegate.processFinish(bool);
    }
}
