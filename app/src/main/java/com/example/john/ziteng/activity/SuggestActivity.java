package com.example.john.ziteng.activity;

import android.app.Activity;
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
import com.example.john.ziteng.domain.Commite;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;
import com.example.john.ziteng.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 意见反馈
 * Created by john on 2016/3/25.
 */
public class SuggestActivity extends Activity implements View.OnClickListener {

    private ImageView fanhui;
    private TextView commit;
    private EditText content;
    private String commitContent;
    private String mPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPhone = (String) SPUtils.get(SuggestActivity.this, "phone", "");
        setContentView(R.layout.activity_suggest);
        initview();
    }

    private void initview() {
        content = (EditText) findViewById(R.id.et_content);
        commit = (TextView) findViewById(R.id.commit);
        fanhui = (ImageView) findViewById(R.id.suggest_fanhui);
        commit.setOnClickListener(this);
        fanhui.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.commit://提交
                commitContent = content.getText().toString().trim();
                sendDateToService();
                break;
            case R.id.suggest_fanhui://返回
                finish();
                break;
        }
    }

    private void sendDateToService() {
        StringRequest request =new StringRequest(Request.Method.POST, Path.Commit, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Commite commite = PaseJson.PaseCommit(s);
                if (commite.isSuccess()){
                    Toast.makeText(SuggestActivity.this,commite.getMsg(),Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SuggestActivity.this,commite.getMsg(),Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parsma=new HashMap<String,String>();
                parsma.put("loginname",mPhone);
                parsma.put("content",commitContent);
                return parsma;
            }
        };
        request.setTag("Suggest");
        MyApplication.getHttpQueue().add(request);
    }
}
