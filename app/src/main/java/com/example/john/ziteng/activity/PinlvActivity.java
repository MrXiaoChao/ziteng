package com.example.john.ziteng.activity;

import android.app.AlertDialog;
import android.content.Context;
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

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by john on 2016/12/29.
 */
public class PinlvActivity extends BaseActivity {
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
    private String pinglv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinlv);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        equipId = intent.getStringExtra("equipId");
        siteId = intent.getStringExtra("siteId");
        groupId = intent.getStringExtra("groupId");
    }

    @OnClick({R.id.device_control_fanhui, R.id.btn_shezhi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.device_control_fanhui:
                finish();
                break;
            case R.id.btn_shezhi:
                pinglv = edDianliu.getText().toString().trim();
                if (pinglv.isEmpty()){
                    Toast.makeText(this,getResources().getString(R.string.srcypl),Toast.LENGTH_SHORT).show();
                }else {
                    showDialog(this);
                }
                break;
        }
    }

    private void showDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.ts))
                .setMessage(getResources().getString(R.string.qrts))
                .setPositiveButton(getResources().getString(R.string.confirm), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SendPLToService(pinglv);
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
    private void SendPLToService(final String pl) {
        StringRequest request = new StringRequest(Request.Method.POST, Path.PL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(PinlvActivity.this, getResources().getString(R.string.xgcg), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("siteId", siteId);
                params.put("groupId", groupId);
                params.put("equipId", equipId);
                params.put("frequency", pl);
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }

}
