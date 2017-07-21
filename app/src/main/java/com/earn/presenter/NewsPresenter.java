package com.earn.presenter;

import android.content.Context;
import android.util.Log;

import com.earn.Contract.NewsContract;
import com.earn.model.News;
import com.earn.util.Api;
import com.earn.util.OkHttpUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by asus on 2017/7/20.
 */

public class NewsPresenter implements NewsContract.Presenter {


    private  NewsContract.View view;
    private Context context;
    private ArrayList<News.result> list = new ArrayList<>();
    private Gson gson = new Gson();

    public NewsPresenter(Context context, NewsContract.View view){
        this.context = context;
        this.view = view;
        view.setPresenter(this);

    }
    @Override
    public void start(){
        loadPosts();
    }

    @Override
    public void loadPosts() {
        view.showLoading();
        /**
         * model表演时间
         */
        view.showLoading();
        OkHttpUtil okHttpUtil = OkHttpUtil.getOkHttpUtil();
        okHttpUtil.get(Api.GUOKR_ARTICLES, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("获取results","失败",e);
                view.stopLoading();
                view.showError();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();

                News question = gson.fromJson(responseBody,News.class);
                for(News.result re : question.getResult()){
                    list.add(re);
                }
                //ArrayList<News.result> list1 = new ArrayList<>();
                view.showResult(list);
                //final String token = JsonUtil.getToken(responseBody);
                Log.d("打印json内容", responseBody);

            }
        });
        view.stopLoading();
    }

    @Override
    public void refresh() {
        loadPosts();
    }

    /**
     * 点进去阅读呀
     * @param position
     */
    @Override
    public void starReading(int position) {

    }
}
