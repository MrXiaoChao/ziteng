package com.example.john.ziteng.protocol;


import com.example.john.ziteng.R;
import com.example.john.ziteng.citylist.SortModel;
import com.example.john.ziteng.domain.BatteryList;
import com.example.john.ziteng.domain.ChangDianliu;
import com.example.john.ziteng.domain.ChangEmail;
import com.example.john.ziteng.domain.ChangPassword;
import com.example.john.ziteng.domain.ChangPhone;
import com.example.john.ziteng.domain.CheckYzm;
import com.example.john.ziteng.domain.CityPid;
import com.example.john.ziteng.domain.Commite;
import com.example.john.ziteng.domain.DeviceCanshu;
import com.example.john.ziteng.domain.DeviceControl;
import com.example.john.ziteng.domain.DeviceGroup;
import com.example.john.ziteng.domain.DeviceGroupInfo;
import com.example.john.ziteng.domain.DeviceInfo;
import com.example.john.ziteng.domain.DeviceModer;
import com.example.john.ziteng.domain.DevicePassWord;
import com.example.john.ziteng.domain.DeviceState;
import com.example.john.ziteng.domain.MapSiteInfo;
import com.example.john.ziteng.domain.MarkInfo;
import com.example.john.ziteng.domain.Monitor;
import com.example.john.ziteng.domain.MoudleList;
import com.example.john.ziteng.domain.NewsInfo;
import com.example.john.ziteng.domain.NewsTotal;
import com.example.john.ziteng.domain.PassWord;
import com.example.john.ziteng.domain.Personal;
import com.example.john.ziteng.domain.Pic;
import com.example.john.ziteng.domain.SiteDelicInfo;
import com.example.john.ziteng.domain.SiteGuangJiang;
import com.example.john.ziteng.domain.SiteInfo;
import com.example.john.ziteng.domain.SiteListInfo;
import com.example.john.ziteng.domain.SiteLists;
import com.example.john.ziteng.domain.Sitefours;
import com.example.john.ziteng.domain.Unit;
import com.example.john.ziteng.domain.UnitList;
import com.example.john.ziteng.domain.UserLogin;
import com.example.john.ziteng.domain.Warn;
import com.example.john.ziteng.domain.Warn1;
import com.example.john.ziteng.domain.WarnInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 2016/3/23.
 */
public class PaseJson {


    private static UserLogin user;
    private static Personal personal;
    private static ChangPhone changPhone;
    private static Monitor monitor;
    private static ChangPassword changPassword;
    private static ChangEmail changEmail;
    private static DeviceControl deviceControl;
    private static Commite commite;
    private static String siteId;
    private static String anElectric;
    private static String current;
    private static String stationId;
    private static String groupId;


    //用户登录
    public static UserLogin PaseUseLogin(String json) {

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonObject1 = jsonObject.getJSONObject("obj");
            String level = jsonObject1.getString("level");
            String msg = jsonObject.getString("msg");
            Boolean suc = jsonObject.getBoolean("success");
            user = new UserLogin(level, msg, suc);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    //站点数据
    public static Sitefours PaseSite(String json) {
        Sitefours sitefours = null;
        List<SiteInfo> list = new ArrayList<SiteInfo>();
        try {
            JSONObject object = new JSONObject(json);
            int currentPage = object.getInt("currentPage");
            int rows = object.getInt("rows");
            int totalPage = object.getInt("totalPage");
            JSONArray array = object.getJSONArray("lists");
            for (int i = 0; i < array.length(); i++) {
                JSONObject object1 = array.getJSONObject(i);
                int allPower = object1.getInt("allPower");
                String name = object1.getString("name");
                if (!object1.isNull("anElectric")) {
                    anElectric = object1.getString("anElectric");
                }
                int partPower = object1.getInt("partPower");
                String site_id = object1.getString("site_id");
                int ups = object1.getInt("ups");
                String storageCapacity = object1.getString("storageCapacity");
                String deploytime = object1.getString("deploytime");
                String electrovalency = object1.getString("electrovalency");
                String city = object1.getString("city");
                String url = object1.getString("url");
                SiteInfo siteInfo = new SiteInfo(allPower, anElectric, name, partPower, site_id, ups, storageCapacity, deploytime, electrovalency, city, url);
                list.add(siteInfo);
            }
            sitefours = new Sitefours(currentPage, rows, totalPage, list);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sitefours;
    }


    //关注站点轮播图片
    public static List<Pic> PasePic(String json) {
        List<Pic> list = new ArrayList<Pic>();
        try {
            JSONObject object = new JSONObject(json);
            JSONArray array = object.getJSONArray("obj");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                String picUrl = jsonObject.getString("picUrl");
                String titile = jsonObject.getString("titile");
                String newId = jsonObject.getString("newid");
                Pic pic = new Pic(picUrl, titile, newId);
                list.add(pic);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }


    //资讯快递
    public static NewsTotal PaseNews(String json) {
        NewsTotal newsTotal = null;
        try {
            JSONObject object = new JSONObject(json);
            JSONObject object1 = object.getJSONObject("obj");
            int currentPage = object1.getInt("currentPage");
            int rows = object1.getInt("rows");
            int totalPage = object1.getInt("totalPage");
            JSONArray array = object1.getJSONArray("pojo");
            ArrayList<NewsInfo> list = new ArrayList<NewsInfo>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject object2 = array.getJSONObject(i);
                String descrip = object2.getString("descrip");
                String date = object2.getString("date");
                String picUrl = object2.getString("picUrl");
                String titile = object2.getString("titile");
                String newId = object2.getString("newid");
                NewsInfo newsInfo = new NewsInfo(picUrl, titile, newId, date, descrip);
                list.add(newsInfo);
            }
            newsTotal = new NewsTotal(currentPage, rows, totalPage, list);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return newsTotal;
    }

    //个人信息配置
    public static Personal PasePersonal(String json) {

        try {
            JSONObject object = new JSONObject(json);
            JSONObject jsonObject = object.getJSONObject("obj");
            String name = jsonObject.getString("name");
            String company = jsonObject.getString("company");
            int sex = jsonObject.getInt("sex");
            String gladmin = jsonObject.getString("gladmin");
            String email = jsonObject.getString("email");
            String phone = jsonObject.getString("phone");
            String descrip = jsonObject.getString("descrip");
            personal = new Personal(name, sex, company, gladmin, email, phone, descrip);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return personal;
    }

    //修改手机号码
    public static ChangPhone PasePhone(String json) {
        try {
            JSONObject object = new JSONObject(json);
            String obj = object.getString("obj");
            boolean success = object.getBoolean("success");
            changPhone = new ChangPhone(success, obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return changPhone;
    }

    //全网监控
    public static Monitor PaseMonitor(String json) {
        try {
            JSONObject object = new JSONObject(json);
            String currentstorage = object.getString("currentstorage");
            String num = object.getString("num");
            String storageCapacity = object.getString("storageCapacity");
            monitor = new Monitor(currentstorage, num, storageCapacity);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return monitor;
    }

    //修改设备密码
    public static ChangPassword PasePassWord(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            boolean success = jsonObject.getBoolean("success");
            String Msg = jsonObject.getString("msg");
            changPassword = new ChangPassword(success, Msg);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return changPassword;
    }

    //修改Email
    public static ChangEmail PaseEmail(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            boolean success = jsonObject.getBoolean("success");
            String obj = jsonObject.getString("obj");
            changEmail = new ChangEmail(success, obj);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return changEmail;
    }

    //设备群入口群基本信息
    public static ArrayList<DeviceGroup> PaseDevice(String json) {
        ArrayList<DeviceGroup> list = new ArrayList<>();
        ArrayList<DeviceGroup.EquipListBean> list1 = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                String deploy_time = object.getString("deploy_time");
                String groupId = object.getString("groupId");
                String groupName = object.getString("groupName");
                int power = object.getInt("power");
                int stored_energy = object.getInt("stored_energy");
                int voltage = object.getInt("voltage");
                JSONArray listBeans = object.getJSONArray("equipList");
                for (int i1 = 0; i1 < listBeans.length(); i1++) {
                    JSONObject object1 = (JSONObject) listBeans.get(i1);
                    String equipId = object1.getString("equipId");
                    String equip_id = object1.getString("equip_id");
                    DeviceGroup.EquipListBean equipListBean = new DeviceGroup.EquipListBean();
                    equipListBean.setEquip_id(equip_id);
                    equipListBean.setEquipId(equipId);
                    list1.add(equipListBean);
                }
                DeviceGroup.EquipListBean equipListBean1 = new DeviceGroup.EquipListBean();
                equipListBean1.setEquip_id("基本信息");
                list1.add(0, equipListBean1);
                DeviceGroup deviceGroup = new DeviceGroup(deploy_time, groupId, groupName, power, stored_energy, voltage, list1);
                list.add(deviceGroup);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }


    //提醒状态初始化设置
    public static Warn PaseWarn(String json) {
        Warn warn = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            boolean success = jsonObject.getBoolean("success");
            int obj = jsonObject.getInt("obj");
            warn = new Warn(success, obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return warn;
    }

    //改变提醒状态设置
    public static Warn1 PaseWarn1(String json) {
        Warn1 warn1 = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            boolean success = jsonObject.getBoolean("success");
            int obj = jsonObject.getInt("obj");
            warn1 = new Warn1(success, obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return warn1;
    }

    //设备群列表 里面的设备
    public static DeviceGroupInfo PaseDGF(String json) {
        ArrayList<DeviceGroupInfo.UnitlistBean> list = new ArrayList<>();
        DeviceGroupInfo deviceGroupInfo = null;
        try {
            JSONObject object = new JSONObject(json);
            String deploy_time = object.getString("deploy_time");
            String equip_id = object.getString("equip_id");
            String equipId = object.getString("equipId");
            int power = object.getInt("power");
            int stored_energy = object.getInt("stored_energy");
            int voltage = object.getInt("voltage");
            JSONArray array = object.getJSONArray("unitlist");
            for (int i = 0; i < array.length(); i++) {
                JSONObject object1 = array.getJSONObject(i);
                String unitId = object1.getString("unitId");
                DeviceGroupInfo.UnitlistBean unitlistBean = new DeviceGroupInfo.UnitlistBean(unitId);
                list.add(unitlistBean);
            }
            deviceGroupInfo = new DeviceGroupInfo(deploy_time, equipId, equip_id, power, stored_energy, voltage, list);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return deviceGroupInfo;
    }

    //重要告警信息
    public static List<WarnInfo> PaseWarnInfo(String json) {
        ArrayList<WarnInfo> warns = new ArrayList<WarnInfo>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                String time = object.getString("time");
                String warnContent = object.getString("warnContent");
                String siteName = object.getString("siteName");
                WarnInfo warn = new WarnInfo(time, siteName, warnContent);
                warns.add(warn);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return warns;
    }

    //设备控制
    public static DeviceControl PaseControl(String json) {

        try {
            JSONObject object = new JSONObject(json);
            if (!object.isNull("current")) {
                current = object.getString("current");
            }
            JSONArray units = object.getJSONArray("units");
            List<Unit> list2 = new ArrayList<Unit>();
            for (int i = 0; i < units.length(); i++) {
                JSONObject jsonObject = units.getJSONObject(i);
                String unitId = jsonObject.getString("unitId");
                Unit unit = new Unit(unitId);
                list2.add(unit);
            }
            deviceControl = new DeviceControl(current, list2);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return deviceControl;
    }

    //提交意见
    public static Commite PaseCommit(String json) {
        try {
            JSONObject object = new JSONObject(json);
            String msg = object.getString("msg");
            boolean success = object.getBoolean("success");
            commite = new Commite(msg, success);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return commite;
    }

    //验证设备控制密码
    public static DevicePassWord PaseDevicePass(String json) {
        DevicePassWord devicePassWord = null;
        try {
            JSONObject object = new JSONObject(json);
            String msg = object.getString("msg");
            boolean success = object.getBoolean("success");
            devicePassWord = new DevicePassWord(msg, success);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return devicePassWord;
    }

    //模块控制
    public static ArrayList<DeviceModer> PaseModer(String json) {
        DeviceModer deviceModer = null;
        ArrayList<DeviceModer> list = new ArrayList<DeviceModer>();
        try {
            JSONObject object = new JSONObject(json);
            JSONArray array = object.getJSONArray("modules");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                String moduleId = jsonObject.getString("moduleId");
                double electricCurrent = jsonObject.getDouble("electricCurrent");
                deviceModer = new DeviceModer(electricCurrent, moduleId);
                list.add(deviceModer);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    //获取站点的经纬度
    public static ArrayList<MarkInfo> PaseMark(String json) {
        ArrayList<MarkInfo> list = new ArrayList<MarkInfo>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                String CITY = object.getString("CITY");
                String DESCRIPTION = object.getString("DESCRIPTION");
                String LATITUDE = object.getString("LATITUDE");
                String LONGITUDE = object.getString("LONGITUDE");
                String focus = object.getString("focus");
                String name = object.getString("name");
                if (!object.isNull("siteId")) {
                    siteId = object.getString("siteId");
                }
                MarkInfo info = new MarkInfo(CITY, DESCRIPTION, LATITUDE, LONGITUDE, name, focus, siteId);
                list.add(info);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    //城市列表
    public static ArrayList<SortModel> PaseCity(String josn) {
        ArrayList<SortModel> list = new ArrayList<SortModel>();
        try {
            JSONArray array = new JSONArray(josn);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                String area = object.getString("area");
                String cityid = object.getString("cityid");
                SortModel sortModel = new SortModel(area, cityid);
                list.add(sortModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    //修改电流
    public static ChangDianliu PaseDianliu(String json) {
        ChangDianliu changDianliu = null;
        try {
            JSONObject object = new JSONObject(json);
            String msg = object.getString("msg");
            boolean success = object.getBoolean("success");
            changDianliu = new ChangDianliu(msg, success);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return changDianliu;
    }

    //核对验证码
    public static CheckYzm PaseYzm(String json) {
        CheckYzm checkYzm = null;
        try {
            JSONObject object = new JSONObject(json);
            String msg = object.getString("msg");
            boolean success = object.getBoolean("success");
            checkYzm = new CheckYzm(msg, success);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return checkYzm;
    }

    //核对验证码
    public static PassWord PaseWord(String json) {
        PassWord passWord = null;
        try {
            JSONObject object = new JSONObject(json);
            String msg = object.getString("msg");
            boolean success = object.getBoolean("success");
            passWord = new PassWord(msg, success);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return passWord;
    }



    //站点关键数据
    public static SiteGuangJiang sitegj(String josn) {
        SiteGuangJiang siteGuangJiang = null;
        try {
            JSONObject object = new JSONObject(josn);
            String allUps = object.getString("allUps");
            String dayOutpowerNum = object.getString("dayOutpowerNum");
            String dayOutpowerOfen = object.getString("dayOutpowerOfen");
            String dayUps = object.getString("dayUps");
            String monOutpowerNum = object.getString("monOutpowerNum");
            String monOutpowerOfen = object.getString("monOutpowerOfen");
            String monUps = object.getString("monUps");
            String yearOutpowerNum = object.getString("yearOutpowerNum");
            String yearOutpowerOfen = object.getString("yearOutpowerOfen");
            String yearUps = object.getString("yearUps");
            siteGuangJiang = new SiteGuangJiang(allUps, dayOutpowerNum, dayOutpowerOfen, dayUps,
                    monOutpowerNum, monOutpowerOfen, monUps, yearOutpowerNum, yearOutpowerOfen, yearUps);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return siteGuangJiang;
    }

    //城市名称跟PID值
    public static List<CityPid> PaseCityPid(String json) {
        List<CityPid> list = new ArrayList<CityPid>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int pId = object.getInt("pId");
                String pName = object.getString("pName");
                CityPid cityPid = new CityPid(pId, pName);
                list.add(cityPid);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    //获取地图上站点的详情
    public static List<MapSiteInfo> PaseMapSite(String json) {
        List<MapSiteInfo> list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                String CITY = object.getString("CITY");
                String deploytime = object.getString("deploytime");
                String electrovalency = object.getString("electrovalency");
                String id = object.getString("id");
                String name = object.getString("name");
                String storageCapacity = object.getString("storageCapacity");
                MapSiteInfo mapSiteInfo = new MapSiteInfo(deploytime, CITY, electrovalency, id, name, storageCapacity);
                list.add(mapSiteInfo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    //站点全列表
    public static SiteLists PaseSiteLists(String json) {
        SiteLists siteLists = null;
        try {
            JSONObject object = new JSONObject(json);
            int currentPage = object.getInt("currentPage");
            int rows = object.getInt("rows");
            int totalPage = object.getInt("totalPage");
            JSONArray array = object.getJSONArray("listSite");
            ArrayList<SiteListInfo> list = new ArrayList<SiteListInfo>();
            for (int i = 0; i < array.length(); i++) {
                String urlsite = null;
                String manage=null;
                JSONObject object2 = array.getJSONObject(i);
                String S_PROVNAME = object2.getString("S_PROVNAME");
                String companyName = object2.getString("companyName");
                String focus = object2.getString("focus");
                String kind = object2.getString("kind");
                String name = object2.getString("name");
                String siteId = object2.getString("siteId");
                String status = object2.getString("status");
                if (!object2.isNull("manage")) {
                     manage = object2.getString("manage");
                }
                if (!object2.isNull("url")) {
                    urlsite = object2.getString("url");
                }
                SiteListInfo newsInfo = new SiteListInfo(manage,S_PROVNAME, companyName, focus, kind, name, siteId, status, urlsite);
                list.add(newsInfo);
            }
            siteLists = new SiteLists(currentPage, rows, totalPage, list);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return siteLists;
    }

    //单元列表
    public static UnitList PaseUnitlist(String json) {
        UnitList unitList = null;
        ArrayList<UnitList.ModulelistBean> modulelistBeans = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(json);
            String current = object.getString("current");
            int status = object.getInt("status");
            String unitId = object.getString("unitId");
            String voltage = object.getString("voltage");
            JSONArray array = object.getJSONArray("modulelist");
            for (int i = 0; i < array.length(); i++) {
                JSONObject object1 = array.getJSONObject(i);
                String moudleId = object1.getString("moudleId");
                UnitList.ModulelistBean modulelistBean = new UnitList.ModulelistBean(moudleId);
                modulelistBeans.add(modulelistBean);
            }
            unitList = new UnitList(current, status, voltage, unitId, modulelistBeans);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return unitList;
    }

    //模块列表
    public static MoudleList PaseMoudlelist(String json) {
        MoudleList moudleList = null;
        ArrayList<MoudleList.BatterylistBean> list = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(json);
            String moudleid = object.getString("moudleid");
            String current = object.getString("current");
            String temperature = object.getString("temperature");
            String voltage = object.getString("voltage");
            JSONArray array = object.getJSONArray("batterylist");
            for (int i = 0; i < array.length(); i++) {
                JSONObject object1 = array.getJSONObject(i);
                String batteryId = object1.getString("batteryId");
                MoudleList.BatterylistBean batterylistBean = new MoudleList.BatterylistBean(batteryId);
                list.add(batterylistBean);
            }
            moudleList = new MoudleList(moudleid, current, temperature, voltage, list);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return moudleList;
    }

    //电池列表
    public static BatteryList PaseBatteryList(String json) {
        BatteryList batteryList = null;
        try {
            JSONObject object = new JSONObject(json);
            String batteryid = object.getString("batteryid");
            String current = object.getString("current");
            String soc = object.getString("soc");
            String soh = object.getString("soh");
            String state = object.getString("state");
            String temperature = object.getString("temperature");
            String voltage = object.getString("voltage");
            batteryList = new BatteryList(batteryid, current, soc, state, soh, temperature, voltage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return batteryList;
    }

    //设备基本信息
    public static DeviceInfo PaseInfo(String json) {
        DeviceInfo deviceInfo = null;
        try {
            JSONObject object = new JSONObject(json);
            String deploy_time = object.getString("deploy_time");
            String equip_id = object.getString("equip_id");
            double power = object.getDouble("power");
            double stored_energy = object.getDouble("stored_energy");
            double voltage = object.getDouble("voltage");
            deviceInfo = new DeviceInfo(deploy_time, power, equip_id, stored_energy, voltage);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return deviceInfo;
    }

    //设备基本参数
    public static DeviceCanshu PaseCanshu(String json) {
        DeviceCanshu deviceCanshu = null;
        try {
            JSONObject object = new JSONObject(json);
            JSONObject object1 = object.getJSONObject("obj");
            JSONObject object2 = object1.getJSONObject("devices");
            double voltage = object2.getDouble("voltage");
            String equip_id = object2.getString("equipId");
            double electricCurrent = object2.getDouble("electricCurrent");
            double holdTime = object2.getDouble("holdTime");
            double temperature = object2.getDouble("temperature");
            deviceCanshu = new DeviceCanshu(electricCurrent, equip_id, holdTime, temperature, voltage);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return deviceCanshu;
    }

    //设备状态
    public static DeviceState PaseState(String json) {
        DeviceState deviceState = null;
        try {
            JSONObject object = new JSONObject(json);
            JSONObject object1 = object.getJSONObject("obj");
            String state = object1.getString("state");
            String time = object1.getString("time");
            deviceState = new DeviceState(state, time);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return deviceState;
    }
}
