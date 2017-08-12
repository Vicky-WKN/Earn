package com.earn.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.earn.Contract.SettingWDContract;
import com.earn.R;
import com.earn.presenter.WDSettingPresenter;
import com.earn.util.Constants;
import com.earn.util.ToastUtil;

/**
 * Created by asus on 2017/7/21.
 */

public class WdSettingActivity extends AppCompatActivity implements View.OnClickListener,SettingWDContract.View{
    private ImageView back;
    private EditText alipay;
    private EditText realName;
    private EditText wechat;
    private Button ok;

    private SettingWDContract.Presenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_setting_wd);
        presenter = new WDSettingPresenter(this,this);
        initView();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.setting_back_button);
        back.setOnClickListener(this);
        alipay = (EditText) findViewById(R.id.setting_alipay_edittext);
        realName = (EditText) findViewById(R.id.setting_realname_edittext);
        wechat = (EditText) findViewById(R.id.setting_wechat_edittext);
        ok = (Button) findViewById(R.id.setting_ok_button);
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_back_button:
                finish();
                break;
            case R.id.setting_ok_button:
                Boolean flag = false;
                //修改账号操作
                if(alipay.getText().toString().length()>1&&realName.getText().toString().length()>1){
                    presenter.setAlipay(alipay.getText().toString(),realName.getText().toString());
                   flag = true;
                }
                if(wechat.getText().length()>1){
                    presenter.setWechat(wechat.getText().toString());
                    flag = true;
                }

                if(!flag){
                    new ToastUtil(this,"请填写支付宝或者微信");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void showError(final int i) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ToastUtil(WdSettingActivity.this,i);
            }
        });
    }

    @Override
    public void showSuccess() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //setResult(1);
                Constants.SETTING = 1;
                finish();
            }
        });
    }
}
