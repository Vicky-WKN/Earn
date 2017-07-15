package com.earn.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.earn.R;
import com.earn.view.fragment.HomeFragment;
import com.earn.view.fragment.InviteFragment;
import com.earn.view.fragment.MeFragment;
import com.earn.view.fragment.SignFragment;

/**
 * Created by asus on 2017/7/14.
 */

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    BottomNavigationBar bottomNavigationBar;
    FrameLayout frameLayout;
    private HomeFragment homeFragment;
    private SignFragment signFragment;
    private InviteFragment inviteFragment;
    private MeFragment meFragment;
    FragmentManager fm;
    FragmentTransaction transaction;
    TextView toolTitle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setDefaultFragment();
        InitBottomNavigationBar();
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        toolTitle = (TextView) findViewById(R.id.toolBarTitle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//隐藏默认标题,貌似toolbar.setTitle("")有同样的效果
    }

    /**
     * 碎片管理
     */
    private void setDefaultFragment() {
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        homeFragment = HomeFragment.getInstance();
        transaction.replace(R.id.fragment_container,homeFragment);
        transaction.commit();
    }

    /**
     * 初始化Bottom
     */
    private void InitBottomNavigationBar() {
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.home,"主页"))
                .addItem(new BottomNavigationItem(R.drawable.sign,"签到"))
                .addItem(new BottomNavigationItem(R.drawable.invite,"邀请"))
                .addItem(new BottomNavigationItem(R.drawable.me,"我"))
                .initialise();

        bottomNavigationBar.setTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(int position) {
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        switch (position){
            case 0:
                if(homeFragment == null){
                    homeFragment = HomeFragment.getInstance();
                }
                transaction.replace(R.id.fragment_container,homeFragment);
                toolTitle.setText("阅赚宝");
                break;
            case 1:
                if(signFragment == null){
                    signFragment = SignFragment.getInstance();
                }
                transaction.replace(R.id.fragment_container,signFragment);
                toolTitle.setText("签到");
                break;
            case 2:
                if(inviteFragment == null){
                    inviteFragment = InviteFragment.getInstance();
                }
                transaction.replace(R.id.fragment_container,inviteFragment);
                toolTitle.setText("邀请");
                break;
            case 3:
                if(meFragment == null){
                    meFragment = MeFragment.getInstance();
                }
                transaction.replace(R.id.fragment_container,meFragment);
                toolTitle.setText("个人中心");
                break;
        }
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
