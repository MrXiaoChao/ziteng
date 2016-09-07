package com.example.john.ziteng.urlpath;

/**
 * Created by john on 2016/3/23.
 */
public class Path {
    //用户登录
    public static String LoginPath="http://123.57.251.129:8088/dem/client/clientinter!doNotNeedSessionAndSecurity_login.do?";

    //关注站点轮播图
    public static String PicPath="http://123.57.251.129:8088/dem/client/clientinfo!doNotNeedSessionAndSecurity_findInformation.do?";

    //关注站点
    public static String SitePath="http://123.57.251.129:8088/dem/client/clientsite!doNotNeedSessionAndSecurity_findSiteByUserId.do?";

    //资讯快递
    public static String NewsPath="http://123.57.251.129:8088/dem/client/clientinfo!doNotNeedSessionAndSecurity_findInformationByCategory.do?";

    //轮播图片webview
    public static String PicPathWeb="http://123.57.251.129:8088/dem/phone/station/informationNews.jsp?";

    //个人信息配置
    public static String PersonalPath="http://123.57.251.129:8088/dem/client/clientremind!doNotNeedSessionAndSecurity_infoConfiguration.do?";

    //通信流量统计
    public static String liulian="http://123.57.251.129:8088/dem/phone/statistic/communicationFlow.jsp?loginname=";

    //存储数据量统计
    public static String shuju="http://123.57.251.129:8088/dem/phone/statistic/dataStorage.jsp?loginname=";

    //用户活动日志实时监控
    public static String huodong="http://123.57.251.129:8088/dem/phone/statistic/monitoring.jsp?loginname=";

    //APP和UI后端crash报告

    public static String crash="http://123.57.251.129:8088/dem/phone/statistic/crash.jsp?zyw=";

    //站点全列表
    public static String SiteLB="http://123.57.251.129:8088/dem/phone/site/sitelist.jsp?loginname=";

    //停电站点全列表
    public static String TdSite="http://123.57.251.129:8088/dem/phone/site/tdsitelist.jsp?zyw=";

    //修改手机号码

    public static String ChangPhone="http://123.57.251.129:8088/dem/client/clientremind!doNotNeedSessionAndSecurity_changePhone.do?";

    //修改Email
    public static String ChangEmail="http://123.57.251.129:8088/dem/client/clientremind!doNotNeedSessionAndSecurity_changeEmail.do?";

    //全网监控
    public static String Jiankong="http://123.57.251.129:8088/dem/client/clientsite!doNotNeedSessionAndSecurity_SumSite.do";

    //当前储能量
    public static String ChuPower="http://123.57.251.129:8088/dem/phone/site/current.jsp?";

    //总共省电量和平均省电量
    public static String Dianliang="http://123.57.251.129:8088/dem/phone/site/Electricity.jsp?";

    //总共省钱数和平均省钱数
    public static String Qian="http://123.57.251.129:8088/dem/phone/site/phone_savemoney.jsp?";
    //减少碳的排量
    public static String Pailiang="http://123.57.251.129:8088/dem/phone/site/reduce_emissions.jsp?";

    //年月日停电次数直方图
    public static String Cishu="http://123.57.251.129:8088/dem/phone/site/power_failnum.jsp?siteid=";
    //年月日停电总时长直方图
    public static String Shichang="http://123.57.251.129:8088/dem/phone/site/power_faillength.jsp?siteid=";
    //年月日UPS供电总时长直方图
    public static String Zongshichang="http://123.57.251.129:8088/dem/phone/site/upslength.jsp?siteid=";
    //停电全列表
    public static String Tingliebiao="http://123.57.251.129:8088/dem/phone/site/blackoutlist.jsp?siteid=";

    //当日停电次数
    public static String Dangri="http://123.57.251.129:8088/dem/phone/site/dayfailure.jsp?";
    //当月停电次数
    public static String Dangyue="http://123.57.251.129:8088/dem/phone/site/monthfailure.jsp?";
    //当年停电次数
    public static String Dangnian="http://123.57.251.129:8088/dem/phone/site/yearfailure.jsp?";
    //当年UPS总供电量
    public static String USP="http://123.57.251.129:8088/dem/phone/site/upspower.jsp?";
    //修改设备密码
    public static String ChangPassWord="http://123.57.251.129:8088/dem/client/clientinter!doNotNeedSessionAndSecurity_changePassword.do?";

    //设备群列表
    public static String DeviceGroup="http://123.57.251.129:8088/dem/client/clientequipgroup!doNotNeedSessionAndSecurity_getEquipAndGroupById.do?";

    //提醒状态初始化
    public static String Warm="http://123.57.251.129:8088/dem/client/clientremind!doNotNeedSessionAndSecurity_Conditions.do?";

    //改变我的提醒状态
    public static String Warm1="http://123.57.251.129:8088/dem/client/clientremind!doNotNeedSessionAndSecurity_changeEmailConditions.do?";

    //设备群列表
    public static String DeviceGroupInfo="http://123.57.251.129:8088/dem/client/clientequipgroup!doNotNeedSessionAndSecurity_getEquipAndUnitById.do?";

    //设备历史入口告警信息
    public static String Gaojing="http://123.57.251.129:8088/dem/phone/station/equipmentAlarmList.jsp?";

    //设备历史入口状态全列表
    public static String ZhuangTai="http://123.57.251.129:8088/dem/phone/station/allEquipmentStatus.jsp?";

    //设备历史入口参数全列表
    public static String Canshu="http://123.57.251.129:8088/dem/phone/station/equipmentParameterList.jsp?";

    //设备历史入口参数曲线
    public static String Quxian="http://123.57.251.129:8088/dem/phone/station/equipmentParameterCurve.jsp?";

    //设备状态
    public static String NowZhuangtai="http://123.57.251.129:8088/dem/phone/station/deviceList.jsp?";

    //设备基本参数
    public static String JibenCanshu="http://123.57.251.129:8088/dem/phone/station/equipmentParameter.jsp?";

    //重要告警信息
    public static String ImportWarn="http://123.57.251.129:8088/dem/phone/station/alarmInformation.jsp?";
    //电池列表
    public static String PowerLiebiao="http://123.57.251.129:8088/dem/phone/station/allDataList.jsp?";

    //告警信息 首页那里
    public static String SiteGaojing="http://123.57.251.129:8088/dem/client/clientinfo!doNotNeedSessionAndSecurity_getWarnsByUserId.do?";

    //设备控制
    public static String DeviceControl="http://123.57.251.129:8088/dem/client/clientequip!doNotNeedSessionAndSecurity_ControlEquip.do?";

    //提交意见
    public static String Commit="http://123.57.251.129:8088/dem/client/clientfeedback!doNotNeedSessionAndSecurity_update.do?";

    //更新说明
    public static String Gengxin="http://123.57.251.129:8088/dem/phone/station/update_instructions.jsp?zyw=";

    //使用帮助
    public static String Help="http://123.57.251.129:8088/dem/phone/station/use_help.jsp?zyw=";

    //验证设备控制密码
    public static String DevicePassWord="http://123.57.251.129:8088/dem/client/clientremind!doNotNeedSessionAndSecurity_password.do?";

    //模块控制列表
    public static  String DeviceModer="http://123.57.251.129:8088/dem/client/clientequip!doNotNeedSessionAndSecurity_ControlModules.do?";

    //获取地图上的站点
    public static String WebMap="http://123.57.251.129:8088/dem/client/clientsite!doNotNeedSessionAndSecurity_getSiteMap.do?";

    //城市列表
    public static String CityList="http://123.57.251.129:8088/dem/client/clientcity!doNotNeedSessionAndSecurity_getallcityforAndrorid.do?";

    //修改电流
    public static String ChangDianliu="http://123.57.251.129:8088/dem/client/clientequip!doNotNeedSessionAndSecurity_ModulesControl.do?";

    //单元控制模块更改
    public static String Unit="http://123.57.251.129:8088/dem/client/clientequip!doNotNeedSessionAndSecurity_unitControl.do?";
    //获取验证码
    public static String YZM="http://123.57.251.129:8088/dem/client/clientremind!doNotNeedSessionAndSecurity_emailxx.do?";
    //验证验证码
    public static String YZyzm="http://123.57.251.129:8088/dem/client/clientremind!doNotNeedSessionAndSecurity_Validationemailxx.do?";

    //修改密码
    public static String CpassWord="http://123.57.251.129:8088/dem/client/clientremind!doNotNeedSessionAndSecurity_savePassword.do?";

    //设备基本信息
    public static String DeviceXinxi="http://123.57.251.129:8088/dem/phone/station/equipmentBasicInformation.jsp?";
    //站点详情数据及天气状况
    public static String SiteDelicInfo="http://123.57.251.129:8088/dem/client/clientcity!doNotNeedSessionAndSecurity_getWeather.do?";

    //站点关键数据
    public static String SiteGuanJiang="http://123.57.251.129:8088/dem/client/clientsite!doNotNeedSessionAndSecurity_criticalData.do?";

    //城市名称与PID值
    public static String CityPid="http://123.57.251.129:8088/dem/client/clientsite!doNotNeedSessionAndSecurity_getprovince.do";
    //地图上的关注与未关注
    public static String ChangFourcs="http://123.57.251.129:8088/dem/client/clientsite!doNotNeedSessionAndSecurity_changeFocus.do?";

    //获取地图上的站点详情
    public static String MapSiteInfo="http://123.57.251.129:8088/dem/client/clientsite!doNotNeedSessionAndSecurity_findSiteBySiteId.do?";
    //停电站点全列表
    public static String SiteListTD="http://123.57.251.129:8088/dem/client/clientsite!doNotNeedSessionAndSecurity_findSomeSiteByUserid.do?";
    //站点全列表
    public static  String SiteList="http://123.57.251.129:8088/dem/client/clientsite!doNotNeedSessionAndSecurity_findSiteByUserid.do?";
    //单元列表
    public static  String UintList="http://123.57.251.129:8088/dem/client/clientequipgroup!doNotNeedSessionAndSecurity_getMoudleAndUnitById.do?";
    //模块列表
    public static String  ModuleList="http://123.57.251.129:8088/dem/client/clientequipgroup!doNotNeedSessionAndSecurity_getMoudleAndBatteryById.do?";
    //电池列表
    public static String BatteryList="http://123.57.251.129:8088/dem/client/clientequipgroup!doNotNeedSessionAndSecurity_getBatteryById.do?";
    //设备基本信息
    public static String DeviceInfo="http://123.57.251.129:8088/dem/base/equipment!doNotNeedSessionAndSecurity_EquipmentBasicInformation.do?";
    //设备基本参数
    public static String DeviceCanshu="http://123.57.251.129:8088/dem/base/equipment!doNotNeedSessionAndSecurity_EquipmentParameter.do?";
    //设备状态
    public static String DeviceState="http://123.57.251.129:8088/dem/base/equipment!doNotNeedSessionAndSecurity_equipmentstate.do?";
    //站点新闻
    public static String SiteNews="http://123.57.251.129:8088/dem/client/clientinfo!doNotNeedSessionAndSecurity_findInformationBySiteId.do?";
    //站点告警
    public static String SiteWarm="http://123.57.251.129:8088/dem/phone/site/sitewarning.jsp?siteid=";
    //设备电流控制
    public static String dlCortol="http://123.57.251.129:8088/dem/client/clientequip!doNotNeedSessionAndSecurity_controlEquipCurrent.do?";
    //设备开关控制
    public static String kgCortol="http://123.57.251.129:8088/dem/client/clientequip!doNotNeedSessionAndSecurity_controlEquip.do?";
    //单元开关展示
    public static String Ukg="http://123.57.251.129:8088/dem/client/clientequip!doNotNeedSessionAndSecurity_ControlUnits.do?";
    //单元电流展示
    public static String Udl="http://123.57.251.129:8088/dem/client/clientequip!doNotNeedSessionAndSecurity_ControlUnitsCurrent.do?";
    //单元电流控制
    public static String UdlK="http://123.57.251.129:8088/dem/client/clientequip!doNotNeedSessionAndSecurity_controlMoudleCurrent.do?";
    //单元开关控制
    public static String UkgC="http://123.57.251.129:8088/dem/client/clientequip!doNotNeedSessionAndSecurity_controlMoudle.do?";
    //模块电流
    public static String MDl="http://123.57.251.129:8088/dem/client/clientequip!doNotNeedSessionAndSecurity_ControlModules.do?";
    //模块修改之后的电流
    public static String MDXG="http://123.57.251.129:8088/dem/client/clientequip!doNotNeedSessionAndSecurity_controlUnitCurrent.do?";
    //设备开关展示
    public static String SKG="http://123.57.251.129:8088/dem/client/clientequip!doNotNeedSessionAndSecurity_ControlEquipState.do?";
    //采样频率发送
    public static String PL="http://123.57.251.129:8088/dem/client/clientequip!doNotNeedSessionAndSecurity_controlEquipfrequency.do?";
    //设备高级功能设置
    public static String SGJ="http://123.57.251.129:8088/dem/client/clientequip!doNotNeedSessionAndSecurity_controlEquipOperations.do?";
    //单元高级功能设置
    public static String DGJ="http://123.57.251.129:8088/dem/client/clientequip!doNotNeedSessionAndSecurity_controlMoudleOperations.do?";
    //修改用户登录密码
    public static String CP="http://123.57.251.129:8088/dem/client/clientinter!doNotNeedSessionAndSecurity_updatePassword.do?";
}
