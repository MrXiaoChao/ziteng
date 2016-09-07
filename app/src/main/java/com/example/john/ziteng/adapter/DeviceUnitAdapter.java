package com.example.john.ziteng.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.john.ziteng.R;
import com.example.john.ziteng.application.MyApplication;
import com.example.john.ziteng.domain.Unit;
import com.example.john.ziteng.urlpath.Path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 单元控制适配器
 * Created by john on 2016/4/6.
 */
public class DeviceUnitAdapter extends BaseAdapter {
    private Context context;

    private ArrayList<Unit> list;
    private String unitId;

    public DeviceUnitAdapter(Activity context, ArrayList<Unit> list) {
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
        Unit unit = list.get(position);
        unitId = unit.getUnitId();
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.list_device_unit_item, null);
            holder.tvUnit = (TextView) convertView.findViewById(R.id.tv_device_unit);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvUnit.setText(unit.getUnitId());

        return convertView;
    }

    class ViewHolder {
        TextView tvUnit;
    }
}
