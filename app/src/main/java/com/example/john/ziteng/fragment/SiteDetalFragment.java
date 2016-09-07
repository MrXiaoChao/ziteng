package com.example.john.ziteng.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.john.ziteng.R;
import com.example.john.ziteng.activity.CoreDataActivity;
import com.example.john.ziteng.activity.SiteNewsActivity;
import com.example.john.ziteng.activity.WebMapActivity;
import com.example.john.ziteng.activity.WebViewTotalActivity;
import com.example.john.ziteng.application.MyApplication;
import com.example.john.ziteng.domain.SiteDelicInfo;
import com.example.john.ziteng.domain.SiteGuangJiang;
import com.example.john.ziteng.domain.SiteInfo;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;
import com.example.john.ziteng.utils.SPUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.HashMap;
import java.util.Map;

/**
 * 站点概况
 * Created by john on 2016/3/25.
 */
public class SiteDetalFragment extends Fragment {
    private TextView ritd;
    private TextView risc;
    private TextView riups;
    private TextView yuetd;
    private TextView yuesc;
    private TextView yueups;
    private TextView niantd;
    private TextView niansc;
    private TextView nianups;
    private TextView ups;
    private TextView name;
    private TextView time;
    private TextView power;
    private TextView dianjia;
    private SiteInfo site;
    private TextView city;
    private TextView temperature;
    private TextView cond;
    private TextView max;
    private TextView dir;
    private TextView currentstorageCapacity;
    private TextView currentPower;
    private TextView allSaveElectricity;
    private TextView aveSaveElectricity;
    private TextView allSaveMoney;
    private TextView aveSaveMoney;
    private TextView allemissions;
    private TextView aveemissions;
    private TextView status;
    private TextView statusTime;
    private RelativeLayout rltime;
    private SiteDelicInfo siteDelicInfo;
    private PullToRefreshScrollView scrollView;
    private String level;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_site_datils, container, false);
        //拿到关注站点传过来的对象
        Intent intent = getActivity().getIntent();
        site = (SiteInfo) intent.getSerializableExtra("siteInfo");
        level = (String) SPUtils.get(getActivity(), "level", "一级用户");
        initview(view);
        name.setText(site.getName());
        time.setText(site.getDeploytime());
        power.setText(site.getStorageCapacity());
        dianjia.setText(site.getElectrovalency());
        getDataFromService();
        getdataFromService();
        return view;
    }

    //从服务器获取数据
    private void getDataFromService() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.SiteDelicInfo, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                siteDelicInfo = PaseJson.sitedelicInfo(s);
                if (siteDelicInfo != null) {
                    city.setText(siteDelicInfo.getCity());
                    temperature.setText(siteDelicInfo.getTemperature());
                    cond.setText(siteDelicInfo.getCond());
                    max.setText(siteDelicInfo.getMax());
                    dir.setText(siteDelicInfo.getDir());
                    currentstorageCapacity.setText(siteDelicInfo.getCurrentstorageCapacity() + "KWH");
                    currentPower.setText(siteDelicInfo.getCurrentPower() + "KW");
                    allSaveElectricity.setText(siteDelicInfo.getAllSaveElectricity() + "KW");
                    aveSaveElectricity.setText(siteDelicInfo.getAveSaveElectricity() + "KW");
                    allSaveMoney.setText(siteDelicInfo.getAllSaveMoney() + "¥");
                    aveSaveMoney.setText(siteDelicInfo.getAveSaveMoney() + "¥");
                    allemissions.setText(siteDelicInfo.getAllemissions() + "t");
                    aveemissions.setText(siteDelicInfo.getAveemissions() + "t");
                    statusTime.setText(siteDelicInfo.getStatusTime());
                    if (siteDelicInfo.getStatus() == 1) {
                        status.setText("外电正常");
                    } else if (siteDelicInfo.getStatus() == 2) {
                        rltime.setVisibility(View.VISIBLE);
                        status.setText("ups供电");
                    } else if (siteDelicInfo.getStatus() == 3) {
                        rltime.setVisibility(View.VISIBLE);
                        status.setText("负载部分断电");
                    } else if (siteDelicInfo.getStatus() == 4) {
                        rltime.setVisibility(View.VISIBLE);
                        status.setText("负载全部断电");
                    }
                }
                scrollView.onRefreshComplete();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("cityId", site.getCity());
                params.put("siteId", site.getSite_id());
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }
    private void initview(View view) {
        scrollView = (PullToRefreshScrollView) view.findViewById(R.id.sv);
        name = (TextView) view.findViewById(R.id.tv_mingcheng);
        time = (TextView) view.findViewById(R.id.tv_shijian);
        power = (TextView) view.findViewById(R.id.tv_nengli);
        dianjia = (TextView) view.findViewById(R.id.dianjia);
        city = (TextView) view.findViewById(R.id.city);
        temperature = (TextView) view.findViewById(R.id.temperature);
        cond = (TextView) view.findViewById(R.id.cond);
        max = (TextView) view.findViewById(R.id.max);
        dir = (TextView) view.findViewById(R.id.dir);
        currentstorageCapacity = (TextView) view.findViewById(R.id.currentstorageCapacity);
        currentPower = (TextView) view.findViewById(R.id.currentPower);
        allSaveElectricity = (TextView) view.findViewById(R.id.allSaveElectricity);
        aveSaveElectricity = (TextView) view.findViewById(R.id.aveSaveElectricity);
        allSaveMoney = (TextView) view.findViewById(R.id.allSaveMoney);
        aveSaveMoney = (TextView) view.findViewById(R.id.aveSaveMoney);
        allemissions = (TextView) view.findViewById(R.id.allemissions);
        aveemissions = (TextView) view.findViewById(R.id.aveemissions);
        status = (TextView) view.findViewById(R.id.status);
        statusTime = (TextView) view.findViewById(R.id.statusTime);
        rltime = (RelativeLayout) view.findViewById(R.id.re_time);
        ritd = (TextView)view. findViewById(R.id.tv_ritd);
        risc = (TextView)view.findViewById(R.id.tv_risc);
        riups = (TextView) view.findViewById(R.id.tv_riups);
        yuetd = (TextView) view.findViewById(R.id.tv_yuetd);
        yuesc = (TextView) view.findViewById(R.id.tv_yuesc);
        yueups = (TextView) view.findViewById(R.id.tv_yueups);
        niantd = (TextView) view.findViewById(R.id.tv_niantd);
        niansc = (TextView) view.findViewById(R.id.tv_niansc);
        nianups = (TextView) view.findViewById(R.id.tv_nianups);
        ups = (TextView) view.findViewById(R.id.tv_zups);
        RelativeLayout news = (RelativeLayout) view.findViewById(R.id.ll_news);
        RelativeLayout map = (RelativeLayout) view.findViewById(R.id.ll_map);
        RelativeLayout warm = (RelativeLayout) view.findViewById(R.id.ll_warm);
        if (level.equals("一级用户")||level.equals("二级用户")){
            warm.setVisibility(View.GONE);
        }
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SiteNewsActivity.class);
                intent.putExtra("siteId", site.getSite_id());
                startActivity(intent);
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WebMapActivity.class);
                intent.putExtra("latitude", siteDelicInfo.getLatitude());
                intent.putExtra("longitude", siteDelicInfo.getLongitude());
                startActivity(intent);
            }
        });
        warm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
        scrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                String label = DateUtils.formatDateTime(
                        getActivity(),
                        System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME
                                | DateUtils.FORMAT_SHOW_DATE
                                | DateUtils.FORMAT_ABBREV_ALL);
                // 显示最后更新的时间
                refreshView.getLoadingLayoutProxy()
                        .setLastUpdatedLabel(label);
                getDataFromService();
                getdataFromService();
            }
        });

    }
    private void getdataFromService() {
        StringRequest request =new StringRequest(Request.Method.POST, Path.SiteGuanJiang, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                SiteGuangJiang siteGuangJiang= PaseJson.sitegj(s);
                if (siteGuangJiang!=null){
                    ritd.setText(siteGuangJiang.getDayOutpowerNum());
                    risc.setText(siteGuangJiang.getDayOutpowerOfen()+"h");
                    riups.setText(siteGuangJiang.getDayUps()+"h");
                    yuetd.setText(siteGuangJiang.getMonOutpowerNum());
                    yuesc.setText(siteGuangJiang.getMonOutpowerOfen()+"h");
                    yueups.setText(siteGuangJiang.getMonUps()+"h");
                    niantd.setText(siteGuangJiang.getYearOutpowerNum());
                    niansc.setText(siteGuangJiang.getYearOutpowerOfen()+"h");
                    nianups.setText(siteGuangJiang.getYearUps()+"h");
                    ups.setText(siteGuangJiang.getAllUps());
                }
                scrollView.onRefreshComplete();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("siteId",site.getSite_id());
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }


}
