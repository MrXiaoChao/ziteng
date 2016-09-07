package com.example.john.ziteng.fragment;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
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
import com.example.john.ziteng.activity.GaoJingInfoActivity;
import com.example.john.ziteng.activity.MainActivity;
import com.example.john.ziteng.activity.SiteDetailActivity;
import com.example.john.ziteng.activity.SiteDetailActivity2;
import com.example.john.ziteng.adapter.ListViewSiteAdapter;
import com.example.john.ziteng.adapter.ViewPagerAdapter;
import com.example.john.ziteng.application.MyApplication;
import com.example.john.ziteng.citylist.CityListActivity;
import com.example.john.ziteng.domain.Pic;
import com.example.john.ziteng.domain.SiteInfo;
import com.example.john.ziteng.domain.Sitefours;
import com.example.john.ziteng.domain.WarnInfo;
import com.example.john.ziteng.loopviewpager.AutoScrollViewPager;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;
import com.example.john.ziteng.utils.SPUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 关注站点
 * Created by john on 2016/3/17.
 */
public class SiteFragment extends Fragment implements View.OnClickListener {

    private TextView title;
    private TextView left;
    private ImageView right;
    // 广告语
    private AutoScrollViewPager mViewPager;
    private TextView mTextView;
    private DisplayImageOptions options;
    private ImageLoader imageLoader;
    private CirclePageIndicator indicator;
    private List<SiteInfo> list;
    private ArrayList<SiteInfo> listtotal = new ArrayList<SiteInfo>();//叠加后的list
    private ArrayList<Pic> listPic;
    private Pic pic;
    private String mPhone;
    private int Currentpage = 1;//默认第一次加载是第一页
    private int MorePage = 2;
    private Sitefours sitefours;
    private String city;
    private String cityid;
    private PullToRefreshListView listView;
    private ListView lv;
    private int a;
    private RelativeLayout pb;
    private String level;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_site, container, false);
        mPhone = (String) SPUtils.get(getActivity(), "phone", "");
        level = (String) SPUtils.get(getActivity(), "level", "一级用户");
        initImageLoader();
        initView(view);
        getSitePic();
        getCountFromService();
        return view;
    }


    //关注站点轮播图数据
    private void getSitePic() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.PicPath, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                listPic = (ArrayList<Pic>) PaseJson.PasePic(s);
                //解决滑动冲突:传入viewpager的父view
                mViewPager.setNestedpParent((ViewGroup) mViewPager.getParent());
                mViewPager.setAdapter(new ViewPagerAdapter(getActivity(), listPic));
                indicator.setViewPager(mViewPager);
                indicator.setSnap(true);
                indicator.setOnPageChangeListener(new MyListen());
                mViewPager.setInterval(1000 * 5);//自动轮播间隔5秒
                mViewPager.startAutoScroll();//开启自动轮播
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", mPhone);
                return params;
            }
        };
        request.setTag("Pic");
        MyApplication.getHttpQueue().add(request);
    }

    private boolean isShowing;//上拉加载定位在item最后一个

    private void getDateFromServer(final int page) {
        StringRequest request = new StringRequest(Request.Method.POST, Path.SitePath, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                pb.setVisibility(View.GONE);
                sitefours = PaseJson.PaseSite(s);
                if (sitefours != null) {
                    list = sitefours.getList();
                    listtotal.addAll(list);
                    if (listtotal == null) {
                        return;
                    }
                    ListViewSiteAdapter adapter = new ListViewSiteAdapter(getActivity(), listtotal);
                    if (getActivity() != null && adapter != null) {
                        lv.setAdapter(adapter);
                        if (isShowing) {
                            if (page <= 2) {
                                lv.setSelection(3);
                            } else {
                                lv.setSelection(a);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                    listView.onRefreshComplete();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                int row = 6;
                Map<String, String> params = new HashMap<String, String>();
                params.put("loginname", mPhone);
                params.put("rows", String.valueOf(row));
                params.put("page", String.valueOf(page));
                if (cityid != null) {
                    params.put("city", cityid);
                }
                return params;
            }
        };
        request.setTag("Site");
        MyApplication.getHttpQueue().add(request);
    }

    private void initView(View view) {
        //标题栏
        title = (TextView) view.findViewById(R.id.title);
        left = (TextView) view.findViewById(R.id.left);
        right = (ImageView) view.findViewById(R.id.right);
        title.setText(R.string.site);
        pb = (RelativeLayout) view.findViewById(R.id.rl_progress);
        //左边的小图标
        Drawable dibiao = getResources().getDrawable(R.mipmap.dibiao);
        dibiao.setBounds(0, 0, dibiao.getMinimumWidth(), dibiao.getMinimumHeight());
        left.setVisibility(View.VISIBLE);
        if (cityid == null) {
            left.setText(R.string.all);
        } else {
            left.setText(city);
        }
        left.setCompoundDrawablePadding(6);
        left.setCompoundDrawables(dibiao, null, null, null);
        left.setOnClickListener(this);
        right.setOnClickListener(this);
        if (level.equals("一级用户") || level.equals("二级用户")) {
            right.setVisibility(View.GONE);
        } else {
            right.setBackgroundResource(R.mipmap.xiaoxi12);
        }


        //轮播图所需要的控件初始化
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT);
        View header = getActivity().getLayoutInflater().inflate(R.layout.listview_headview, listView, false);
        header.setLayoutParams(layoutParams);
        mViewPager = (AutoScrollViewPager) header.findViewById(R.id.viewpager);
        mTextView = (TextView) header.findViewById(R.id.tv_bannertext);
        indicator = (CirclePageIndicator) header.findViewById(R.id.indicator);

        //listview 组件初始化
        listView = (PullToRefreshListView) view.findViewById(R.id.lv_site);
        lv = listView.getRefreshableView();
        lv.addHeaderView(header);

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
                getDateFromServer(Currentpage);
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
                if (listtotal.size() == 0) {
                    return;
                }
                a = listtotal.size() - list.size() + 2;
                if (MorePage < sitefours.getTotalpage() + 1) {
                    getDateFromServer(MorePage);
                    MorePage++;

                } else {
                    //延迟1秒刷新
                    listView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            listView.onRefreshComplete();
                        }
                    }, 1000);
                    Toast.makeText(getActivity(), "已经在最后一页了", Toast.LENGTH_SHORT).show();
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SiteInfo siteInfo = listtotal.get(position - 2);
                if (level.equals("一级用户") || level.equals("二级用户")) {
                    Intent intent = new Intent(getActivity(), SiteDetailActivity2.class);
                    intent.putExtra("siteInfo", siteInfo);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity(), SiteDetailActivity.class);
                    intent.putExtra("siteInfo", siteInfo);
                    startActivity(intent);
                }
            }
        });
    }

    public class MyListen implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            pic = listPic.get(position);
            mTextView.setText(pic.getTitile());
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right:
                Intent intent = new Intent(getActivity(), GaoJingInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.left:
                Intent intent1 = new Intent(getActivity(), CityListActivity.class);
                startActivity(intent1);
                getActivity().finish();
                break;
        }
    }

    //初始化加载图片配置
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


    //监控消息数目
    private void getCountFromService() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.SiteGaojing, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                ArrayList<WarnInfo> list = (ArrayList<WarnInfo>) PaseJson.PaseWarnInfo(s);
                if (list.size() > 0) {
                    right.setBackgroundResource(R.mipmap.xiaoxi20);
                } else {
                    right.setBackgroundResource(R.mipmap.xiaoxi12);
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
                return params;
            }
        };
        request.setTag("siteWarn");
        MyApplication.getHttpQueue().add(request);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        listtotal.clear();
        MorePage = 2;
        if (mViewPager != null) {
            mViewPager.stopAutoScroll();
        }//关闭自动轮播
    }

    @Override
    public void onStart() {
        super.onStart();
        MorePage = 2;
        listtotal.clear();
        getDateFromServer(Currentpage);
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        Intent in = context.getIntent();
        city = in.getStringExtra("city");
        cityid = in.getStringExtra("cityid");
    }
}
