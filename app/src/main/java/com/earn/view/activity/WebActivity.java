package com.earn.view.activity;

import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.earn.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Date;

/**
 * Created by asus on 2017/7/21.
 */

public class WebActivity extends AppCompatActivity{
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private TextView text1;
    private TextView titleText;
    private TextView text2;
    private TextView date;
    private SimpleDraweeView mSimpleDraweeView;
    private SimpleDraweeView mSimpleDraweeView1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.webactivity_layout);
        initView();

    }

    private void initView() {
        mSimpleDraweeView1 = (SimpleDraweeView) findViewById(R.id.web_img2);
        mSimpleDraweeView = (SimpleDraweeView) findViewById(R.id.web_img1);

        toolbar = (Toolbar) findViewById(R.id.web_toolbar);
        toolbar.setNavigationIcon(R.drawable.web_close_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //toolbar.setTitleTextColor(Color.WHITE);
        //设置收缩展开toolbar字体颜色
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.ctb);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        titleText = (TextView) findViewById(R.id.web_title);
        String title =getIntent().getStringExtra("title");
        setNewTitle(title);

        //设置toolbar图片
        if(getIntent().hasExtra("img1")){
        Uri uri1 = Uri.parse(getIntent().getStringExtra("img1"));
        setImg1(uri1);
            Log.d("有第一张照片", "initView: ");
        }
        //设置图片
        if(getIntent().hasExtra("img2")){
            Uri uri2 = Uri.parse(getIntent().getStringExtra("img2"));
            setImg2(uri2);
            Log.d("有第二张照片", "initView: ");
        }


        //设置时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        // new Date()为获取当前系统时间
        date = (TextView) findViewById(R.id.web_date);
        date.setText("日期 : "+df.format(new Date()));

        //设置文章内容
        String msg1 = "";
        String msg2 = "";
        String text = getIntent().getStringExtra("text");
        msg1 = text;
        String[] lines = text.split("<-separate->");
        if(lines.length>5&&getIntent().hasExtra("img2")){
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer stringBuffer2 = new StringBuffer();

            for(int i=0;i<=5;i++){
                stringBuffer.append(lines[i]);
            }
            for(int i =6;i<lines.length;i++){
                stringBuffer2.append(lines[i]);
            }
            msg1 = String.valueOf(stringBuffer);
            msg2 = String.valueOf(stringBuffer2);

        }else{
            mSimpleDraweeView1.setVisibility(View.GONE);
        }

        text1 = (TextView) findViewById(R.id.web_text1);
        setText1(msg1);

        text2 = (TextView) findViewById(R.id.web_text2);
        setText2(msg2);

    }


    /**
     * 新闻内容1
     * @param msg
     */
    private void setText1(String msg) {
        text1.setText(msg);
    }

    /**
     * toolbar图片
     * @param uri
     */
    private void setImg1(Uri uri){
        mSimpleDraweeView.setImageURI(uri);
    }

    /**
     * 设置标题
     * @param newTitle
     */
    public void setNewTitle(String newTitle) {
        collapsingToolbarLayout.setTitle(newTitle);
        titleText.setText(newTitle);
    }

    /**
     * 设置文章2
     * @param msg
     */
    public void setText2(String msg) {
        text2.setText(msg);
    }

    public void setImg2(Uri img2) {
        mSimpleDraweeView1.setImageURI(img2);
    }
}
