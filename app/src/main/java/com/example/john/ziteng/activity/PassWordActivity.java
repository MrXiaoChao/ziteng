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
import com.example.john.ziteng.urlpath.Path;

import java.util.HashMap;
import java.util.Map;

/**
 * 修改密码
 * Created by john on 2016/4/13.
 */
public class PassWordActivity extends Activity implements View.OnClickListener {

    private ImageView back;
    private Button commit;
    private EditText password;
    private EditText qpassword;
    private String email;
    private String qpassword1;
    private String password1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_word);
        Intent intent=getIntent();
        email = intent.getStringExtra("email");
        initview();
    }

    private void initview() {
        qpassword = (EditText) findViewById(R.id.et_Qpassword1);
        password = (EditText) findViewById(R.id.et_password1);
        back = (ImageView) findViewById(R.id.mima_fanhui);
        commit = (Button) findViewById(R.id.tv_chang);
        back.setOnClickListener(this);
        commit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mima_fanhui:
                finish();
                break;
            case R.id.tv_chang:
                qpassword1 = qpassword.getText().toString().trim();
                password1 = password.getText().toString().trim();
                if (qpassword1.equals(password1)){
                    PostPassWord();
                }else {
                    Toast.makeText(PassWordActivity.this,getResources().getString(R.string.byz),Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void PostPassWord() {
        StringRequest request =new StringRequest(Request.Method.POST, Path.CpassWord, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                    finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parasms=new HashMap<>();
                parasms.put("email",email);
                parasms.put("password",qpassword1);
                return parasms;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }
}
