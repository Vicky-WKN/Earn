package com.earn.view;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.earn.R;
import com.earn.util.Constants;

/**
 * Created by asus on 2017/7/29.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private MyVideoView myVideoView;
    private Button loginButton;
    private TextView forgetButton;
    private TextView registerButton;
    @Override
    public void onCreate(Bundle savedInstanceStated){
        super.onCreate(savedInstanceStated);
        //去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.layout_login);

        initView();

    }

    private void initView() {
        loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);
        forgetButton=(TextView) findViewById(R.id.login_forget_button);
        forgetButton.setOnClickListener(this);
        registerButton =(TextView) findViewById(R.id.login_register_button);
        registerButton.setOnClickListener(this);


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
                Constants.logined =true;
                finish();
                break;
            case R.id.login_forget_button:
                Toast.makeText(this,"忘记密码操作",Toast.LENGTH_LONG).show();
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
}
