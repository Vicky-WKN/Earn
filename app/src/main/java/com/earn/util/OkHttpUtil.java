package com.earn.util;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by asus on 2017/4/8.
 */

public class OkHttpUtil {
    private static OkHttpUtil okHttpUtil;
    static OkHttpClient client;
    public static synchronized OkHttpUtil getOkHttpUtil(){
        if(okHttpUtil == null)
        {
            okHttpUtil = new OkHttpUtil();
            client = new OkHttpClient();
        }
        return okHttpUtil;
    }

    public  void post(String address, okhttp3.Callback callback, Map<String,String> map)
    {
        FormBody.Builder builder = new FormBody.Builder();
        if (map!=null)
        {
            for (Map.Entry<String,String> entry:map.entrySet())
            {
                builder.add(entry.getKey(),entry.getValue());
            }
        }
        FormBody body = builder.build();
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void get(String stress,okhttp3.Callback callback){
        Request request = new Request.Builder()
                .get()
                .url(stress)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
