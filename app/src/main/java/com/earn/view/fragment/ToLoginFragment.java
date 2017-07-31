package com.earn.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.earn.R;
import com.earn.view.LoginActivity;

/**
 * Created by asus on 2017/7/29.
 */

public class ToLoginFragment extends Fragment {

    static ToLoginFragment toLoginFragment;


    public static ToLoginFragment getInstance(){
        if (toLoginFragment == null){
            toLoginFragment = new ToLoginFragment();
        }

        return toLoginFragment;
    }

    private Button button;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.fragment_to_login,container,false);
        button = (Button) view.findViewById(R.id.to_login_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
