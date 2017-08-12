package com.earn.presenter;

import android.content.Context;

import com.earn.Contract.RegisterContract;
import com.earn.model.Model;
import com.earn.util.Api;
import com.earn.util.JsonUtil;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by asus on 2017/8/4.
 */

public class RegisterPresenter implements RegisterContract.Presenter{

    private  RegisterContract.View view;
    private Context context;
    private Model model;


    public RegisterPresenter(Context context, RegisterContract.View view){
        this.context = context;
        this.view = view;
    }


    @Override
    public void setPhone(HashMap<String,String> map) {
        model = Model.getInstance();
        model.post(Api.getCodeUrl,new okhttp3.Callback(){

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
                        view.setPhone();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },map);

    }


    @Override
    public void setPassWord(HashMap<String,String> map) {
        model = Model.getInstance();
        model.post(Api.registerUrl,new okhttp3.Callback(){

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
                        view.setPassWord();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },map);
    }
}
