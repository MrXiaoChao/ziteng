package com.example.john.ziteng.activity;


import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.example.john.ziteng.domain.BatteryList;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;
import com.example.john.ziteng.utils.SPUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.HashMap;
import java.util.Map;

/**
 * 电池列表
 * Created by john on 2016/5/17.
 */
public class BatteryListActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back;
    private BatteryList batteryList;
    private TextView unitxinxi;
    private TextView wendu;
    private TextView dianliu;
    private TextView dianya;
    private LinearLayout llunit;
    private RelativeLayout rlfx;
    private ImageView fx;
    private String batteryId;
    private RelativeLayout rl_history;
    private TextView tv_zhuantai;
    private TextView tv_soc;
    private TextView tv_soh;
    private PullToRefreshScrollView scrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_list);
        batteryId = String.valueOf(SPUtils.get(BatteryListActivity.this, "batteryId", ""));
        initview();
        getDataFromService();
    }

    private void getDataFromService() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.BatteryList, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                batteryList = PaseJson.PaseBatteryList(s);
                if (batteryList != null) {
                    unitxinxi.setText(batteryList.getBatteryid());
                    dianliu.setText(batteryList.getCurrent() + "mA");
                    dianya.setText(batteryList.getVoltage() + "mV");
                    wendu.setText(batteryList.getTemperature() + "°C");
                    tv_zhuantai.setText(batteryList.getState());
                    tv_soc.setText(batteryList.getSoc());
                    tv_soh.setText(batteryList.getSoh());
                }
                scrollView.onRefreshComplete();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("batteryid", batteryId);
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }


    private void initview() {
        scrollView = (PullToRefreshScrollView) findViewById(R.id.scrollView);
        back = (ImageView) findViewById(R.id.unit_fanhui);
        tv_soh = (TextView) findViewById(R.id.tv_soh);
        tv_soc = (TextView) findViewById(R.id.tv_soc);
        tv_zhuantai = (TextView) findViewById(R.id.tv_zhuantai);
        unitxinxi = (TextView) findViewById(R.id.unit_xinxi);
        wendu = (TextView) findViewById(R.id.tv_wendu);
        dianliu = (TextView) findViewById(R.id.tv_dianliu);
        dianya = (TextView) findViewById(R.id.tv_dianya);
        llunit = (LinearLayout) findViewById(R.id.ll_unit);
        rlfx = (RelativeLayout) findViewById(R.id.rl_fx);
        fx = (ImageView) findViewById(R.id.image_fx);
        fx.setImageDrawable(getResources().getDrawable(R.mipmap.down));
        rl_history = (RelativeLayout) findViewById(R.id.rl_history);
        rl_history.setOnClickListener(this);
        back.setOnClickListener(this);
        rlfx.setOnClickListener(this);
        scrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                String label = DateUtils.formatDateTime(
                        BatteryListActivity.this,
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

    private boolean isselect;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.unit_fanhui:
                finish();
                break;

            case R.id.rl_fx:
                if (!isselect) {
                    fx.setImageDrawable(getResources().getDrawable(R.mipmap.buleyou));
                    llunit.setVisibility(View.GONE);
                    isselect = true;
                } else {
                    fx.setImageDrawable(getResources().getDrawable(R.mipmap.down));
                    llunit.setVisibility(View.VISIBLE);
                    isselect = false;
                }
                break;
            case R.id.rl_history:
                Intent intent =new Intent(BatteryListActivity.this,BatteryActivity.class);
                startActivity(intent);
                break;
        }
    }

}
