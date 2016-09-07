package com.example.john.ziteng.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.john.ziteng.R;
import com.example.john.ziteng.fragment.InfoFragment;
import com.example.john.ziteng.fragment.MineFragment;
import com.example.john.ziteng.fragment.MonitorFragment;
import com.example.john.ziteng.fragment.NewsFragment;
import com.example.john.ziteng.fragment.SiteFragment;


import java.util.ArrayList;


public class MainActivity extends BaseActivity implements View.OnClickListener{

    private Button btnSite;
    private Button btnNews;
    private Button btnMonitor;
    private Button btnInfo;
    private Button btnMine;
    private Button[] mTab = new Button[5];
    private SiteFragment siteFragment;
    private NewsFragment newsFragment;
    private MonitorFragment monitorFragment;
    private InfoFragment infoFragment;
    private MineFragment mineFragment;
    public long backtime = 0;
    private int index;
    private int currentpos;
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVixew();
        mTab[currentpos].setSelected(true);//设置默认选择的按钮
    }

    private void initVixew() {
        vp = (ViewPager) findViewById(R.id.vp_main);
        btnSite = (Button) findViewById(R.id.btn_site);
        btnNews = (Button) findViewById(R.id.btn_news);
        btnMonitor = (Button) findViewById(R.id.btn_monitor);
//        btnInfo = (Button) findViewById(R.id.btn_info);
        btnMine = (Button) findViewById(R.id.btn_mine);

        mTab[0] = btnSite;
        mTab[2] = btnNews;
        mTab[1] = btnMonitor;
//        mTab[3] = btnInfo;
        mTab[3] = btnMine;

        btnSite.setOnClickListener(this);
        btnNews.setOnClickListener(this);
        btnMonitor.setOnClickListener(this);
//        btnInfo.setOnClickListener(this);
        btnMine.setOnClickListener(this);

        siteFragment = new SiteFragment();
        monitorFragment = new MonitorFragment();
        newsFragment = new NewsFragment();
//        infoFragment = new InfoFragment();
        mineFragment = new MineFragment();

        ArrayList<Fragment> fList = new ArrayList<Fragment>();
        fList.add(siteFragment);
        fList.add(monitorFragment);
        fList.add(newsFragment);
//        fList.add(infoFragment);
        fList.add(mineFragment);

        vp.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), fList));
        vp.setOnPageChangeListener(new MainChangeListence());

    }

    @Override
    public void onClick(View v) {
        switchButton(index);
        switch (v.getId()) {
            case R.id.btn_site://关注站点
                index = 0;
                vp.setCurrentItem(index);
                break;
            case R.id.btn_monitor://全网监控
                index = 1;
                vp.setCurrentItem(index);
                break;
            case R.id.btn_news://资讯快递
                index = 2;
                vp.setCurrentItem(index);
                break;

//            case R.id.btn_info://统计信息
//                index = 3;
//                vp.setCurrentItem(index);
//                break;
            case R.id.btn_mine://我的
                index = 4;
                vp.setCurrentItem(index);
                break;
        }

    }

    class MainPagerAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> list;

        public MainPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

    private  void switchButton(int pos) {
        index = pos;
        vp.setCurrentItem(index);
        if (currentpos != index) {
            mTab[currentpos].setSelected(false);
            mTab[index].setSelected(true);
            currentpos = index;
        }
    }
    //是否退出客户端
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long t = System.currentTimeMillis();
            if (t - backtime > 3000) {
                Toast.makeText(this, "再按一次退出客户端", Toast.LENGTH_SHORT).show();
                backtime = t;
                return true;
            }
            KillAll();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }


    class MainChangeListence implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switchButton(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}
