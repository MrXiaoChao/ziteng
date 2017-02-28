package com.example.john.ziteng.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;

import java.io.File;
import java.io.FileFilter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by john on 2016/3/21.
 */
public class ComUtils {

    /**
     * 判断网络是否已经连接
     * @param
     * @return true 已经连接网络， false 网络连接失效
     * */
    public static  boolean isNetworkAvailable(Activity activity)
    {
        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null)
        {
            return false;
        }
        else
        {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0)
            {
                for (int i = 0; i < networkInfo.length; i++)
                {

                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }



    /**
     * 判断当前网络是否是WIFI
     *
     */
    public static boolean isWIFI(Context context){
        try{
            ConnectivityManager cm = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if(cm != null){
                NetworkInfo ni = cm.getActiveNetworkInfo();
                if(ni!=null && ni.getType() == ConnectivityManager.TYPE_WIFI){
                    return true;
                }
            }
        } catch (Exception e){
            return false;
        }
        return false;
    }

    /**
     * 判断当前网络是否是GPRS
     * */
    public static boolean is3G(Context context){
        try{
            ConnectivityManager cm =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if(cm != null){
                NetworkInfo ni = cm.getActiveNetworkInfo();
                if(ni!=null && ni.getType() == ConnectivityManager.TYPE_MOBILE){
                    return true;
                }
            }
        } catch (Exception e){

        }
        return false;
    }

    /**
     * 获取手机分辨率--W
     * */
    public static int getPhoneHW(Context context){
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int disW = dm.widthPixels;
        return disW;
    }


    /**
     * 获取手机分辨率--H
     * */
    public static int getPhoneWH(Context context){
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int disH = dm.heightPixels;
        return disH;
    }

    /**
     * MD5加密
     *
     * @param str 传入的密码
     * @return
     */
    public static String md5(String str) {

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return str;
    }



    /**
     * 获取当前sdk版本
     * @return
     */

    public static int getAndroidSDKVersion() {
        int version = 0;
        try {
            version = Integer.valueOf(android.os.Build.VERSION.SDK_INT);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return version;
    }


    /**
     * 获取CPU数量
     * */
    public static int cpuNums(){
        int nums = 1;
        try{
            File file = new File("/sys/devices/system/cpu/");
            File[] files = file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File arg0) {
                    if(Pattern.matches("cpu[0-9]", arg0.getName())){
                        return true;
                    }
                    return false;
                }
            });
            nums = files.length;
        } catch (Exception e){
            e.printStackTrace();
        }
        return nums;
    }

    /**
     * 判断app是否在后台运行
     *
     * @param context
     * @return
     */
    public static boolean isBackgroundRunning(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }
    //判断手机号码是否合法
    public static boolean isMobileNum(String mobiles) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    //判断email是否合法
    public static boolean isEmail(String email){
        if (null==email || "".equals(email)) return false;
        //Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }

}
