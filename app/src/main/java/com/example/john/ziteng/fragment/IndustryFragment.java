package com.example.john.ziteng.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.john.ziteng.R;
import com.example.john.ziteng.activity.WebViewInfoActivity;
import com.example.john.ziteng.adapter.ListViewNewsAdapter;
import com.example.john.ziteng.application.MyApplication;
import com.example.john.ziteng.domain.NewsInfo;
import com.example.john.ziteng.domain.NewsTotal;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;
import com.example.john.ziteng.utils.SPUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 行业动态
 * Created by john on 2016/3/21.
 */
public class IndustryFragment extends Fragment {

    private PullToRefreshListView listView;
    private String mPhone;
    private ArrayList<NewsInfo> list;
    private ArrayList<NewsInfo> listtotal = new ArrayList<NewsInfo>();
    private ListViewNewsAdapter adapter;
    private NewsTotal newsTotal;
    private int Currentpage = 1;//默认第一次加载是第一页
    private int MorePage = 2;
    private ListView lv;
    private int a;
    private RelativeLayout pb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yeardate, container, false);
        mPhone = (String) SPUtils.get(getActivity(), "phone", "");
        initView(view);
        getInfoFromService(Currentpage);
        return view;
    }

    private void initView(View view) {
        pb = (RelativeLayout) view.findViewById(R.id.rl_progress);
        listView = (PullToRefreshListView) view.findViewById(R.id.lv_year_date);
        lv = listView.getRefreshableView();
        //上下拉刷新
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                isShowing = false;
                String label = DateUtils.formatDateTime(
                        getActivity(),
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
                        getActivity(),
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
                if (MorePage < newsTotal.getTotalPage() + 1) {
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
                    Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.zhyy), Toast.LENGTH_SHORT).show();
                }


            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsInfo info = listtotal.get(position - 1);
                StringBuffer url = new StringBuffer();
                url.append(Path.PicPathWeb);
                url.append("id=");
                url.append(info.getNewId());
                Intent intent = new Intent(getActivity(), WebViewInfoActivity.class);
                intent.putExtra("URL", String.valueOf(url));
                startActivity(intent);

            }
        });
    }

    private boolean isShowing;

    private void getInfoFromService(final int page) {
        StringRequest request = new StringRequest(Request.Method.POST, Path.NewsPath, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                pb.setVisibility(View.GONE);
                newsTotal = PaseJson.PaseNews(s);
                if (newsTotal != null) {
                    list = (ArrayList<NewsInfo>) newsTotal.getList();
                    listtotal.addAll(list);
                    adapter = new ListViewNewsAdapter(getActivity(), listtotal);
                    if (getActivity() != null && adapter != null) {
                        listView.setAdapter(adapter);
                        if (isShowing) {
                            if (page <= 2) {
                                lv.setSelection(2);
                            } else {
                                lv.setSelection(a);
                            }
                        }
                    }
                    adapter.notifyDataSetChanged();
                    listView.onRefreshComplete();
                } else {
                    Toast.makeText(getActivity(), "暂无数据", Toast.LENGTH_SHORT).show();
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
                params.put("info.category", "3");
                params.put("username", mPhone);
                params.put("rows", String.valueOf(5));
                params.put("page", String.valueOf(page));
                return params;
            }
        };
        request.setTag("News_3");
        MyApplication.getHttpQueue().add(request);
    }

    public void onDestroyView() {
        super.onDestroyView();
        listtotal.clear();
        MorePage = 2;
    }


}
