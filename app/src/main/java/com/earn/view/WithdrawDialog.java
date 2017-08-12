package com.earn.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.earn.Contract.WithDrawContract;
import com.earn.R;
import com.earn.presenter.WithDrawPresenter;
import com.earn.util.Constants;
import com.earn.util.ToastUtil;
import com.earn.view.activity.WdSettingActivity;

/**
 * Created by asus on 2017/8/5.
 */

public class WithdrawDialog extends Dialog implements View.OnClickListener ,WithDrawContract.View{
    private Context context;
    private LinearLayout withdrawLayout1;
    private LinearLayout withdrawLayout2;
    private RadioGroup radioGroup;
    private RadioButton alipayButton;
    private RadioButton wechatButton;
    private Button bt;
    private Button cancelButton;
    private Button settingButton;
    private Button okButton2;
    private Button cancelButton2;
    private TextView alipayID;
    private TextView wecahtID;

    private EditText inputMoney;
    private EditText inputPwd;

    public static int ALIPAY_WAY = 0;
    public static int WECHAT_WAY = 1;

    private int FLAG ;//判断是以什么方式提现

    private WithDrawContract.Presenter presenter;

    public WithdrawDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置不显示对话框标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置对话框显示哪个布局文件
        setContentView(R.layout.withdraw_dialog);

        presenter = new WithDrawPresenter(context,this,mHandler);
        initView();

    }

    private void initView() {
        //隐藏与显示layout
        withdrawLayout1 = (LinearLayout) findViewById(R.id.with_way_select);
        withdrawLayout2 = (LinearLayout) findViewById(R.id.withd_input_pwd);

        //对话框也可以通过资源id找到布局文件中的组件，从而设置点击侦听
        bt = (Button) findViewById(R.id.dialog_ok);
        bt.setOnClickListener(this);
        cancelButton =(Button) findViewById(R.id.dialog_cancel);
        cancelButton.setOnClickListener(this);
        settingButton = (Button) findViewById(R.id.dialog_setting);
        settingButton.setOnClickListener(this);
        okButton2 = (Button) findViewById(R.id.dialog_ok2);
        okButton2.setOnClickListener(this);
        cancelButton2 =(Button) findViewById(R.id.dialog_cancel2);
        cancelButton2.setOnClickListener(this);

        alipayButton = (RadioButton) findViewById(R.id.alipay_button);
        wechatButton = (RadioButton) findViewById(R.id.wechat_button);

        //设置金钱与密码
        inputMoney = (EditText) findViewById(R.id.wd_input_money);
        inputPwd = (EditText) findViewById(R.id.wd_input_pwd);

        //显示账号
        alipayID = (TextView) findViewById(R.id.wd_alipay_id);
        wecahtID = (TextView) findViewById(R.id.wd_wechat_id);

        if(Constants.alipay!=null){
            alipayID.setText(Constants.alipay);
        }
        if(Constants.wechat!=null){
            wecahtID.setText(Constants.wechat);
        }

        //根据ID找到RadioGroup实例
        radioGroup = (RadioGroup)this.findViewById(R.id.radio_group);
        //绑定一个匿名监听器
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                // TODO Auto-generated method stub
                //获取变更后的选中项的ID
                int radioButtonId = arg0.getCheckedRadioButtonId();
                //判断
                if(radioButtonId == alipayButton.getId()){
                    //new ToastUtil(context,"支付宝");
                    FLAG = ALIPAY_WAY;
                }else{
                    //new ToastUtil(context,"微信");
                   FLAG = WECHAT_WAY;
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_ok:
                if(FLAG == ALIPAY_WAY){
                    if(Constants.alipay == null){
                        new ToastUtil(context,"支付宝账号还没设置");
                    }else{
                        withdrawLayout1.setVisibility(View.GONE);
                        withdrawLayout2.setVisibility(View.VISIBLE);

                    }
                }else{
                    if(Constants.wechat == null){
                        new ToastUtil(context,"微信名还没设置");
                    }else{
                        withdrawLayout1.setVisibility(View.GONE);
                        withdrawLayout2.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case R.id.dialog_cancel:
                //new ToastUtil(context,"取消");
                dismiss();//对话框消失
                break;

            case R.id.dialog_setting:
                Intent intent = new Intent(context, WdSettingActivity.class);
                //intent.putExtra("handle",mHandler);
                context.startActivity(intent);
                //context.startActivityForResult(intent,1);
                dismiss();
                break;

            case R.id.dialog_ok2:
                if(inputMoney.getText().toString().length()<1){
                    new ToastUtil(context,"请设置提现金额");
                }else{
                    if(inputPwd.getText().toString().length()<6){
                        new ToastUtil(context,"请输入不少于6位的密码");
                    }else{
                        presenter.withDraw(FLAG,inputMoney.getText().toString(),inputPwd.getText().toString());
                    }
                }
                break;
            case R.id.dialog_cancel2:
                inputMoney.setText("");
                inputPwd.setText("");
                withdrawLayout1.setVisibility(View.VISIBLE);
                withdrawLayout2.setVisibility(View.GONE);
                break;

            default:
                dismiss();
        }
    }

    @Override
    public void showError(int i) {
        if (i == 308) {
            new ToastUtil(context, "失败，一天只能提现一次");
        } else {
            new ToastUtil(context, i);

        }
    }
    /**
     * 异步处理
     */
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            if (what == 0) {
                //在主线程中需要执行的操作，一般是UI操作
                showSuccess();
            }else{
                showError(what);
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        if(Constants.alipay!=null){
        if(Constants.alipay.length()>2){
            alipayID.setText(Constants.alipay);
        }else{
            alipayID.setText("(未设置支付宝)");
        }}
        if(Constants.wechat!=null){
            if(Constants.wechat.length()>2){
                wecahtID.setText(Constants.wechat);
            }else {
                wecahtID.setText("(未设置微信名)");
            }
        }
    }


    @Override
    public void showSuccess() {
        new ToastUtil(context,"提交提现请求发出，我们将工作日3天内为您处理");
        inputMoney.setText("");
        inputPwd.setText("");
        withdrawLayout1.setVisibility(View.VISIBLE);
        withdrawLayout2.setVisibility(View.GONE);
        dismiss();
    }
}
