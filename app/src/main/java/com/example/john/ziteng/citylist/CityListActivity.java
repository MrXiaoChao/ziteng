package com.example.john.ziteng.citylist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.john.ziteng.R;
import com.example.john.ziteng.activity.BaseActivity;
import com.example.john.ziteng.activity.MainActivity;
import com.example.john.ziteng.application.MyApplication;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;
import com.example.john.ziteng.utils.SPUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CityListActivity extends BaseActivity implements View.OnClickListener {

    private ListView sortListView;
    private SideBar sideBar;
    private TextView dialog;
    private SortAdapter adapter;
    private CharacterParser characterParser;
    private List<SortModel> SourceDateList;
    private ArrayList<SortModel> list;
    private String mPhone;
    private ImageView back;
    private TextView citysite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        mPhone = (String) SPUtils.get(CityListActivity.this, "phone", "");
        initViews();
        getDateFromService();
    }

    private void initViews() {
        back = (ImageView) findViewById(R.id.city_fanhui);//返回键
        citysite = (TextView) findViewById(R.id.tv_city_site);
        back.setOnClickListener(this);
        citysite.setOnClickListener(this);
        characterParser = CharacterParser.getInstance();
        sideBar = (SideBar) findViewById(R.id.sidrbar);
        dialog = (TextView) findViewById(R.id.dialog);
        sideBar.setTextView(dialog);
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    sortListView.setSelection(position + 1);
                }
            }
        });
        sortListView = (ListView) findViewById(R.id.country_lvcountry);
        sortListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                Intent intent =new Intent(CityListActivity.this, MainActivity.class);
                intent.putExtra("city",((SortModel)adapter.getItem(position)).getName());
                intent.putExtra("cityid",((SortModel)adapter.getItem(position)).getCityid());
                startActivity(intent);
                finish();
            }
        });

    }



    private List<SortModel> filledData(ArrayList<SortModel> list1) {
        List<SortModel> mSortList = new ArrayList<>();
        ArrayList<String> indexString = new ArrayList<>();

        for (int i = 0; i < list1.size(); i++) {
            SortModel sortModel = list1.get(i);
            String pinyin = characterParser.getSelling(sortModel.getName());
            String sortString = pinyin.substring(0, 1).toUpperCase();
            if (sortString.matches("[A-Z]")) {
                //对重庆多音字做特殊处理
                if (pinyin.startsWith("zhongqing")) {
                    sortString = "C";
                    sortModel.setSortLetters("C");
                } else {
                    sortModel.setSortLetters(sortString.toUpperCase());
                }

                if (!indexString.contains(sortString)) {
                    indexString.add(sortString);
                }
            }

            mSortList.add(sortModel);
        }
        Collections.sort(indexString);
        sideBar.setIndexText(indexString);
        return mSortList;

    }
    private void getDateFromService() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.CityList, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                list = PaseJson.PaseCity(s);
                SourceDateList = filledData(list);
                Collections.sort(SourceDateList, new PinyinComparator());
                adapter = new SortAdapter(CityListActivity.this, SourceDateList);
                sortListView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("loginname", mPhone);
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_city_site:
                Intent in  =new Intent(CityListActivity.this,MainActivity.class);
                in.putExtra("cityid","");
                in.putExtra("city",v.getResources().getString(R.string.all));
                startActivity(in);
                finish();
                break;
            case R.id.city_fanhui:
                Intent intent1=new Intent(CityListActivity.this,MainActivity.class);
                startActivity(intent1);
                finish();
                break;
        }
    }
}