package com.example.john.ziteng.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.john.ziteng.R;
import com.example.john.ziteng.activity.DeviceGroupActivity;
import com.example.john.ziteng.adapter.DeviceGroupExListAdapter;
import com.example.john.ziteng.application.MyApplication;
import com.example.john.ziteng.domain.DeviceGroup;
import com.example.john.ziteng.domain.SiteInfo;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;
import com.example.john.ziteng.utils.SPUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 设备群入口
 * Created by john on 2016/3/25.
 */
public class DeviceGroupFragment extends Fragment {

    private SiteInfo site;
    private ArrayList<DeviceGroup> list;
    private RelativeLayout pb;
    private ExpandableListView deviceExlist;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_site_qun, container, false);
        initView(view);
        Intent intent = getActivity().getIntent();
        site = (SiteInfo) intent.getSerializableExtra("siteInfo");
        getDateFromService();
        return view;
    }

    private void getDateFromService() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.DeviceGroup, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                pb.setVisibility(View.GONE);
                list = PaseJson.PaseDevice(s,getActivity());
                DeviceGroupExListAdapter adapter = new DeviceGroupExListAdapter(getActivity(), list);
                if (getActivity() != null && adapter != null) {
                    deviceExlist.setAdapter(adapter);
                    deviceExlist.expandGroup(0);
                    deviceExlist.setGroupIndicator(null); //系统默认的图标给隐藏了
                }
                deviceExlist.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
//                        Toast.makeText(DeviceGroupActivity.this, "第" + groupPosition + "组被点击", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
               deviceExlist.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                   @Override
                   public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                       DeviceGroup deviceGroup = list.get(groupPosition);
                       if (childPosition >4) {
                           String equipId = String.valueOf(SPUtils.put(getActivity(), "equipId", deviceGroup.getEquipList().get(childPosition).getEquipId()));
                           Intent intent = new Intent(getActivity(), DeviceGroupActivity.class);
                           intent.putExtra("group_id", deviceGroup.getGroupId());
                           intent.putExtra("site", site);
                           intent.putExtra("equipId", deviceGroup.getEquipList().get(childPosition).getEquipId());
                           startActivity(intent);
                       }
                       return false;
                   }
               });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("siteId", site.getSite_id());
                return params;
            }
        };
        request.setTag("DeviceGroup");
        MyApplication.getHttpQueue().add(request);
    }

    private void initView(View view) {
        deviceExlist = (ExpandableListView) view.findViewById(R.id.devicegroup_exlist);
        pb = (RelativeLayout) view.findViewById(R.id.rl_progress);
//        deviceExlist.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
//        deviceExlist.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ExpandableListView>() {
//            @Override
//            public void onRefresh(PullToRefreshBase<ExpandableListView> refreshView) {
//                String label = DateUtils.formatDateTime(
//                        getActivity(),
//                        System.currentTimeMillis(),
//                        DateUtils.FORMAT_SHOW_TIME
//                                | DateUtils.FORMAT_SHOW_DATE
//                                | DateUtils.FORMAT_ABBREV_ALL);
//                // 显示最后更新的时间
//                refreshView.getLoadingLayoutProxy()
//                        .setLastUpdatedLabel(label);
//                getDateFromService();
//            }
//        });

    }
}
