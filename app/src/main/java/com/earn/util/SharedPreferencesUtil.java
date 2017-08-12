package com.earn.util;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by asus on 2017/4/10.
 */

public class SharedPreferencesUtil {
    //数据都存放以LOGIN命名的SharePreferences中
    public static final String LOGIN = "loginDate";

    /**
     * 把账号和密码存入SharedPreferences中
     * @param context
     * @param id
     * @param password
     */
    public static void pustData(Context context,String id, String password,String token)
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(LOGIN,MODE_PRIVATE).edit();
        editor.putString("id",id);
        editor.putString("pwd",password);
        editor.putString("token",token);
        editor.apply();
    }

    /**
     * 把对应的数据从SharedPreferences取出来
     * @param context
     * @param data
     * @return
     */
    public static String getData(Context context,String data)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(LOGIN,MODE_PRIVATE);
        return sharedPreferences.getString(data,"");
    }

    /**
     * 清理SharedPreferences
     * @param context
     */
    public static void  clear(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(LOGIN,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public static void saveToken(Context context, String token) {
        SharedPreferences.Editor editor = context.getSharedPreferences(LOGIN,MODE_PRIVATE).edit();
        editor.putString("token",token);
        editor.apply();
    }
}
