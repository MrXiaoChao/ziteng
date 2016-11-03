package com.example.john.ziteng.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
                deviceGroupInfo = PaseJson.PaseDGF(s);
                if (deviceGroupInfo != null) {
                    listview.setAdapter(new MyListAdapter(DeviceGroupActivity.this, (ArrayList<DeviceGroupInfo.UnitlistBean>) deviceGroupInfo.getUnitlist()));
                    groupTextView.setText("设备信息");
                    name.setText(getResources().getString(R.string.dl)+"-"+equipId.substring(5,7));
                }
                scrollView.onRefreshComplete();
            }
        },new Response.ErrorListener() {
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
        name = (TextView) findViewById(R.id.web_title);
        scrollView = (PullToRefreshScrollView) findViewById(R.id.scrollView);
        xinxi = (RelativeLayout) findViewById(R.id.rl_xinxi);
        zhuangtai = (RelativeLayout) findViewById(R.id.rl_zhuantai);
        canshu = (RelativeLayout) findViewById(R.id.rl_canshu);
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
        xinxi.setOnClickListener(this);
        zhuangtai.setOnClickListener(this);
        canshu.setOnClickListener(this);
        gxinxi.setOnClickListener(this);
        shuju.setOnClickListener(this);
        kongzhi.setOnClickListener(this);
        //用户权限
        if (level.equals("一级用户")||level.equals("二级用户")||level.equals("三级用户")){
            kongzhi.setVisibility(View.GONE);
        }
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String unitId = String.valueOf(SPUtils.put(DeviceGroupActivity.this, "unitId", deviceGroupInfo.getUnitlist().get(position).getUnitId()));
                Intent intent4 = new Intent(DeviceGroupActivity.this, UnitActivity.class);
                intent4.putExtra("unitId", deviceGroupInfo.getUnitlist().get(position).getUnitId());
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
        switch (v.getId()){
            case R.id.group_fanhui:
                finish();
                break;
            case R.id.rl_fx:
                if (!isselect){
                    fx.setImageDrawable(getResources().getDrawable(R.mipmap.buleyou));
                    ll.setVisibility(View.GONE);
                    isselect=true;
                }else {
                    fx.setImageDrawable(getResources().getDrawable(R.mipmap.down));
                    ll.setVisibility(View.VISIBLE);
                    isselect=false;
                }
                break;
            case R.id.rl_xinxi://基本信息
                Intent intent = new Intent(DeviceGroupActivity.this, DeviceInfoActivity.class);
                intent.putExtra("equipId", deviceGroupInfo.getEquipId());
                startActivity(intent);
                break;
            case R.id.rl_zhuantai://设备状态
                Intent intent0 = new Intent(DeviceGroupActivity.this, DeviceStateActivity.class);
                intent0.putExtra("equipId", deviceGroupInfo.getEquipId());
                startActivity(intent0);
                break;
            case R.id.rl_canshu://设备参数
                Intent intent1 = new Intent(DeviceGroupActivity.this, DeviceParameActivity.class);
                intent1.putExtra("equipId", deviceGroupInfo.getEquipId());
                startActivity(intent1);
                break;
            case R.id.rl_gxinxi://告警信息
                StringBuffer URL2 = new StringBuffer();
                URL2.append(Path.ImportWarn);
                URL2.append("equip_id=");
                URL2.append(deviceGroupInfo.getEquipId() + "&");
                URL2.append("group_id=");
                URL2.append(group_id+"&");
                URL2.append("zyw=");
                if (getResources().getConfiguration().locale.getCountry().equals("CN")) {
                    URL2.append(1);//1代表中文
                } else {
                    URL2.append(2);//2代表英文
                }
                Intent intent2 = new Intent(DeviceGroupActivity.this, WebViewTotalActivity.class);
                intent2.putExtra("URL", String.valueOf(URL2));
                intent2.putExtra("biaoti", "告警信息");
                startActivity(intent2);
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



