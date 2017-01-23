package com.example.john.ziteng.fragment;

import android.app.Fragment;
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
 * 列表
 * Created by john on 2016/5/17.
 */
public class ListFragment extends Fragment{
    private ImageView back;
    private WebView webView;
    private String batteryId;
    private String moduleId;
    private String unitId;
    private String equipId;
    private int checknumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_list, container, false);
        initview(view);
        equipId = String.valueOf(SPUtils.get(getActivity(), "equipId", ""));
        unitId = String.valueOf(SPUtils.get(getActivity(), "unitId", ""));
        moduleId = String.valueOf(SPUtils.get(getActivity(), "moduleId", ""));
        batteryId = String.valueOf(SPUtils.get(getActivity(), "batteryId", ""));
        checknumber = getActivity().getIntent().getIntExtra("checknumber",1);
        checkURl(checknumber);
        loadweb();
        return view;
    }
    private StringBuffer checkURl(int checknumber) {
        StringBuffer url=new StringBuffer();
        switch (checknumber){
            case 1:
                if (getResources().getConfiguration().locale.getCountry().equals("CN")){
                    url.append("http://123.57.251.129/dem/phone/station/allUnitHistory.jsp?equip_id=");
                    url.append(equipId+"&unitId=");
                    url.append(unitId+"&zyw=1");
                    StringBuffer a=url;
                }else {
                    url.append("http://123.57.251.129/dem/phone/station/allUnitHistory.jsp?equip_id=");
                    url.append(equipId+"&unitId=");
                    url.append(unitId+"&zyw=2");
                }
                break;
            case 2:
                if (getResources().getConfiguration().locale.getCountry().equals("CN")){
                    url.append("http://123.57.251.129/dem/phone/station/allModuleHistory.jsp?equip_id=");
                    url.append(equipId+"&moduleId=");
                    url.append(moduleId+"&zyw=1");
                }else {
                    url.append("http://123.57.251.129/dem/phone/station/allUnitHistory.jsp?equip_id=");
                    url.append(equipId+"&moduleId=");
                    url.append(moduleId+"&zyw=2");
                }
                break;
            case 3:
                if (getResources().getConfiguration().locale.getCountry().equals("CN")){
                    url.append("http://123.57.251.129/dem/phone/station/batteryDataCurve.jsp?equip_id=");
                    url.append(equipId+"&batteryId=");
                    url.append(batteryId+"&zyw=1");
                    StringBuffer a=url;
                }else {
                    url.append("http://123.57.251.129/dem/phone/station/batteryDataCurve.jsp?equip_id=");
                    url.append(equipId+"&batteryId=");
                    url.append(batteryId+"&zyw=2");
                }
                break;
        }


        return url;
    }

    private void loadweb() {
        webView.loadUrl(String.valueOf(checkURl(checknumber)));
        webView.requestFocusFromTouch();//支持获取手势焦点
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        WebSettings setting = webView.getSettings();
        setting.setJavaScriptEnabled(true);//JS交互
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(String.valueOf(checkURl(checknumber)));
                return true;
            }
        });
    }

    private void initview(View view) {
        back = (ImageView) view.findViewById(R.id.list1_fanhui);
        webView = (WebView) view.findViewById(R.id.list_web);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }
}
