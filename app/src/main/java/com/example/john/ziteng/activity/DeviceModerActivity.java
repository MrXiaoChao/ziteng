package com.example.john.ziteng.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
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
import com.example.john.ziteng.application.MyApplication;
import com.example.john.ziteng.domain.DeviceModer;
import com.example.john.ziteng.fragment.ExpertContorlFragment;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设备控制 单元  模块
 * Created by john on 2016/4/11.
 */
public class DeviceModerActivity extends Activity implements View.OnClickListener, ExpertContorlFragment.InputListener {

    private LinearLayout unit;
    private LinearLayout moder;
    private ToggleButton tbModer;
    private Button btnUnit;
    private Button btnModer;
    private TextView unitContor;
    private RelativeLayout gcontorl;
    private TextView gaoji;
    private ExpertContorlFragment dialog;
    private ImageView fanhui;
    private String unitId;
    private String equipId;
    private String stationId;
    private String groupId;
    private Button commit;
    private ListView listmoder;
    private Button btn_tijia;
    private ArrayList<DeviceModer> deviceModers;
    private String str = null;
    private DeviceModer moder1;
    List<JSONObject> stringList = new ArrayList<>();
    private LinearLayout lldan;
    private PullToRefreshScrollView scrollView;
    private RelativeLayout rl_dianliu;
    private RelativeLayout rl_gaoji;
    private boolean flag;
    private TextView dianliu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moder);
        Intent intent = getIntent();
        unitId = intent.getStringExtra("unitId");
        equipId = intent.getStringExtra("equipId");
        stationId = intent.getStringExtra("stationId");
        groupId = intent.getStringExtra("groupId");
        initview();
        getDateFromService();
        getKGFromService();
        getDLFromService();
    }

    private void getDateFromService() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.MDl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                deviceModers = PaseJson.PaseModer(s);
                if (deviceModers != null) {
                    listmoder.setAdapter(new DeviceMondlerAdapter(DeviceModerActivity.this, deviceModers));
                }
                setListViewHeightBasedOnChildren(listmoder);
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
                params.put("unitId", unitId);
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }

    //单元电流展示
    private void getDLFromService() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.Udl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    String current = object.getString("setting_current");
                    dianliu.setText(current+" A");
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
                params.put("unitId", unitId);
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }

    //单元开关展示
    private void getKGFromService() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.Ukg, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    String status = object.getString("system_status");
                    if (status.equals("STOP")) {
                        tbModer.setChecked(false);
                    } else {
                        tbModer.setChecked(true);
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
                params.put("unitId", unitId);
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }

    //单元开关控制
    private void SendKgToService(final String status) {
        StringRequest request = new StringRequest(Request.Method.POST, Path.UkgC, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(DeviceModerActivity.this,getResources().getString(R.string.szcg),Toast.LENGTH_SHORT).show();
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
                params.put("siteId", stationId);
                params.put("groupId", groupId);
                params.put("unitId", unitId);
                params.put("status", status);
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
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


    private void initview() {
        dianliu = (TextView) findViewById(R.id.dianliu);
        rl_gaoji = (RelativeLayout) findViewById(R.id.rl_gaoji);
        rl_dianliu = (RelativeLayout) findViewById(R.id.rl_dianliu);
        scrollView = (PullToRefreshScrollView) findViewById(R.id.svw);
        lldan = (LinearLayout) findViewById(R.id.ll_danyuan);
        listmoder = (ListView) findViewById(R.id.list_device_moder);
        fanhui = (ImageView) findViewById(R.id.moder_fanhui);
        fanhui.setOnClickListener(this);

        unitContor = (TextView) findViewById(R.id.tv_unit_contor);
        unit = (LinearLayout) findViewById(R.id.ll_unitContor);
        moder = (LinearLayout) findViewById(R.id.ll_moder);
        tbModer = (ToggleButton) findViewById(R.id.tb_moder);
        btnUnit = (Button) findViewById(R.id.btn_unit);
        btnModer = (Button) findViewById(R.id.bt_moder);

        btnUnit.setOnClickListener(this);
        btnModer.setOnClickListener(this);
        btnUnit.setSelected(true);
        rl_dianliu.setOnClickListener(this);
        rl_gaoji.setOnClickListener(this);

        tbModer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    unit.setVisibility(View.VISIBLE);
                    unitContor.setTextColor(getResources().getColor(R.color.blue));
                    if (flag) {
                        showDialog1();
                    }

                } else {
                    unit.setVisibility(View.GONE);
                    unitContor.setTextColor(getResources().getColor(R.color.gray));
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
                        DeviceModerActivity.this,
                        System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME
                                | DateUtils.FORMAT_SHOW_DATE
                                | DateUtils.FORMAT_ABBREV_ALL);
                // 显示最后更新的时间
                refreshView.getLoadingLayoutProxy()
                        .setLastUpdatedLabel(label);
                getDateFromService();
                getKGFromService();
                getDLFromService();
            }
        });
        listmoder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent(DeviceModerActivity.this,DianliuActivity.class);
                intent.putExtra("moduleId",deviceModers.get(position).getModuleId());
                intent.putExtra("siteId",stationId);
                intent.putExtra("groupId",groupId);
                intent.putExtra("equipId",equipId);
                intent.putExtra("unitId",unitId);
                intent.putExtra("number","3");
                startActivity(intent);
            }
        });

    }

    private void showDialog() {
        dialog = new ExpertContorlFragment();
        dialog.show(getFragmentManager(), "");
    }

    @Override
    public void InputComplete(String text) {
        if (!text.equals("取消")) {

        }
        dialog.dismiss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_unit:
                lldan.setVisibility(View.VISIBLE);
                btnUnit.setSelected(true);
                btnModer.setSelected(false);
                moder.setVisibility(View.GONE);
                unit.setVisibility(View.VISIBLE);
                break;
            case R.id.bt_moder:
                lldan.setVisibility(View.GONE);
                btnUnit.setSelected(false);
                btnModer.setSelected(true);
                moder.setVisibility(View.VISIBLE);
                unit.setVisibility(View.GONE);
                break;


            case R.id.moder_fanhui:
                finish();
                break;

            case R.id.rl_dianliu:
                Intent intent=new Intent(this,DianliuActivity.class);
                intent.putExtra("equipId", equipId);
                intent.putExtra("siteId", stationId);
                intent.putExtra("groupId", groupId);
                intent.putExtra("unitId", unitId);
                intent.putExtra("number","0");
                startActivity(intent);
                break;
            case R.id.rl_gaoji:
                Intent intent1=new Intent(this,GaojiCaozuoActivity.class);
                startActivity(intent1);
                break;
        }

    }
    //单元高级功能设置
    private void sendGJToService(final int status) {
        StringRequest request=new StringRequest(Request.Method.POST, Path.DGJ, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("siteId",stationId);
                params.put("groupId",groupId);
                params.put("equipId",equipId);
                params.put("unitId",unitId);
                params.put("status",String.valueOf(status));
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }


    private void showDialog1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DeviceModerActivity.this)
                .setTitle(getResources().getString(R.string.ts))
                .setMessage(getResources().getString(R.string.qrts))
                .setPositiveButton(getResources().getString(R.string.confirm), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!tbModer.isChecked()) {
                            flag = true;
                            SendKgToService("1");
                        } else {
                            flag=false;
                            SendKgToService("0");
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(getResources().getString(R.string.qx1), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!tbModer.isChecked()) {
                            tbModer.setChecked(true);
                            flag = false;
                        } else {
                            tbModer.setChecked(false);
                            flag=true;
                        }
                        dialog.dismiss();
                    }
                });
        builder.setCancelable(false);
        builder.show();

    }

    //模块列表中的adapter
    public class DeviceMondlerAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<DeviceModer> list;
        public DeviceMondlerAdapter(Context context, ArrayList<DeviceModer> list) {
            this.context = context;
            this.list = list;
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            moder1 = list.get(position);
            final viewHolder holder;
            if (convertView == null) {
                holder = new viewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.list_moder, null);
                holder.moder = (TextView) convertView.findViewById(R.id.tv_device_moder);
                holder.tv_dianliu= (TextView) convertView.findViewById(R.id.tv_dianliu);
                convertView.setTag(holder);
            } else {
                holder = (viewHolder) convertView.getTag();
            }
            holder.moder.setText(getResources().getString(R.string.mka)+"-" + moder1.getModuleId().substring(5, 7));
            holder.moder.setTextColor(context.getResources().getColor(R.color.blue));
            holder.tv_dianliu.setText(String.valueOf(moder1.getElectricCurren()) + " A");
            return convertView;
        }

        class viewHolder {
            TextView moder;
            TextView tv_dianliu;
        }
    }
}
