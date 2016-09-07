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
import com.example.john.ziteng.domain.ChangEmail;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;
import com.example.john.ziteng.utils.ComUtils;
import com.example.john.ziteng.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by john on 2016/3/26.
 */
public class ChangEmailActivity extends Activity implements View.OnClickListener {

    private ImageView fanhui;
    private TextView baocun;
    private EditText email;
    private String mPhone;
    private String newEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chang_emain);
        mPhone = (String) SPUtils.get(ChangEmailActivity.this, "phone", "");
        initView();
    }

    private void initView() {
        fanhui = (ImageView) findViewById(R.id.email_fanhui);
        baocun = (TextView) findViewById(R.id.tv_baocun);
        email = (EditText) findViewById(R.id.et_email);
        fanhui.setOnClickListener(this);
        baocun.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.email_fanhui:
                finish();
                break;
            case R.id.tv_baocun:
                newEmail = email.getText().toString().trim();
                if (newEmail.isEmpty()) {
                    Toast.makeText(ChangEmailActivity.this, "新的Email不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!ComUtils.isEmail(newEmail)) {
                    Toast.makeText(ChangEmailActivity.this, "请正确填写你的Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                getDateFromService();
                break;

        }

    }

    private void getDateFromService() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.ChangEmail, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                ChangEmail changEmail = PaseJson.PaseEmail(s);
                if (changEmail.isSuccess()) {
                    Intent intent = new Intent(ChangEmailActivity.this, PersonalActivity.class);
                    intent.putExtra("email", changEmail.getObj());
                    setResult(2, intent);
                    finish();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> Params = new HashMap<String, String>();
                Params.put("data.loginname", mPhone);
                Params.put("data.email", newEmail);
                return Params;
            }
        };
        request.setTag("ChangEmail");
        MyApplication.getHttpQueue().add(request);
    }
}
