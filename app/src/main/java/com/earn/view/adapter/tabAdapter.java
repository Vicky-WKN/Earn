package com.earn.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.earn.view.fragment.NewFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by asus on 2017/7/20.
 */


public class tabAdapter extends FragmentPagerAdapter {

    private NewFragment newFragment = null;

    private Context context;

    private HashMap<String,NewFragment> fragments = new HashMap<>();

    private final List<String> catalogs = new ArrayList<String>();

    private FragmentManager fm;

    public tabAdapter(FragmentManager fm, Context context) {
        super(fm);
        catalogs.add("军事");
        catalogs.add("排行");
        catalogs.add("公益");
        catalogs.add("国内");
        catalogs.add("国际");
        catalogs.add("媒体");
        catalogs.add("政务");
        catalogs.add("社会");
        this.context = context;
        this.fm = fm;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return catalogs.get(position);
    }

    @Override
    public int getCount() {
        return catalogs.size();
    }


    //++++++++++++++++++++切换优化+++++++++++++++++++++//
    @Override
    public Fragment getItem(int position) {
        //切换优化
        if(fragments.get(position+"") == null){
            newFragment = NewFragment.newInstance(position + 1);
            fragments.put(position+"",newFragment);
        }else{
            newFragment = fragments.get(position+"");
        }
        //new ToastUtil(context,"第"+position);
        return newFragment;
    }


    @Override
    public Fragment instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container,
                position);
        fm.beginTransaction().show(fragment).commit();
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container, position, object);
        fm.beginTransaction().hide(fragments.get(position+"")).commit();
    }



}