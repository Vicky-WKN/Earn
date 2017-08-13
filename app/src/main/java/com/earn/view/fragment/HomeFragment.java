package com.earn.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.earn.R;
import com.earn.view.ExpandView;
import com.earn.view.MyTab;
import com.earn.view.adapter.tabAdapter;

/**
 * Created by asus on 2017/7/15.
 */

public class HomeFragment extends Fragment{
    private ImageView imageView;
    private ExpandView mExpandView;
    private ViewPager viewPager;
    private MyTab tabs;
    private tabAdapter adapter;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_home,container,false);
        initExpand();
        initViewPager();
        return view;
    }

    /**
     * 初始化可展开
     */
    private void initExpand() {
        mExpandView = (ExpandView) view.findViewById(R.id.expandView);
        imageView = (ImageView) view.findViewById(R.id.icon_category);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(mExpandView.isExpand()){
                    mExpandView.collapse();
                    //mExpandView.setVisibility(View.GONE);
                }else{
                    //mExpandView.setVisibility(View.VISIBLE);
                    mExpandView.expand();
                }
            }
        });
    }

    /**
     * 初始化viewpager
     */
    private void initViewPager() {
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabs = (MyTab) view.findViewById(R.id.category_strip);
        adapter = new tabAdapter((getActivity()).getSupportFragmentManager(),getActivity());
        viewPager.setAdapter(adapter);
        tabs.setViewPager(viewPager);
    }

}