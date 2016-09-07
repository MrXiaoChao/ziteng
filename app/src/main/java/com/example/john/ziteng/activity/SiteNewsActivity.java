package com.example.john.ziteng.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.john.ziteng.R;
import com.example.john.ziteng.adapter.ListViewNewsAdapter;
import com.example.john.ziteng.application.MyApplication;
import com.example.john.ziteng.domain.NewsInfo;
import com.example.john.ziteng.domain.NewsTotal;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 站点新闻
 * Created by john on 2016/5/26.
 */
public class SiteNewsActivity extends BaseActivity {
    private PullToRefreshListView listview;
    private ListViewNewsAdapter adapter;
    private ArrayList<NewsInfo> list;
    private ArrayList<NewsInfo> listtotal = new ArrayList<NewsInfo>();
    private NewsTotal newsTotal;
    private int Currentpage = 1;//默认第一次加载是第一页
    private int MorePage = 2;
    private ListView lv;
    private int a;
    private String siteId;
    private ImageView back;
    private RelativeLayout pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_news);
        Intent intent = getIntent();
        siteId = intent.getStringExtra("siteId");
        initView();
        getInfoFromService(Currentpage);
    }


    private void initView() {
        pb = (RelativeLayout) findViewById(R.id.rl_progress);
        back = (ImageView) findViewById(R.id.sitenews_fanhui);
        listview = (PullToRefreshListView) findViewById(R.id.lv_date);
        lv = listview.getRefreshableView();
        listview.setMode(PullToRefreshBase.Mode.BOTH);
        listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            protected Object clone() throws CloneNotSupportedException {
                return super.clone();
            }

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                isShowing = false;
                String label = DateUtils.formatDateTime(
                        SiteNewsActivity.this,
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
                        SiteNewsActivity.this,
                        System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME
                                | DateUtils.FORMAT_SHOW_DATE
                                | DateUtils.FORMAT_ABBREV_ALL);
                // 显示最后更新的时间
                refreshView.getLoadingLayoutProxy()
                        .setLastUpdatedLabel(label);
                if (listtotal.size() == 0) {
                    return;
                }
                a = listtotal.size() - list.size() + 1;
                if (MorePage < newsTotal.getTotalPage() + 1) {
                    getInfoFromService(MorePage);
                    MorePage++;
                } else {
                    //延迟1秒刷新
                    listview.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            listview.onRefreshComplete();
                        }
                    }, 1000);
                    Toast.makeText(SiteNewsActivity.this, "已经在最后一页了", Toast.LENGTH_SHORT).show();
                }

            }

        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsInfo info = listtotal.get(position - 1);
                StringBuffer url = new StringBuffer();
                url.append(Path.PicPathWeb);
                url.append("id=");
                url.append(info.getNewId());
                Intent intent = new Intent(SiteNewsActivity.this, WebViewInfoActivity.class);
                intent.putExtra("URL", String.valueOf(url));
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private boolean isShowing;

    private void getInfoFromService(final int page) {
        StringRequest request = new StringRequest(Request.Method.POST, Path.SiteNews, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                pb.setVisibility(View.GONE);
                newsTotal = PaseJson.PaseNews(s);
                if (newsTotal != null) {
                    list = (ArrayList<NewsInfo>) newsTotal.getList();
                    if (list != null) {
                        listtotal.addAll(list);
                        adapter = new ListViewNewsAdapter(SiteNewsActivity.this, listtotal);
                        if (adapter != null) {
                            listview.setAdapter(adapter);
                            if (isShowing) {
                                if (page <= 2) {
                                    lv.setSelection(2);
                                } else {
                                    lv.setSelection(a);
                                }
                            }
                        }
                    }
                    adapter.notifyDataSetChanged();
                    listview.onRefreshComplete();
                } else {
                    Toast.makeText(SiteNewsActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                    listview.onRefreshComplete();
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
                params.put("siteId", siteId);
                params.put("rows", String.valueOf(6));
                params.put("page", String.valueOf(page));
                return params;
            }
        };
        request.setTag("News_5");
        MyApplication.getHttpQueue().add(request);
    }

    public void onDestroy() {
        super.onDestroy();
        listtotal.clear();
        MorePage = 2;
    }
}
