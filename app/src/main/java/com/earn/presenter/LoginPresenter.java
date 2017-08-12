package com.earn.presenter;

import android.content.Context;
import android.util.Log;

import com.earn.Contract.LoginContract;
import com.earn.model.Model;
import com.earn.util.Api;
import com.earn.util.Constants;
import com.earn.util.JsonUtil;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by asus on 2017/8/4.
 */

public class LoginPresenter implements LoginContract.Presenter{


    private  LoginContract.View view;
    private Context context;
    private Model model;


    public LoginPresenter(Context context, LoginContract.View view){
        this.context = context;
        this.view = view;
    }


    @Override
    public void login(HashMap<String,String> map) {
        model = Model.getInstance();
        model.post(Api.loginUrl,new okhttp3.Callback(){

            @Override
            public void onFailure(Call call, IOException e) {
              view.showError(404);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                try {
                    int status = JsonUtil.getStatus(responseBody);
                    if(status == 1){
                        int code = JsonUtil.getErrCode(responseBody);
                        view.showError(code);
                    }else {
                        Log.d("登录成功", "onResponse: "+status);
                        Constants.token = JsonUtil.getToken(responseBody);
                        view.showSuccess();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },map);
    }

    @Override
    public void changeToken(HashMap<String,String> map) {
        model = Model.getInstance();
        model.post(Api.loginUrl,new okhttp3.Callback(){

            @Override
            public void onFailure(Call call, IOException e) {
                view.showError(404);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                try {
                    int status = JsonUtil.getStatus(responseBody);
                    if(status == 1){
                        int code = JsonUtil.getErrCode(responseBody);
                        view.showError(code);
                    }else {
                        Log.d("登录成功", "onResponse: "+status);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },map);
    }
}
