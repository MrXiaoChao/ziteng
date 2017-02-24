package com.example.john.ziteng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.john.ziteng.R;
import com.example.john.ziteng.application.MyApplication;
import com.example.john.ziteng.domain.Warn;
import com.example.john.ziteng.domain.Warn1;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;
import com.example.john.ziteng.utils.SPUtils;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

/**
 * 提醒设置
 * Created by john on 2016/3/25.
 */
public class WarnActivity extends Activity implements View.OnClickListener {

    private ImageView fanhui;
    private TextView save;
    private ToggleButton duanxi;
    private ToggleButton email;
    private String mPhone;
    private int emailStatus;
    private int noteStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warn);
        mPhone = (String) SPUtils.get(WarnActivity.this, "phone", "");
        initview();
        getDateFromService();

    }

    //提醒设置初始状态
    private void getDateFromService() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.Warm, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Warn warn = PaseJson.PaseWarn(s);
                if (warn != null) {
                    if (warn.isSuccess()) {
                        if (warn.getObj() == 0) {
                            duanxi.setChecked(false);
                            email.setChecked(false);
                            return;
                        } else if (warn.getObj() == 1) {
                            duanxi.setChecked(false);
                            email.setChecked(true);
                            return;
                        } else if (warn.getObj() == 2) {
                            duanxi.setChecked(true);
                            email.setChecked(false);
                            return;
                        } else if (warn.getObj() == 3) {
                            duanxi.setChecked(true);
                            email.setChecked(true);
                            return;
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("data.loginname", mPhone);
                return params;
            }
        };
        request.setTag("Warm");
        MyApplication.getHttpQueue().add(request);
    }

    private void initview() {
        fanhui = (ImageView) findViewById(R.id.warn_fanhui);
        save = (TextView) findViewById(R.id.tv_save);
        duanxi = (ToggleButton) findViewById(R.id.tb_duanxin);
        email = (ToggleButton) findViewById(R.id.tb_email);
        save.setOnClickListener(this);
        fanhui.setOnClickListener(this);

        duanxi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    noteStatus = 1;
                    getDateFromService1();
                } else {
                    noteStatus = 0;
                    getDateFromService1();
                }
            }
        });
        email.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    emailStatus = 1;
                    getDateFromService1();
                } else {
                    emailStatus = 0;
                    getDateFromService1();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.warn_fanhui:
                finish();
                break;
            case R.id.tv_save:
//                getDateFromService1();
//                finish();
//                break;
        }

    }

    private void getDateFromService1() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.Warm1, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Warn1 warn1 = PaseJson.PaseWarn1(s);
                if (warn1 != null) {
                    if (warn1.isSuccess()) {
                        if (warn1.getObj() == 0) {
                            email.setChecked(false);
                            duanxi.setChecked(false);
                            return;
                        }
                        if (warn1.getObj() == 1) {
                            email.setChecked(true);
                            duanxi.setChecked(false);
                            return;
                        }
                        if (warn1.getObj() == 2) {
                            email.setChecked(false);
                            duanxi.setChecked(true);
                            return;
                        }
                        if (warn1.getObj() == 3) {
                            duanxi.setChecked(true);
                            email.setChecked(true);
                            return;
                        }
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> pamars = new HashMap<String, String>();
                pamars.put("data.loginname", mPhone);
                pamars.put("emailStatus", emailStatus + "");
                pamars.put("noteStatus", noteStatus + "");
                return pamars;
            }
        };
        request.setTag("Warn1");
        MyApplication.getHttpQueue().add(request);
    }

    //友盟统计
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

}
