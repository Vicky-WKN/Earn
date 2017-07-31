package com.earn.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.earn.R;

/**
 * Created by asus on 2017/7/30.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayout backButton ;
    private Button getPhoneButton;
    private Button getCodeButton;
    private Button getPwdButton;
    private LinearLayout getPhoneLayout;
    private LinearLayout getCodeLayout;
    private LinearLayout getPwdLayout;

    @Override
    public void onCreate(Bundle savedInstanceStated){
        super.onCreate(savedInstanceStated);
        //去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.layout_register);

        initView();

    }

    private void initView() {
        backButton = (LinearLayout) findViewById(R.id.register_back_button);
        backButton.setOnClickListener(this);
        getPhoneButton = (Button) findViewById(R.id.register_get_phone_button);
        getPhoneButton.setOnClickListener(this);
        getCodeButton = (Button) findViewById(R.id.register_get_code_button);
        getCodeButton.setOnClickListener(this);
        getPwdButton = (Button) findViewById(R.id.register_set_pwd_button);
        getPwdButton.setOnClickListener(this);

        getPhoneLayout = (LinearLayout) findViewById(R.id.layout_get_phone);
        getCodeLayout = (LinearLayout) findViewById(R.id.layout_get_code);
        getPwdLayout = (LinearLayout) findViewById(R.id.layout_set_pwd);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.login_swi);
    }


    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    getPhoneLayout.setVisibility(View.GONE);
                    getCodeLayout.setVisibility(View.VISIBLE);
                    swipeRefreshLayout.setRefreshing(false);
                    break;
                case 1:
                    getCodeLayout.setVisibility(View.GONE);
                    getPwdLayout.setVisibility(View.VISIBLE);
                    swipeRefreshLayout.setRefreshing(false);
                    break;
                case 2:
                    finish();
                    break;
                default:
                    handler.sendEmptyMessageDelayed(msg.what-1, 1000);
                    break;
            }
        };
    };

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.register_back_button:
                finish();
                break;
            case R.id.register_get_phone_button:
                swipeRefreshLayout.setRefreshing(true);
                handler.sendEmptyMessageDelayed(0,3000);
                break;
            case R.id.register_get_code_button:
                swipeRefreshLayout.setRefreshing(true);
                handler.sendEmptyMessageDelayed(1,3000);
                break;
            case R.id.register_set_pwd_button:
                swipeRefreshLayout.setRefreshing(true);
                handler.sendEmptyMessageDelayed(2,3000);
                break;
        }
    }



}
