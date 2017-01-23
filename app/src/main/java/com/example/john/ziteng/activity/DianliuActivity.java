package com.example.john.ziteng.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.example.john.ziteng.urlpath.Path;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by john on 2016/12/29.
 */
public class DianliuActivity extends BaseActivity {
    @BindView(R.id.device_control_fanhui)
    ImageView deviceControlFanhui;
    @BindView(R.id.device_control_title)
    TextView deviceControlTitle;
    @BindView(R.id.btn_shezhi)
    Button btnShezhi;
    @BindView(R.id.ed_dianliu)
    EditText edDianliu;
    private String equipId;
    private String siteId;
    private String groupId;
    private String dianliu;
    private String number;
    private String unitId;
    private String moduleId;
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dianliu);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        number = intent.getStringExtra("number");
        equipId = intent.getStringExtra("equipId");
        siteId = intent.getStringExtra("siteId");
        groupId = intent.getStringExtra("groupId");
        unitId = intent.getStringExtra("unitId");
        moduleId = intent.getStringExtra("moduleId");
    }

    @OnClick({R.id.device_control_fanhui, R.id.btn_shezhi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.device_control_fanhui:
                finish();
                break;
            case R.id.btn_shezhi:
                dianliu = edDianliu.getText().toString().trim();
                //模块电流
                List<JSONObject> stringList = new ArrayList<>();
                JSONObject object=new JSONObject();
                try {
                    object.put("moduleCurrent",dianliu);
                    object.put("moduleId",moduleId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                stringList.add(object);
                str = String.valueOf(stringList);
                if (dianliu.isEmpty()) {
                    Toast.makeText(this, getResources().getString(R.string.srdl), Toast.LENGTH_SHORT).show();
                } else {
                    showDialog(number);
                }
                break;
        }
    }

    private void showDialog(final String number) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.ts))
                .setMessage(getResources().getString(R.string.qrts))
                .setPositiveButton(getResources().getString(R.string.confirm), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (number.equals("1")) {//设备电流修改
                            sendDlToService(dianliu);
                        }else if (number.equals("3")){//模块电流修改
                            sendMKdl(dianliu);
                        }else {//单元电流修改
                            sendDLToService(dianliu);
                        }
                        dialog.dismiss();
                        finish();
                    }
                })
                .setNegativeButton(getResources().getString(R.string.qx1), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
        builder.setCancelable(false);
        builder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void sendDlToService(final String electric) {
        StringRequest request = new StringRequest(Request.Method.POST, Path.dlCortol, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(DianliuActivity.this, getResources().getString(R.string.xgcg), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("equipId", equipId);
                params.put("siteId", siteId);
                params.put("groupId", groupId);
                params.put("electric", electric);
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }


    private void sendDLToService(final String electric) {
        StringRequest request = new StringRequest(Request.Method.POST, Path.UdlK, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(DianliuActivity.this, getResources().getString(R.string.xgcg), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("equipId", equipId);
                params.put("siteId", siteId);
                params.put("groupId", groupId);
                params.put("unitId", unitId);
                params.put("electric", electric);
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }
    private void sendMKdl(String moduleCurrent){
        StringRequest request =new StringRequest(Request.Method.POST, Path.MDXG, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
            Toast.makeText(DianliuActivity.this,getResources().getString(R.string.xgcg),Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("equipId", equipId);
                params.put("siteId", siteId);
                params.put("groupId", groupId);
                params.put("unitId", unitId);
                params.put("jsonStr",str);
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }
}
