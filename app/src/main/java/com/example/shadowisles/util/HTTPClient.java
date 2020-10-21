package com.example.shadowisles.util;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HTTPClient {

    protected Response get(String urlString) throws IOException {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(getConnectTimeout(), TimeUnit.SECONDS);
        client.setReadTimeout(getReadTimeout(), TimeUnit.SECONDS);
        Request request = new Request.Builder()
                .url(urlString)
                .get()
                .build();

        return client.newCall(request).execute();
    }

    protected Response post(String urlString, String jsonData) throws IOException {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(getConnectTimeout(), TimeUnit.SECONDS);
        client.setReadTimeout(getReadTimeout(), TimeUnit.SECONDS);
        Request request = new Request.Builder()
                .url(urlString)
                .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
                .build();

        return client.newCall(request).execute();
    }

    protected Response put(String urlString, String jsonData) throws IOException {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(getConnectTimeout(), TimeUnit.SECONDS);
        client.setReadTimeout(getReadTimeout(), TimeUnit.SECONDS);
        Request request = new Request.Builder()
                .url(urlString)
                .put(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
                .build();

        return client.newCall(request).execute();
    }

    protected Response delete(String urlString) throws IOException {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(getReadTimeout(), TimeUnit.SECONDS);
        client.setReadTimeout(getReadTimeout(), TimeUnit.SECONDS);
        Request request = new Request.Builder()
                .url(urlString)
                .delete()
                .build();

        return client.newCall(request).execute();
    }

    protected int getReadTimeout(){
        return 10;
    }

    protected int getConnectTimeout(){
        return 10;
    }
}
