package com.earn.model;

import com.android.volley.VolleyError;

/**
 * Created by asus on 2017/3/20.
 */

public interface OnStringListener {
    /**
     * 请求成功时回调
     */
    void onSuccess(String result);

    /**
     * 请求时失败时回调
     */
    void onError(VolleyError error);
}
