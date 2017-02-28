package com.example.john.ziteng.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
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
import com.example.john.ziteng.utils.ComUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luochao on 2016/2/28.
 */

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.phone_fanhui)
    ImageView phoneFanhui;
    @BindView(R.id.register_title)
    TextView registerTitle;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.btn_huoqu)
    Button btnHuoqu;
    @BindView(R.id.et_yzm)
    EditText etYzm;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_confirmpassword)
    EditText etConfirmpassword;
    @BindView(R.id.btn_register)
    Button btnRegister;
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
    private String phone;
    private String YzmUrl="http://123.57.251.129:8088/dem/client/clientinter!doNotNeedSessionAndSecurity_identifyCode.do?";
    private String RegisterUrl="http://123.57.251.129:8088/dem/client/clientinter!doNotNeedSessionAndSecurity_verifyIdentifyCode.do?";
    private String code;
    private String password;
    private String confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.phone_fanhui, R.id.btn_huoqu, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.phone_fanhui:
                finish();
                break;
            case R.id.btn_huoqu:
                phone = etPhone.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(RegisterActivity.this, getResources().getString(R.string.hmbwk), Toast.LENGTH_SHORT).show();
                } else {
                    if (!ComUtils.isMobileNum(phone)) {
                        Toast.makeText(RegisterActivity.this, getResources().getString(R.string.sjbhf), Toast.LENGTH_SHORT).show();
                    } else {
                        //获取验证码
                        timer.start();
                        getYanzm();

                    }
                }
                break;
            case R.id.btn_register:
                code = etYzm.getText().toString().trim();
                password = etPassword.getText().toString().trim();
                confirm = etConfirmpassword.getText().toString().trim();
                getRegister();
                break;
        }
    }

    //获取验证码
    private void getYanzm() {
        StringRequest request = new StringRequest(Request.Method.POST, YzmUrl, new Response.Listener<String>() {
            private String msg;
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject jsonObject= new JSONObject(s);
                    msg = jsonObject.getString("msg");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("phoneNumber", phone);
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }
    //注册按钮
    private void getRegister(){
        StringRequest request =new StringRequest(Request.Method.POST, RegisterUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject jsonObject=new JSONObject(s);
                    boolean success=jsonObject.getBoolean("success");
                    if (success){
                        Toast.makeText(RegisterActivity.this,getResources().getString(R.string.registersuccess),Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("phoneNumber",phone);
                params.put("newPWD",password);
                params.put("name","");
                params.put("code",code);
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }
}
