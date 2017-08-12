package com.earn.presenter;

import android.content.Context;
import android.os.Handler;

import com.earn.Contract.WithDrawContract;
import com.earn.model.Model;
import com.earn.util.Api;
import com.earn.util.Constants;
import com.earn.util.JsonUtil;
import com.earn.view.WithdrawDialog;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by asus on 2017/8/6.
 */

public class WithDrawPresenter implements WithDrawContract.Presenter {
    private Context context;
    private WithDrawContract.View view;
    private Model model;
    private Handler handler;
    public WithDrawPresenter(Context context, WithDrawContract.View view, Handler mHandler){
        this.context = context;
        this.view = view;
        this.handler = mHandler;
    }

    /**
     * 提现操作
     * @param way
     * @param money
     * @param pwd
     */
    @Override
    public void withDraw(int way, String money,String pwd) {
        HashMap<String,String> map = new HashMap<>();
        map.put("token", Constants.token);
        map.put("pwd",pwd);
        map.put("money",money);
        if(way == WithdrawDialog.ALIPAY_WAY){
        map.put("way","alipay");
        }else{
            map.put("way","alipay");
        }
        model = Model.getInstance();
        model.post(Api.withDrawUrl,new okhttp3.Callback(){

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
                        //view.showError(code);
                        handler.sendEmptyMessage(code);
                    }else {
                        handler.sendEmptyMessage(0);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },map);
    }
}
