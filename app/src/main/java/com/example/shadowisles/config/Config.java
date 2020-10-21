package com.example.shadowisles.config;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.shadowisles.util.ShadowIslesClient;

public class Config {

    private SharedPreferences sharedPreferences;

    public static Config getInstance(Context context) {
        return new Config(context);
    }

    private Config(Context context){
        sharedPreferences = context.getSharedPreferences("Config", Context.MODE_PRIVATE);
    }

    public void setIPAddress(String ipAddress){
        ShadowIslesClient.updateIP(ipAddress);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ipAddress", ipAddress);
        editor.apply();
    }

    public String getIPAddress(){
        return sharedPreferences.getString("ipAddress", null);
    }
}
