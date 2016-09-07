package com.example.john.ziteng.activity;

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
import com.example.john.ziteng.domain.ChangPassword;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;
import com.example.john.ziteng.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 修改设备控制密码
 * Created by john on 2016/3/25.
 */
public class ChangPasswordActivity2 extends BaseActivity implements View.OnClickListener {

    private ImageView fanhui;
    private EditText password;
    private EditText newpassword;
    private EditText newpassword2;
    private TextView baocun;
    private String passWordjiu;
    private String passWordnew;
    private String passWordnew2;
    private String mPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changpassword2);
        mPhone = (String) SPUtils.get(ChangPasswordActivity2.this, "phone", "");
        initview();
    }

    private void initview() {
        fanhui = (ImageView) findViewById(R.id.password_fanhui);
        baocun = (TextView) findViewById(R.id.tv_baocun);
        password = (EditText) findViewById(R.id.et_password_shebei);
        newpassword = (EditText) findViewById(R.id.et_new_password);
        newpassword2 = (EditText) findViewById(R.id.et_new_password2);

        fanhui.setOnClickListener(this);
        baocun.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.password_fanhui:
                finish();
                break;
            case R.id.tv_baocun:
                passWordjiu = password.getText().toString().trim();
                passWordnew = newpassword.getText().toString().trim();
                passWordnew2 = newpassword2.getText().toString().trim();
                if (passWordjiu.isEmpty()){
                    Toast.makeText(ChangPasswordActivity2.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!passWordnew.equals(passWordnew2)){
                    Toast.makeText(ChangPasswordActivity2.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
                getDateFromService();
                break;

        }
    }

    private void getDateFromService() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.CP, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                ChangPassword changPassword = PaseJson.PasePassWord(s);
                if (changPassword.getSuccess()) {
                    Toast.makeText(ChangPasswordActivity2.this,"修改密码成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ChangPasswordActivity2.this, changPassword.getMsg(), Toast.LENGTH_SHORT).show();
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
                params.put("newPWD", passWordnew2);
                params.put("oldPWD", passWordjiu);
                return params;
            }
        };
        request.setTag("changPassWord");
        MyApplication.getHttpQueue().add(request);
    }
}

