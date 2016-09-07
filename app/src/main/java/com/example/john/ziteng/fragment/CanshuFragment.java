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

import com.example.john.ziteng.R;
import com.example.john.ziteng.urlpath.Path;

/**
 * 设备参数全列表
 * Created by john on 2016/3/29.
 */
public class CanshuFragment extends Fragment{
    private WebView webView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_canshu,container,false);
        Intent intent = getActivity().getIntent();
        String equip_id = intent.getStringExtra("equip_id");
        String group_id = intent.getStringExtra("group_id");
        StringBuffer url = new StringBuffer();
        url.append(Path.Canshu);
        url.append("equip_id=");
        url.append(equip_id+"&");
        url.append("group_id=");
        url.append(group_id+"&");
        url.append("zyw=");
        if (getResources().getConfiguration().locale.getCountry().equals("CN")){
            url.append(1);//1代表中文
        }else {
            url.append(2);//2代表英文
        }
        webView = (WebView) view.findViewById(R.id.webViewCanshu);
        webView.loadUrl(String.valueOf(url));
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        WebSettings setting = webView.getSettings();
        setting.setJavaScriptEnabled(true);//JS交互
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        return view;
    }
}
