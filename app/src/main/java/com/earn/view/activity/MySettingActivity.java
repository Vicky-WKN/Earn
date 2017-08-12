package com.earn.view.activity;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.earn.Contract.SettingContract;
import com.earn.R;
import com.earn.model.UpdateInfo;
import com.earn.presenter.SettingPresenter;
import com.earn.util.Constants;
import com.earn.util.ToastUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by asus on 2017/7/29.
 */

public class MySettingActivity extends AppCompatActivity implements View.OnClickListener,SettingContract.View {
    private ListView listView;
    private LinearLayout mainLayout;
    private LinearLayout layout1;
    private LinearLayout layout2;
    private LinearLayout layout3;
    private LinearLayout layout4;
    private ImageView backButton;
    private EditText code;//验证码
    private Button getCodeButton;//获取验证码
    private EditText pwd1;
    private EditText pwd2;
    private Button updataPWDButton;
    private EditText name;
    private Button updataNameButton;
    private Button sugestionButton;
    private Button outButton;
    private SettingPresenter presenter;
    private Toolbar toolbar;
    private Dialog dialog;
    //下载进度
    private ProgressDialog progressDialog;

    //当前页面级数
    private int flag;
    private String[] titles = null;//={"修改密码","修改名称","意见反馈","关于我们","检测更新"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_setting);
        //presenter = new WDSettingPresenter(this,this);
        presenter = new SettingPresenter(this,this);
        //new ToastUtil(SettingActivity.this, Constants.id);
        initView();
        toolbar = (Toolbar) findViewById(R.id.setting_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//隐藏默认标题,貌似toolbar.setTitle("")有同样的效果
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return;
                }
            }
        }
    }

    private void initView() {
        mainLayout = (LinearLayout) findViewById(R.id.setting_main);
        layout1 = (LinearLayout) findViewById(R.id.setting1);
        layout2 = (LinearLayout) findViewById(R.id.setting2);
        layout3 = (LinearLayout) findViewById(R.id.setting3);
        layout4 = (LinearLayout) findViewById(R.id.setting4);
        listView = (ListView) findViewById(R.id.setting_list);
        backButton = (ImageView) findViewById(R.id.setting_back);
        backButton.setOnClickListener(this);

        //修改密码
        code = (EditText) findViewById(R.id.setting_code);
        getCodeButton = (Button) findViewById(R.id.setting_getcode_button);
        getCodeButton.setOnClickListener(this);
        updataPWDButton = (Button) findViewById(R.id.setting_updata_pwd);
        updataPWDButton.setOnClickListener(this);
        pwd1 = (EditText) findViewById(R.id.setting_pwd1);
        pwd2 = (EditText) findViewById(R.id.setting_pwd2);

        //修改名称
        name = (EditText) findViewById(R.id.setting_name);
        updataNameButton = (Button) findViewById(R.id.setting_name_ok_button);
        updataNameButton.setOnClickListener(this);
        //反馈
        sugestionButton = (Button) findViewById(R.id.setting_sugestion_button);
        sugestionButton.setOnClickListener(this);
        //退出
        outButton = (Button) findViewById(R.id.setting_out);
        outButton.setOnClickListener(this);

        flag = 1;
        titles = new String[]{"修改密码", "修改名称", "意见反馈", "关于我们", "检测更新"};
        initListView();

    }

    /**
     * 初始化listView
     */
    private void initListView() {
        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
        for (int i = 0;i<titles.length;i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("ItemTitle", titles[i]);
            mylist.add(map);
        }
        //生成适配器，数组===》ListItem
        SimpleAdapter mSchedule = new SimpleAdapter(this, //没什么解释
                mylist,//数据来源
                R.layout.list_item,//ListItem的XML实现

                //动态数组与ListItem对应的子项
                new String[] {"ItemTitle"},

                //ListItem的XML文件里面的两个TextView ID
                new int[] {R.id.itemTile});
        //添加并且显示
        listView.setAdapter(mSchedule);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        mainLayout.setVisibility(View.GONE);
                        layout1.setVisibility(View.VISIBLE);
                        flag = 2;
                        break;
                    case 1:
                        mainLayout.setVisibility(View.GONE);
                        layout2.setVisibility(View.VISIBLE);
                        flag = 2;
                        break;
                    case 2:
                        mainLayout.setVisibility(View.GONE);
                        layout3.setVisibility(View.VISIBLE);
                        flag = 2;
                        break;
                    case 3:
                        mainLayout.setVisibility(View.GONE);
                        layout4.setVisibility(View.VISIBLE);
                        flag = 2;
                        break;
                    case 4:
                        //new ToastUtil(SettingActivity.this,"已是最新版本");
                        //new ToastUtil(SettingActivity.this,"正在检查版本更新");
                        presenter.updateAPK();
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_back://返回
                if(flag == 1){
                    finish();
                }else{
                    mainLayout.setVisibility(View.VISIBLE);
                    layout1.setVisibility(View.GONE);
                    layout2.setVisibility(View.GONE);
                    layout3.setVisibility(View.GONE);
                    layout4.setVisibility(View.GONE);
                    flag = 1;
                }
                break;
            case R.id.setting_getcode_button://获取验证码
                presenter.getCode();
                break;
            case R.id.setting_updata_pwd:
                if(code.getText().toString().length()<6){
                    new ToastUtil(this,"验证码为6位");
                }else {
                    if(pwd1.getText().toString().equals(pwd2.getText().toString())&&pwd1.getText().toString().length()>=6)
                    {
                        presenter.updataPWD(code.getText().toString(),pwd1.getText().toString());
                    }
                }
                break;
            case R.id.setting_name_ok_button:
                if(name.getText().toString().length()<1){
                    new ToastUtil(this,"请设置名字");
                }else{
                    presenter.updataName(name.getText().toString());
                }
                break;
            case R.id.setting_out:
                //presenter.out();
                Intent intent = new Intent(MySettingActivity.this,LoginActivity.class);
                Constants.FROMLOGIN = 1;
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
            case R.id.setting_sugestion_button:
                new ToastUtil(this,"非常感谢您的意见");
                mainLayout.setVisibility(View.VISIBLE);
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.GONE);
                layout3.setVisibility(View.GONE);
                layout4.setVisibility(View.GONE);
                flag = 1;
                break;
            default:
                new ToastUtil(this,"还有这种操作？");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (flag == 2) {
            mainLayout.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.GONE);
            layout2.setVisibility(View.GONE);
            layout3.setVisibility(View.GONE);
            layout4.setVisibility(View.GONE);
            flag = 1;
            //do something...
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }




    @Override
    public void showSuccess() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(flag == 2){
                    mainLayout.setVisibility(View.VISIBLE);
                    layout1.setVisibility(View.GONE);
                    layout2.setVisibility(View.GONE);
                    layout3.setVisibility(View.GONE);
                    layout4.setVisibility(View.GONE);
                }else {
                    finish();
                }
            }
        });

    }

    @Override
    public void showError(final int i) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ToastUtil(MySettingActivity.this,i);
            }
        });

    }

    @Override
    public void showUpdateDialog(final UpdateInfo updateInfo) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(MySettingActivity.this);
                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setTitle("请升级APP至版本" + updateInfo.getVersion());
                builder.setMessage(updateInfo.getDescription());
                builder.setCancelable(false);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Environment.getExternalStorageState().equals(
                                Environment.MEDIA_MOUNTED)) {
                            downFile(updateInfo.getUrl());
                        } else {
                            new ToastUtil(MySettingActivity.this, "SD卡不可用，请插入SD卡");
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create().show();
            }
        });

    }

    @Override
    public void downFile(final String url) {

//        dialog = new Dialog(this);
//        dialog.setTitle("正在下载");
//        dialog.show();
        progressDialog = new ProgressDialog(MySettingActivity.this);    //进度条，在下载的时候实时更新进度，提高用户友好度
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("正在下载");
        progressDialog.setMessage("请稍候...");
        progressDialog.setProgress(0);
        progressDialog.show();
        presenter.downFile(url);
        Log.d("SettingActivity", "downFile: ");

    }

    /**
     * 进度条
     * @param i
     */
    @Override
    public void downLoading(final int i) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.setProgress(i);
            }
        });
    }

    /**
     * 下载成功
     */

    @Override
    public void downSuccess() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //dialog.dismiss();
                //dialog.cancel();

                if (progressDialog != null && progressDialog.isShowing())
                {
                    progressDialog.dismiss();
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MySettingActivity.this);
                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setTitle("下载完成");
                builder.setMessage("是否安装");
                builder.setCancelable(false);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
//                        intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "Earn.apk")), "application/vnd.android.package-archive");
//                        startActivity(intent);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //aandroid N的权限问题
                            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            Uri contentUri = FileProvider.getUriForFile(MySettingActivity.this, "com.earn.fileprovider", new File(Environment.getExternalStorageDirectory(), "Earn.apk"));
                            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
                        } else {
                            intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "Earn.apk")), "application/vnd.android.package-archive");
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        }
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create().show();

            }
        });
    }

    /**
     * 下载失败
     */
    @Override
    public void downFial() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.cancel();
                new ToastUtil(MySettingActivity.this,006);
            }
        });
    }

    @Override
    public void setMax(final long total) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.setMax((int) total);
            }
        });
    }


}
