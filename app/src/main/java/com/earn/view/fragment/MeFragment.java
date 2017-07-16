package com.earn.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.earn.R;

/**
 * Created by asus on 2017/7/15.
 */

public class MeFragment extends Fragment {
    static MeFragment meFragment;
    public static MeFragment getInstance()
    {
        if (meFragment == null)
        {
            meFragment = new MeFragment();
            return meFragment;
        }else{
            return meFragment;
        }    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_me,container,false);
        return view;
    }
}
