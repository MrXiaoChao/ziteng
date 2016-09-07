package com.example.john.ziteng.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.john.ziteng.R;
import com.example.john.ziteng.fragment.BarrteyFragment;
import com.example.john.ziteng.fragment.ListFragment;
import com.example.john.ziteng.fragment.MonitorFragment;
import com.example.john.ziteng.fragment.MoudleFragment;
import com.example.john.ziteng.fragment.SiteDetalFragment;
import com.example.john.ziteng.fragment.UnitFragment;

/**
 * 电池列表1
 * Created by john on 2016/5/13.
 */
public class BatteryActivity extends BaseActivity implements View.OnClickListener {

    private Button battery;
    private Button moudle;
    private Button unit;
    private Button list;
    private Button[] mTab = new Button[4];
    private int index;
    private int currentpos;
    private BarrteyFragment barrteyFragment;
    private MoudleFragment moudleFragment;
    private UnitFragment unitFragment;
    private ListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);
        Intent intent=getIntent();
        initview();
        mTab[currentpos].setSelected(true);
        setDefaultFragment();
    }

    private void initview() {
        battery = (Button) findViewById(R.id.btn_battery);
        moudle = (Button) findViewById(R.id.btn_moudle);
        unit = (Button) findViewById(R.id.btn_unit);
        list = (Button) findViewById(R.id.btn_list);


        mTab[0] = battery;
        mTab[1] = moudle;
        mTab[2] = unit;
        mTab[3] = list;

        battery.setOnClickListener(this);
        moudle.setOnClickListener(this);
        unit.setOnClickListener(this);
        list.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        android.app.FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction ft = fm.beginTransaction();
        switch (v.getId()){
            case R.id.btn_battery:
                index=0;
                if (barrteyFragment == null) {
                    barrteyFragment = new BarrteyFragment();
                }
                ft.replace(R.id.ll_layout, barrteyFragment);
                break;
            case R.id.btn_moudle:
                index=1;
                if (moudleFragment == null) {
                    moudleFragment = new MoudleFragment();
                }
                ft.replace(R.id.ll_layout, moudleFragment);
                break;
            case R.id.btn_unit:
                index=2;
                if (unitFragment == null) {
                   unitFragment= new UnitFragment();
                }
                ft.replace(R.id.ll_layout, unitFragment);
                break;
            case R.id.btn_list:
                index=3;
                if (listFragment == null) {
                    listFragment = new ListFragment();
                }
                ft.replace(R.id.ll_layout,listFragment);
                break;
        }
        ft.commit();
        switchButton(index);
    }
    private  void switchButton(int pos) {
        index = pos;
        if (currentpos != index) {
            mTab[currentpos].setSelected(false);
            mTab[index].setSelected(true);
            currentpos = index;
        }
    }
    //选择默认的Fragment
    private void setDefaultFragment() {
        android.app.FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction ft = fm.beginTransaction();
        barrteyFragment = new BarrteyFragment();
        ft.replace(R.id.ll_layout, barrteyFragment).commit();
    }
}
