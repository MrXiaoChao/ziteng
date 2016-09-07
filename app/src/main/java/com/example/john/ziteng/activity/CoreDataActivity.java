package com.example.john.ziteng.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.john.ziteng.R;
import com.example.john.ziteng.application.MyApplication;
import com.example.john.ziteng.domain.SiteGuangJiang;
import com.example.john.ziteng.domain.SiteInfo;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.HashMap;
import java.util.Map;

/**
 * 站点关键数据
 * Created by john on 2016/7/28.
 */
public class CoreDataActivity extends BaseActivity{
    private TextView ritd;
    private TextView risc;
    private TextView riups;
    private TextView yuetd;
    private TextView yuesc;
    private TextView yueups;
    private TextView niantd;
    private TextView niansc;
    private TextView nianups;
    private TextView ups;
    private PullToRefreshScrollView scrollView;
    private String siteId;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_site_shuju);
        Intent intent=getIntent();
        siteId = intent.getStringExtra("siteId");
        initview();
        getDataFromService();
    }
    private void getDataFromService() {
        StringRequest request =new StringRequest(Request.Method.POST, Path.SiteGuanJiang, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                SiteGuangJiang siteGuangJiang= PaseJson.sitegj(s);
                if (siteGuangJiang!=null){
                    ritd.setText(siteGuangJiang.getDayOutpowerNum());
                    risc.setText(siteGuangJiang.getDayOutpowerOfen()+"h");
                    riups.setText(siteGuangJiang.getDayUps()+"h");
                    yuetd.setText(siteGuangJiang.getMonOutpowerNum());
                    yuesc.setText(siteGuangJiang.getMonOutpowerOfen()+"h");
                    yueups.setText(siteGuangJiang.getMonUps()+"h");
                    niantd.setText(siteGuangJiang.getYearOutpowerNum());
                    niansc.setText(siteGuangJiang.getYearOutpowerOfen()+"h");
                    nianups.setText(siteGuangJiang.getYearUps()+"h");
                    ups.setText(siteGuangJiang.getAllUps());
                }
                scrollView.onRefreshComplete();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("siteId",siteId);
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }

    private void initview() {
        back = (ImageView) findViewById(R.id.coredata_fanhui);
        scrollView = (PullToRefreshScrollView)findViewById(R.id.sv);
        ritd = (TextView) findViewById(R.id.tv_ritd);
        risc = (TextView)findViewById(R.id.tv_risc);
        riups = (TextView) findViewById(R.id.tv_riups);
        yuetd = (TextView) findViewById(R.id.tv_yuetd);
        yuesc = (TextView) findViewById(R.id.tv_yuesc);
        yueups = (TextView) findViewById(R.id.tv_yueups);
        niantd = (TextView) findViewById(R.id.tv_niantd);
        niansc = (TextView) findViewById(R.id.tv_niansc);
        nianups = (TextView) findViewById(R.id.tv_nianups);
        ups = (TextView) findViewById(R.id.tv_zups);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        scrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                String label = DateUtils.formatDateTime(
                        CoreDataActivity.this,
                        System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME
                                | DateUtils.FORMAT_SHOW_DATE
                                | DateUtils.FORMAT_ABBREV_ALL);
                // 显示最后更新的时间
                refreshView.getLoadingLayoutProxy()
                        .setLastUpdatedLabel(label);
                getDataFromService();
            }
        });
    }
}
