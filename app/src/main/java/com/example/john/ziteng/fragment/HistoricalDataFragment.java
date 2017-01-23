package com.example.john.ziteng.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.john.ziteng.R;
import com.example.john.ziteng.activity.WebViewTotalActivity;
import com.example.john.ziteng.domain.SiteInfo;
import com.example.john.ziteng.urlpath.Path;

/**
 * 站点历史数据
 * Created by john on 2016/3/25.
 */
public class HistoricalDataFragment extends Fragment implements View.OnClickListener {
    private SiteInfo site;
    private RelativeLayout rl_cishu;
    private RelativeLayout rl_shichang;
    private RelativeLayout rl_zongshichang;
    private RelativeLayout rl_tingdian;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_site_lishi,container,false);
        //拿到关注站点传过来的对象
        Intent intent=getActivity().getIntent();
        site = (SiteInfo) intent.getSerializableExtra("siteInfo");
        initview(view);
        return view;
    }

    private void initview(View view) {
        rl_cishu = (RelativeLayout) view.findViewById(R.id.rl_cishu);
        rl_shichang = (RelativeLayout) view.findViewById(R.id.rl_shichang);
        rl_zongshichang = (RelativeLayout) view.findViewById(R.id.rl_zongshichang);
        rl_tingdian = (RelativeLayout) view.findViewById(R.id.rl_tingdian);

        rl_cishu.setOnClickListener(this);
        rl_shichang.setOnClickListener(this);
        rl_zongshichang.setOnClickListener(this);
        rl_tingdian.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_cishu:
                StringBuffer url=new StringBuffer();
                url.append(Path.Cishu);
                url.append(site.getSite_id()+"&");
                url.append("zyw=");
                if (getResources().getConfiguration().locale.getCountry().equals("CN")){
                    url.append(1);//1代表中文
                }else {
                    url.append(2);//2代表英文
                }
                Intent intent=new Intent(getActivity(), WebViewTotalActivity.class);
                intent.putExtra("biaoti",getActivity().getResources().getString(R.string.tdcst));
                intent.putExtra("URL",String.valueOf(url));
                startActivity(intent);
                break;
            case R.id.rl_shichang:
                StringBuffer url1=new StringBuffer();
                url1.append(Path.Shichang);
                url1.append(site.getSite_id()+"&");
                url1.append("zyw=");
                if (getResources().getConfiguration().locale.getCountry().equals("CN")){
                    url1.append(1);//1代表中文
                }else {
                    url1.append(2);//2代表英文
                }
                Intent intent1=new Intent(getActivity(), WebViewTotalActivity.class);
                intent1.putExtra("biaoti",getActivity().getResources().getString(R.string.tdsct));
                intent1.putExtra("URL",String.valueOf(url1));
                startActivity(intent1);
                break;
            case R.id.rl_zongshichang:
                StringBuffer url2=new StringBuffer();
                url2.append(Path.Zongshichang);
                url2.append(site.getSite_id()+"&");
                url2.append("zyw=");
                if (getResources().getConfiguration().locale.getCountry().equals("CN")){
                    url2.append(1);//1代表中文
                }else {
                    url2.append(2);//2代表英文
                }
                Intent intent2=new Intent(getActivity(), WebViewTotalActivity.class);
                intent2.putExtra("biaoti",getActivity().getResources().getString(R.string.UPSgdt));
                intent2.putExtra("URL",String.valueOf(url2));
                startActivity(intent2);
                break;
            case R.id.rl_tingdian:
                StringBuffer url3=new StringBuffer();
                url3.append(Path.Tingliebiao);
                url3.append(site.getSite_id()+"&");
                url3.append("zyw=");
                if (getResources().getConfiguration().locale.getCountry().equals("CN")){
                    url3.append(1);//1代表中文
                }else {
                    url3.append(2);//2代表英文
                }
                Intent intent3=new Intent(getActivity(), WebViewTotalActivity.class);
                intent3.putExtra("biaoti",getActivity().getResources().getString(R.string.ol));
                intent3.putExtra("URL",String.valueOf(url3));
                startActivity(intent3);
                break;
        }

    }
}
