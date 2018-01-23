package com.ljt.glides2;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ${JT.L} on 2018/1/23.
 */

public class MyOkhttp {

    private OkHttpClient okHttpClient;

    public static String load(String urls){
         OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newBuilder().connectTimeout(10000, TimeUnit.MILLISECONDS);
        Request request = new Request.Builder().url(urls).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if(response.isSuccessful()){
                return response.body().string();
            }else{
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
