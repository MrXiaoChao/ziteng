package com.example.john.ziteng.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.john.ziteng.R;
import com.example.john.ziteng.adapter.WarnListviewAdapter1;
import com.example.john.ziteng.application.MyApplication;
import com.example.john.ziteng.domain.Shebeigaojing;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by john on 2016/12/27.
 */
public class SiteGaojingActivity extends BaseActivity {


    @BindView(R.id.gaojing_fanhui)
    ImageView gaojingFanhui;
    @BindView(R.id.web_title)
    TextView webTitle;
    @BindView(R.id.progress_horizontal)
    ProgressBar progressHorizontal;
    @BindView(R.id.rl_progress)
    RelativeLayout rlProgress;
    @BindView(R.id.listview_warn)
    ListView listviewWarn;
    private String equipId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaojing);
        ButterKnife.bind(this);
        equipId = getIntent().getStringExtra("equipId");
        webTitle.setText(getResources().getString(R.string.w));
        getDateFromService();
    }

    private void getDateFromService() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.sbgaojing, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                rlProgress.setVisibility(View.GONE);
                ArrayList<Shebeigaojing> list = PaseJson.PaseSBgaojing(s);
                WarnListviewAdapter1 adapter = new WarnListviewAdapter1(SiteGaojingActivity.this, list);
                if (adapter != null && list.size() > 0) {
                    listviewWarn.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(SiteGaojingActivity.this, "解析出错啦", Toast.LENGTH_SHORT).show();
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
                params.put("equipId",equipId);
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }

    @OnClick(R.id.gaojing_fanhui)
    public void onClick() {
        finish();
    }
}
