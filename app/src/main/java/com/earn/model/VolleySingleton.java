package com.earn.model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by asus on 2017/3/20.
 */

//Volley的单例模式
public class VolleySingleton {
    private static VolleySingleton volleySingleton;
    private RequestQueue requestQueue;

    private VolleySingleton(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    //锁住这块代码块
    public static synchronized VolleySingleton getVolleySingleton(Context context){
        if(volleySingleton == null)
        {
            volleySingleton = new VolleySingleton(context);
        }
        return volleySingleton;
    }

    //请求队列
    public RequestQueue getRequestQueue(){
        return this.requestQueue;
    }
    public <T> void addToRequestQueue(Request<T> req)
    {
        getRequestQueue().add(req);
    }
}
