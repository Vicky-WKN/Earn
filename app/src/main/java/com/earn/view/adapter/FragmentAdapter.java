package com.earn.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by asus on 2017/7/15.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter{
    private ArrayList<Fragment> fragmentList ;
    private ArrayList<String> titles;
    public FragmentAdapter(FragmentManager fm, ArrayList<Fragment> fragmentList, ArrayList<String> titles) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return titles.get(position);
    }
}
