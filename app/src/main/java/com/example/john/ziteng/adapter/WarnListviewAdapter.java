package com.example.john.ziteng.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.john.ziteng.R;
import com.example.john.ziteng.domain.WarnInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 2016/3/30.
 */
public class WarnListviewAdapter extends BaseAdapter {
    private ArrayList<WarnInfo> list;
    private Context context;

    public WarnListviewAdapter(Context context, ArrayList<WarnInfo> list) {
        this.list = list;
        this.context = context;
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
        WarnInfo warnInfo = list.get(position);
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_warn_info, null);
            holder.time = (TextView) convertView.findViewById(R.id.tv_warn_time);
            holder.tital = (TextView) convertView.findViewById(R.id.tv_warn_tital);
            holder.site = (TextView) convertView.findViewById(R.id.tv_warn_site);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.time.setText(warnInfo.getTime());
        holder.tital.setText(warnInfo.getWarnContent());
        holder.site.setText(warnInfo.getSiteName());
        return convertView;
    }

    class ViewHolder {
        TextView time;
        TextView tital;
        TextView site;
    }
}
