package com.earn.view.activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.earn.Contract.MainContract;
import com.earn.R;
import com.earn.model.Ad;
import com.earn.presenter.MainPresenter;
import com.earn.util.Constants;

import java.util.List;


public class StartLoadingActivity extends AppCompatActivity implements MainContract.View {
    private final int MSG_LOGIN_SUCCESS = 100;
    private final int MSG_LOGIN_FAIL = 101;
    private final int MSG_VERSION_CHECK_TIMEOUT = 102;
    private final int UPDATE_TEAY_TIME = 103;

    private int delayTime = 4;// 广告4秒倒计时
    private List<Ad> alist;
    private ImageView welComeImg, adImg;
    private RelativeLayout rl;

    private Button skipBtn;
    // 是否首次登陆
    private Boolean myIsfirst = null;
    Editor edited = null;
    SharedPreferences share = null;
    private Context context;
    private boolean adIsFinish = true;
    private Ad mAdver = null;

    //更新信息
    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.start_loading_activity);
        welComeImg = (ImageView) findViewById(R.id.iv_welcome_img);
        rl = (RelativeLayout) findViewById(R.id.rl_show_ad);
        skipBtn = (Button) findViewById(R.id.ll_ad_skip_btn);

        adImg = (ImageView) findViewById(R.id.iv_ad_img);

        presenter = new MainPresenter(this,this);
        presenter.start();
        // alist = AdvertisementDao.getAdDataList();// 查询广告信息
//        if (alist != null && alist.size() > 0) {
//            mAdver = alist.get(alist.size() - 1);
//            File f = new File(mAdver.getAndroidimg());// 获取最新的一条广告信息
//            if (f.exists() && !isDeadline(alist.get(alist.size() - 1))) {//// 判断文件存在，并且没有过期
//                Bitmap b = AdvertiseUtil.scaleImgSize(f);
//                adImg.setImageBitmap(b);
//                adIsFinish = true;
//                b = null;
//            }
//        }

        skipBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToMain();
            }
        });

    }
//        adImg.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if ("0".equals(mAdver.getFlag())) {// 0 代表这个广告可以点击
//					/*
//					 * Intent i = new Intent(context, AdverActivity.class);
//					 * i.putExtra("url_key", mAdver.getUrl());
//					 * context.startActivity(i); finish();
//					 */
//                }
//            }
//        });

        // 启动一个线程，去删除过期的广告信息
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (Ad ad : alist) {
//                    if (isDeadline(ad)) {
//                        // 如果当前广告过期，则删除数据库中的记录，和本地的文件
//                        AdvertiseUtil.deleteCachedAdverImg(context, ad);
//                        AdvertisementDao.deleteAd(ad);
//                    }
//                }
//            }
//        }).start();
//    }

    /**
     * 判断某个广告是否过期 过期 true 未过期 false
     *
     *
     * @return
     */
//    private boolean isDeadline(Ad adver) {
//        long deadLine = Long.parseLong(adver.getEnd() + "000");
//        // 获取当前时间的时间戳
//        long currentLine = AdvertiseUtil.getCurrentDateMil();
//        if (currentLine > deadLine) {// 过期
//            return true;
//        }
//        return false;
//    }

    protected void onResume() {
        super.onResume();
        // 获取广告
        //AdvertiseUtil.getLoadingAd(context);
        /**
         * 超时设置
         */
        handler.sendEmptyMessageDelayed(UPDATE_TEAY_TIME, 1 * 1000);
    }

    @Override
    protected void onDestroy() {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler.removeMessages(MSG_VERSION_CHECK_TIMEOUT);
            handler = null;
        }
        super.onDestroy();
    }

    private void jumpToMain() {
        startActivity(new Intent(StartLoadingActivity.this, MainActivity.class));
        handler.removeMessages(100);
        handler.removeMessages(101);
        handler.removeMessages(102);
        handler.removeMessages(103);
        finish();
    }

    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOGIN_SUCCESS:
                    Intent intent = new Intent(StartLoadingActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case MSG_LOGIN_FAIL:

                    break;
                case MSG_VERSION_CHECK_TIMEOUT:
                    // 启动页面版本检查接口调用：3秒中没有返回，跳转页面
                    skipToLoginOrMain();
                    break;
                case UPDATE_TEAY_TIME:
                    if (adIsFinish) {
                        if (delayTime > 0) {
                            //welComeImg.setVisibility(View.GONE);
                            rl.setVisibility(View.VISIBLE);
                            skipBtn.setText("跳过 "+delayTime);
                            handler.sendEmptyMessageDelayed(UPDATE_TEAY_TIME, 1000);
                            delayTime--;
                        } else {
                            jumpToMain();
                            finish();
                        }
                    } else {
                        jumpToMain();
                        finish();
                    }
                    break;
            }
        };
    };

    private void skipToLoginOrMain() {
        handler.sendEmptyMessageDelayed(UPDATE_TEAY_TIME, 1000);
    }

    /**
     * 重新获取用户信息，更新信息
     * @param i
     */
    @Override
    public void start(final int i) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(i == 0){
                    Constants.logined = true;
                    Log.d("获取信息成功", "run: ");
                    //new ToastUtil(MainActivity.this,"获取信息成功");
                }else{
                    Constants.logined = false;
                    //new ToastUtil(MainActivity.this,"获取信息失败");
                    Log.d("获取信息失败", "run: ");
                }
            }
        });

    }
}
