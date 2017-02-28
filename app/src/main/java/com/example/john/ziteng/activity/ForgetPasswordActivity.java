package com.example.john.ziteng.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.john.ziteng.R;
import com.example.john.ziteng.application.MyApplication;
import com.example.john.ziteng.domain.CheckYzm;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;
import com.example.john.ziteng.utils.ComUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 忘记密码
 * Created by john on 2016/3/22.
 */
public class ForgetPasswordActivity extends BaseActivity implements View.OnClickListener {

    private EditText etEmail;
    private Button btnHuoqu;
    private EditText etYanzm;
    private EditText etPassWord;
    private EditText etQpassWord;
    private Button btnRegister;
    private long backtime = 0;
    private String email;
    private String yzm;
    private CountDownTimer timer = new CountDownTimer(30000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            btnHuoqu.setEnabled(false);
            btnHuoqu.setText((millisUntilFinished / 1000) + "秒后重发");

        }

        @Override
        public void onFinish() {
            btnHuoqu.setEnabled(true);
            btnHuoqu.setText(getResources().getString(R.string.hqyzm));
        }
    };


    private void GetYzmFromService() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.YZM, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(ForgetPasswordActivity.this,getResources().getString(R.string.cs), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(ForgetPasswordActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }

    private ImageView back;
    private Button queren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        initView();
    }


    private void initView() {
        back = (ImageView) findViewById(R.id.phone_fanhui);
        etEmail = (EditText) findViewById(R.id.et_email_1);
        btnHuoqu = (Button) findViewById(R.id.btn_huoqu);
        etYanzm = (EditText) findViewById(R.id.et_yanzm);
        queren = (Button) findViewById(R.id.queren);
        queren.setOnClickListener(this);
        back.setOnClickListener(this);
        btnHuoqu.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_huoqu:
                //获取验证码
                email = etEmail.getText().toString().trim();
                if (ComUtils.isEmail(email)) {
                    GetYzmFromService();
                } else {
                    Toast.makeText(ForgetPasswordActivity.this, getResources().getString(R.string.zqxy), Toast.LENGTH_SHORT).show();
                    return;
                }
                timer.start();
                break;
            case R.id.phone_fanhui:
                finish();
                break;
            case R.id.queren:
                yzm = etYanzm.getText().toString().trim();
                if (yzm == null) {
                    Toast.makeText(ForgetPasswordActivity.this, getResources().getString(R.string.qsryzm), Toast.LENGTH_SHORT).show();
                } else {
                    ComPareYZM();
                }
                break;
        }
    }

    //校验验证码
    private void ComPareYZM() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.YZyzm, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                CheckYzm checkYzm = PaseJson.PaseYzm(s);
                if (!checkYzm.isSuccess()) {
                    Intent intent = new Intent(ForgetPasswordActivity.this, PassWordActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(ForgetPasswordActivity.this, getResources().getString(R.string.adjk), Toast.LENGTH_SHORT).show();
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
                params.put("email", email);
                params.put("num", yzm);
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }
}
