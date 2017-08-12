package com.earn.presenter;

import android.content.Context;

import com.earn.Contract.SettingWDContract;
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
 * Created by asus on 2017/8/6.
 */

public class WDSettingPresenter implements SettingWDContract.Presenter {
    private SettingWDContract.View view;
    private Context context;
    private Model model;
    public WDSettingPresenter(Context context,SettingWDContract.View view){
        this.context = context;
        this.view = view;
    }


    @Override
    public void setAlipay(final String alipay, String realName) {
        model = Model.getInstance();
        HashMap<String,String> map = new HashMap<>();
        map.put("token", Constants.token);
        map.put("alipayId",alipay);
        map.put("realName",realName);
        model.post(Api.updataAlipay,new okhttp3.Callback(){
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                try {
                    int status = JsonUtil.getStatus(responseBody);
                    if(status==0){
                        Constants.alipay = alipay;
                        view.showSuccess();
                    }else{
                        int code = JsonUtil.getErrCode(responseBody);
                        view.showError(code);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },map);
    }

    @Override
    public void setWechat(final String wechat) {
        model = Model.getInstance();
        HashMap<String,String> map = new HashMap<>();
        map.put("token", Constants.token);
        map.put("wechat",wechat);
        model.post(Api.udataWechat,new okhttp3.Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                    view.showError(404);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                try {
                    int status = JsonUtil.getStatus(responseBody);
                    if(status==0){
                        Constants.wechat  = wechat;
                        view.showSuccess();
                    }else{
                        int code = JsonUtil.getErrCode(responseBody);
                        view.showError(code);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },map);
    }
}
