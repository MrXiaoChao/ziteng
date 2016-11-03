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
import com.example.john.ziteng.domain.DeviceInfo;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;

import java.util.HashMap;
import java.util.Map;

/**
 * 设备基本信息
 * Created by john on 2016/5/25.
 */
public class DeviceInfoActivity extends BaseActivity {

    private TextView mc;
    private TextView gl;
    private TextView dy;
    private TextView cn;
    private TextView bs;
    private String equipId;
    private DeviceInfo deviceInfo;
    private ImageView banck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deviceinfo);
        Intent intent=getIntent();
        equipId = intent.getStringExtra("equipId");
        initview();
        getdatafromService();
    }

    private void getdatafromService() {
        StringRequest request =new StringRequest(Request.Method.POST, Path.DeviceInfo, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                deviceInfo = PaseJson.PaseInfo(s);
                mc.setText("设备-"+deviceInfo.getEquip_id().substring(5,7));
                gl.setText(String.valueOf(deviceInfo.getPower())+"KW");
                dy.setText(String.valueOf(deviceInfo.getVoltage())+"mV");
                cn.setText(String.valueOf(deviceInfo.getStored_energy())+"KWH");
                bs.setText(deviceInfo.getDeploy_time());
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
        banck = (ImageView) findViewById(R.id.info_fanhui);
        mc = (TextView) findViewById(R.id.tv_mc);
        gl = (TextView) findViewById(R.id.tv_gl);
        dy = (TextView) findViewById(R.id.tv_dy);
        cn = (TextView) findViewById(R.id.tv_cn);
        bs = (TextView) findViewById(R.id.tv_bs);
        banck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
