package com.earn.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.earn.R;

/**
 * Created by asus on 2017/7/15.
 */

public class SignFragment extends Fragment{
    static SignFragment signFragment;
    ImageView sign;
    ImageView signed;
    public static SignFragment getInstance()
    {
        if (signFragment == null)
        {
            signFragment = new SignFragment();
            return signFragment;
        }else{
            return signFragment;
        }    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_sign,container,false);
        sign = (ImageView) view.findViewById(R.id.sign_image);
        signed = (ImageView) view.findViewById(R.id.signed_image);
        listener();
        return view;
    }

    /**
     * 监听签到按钮
     */
    private void listener() {
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign.setVisibility(View.GONE);
                signed.setVisibility(View.VISIBLE);
            }
        });


        signed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signed.setVisibility(View.GONE);
                sign.setVisibility(View.VISIBLE);
            }
        });
    }
}
