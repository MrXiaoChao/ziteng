package com.example.john.ziteng.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.john.ziteng.R;
import com.example.john.ziteng.activity.WebViewTotalActivity;
import com.example.john.ziteng.urlpath.Path;
import com.example.john.ziteng.utils.SPUtils;

/**
 * 统计信息
 * Created by john on 2016/3/17.
 */
public class InfoFragment extends Fragment implements View.OnClickListener {

    private TextView title;
    private RelativeLayout liulian;
    private RelativeLayout shuju;
    private RelativeLayout huodong;
    private RelativeLayout crash;
    private String loginname;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        initView(view);
        loginname = (String) SPUtils.get(getActivity(), "phone", "");
        return view;
    }

    private void initView(View view) {
        title = (TextView) view.findViewById(R.id.title);
        title.setText(getResources().getString(R.string.ss));

        liulian = (RelativeLayout) view.findViewById(R.id.rl_liulian);
        shuju = (RelativeLayout) view.findViewById(R.id.rl_shuju);
        huodong = (RelativeLayout) view.findViewById(R.id.rl_huodong);
        crash = (RelativeLayout) view.findViewById(R.id.rl_crash);

        liulian.setOnClickListener(this);
        shuju.setOnClickListener(this);
        huodong.setOnClickListener(this);
        crash.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_liulian:
                StringBuffer url1=new StringBuffer();
                url1.append(Path.liulian);
                url1.append(loginname);
                url1.append("&zyw=");
                if (getResources().getConfiguration().locale.getCountry().equals("CN")){
                    url1.append(1);//1代表中文
                }else {
                    url1.append(2);//2代表英文
                }
                Intent intent1=new Intent(getActivity(), WebViewTotalActivity.class);
                intent1.putExtra("URL",String.valueOf(url1));
                intent1.putExtra("biaoti",getResources().getString(R.string.mt));
                startActivity(intent1);
                break;
            case R.id.rl_shuju:
                StringBuffer url2=new StringBuffer();
                url2.append(Path.shuju);
                url2.append(loginname);
                url2.append("&zyw=");
                if (getResources().getConfiguration().locale.getCountry().equals("CN")){
                    url2.append(1);//1代表中文
                }else {
                    url2.append(2);//2代表英文
                }
                Intent intent2=new Intent(getActivity(), WebViewTotalActivity.class);
                intent2.putExtra("URL",String.valueOf(url2));
                intent2.putExtra("biaoti",getResources().getString(R.string.db));
                startActivity(intent2);
                break;
            case R.id.rl_huodong:
                StringBuffer url3=new StringBuffer();
                url3.append(Path.huodong);
                url3.append(loginname);
                url3.append("&zyw=");
                if (getResources().getConfiguration().locale.getCountry().equals("CN")){
                    url3.append(1);//1代表中文
                }else {
                    url3.append(2);//2代表英文
                }
                Intent intent3=new Intent(getActivity(), WebViewTotalActivity.class);
                intent3.putExtra("URL",String.valueOf(url3));
                intent3.putExtra("biaoti",getResources().getString(R.string.Ol));
                startActivity(intent3);
                break;
            case R.id.rl_crash:
                StringBuffer url4=new StringBuffer();
                url4.append(Path.crash);
                if (getResources().getConfiguration().locale.getCountry().equals("CN")){
                    url4.append(1);//1代表中文
                }else {
                    url4.append(2);//2代表英文
                }
                Intent intent4=new Intent(getActivity(), WebViewTotalActivity.class);
                intent4.putExtra("URL",String.valueOf(url4));
                intent4.putExtra("biaoti",getResources().getString(R.string.ar));
                startActivity(intent4);
                break;
        }

    }
}
