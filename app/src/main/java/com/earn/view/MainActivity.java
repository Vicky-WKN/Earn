package com.earn.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.earn.Contract.MainContract;
import com.earn.R;
import com.earn.presenter.MainPresenter;
import com.earn.util.Constants;
import com.earn.view.fragment.HomeFragment;
import com.earn.view.fragment.InviteFragment;
import com.earn.view.fragment.MeFragment;
import com.earn.view.fragment.SignFragment;
import com.earn.view.fragment.ToLoginFragment;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by asus on 2017/7/14.
 */

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener ,MainContract.View{

    BottomNavigationBar bottomNavigationBar;
    FrameLayout frameLayout;
    private HomeFragment homeFragment;
    private SignFragment signFragment;
    private InviteFragment inviteFragment;
    private MeFragment meFragment;
    private ToLoginFragment toLoginFragment;
    FragmentManager fm;
    FragmentTransaction transaction;
    private MainContract.Presenter presenter;
    //TextView toolTitle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Fresco.initialize(this);
        setDefaultFragment();//设置一打开就默认的碎片
        InitBottomNavigationBar();//BottomBar设置
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        //toolTitle = (TextView) findViewById(R.id.toolBarTitle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//隐藏默认标题,貌似toolbar.setTitle("")有同样的效果
        presenter = new MainPresenter(this,this);
        presenter.start();
    }

    /**
     * 碎片管理
     */
    private void setDefaultFragment() {
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        homeFragment = new HomeFragment();
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

        bottomNavigationBar.setTabSelectedListener(MainActivity.this);
    }

    @Override
    public void onTabSelected(int position) {
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        switch (position){
            case 0:
                if(homeFragment == null){
                    homeFragment = new HomeFragment();
                }
                transaction.replace(R.id.fragment_container,homeFragment);
                //toolTitle.setText("阅赚宝");
                break;
            case 1:

                if(Constants.logined){
                    if(signFragment == null){
                        signFragment = SignFragment.getInstance();
                    }
                    transaction.replace(R.id.fragment_container,signFragment);
                }else{
                    if(toLoginFragment == null){
                        toLoginFragment = ToLoginFragment.getInstance();
                    }
                    transaction.replace(R.id.fragment_container,toLoginFragment);
                }
                //toolTitle.setText("签到");
                break;
            case 2:

                if(Constants.logined) {
                    if (inviteFragment == null) {
                        inviteFragment = InviteFragment.getInstance();
                    }
                    transaction.replace(R.id.fragment_container, inviteFragment);
                } else{
                    if(toLoginFragment == null){
                        toLoginFragment = ToLoginFragment.getInstance();
                    }
                    transaction.replace(R.id.fragment_container,toLoginFragment);
                }
                break;
            case 3:
                if(Constants.logined){
                    if(meFragment == null){
                        meFragment = MeFragment.getInstance();
                    }
                    transaction.replace(R.id.fragment_container,meFragment);
                } else{
                    if(toLoginFragment == null){
                        toLoginFragment = ToLoginFragment.getInstance();
                    }
                    transaction.replace(R.id.fragment_container,toLoginFragment);
                }
                //toolTitle.setText("个人中心");
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

    @Override
    public void start(final int i) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(i == 0){
                    Constants.logined = true;
                }

            }
        });

    }
}
