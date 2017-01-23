package com.example.john.ziteng.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.john.ziteng.R;
import com.example.john.ziteng.adapter.DeviceUnitAdapter;
import com.example.john.ziteng.application.MyApplication;
import com.example.john.ziteng.domain.DeviceControl;
import com.example.john.ziteng.domain.Unit;
import com.example.john.ziteng.fragment.ExpertContorlFragment;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 设备控制
 * Created by john on 2016/4/6.
 */
public class DeviceControlActivity extends Activity implements View.OnClickListener, ExpertContorlFragment.InputListener {

    private String groupId;
    private String equipId;
    private String stationId;
    private ImageView fanhui;
    private ListView listUnit;
    private DeviceControl deviceControl;
    private ToggleButton tbdevice;
    private LinearLayout contor;
    private TextView tvControl;
    private EditText pl;
    private Button btnDevice;
    private Button btnUnit;
    private LinearLayout con;
    private LinearLayout llUnit;
    private TextView gaoji;
    private ExpertContorlFragment dialog;
    private RelativeLayout rlGao;
    private PullToRefreshScrollView scrollView;
    private String cp;
    private TextView dianliu;
    private RelativeLayout rl_dianliu;
    private RelativeLayout rl_pinglv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_control);
        Intent intent = getIntent();
        //访问接口需要的参数
        groupId = intent.getStringExtra("groupId");
        equipId = intent.getStringExtra("equipId");
        stationId = intent.getStringExtra("stationId");
        initview();
        getDateFromService();
        getKGFromService();
        onclickItem();
    }

    //设备开关展示
    private void getKGFromService() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.SKG, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    String status = object.getString("state");
                    if (status.equals("STOP")) {
                        tbdevice.setChecked(false);
                        contor.setVisibility(View.GONE);
                    } else {
                        tbdevice.setChecked(true);
                        contor.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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
                params.put("equipId", equipId);
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }
    boolean flag;
    private void onclickItem() {
        //点击之后进去相应单元里面的模块
        listUnit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Unit unit = deviceControl.getList().get(position);
                Intent intent = new Intent(DeviceControlActivity.this, DeviceModerActivity.class);
                intent.putExtra("unitId", unit.getUnitId());
                intent.putExtra("groupId", groupId);
                intent.putExtra("equipId", equipId);
                intent.putExtra("stationId", stationId);
                intent.putExtra("unit", unit);
                startActivity(intent);
            }
        });

        tbdevice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    contor.setVisibility(View.VISIBLE);
                    tvControl.setTextColor(getResources().getColor(R.color.blue));
                    if (flag) {
                        showDialog1();
                    }

                } else {
                    contor.setVisibility(View.GONE);
                    tvControl.setTextColor(getResources().getColor(R.color.gray));
                    if (!flag) {
                        showDialog1();
                    }


                }
            }
        });
        scrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                String label = DateUtils.formatDateTime(
                        DeviceControlActivity.this,
                        System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME
                                | DateUtils.FORMAT_SHOW_DATE
                                | DateUtils.FORMAT_ABBREV_ALL);
                // 显示最后更新的时间
                refreshView.getLoadingLayoutProxy()
                        .setLastUpdatedLabel(label);
                getDateFromService();
//                getKGFromService();
            }
        });
    }

    //设备开关控制
    private void sendkgToService(final String status) {
        StringRequest request = new StringRequest(Request.Method.POST, Path.kgCortol, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(DeviceControlActivity.this, getResources().getString(R.string.szcg), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("equipId", equipId);
                params.put("siteId", stationId);
                params.put("groupId", groupId);
                params.put("status", status);
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }


    //获取设备电流 单元列表
    private void getDateFromService() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.DeviceControl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                deviceControl = PaseJson.PaseControl(s);
                DeviceUnitAdapter adapter = new DeviceUnitAdapter(DeviceControlActivity.this, (ArrayList<Unit>) deviceControl.getList());
                if (adapter != null) {
                    listUnit.setAdapter(adapter);
                }
                dianliu.setText(deviceControl.getCurrent() + " A");
//                caiyang.setText(deviceControl.getList().get());
                setListViewHeightBasedOnChildren(listUnit);
                scrollView.onRefreshComplete();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("equipId", equipId);
                return params;
            }
        };
        request.setTag("DeviceUnit");
        MyApplication.getHttpQueue().add(request);
    }

    //设备高级功能设置
    private void sendGJToService(final int status) {
        StringRequest request = new StringRequest(Request.Method.POST, Path.SGJ, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("siteId", stationId);
                params.put("groupId", groupId);
                params.put("equipId", equipId);
                params.put("status", String.valueOf(status));
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }

    private void initview() {
        rl_pinglv = (RelativeLayout) findViewById(R.id.rl_pinglv);
        rl_dianliu = (RelativeLayout) findViewById(R.id.rl_dianliu);
        dianliu = (TextView) findViewById(R.id.dianliu);
        scrollView = (PullToRefreshScrollView) findViewById(R.id.Sv);
        rlGao = (RelativeLayout) findViewById(R.id.rl_gaoji);
        rlGao.setOnClickListener(this);
        btnDevice = (Button) findViewById(R.id.btn_device);
        btnUnit = (Button) findViewById(R.id.bt_unit);
        btnDevice.setSelected(true);
        btnDevice.setOnClickListener(this);
        btnUnit.setOnClickListener(this);
        con = (LinearLayout) findViewById(R.id.ll_con);
        llUnit = (LinearLayout) findViewById(R.id.ll_unit);
        tvControl = (TextView) findViewById(R.id.tv_device_control);
        fanhui = (ImageView) findViewById(R.id.device_control_fanhui);
        listUnit = (ListView) findViewById(R.id.list_device_unit);
        fanhui.setOnClickListener(this);
        tbdevice = (ToggleButton) findViewById(R.id.tb_device);
        contor = (LinearLayout) findViewById(R.id.ll_contor);
//        commit = (Button) findViewById(R.id.btn);
//        commit.setOnClickListener(this);
        rl_dianliu.setOnClickListener(this);
        rl_pinglv.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.device_control_fanhui:
                finish();
                break;

            case R.id.btn_device:
                con.setVisibility(View.VISIBLE);
                llUnit.setVisibility(View.GONE);
                btnDevice.setSelected(true);
                btnUnit.setSelected(false);
                break;
            case R.id.bt_unit:
                con.setVisibility(View.GONE);
                llUnit.setVisibility(View.VISIBLE);
                btnUnit.setSelected(true);
                btnDevice.setSelected(false);
                break;
            case R.id.rl_gaoji:
                Intent intent0=new Intent(this,GaojiCaozuoActivity.class);
                startActivity(intent0);
                break;
            case R.id.rl_dianliu:
                Intent intent=new Intent(this,DianliuActivity.class);
                intent.putExtra("equipId", equipId);
                intent.putExtra("siteId", stationId);
                intent.putExtra("groupId", groupId);
                intent.putExtra("number","1");
                startActivity(intent);
                break;
//            case R.id.btn:
//                Toast.makeText(DeviceControlActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
//                finish();
//                break;
            case R.id.rl_pinglv:
                Intent intent1=new Intent(this,PinlvActivity.class);
                intent1.putExtra("equipId", equipId);
                intent1.putExtra("siteId", stationId);
                intent1.putExtra("groupId", groupId);
                startActivity(intent1);
                break;
        }
    }

    private void showDialog() {
        dialog = new ExpertContorlFragment();
        dialog.show(getFragmentManager(), "");

    }

    private void showDialog1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DeviceControlActivity.this)
                .setTitle(getResources().getString(R.string.ts))
                .setMessage(getResources().getString(R.string.qrts))
                .setPositiveButton(getResources().getString(R.string.confirm), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!tbdevice.isChecked()) {
                            flag = true;
                            sendkgToService("1");//1表示开机
                        } else {
                            flag = false;
                            sendkgToService("0");//0表示关机
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(getResources().getString(R.string.qx1), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!tbdevice.isChecked()) {
                            tbdevice.setChecked(true);
                            flag = false;
                        } else {
                            tbdevice.setChecked(false);
                            flag = true;
                        }
                        dialog.dismiss();
                    }
                });
        builder.setCancelable(false);
        builder.show();

    }


    @Override
    public void InputComplete(String text) {
        if (!text.equals("取消")) {


        }
        dialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getHttpQueue().cancelAll("DeviceUnit");
    }


    //解决ScrollView嵌套ListView只显示一行
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
