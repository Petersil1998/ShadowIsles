package com.example.shadowisles.util;

import android.os.StrictMode;
import android.util.Log;

import com.example.shadowisles.entities.Queue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Response;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ThreshAPIClient extends HTTPClient {

    private static String basePath = "http://www.threshstats.ml/thresh_api/";

    public static ThreshAPIClient getInstance() {
        return new ThreshAPIClient();
    }

    public Queue getQueue(int id){
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            String url = basePath+"queues/"+id;

            try {
                Response response = get(url);
                if(response.code() == 200){
                    Gson gson = new GsonBuilder().create();
                    return gson.fromJson(response.body().string(), Queue.class);
                } else {
                    Log.e("THRESH API CLIENT", "getQueue: "+response.code()+" "+response.body().string());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Queue> getQueues(){
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            String url = basePath+"queues";

            try {
                Response response = get(url);
                String body = response.body().string();
                if(response.code() == 200){
                    Gson gson = new GsonBuilder().create();
                    Type listType = new TypeToken<ArrayList<Queue>>(){}.getType();
                    return gson.fromJson(body, listType);
                } else {
                    Log.e("THRESH API CLIENT", "getQueues: "+response.code()+" "+response.body().string());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected int getReadTimeout() {
        return 30;
    }
}
