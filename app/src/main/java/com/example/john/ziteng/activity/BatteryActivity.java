package com.example.john.ziteng.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.john.ziteng.R;
import com.example.john.ziteng.fragment.BarrteyFragment;
import com.example.john.ziteng.fragment.ListFragment;
import com.example.john.ziteng.fragment.MoudleFragment;
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
    private Button[] mTab = new Button[2];
    private int index;
    private int currentpos;
    private BarrteyFragment barrteyFragment;
    private MoudleFragment moudleFragment;
    private UnitFragment unitFragment;
    private ListFragment listFragment;
    private int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);
        Intent intent=getIntent();
        number = intent.getIntExtra("number",1);
        initview();
        mTab[currentpos].setSelected(true);
        setDefaultFragment(number);
    }

    private void initview() {
        View view=findViewById(R.id.view);
        battery = (Button) findViewById(R.id.btn_battery);
        moudle = (Button) findViewById(R.id.btn_moudle);
        unit = (Button) findViewById(R.id.btn_unit);
        list = (Button) findViewById(R.id.btn_list);
        battery.setOnClickListener(this);
        moudle.setOnClickListener(this);
        unit.setOnClickListener(this);
        list.setOnClickListener(this);
        switch (number){
            case 1:
                mTab[0] = unit;
                mTab[1] = list;
                battery.setVisibility(View.GONE);
                moudle.setVisibility(View.GONE);
                break;
            case 2:
                view.setVisibility(View.GONE);
                mTab[0] = moudle;
                mTab[1] = list;
                unit.setVisibility(View.GONE);
                battery.setVisibility(View.GONE);
                break;
            case 3:
                view.setVisibility(View.GONE);
                mTab[0] = battery;
                mTab[1] = list;
                unit.setVisibility(View.GONE);
                moudle.setVisibility(View.GONE);
                break;
        }
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
                index=0;
                if (moudleFragment == null) {
                    moudleFragment = new MoudleFragment();
                }
                ft.replace(R.id.ll_layout, moudleFragment);
                break;
            case R.id.btn_unit:
                index=0;
                if (unitFragment == null) {
                   unitFragment= new UnitFragment();
                }
                ft.replace(R.id.ll_layout, unitFragment);
                break;
            case R.id.btn_list:
                index=1;
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
    private void setDefaultFragment(int number) {
        android.app.FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction ft = fm.beginTransaction();
        switch (number){
            case 1://单元历史数据
                unitFragment = new UnitFragment();
                ft.replace(R.id.ll_layout, unitFragment).commit();
                break;
            case 2://模块历史数据
                moudleFragment=new MoudleFragment();
                ft.replace(R.id.ll_layout, moudleFragment).commit();
                break;
            case 3://电池历史数据
                barrteyFragment = new BarrteyFragment();
                ft.replace(R.id.ll_layout, barrteyFragment).commit();
                break;
        }

    }
}
