package com.example.john.ziteng.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.john.ziteng.R;
import com.example.john.ziteng.activity.SiteList2Activity;
import com.example.john.ziteng.activity.SiteListActivity;
import com.example.john.ziteng.activity.WebMapActivity;
import com.example.john.ziteng.activity.WebViewTotalActivity;
import com.example.john.ziteng.application.MyApplication;
import com.example.john.ziteng.domain.Monitor;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;
import com.example.john.ziteng.utils.SPUtils;

/**
 * 全网监控
 * Created by john on 2016/3/17.
 */
public class MonitorFragment extends Fragment implements View.OnClickListener {

    private TextView title;
    private RelativeLayout site;
    private RelativeLayout sitetd;
    private RelativeLayout ditu;
    private TextView tv_item_total2;
    private TextView tv_item_power2;
    private TextView tv_item_nowpower2;
    private String mPhone;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monitor, container, false);
        mPhone = (String) SPUtils.get(getActivity(), "phone", "");
        initView(view);
        getDateFromService();
        return view;
    }

    private void getDateFromService() {
        StringRequest request = new StringRequest(Request.Method.GET, Path.Jiankong, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Monitor monitor = PaseJson.PaseMonitor(s);
                if (monitor != null) {
                    tv_item_total2.setText(monitor.getNum());
                    tv_item_power2.setText(monitor.getStorageCapacity());
                    tv_item_nowpower2.setText(monitor.getCurrentstorage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        request.setTag("Jiankong");
        MyApplication.getHttpQueue().add(request);
    }

    private void initView(View view) {
        title = (TextView) view.findViewById(R.id.title);
        title.setText(R.string.monitor);

        tv_item_total2 = (TextView) view.findViewById(R.id.tv_item_total2);
        tv_item_power2 = (TextView) view.findViewById(R.id.tv_item_power2);
        tv_item_nowpower2 = (TextView) view.findViewById(R.id.tv_item_nowpower2);

        site = (RelativeLayout) view.findViewById(R.id.rl_site);
        sitetd = (RelativeLayout) view.findViewById(R.id.rl_site_tingdian);
        ditu = (RelativeLayout) view.findViewById(R.id.rl_ditu);

        site.setOnClickListener(this);
        sitetd.setOnClickListener(this);
        ditu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_site:
                Intent intent1 = new Intent(getActivity(), SiteListActivity.class);
                startActivity(intent1);
                break;
            case R.id.rl_site_tingdian:

                Intent intent2 = new Intent(getActivity(), SiteList2Activity.class);
                startActivity(intent2);
                break;
            case R.id.rl_ditu:
                Intent intent3 = new Intent(getActivity(), WebMapActivity.class);
                startActivity(intent3);
                break;
        }

    }
}
