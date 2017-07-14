package com.earn.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.earn.R;

/**
 * Created by asus on 2017/7/14.
 */

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.home,"主页"))
                .addItem(new BottomNavigationItem(R.drawable.sign,"签到"))
                .addItem(new BottomNavigationItem(R.drawable.invite,"邀请"))
                .addItem(new BottomNavigationItem(R.drawable.me,"我"))
                .initialise();

        bottomNavigationBar.setTabSelectedListener(this);

    }

    @Override
    public void onTabSelected(int position) {

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
