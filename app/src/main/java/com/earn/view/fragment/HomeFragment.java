package com.earn.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.earn.R;
import com.earn.view.ExpandView;
import com.earn.view.MyTab;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2017/7/15.
 */

public class HomeFragment extends Fragment {
    private ImageView imageView;
    private ExpandView mExpandView;

    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    private MyTab tabs;
    private MyPagerAdapter adapter;
    //private TabLayout tabLayout;

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

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabs = (MyTab) view.findViewById(R.id.category_strip);
        adapter = new MyPagerAdapter((getActivity()).getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        tabs.setViewPager(viewPager);
        initViewPager();
        return view;
    }

    private void initViewPager() {
        //tabLayout = (TabLayout) view.findViewById(R.id.tabs);
//        ArrayList<String> titles = new ArrayList<>();
//        titles.add("热点");
//        titles.add("体育");
//        titles.add("时尚");
//        titles.add("情感");
//        titles.add("娱乐");
//        titles.add("汽车");
//        titles.add("财经");
//        titles.add("游戏");
//        titles.add("教育");
//        titles.add("科技");

//        for(int i =0 ;i<titles.size();i++)
//        {
//            tabLayout.addTab(tabLayout.newTab().setText(titles.get(i)));
//        }
//        ArrayList<Fragment> fragments = new ArrayList<>();
//        for(int i=0;i<titles.size();i++)
//        {
//            fragments.add(new NewFragment());
//        }
//        FragmentAdapter fragmentAdapteradapter =
//                new FragmentAdapter(getActivity().getSupportFragmentManager(), fragments, titles);
//        //给ViewPager设置适配器
//        viewPager.setAdapter(fragmentAdapteradapter);
//        //将TabLayout和ViewPager关联起来。
//        tabLayout.setupWithViewPager(viewPager);
//        //给TabLayout设置适配器
//        tabLayout.setTabsFromPagerAdapter(fragmentAdapteradapter);

    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final List<String> catalogs = new ArrayList<String>();

        public MyPagerAdapter(FragmentManager fm) {
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
            return new NewFragment();
        }

    }

}
