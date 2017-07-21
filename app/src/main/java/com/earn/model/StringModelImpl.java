package com.earn.model;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by asus on 2017/3/20.
 */

public class StringModelImpl {
    private Context context;

    public StringModelImpl(Context context)
    {
        this.context = context;
    }

    //加载内容
    public void load(String url,final OnStringListener listener){
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {//啥意思,反正会在应用引用的那里回调的
            @Override
            public void onResponse(String s) {
                listener.onSuccess(s);
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error);
            }
        });

        //加入请求队列
        request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleySingleton.getVolleySingleton(context).addToRequestQueue(request);

    }
}
