package com.example.john.ziteng.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.john.ziteng.R;
import com.example.john.ziteng.domain.DeviceGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 2016/7/22.
 * 设备群列表
 */
public class DeviceGroupExListAdapter extends BaseExpandableListAdapter {

    Context context;
    List<DeviceGroup> groupList = new ArrayList<>();

    public DeviceGroupExListAdapter(Context context, List<DeviceGroup> groupList) {
        this.context = context;
        this.groupList = groupList;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return groupList.get(i).getEquipList().size();  //每一个Group 中的Child 数目相同
    }

    @Override
    public Object getGroup(int i)    {
        return groupList.get(i);
    }

    @Override
    public Object getChild(int i, int i2) {
        return groupList.get(i).getEquipList().get(i2);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i2) {
        return i2;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //父listview视图
    @Override
    public View getGroupView(int groupPosition, boolean groupIsSelected, View view, ViewGroup viewGroup) {
        DeviceGroup doi = groupList.get(groupPosition);
        view = LayoutInflater.from(context).inflate(R.layout.grouplist, null);
        TextView textView = (TextView) view.findViewById(R.id.groupTextView);
        textView.setText("设备群-"+doi.getGroupName());
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        if (groupIsSelected) {
            imageView.setImageDrawable(context.getResources().getDrawable(R.mipmap.down));
        } else {
            imageView.setImageDrawable(context.getResources().getDrawable(R.mipmap.buleyou));
        }
        return view;
    }

    //子listview视图

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean childIsSelected, View view, ViewGroup viewGroup) {
        DeviceGroup.EquipListBean equipListBean = groupList.get(groupPosition).getEquipList().get(childPosition);
        view = LayoutInflater.from(context).inflate(R.layout.childlist, null);
        TextView textView = (TextView) view.findViewById(R.id.childTextView);
        ImageView image = (ImageView) view.findViewById(R.id.image);
        if (childPosition == 0) {
            textView.setTextColor(context.getResources().getColor(R.color.gray));
            textView.setText(equipListBean.getEquip_id());
            image.setImageDrawable(context.getResources().getDrawable(R.mipmap.you2));
        } else {
            textView.setText("设备" + equipListBean.getEquip_id());
        }
        return view;
    }

    //此方法是用来触发子listview 是否可以点击的  默认为false
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
