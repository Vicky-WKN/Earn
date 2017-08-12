package com.earn.Contract;

import com.earn.model.UpdateInfo;

/**
 * Created by asus on 2017/8/7.
 */

public interface SettingContract {
    interface View{
        //void showError();
        void showSuccess();

        void showError(int i);

        void showUpdateDialog(UpdateInfo updateInfo);

        void downFile(final String url);

        void downLoading(int i);

        void downSuccess();

        void downFial();

        void setMax(long total);
    }

    interface Presenter{

    }
}
