package com.example.john.ziteng.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.john.ziteng.R;
import com.example.john.ziteng.activity.WebViewInfoActivity;
import com.example.john.ziteng.domain.Pic;
import com.example.john.ziteng.urlpath.Path;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * 轮播图片
 * Created by john on 2016/3/24.
 */
public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<Pic> list;
    private DisplayImageOptions options;
    private ImageLoader imageLoader;

    public ViewPagerAdapter(Context context, ArrayList<Pic> list) {
        this.context = context;
        this.list = list;
        initImageLoader();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Pic pic = list.get(position);
        final StringBuffer web_url = new StringBuffer();
        web_url.append(Path.PicPathWeb);
        web_url.append("id=");
        web_url.append(pic.getNewId());

        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageLoader.displayImage(pic.getPicUrl(), imageView, options);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebViewInfoActivity.class);
                intent.putExtra("URL", String.valueOf(web_url));
                context.startActivity(intent);
            }
        });
        container.addView(imageView);
        return imageView;
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