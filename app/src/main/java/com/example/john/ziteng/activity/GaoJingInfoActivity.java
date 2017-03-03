package com.example.john.ziteng.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.john.ziteng.R;
import com.example.john.ziteng.adapter.WarnListviewAdapter;
import com.example.john.ziteng.application.MyApplication;
import com.example.john.ziteng.domain.WarnInfo;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;
import com.example.john.ziteng.utils.SPUtils;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 重要告警信息
 * Created by john on 2016/3/30.
 */
public class GaoJingInfoActivity extends BaseActivity {

    private ImageView gaojing_fanhui;
    private ListView listview_warn;
    private String mPhone;
    private RelativeLayout pb;
    private String siteId;
    private int number;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaojing);
        mPhone = (String) SPUtils.get(GaoJingInfoActivity.this, "phone", "");
        siteId = getIntent().getStringExtra("siteId");
        number = getIntent().getIntExtra("number", 0);
        initview();
        checkSJ(number);
    }

    private void checkSJ(int number) {
        switch (number){
            case 0:
                title.setText(getResources().getString(R.string.snas));
                getDateFromService();
                break;
            case 1:
                getDateFromService1();
                break;
        }
    }


    //首页告警
    private void getDateFromService() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.SiteGaojing, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                pb.setVisibility(View.GONE);
                ArrayList<WarnInfo> list = (ArrayList<WarnInfo>) PaseJson.PaseWarnInfo(s);
                WarnListviewAdapter adapter = new WarnListviewAdapter(GaoJingInfoActivity.this, list);
                if (adapter != null && list.size() > 0) {
                    listview_warn.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(GaoJingInfoActivity.this, "解析出错啦", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("loginname", mPhone);
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }

    //站点概况里面的告警
    private void getDateFromService1() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.gaojing, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                pb.setVisibility(View.GONE);
                ArrayList<WarnInfo> list = (ArrayList<WarnInfo>) PaseJson.PaseWarnInfo(s);
                WarnListviewAdapter adapter = new WarnListviewAdapter(GaoJingInfoActivity.this, list);
                if (adapter != null && list.size() > 0) {
                    listview_warn.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(GaoJingInfoActivity.this, "解析出错啦", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("siteId", siteId);
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }

    private void initview() {
        title = (TextView) findViewById(R.id.web_title);
        pb = (RelativeLayout)findViewById(R.id.rl_progress);
        gaojing_fanhui = (ImageView) findViewById(R.id.gaojing_fanhui);
        listview_warn = (ListView) findViewById(R.id.listview_warn);
        gaojing_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getHttpQueue().cancelAll("siteWarn");
    }
    //友盟统计
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
