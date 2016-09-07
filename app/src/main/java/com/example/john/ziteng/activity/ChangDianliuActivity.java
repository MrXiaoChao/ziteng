package com.example.john.ziteng.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.john.ziteng.R;
import com.example.john.ziteng.application.MyApplication;
import com.example.john.ziteng.domain.ChangDianliu;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;

import java.util.HashMap;
import java.util.Map;

/**
 * 设备控制电流修改
 * Created by john on 2016/4/13.
 */
public class ChangDianliuActivity extends Activity implements View.OnClickListener {

    private ImageView back;
    private TextView commit;
    private Intent intent;
    private String equipId;
    private String stationId;
    private String groupId;
    private String unitId;
    private String moduleId;
    private EditText dianliu;
    private ChangDianliu changDianliu;
    private Map<String, String> map;
    private String electric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chang_dianliu);
        getDateFromAc();
        initview();

    }

    private void SendDateToService() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.ChangDianliu, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                changDianliu = PaseJson.PaseDianliu(s);
                if (changDianliu.isSuccess()){
                    Intent intent1=new Intent(ChangDianliuActivity.this,DeviceModerActivity.class);
                    intent1.putExtra("electric",electric);
                    setResult(1,intent1);
                    Toast.makeText(ChangDianliuActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(ChangDianliuActivity.this,"修改失败",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("stationId",stationId);
                params.put("groupId",groupId);
                params.put("equipId",equipId);
                params.put("unitId", unitId);
                //拼接了一个map参数发送至服务器
                params.put("modules", String.valueOf(map));
                return params;
            }
        };
        request.setTag("ChangDianliu");
        MyApplication.getHttpQueue().add(request);
    }

    private void getDateFromAc() {
        intent = getIntent();
        equipId = intent.getStringExtra("equipId");
        stationId = intent.getStringExtra("stationId");
        groupId = intent.getStringExtra("groupId");
        unitId = intent.getStringExtra("unitId");
        moduleId = intent.getStringExtra("moduleId");
    }

    private void initview() {
        back = (ImageView) findViewById(R.id.dianliu_fanhui);
        commit = (TextView) findViewById(R.id.tv_commit);
        dianliu = (EditText) findViewById(R.id.et_dianliu);
        back.setOnClickListener(this);
        commit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dianliu_fanhui:
                finish();
                break;
            case R.id.tv_commit:
                electric = dianliu.getText().toString().trim();
                map = new HashMap<>();
                map.put("moduleid", moduleId);
                map.put("electricCurrent", electric);
                SendDateToService();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getHttpQueue().cancelAll("ChangDianliu");
    }
}
