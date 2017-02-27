package com.example.john.ziteng.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.john.ziteng.urlpath.Path;
import com.example.john.ziteng.utils.SPUtils;
import com.example.john.ziteng.view.MyListview;
import com.google.gson.Gson;
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
    private TextView name;
    private RelativeLayout lssj;
    private String equip_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);
        Intent intent = getIntent();
        unitId = intent.getStringExtra("unitId");
        equip_id = intent.getStringExtra("equip_id");
        initview();
        getDataFromService();
    }

    private void getDataFromService() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.UintList, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Gson gson = new Gson();
                unitList = gson.fromJson(s, UnitList.class);
                if (unitList != null) {
                    listview_unit.setAdapter(new UnitlistAdapter(UnitActivity.this, (ArrayList<UnitList.ModulelistBean>) unitList.getModulelist()));
                    name.setText(getResources().getString(R.string.dya) + "-" + unitId.substring(5, 7));
                    unitxinxi.setText(getResources().getString(R.string.dyxx));
                    dianliu.setText(unitList.getCurrent() + " A");
                    dianya.setText(unitList.getVoltage() + " V");
                        if (unitList.getStatus().equals("空闲")) {
                            zhuantai.setText(getResources().getString(R.string.kx));
                        } else if (unitList.getStatus().equals("放电")) {
                            zhuantai.setText(getResources().getString(R.string.fd));
                        } else if (unitList.getStatus().equals("充电")) {
                            zhuantai.setText(getResources().getString(R.string.cd));
                        } else if (unitList.getStatus().equals("告警")) {
                            zhuantai.setText(getResources().getString(R.string.gj));
                        } else if (unitList.getStatus().equals("停机")) {
                            zhuantai.setText(getResources().getString(R.string.tj));
                        }else {
                            zhuantai.setText(getResources().getString(R.string.gza));
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
                params.put("unitId", unitId);
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }


    private void initview() {
        lssj = (RelativeLayout) findViewById(R.id.lssj);
        name = (TextView) findViewById(R.id.unit_title);
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
        lssj.setOnClickListener(this);
        back.setOnClickListener(this);
        rlfx.setOnClickListener(this);
        listview_unit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String moduleId = String.valueOf(SPUtils.put(UnitActivity.this, "moduleId", unitList.getModulelist().get(position).getMoudleId()));
                Intent intent = new Intent(UnitActivity.this, ModuleActivity.class);
                intent.putExtra("moudleid", unitList.getModulelist().get(position).getMoudleId());
                intent.putExtra("unitId", unitList.getUnitId());
                intent.putExtra("equip_id", equip_id);
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
            case R.id.lssj:
                Intent intent = new Intent(this, HistoricalActivity.class);
                intent.putExtra("number", 1);
                intent.putExtra("checknumber", 1);
                intent.putExtra("equip_id", equip_id);
                startActivity(intent);
                break;
        }
    }
}
