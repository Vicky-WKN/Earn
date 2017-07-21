package com.earn.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.earn.R;

/**
 * Created by asus on 2017/7/21.
 */

public class WebActivity extends AppCompatActivity{
    private WebView webView ;
    private ImageView close_button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webactivity_layout);
        webView = (WebView) findViewById(R.id.web_layout);
        close_button = (ImageView) findViewById(R.id.web_close);
        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();

        String uri = intent.getStringExtra("uri");
        webView.loadUrl(uri);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
