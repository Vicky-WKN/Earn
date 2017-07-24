package com.earn.Contract;

import com.earn.model.News;

import java.util.ArrayList;

/**
 * Created by asus on 2017/7/20.
 */

public interface NewsContract {
    interface View {

        void showError();

        void showLoading();

        void stopLoading();

        void showResult(ArrayList<News.result> list);
    }

    interface Presenter{

        void start();

        void loadPosts();

        void refresh();

        void starReading(int position);
    }
}
