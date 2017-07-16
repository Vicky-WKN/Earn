package com.earn.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.earn.R;
import com.earn.view.adapter.FragmentAdapter;

import java.util.ArrayList;

/**
 * Created by asus on 2017/7/15.
 */

public class HomeFragment extends Fragment {
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    View view;
    static HomeFragment homeFragment;
    public static HomeFragment getInstance()
    {
        if (homeFragment == null)
        {
            homeFragment = new HomeFragment();
            return homeFragment;
        }else{
            return homeFragment;
        }    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_home,container,false);
//        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);


        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        initViewPager();
        return view;
    }

    private void initViewPager() {
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        ArrayList<String> titles = new ArrayList<>();
        titles.add("热点");
        titles.add("体育");
        titles.add("时尚");
        titles.add("情感");
        titles.add("娱乐");
        titles.add("汽车");
        titles.add("财经");
        titles.add("游戏");
        titles.add("教育");
        titles.add("科技");

        for(int i =0 ;i<titles.size();i++)
        {
            tabLayout.addTab(tabLayout.newTab().setText(titles.get(i)));
        }
        ArrayList<Fragment> fragments = new ArrayList<>();
        for(int i=0;i<titles.size();i++)
        {
            fragments.add(new NewFragment());
        }
        FragmentAdapter fragmentAdapteradapter =
                new FragmentAdapter(getActivity().getSupportFragmentManager(), fragments, titles);
        //给ViewPager设置适配器
        viewPager.setAdapter(fragmentAdapteradapter);
        //将TabLayout和ViewPager关联起来。
        tabLayout.setupWithViewPager(viewPager);
        //给TabLayout设置适配器
        tabLayout.setTabsFromPagerAdapter(fragmentAdapteradapter);
    }
}
