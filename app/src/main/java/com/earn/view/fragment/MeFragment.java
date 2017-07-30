package com.earn.view.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.earn.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by asus on 2017/7/15.
 */

public class MeFragment extends Fragment {
    static MeFragment meFragment;
    private SimpleDraweeView mSimpleDraweeView;
    View view;
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
    }
}
