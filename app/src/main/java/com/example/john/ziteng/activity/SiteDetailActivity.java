package com.example.john.ziteng.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.john.ziteng.R;
import com.example.john.ziteng.domain.SiteInfo;
import com.example.john.ziteng.fragment.CoreDataFragment;
import com.example.john.ziteng.fragment.DeviceGroupFragment;
import com.example.john.ziteng.fragment.HistoricalDataFragment;
import com.example.john.ziteng.fragment.SiteDetalFragment;
import com.example.john.ziteng.utils.DensityUtils;
import com.example.john.ziteng.utils.ResourceReader;

/**
 * 站点详情
 * Created by john on 2016/3/25.
 */
public class SiteDetailActivity extends BaseActivity implements View.OnClickListener{
    private LinearLayout layout;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private SiteDetalFragment siteDetalFragment;
    private CoreDataFragment coreDataFragment;
    private HistoricalDataFragment historicalDataFragment;
    private DeviceGroupFragment deviceGroupFragment;
    private ImageView fanhui;
    private TextView tv_site_title;
    private SiteInfo site;
    private String siteId;
    private View view;
    private View view3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_detal);
        Intent intent=getIntent();
        siteId = intent.getStringExtra("siteId");
        site = (SiteInfo) intent.getSerializableExtra("siteInfo");
        initView();
        setTabSelected(btn1);
        setDefaultFragment();
    }


    private void initView() {
        view3 = findViewById(R.id.view3);
        view = findViewById(R.id.view);
        fanhui = (ImageView) findViewById(R.id.site_fanhui);
        tv_site_title = (TextView) findViewById(R.id.tv_site_title);
        //Tab
        layout = (LinearLayout)findViewById(R.id.layout_tab);
        btn1 = (Button)findViewById(R.id.btnTab001);
//        btn2 = (Button)findViewById(R.id.btnTab002);
        btn3 = (Button)findViewById(R.id.btnTab003);
        btn4 = (Button)findViewById(R.id.btnTab004);
        btn1.setOnClickListener(this);
//        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        fanhui.setOnClickListener(this);
        if (siteId!=null){
            layout.setVisibility(View.GONE);
            view.setVisibility(View.GONE);
            view3.setVisibility(View.GONE);
        }
    }
    //选择button绘制下面的横线
    private void setTabSelected(Button btnSelected) {
        Drawable selectedDrawable = ResourceReader.readDrawable(SiteDetailActivity.this, R.drawable.shape_nav_indicator);
        int screenWidth = DensityUtils.getScreenSize(SiteDetailActivity.this)[0];
        int right = screenWidth / 2;
        selectedDrawable.setBounds(0, 0, right, DensityUtils.dipTopx(SiteDetailActivity.this, 6));
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

    public void onClick(View v) {
        android.app.FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction ft = fm.beginTransaction();
        switch (v.getId()) {
            case R.id.btnTab001:
                setTabSelected(btn1);
                if (siteDetalFragment == null) {
                    siteDetalFragment = new SiteDetalFragment();
                }
                ft.replace(R.id.fl_layout, siteDetalFragment);
                break;
//            case R.id.btnTab002:
//                setTabSelected(btn2);
//                if (coreDataFragment == null) {
//                    coreDataFragment = new CoreDataFragment();
//                }
//                ft.replace(R.id.fl_layout, coreDataFragment);
//                break;
            case R.id.btnTab003:
                setTabSelected(btn3);
                if (historicalDataFragment == null) {
                    historicalDataFragment = new HistoricalDataFragment();
                }
                ft.replace(R.id.fl_layout, historicalDataFragment);
                break;
            case R.id.btnTab004:
                setTabSelected(btn4);
                if (deviceGroupFragment == null) {
                    deviceGroupFragment = new DeviceGroupFragment();
                }
                ft.replace(R.id.fl_layout, deviceGroupFragment);
                break;
            case R.id.site_fanhui:
                finish();
                break;
        }
        ft.commit();
    }



    //选择默认的Fragment
    private void setDefaultFragment() {
        android.app.FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction ft = fm.beginTransaction();
        siteDetalFragment = new SiteDetalFragment();
        ft.replace(R.id.fl_layout, siteDetalFragment).commit();
    }
}
