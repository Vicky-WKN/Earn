package com.earn.presenter;

import android.content.Context;
import android.util.Log;

import com.earn.Contract.MainContract;
import com.earn.model.Model;
import com.earn.util.Api;
import com.earn.util.Constants;
import com.earn.util.JsonUtil;
import com.earn.util.SharedPreferencesUtil;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by asus on 2017/8/4.
 */

public class MainPresenter implements MainContract.Presenter {
    private  MainContract.View view;
    private Context context;
    private Model model;


    public MainPresenter(Context context, MainContract.View view){
        this.context = context;
        this.view = view;
    }

    @Override
    public void start() {
        Constants.token = SharedPreferencesUtil.getData(context,"token");
        Log.d("token","是"+Constants.token);
        if(Constants.token !=null){
            HashMap<String,String> map = new HashMap<>();
            map.put("token",Constants.token);
            Log.d("获取信息中", "run: ");
            model = Model.getInstance();
            model.post(Api.getUserData,new okhttp3.Callback(){
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d("获取信息失败", "run: ");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseBody = response.body().string();
                    try {
                        int status = JsonUtil.getData(responseBody);
                        if(status == 0){
                            Constants.logined = true;
                            Log.d("获取信息成功", "run: ");
                            //new ToastUtil(MainActivity.this,"获取信息成功");
                        }else{
                            Constants.logined = false;
                            //new ToastUtil(MainActivity.this,"获取信息失败");
                            Log.d("获取信息失败", "run: ");
                        }
                        //view.start(status);
                        Log.d("获取信息失败", "run: ");
                        Log.d("申请获取用户信息", "onResponse: "+status);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },map);
        }

    }

}
