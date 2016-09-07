package com.example.john.ziteng.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.john.ziteng.R;

/**
 * 资讯加载的webview
 * Created by john on 2016/3/27.
 */
public class WebViewInfoActivity extends BaseActivity{

    private ImageView web_fanhui;
    private WebView webView;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webviewinfo);
        Intent intent = getIntent();
        String URL = intent.getStringExtra("URL");
        initView(URL);
    }

    private void initView(String url) {
        pb = (ProgressBar) findViewById(R.id.pb);
        pb.setMax(100);
        web_fanhui = (ImageView) findViewById(R.id.web_fanhui);
        web_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        webView = (WebView) findViewById(R.id.webViewinfo);
        webView.loadUrl(url);
        webView.requestFocusFromTouch();//支持获取手势焦点
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        WebSettings setting = webView.getSettings();
        setting.setJavaScriptEnabled(true);//JS交互
        webView.setWebChromeClient(new WebViewChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }
    private class WebViewChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            pb.setProgress(newProgress);
            if(newProgress==100){
                pb.setVisibility(View.GONE);
            }
            super.onProgressChanged(view, newProgress);
        }

    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();//返回上一页面
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}

