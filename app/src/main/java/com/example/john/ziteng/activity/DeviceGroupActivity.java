package com.example.john.ziteng.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.john.ziteng.R;
import com.example.john.ziteng.adapter.MyListAdapter;
import com.example.john.ziteng.application.MyApplication;
import com.example.john.ziteng.domain.DeviceGroupInfo;
import com.example.john.ziteng.domain.DevicePassWord;
import com.example.john.ziteng.domain.SiteInfo;
import com.example.john.ziteng.fragment.DevicePassWordFragment;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;
import com.example.john.ziteng.utils.SPUtils;
import com.example.john.ziteng.view.MyListview;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * 设备列表
 * Created by john on 2016/3/28.
 */
public class DeviceGroupActivity extends BaseActivity implements DevicePassWordFragment.LoginInputListener, View.OnClickListener {

    private ImageView imageView;
    private DeviceGroupInfo deviceGroupInfo;
    private String mPhone;
    private SiteInfo siteInfo;
    private String group_id;
    private String equipId;
    private ImageView fx;
    private LinearLayout ll;
    private RelativeLayout rl_fx;
    private MyListview listview;
    private TextView groupTextView;
    private RelativeLayout xinxi;
    private RelativeLayout zhuangtai;
    private RelativeLayout canshu;
    private RelativeLayout gxinxi;
    private RelativeLayout shuju;
    private RelativeLayout kongzhi;
    private PullToRefreshScrollView scrollView;
    private String level;
    private TextView name;
    private TextView sbzt;
    private TextView dy;
    private TextView dl;
    private TextView wd;
    private TextView xhsj;
    private TextView cnl;
    private TextView bssj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devicegroup);
        mPhone = (String) SPUtils.get(DeviceGroupActivity.this, "phone", "");
        level = (String) SPUtils.get(DeviceGroupActivity.this, "level", "一级用户");
        Intent intent = getIntent();
        siteInfo = (SiteInfo) intent.getSerializableExtra("site");
        group_id = intent.getStringExtra("group_id");
        equipId = intent.getStringExtra("equipId");
        initview();
        getDate();
    }

    private void getDate() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.DeviceGroupInfo, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //这个限制条件是后台只有equipId=3000000有数据·如果不限制的话·gson解析会出错误,如果后台有数据解除这个限制就行
                if (equipId.equals("3000000")) {
                    Gson gson = new Gson();
                    deviceGroupInfo = gson.fromJson(s, DeviceGroupInfo.class);
                    if (deviceGroupInfo != null) {
                        listview.setAdapter(new MyListAdapter(DeviceGroupActivity.this, (ArrayList<DeviceGroupInfo.UnitlistBean>) deviceGroupInfo.getUnitlist()));
                        groupTextView.setText(getResources().getString(R.string.sbxx));
                        name.setText(getResources().getString(R.string.dl) + "-" + equipId.substring(5, 7));
                        if (deviceGroupInfo.getState().equals("IDLE")) {
                            sbzt.setText(getResources().getString(R.string.kx));
                        } else if (deviceGroupInfo.getState().equals("DISCHARGING")) {
                            sbzt.setText(getResources().getString(R.string.fd));
                        } else if (deviceGroupInfo.getState().equals("CHARGING")) {
                            sbzt.setText(getResources().getString(R.string.cd));
                        } else if (deviceGroupInfo.getState().equals("WARNING")) {
                            sbzt.setText(getResources().getString(R.string.gj));
                        } else if (deviceGroupInfo.getState().equals("STOP")) {
                            sbzt.setText(getResources().getString(R.string.tj));
                        }
                        dy.setText(String.valueOf(deviceGroupInfo.getVoltage()) + " V");
                        dl.setText(deviceGroupInfo.getElectricCurrent() + " A");
                        wd.setText(deviceGroupInfo.getTemperature() + " °C");
                        xhsj.setText(String.valueOf(deviceGroupInfo.getHoldTime()) + " h");
                        cnl.setText(deviceGroupInfo.getStored_energy() + " kWh");
                        bssj.setText(deviceGroupInfo.getDeploy_time());
                    }
                    scrollView.onRefreshComplete();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> Params = new HashMap<String, String>();
                Params.put("equipId", equipId);
                return Params;
            }
        };
        request.setTag("DeviceGroup");
        MyApplication.getHttpQueue().add(request);
    }

    //输入设备密码对话框
    public void showLoginDialog(Activity activity) {
        DevicePassWordFragment dialog = new DevicePassWordFragment();
        dialog.show(getFragmentManager(), "devicePassword");
    }

    //回调过来的设备控制密码
    @Override
    public void onLoginInputComplete(String password) {
        getPassWord(password);
    }

    //验证用户输入的设备密码是否正确
    private void getPassWord(final String password) {
        StringRequest request = new StringRequest(Request.Method.POST, Path.DevicePassWord, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                DevicePassWord devicePW = PaseJson.PaseDevicePass(s);
                if (devicePW.isSuccess()) {
                    Intent intent5 = new Intent(DeviceGroupActivity.this, DeviceControlActivity.class);
                    intent5.putExtra("groupId", group_id);
                    intent5.putExtra("equipId", deviceGroupInfo.getEquipId());
                    intent5.putExtra("stationId", siteInfo.getSite_id());
                    startActivity(intent5);
                } else {
                    Toast.makeText(DeviceGroupActivity.this, devicePW.getMsg(), Toast.LENGTH_SHORT).show();
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
                params.put("loginname", mPhone);
                params.put("password", password);
                return params;
            }
        };
        request.setTag("devicePassword");
        MyApplication.getHttpQueue().add(request);
    }

    private void initview() {
        sbzt = (TextView) findViewById(R.id.sbzt);
        dy = (TextView) findViewById(R.id.dy);
        dl = (TextView) findViewById(R.id.dl);
        wd = (TextView) findViewById(R.id.wd);
        xhsj = (TextView) findViewById(R.id.xhsj);
        cnl = (TextView) findViewById(R.id.cnl);
        bssj = (TextView) findViewById(R.id.bssj);
        name = (TextView) findViewById(R.id.web_title);
        scrollView = (PullToRefreshScrollView) findViewById(R.id.scrollView);
        zhuangtai = (RelativeLayout) findViewById(R.id.rl_zhuantai);
        gxinxi = (RelativeLayout) findViewById(R.id.rl_gxinxi);
        shuju = (RelativeLayout) findViewById(R.id.rl_shuju);
        kongzhi = (RelativeLayout) findViewById(R.id.rl_kongzhi);
        ll = (LinearLayout) findViewById(R.id.ll);
        groupTextView = (TextView) findViewById(R.id.groupTextView);
        rl_fx = (RelativeLayout) findViewById(R.id.rl_fx);
        listview = (MyListview) findViewById(R.id.listview_shebei);
        imageView = (ImageView) findViewById(R.id.group_fanhui);
        fx = (ImageView) findViewById(R.id.image_fx);
        fx.setImageDrawable(getResources().getDrawable(R.mipmap.down));
        imageView.setOnClickListener(this);
        rl_fx.setOnClickListener(this);
        gxinxi.setOnClickListener(this);
        shuju.setOnClickListener(this);
        kongzhi.setOnClickListener(this);
        //用户权限
        if (level.equals("一级用户") || level.equals("二级用户") || level.equals("三级用户")) {
            kongzhi.setVisibility(View.GONE);
        }
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String unitId = String.valueOf(SPUtils.put(DeviceGroupActivity.this, "unitId", deviceGroupInfo.getUnitlist().get(position).getUnitId()));
                Intent intent4 = new Intent(DeviceGroupActivity.this, UnitActivity.class);
                intent4.putExtra("unitId", deviceGroupInfo.getUnitlist().get(position).getUnitId());
                intent4.putExtra("equip_id", deviceGroupInfo.getEquip_id());
                startActivity(intent4);

            }
        });
        scrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                String label = DateUtils.formatDateTime(
                        DeviceGroupActivity.this,
                        System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME
                                | DateUtils.FORMAT_SHOW_DATE
                                | DateUtils.FORMAT_ABBREV_ALL);
                // 显示最后更新的时间
                refreshView.getLoadingLayoutProxy()
                        .setLastUpdatedLabel(label);
                getDate();
            }
        });

    }

    private boolean isselect;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.group_fanhui:
                finish();
                break;
            case R.id.rl_fx:
                if (!isselect) {
                    fx.setImageDrawable(getResources().getDrawable(R.mipmap.buleyou));
                    ll.setVisibility(View.GONE);
                    isselect = true;
                } else {
                    fx.setImageDrawable(getResources().getDrawable(R.mipmap.down));
                    ll.setVisibility(View.VISIBLE);
                    isselect = false;
                }
                break;

            case R.id.rl_gxinxi://告警信息
                Intent intent = new Intent(DeviceGroupActivity.this, SiteGaojingActivity.class);
                intent.putExtra("equipId", deviceGroupInfo.getEquipId());
                startActivity(intent);
                break;
            case R.id.rl_shuju://历史数据
                Intent intent3 = new Intent(DeviceGroupActivity.this, DeviceHistoryActivity.class);
                intent3.putExtra("equip_id", deviceGroupInfo.getEquipId());
                startActivity(intent3);
                break;
            case R.id.rl_kongzhi://设备控制
                showLoginDialog(DeviceGroupActivity.this);
                break;
        }
    }

}




