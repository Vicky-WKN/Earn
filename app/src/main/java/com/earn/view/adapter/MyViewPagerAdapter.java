package com.earn.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.earn.view.fragment.NewFragment;

import java.util.List;

/**
 * Created by asus on 2017/8/14.
 */

public class MyViewPagerAdapter extends FragmentPagerAdapter{
    private List<String> titles;
    private NewFragment fragment1;
    private NewFragment fragment2;
    private NewFragment fragment3;
    private NewFragment fragment4;
    private NewFragment fragment5;
    private NewFragment fragment6;
    private NewFragment fragment7;
    private NewFragment fragment8;
    /**
     * 构造方法
     * @param manager
     * @param titles
     */
    public MyViewPagerAdapter(FragmentManager manager,List<String> titles){
        super(manager);
        this.titles=titles;
        fragment1 = NewFragment.newInstance(1);
        fragment2 = NewFragment.newInstance(2);
        fragment3 = NewFragment.newInstance(3);
        fragment4 = NewFragment.newInstance(4);
        fragment5 = NewFragment.newInstance(5);
        fragment6 = NewFragment.newInstance(6);
        fragment7 = NewFragment.newInstance(7);
        fragment8 = NewFragment.newInstance(8);
    }

    @Override
    public int getCount() {
        if (titles!=null){
            return titles.size();
        }
        return 0;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return fragment1;
            case 1:
                return fragment2;
            case 2:
                return fragment3;
            case 3:
                return fragment4;
            case 4:
                return fragment5;
            case 5:
                return fragment6;
            case 6:
                return fragment7;
            case 7:
                return fragment8;
            default:
                return fragment1;
        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        if (titles!=null){
            return titles.get(position);
        }
        return "";
    }

}