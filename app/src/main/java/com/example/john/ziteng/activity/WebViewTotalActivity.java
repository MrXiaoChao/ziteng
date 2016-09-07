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
import android.widget.TextView;

import com.example.john.ziteng.R;

/**
 * webview统一顶栏样式
 * Created by john on 2016/4/7.
 */
public class WebViewTotalActivity extends BaseActivity {


    private ImageView back;
    private TextView title;
    private WebView webView;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_total);
        Intent intent = getIntent();
        String URL = intent.getStringExtra("URL");
        String title = intent.getStringExtra("biaoti");
        initview(URL, title);
    }
//初始化控件

    private void initview(String url, String biaoti) {
        pb = (ProgressBar) findViewById(R.id.pb);
        pb.setMax(100);
        back = (ImageView) findViewById(R.id.web_fanhui_taotal);
        title = (TextView) findViewById(R.id.web_title_total);
        //返回键
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //设置标题
        title.setText(biaoti);
        webView = (WebView) findViewById(R.id.webViewtotal);
        webView.requestFocusFromTouch();//支持获取手势焦点
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        WebSettings s = webView.getSettings();
        s.setJavaScriptEnabled(true);//JS交互
        s.setBuiltInZoomControls(true);
        s.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        s.setUseWideViewPort(true);
        s.setLoadWithOverviewMode(true);
        s.setSavePassword(true);
        s.setSaveFormData(true);
        s.setJavaScriptEnabled(true);
        s.setGeolocationEnabled(true);
        s.setDomStorageEnabled(true);
        s.setDatabaseEnabled(true);
        s.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.requestFocus();
        webView.loadUrl(url);
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
    //js弹框处理
//        webView.setWebChromeClient(new WebChromeClient() {
//            //处理javascript中的alert
//            @Override
//            public boolean onJsAlert(WebView view, String url, String message,
//                                     final JsResult result) {
//                //构架一个builder来显示网页中的对话框
//                AlertDialog.Builder builder = new AlertDialog.Builder(WebViewTotalActivity.this);
//                builder.setTitle("提示对话框");
//                builder.setMessage(message);
//                builder.setPositiveButton(android.R.string.ok, new AlertDialog.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //点击确定按钮之后，继续执行网页中的操作
//                        result.confirm();
//                    }
//                });
//                builder.setCancelable(false);
//                builder.create();
//                builder.show();
//                return true;
//            }
//
//            //处理javascript中的confirm
//            @Override
//            public boolean onJsConfirm(WebView view, String url,
//                                       String message, final JsResult result) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(WebViewTotalActivity.this);
////                builder.setTitle("待选择的对话框");
//                builder.setMessage(message);
//                builder.setPositiveButton(android.R.string.ok, new AlertDialog.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        result.confirm();
//                    }
//                });
//                builder.setNegativeButton(android.R.string.cancel, new AlertDialog.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        result.cancel();
//                    }
//                });
//                builder.setCancelable(false);
//                builder.create();
//                builder.show();
//                return true;
//            }
//
//            //设置网页的加载的进度条
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                getWindow().setFeatureInt(Window.FEATURE_PROGRESS, newProgress);
//                super.onProgressChanged(view, newProgress);
//            }
//        });


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
