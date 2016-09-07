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
import com.example.john.ziteng.adapter.UnitlistAdapter;
import com.example.john.ziteng.application.MyApplication;
import com.example.john.ziteng.domain.UnitList;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;
import com.example.john.ziteng.utils.SPUtils;
import com.example.john.ziteng.view.MyListview;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 单元列表
 * Created by john on 2016/5/17.
 */
public class UnitActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back;
    private UnitList unitList;
    private String unitId;
    private TextView unitxinxi;
    private TextView zhuantai;
    private TextView dianliu;
    private TextView dianya;
    private LinearLayout llunit;
    private RelativeLayout rlfx;
    private ImageView fx;
    private MyListview listview_unit;
    private PullToRefreshScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);
        Intent intent = getIntent();
        unitId = intent.getStringExtra("unitId");
        initview();
        getDataFromService();
    }

    private void getDataFromService() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.UintList, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                unitList = PaseJson.PaseUnitlist(s);
                if (unitList != null) {
                    listview_unit.setAdapter(new UnitlistAdapter(UnitActivity.this, (ArrayList<UnitList.ModulelistBean>) unitList.getModulelist()));
                    unitxinxi.setText(unitList.getUnitId());
                    dianliu.setText(unitList.getCurrent()+"mA");
                    dianya.setText(unitList.getVoltage()+"mV");
                    switch (unitList.getStatus()){
                        case 1:
                            zhuantai.setText("空闲");
                            break;
                        case 2:
                            zhuantai.setText("放电");
                            break;
                        case 3:
                            zhuantai.setText("充电");
                            break;
                        case 4:
                            zhuantai.setText("报警");
                            break;
                        case 5:
                            zhuantai.setText("超级电容充电");
                            break;
                        case 6:
                            zhuantai.setText("停机前等待");
                            break;
                        case 7:
                            zhuantai.setText("停机");
                            break;
                        case 8:
                            zhuantai.setText("告警");
                            break;
                    }
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
                params.put("unitId",unitId);
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }



    private void initview() {
        back = (ImageView) findViewById(R.id.unit_fanhui);
        scrollView = (PullToRefreshScrollView) findViewById(R.id.scrollView);
        unitxinxi = (TextView) findViewById(R.id.unit_xinxi);
        zhuantai = (TextView) findViewById(R.id.tv_zhuantai);
        dianliu = (TextView) findViewById(R.id.tv_dianliu);
        dianya = (TextView) findViewById(R.id.tv_dianya);
        llunit = (LinearLayout) findViewById(R.id.ll_unit);
        rlfx = (RelativeLayout) findViewById(R.id.rl_fx);
        fx = (ImageView) findViewById(R.id.image_fx);
        fx.setImageDrawable(getResources().getDrawable(R.mipmap.down));
        listview_unit = (MyListview) findViewById(R.id.listview_unit);
        back.setOnClickListener(this);
        rlfx.setOnClickListener(this);
        listview_unit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String moduleId = String.valueOf(SPUtils.put(UnitActivity.this, "moduleId", unitList.getModulelist().get(position).getMoudleId()));
                Intent intent = new Intent(UnitActivity.this, ModuleActivity.class);
                intent.putExtra("moudleid", unitList.getModulelist().get(position).getMoudleId());
                startActivity(intent);

            }
        });
        scrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                String label = DateUtils.formatDateTime(
                        UnitActivity.this,
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
