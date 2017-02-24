package com.example.john.ziteng.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.john.ziteng.R;
import com.example.john.ziteng.domain.SiteListInfo;

import java.util.ArrayList;

/**
 * 站点全列表
 * Created by john on 2016/5/4.
 */
public class SiteListAdapter extends BaseAdapter {
    private ArrayList<SiteListInfo> list;
    private Context context;


    public SiteListAdapter(Context context, ArrayList<SiteListInfo> list) {
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
        ViewHolder holder = null;
        SiteListInfo siteListInfo = list.get(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_sitelist, null);
            holder.city = (TextView) convertView.findViewById(R.id.tv_city);
            holder.companyname = (TextView) convertView.findViewById(R.id.tv_sitecompany);
            holder.guanzhu = (TextView) convertView.findViewById(R.id.tv_guanzhu);
            holder.name = (TextView) convertView.findViewById(R.id.tv_sitename);
            holder.kind = (TextView) convertView.findViewById(R.id.tv_kind);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.city.setText(siteListInfo.getS_PROVNAME());
        holder.companyname.setText(siteListInfo.getCompanyName());
        if (siteListInfo.getFocus().equals("已关注")){
            holder.guanzhu.setText(context.getResources().getString(R.string.ygz));
        }else {
            holder.guanzhu.setText(context.getResources().getString(R.string.wgz));
        }
        holder.name.setText(siteListInfo.getName());
        holder.kind.setText(siteListInfo.getKind());
        return convertView;
    }

    class ViewHolder {
        TextView city, companyname, guanzhu, name, status, kind;
    }
}
