package com.earn.view.adapter;

import android.content.Context;
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
    private Context context;

        private final List<String> catalogs = new ArrayList<String>();

        public tabAdapter(FragmentManager fm,Context context) {
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
             newFragment = NewFragment.newInstance(position+1);
            //new ToastUtil(context,"第"+position);
            return newFragment;
        }

    }