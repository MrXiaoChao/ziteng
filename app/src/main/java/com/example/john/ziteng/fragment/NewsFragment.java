package com.example.john.ziteng.fragment;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.john.ziteng.R;
import com.example.john.ziteng.utils.DensityUtils;
import com.example.john.ziteng.utils.ResourceReader;

/**
 * 资讯快递
 * Created by john on 2016/3/17.
 */
public class NewsFragment extends Fragment implements View.OnClickListener {

    private TextView title;
    private LinearLayout layout;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private FocusInfoFragment focusInfoFragment;
    private NationalFragment nationalFragment;
    private IndustryFragment industryFragment;
    private SiteInfoFragment siteInfoFragment;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        initView(view);
        setDefaultFragment();
        setTabSelected(btn1);
        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
    }

    private void initView(View view) {
        //titleBar
        title = (TextView) view.findViewById(R.id.title);
        title.setText(R.string.news);

        //Tab
        layout = (LinearLayout) view.findViewById(R.id.layout_tab);
        btn1 = (Button) view.findViewById(R.id.btnTab001);
        btn2 = (Button) view.findViewById(R.id.btnTab002);
        btn3 = (Button) view.findViewById(R.id.btnTab003);
        btn4 = (Button) view.findViewById(R.id.btnTab004);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

    }

    //选择button绘制下面的横线
    private void setTabSelected(Button btnSelected) {
        Drawable selectedDrawable = ResourceReader.readDrawable(getActivity(), R.drawable.shape_nav_indicator);
        int screenWidth = DensityUtils.getScreenSize(getActivity())[0];
        int right = screenWidth / 2;
        selectedDrawable.setBounds(0, 0, right, DensityUtils.dipTopx(getActivity(), 6));
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

    //button点击事件，切换视图
    @Override
    public void onClick(View v) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        switch (v.getId()) {
            case R.id.btnTab001:
                setTabSelected(btn1);
                if (focusInfoFragment == null) {
                    focusInfoFragment = new FocusInfoFragment();
                }
                ft.replace(R.id.fl_layout, focusInfoFragment);
                break;
            case R.id.btnTab002:
                setTabSelected(btn2);
                if (nationalFragment == null) {
                    nationalFragment = new NationalFragment();
                }
                ft.replace(R.id.fl_layout, nationalFragment);
                break;
            case R.id.btnTab003:
                setTabSelected(btn3);
                if (industryFragment == null) {
                    industryFragment = new IndustryFragment();
                }
                ft.replace(R.id.fl_layout, industryFragment);
                break;
            case R.id.btnTab004:
                setTabSelected(btn4);
                if (siteInfoFragment == null) {
                    siteInfoFragment = new SiteInfoFragment();
                }
                ft.replace(R.id.fl_layout, siteInfoFragment);
                break;
        }
        ft.commit();
    }

    //选择默认的Fragment
    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        focusInfoFragment = new FocusInfoFragment();
        ft.replace(R.id.fl_layout, focusInfoFragment).commit();
    }

}