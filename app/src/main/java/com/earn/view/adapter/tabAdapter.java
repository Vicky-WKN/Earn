package com.earn.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.earn.view.fragment.NewFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2017/7/20.
 */


public class tabAdapter extends FragmentPagerAdapter {

    private NewFragment newFragment = null;
    private NewFragment last = null;

        private final List<String> catalogs = new ArrayList<String>();

        public tabAdapter(FragmentManager fm) {
            super(fm);
            catalogs.add("热点");
            catalogs.add("\u672c\u5730");
            catalogs.add("视频");
            catalogs.add("社会");
            catalogs.add("娱乐");
            catalogs.add("科技");
            catalogs.add("财经");
            catalogs.add("军事");
            catalogs.add("国际");
            catalogs.add("美女");
            catalogs.add("政务");
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return catalogs.get(position);
        }

        @Override
        public int getCount() {
            return catalogs.size();
        }

        @Override
        public Fragment getItem(int position) {
            last = newFragment;
             newFragment = new NewFragment();
            return newFragment;

        }

    }