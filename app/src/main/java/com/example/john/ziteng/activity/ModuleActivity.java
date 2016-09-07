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
import com.example.john.ziteng.adapter.MoudleListAdapter;
import com.example.john.ziteng.application.MyApplication;
import com.example.john.ziteng.domain.BatteryList;
import com.example.john.ziteng.domain.MoudleList;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;
import com.example.john.ziteng.utils.SPUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 模块列表
 * Created by john on 2016/5/17.
 */
public class ModuleActivity extends BaseActivity implements View.OnClickListener {
    private ImageView back;
    private MoudleList moudleList;
    private String moudleid;
    private TextView unitxinxi;
    private TextView wendu;
    private TextView dianliu;
    private TextView dianya;
    private LinearLayout llunit;
    private RelativeLayout rlfx;
    private ImageView fx;
    private ListView listview_modul;
    private PullToRefreshScrollView scrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);
        Intent intent = getIntent();
        moudleid = intent.getStringExtra("moudleid");
        initview();
        getDataFromService();
    }

    private void getDataFromService() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.ModuleList, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                moudleList = PaseJson.PaseMoudlelist(s);
                if (moudleList != null) {
                    listview_modul.setAdapter(new MoudleListAdapter(ModuleActivity.this, (ArrayList<MoudleList.BatterylistBean>) moudleList.getBatterylist()));
                    unitxinxi.setText(moudleList.getMoudleId());
                    dianliu.setText(moudleList.getCurrent()+"mA");
                    dianya.setText(moudleList.getVoltage()+"mV");
                    wendu.setText(moudleList.getTemperature()+"°C");
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
                params.put("moudleid",moudleid);
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }



    private void initview() {
        scrollView = (PullToRefreshScrollView) findViewById(R.id.scrollView);
        back = (ImageView) findViewById(R.id.unit_fanhui);
        unitxinxi = (TextView) findViewById(R.id.unit_xinxi);
        wendu = (TextView) findViewById(R.id.tv_wendu);
        dianliu = (TextView) findViewById(R.id.tv_dianliu);
        dianya = (TextView) findViewById(R.id.tv_dianya);
        llunit = (LinearLayout) findViewById(R.id.ll_unit);
        rlfx = (RelativeLayout) findViewById(R.id.rl_fx);
        fx = (ImageView) findViewById(R.id.image_fx);
        fx.setImageDrawable(getResources().getDrawable(R.mipmap.down));
        listview_modul = (ListView) findViewById(R.id.listview_modul);

        back.setOnClickListener(this);
        rlfx.setOnClickListener(this);
        listview_modul.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String batteryId = String.valueOf(SPUtils.put(ModuleActivity.this, "batteryId", moudleList.getBatterylist().get(position).getBatteryId()));
                Intent intent = new Intent(ModuleActivity.this, BatteryListActivity.class);
                startActivity(intent);
            }
        });
        scrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                String label = DateUtils.formatDateTime(
                        ModuleActivity.this,
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
        switch (v.getId()){
            case R.id.unit_fanhui:
                finish();
                break;

            case R.id.rl_fx:
                if (!isselect){
                    fx.setImageDrawable(getResources().getDrawable(R.mipmap.buleyou));
                    llunit.setVisibility(View.GONE);
                    isselect=true;
                }else {
                    fx.setImageDrawable(getResources().getDrawable(R.mipmap.down));
                    llunit.setVisibility(View.VISIBLE);
                    isselect=false;
                }
                break;
        }
    }

}


