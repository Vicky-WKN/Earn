package com.earn.util;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by asus on 2017/4/8.
 */

public class JsonUtil {
    public  static int getStatus(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        int status = jsonObject.getInt("status");
        if(jsonObject.has("errcode"))
        {
            return jsonObject.getInt("errcode");
        }
        return status;
    }
    public  static int getErrCode(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        int code = jsonObject.getInt("err");
        return code;
    }



    public static String getToken(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);

        JSONObject data = jsonObject.getJSONObject("data");
        String token = data.getString("token");
        return token;

    }

    public static int getData(String response) throws JSONException{
        JSONObject jsonObject = new JSONObject(response);
        int status = jsonObject.getInt("status");
        JSONObject data = jsonObject.getJSONObject("data");
        Log.d("获取用户信息", "getData: ");
        if(data.has("myselfMoney")){
            Constants.money = data.getDouble("myselfMoney");
        }
        if(data.has("studentMolney")){
            Constants.studentMoney = data.getDouble("studentMolney");
        }
        if(data.has("wechat")){
            Constants.wechat = data.getString("wechat");
        }
        if(data.has("alipay")){
            Constants.alipay = data.getString("alipay");
            Log.d("获取支付宝信息", Constants.alipay);
        }
        //Constants.id = data.getString("id");
        Constants.name = data.getString("name");
        return status;
    }
}
