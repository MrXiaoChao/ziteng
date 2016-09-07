package com.example.john.ziteng.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.john.ziteng.R;

/**
 * 注册成功页面
 * Created by john on 2016/3/22.
 */
public class RegisterSuccessActivity extends BaseActivity implements View.OnClickListener {

    private Button btnfanhui;
    private long backtime=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registersuccess);
        btnfanhui = (Button) findViewById(R.id.btn_fanhui);
        btnfanhui.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent =new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
    //是否退出
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long t = System.currentTimeMillis();
            if (t - backtime > 3000) {
                Toast.makeText(this, "你还没登录,确定退出吗?", Toast.LENGTH_SHORT).show();
                backtime = t;
                return true;
            }
            KillAll();
            return true;
        }
        return super.onKeyUp(keyCode,event);
    }
}
