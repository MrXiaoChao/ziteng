package com.example.john.ziteng.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;

/**资源读取工具类
 * Created by john on 2016/3/21.
 */
public class ResourceReader {
    public static String readString (Context context, int id) {
        return context.getResources().getString(id);
    }
    public static int readDimen(Context context,int id){
        return context.getResources().getDimensionPixelOffset(id);
    }
    public static Drawable readDrawable(Context context,int id){
        return context.getResources().getDrawable(id);
    }
    public static int readColor(Context context,int id){
        return context.getResources().getColor(id);
    }
    public static ColorStateList readColorStateList(Context context,int id){
        return context.getResources().getColorStateList(id);
    }
}
