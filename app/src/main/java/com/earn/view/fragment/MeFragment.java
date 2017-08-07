package com.earn.view.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.earn.R;
import com.earn.util.Constants;
import com.earn.view.WithdrawDialog;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by asus on 2017/7/15.
 */

public class MeFragment extends Fragment {
    private WithdrawDialog withdrawDialog;
    static MeFragment meFragment;
    private SimpleDraweeView mSimpleDraweeView;
    View view;
    private TextView userName;
    private TextView money;
    private Button withDrawButton;
    public static MeFragment getInstance()
    {
        if (meFragment == null)
        {
            meFragment = new MeFragment();
            return meFragment;
        }else{
            return meFragment;
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view  = inflater.inflate(R.layout.fragment_me,container,false);
        initView();
        return view;
    }

    private void initView() {
        mSimpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.user_image);
        Uri imageUri = Uri.parse("http://oct7drhkl.bkt.clouddn.com/myImage.png");
        mSimpleDraweeView.setImageURI(imageUri);
        userName = (TextView) view.findViewById(R.id.user_name);
        money = (TextView) view.findViewById(R.id.number_money);
        userName.setText(Constants.name);
        double num_money =Constants.studentMoney+Constants.money;
        money.setText(String.valueOf(num_money));

        //提现按钮
        withDrawButton = (Button) view.findViewById(R.id.me_withdraw_button);

        //提现对话框
        withdrawDialog = new WithdrawDialog(getActivity());
        withDrawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                withdrawDialog.show();
            }
        });

    }


//
//    @Override
//    public void onActivityResult(int requestCode,int resultCode,Intent intent)
//    {
//        super.onActivityResult(requestCode,resultCode,intent);
//        Log.d("返回码",""+requestCode);
//        switch(resultCode){
//            case RESULT_OK:
//                Log.d("返回码","有");
//                withdrawDialog.show();
//                break;
//        }
//    }

    @Override
    public void onStart() {
        super.onStart();
        if (Constants.SETTING == 1){
            withdrawDialog.show();
        }
        Constants.SETTING = 0;
    }
}
