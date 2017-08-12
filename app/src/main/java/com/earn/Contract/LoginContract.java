package com.earn.Contract;

import java.util.HashMap;

/**
 * Created by asus on 2017/8/4.
 */

public interface LoginContract {
    interface View{
        //显示失败
        void showError(int i);
        //登录成功
        void showSuccess();
    }

    interface Presenter{
        //登录操作
        void login(HashMap<String,String> map);
        //change
        void changeToken(HashMap<String,String> map);
    }
}
