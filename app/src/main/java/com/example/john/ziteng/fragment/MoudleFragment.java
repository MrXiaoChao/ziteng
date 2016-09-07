package com.example.john.ziteng.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.john.ziteng.R;
import com.example.john.ziteng.utils.SPUtils;

/**
 * 模块
 * Created by john on 2016/5/17.
 */
public class MoudleFragment extends Fragment{
    private ImageView back;
    private WebView webView;
    private String batteryId;
    private String moduleId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_moudle, container, false);
        initview(view);
        moduleId = String.valueOf(SPUtils.get(getActivity(), "moduleId", ""));
        checkURl();
        loadweb();
        return view;
    }

    private StringBuffer checkURl() {
        StringBuffer url=new StringBuffer();
        if (getResources().getConfiguration().locale.getCountry().equals("CN")){
            url.append("http://123.57.251.129:8088/dem/phone/station/moduleDataCurve.jsp?moduleId=");
            url.append(moduleId);
            url.append("&zyw=1");
        }else {
            url.append("http://123.57.251.129:8088/dem/phone/station/moduleDataCurve.jsp?moduleId=");
            url.append(moduleId);
            url.append("&zyw=2");
        }
        return url;
    }

    private void loadweb() {
        webView.loadUrl(String.valueOf(checkURl()));
        webView.requestFocusFromTouch();//支持获取手势焦点
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        WebSettings setting = webView.getSettings();
        setting.setJavaScriptEnabled(true);//JS交互
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(String.valueOf(checkURl()));
                return true;
            }
        });
    }


    private void initview(View view) {
        back = (ImageView) view.findViewById(R.id.module1_fanhui);
        webView = (WebView) view.findViewById(R.id.module_web);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }
}
