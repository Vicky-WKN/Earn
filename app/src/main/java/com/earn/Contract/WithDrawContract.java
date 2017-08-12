package com.earn.Contract;

/**
 * Created by asus on 2017/8/6.
 */

public interface WithDrawContract {
    interface View{
        void showError(int i);
        void showSuccess();
    }
    interface Presenter{
        void withDraw(int way,String money,String pwd);
    }
}
