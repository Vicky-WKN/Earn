package com.earn.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import com.earn.Contract.SettingContract;
import com.earn.model.Model;
import com.earn.model.UpdateInfo;
import com.earn.util.Api;
import com.earn.util.Constants;
import com.earn.util.JsonUtil;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by asus on 2017/8/7.
 */

public class SettingPresenter implements SettingContract.Presenter{
    private Context context;
    private SettingContract.View view;
    private Model model;
    private String phone = Constants.id;
    private ProgressDialog progressDialog;
    public SettingPresenter(Context context,SettingContract.View view){
        this.context = context;
        this.view = view;
    }

    public void getCode(){
       if( phone!=null)
       {
           HashMap<String,String> map = new HashMap<>();
           map.put("id",phone);
           model = Model.getInstance();
           model.post(Api.getCodeUrl, new okhttp3.Callback(){

               @Override
               public void onFailure(Call call, IOException e) {
                   view.showError(404);
               }

               @Override
               public void onResponse(Call call, Response response) throws IOException {
                   String responseBody = response.body().string();
                   int status = 0;
                   try {
                       status = JsonUtil.getStatus(responseBody);
                       if(status == 0)
                       {
                           view.showError(104);
                           //new ToastUtil(context,"接收验证码中");
                       }else {
                           int code = JsonUtil.getErrCode(responseBody);
                           view.showError(code);
                       }
                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
               }
           },map);
       }else{
           view.showError(003);
       }
    }

    public void updataPWD(String code,String pwd){
        if( phone.length()==11)
        {
            HashMap<String,String> map = new HashMap<>();
            map.put("code",code);
            map.put("pwd",pwd);
            map.put("token",Constants.token);
            model = Model.getInstance();
            model.post(Api.updataPWD, new okhttp3.Callback(){

                @Override
                public void onFailure(Call call, IOException e) {
                    view.showError(404);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseBody = response.body().string();
                    int status = 0;
                    try {
                        status = JsonUtil.getStatus(responseBody);
                        if(status == 0)
                        {
                            view.showSuccess();
                        }else {
                            int code = JsonUtil.getErrCode(responseBody);
                            view.showError(code);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            },map);
        }else{
            view.showError(003);
        }
    }
    public void updataName(final String name){
        if( phone.length()==11)
        {
            HashMap<String,String> map = new HashMap<>();
            map.put("name",name);
            map.put("token",Constants.token);
            model = Model.getInstance();
            model.post(Api.updataName, new okhttp3.Callback(){
                @Override
                public void onFailure(Call call, IOException e) {
                    view.showError(404);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseBody = response.body().string();
                    int status = 0;
                    try {
                        status = JsonUtil.getStatus(responseBody);
                        if(status == 0)
                        {
                            view.showSuccess();
                            Constants.name = name;
                            view.showSuccess();
                        }else {
                            int code = JsonUtil.getErrCode(responseBody);
                            view.showError(code);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            },map);
        }else{
            view.showError(003);
        }
    }


    public void changeGetCode(String phone) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", phone);
        model = Model.getInstance();
        model.post(Api.getCodeUrl, new okhttp3.Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                view.showError(404);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                int status = 0;
                try {
                    status = JsonUtil.getStatus(responseBody);
                    if (status == 0) {
                        view.showError(104);
                        //new ToastUtil(context,"接收验证码中");
                    } else {
                        int code = JsonUtil.getErrCode(responseBody);
                        view.showError(code);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, map);
    }

    public void updateAPK() {
        model = Model.getInstance();
        model.get(Api.checkUpadte,new okhttp3.Callback(){
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                StringBuffer sb = new StringBuffer();
                BufferedReader reader = null;
                UpdateInfo updateInfo = new UpdateInfo();
                Log.d("版本", "onResponse: "+responseBody);
                reader = new BufferedReader(new StringReader(responseBody));
                  updateInfo.setVersion(reader.readLine());
                  updateInfo.setDescription(reader.readLine());
                  updateInfo.setUrl(reader.readLine());
                  String new_version = updateInfo.getVersion();
                  Log.d("版本", "onResponse: "+updateInfo.getVersion());
                  Log.d("版本描述", "onResponse: "+updateInfo.getDescription());
                  Log.d("更新链接", "onResponse: "+updateInfo.getUrl());
                String now_version = "";
                try {
                    PackageManager packageManager = context.getPackageManager();
                    PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(),0);
                    now_version = packageInfo.versionName;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }

                if(new_version.equals(now_version)){
                    view.showError(005);
                    Log.d("版本号是", "onResponse: "+now_version);
                }else{
                    view.showUpdateDialog(updateInfo);
                }
            }
        });
    }

    public void downFile(final String url) {
        Log.d("SettingPresenter", "downFile: ");
        this.progressDialog = progressDialog;
        model = Model.getInstance();
        model.get(url,new okhttp3.Callback(){

            @Override
            public void onFailure(Call call, IOException e) {
                view.downFial();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;//输入流
                FileOutputStream fos = null;//输出流
                try {
                    is = response.body().byteStream();//获取输入流
                    long total = response.body().contentLength();//获取文件大小
                    view.setMax(total);//为progressDialog设置大小
                    if(is != null){
                        Log.d("SettingPresenter", "onResponse: 不为空");
                        File file = new File(Environment.getExternalStorageDirectory(),"Earn.apk");// 设置路径
                        fos = new FileOutputStream(file);
                        byte[] buf = new byte[1024];
                        int ch = -1;
                        int process = 0;
                        while ((ch = is.read(buf)) != -1) {
                            fos.write(buf, 0, ch);
                            process += ch;
                            view.downLoading(process);       //这里就是关键的实时更新进度了！
                        }

                    }
                    fos.flush();
                    // 下载完成
                    if(fos != null){
                        fos.close();
                    }
                    //down();
                    view.downSuccess();
                    //view.showSuccess();
                } catch (Exception e) {
                    view.downFial();
                    Log.d("SettingPresenter",e.toString());
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                    }
                }
            }

            //private void down() {
               // progressDialog.cancel();
           // }
        });
    }


    /**
     * @param url
     * @return
     * 从下载连接中解析出文件名
     */
    private String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }
}