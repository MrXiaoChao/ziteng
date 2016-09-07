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
import com.example.john.ziteng.domain.DeviceState;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;

import java.util.HashMap;
import java.util.Map;

/**
 * 设备状态
 * Created by john on 2016/5/25.
 */
public class DeviceStateActivity extends BaseActivity {

    private TextView state;
    private TextView time;
    private String equipId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devicestate);
        Intent intent = getIntent();
        equipId = intent.getStringExtra("equipId");
        initview();
        getDataFromService();
    }

    private void getDataFromService() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.DeviceState, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                DeviceState deviceState = PaseJson.PaseState(s);
                if (deviceState.getState().equals("IDLE")) {
                    state.setText("空闲");
                }else if (deviceState.getState().equals("DISCHARGING")){
                    state.setText("放电");
                }else if (deviceState.getState().equals("CHARGING")){
                    state.setText("充电");
                }else if (deviceState.getState().equals("WARNING")){
                    state.setText("告警");
                }else if (deviceState.getState().equals("STOP")){
                    state.setText("停机");
                }

                time.setText(deviceState.getTime());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("equipId", equipId);
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }

    private void initview() {
        ImageView back = (ImageView) findViewById(R.id.state_fanhui);
        state = (TextView) findViewById(R.id.tv_state);
        time = (TextView) findViewById(R.id.tv_time);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
