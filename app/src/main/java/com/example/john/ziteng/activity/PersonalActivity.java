package com.example.john.ziteng.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.john.ziteng.R;
import com.example.john.ziteng.application.MyApplication;
import com.example.john.ziteng.domain.Personal;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;
import com.example.john.ziteng.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 个人信息配置
 * Created by john on 2016/3/24.
 */
public class PersonalActivity extends BaseActivity implements View.OnClickListener {

    private ImageView fanhui;
    private TextView company;
    private TextView phone;
    private TextView email;
    private String mPhone;
    private Personal personal;
    private RelativeLayout changPhone;
    private RelativeLayout changEmail;
    private TextView loginName;
    private Button login;
    private TextView name;
    private TextView sex;
    private TextView zhiwu;
    private RelativeLayout changpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        mPhone = (String) SPUtils.get(PersonalActivity.this, "phone", "");
        initview();
        getDateFromServce();
    }


    private void getDateFromServce() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.PersonalPath, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                personal = PaseJson.PasePersonal(s);
                if (personal != null) {
                    loginName.setText(mPhone);
                    company.setText(personal.getCompany());
                    phone.setText(personal.getPhone());
                    email.setText(personal.getEmail());
                    name.setText(personal.getName());

                    if (getResources().getConfiguration().locale.getCountry().equals("CN")) {
                        switch (personal.getSex()) {
                            case 1:
                                sex.setText(getResources().getString(R.string.nan));
                                break;
                            case 2:
                                sex.setText(getResources().getString(R.string.nv));
                                break;
                        }
                    } else {
                        switch (personal.getSex()) {
                            case 1:
                                sex.setText(getResources().getString(R.string.nan));
                                break;
                            case 2:
                                sex.setText(getResources().getString(R.string.nv));
                                break;
                        }
                    }

                    if (getResources().getConfiguration().locale.getCountry().equals("CN")) {
                        switch (Integer.parseInt(personal.getGladmin())) {
                            case 1:
                                zhiwu.setText(getResources().getString(R.string.cj));
                                break;
                            case 2:
                                zhiwu.setText(getResources().getString(R.string.zd));
                                break;
                            case 3:
                                zhiwu.setText(getResources().getString(R.string.yj));
                                break;
                            case 4:
                                zhiwu.setText(getResources().getString(R.string.ej));
                                break;
                            case 5:
                                zhiwu.setText(getResources().getString(R.string.sj));
                                break;
                            case 6:
                                zhiwu.setText(getResources().getString(R.string.sij));
                                break;
                        }

                    } else {
                        switch (Integer.parseInt(personal.getGladmin())) {
                            case 1:
                                zhiwu.setText(getResources().getString(R.string.cj));
                                break;
                            case 2:
                                zhiwu.setText(getResources().getString(R.string.zd));
                                break;
                            case 3:
                                zhiwu.setText(getResources().getString(R.string.yj));
                                break;
                            case 4:
                                zhiwu.setText(getResources().getString(R.string.ej));
                                break;
                            case 5:
                                zhiwu.setText(getResources().getString(R.string.sj));
                                break;
                            case 6:
                                zhiwu.setText(getResources().getString(R.string.sij));
                                break;
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
        request.setTag("personal");
        MyApplication.getHttpQueue().add(request);
    }

    private void initview() {
        fanhui = (ImageView) findViewById(R.id.personal_fanhui);
        loginName = (TextView) findViewById(R.id.tv_login);
        company = (TextView) findViewById(R.id.tv_company);
        phone = (TextView) findViewById(R.id.tv_phone);
        email = (TextView) findViewById(R.id.tv_email);
        changPhone = (RelativeLayout) findViewById(R.id.rl_phone);
        changEmail = (RelativeLayout) findViewById(R.id.rl_email);
        login = (Button) findViewById(R.id.btn_login);
        name = (TextView) findViewById(R.id.tv_name);
        sex = (TextView) findViewById(R.id.tv_sex);
        zhiwu = (TextView) findViewById(R.id.tv_zhiwu);
        changpassword = (RelativeLayout) findViewById(R.id.rl_chang_password);
        changpassword.setOnClickListener(this);
        login.setOnClickListener(this);
        changPhone.setOnClickListener(this);
        changEmail.setOnClickListener(this);

        fanhui.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personal_fanhui:
                finish();
                break;

            case R.id.rl_phone:
                int requestCode = 0;
                Intent intentchang = new Intent(PersonalActivity.this, ChangPhoneActivity.class);
                startActivityForResult(intentchang, requestCode);
                break;
            case R.id.rl_email:
                Intent intentemail = new Intent(PersonalActivity.this, ChangEmailActivity.class);
                startActivityForResult(intentemail, 1);
                break;
            case R.id.btn_login:
                Intent intentlogin=new Intent(PersonalActivity.this,LoginActivity.class);
                startActivity(intentlogin);
                finish();
                MainActivity activity=new MainActivity();
                activity.onDestroy();
                break;
            case R.id.rl_chang_password:
                Intent intent=new Intent(PersonalActivity.this,ChangPasswordActivity2.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 1) {
            String newphone = data.getStringExtra("newPhone");
            phone.setText(newphone);
        }
        if (requestCode == 1 && resultCode == 2) {
            String emai = data.getStringExtra("email");
            email.setText(emai);
        }
    }
}
