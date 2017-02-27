package com.example.john.ziteng.activity;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;

import com.example.john.ziteng.utils.SPUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Created by lc on 2016/3/17.
 */
public class BaseActivity extends FragmentActivity {
    //mac
    List<BaseActivity> mActivitys = new LinkedList<BaseActivity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        synchronized (mActivitys) {
            mActivitys.add(this);
        }
        //中英文切换
        switchLanguage((String) SPUtils.get(this, "language", ""));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        synchronized (mActivitys) {
            mActivitys.remove(this);
        }
    }

    public void KillAll() {
        List<BaseActivity> copy;
        synchronized (mActivitys) {
            copy = new LinkedList<BaseActivity>(mActivitys);
        }
        for (BaseActivity activity : copy) {
            activity.finish();
        }
        //杀死当前的进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }
    //语言切换
    public  void switchLanguage(String language) {

        Configuration config =getResources().getConfiguration();
        DisplayMetrics dm = getResources().getDisplayMetrics();
        if (language.equals("英文")) {
            config.locale = Locale.ENGLISH;
        } else if (language.equals("中文")) {
            config.locale = Locale.CHINA;
        } else if (language.equals("English")) {
            config.locale = Locale.ENGLISH;
        } else if (language.equals("Chinese")) {
            config.locale = Locale.CHINA;
        }
        getResources().updateConfiguration(config, dm);
        SPUtils.put(this, "language", language);
    }
}
