package com.example.john.ziteng.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.john.ziteng.R;
import com.example.john.ziteng.activity.SiteNewsActivity;
import com.example.john.ziteng.activity.WebMapActivity;
import com.example.john.ziteng.activity.WebViewTotalActivity;
import com.example.john.ziteng.application.MyApplication;
import com.example.john.ziteng.domain.SiteDelicInfo;
import com.example.john.ziteng.domain.SiteInfo;
import com.example.john.ziteng.urlpath.Path;
import com.example.john.ziteng.utils.SPUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 站点概况
 * Created by john on 2016/10/26.
 */
public class SiteDetalFragment extends Fragment {
    @BindView(R.id.city)
    TextView city;
    @BindView(R.id.temperature)
    TextView temperature;
    @BindView(R.id.cond)
    TextView cond;
    @BindView(R.id.max)
    TextView max;
    @BindView(R.id.dir)
    TextView dir;
    @BindView(R.id.tv_mingcheng)
    TextView tvMingcheng;
    @BindView(R.id.rl_zhandian)
    RelativeLayout rlZhandian;
    @BindView(R.id.tv_zhuantai)
    TextView tvZhuantai;
    @BindView(R.id.rl_zhuantai)
    RelativeLayout rlZhuantai;
    @BindView(R.id.tv_xchucun)
    TextView tvXchucun;
    @BindView(R.id.rl_xchucun)
    RelativeLayout rlXchucun;
    @BindView(R.id.tv_zchucun)
    TextView tvZchucun;
    @BindView(R.id.rl_zchucun)
    RelativeLayout rlZchucun;
    @BindView(R.id.tv_gonglv)
    TextView tvGonglv;
    @BindView(R.id.rl_gonglv)
    RelativeLayout rlGonglv;
    @BindView(R.id.tv_zdianl)
    TextView tvZdianl;
    @BindView(R.id.rl_zdianl)
    RelativeLayout rlZdianl;
    @BindView(R.id.tv_shijian)
    TextView tvShijian;
    @BindView(R.id.rl_pers)
    RelativeLayout rlPers;
    @BindView(R.id.dianjia)
    TextView dianjia;
    @BindView(R.id.rl_ponal)
    RelativeLayout rlPonal;
    @BindView(R.id.iv_jingji)
    ImageView ivJingji;
    @BindView(R.id.rl_jingji)
    RelativeLayout rlJingji;
    @BindView(R.id.tv_zsdl)
    TextView tvZsdl;
    @BindView(R.id.tv_psdl)
    TextView tvPsdl;
    @BindView(R.id.tv_zsqs)
    TextView tvZsqs;
    @BindView(R.id.tv_psqs)
    TextView tvPsqs;
    @BindView(R.id.tv_ztjsl)
    TextView tvZtjsl;
    @BindView(R.id.tv_ptjsl)
    TextView tvPtjsl;
    @BindView(R.id.ll_jingji)
    LinearLayout llJingji;
    @BindView(R.id.iv_guanjian)
    ImageView ivGuanjian;
    @BindView(R.id.rl_guanjian)
    RelativeLayout rlGuanjian;
    @BindView(R.id.tv_dtd)
    TextView tvDtd;
    @BindView(R.id.tv_dtdzsc)
    TextView tvDtdzsc;
    @BindView(R.id.tv_dups)
    TextView tvDups;
    @BindView(R.id.tv_ytd)
    TextView tvYtd;
    @BindView(R.id.tv_ytdsc)
    TextView tvYtdsc;
    @BindView(R.id.tv_yups)
    TextView tvYups;
    @BindView(R.id.tv_ntd)
    TextView tvNtd;
    @BindView(R.id.tv_ntdzsc)
    TextView tvNtdzsc;
    @BindView(R.id.tv_nups)
    TextView tvNups;
    @BindView(R.id.ll_guanjian)
    LinearLayout llGuanjian;
    @BindView(R.id.ll_warm)
    RelativeLayout llWarm;
    @BindView(R.id.ll_news)
    RelativeLayout llNews;
    @BindView(R.id.ll_map)
    RelativeLayout llMap;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.view2)
    View view2;
    private SiteInfo site;
    private String level;
    private SiteDelicInfo siteDelicInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_site_datils, container, false);
        //拿到关注站点传过来的对象
        Intent intent = getActivity().getIntent();
        site = (SiteInfo) intent.getSerializableExtra("siteInfo");
        level = (String) SPUtils.get(getActivity(), "level", "一级用户");
        ButterKnife.bind(this, view);
        initview(view);
        return view;
    }

    private void initview(View view) {
        if (level.equals("一级用户")||level.equals("二级用户")){
            llWarm.setVisibility(View.GONE);
        }
        StringRequest request =new StringRequest(Request.Method.POST, Path.sitexq, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Gson gson=new Gson();
                siteDelicInfo = gson.fromJson(s,SiteDelicInfo.class);
                if (siteDelicInfo.getWeather().getCity()!=null){
                    city.setText(siteDelicInfo.getWeather().getCity());
                    cond.setText(siteDelicInfo.getWeather().getCond());
                    dir.setText(siteDelicInfo.getWeather().getDir());
                    max.setText(siteDelicInfo.getWeather().getMax());
                    temperature.setText(siteDelicInfo.getWeather().getTemperature());
                }
                if (siteDelicInfo.getBaseInformation().getName()!=null){
                    tvMingcheng.setText(siteDelicInfo.getBaseInformation().getName());
                    tvZhuantai.setText(siteDelicInfo.getBaseInformation().getSiteState());
                    tvXchucun.setText(siteDelicInfo.getBaseInformation().getEnergy_storage()+"kwh");
                    tvZchucun.setText(siteDelicInfo.getBaseInformation().getStorageCapacity()+"kwh");
                    tvGonglv.setText(siteDelicInfo.getBaseInformation().getPower()+"kw");
                    tvZdianl.setText(siteDelicInfo.getBaseInformation().getAllUps());
                    tvShijian.setText(siteDelicInfo.getBaseInformation().getDeploytime());
                    dianjia.setText("¥ "+ siteDelicInfo.getBaseInformation().getElectrovalency());
                }
                if (siteDelicInfo.getBenefit().getAllSaveElectricity()!=null){
                    tvZsdl.setText(siteDelicInfo.getBenefit().getAllSaveElectricity()+"kwh");
                    tvPsdl.setText(siteDelicInfo.getBenefit().getAveSaveElectricity()+"kwh");
                    tvZsqs.setText("¥ "+ siteDelicInfo.getBenefit().getAllSaveMoney());
                    tvPsqs.setText("¥ "+ siteDelicInfo.getBenefit().getAveSaveMoney());
                    tvZtjsl.setText(siteDelicInfo.getBenefit().getAllemissions()+" t");
                    tvPtjsl.setText(siteDelicInfo.getBenefit().getAveemissions()+" t");
                }
                if (siteDelicInfo.getKeyDate().getDayOutpowerNum()!=null){
                    tvDtd.setText(siteDelicInfo.getKeyDate().getDayOutpowerNum());
                    tvDtdzsc.setText(siteDelicInfo.getKeyDate().getDayOutpowerOfen()+" h");
                    tvDups.setText(siteDelicInfo.getKeyDate().getDayUps()+" h");
                    tvYtd.setText(siteDelicInfo.getKeyDate().getMonOutpowerNum());
                    tvYtdsc.setText(siteDelicInfo.getKeyDate().getMonOutpowerOfen()+" h");
                    tvYups.setText(siteDelicInfo.getKeyDate().getMonUps()+" h");
                    tvNtd.setText(siteDelicInfo.getKeyDate().getYearOutpowerNum());
                    tvNtdzsc.setText(siteDelicInfo.getKeyDate().getYearOutpowerOfen()+" h");
                    tvNups.setText(siteDelicInfo.getKeyDate().getYearUps()+" h");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("cityId", site.getCity());
                params.put("siteId", site.getSite_id());
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);

    }

    private boolean flag=true;
    private boolean flag1=true;

    @OnClick({R.id.rl_jingji, R.id.rl_guanjian, R.id.ll_warm, R.id.ll_news, R.id.ll_map})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_jingji://经济环境效益
                if (flag) {
                    llJingji.setVisibility(View.VISIBLE);
                    view1.setVisibility(View.VISIBLE);
                    flag = false;
                } else {
                    llJingji.setVisibility(View.GONE);
                    view1.setVisibility(View.GONE);
                    flag = true;
                }
                break;
            case R.id.rl_guanjian://关键数据
                if (flag1) {
                    view2.setVisibility(View.VISIBLE);
                    llGuanjian.setVisibility(View.VISIBLE);
                    flag1 = false;
                } else {
                    view2.setVisibility(View.GONE);
                    llGuanjian.setVisibility(View.GONE);
                    flag1 = true;
                }
                break;
            case R.id.ll_warm://站点告警
                StringBuffer url = new StringBuffer();
                url.append(Path.SiteWarm);
                url.append(site.getSite_id() + "&");
                url.append("zyw=");
                if (getResources().getConfiguration().locale.getCountry().equals("CN")) {
                    url.append(1);//1代表中文
                } else {
                    url.append(2);//2代表英文
                }
                Intent intent = new Intent(getActivity(), WebViewTotalActivity.class);
                intent.putExtra("biaoti", "站点告警");
                intent.putExtra("URL", String.valueOf(url));
                startActivity(intent);
                break;
            case R.id.ll_news://站点新闻
                Intent intent2 = new Intent(getActivity(), SiteNewsActivity.class);
                intent2.putExtra("siteId", site.getSite_id());
                startActivity(intent2);
                break;
            case R.id.ll_map://站点地图
                Intent intent1 = new Intent(getActivity(), WebMapActivity.class);
                intent1.putExtra("latitude",siteDelicInfo.getBaseInformation().getLatitude());
                intent1.putExtra("longitude", siteDelicInfo.getBaseInformation().getLongitude());
                startActivity(intent1);
                break;
        }
    }
}
