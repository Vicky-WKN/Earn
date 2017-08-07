package com.earn.util;

/**
 * Created by asus on 2017/7/29.
 */

public class Constants {
    public static boolean logined = false;//是否登录
    public static String name = "姓名";//用户名称
    public static String id = null;
    public static String token = null;//token
    public static String alipay = null;
    public static String wechat = null;
    public static double money = 0;
    public static double studentMoney = 0;

    public final static int DIF = 405;
    public final static int NOFOUND = 404;
    //立fag判断是否从注册或者修改密码跳转过来的
    public static int LOGINFAG = 0;
    //立fag判断是否从退出登录跳转过来的
    public static int FROMLOGIN = 0;
    //判断是否从设置账号中过来的
    public static int SETTING = 0;
}
