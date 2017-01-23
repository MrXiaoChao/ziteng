package com.example.john.ziteng.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.john.ziteng.R;
import com.example.john.ziteng.application.MyApplication;
import com.example.john.ziteng.domain.UserLogin;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;
import com.example.john.ziteng.utils.SPUtils;
import com.umeng.analytics.MobclickAgent;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


/**
 * 登录页面
 * Created by john on 2016/3/22.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private long backtime = 0;
    private CheckBox ck;
    private EditText etPhone;
    private EditText etPassword;
    private SharedPreferences sp;
    private String phone;
    private String password;
    private String msg;
    private TextView changpassword;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }


    private void initView() {
        changpassword = (TextView) findViewById(R.id.tv_password);
        changpassword.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        changpassword.getPaint().setAntiAlias(true);
        login = (Button) findViewById(R.id.tv_login);
        login.setOnClickListener(this);
        changpassword.setOnClickListener(this);


        ck = (CheckBox) findViewById(R.id.ck_password);
        etPhone = (EditText) findViewById(R.id.et_phone);
        etPassword = (EditText) findViewById(R.id.et_password);
        sp = getSharedPreferences("userInfo", MODE_PRIVATE);
        //记住密码
        if (sp.getBoolean("ISCHECK", false)) {
            ck.setChecked(true);
            etPhone.setText(sp.getString("PHONE", ""));
            etPassword.setText(sp.getString("PASSWORD", ""));
        }
        //checkBox的监听事件
        ck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ck.isChecked()) {
                    sp.edit().putBoolean("ISCHECK", true).commit();

                } else {
                    sp.edit().putBoolean("ISCHECK", false).commit();

                }

            }
        });

    }

    private void getDateFromSerce() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.LoginPath, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                UserLogin userLogin = PaseJson.PaseUseLogin(s);
                if (userLogin.isSuccess()) {
                    SPUtils.put(LoginActivity.this, "phone", phone);
                    SPUtils.put(LoginActivity.this,"level",userLogin.getLevel());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, userLogin.getMsg(), Toast.LENGTH_LONG).show();
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
                params.put("data.loginname", phone);
                params.put("data.pwd", password);
                return params;
            }
        };
        request.setTag("Login");
        MyApplication.getHttpQueue().add(request);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                phone = etPhone.getText().toString();
                password = etPassword.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.hmbwk), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.mmbwk), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (ck.isChecked()) {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("PHONE", phone);
                    editor.putString("PASSWORD", password);
                    editor.commit();
                }
//判断是否与服务器连接
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (isConnByHttp()){
                           runOnUiThread(new Runnable() {
                               @Override
                               public void run() {
                                  getDateFromSerce();
                               }
                           });
                        }else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(LoginActivity.this,getResources().getString(R.string.kxc), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
                break;
            case R.id.tv_password:
                Intent intent1 = new Intent(this, RegisterActivity.class);
                startActivity(intent1);
                break;

        }
    }


    //是否退出
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long t = System.currentTimeMillis();
            if (t - backtime > 3000) {
                Toast.makeText(this, getResources().getString(R.string.qddl), Toast.LENGTH_SHORT).show();
                backtime = t;
                return true;
            }
            KillAll();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    public boolean isConnByHttp() {
        boolean isConn = false;
        URL url;
        HttpURLConnection conn = null;
        try {
            url = new URL("http://123.57.251.129:8088/dem/phone/site/tdsitelist.jsp?zyw=1");
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(1000 * 8);
            if (conn.getResponseCode() == 200) {
                isConn = true;
            }
        } catch (MalformedURLException e) {

        } catch (IOException e) {
        } finally {
            conn.disconnect();
        }
        return isConn;
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
