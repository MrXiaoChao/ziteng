package com.example.john.ziteng.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.john.ziteng.R;
import com.example.john.ziteng.fragment.CanshuFragment;
import com.example.john.ziteng.fragment.GaojingDeviceFragment;
import com.example.john.ziteng.fragment.QuxianFragment;
import com.example.john.ziteng.fragment.ZhuangTaiFragment;
import com.example.john.ziteng.utils.DensityUtils;
import com.example.john.ziteng.utils.ResourceReader;

/**
 * 设备历史入口
 * Created by john on 2016/3/29.
 */
public class DeviceHistoryActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout layout;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private GaojingDeviceFragment gaojingDeviceFragment;
    private ZhuangTaiFragment zhuangTaiFragment;
    private CanshuFragment canshuFragment;
    private QuxianFragment quxianFragment;
    private ImageView site_fanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_history);
        initview();
        setTabSelected(btn1);
        setDefaultFragment();

    }

    private void initview() {
        site_fanhui = (ImageView) findViewById(R.id.site_fanhui);
        layout = (LinearLayout)findViewById(R.id.layout_tab);
        btn1 = (Button)findViewById(R.id.btnTab001);
        btn2 = (Button)findViewById(R.id.btnTab002);
        btn3 = (Button)findViewById(R.id.btnTab003);
        btn4 = (Button)findViewById(R.id.btnTab004);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        site_fanhui.setOnClickListener(this);
    }


    private void setTabSelected(Button btnSelected) {
        Drawable selectedDrawable = ResourceReader.readDrawable(DeviceHistoryActivity.this, R.drawable.shape_nav_indicator);
        int screenWidth = DensityUtils.getScreenSize(DeviceHistoryActivity.this)[0];
        int right = screenWidth / 2;
        selectedDrawable.setBounds(0, 0, right, DensityUtils.dipTopx(DeviceHistoryActivity.this, 6));
        btnSelected.setSelected(true);
        btnSelected.setCompoundDrawables(null, null, null, selectedDrawable);
        int size = layout.getChildCount();
        for (int i = 0; i < size; i++) {
            if (btnSelected.getId() != layout.getChildAt(i).getId()) {
                layout.getChildAt(i).setSelected(false);
                ((Button) layout.getChildAt(i)).setCompoundDrawables(null, null, null, null);
            }
        }
    }

    @Override
    public void onClick(View v) {
        android.app.FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction ft = fm.beginTransaction();
        switch (v.getId()) {
            case R.id.btnTab001:
                setTabSelected(btn1);
                if (gaojingDeviceFragment == null) {
                    gaojingDeviceFragment = new GaojingDeviceFragment();
                }
                ft.replace(R.id.fl_layout, gaojingDeviceFragment);
                break;
            case R.id.btnTab002:
                setTabSelected(btn2);
                if (zhuangTaiFragment == null) {
                    zhuangTaiFragment = new ZhuangTaiFragment();
                }
                ft.replace(R.id.fl_layout, zhuangTaiFragment);
                break;
            case R.id.btnTab003:
                setTabSelected(btn3);
                if (canshuFragment == null) {
                    canshuFragment = new CanshuFragment();
                }
                ft.replace(R.id.fl_layout, canshuFragment);
                break;
            case R.id.btnTab004:
                setTabSelected(btn4);
                if (quxianFragment == null) {
                    quxianFragment = new QuxianFragment();
                }
                ft.replace(R.id.fl_layout, quxianFragment);
                break;
            case R.id.site_fanhui:
                finish();
                break;
        }
        ft.commit();

    }

    private void setDefaultFragment() {
        android.app.FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction ft = fm.beginTransaction();
        gaojingDeviceFragment = new GaojingDeviceFragment();
        ft.replace(R.id.fl_layout, gaojingDeviceFragment).commit();
    }
}
