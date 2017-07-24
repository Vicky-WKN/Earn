package com.earn.util;

import okhttp3.OkHttpClient;

/**
 * Created by asus on 2017/7/24.
 */

public class OkHttp3 {
    private static OkHttpClient client = new OkHttpClient();

    public static OkHttpClient getClient() {
        return client;
    }
}
