package com.earn.presenter;

import android.content.Context;
import android.util.Log;

import com.earn.Contract.InviteContract;
import com.earn.model.Model;
import com.earn.model.StudentData;
import com.earn.util.Api;
import com.earn.util.Constants;
import com.earn.util.JsonUtil;
import com.google.gson.Gson;
import com.alibaba.fastjson.JSON;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by asus on 2017/8/8.
 */

public class InvitePresenter implements InviteContract.Presenter {
    private Context context;
    private InviteContract.View view;
    private Model model;
    private Gson gson;
    public InvitePresenter(Context context,InviteContract.View view){
        this.context = context;
        this.view = view;
    }

    @Override
    public void getStudents() {
        model = Model.getInstance();
        HashMap<String,String> map = new HashMap<>();
        map.put("token", Constants.token);
        model.post(Api.getStudent,new okhttp3.Callback(){

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                Log.d("打印",responseBody);
                ArrayList<StudentData.Students> studentses = new ArrayList<>();
                try {
                    int status = JsonUtil.getStatus(responseBody);
                    if(status == 0){
                        //StudentData studentData = gson.fromJson(responseBody,StudentData.class);
                        StudentData studentData = JSON.parseObject(responseBody,StudentData.class);
                        for (StudentData.Students re : studentData.getStudents()) {
                            // list.add(re);
                            studentses.add(re);
                        }
                        view.showSuccess(studentses);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //News question = gson.fromJson(responseBody, News.class)
            }
        },map);
    }
}
