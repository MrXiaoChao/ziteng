package com.example.john.ziteng.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.john.ziteng.R;
import com.example.john.ziteng.adapter.SiteListAdapter;
import com.example.john.ziteng.application.MyApplication;
import com.example.john.ziteng.domain.CityPid;
import com.example.john.ziteng.domain.MapSiteInfo;
import com.example.john.ziteng.domain.SiteListInfo;
import com.example.john.ziteng.domain.SiteLists;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;
import com.example.john.ziteng.utils.SPUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 站点全列表
 * Created by john on 2016/5/4.
 */
public class SiteListActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back;
    private PullToRefreshListView listView;
    private LinearLayout siteInfo;
    private ImageView img;
    private TextView siteName;
    private TextView guanzhu;
    private String mPhone;
    private SiteLists siteLists;
    private int Currentpage = 1;//默认第一次加载是第一页
    private int MorePage = 2;
    private ArrayList<SiteListInfo> list;
    private ArrayList<SiteListInfo> listtotal = new ArrayList<>();
    private SiteListAdapter adapter;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
    private MapSiteInfo mapSiteInfo;
    private SiteListInfo siteListInfo;
    private ImageView search;
    private RelativeLayout rltop;
    private EditText etSearch;
    private ImageView ivDeleteText;
    private TextView tvSearch;
    private List<CityPid> listcity;
    private ProgressDialog dialog;
    private ListView lv;
    private int a;
    private RelativeLayout pb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_list);
        mPhone = (String) SPUtils.get(SiteListActivity.this, "phone", "");
        initImageLoader();
        getPidFromService();
        initview();
        getInfoFromService(Currentpage);
    }

    private void initview() {
        pb = (RelativeLayout) findViewById(R.id.rl_progress);
        back = (ImageView) findViewById(R.id.sitelist_fanhui);
        listView = (PullToRefreshListView) findViewById(R.id.sitelist_lv);
        lv = listView.getRefreshableView();
        siteInfo = (LinearLayout) findViewById(R.id.sitelist_info);
        img = (ImageView) findViewById(R.id.sitelist_img);
        siteName = (TextView) findViewById(R.id.sitelist_name);
        guanzhu = (TextView) findViewById(R.id.tv_guanzhu);
        //搜索框
        search = (ImageView) findViewById(R.id.iv_search);
        rltop = (RelativeLayout) findViewById(R.id.top);
        etSearch = (EditText) findViewById(R.id.etSearch);
        ivDeleteText = (ImageView) findViewById(R.id.ivDeleteText);
        tvSearch = (TextView) findViewById(R.id.tvSearch);
        ivDeleteText.setOnClickListener(this);
        tvSearch.setOnClickListener(this);
        search.setOnClickListener(this);
        //自定义搜索框字体的监控
        etSearch.addTextChangedListener(new MyTextWatcher());
        back.setOnClickListener(this);
        //上下拉刷新初始化
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                isShowing = false;
                String label = DateUtils.formatDateTime(
                        SiteListActivity.this,
                        System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME
                                | DateUtils.FORMAT_SHOW_DATE
                                | DateUtils.FORMAT_ABBREV_ALL);
                // 显示最后更新的时间
                refreshView.getLoadingLayoutProxy()
                        .setLastUpdatedLabel(label);
                MorePage = 2;
                listtotal.clear();
                getInfoFromService(Currentpage);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                isShowing = true;
                String label = DateUtils.formatDateTime(
                        SiteListActivity.this,
                        System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME
                                | DateUtils.FORMAT_SHOW_DATE
                                | DateUtils.FORMAT_ABBREV_ALL);
                // 显示最后更新的时间
                refreshView.getLoadingLayoutProxy()
                        .setLastUpdatedLabel(label);
                if (listtotal.size()==0){
                    return;
                }
                a = listtotal.size() - list.size() + 1;
                if (MorePage < siteLists.getTotalPage() + 1) {
                    getInfoFromService(MorePage);
                    MorePage++;
                } else {
                    //延迟1秒刷新
                    listView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            listView.onRefreshComplete();
                        }
                    }, 1000);
                    Toast.makeText(SiteListActivity.this, getResources().getString(R.string.zhyy), Toast.LENGTH_SHORT).show();
                }

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                siteInfo.setVisibility(View.VISIBLE);
                siteListInfo = listtotal.get(position - 1);
                popupInfo(siteInfo, siteListInfo);
                if (siteListInfo.getFocus().equals("已关注")) {
                    guanzhu.setText(getResources().getString(R.string.ygz));
                } else {
                    guanzhu.setText(getResources().getString(R.string.wgz));
                }
                getSiteInfo();
            }
        });
    }

    private boolean isShowing;

    private void getInfoFromService(final int page) {
        StringRequest request = new StringRequest(Request.Method.POST, Path.SiteList, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                pb.setVisibility(View.GONE);
                siteLists = PaseJson.PaseSiteLists(s);
                if (siteLists != null) {
                    list = (ArrayList<SiteListInfo>) siteLists.getSiteListInfos();
                    if (list != null) {
                        listtotal.addAll(list);
                        adapter = new SiteListAdapter(SiteListActivity.this, listtotal);
                        if (adapter != null) {
                            listView.setAdapter(adapter);
                        }
                    }
                    adapter.notifyDataSetChanged();
                    listView.onRefreshComplete();
                    if (isShowing) {
                        if (page <= 2) {
                            lv.setSelection(2);
                        } else {
                            lv.setSelection(a);
                        }
                    }

                } else {
                    Toast.makeText(SiteListActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
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
                params.put("page", String.valueOf(page));
                params.put("rows", String.valueOf(6));
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }

    private boolean isfrist = true;
    private boolean istrue;
    private int pId;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sitelist_fanhui:
                finish();
                break;
            case R.id.iv_search://搜索
                if (siteInfo != null) {
                    siteInfo.setVisibility(View.GONE);
                }
                if (isfrist) {
                    rltop.setVisibility(View.VISIBLE);
                    isfrist = false;
                } else {
                    rltop.setVisibility(View.GONE);
                    etSearch.setText("");
                    isfrist = true;
                }

                break;
            case R.id.tvSearch://搜索
                rltop.setVisibility(View.GONE);
                String city = null;
                String cityname = etSearch.getText().toString().trim();
                for (CityPid cityPid : listcity) {
                    city = cityPid.getpName();
                    if (cityname.equals(city)) {
                        istrue = true;
                        pId = cityPid.getpId();
                        showDialog();
                        sendCityToService(pId);
                    }
                }
                if (cityname.equals("")) {
                    showDialog();
                    istrue = false;
                    sendCityToService(pId);
                }

                break;
            case R.id.ivDeleteText://清除功能
                etSearch.setText("");
                break;
        }

    }

    //搜索
    private void sendCityToService(final int pId) {
        StringRequest request = new StringRequest(Request.Method.POST, Path.SiteList, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                disDialog();
                siteLists = PaseJson.PaseSiteLists(s);
                if (siteLists != null) {
                    if (listtotal != null && listtotal.size() > 0) {
                        listtotal.clear();
                    }
                    listtotal = (ArrayList<SiteListInfo>) siteLists.getSiteListInfos();
                    if (listtotal != null) {
                        adapter = new SiteListAdapter(SiteListActivity.this, listtotal);
                        if (adapter != null) {
                            listView.setAdapter(adapter);
                        }
                    }
                    adapter.notifyDataSetChanged();
                    listView.onRefreshComplete();
                } else {
                    Toast.makeText(SiteListActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("loginname", mPhone);
                if (istrue) {
                    params.put("province", String.valueOf(pId));
                }
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        listtotal.clear();
        MorePage = 2;
    }

    public void popupInfo(LinearLayout linearLayout, final SiteListInfo info) {
        ViewHolder viewHolder = null;
        if (linearLayout.getTag() == null) {
            viewHolder = new ViewHolder();
            viewHolder.siteinfo = (TextView) linearLayout.findViewById(R.id.tv_site_info);
            viewHolder.guanzhu = (TextView) linearLayout.findViewById(R.id.tv_guanzhu);
            viewHolder.img = (ImageView) linearLayout.findViewById(R.id.sitelist_img);
            viewHolder.name = (TextView) linearLayout.findViewById(R.id.sitelist_name);
            linearLayout.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) linearLayout.getTag();
        }
        //权限问题
        if (!info.getManage().equals("1")){
            viewHolder.guanzhu.setVisibility(View.GONE);
        }else {
            viewHolder.guanzhu.setVisibility(View.VISIBLE);
        }
        imageLoader.displayImage(info.getUrl(), viewHolder.img, options);
        viewHolder.name.setText(info.getName());
        viewHolder.guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info.getFocus().equals("已关注")) {
                    sendGuanzhu(0, info);
                } else {
                    sendGuanzhu(1, info);
                }
                adapter.notifyDataSetChanged();
                siteInfo.setVisibility(View.GONE);
            }
        });
        viewHolder.siteinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siteInfo.setVisibility(View.GONE);
                Intent intent = new Intent(SiteListActivity.this, SiteDetailActivity.class);
                if (mapSiteInfo == null) {
                    return;
                }
                intent.putExtra("cityId", mapSiteInfo.getCITY());
                intent.putExtra("siteId",info.getSiteId());
                startActivity(intent);
            }
        });
    }

    private class ViewHolder {
        ImageView img;
        TextView name;
        TextView guanzhu;
        TextView siteinfo;
    }

    //自定义搜索框清除功能
    private class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() == 0) {
                ivDeleteText.setVisibility(View.GONE);
            } else {
                ivDeleteText.setVisibility(View.VISIBLE);

            }
        }
    }

    private void initImageLoader() {
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.kong)
                .showImageOnFail(R.mipmap.kong)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    //关注 未关注
    private void sendGuanzhu(final int fourcs, final SiteListInfo info) {
        StringRequest request = new StringRequest(Request.Method.POST, Path.ChangFourcs, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    String focus = object.getString("focus");
                    if (focus.equals("已关注")) {
                        guanzhu.setText(getResources().getString(R.string.ygz));
                    }else {
                        guanzhu.setText(getResources().getString(R.string.wgz));
                    }
                    info.setFocus(focus);
                    adapter.notifyDataSetChanged();
                    if (info.getFocus().equals("已关注")) {
                        guanzhu.setText(getResources().getString(R.string.ygz));
                        Toast.makeText(SiteListActivity.this, getResources().getString(R.string.gz), Toast.LENGTH_SHORT).show();
                    } else {
                        guanzhu.setText(getResources().getString(R.string.wgz));
                        Toast.makeText(SiteListActivity.this, getResources().getString(R.string.qx), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("change", String.valueOf(fourcs));
                params.put("siteId", info.getSiteId());
                params.put("loginname", mPhone);
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }

    //获取站点的详情
    private void getSiteInfo() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.MapSiteInfo, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                List<MapSiteInfo> list = PaseJson.PaseMapSite(s);
                mapSiteInfo = list.get(0);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("siteId", siteListInfo.getSiteId());
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }

    //获取城市的pid
    private void getPidFromService() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.CityPid, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                listcity = PaseJson.PaseCityPid(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        MyApplication.getHttpQueue().add(request);
    }

    private void showDialog() {
        dialog = new ProgressDialog(SiteListActivity.this);
        dialog.setMessage(getResources().getString(R.string.zzjz));
        dialog.show();
    }

    private void disDialog() {
        if (dialog.isShowing() && dialog != null) {
            dialog.dismiss();
        }
    }
}
