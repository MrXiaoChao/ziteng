package com.example.john.ziteng.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.john.ziteng.R;
import com.example.john.ziteng.domain.DeviceGroupInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 多级下拉列表
 * Created by john on 2016/3/28.
 */
public class MyListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<DeviceGroupInfo.UnitlistBean> list;

    public MyListAdapter(Context context, ArrayList<DeviceGroupInfo.UnitlistBean> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder holder=null;
        if (convertView==null){
            holder=new Viewholder();
            convertView=LayoutInflater.from(context).inflate(R.layout.childlist, null);
            holder.childTextView= (TextView) convertView.findViewById(R.id.childTextView);
            convertView.setTag(holder);
        }else {
            holder= (Viewholder) convertView.getTag();
        }
        holder.childTextView.setText("单元-"+list.get(position).getUnitId().substring(5,7));
        return convertView;
    }
    class Viewholder{
        TextView childTextView;
    }
}
