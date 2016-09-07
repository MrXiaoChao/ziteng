package com.example.john.ziteng.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.john.ziteng.R;
import com.example.john.ziteng.domain.NewsInfo;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * 新闻数据适配器
 * Created by john on 2016/3/21.
 */
public class ListViewNews2Adapter extends BaseAdapter {
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
    private Context context;
    private ArrayList<NewsInfo> list;
    private final int TYPE_ONE = 0, TYPE_TWO = 1, TYPE_COUNT = 2;

    public ListViewNews2Adapter(Context context, ArrayList<NewsInfo> list) {
        this.context = context;
        this.list = list;
        initImageLoader();
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    //根据position返回相应的Item
    @Override
    public int getItemViewType(int position) {
        int po = position;
        if (po == 0) {
            return TYPE_TWO;
        } else if(po>0&&0==po%4){
            return TYPE_TWO;
        }
        return TYPE_ONE;
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
        ViewHolder viewHolder = null;
        ViewHolder1 viewHolder1 = null;
        NewsInfo newsInfo = list.get(position);
        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case TYPE_ONE:
                    viewHolder = new ViewHolder();
                    convertView = LayoutInflater.from(context).inflate(R.layout.listview_daydate, null);
                    viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_date);
                    viewHolder.title = (TextView) convertView.findViewById(R.id.tv_title);
                    viewHolder.content = (TextView) convertView.findViewById(R.id.tv_content);
                    viewHolder.time = (TextView) convertView.findViewById(R.id.tv_time);
                    convertView.setTag(viewHolder);
                    break;
                case TYPE_TWO:
                    viewHolder1 = new ViewHolder1();
                    convertView = LayoutInflater.from(context).inflate(R.layout.listview_text, null);
                    viewHolder1.title = (TextView) convertView.findViewById(R.id.tv_title);
                    viewHolder1.content = (TextView) convertView.findViewById(R.id.tv_content);
                    viewHolder1.time = (TextView) convertView.findViewById(R.id.tv_time);
                    convertView.setTag(viewHolder1);
                    break;
            }
        } else {
            switch (type) {
                case TYPE_ONE:
                    viewHolder = (ViewHolder) convertView.getTag();
                    break;
                case TYPE_TWO:
                    viewHolder1 = (ViewHolder1) convertView.getTag();
            }
        }
        switch (type) {
            case TYPE_ONE:
                imageLoader.displayImage(newsInfo.getPicUrl(), viewHolder.imageView, options);
                viewHolder.time.setText(newsInfo.getDate());
                viewHolder.title.setText(newsInfo.getTitile());
                viewHolder.content.setText(newsInfo.getDescrip());
                break;
            case TYPE_TWO:
                viewHolder1.time.setText(newsInfo.getDate());
                viewHolder1.title.setText(newsInfo.getTitile());
                viewHolder1.content.setText(newsInfo.getDescrip());
                break;
        }
        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView title, content, time;
    }

    static class ViewHolder1 {
        TextView title, content, time;
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
