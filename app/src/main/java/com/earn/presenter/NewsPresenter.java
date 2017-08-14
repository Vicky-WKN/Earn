package com.earn.presenter;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.earn.Contract.NewsContract;
import com.earn.model.Model;
import com.earn.model.NewResult;
import com.earn.util.Api;
import com.earn.util.JsonUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by asus on 2017/7/20.
 */

public class NewsPresenter implements NewsContract.Presenter {


    private  NewsContract.View view;
    private Context context;
    private ArrayList<NewResult> list = new ArrayList<>();
    private Gson gson = new Gson();
    private Model model;
    private Model m;


    public NewsPresenter(Context context, NewsContract.View view){
        this.context = context;
        this.view = view;

    }
    @Override
    public void start(int i){
        loadPosts(i);
    }

    @Override
    public void loadPosts(int i) {
        view.showLoading();
        /**
         * model表演时间
         */

        model = new Model();
        HashMap<String,String> map = new HashMap<>();
        map.put("category",i+"");
        model.post(Api.getNew, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                Log.d("获取新闻", "onResponse: "+responseBody);
                ArrayList<NewResult.News> news = new ArrayList<>();
                try {
                    int status = JsonUtil.getStatus(responseBody);
                    if(status == 0){
                        //StudentData studentData = gson.fromJson(responseBody,StudentData.class);
                        NewResult newResult = JSON.parseObject(responseBody,NewResult.class);
                        for (NewResult.News re : newResult.getNews()) {
                            // list.add(re);
                            //news.add(re);
                            Log.d("新闻标题", "onResponse: "+re.getTitle());
                            //Log.d("图片链接", "onResponse: "+re.getImgLinks());
                            String num[] = new String[11];
                            if(re.getImgLinks().length()>5){
                                num = re.getImgLinks().split("<-separate->");
                            }
                            re.setImgLinks(num[1]);
                            news.add(re);
                            Log.d("文章", "onResponse: "+re.getArtcle());
                        }
                        view.showResult(news);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (org.json.JSONException e) {
                    e.printStackTrace();
                }
            }
        },map);
        //view.stopLoading();
    }
    @Override
    public void refresh(int i) {
        loadPosts(i);
    }

    /**
     * 点进去阅读呀
     * @param position
     */
    @Override
    public void starReading(int position) {

    }
}