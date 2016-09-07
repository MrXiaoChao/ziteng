package com.example.john.ziteng.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.john.ziteng.R;
import com.example.john.ziteng.application.MyApplication;
import com.example.john.ziteng.domain.DeviceCanshu;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;

import java.util.HashMap;
import java.util.Map;

/**
 * 设备基本参数
 * Created by john on 2016/5/25.
 */
public class DeviceParameActivity extends BaseActivity{

    private TextView wc;
    private TextView wd;
    private TextView dl;
    private TextView dy;
    private TextView mc;
    private String equipId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deviceparams);
        Intent intent=getIntent();
        equipId = intent.getStringExtra("equipId");
        initview();
        getDataFromService();
    }

    private void getDataFromService() {
        StringRequest request=new StringRequest(Request.Method.POST, Path.DeviceCanshu, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                DeviceCanshu deviceCanshu = PaseJson.PaseCanshu(s);
                mc.setText(deviceCanshu.getEquipId());
                dy.setText(String.valueOf(deviceCanshu.getVoltage())+"mV");
                dl.setText(String.valueOf(deviceCanshu.getElectricCurrent())+"mA");
                wd.setText(String.valueOf(deviceCanshu.getTemperature())+"°C");
                wc.setText(String.valueOf(deviceCanshu.getHoldTime())+"h");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("equipId",equipId);
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }

    private void initview() {
        ImageView back= (ImageView) findViewById(R.id.canshu_fanhui);
        mc = (TextView) findViewById(R.id.tv_mc);
        dy = (TextView) findViewById(R.id.tv_dy);
        dl = (TextView) findViewById(R.id.tv_dl);
        wd = (TextView) findViewById(R.id.tv_wd);
        wc = (TextView) findViewById(R.id.tv_wc);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
