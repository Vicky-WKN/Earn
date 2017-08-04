package com.earn.model;

import com.earn.util.OkHttp3;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 方法模型层
 * Created by D&LL on 2017/3/13.
 */

public class Model {
    private static Model instance = new Model();//单例

    public static Model getInstance() {
        return instance;
    }


    public OkHttpClient client = OkHttp3.getClient();



    /**
     * post请求
     * @param address
     * @param callback
     * @param map
     */

    public void post(String address, okhttp3.Callback callback, Map<String,String> map)
    {
        OkHttpClient client = new OkHttpClient();
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


    /**
     * post请求
     * @param address
     * @param callback
     */

    public void get(String address, okhttp3.Callback callback)
    {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        FormBody body = builder.build();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }
}