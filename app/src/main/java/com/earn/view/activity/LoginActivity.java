package com.earn.view.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.earn.Contract.LoginContract;
import com.earn.R;
import com.earn.presenter.LoginPresenter;
import com.earn.util.Constants;
import com.earn.util.SharedPreferencesUtil;
import com.earn.util.ToastUtil;
import com.earn.view.MyVideoView;

import java.util.HashMap;

/**
 * Created by asus on 2017/7/29.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,LoginContract.View {
    private MyVideoView myVideoView;
    private Button loginButton;
    private EditText id;
    private EditText pwd;
    private TextView forgetButton;
    private TextView registerButton;
    private LoginContract.Presenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceStated){
        super.onCreate(savedInstanceStated);
        //去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);


        setContentView(R.layout.layout_login);
        presenter = new LoginPresenter(this,this);
        initView();

    }

    private void initView() {
        loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);
        forgetButton=(TextView) findViewById(R.id.login_forget_button);
        forgetButton.setOnClickListener(this);
        registerButton =(TextView) findViewById(R.id.login_register_button);
        registerButton.setOnClickListener(this);

        id = (EditText) findViewById(R.id.login_id);
        pwd = (EditText) findViewById(R.id.login_pwd);

        if(Constants.LOGINFAG == 0)
        {
            //读取上一次登录的账号密码
            id.setText(SharedPreferencesUtil.getData(this,"id"));
            pwd.setText(SharedPreferencesUtil.getData(this,"pwd"));
            Constants.id = SharedPreferencesUtil.getData(this,"id");
        }else{
            //如果是从注册或者修改密码界面中过来的就读取Extra内容
            Intent intent = getIntent();
            id.setText(intent.getStringExtra("id"));
            pwd.setText(intent.getStringExtra("pwd"));
            Constants.LOGINFAG = 0;
        }

        //补充，如果从退出登录页面来的话
        if(Constants.FROMLOGIN == 1){
            Constants.FROMLOGIN = 0;
            HashMap<String,String> map = new HashMap<>();
            map.put("id",id.getText().toString());
            map.put("pwd",pwd.getText().toString());
            presenter.changeToken(map);
            Constants.logined = false;
            new ToastUtil(this,"退出登录");
        }
        playVideo();
    }

    /**
     * 视频背景播放设置
     */
    private void playVideo() {
        //加载视频资源控件
        myVideoView = (MyVideoView) findViewById(R.id.login_myvideo);
        //设置播放加载路径
        myVideoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video));
        //播放
        myVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(0f, 0f);//静音
                mp.start();
                myVideoView.start();
            }
        });
        //循环播放
        myVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.setVolume(0f, 0f);//静音
                mp.start();
                myVideoView.start();
            }
        });
    }


    /**
     * 返回重启加载
     */
    @Override
    protected void onRestart(){
        playVideo();
        super.onRestart();
    }

    @Override
    protected void onStop(){
        myVideoView.stopPlayback();//停止视频
        super.onStop();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                //Toast.makeText(this,"登录操作",Toast.LENGTH_LONG).show();
                //成功的话
                HashMap<String,String> map = new HashMap<>();
                map.put("id",id.getText().toString());
                map.put("pwd",pwd.getText().toString());
                presenter.login(map);
                break;
            case R.id.login_forget_button:
                Intent intent1 = new Intent(this,ChangeActivity.class);
                startActivity(intent1);
                break;

            case R.id.login_register_button:
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                //Toast.makeText(this,"新用户注册操作",Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(this,"还有这种操作？？？",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showError(final int i) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ToastUtil(LoginActivity.this,i);
            }
        });
    }

    @Override
    public void showSuccess() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Constants.logined =true;
                SharedPreferencesUtil.pustData(LoginActivity.this,id.getText().toString(),pwd.getText().toString(),Constants.token);
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
