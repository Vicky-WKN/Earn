package com.earn.util;

/**
 * Created by asus on 2017/7/20.
 */

public class Api
{
    // 获取果壳精选的文章列表,通过组合相应的参数成为完整的url
    // Guokr handpick articles. make complete url by combining params
    public static final String GUOKR_ARTICLES = "http://apis.guokr.com/handpick/article.json?retrieve_type=by_since&category=all&limit=25&ad=1";
    //public static final String GUOKR_ARTICLES = "http://apis.guokr.com/handpick/article.json?retrieve_type=by_since&category=all&limit=25&ad=1";

    public static final String EarnServer = "http://39.108.98.193:8080/EarnServer/";

    public static final String loginUrl= EarnServer+"LoginServlet";

    public static final String getCodeUrl = EarnServer+"GetC";

    public static final String registerUrl = EarnServer+"RegisterServlet";
    //获取用户信息
    public static final String getUserData = EarnServer+"GetDataServlet";
    //提现
    public static final String withDrawUrl = EarnServer+"WithdrawServlet";
    //更新alipay
    public static final String updataAlipay = EarnServer+"AlipayServlet";
    //更新wechat
    public static final String udataWechat = EarnServer+"WechatServlet";
    //更新密码
    public static final String updataPWD  = EarnServer+"UpdataPWDServlet";
    //更新Name
    public static final String updataName  = EarnServer+"UpdataNameServlet";
    //查询徒弟
    public static final String getStudent = EarnServer+"GetStudentServlet";
}
