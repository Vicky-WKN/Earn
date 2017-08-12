package com.earn.Contract;

/**
 * Created by asus on 2017/8/6.
 */

public interface SettingWDContract {
    interface View{
        void showError(int i);
        void showSuccess();
    }

    interface Presenter{

        void setAlipay(String alipay, String realName);

        void setWechat(String wechat);
    }
}
