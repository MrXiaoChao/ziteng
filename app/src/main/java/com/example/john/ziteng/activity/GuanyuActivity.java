package com.example.john.ziteng.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.john.ziteng.R;
import com.example.john.ziteng.urlpath.Path;
import com.umeng.analytics.MobclickAgent;

/**
 * 关于能量监控平台
 * Created by john on 2016/3/27.
 */
public class GuanyuActivity extends BaseActivity implements View.OnClickListener {

    private ImageView guanyu_fanhui;
    private RelativeLayout guanyu;
    private RelativeLayout help;
    private RelativeLayout verson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guanyu);
        initview();
    }

    private void initview() {
        guanyu_fanhui = (ImageView) findViewById(R.id.guanyu_fanhui);
        guanyu = (RelativeLayout) findViewById(R.id.rl_guanyu);
        help = (RelativeLayout) findViewById(R.id.rl_help);
        verson = (RelativeLayout) findViewById(R.id.rl_verson);
        guanyu.setOnClickListener(this);
        help.setOnClickListener(this);
        guanyu_fanhui.setOnClickListener(this) ;
        verson.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.guanyu_fanhui://返回键
                finish();
                break;
            case R.id.rl_guanyu:
                Intent intent=new Intent(GuanyuActivity.this,WebViewTotalActivity.class);
                StringBuffer url=new StringBuffer();
                url.append(Path.Gengxin);
                if (getResources().getConfiguration().locale.getCountry().equals("CN")){
                    url.append(1);//1代表中文
                }else {
                    url.append(2);//2代表英文
                }
                intent.putExtra("URL", String.valueOf(url));
                intent.putExtra("biaoti","更新说明");
                startActivity(intent);
                break;
            case R.id.rl_help:
                Intent intent1=new Intent(GuanyuActivity.this,WebViewTotalActivity.class);
                StringBuffer url1=new StringBuffer();
                url1.append(Path.Help);
                if (getResources().getConfiguration().locale.getCountry().equals("CN")){
                    url1.append(1);//1代表中文
                }else {
                    url1.append(2);//2代表英文
                }
                intent1.putExtra("URL", String.valueOf(url1));
                intent1.putExtra("biaoti", "使用帮助");
                startActivity(intent1);
                break;
            case R.id.rl_verson://版本更新
                AlertDialog.Builder builder=new AlertDialog.Builder(GuanyuActivity.this);
                builder.setTitle("已经是最新版本");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setCancelable(false);
                builder.create().show();
                break;
        }
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
