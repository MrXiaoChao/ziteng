package com.example.john.ziteng.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.john.ziteng.R;
import com.example.john.ziteng.application.MyApplication;
import com.example.john.ziteng.domain.CityPid;
import com.example.john.ziteng.domain.MapSiteInfo;
import com.example.john.ziteng.domain.MarkInfo;
import com.example.john.ziteng.protocol.PaseJson;
import com.example.john.ziteng.urlpath.Path;
import com.example.john.ziteng.utils.SPUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全网地图
 * Created by john on 2016/4/9.
 */
public class WebMapActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mapback;
    private BaiduMap mBaiduMap;
    private MapView mMapView = null;
    private LocationClient mLocationClient;
    public MyLocationListener mMyLocationListener;
    //当前定位的模式是普通模式
    private MyLocationConfiguration.LocationMode mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
    // 是否是第一次定位
    private volatile boolean isFristLocation = true;
    /**
     * 最新一次的经纬度
     */
    private double mCurrentLantitude;
    private double mCurrentLongitude;
    /**
     * 当前的精度
     */
    private float mCurrentAccracy;
    private BitmapDescriptor mCurrentMarker;

    // 初始化全局 bitmap 信息，不用时及时 recycle
    private BitmapDescriptor mIconMaker;
    private String mPhone;
    private ArrayList<MarkInfo> list;
    private LinearLayout mMarkerInfoLy;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
    private ImageView search;
    private RelativeLayout rltop;
    private EditText etSearch;
    private ImageView ivDeleteText;
    private TextView tvSearch;
    private List<CityPid> listcity;
    private ProgressDialog dialog;
    private TextView guanzhu;
    private MarkInfo info;
    private MapSiteInfo mapSiteInfo;
    Marker marker = null;
    LatLng latLng = null;
    LatLng latLng1 = null;
    OverlayOptions overlayOptions = null;
    private String latitude;
    private String longitude;
    private boolean isClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_map);
        //获取用户名
        mPhone = (String) SPUtils.get(WebMapActivity.this, "phone", "");
        Intent intent = getIntent();
        latitude = intent.getStringExtra("latitude");
        longitude = intent.getStringExtra("longitude");
        initview();
        initImageLoader();
        if (latitude == null) {
            getPidFromService();//获取城市的pid
            showDialog();
            getDateFromService();
        } else {
            addInfosOverlay1(longitude,latitude);
        }

//        initMyLocation();自我定位 暂时用不上
        initMarkerClickEvent();
        initMapClickEvent();

    }

    //获取地图站点的详情
    private void getSiteInfo() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.MapSiteInfo, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                List<MapSiteInfo> list = PaseJson.PaseMapSite(s);
                mapSiteInfo = list.get(0);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("siteId", info.getSiteId());
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }

    //获取城市的pid
    private void getPidFromService() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.CityPid, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                listcity = PaseJson.PaseCityPid(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        request.setTag("city");
        MyApplication.getHttpQueue().add(request);
    }

    //从服务器获取经纬度
    private void getDateFromService() {
        StringRequest request = new StringRequest(Request.Method.POST, Path.WebMap, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                list = PaseJson.PaseMark(s);
                addInfosOverlay(list);
                disDialog();
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

    //定位初始化
    private void initMyLocation() {
        // 定位初始化
        mLocationClient = new LocationClient(this);
        mMyLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(mMyLocationListener);
        mLocationClient.start();
        // 设置定位的相关配置
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);//每隔1秒定位一次
        mLocationClient.setLocOption(option);
    }

    //地图定位监听
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null)
                return;
            // 构造定位数据
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                            // 此处设置开发者获取到的方向信息，顺时针0-360
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mCurrentAccracy = location.getRadius();
            // 设置定位数据
            mBaiduMap.setMyLocationData(locData);
            mCurrentLantitude = location.getLatitude();
            mCurrentLongitude = location.getLongitude();
            // 设置自定义图标
            mCurrentMarker = BitmapDescriptorFactory
                    .fromResource(R.mipmap.location);
            MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);
            mBaiduMap.setMyLocationConfigeration(config);
            // 第一次定位时，将地图位置移动到当前位置
            if (isFristLocation) {
                isFristLocation = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
                mBaiduMap.animateMapStatus(u);
            }
        }

    }

    private boolean issearch = true;// 地图缩放级别的控制

    public void addInfosOverlay(ArrayList<MarkInfo> infos) {
        mBaiduMap.clear();
        if (infos != null) {
            for (MarkInfo info : infos) {
                // 位置
                latLng = new LatLng(Double.parseDouble(info.getLONGITUDE()), Double.parseDouble(info.getLATITUDE()));
                // 图标
                overlayOptions = new MarkerOptions().position(latLng).icon(mIconMaker).zIndex(5);
                marker = (Marker) (mBaiduMap.addOverlay(overlayOptions));
                Bundle bundle = new Bundle();
                bundle.putSerializable("info", info);
                marker.setExtraInfo(bundle);
            }
            latLng1 = new LatLng(35.3349, 103.2319);
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(latLng1);
            mBaiduMap.setMapStatus(u);
            MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(5.2f);
            mBaiduMap.setMapStatus(msu);

        }
    }

    public void addInfosOverlay1(String latitude, String longitude) {
            mBaiduMap.clear();
        // 位置
            latLng = new LatLng(Double.parseDouble(longitude), Double.parseDouble(latitude));
        // 图标
            overlayOptions = new MarkerOptions().position(latLng).icon(mIconMaker).zIndex(5);
            marker = (Marker) (mBaiduMap.addOverlay(overlayOptions));
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(latLng);
            mBaiduMap.setMapStatus(u);
            MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(8.6f);
            mBaiduMap.setMapStatus(msu);
            search.setVisibility(View.GONE);
            isClick=true;
    }


    private void initMarkerClickEvent() {
        if (!isClick) {
            // 对Marker的点击
            mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(final Marker marker) {
                    // 获得marker中的数据
                    info = (MarkInfo) marker.getExtraInfo().get("info");
//                InfoWindow mInfoWindow;
//                // 生成一个TextView用户在地图中显示InfoWindow
//                TextView location = new TextView(getApplicationContext());
//                location.setBackgroundResource(R.drawable.location_tips);
//                location.setPadding(30, 20, 30, 20);
//                location.setText(info.getName());
//                // 将marker所在的经纬度的信息转化成屏幕上的坐标
//                final LatLng ll = marker.getPosition();
//                LatLng llNew = new LatLng(ll.latitude + 0.005,
//                        ll.longitude + 0.005);
//                marker.setPosition(llNew);
//                // 为弹出的InfoWindow添加点击事件
//                mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(location), ll, -47, new OnInfoWindowClickListener() {
//                    @Override
//                    public void onInfoWindowClick() {
//
//                        mBaiduMap.hideInfoWindow();
//                    }
//                });
//                // 显示InfoWindow
//                mBaiduMap.showInfoWindow(mInfoWindow);
                    guanzhu.setText(info.getFocus());
                    mMarkerInfoLy.setVisibility(View.VISIBLE);
                    popupInfo(mMarkerInfoLy, info);
                    getSiteInfo();
                    return true;
                }
            });
        }
    }


    //地图的点击事件
    private void initMapClickEvent() {
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public boolean onMapPoiClick(MapPoi arg0) {
                return false;
            }

            @Override
            public void onMapClick(LatLng arg0) {
                mBaiduMap.hideInfoWindow();
                mMarkerInfoLy.setVisibility(View.GONE);
                rltop.setVisibility(View.GONE);
            }
        });
    }

    private void initview() {
        mapback = (ImageView) findViewById(R.id.map_fanhui);
        mapback.setOnClickListener(this);
        //搜索框
        search = (ImageView) findViewById(R.id.iv_search);
        rltop = (RelativeLayout) findViewById(R.id.top);
        etSearch = (EditText) findViewById(R.id.etSearch);
        ivDeleteText = (ImageView) findViewById(R.id.ivDeleteText);
        tvSearch = (TextView) findViewById(R.id.tvSearch);
        ivDeleteText.setOnClickListener(this);
        tvSearch.setOnClickListener(this);
        search.setOnClickListener(this);
        //自定义搜索框字体的监控
        etSearch.addTextChangedListener(new MyTextWatcher());
        //百度地图
        isFristLocation = true;
        //弹出来的pupopwindow
        mMarkerInfoLy = (LinearLayout) findViewById(R.id.id_marker_info);
        guanzhu = (TextView) findViewById(R.id.tv_guanzhu);
        guanzhu.setOnClickListener(this);
        // 获取地图控件引用
        mMapView = (MapView) findViewById(R.id.id_bmapView);
        // 获得地图的实例
        mBaiduMap = mMapView.getMap();
        mIconMaker = BitmapDescriptorFactory.fromResource(R.mipmap.icon_gcoding);
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

    }

    //自定义搜索框清除功能
    private class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() == 0) {
                ivDeleteText.setVisibility(View.GONE);
            } else {
                ivDeleteText.setVisibility(View.VISIBLE);

            }
        }
    }

    protected void popupInfo(LinearLayout mMarkerLy, final MarkInfo info) {
        ViewHolder viewHolder = null;
        if (mMarkerLy.getTag() == null) {
            viewHolder = new ViewHolder();
            viewHolder.siteinfo = (TextView) mMarkerLy.findViewById(R.id.tv_site_info);
            viewHolder.infoImg = (ImageView) mMarkerLy.findViewById(R.id.info_img);
            viewHolder.infoName = (TextView) mMarkerLy.findViewById(R.id.info_name);
            mMarkerLy.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) mMarkerLy.getTag();
        }
        imageLoader.displayImage(info.getDESCRIPTION(), viewHolder.infoImg, options);
        viewHolder.infoName.setText(info.getName());
        viewHolder.siteinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WebMapActivity.this, SiteInfoActivity.class);
                if (mapSiteInfo == null) {
                    return;
                }
                intent.putExtra("mapsiteinfo", mapSiteInfo);
                startActivity(intent);
            }
        });
    }

    private class ViewHolder {
        ImageView infoImg;
        TextView infoName;
        TextView siteinfo;
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

    @Override
    protected void onStart() {
        mBaiduMap.setMyLocationEnabled(true);
//        if (!mLocationClient.isStarted()) {
//            mLocationClient.start();
//        }

        super.onStart();
    }

    protected void onStop() {
        // 关闭图层定位
        mBaiduMap.setMyLocationEnabled(false);
//        mLocationClient.stop();
        super.onStop();
    }

    protected void onResume() {
        super.onResume();
        // 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    protected void onPause() {
        super.onPause();
        // 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
        // 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        mIconMaker.recycle();
        mMapView = null;
        MyApplication.getHttpQueue().cancelAll("city");
        if (marker != null && marker.isVisible()) {
            marker = null;
        }// mark图标释放

    }

    private boolean isfrist = true;
    private int pId;
    private boolean istrue;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.map_fanhui://返回键
                finish();
                break;
            case R.id.iv_search://搜索
                if (isfrist) {
                    rltop.setVisibility(View.VISIBLE);
                    isfrist = false;
                } else {
                    rltop.setVisibility(View.GONE);
                    etSearch.setText("");
                    isfrist = true;
                }
                break;
            case R.id.tvSearch://搜索
                String city = null;
                String cityname = etSearch.getText().toString().trim();
                for (CityPid cityPid : listcity) {
                    city = cityPid.getpName();
                    if (cityname.equals(city)) {
                        istrue = true;
                        pId = cityPid.getpId();
                        showDialog();
                        getDateFromService();
                        sendCityToService(pId);
                    }
                    issearch = false;
                }
                if (cityname.equals("")) {
                    showDialog();
                    istrue = false;
                    issearch = true;
                    sendCityToService(pId);
                }
                rltop.setVisibility(View.GONE);
                break;
            case R.id.ivDeleteText://清除功能
                etSearch.setText("");
                break;
            case R.id.tv_guanzhu://关注站点
                if (info.getFocus().equals("已关注")) {
                    sendGuanzhu(0, info);
                } else {
                    sendGuanzhu(1, info);
                }
                break;

        }
    }

    //关注 未关注
    private void sendGuanzhu(final int fourcs, final MarkInfo info) {
        StringRequest request = new StringRequest(Request.Method.POST, Path.ChangFourcs, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    String focus = object.getString("focus");
                    guanzhu.setText(focus);
                    info.setFocus(focus);
                    if (info.getFocus().equals("已关注")) {
                        Toast.makeText(WebMapActivity.this, "你已关注站点", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(WebMapActivity.this, "你已取消关注", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("change", String.valueOf(fourcs));
                params.put("siteId", info.getSiteId());
                params.put("loginname", mPhone);
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }

    //搜索
    private void sendCityToService(final int pId) {
        StringRequest request = new StringRequest(Request.Method.POST, Path.WebMap, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                list = PaseJson.PaseMark(s);
                for (MarkInfo info : list) {
                    // 位置
                    latLng = new LatLng(Double.parseDouble(info.getLONGITUDE()), Double.parseDouble(info.getLATITUDE()));
                    // 图标
                    overlayOptions = new MarkerOptions().position(latLng).icon(mIconMaker).zIndex(5);
                    marker = (Marker) (mBaiduMap.addOverlay(overlayOptions));
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("info", info);
                    marker.setExtraInfo(bundle);
                }
                if (issearch) {
                    latLng1 = new LatLng(35.3349, 103.2319);
                    MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(latLng1);
                    mBaiduMap.setMapStatus(u);
                    MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(5.2f);
                    mBaiduMap.setMapStatus(msu);
                } else {
                    MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(latLng);
                    mBaiduMap.setMapStatus(u);
                    MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(8.6f);
                    mBaiduMap.setMapStatus(msu);
                }
                disDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("loginname", mPhone);
                if (istrue) {
                    params.put("province", String.valueOf(pId));
                }
                return params;
            }
        };
        MyApplication.getHttpQueue().add(request);
    }

    private void showDialog() {
        dialog = new ProgressDialog(WebMapActivity.this);
        dialog.setMessage("正在加载中...");
        dialog.show();
    }

    private void disDialog() {
        if (dialog.isShowing() && dialog != null) {
            dialog.dismiss();
        }
    }
}
