package com.example.bearcat.server;

import android.net.Uri;

import org.json.JSONException;
import org.json.JSONObject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import java.util.ArrayList;

public class Api {
    private String ENDPOINT_URI = "destress.appspot.com";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    private static Uri.Builder getUriBuilder() {
        return new Uri.Builder()
                .scheme("https")
                .authority(ENDPOINT_URI);
    }

    public static void authenticate(String username, String password, AsyncResponse<Boolean> asyncResponse) throws JSONException {
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

    public static void postDataClass(String building,
                         String roomNumber,
                         String roomType,
                         ArrayList<Boolean> flags,
                         AsyncResponse<Boolean> asyncResponse) throws JSONException {

        Boolean powerSocketBroken=false,switchLightsOff=false,tooHot=false,tooCold=false,dirtyRoom=false,Other=false;

        for(int i = 0 ;i<flags.size();i++){
            if(flags.get(i)){
                switch(i){
                    case 0: powerSocketBroken = true; break;
                    case 1: switchLightsOff = true; break;
                    case 2: tooHot = true; break;
                    case 3: tooCold = true; break;
                    case 4: dirtyRoom = true; break;
                    case 5: Other = true; break;
                }
            }
        }
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
        jsonObject.put("Other",Other);
        RequestBody requestBody = RequestBody.create(JSON, jsonObject.toString());

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        ApiTask postTask = new ApiTask(request, asyncResponse);
        postTask.execute();
    }

    public static void postDataWash(String building,
                                    String roomNumber,
                                    String roomType,
                                    ArrayList<Boolean> flags,
                                    AsyncResponse<Boolean> asyncResponse) throws JSONException {

        Boolean WaterCold=false,TrashFull=false,Unclean=false,RefillPaper=false,Slippery=false,Other=false;
        for(int i = 0 ;i<flags.size();i++){
            if(flags.get(i)){
                switch(i){
                    case 0: WaterCold = true; break;
                    case 1: TrashFull = true; break;
                    case 2: Unclean = true; break;
                    case 3: RefillPaper = true; break;
                    case 4: Slippery = true; break;
                    case 5: Other = true; break;
                }
            }
        }
        String url = getUriBuilder()
                .appendPath("postData")
                .build()
                .toString();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("building", building);
        jsonObject.put("roomNumber", roomNumber);
        jsonObject.put("roomType", roomType);
        jsonObject.put("WaterCold", WaterCold);
        jsonObject.put("TrashFull", TrashFull);
        jsonObject.put("Unclean", Unclean);
        jsonObject.put("RefillPaper", RefillPaper);
        jsonObject.put("Slippert", Slippery);
        jsonObject.put("Other",Other);
        RequestBody requestBody = RequestBody.create(JSON, jsonObject.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        ApiTask postTask = new ApiTask(request, asyncResponse);
        postTask.execute();
    }
}