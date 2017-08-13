package com.earn.Contract;

import com.earn.model.NewResult;

import java.util.ArrayList;

/**
 * Created by asus on 2017/7/20.
 */

public interface NewsContract {
    interface View {

        void showError();

        void showLoading();

        void stopLoading();

        void showResult(ArrayList<NewResult.News> list);
    }

    interface Presenter{

        void start(int i);

        void loadPosts(int i);

        void refresh(int i);

        void starReading(int position);
    }
}
