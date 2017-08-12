package com.earn.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by asus on 2017/4/9.
 */

public class ToastUtil {
    private Context context;
    private int code;
    private String msg = "错误";
    public ToastUtil(Context context, int code)
    {
        this.context = context;
        this.code = code;
        initMsg();
        showToast();
    }

    public ToastUtil(Context context, String msg)
    {
        this.context = context;
        this.msg = msg;
        showToast();
    }


    private void initMsg() {
        switch (code)
        {
            case Constants.NOFOUND:
                msg = "连接服务器失败";
                break;
            case Constants.DIF:
                msg = "两次密码输入不同";
                break;
            case 300:
                msg = "获取验证码失败";
                break;
            case 301:
                msg = "手机号已被注册";
                break;
            case 302:
                msg = "验证码错误或过期";
                break;
            case 303:
                msg = "手机号不存在";
                break;
            case 304:
                msg = "密码错误";
                break;
            case 305:
                msg = "新旧密码相同";
                break;
            case 306:
                msg = "id不存在";
                break;
            case 308:
                msg = "数据库插入异常";
                break;
            case 000:
                msg = "手机号格式不对";
                break;
            case 001:
                msg = "密码不可少于6位";
                break;
            case 002:
                msg = "验证码为6位数字";
                break;
            case 003:
                msg = "请先登录";
                break;
            case 004:
                msg = "请先登录";
                break;
            case 005:
                msg="当前为最新版本";
                break;
            case 006:
                msg = "更新失败";
                break;
            default:
                break;
        }
    }

    private void showToast() {
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }


}
