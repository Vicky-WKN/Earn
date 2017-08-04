package com.earn.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.earn.Contract.RegisterContract;
import com.earn.R;
import com.earn.presenter.RegisterPresenter;
import com.earn.util.Constants;
import com.earn.util.ToastUtil;

import java.util.HashMap;

/**
 * Created by asus on 2017/7/30.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener,RegisterContract.View {
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayout backButton ;
    private EditText id;
    private Button getPhoneButton;
    private EditText code;
    private EditText pwd1;
    private EditText pwd2;
    private Button getPwdButton;
    private LinearLayout getPhoneLayout;
    private LinearLayout getCodeLayout;
    private LinearLayout getPwdLayout;
    private RegisterContract.Presenter presenter;


    @Override
    public void onCreate(Bundle savedInstanceStated){
        super.onCreate(savedInstanceStated);
        //去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.layout_register);

        presenter = new RegisterPresenter(this,this);

        initView();

    }

    private void initView() {
        backButton = (LinearLayout) findViewById(R.id.register_back_button);
        backButton.setOnClickListener(this);
        getPhoneButton = (Button) findViewById(R.id.register_get_phone_button);
        getPhoneButton.setOnClickListener(this);
        getPwdButton = (Button) findViewById(R.id.register_set_pwd_button);
        getPwdButton.setOnClickListener(this);

        id =(EditText) findViewById(R.id.register_phone_edit_text);
        code =(EditText) findViewById(R.id.register_code_edit_text);
        pwd1 = (EditText) findViewById(R.id.register_pwd_edit_text);
        pwd2 = (EditText) findViewById(R.id.register_pwd_edit_text2);

        getPhoneLayout = (LinearLayout) findViewById(R.id.layout_get_phone);
        getCodeLayout = (LinearLayout) findViewById(R.id.layout_get_code);
        getPwdLayout = (LinearLayout) findViewById(R.id.layout_set_pwd);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.login_swi);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.register_back_button:
                finish();
                break;
            case R.id.register_get_phone_button:

                if(id.getText().toString().length()<11){
                    new ToastUtil(this,000);
                }else{
                    swipeRefreshLayout.setRefreshing(true);
                    HashMap<String,String> map = new HashMap<>();
                    map.put("id",id.getText().toString());
                    presenter.setPhone(map);
                }
                break;

            case R.id.register_set_pwd_button:

                String p1 = pwd1.getText().toString();
                String p2 = pwd2.getText().toString();
                if(p1.length()<6){
                    new ToastUtil(this,001);
                }else{
                if(!p1.equals(p2)){
                    showError(Constants.DIF);
                }else{
                    if(code.getText().toString().length()<6){
                        new ToastUtil(this,002);
                    }else {
                        swipeRefreshLayout.setRefreshing(true);
                        HashMap<String, String> map1 = new HashMap<>();
                        map1.put("id", id.getText().toString());
                        map1.put("code", code.getText().toString());
                        map1.put("pwd", p1);
                        presenter.setPassWord(map1);
                        }
                    }
                }
                break;
        }
    }


    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void showError(final int i) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
                new ToastUtil(RegisterActivity.this,i);
            }
        });
    }

    @Override
    public void setPhone() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
                getPhoneLayout.setVisibility(View.GONE);
                getCodeLayout.setVisibility(View.VISIBLE);
                getPwdLayout.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void setPassWord() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Constants.LOGINFAG = 1;
                swipeRefreshLayout.setRefreshing(false);
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                intent.putExtra("id",id.getText().toString());
                intent.putExtra("pwd",pwd1.getText().toString());
                startActivity(intent);
               finish();
            }
        });
    }
}
