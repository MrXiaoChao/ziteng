package com.example.john.ziteng.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.john.ziteng.R;
import com.example.john.ziteng.application.MyApplication;
import com.example.john.ziteng.domain.MapSiteInfo;
import com.example.john.ziteng.domain.SiteDelicInfo;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.HashMap;
import java.util.Map;

/**
 * 地图进入该详情页
 * Created by john on 2016/4/28.
 */
public class SiteInfoActivity extends BaseActivity {

    private MapSiteInfo mapSiteInfo;
    private TextView name;
    private TextView time;
    private TextView power;
    private TextView dianjia;
    private TextView city;
    private TextView temperature;
    private TextView cond;
    private TextView max;
    private TextView dir;
    private TextView currentstorageCapacity;
    private TextView currentPower;
    private TextView allSaveElectricity;
    private TextView aveSaveElectricity;
    private TextView allSaveMoney;
    private TextView aveSaveMoney;
    private TextView allemissions;
    private TextView aveemissions;
    private TextView status;
    private TextView statusTime;
    private RelativeLayout rltime;
    private ImageView fanhui;
    private TextView title;
    private PullToRefreshScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siteinfo);
        Intent intent = getIntent();
        mapSiteInfo = (MapSiteInfo) intent.getSerializableExtra("mapsiteinfo");
        initview();
        getDataFromService();
        setDataTOUi();
    }
    private void setDataTOUi() {
        title.setText(mapSiteInfo.getName());
        name.setText(mapSiteInfo.getName());
        time.setText(mapSiteInfo.getDeploytime());
        power.setText(mapSiteInfo.getStorageCapacity());
        dianjia.setText(mapSiteInfo.getElectrovalency());
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initview() {
        scrollView = (PullToRefreshScrollView)findViewById(R.id.sv);
        fanhui = (ImageView) findViewById(R.id.mapsite_fanhui);
        title = (TextView) findViewById(R.id.mapsite_title);
        name = (TextView) findViewById(R.id.tv_mingcheng);
        time = (TextView) findViewById(R.id.tv_shijian);
        power = (TextView) findViewById(R.id.tv_nengli);
        dianjia = (TextView) findViewById(R.id.dianjia);

        city = (TextView) findViewById(R.id.city);
        temperature = (TextView) findViewById(R.id.temperature);
        cond = (TextView) findViewById(R.id.cond);
        max = (TextView) findViewById(R.id.max);
        dir = (TextView) findViewById(R.id.dir);
        currentstorageCapacity = (TextView) findViewById(R.id.currentstorageCapacity);
        currentPower = (TextView) findViewById(R.id.currentPower);
        allSaveElectricity = (TextView) findViewById(R.id.allSaveElectricity);
        aveSaveElectricity = (TextView) findViewById(R.id.aveSaveElectricity);
        allSaveMoney = (TextView) findViewById(R.id.allSaveMoney);
        aveSaveMoney = (TextView) findViewById(R.id.aveSaveMoney);
        allemissions = (TextView) findViewById(R.id.allemissions);
        aveemissions = (TextView) findViewById(R.id.aveemissions);
        status = (TextView) findViewById(R.id.status);
        statusTime = (TextView) findViewById(R.id.statusTime);
        rltime = (RelativeLayout) findViewById(R.id.re_time);
        scrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                String label = DateUtils.formatDateTime(
                        SiteInfoActivity.this,
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
    private void getDataFromService() {
        StringRequest request =new StringRequest(Request.Method.POST, Path.SiteDelicInfo, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                SiteDelicInfo siteDelicInfo= PaseJson.sitedelicInfo(s);
                if (siteDelicInfo!=null){
                    city.setText(siteDelicInfo.getCity());
                    temperature.setText(siteDelicInfo.getTemperature());
                    cond.setText(siteDelicInfo.getCond());
                    max.setText(siteDelicInfo.getMax());
                    dir.setText(siteDelicInfo.getDir());
                    currentstorageCapacity.setText(siteDelicInfo.getCurrentstorageCapacity()+"KWH");
                    currentPower.setText(siteDelicInfo.getCurrentPower()+"KW");
                    allSaveElectricity.setText(siteDelicInfo.getAllSaveElectricity()+"KWH");
                    aveSaveElectricity.setText(siteDelicInfo.getAveSaveElectricity()+"KWH");
                    allSaveMoney.setText(siteDelicInfo.getAllSaveMoney()+"¥");
                    aveSaveMoney.setText(siteDelicInfo.getAveSaveMoney()+"¥");
                    allemissions.setText(siteDelicInfo.getAllemissions()+"t");
                    aveemissions.setText(siteDelicInfo.getAveemissions()+"t");
                    statusTime.setText(siteDelicInfo.getStatusTime());
                    if (siteDelicInfo.getStatus()==1){
                        status.setText("外电正常");
                    }else if (siteDelicInfo.getStatus()==2){
                        rltime.setVisibility(View.VISIBLE);
                        status.setText("ups供电");
                    }else if (siteDelicInfo.getStatus()==3){
                        rltime.setVisibility(View.VISIBLE);
                        status.setText("负载部分断电");
                    }else if (siteDelicInfo.getStatus()==4){
                        rltime.setVisibility(View.VISIBLE);
                        status.setText("负载全部断电");
                    }
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
                params.put("cityId",mapSiteInfo.getCITY());
                params.put("siteId",mapSiteInfo.getId());
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }
}
