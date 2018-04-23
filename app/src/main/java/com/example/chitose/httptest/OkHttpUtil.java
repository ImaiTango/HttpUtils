package com.example.chitose.httptest;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtil {
    public static String sendOkHttpRequest(String address){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        try {
            Response response = client.newCall(request).execute();
            String data = response.body().string();
            return data;
        } catch (IOException e) {
            return e.toString();
        }
    }
}
