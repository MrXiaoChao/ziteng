package com.example.john.ziteng.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.john.ziteng.R;
import com.example.john.ziteng.domain.SiteInfo;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * 关注站点
 * Created by john on 2016/3/18.
 */
public class ListViewSiteAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SiteInfo> list;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;

    public ListViewSiteAdapter(Context context, ArrayList<SiteInfo> list) {
        this.context = context;
        this.list = list;
        initImageLoader();
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
        SiteInfo siteInfo = list.get(position);
        ViewHolder holder;
        String imgurl = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.listviewsite_item, null);
            holder.icon = (ImageView) convertView.findViewById(R.id.iv_site_icon);
            holder.waidian = (TextView) convertView.findViewById(R.id.tv_waidian);
            holder.tital = (TextView) convertView.findViewById(R.id.tv_site_tital);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //防止图片加载异位
        if (!TextUtils.isEmpty(siteInfo.getUrl())){
            imageLoader.displayImage(siteInfo.getUrl(), holder.icon, options);
        }else {
            imageLoader.displayImage("", holder.icon, options);
        }
        if (siteInfo.getAnElectric().equals("外电正常")){
            holder.waidian.setTextColor(context.getResources().getColor(R.color.green));
        }else if (siteInfo.getAnElectric().equals("UPS供电")||siteInfo.getAnElectric().equals("后备电源供电")){
            holder.waidian.setTextColor(context.getResources().getColor(R.color.orange));
        }else if (siteInfo.getAnElectric().equals("负载部分断电")||siteInfo.getAnElectric().equals("负载全部断电")){
            holder.waidian.setTextColor(context.getResources().getColor(R.color.red));
        }
        holder.waidian.setText(siteInfo.getAnElectric());
        holder.tital.setText(siteInfo.getName());


        return convertView;
    }

    class ViewHolder {
        TextView waidian, ups, tital;
        ImageView icon;
    }

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
}
