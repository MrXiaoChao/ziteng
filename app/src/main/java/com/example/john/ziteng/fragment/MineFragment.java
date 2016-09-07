package com.example.john.ziteng.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.john.ziteng.R;
import com.example.john.ziteng.activity.ChangPasswordActivity;
import com.example.john.ziteng.activity.GuanyuActivity;
import com.example.john.ziteng.activity.MainActivity;
import com.example.john.ziteng.activity.PersonalActivity;
import com.example.john.ziteng.activity.SuggestActivity;
import com.example.john.ziteng.activity.WarnActivity;
import com.example.john.ziteng.application.MyApplication;
import com.example.john.ziteng.domain.Personal;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;
import com.example.john.ziteng.utils.SPUtils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * 我的
 * Created by john on 2016/3/17.
 */
public class MineFragment extends Fragment implements View.OnClickListener {

    private TextView title;
    private RelativeLayout personal;
    private RelativeLayout warn;
    private RelativeLayout language;
    private RelativeLayout changpassword;
    private RelativeLayout suggest;
    private RelativeLayout rl_guanyu;
    private TextView language1;
    private int chenckItem;
    private TextView name;
    private TextView sx;
    private TextView zhiwu;
    private String mPhone;
    private Personal personal1;
    private String[] items;
    private String level;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        mPhone = (String) SPUtils.get(getActivity(), "phone", "");
        level = (String) SPUtils.get(getActivity(), "level", "一级用户");
        initView(view);
        //获取dialog选中状态的值
        chenckItem = (int) SPUtils.get(getActivity(), "checkItem", 0);
        setLanguageText();
        getDateFromService();
        return view;
    }

    private void setLanguageText() {
        if (chenckItem == 0) {
            language1.setText(getResources().getString(R.string.cn));
        } else {
            language1.setText(getResources().getString(R.string.en));
        }
    }

    private void getDateFromService() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.PersonalPath, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                personal1 = PaseJson.PasePersonal(s);
                if (personal1 != null) {
                    name.setText(personal1.getName());
                    if (getResources().getConfiguration().locale.getCountry().equals("CN")) {
                        switch (personal1.getSex()) {
                            case 1:
                                sx.setText(getResources().getString(R.string.nan));
                                break;
                            case 2:
                                sx.setText(getResources().getString(R.string.nv));
                                break;
                        }
                    } else {
                        switch (personal1.getSex()) {
                            case 1:
                                sx.setText(getResources().getString(R.string.nan));
                                break;
                            case 2:
                                sx.setText(getResources().getString(R.string.nv));
                                break;
                        }
                    }

                    if (getResources().getConfiguration().locale.getCountry().equals("CN")) {
                        switch (Integer.parseInt(personal1.getGladmin())) {
                            case 1:
                                zhiwu.setText(getResources().getString(R.string.cj));
                                break;
                            case 2:
                                zhiwu.setText(getResources().getString(R.string.zd));
                                break;
                            case 3:
                                zhiwu.setText(getResources().getString(R.string.yj));
                                break;
                            case 4:
                                zhiwu.setText(getResources().getString(R.string.ej));
                                break;
                            case 5:
                                zhiwu.setText(getResources().getString(R.string.sj));
                                break;
                            case 6:
                                zhiwu.setText(getResources().getString(R.string.sij));
                                break;
                        }

                    } else {
                        switch (Integer.parseInt(personal1.getGladmin())) {
                            case 1:
                                zhiwu.setText(getResources().getString(R.string.cj));
                                break;
                            case 2:
                                zhiwu.setText(getResources().getString(R.string.zd));
                                break;
                            case 3:
                                zhiwu.setText(getResources().getString(R.string.yj));
                                break;
                            case 4:
                                zhiwu.setText(getResources().getString(R.string.ej));
                                break;
                            case 5:
                                zhiwu.setText(getResources().getString(R.string.sj));
                                break;
                            case 6:
                                zhiwu.setText(getResources().getString(R.string.sij));
                                break;
                        }
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("data.loginname", mPhone);
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        switchLanguage((String) SPUtils.get(getActivity(), "language", ""));
    }

    private void initView(View view) {
        name = (TextView) view.findViewById(R.id.tv_name_mc);
        sx = (TextView) view.findViewById(R.id.tv_sx);
        zhiwu = (TextView) view.findViewById(R.id.tv_zhiwu);
        personal = (RelativeLayout) view.findViewById(R.id.rl_personal);//个人信息设置
        warn = (RelativeLayout) view.findViewById(R.id.rl_warn);//提醒设置
        language = (RelativeLayout) view.findViewById(R.id.rl_language);//语言选择
        changpassword = (RelativeLayout) view.findViewById(R.id.rl_chang_password);//修改设备控制密码
        suggest = (RelativeLayout) view.findViewById(R.id.rl_suggest);//意见反馈
        rl_guanyu = (RelativeLayout) view.findViewById(R.id.rl_guanyu); //关于能量监控平台
        language1 = (TextView) view.findViewById(R.id.tv_language);
        //点击事件
        personal.setOnClickListener(this);
        warn.setOnClickListener(this);
        language.setOnClickListener(this);
        changpassword.setOnClickListener(this);
        suggest.setOnClickListener(this);
        rl_guanyu.setOnClickListener(this);

        //用户等级权限
        if (level.equals("一级用户")||level.equals("二级用户")||level.equals("三级用户")){
            changpassword.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_personal:
                Intent intent = new Intent(getActivity(), PersonalActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_warn:
                Intent intentWarn = new Intent(getActivity(), WarnActivity.class);
                startActivity(intentWarn);
                break;
            case R.id.rl_language:
                items = new String[]{getResources().getString(R.string.cn), getResources().getString(R.string.en)};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setSingleChoiceItems(items, chenckItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        language1.setText(items[which]);
                        switchLanguage(language1.getText().toString().trim());
                        //记录dialog所选中的状态
                        SPUtils.put(getActivity(), "checkItem", which);
                    }
                });
                builder.setPositiveButton(getResources().getString(R.string.confirm), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        getActivity().finish();
                        Intent intent0 = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent0);

                    }
                });
                builder.setCancelable(false);
                builder.create().show();
                break;
            case R.id.rl_chang_password:
                Intent intentpass = new Intent(getActivity(), ChangPasswordActivity.class);
                startActivity(intentpass);
                break;
            case R.id.rl_suggest:
                Intent intentsuggest = new Intent(getActivity(), SuggestActivity.class);
                startActivity(intentsuggest);
                break;

            case R.id.rl_guanyu:
                Intent intent2 = new Intent(getActivity(), GuanyuActivity.class);
                startActivity(intent2);

                break;

        }
    }

    //语言切换
    public void switchLanguage(String language) {

        Configuration config = getResources().getConfiguration();
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
        SPUtils.put(getActivity(), "language", language);
    }
}
