package com.example.john.ziteng.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.john.ziteng.R;
import com.example.john.ziteng.domain.Shebeigaojing;
import com.example.john.ziteng.domain.WarnInfo;
import com.example.john.ziteng.view.MoreTextView;

import java.util.ArrayList;

/**
 * Created by john on 2016/3/30.
 */
public class WarnListviewAdapter1 extends BaseAdapter {
    private ArrayList<Shebeigaojing> list;
    private Context context;

    public WarnListviewAdapter1(Context context, ArrayList<Shebeigaojing> list) {
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
        Shebeigaojing shebeigaojing = list.get(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.activity_sitegaojing, null);
        TextView time = (TextView) convertView.findViewById(R.id.tv_time);
        TextView content = (TextView) convertView.findViewById(R.id.tv_content);
        time.setText(shebeigaojing.getTime());
        content.setText(shebeigaojing.getWarnContent());
        return convertView;
    }
}
