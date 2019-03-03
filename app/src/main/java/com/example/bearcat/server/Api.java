package com.example.bearcat.server;

import android.net.Uri;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class Api {
    private String ENDPOINT_URI = "destress.appspot.com";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    private Uri.Builder getUriBuilder() {
        return new Uri.Builder()
                .scheme("https")
                .authority(ENDPOINT_URI);
    }

    public void authenticate(String username, String password, AsyncResponse<Boolean> asyncResponse) throws JSONException {
        String url = getUriBuilder()
                    .appendPath("authenticate")
                    .build()
                    .toString();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", password);

        RequestBody requestBody = RequestBody.create(JSON, jsonObject.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        ApiTask postTask = new ApiTask(request, asyncResponse);
        postTask.execute();
    }

    public void postData(String building,
                         String roomNumber,
                         String roomType,
                         Boolean powerSocketBroken,
                         Boolean switchLightsOff,
                         Boolean tooHot,
                         Boolean tooCold,
                         Boolean dirtyRoom,
                         AsyncResponse<Boolean> asyncResponse) throws JSONException {
        String url = getUriBuilder()
                    .appendPath("postData")
                    .build()
                    .toString();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("building", building);
        jsonObject.put("roomNumber", roomNumber);
        jsonObject.put("roomType", roomType);
        jsonObject.put("powerSocketBroken", powerSocketBroken);
        jsonObject.put("switchLightsOff", switchLightsOff);
        jsonObject.put("tooHot", tooHot);
        jsonObject.put("tooCold", tooCold);
        jsonObject.put("dirtyRoom", dirtyRoom);
        RequestBody requestBody = RequestBody.create(JSON, jsonObject.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        ApiTask postTask = new ApiTask(request, asyncResponse);
        postTask.execute();
    }
}