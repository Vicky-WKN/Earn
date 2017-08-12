package com.earn.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.earn.Contract.SettingContract;
import com.earn.R;
import com.earn.model.UpdateInfo;
import com.earn.presenter.SettingPresenter;
import com.earn.util.ToastUtil;

/**
 * Created by asus on 2017/8/7.
 */

public class ChangeActivity extends AppCompatActivity implements View.OnClickListener, SettingContract.View {

    private LinearLayout backButton;
    private EditText phone;
    private EditText code;//验证码
    private Button getCodeButton;//获取验证码
    private EditText pwd1;
    private EditText pwd2;
    private Button updataPWDButton;
    private SettingPresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceStated){
        super.onCreate(savedInstanceStated);

        setContentView(R.layout.layout_change_pwd);

        initView();

        presenter = new SettingPresenter(this,this);
    }

    private void initView() {
        phone = (EditText) findViewById(R.id.change_id);
        backButton = (LinearLayout) findViewById(R.id.change_back_button);
        backButton.setOnClickListener(this);
        code = (EditText) findViewById(R.id.change_code);
        getCodeButton = (Button) findViewById(R.id.change_getcode_button);
        getCodeButton.setOnClickListener(this);
        Log.d("监听", "onClick: ");
        updataPWDButton = (Button) findViewById(R.id.change_updata_pwd);
        updataPWDButton.setOnClickListener(this);
        pwd1 = (EditText) findViewById(R.id.change_pwd1);
        pwd2 = (EditText) findViewById(R.id.change_pwd2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_back_button:
                finish();
                break;
            case R.id.change_getcode_button://获取验证码
                if(phone.getText().toString().length()<11||phone.getText().toString().equals(null)){
                    new ToastUtil(this,"手机号为11位");
                    Log.d("手机号为11位", "onClick: ");
                }else{
                    presenter.changeGetCode(phone.getText().toString());
                }
                break;
            case R.id.change_updata_pwd:
                if(code.getText().toString().length()<6){
                    new ToastUtil(this,"验证码为6位");
                }else {
                    if(pwd1.getText().toString().equals(pwd2.getText().toString())&&pwd1.getText().toString().length()>=6)
                    {
                        presenter.updataPWD(code.getText().toString(),pwd1.getText().toString());
                    }
                }
                break;
        }
    }

    @Override
    public void showSuccess() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ToastUtil(ChangeActivity.this,"修改成功");
                finish();
            }
        });
    }

    @Override
    public void showError(final int i) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ToastUtil(ChangeActivity.this,i);
            }
        });
    }

    //不用理会///////////////////////////////////////////////////////////////////
    @Override
    public void showUpdateDialog(UpdateInfo updateInfo) {

    }

    @Override
    public void downFile(String url) {

    }

    @Override
    public void downLoading(int i) {

    }

    @Override
    public void downSuccess() {

    }

    @Override
    public void downFial() {

    }

    @Override
    public void setMax(long total) {

    }


}
