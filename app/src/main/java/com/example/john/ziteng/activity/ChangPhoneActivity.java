package com.example.john.ziteng.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.john.ziteng.domain.ChangPhone;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;
import com.example.john.ziteng.utils.ComUtils;
import com.example.john.ziteng.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 修改手机号码
 * Created by john on 2016/3/26.
 */
public class ChangPhoneActivity extends Activity implements View.OnClickListener {

    private ImageView fanhui;
    private EditText password;
    private EditText password1;
    private Button btnchang;
    private String mPhone;
    private String newPhone;
    private String newPhone1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chang_pass);
        initview();
        mPhone = (String) SPUtils.get(ChangPhoneActivity.this, "phone", "");
    }

    private void initview() {
        fanhui = (ImageView) findViewById(R.id.mima_fanhui);
        password = (EditText) findViewById(R.id.et_chang_password);
        password1 = (EditText) findViewById(R.id.et_queren_password);
        btnchang = (Button) findViewById(R.id.btn_chang);

        fanhui.setOnClickListener(this);
        btnchang.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mima_fanhui:
                finish();
                break;
            case R.id.btn_chang:
                newPhone = password.getText().toString().trim();
                newPhone1 = password1.getText().toString().trim();
                if (newPhone.isEmpty()) {
                    Toast.makeText(ChangPhoneActivity.this, getResources().getString(R.string.xsjhm), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!ComUtils.isMobileNum(newPhone)) {
                    Toast.makeText(ChangPhoneActivity.this, getResources().getString(R.string.sjbhf), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (newPhone1.isEmpty()) {
                    Toast.makeText(ChangPhoneActivity.this, getResources().getString(R.string.qrxsh), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (newPhone.equals(newPhone1)) {
                    if (mPhone != null) {
                        getDateFromService();
                    } else {
                        Toast.makeText(ChangPhoneActivity.this, getResources().getString(R.string.byz), Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else {
                    Toast.makeText(ChangPhoneActivity.this,getResources().getString(R.string.byz1), Toast.LENGTH_SHORT).show();
                    return;
                }

                break;
        }
    }

    private void getDateFromService() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.ChangPhone, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                ChangPhone changPhone = PaseJson.PasePhone(s);
                if (changPhone.getSuccess()) {
                    Intent intent = new Intent(ChangPhoneActivity.this, PersonalActivity.class);
                    intent.putExtra("newPhone", changPhone.getObj());
                    setResult(1, intent);
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
                Map<String, String> params = new HashMap<String, String>();
                params.put("data.loginname", mPhone);
                params.put("data.phone", newPhone);
                return params;
            }
        };
        request.setTag("newPhone");
        MyApplication.getHttpQueue().add(request);
    }
}
