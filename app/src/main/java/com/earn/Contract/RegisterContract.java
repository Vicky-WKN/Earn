package com.earn.Contract;

import java.util.HashMap;

/**
 * Created by asus on 2017/8/4.
 */

public interface RegisterContract {
    interface View{
        void showLoading();

        void showError(int i);

        void setPhone();


        void setPassWord();
    }

    interface Presenter{
        void setPhone(HashMap<String,String> map);


        void setPassWord(HashMap<String,String> map);
    }
}
