package com.example.john.ziteng.domain;

/**
 * 站点概况详情
 * Created by john on 2016/4/25.
 */
public class SiteDelicInfo {


    /**
     * AllUps : 2000
     * deploytime : 2016-05-13 17:40:57
     * electrovalency : 1
     * energy_storage : 658
     * latitude : 36.6728230000
     * longitude : 117.1345356327
     * name : 济南UPS测试
     * power : 173
     * siteId : 1000000
     * siteState : 负载部分断电
     * statusTime : 2016-12-12 11:06:04
     * storageCapacity : 20
     */

    private BaseInformationBean baseInformation;
    /**
     * allSaveElectricity : 100.0
     * allSaveMoney : 1000.0
     * allemissions : 2000.0
     * aveSaveElectricity : 1.5
     * aveSaveMoney : 20.0
     * aveemissions : 2000.0
     */

    private BenefitBean benefit;
    /**
     * dayOutpowerNum : 10
     * dayOutpowerOfen : 10
     * dayUps : 10
     * monOutpowerNum : 20
     * monOutpowerOfen : 20
     * monUps : 20
     * yearOutpowerNum : 30
     * yearOutpowerOfen : 30
     * yearUps : 30
     */

    private KeyDateBean keyDate;
    /**
     * city : 西城区
     * cond : 霾
     * dir : 西风3-4级
     * max : 3°C - -3°C
     * temperature : 3
     */

    private WeatherBean weather;

    public BaseInformationBean getBaseInformation() {
        return baseInformation;
    }

    public void setBaseInformation(BaseInformationBean baseInformation) {
        this.baseInformation = baseInformation;
    }

    public BenefitBean getBenefit() {
        return benefit;
    }

    public void setBenefit(BenefitBean benefit) {
        this.benefit = benefit;
    }

    public KeyDateBean getKeyDate() {
        return keyDate;
    }

    public void setKeyDate(KeyDateBean keyDate) {
        this.keyDate = keyDate;
    }

    public WeatherBean getWeather() {
        return weather;
    }

    public void setWeather(WeatherBean weather) {
        this.weather = weather;
    }

    public static class BaseInformationBean {
        private String AllUps;
        private String deploytime;
        private int electrovalency;
        private String energy_storage;
        private String latitude;
        private String longitude;
        private String name;
        private String power;
        private String siteId;
        private String siteState;
        private String statusTime;
        private String storageCapacity;

        public String getAllUps() {
            return AllUps;
        }

        public void setAllUps(String AllUps) {
            this.AllUps = AllUps;
        }

        public String getDeploytime() {
            return deploytime;
        }

        public void setDeploytime(String deploytime) {
            this.deploytime = deploytime;
        }

        public int getElectrovalency() {
            return electrovalency;
        }

        public void setElectrovalency(int electrovalency) {
            this.electrovalency = electrovalency;
        }

        public String getEnergy_storage() {
            return energy_storage;
        }

        public void setEnergy_storage(String energy_storage) {
            this.energy_storage = energy_storage;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPower() {
            return power;
        }

        public void setPower(String power) {
            this.power = power;
        }

        public String getSiteId() {
            return siteId;
        }

        public void setSiteId(String siteId) {
            this.siteId = siteId;
        }

        public String getSiteState() {
            return siteState;
        }

        public void setSiteState(String siteState) {
            this.siteState = siteState;
        }

        public String getStatusTime() {
            return statusTime;
        }

        public void setStatusTime(String statusTime) {
            this.statusTime = statusTime;
        }

        public String getStorageCapacity() {
            return storageCapacity;
        }

        public void setStorageCapacity(String storageCapacity) {
            this.storageCapacity = storageCapacity;
        }
    }

    public static class BenefitBean {
        private String allSaveElectricity;
        private String allSaveMoney;
        private String allemissions;
        private String aveSaveElectricity;
        private String aveSaveMoney;
        private String aveemissions;

        public String getAllSaveElectricity() {
            return allSaveElectricity;
        }

        public void setAllSaveElectricity(String allSaveElectricity) {
            this.allSaveElectricity = allSaveElectricity;
        }

        public String getAllSaveMoney() {
            return allSaveMoney;
        }

        public void setAllSaveMoney(String allSaveMoney) {
            this.allSaveMoney = allSaveMoney;
        }

        public String getAllemissions() {
            return allemissions;
        }

        public void setAllemissions(String allemissions) {
            this.allemissions = allemissions;
        }

        public String getAveSaveElectricity() {
            return aveSaveElectricity;
        }

        public void setAveSaveElectricity(String aveSaveElectricity) {
            this.aveSaveElectricity = aveSaveElectricity;
        }

        public String getAveSaveMoney() {
            return aveSaveMoney;
        }

        public void setAveSaveMoney(String aveSaveMoney) {
            this.aveSaveMoney = aveSaveMoney;
        }

        public String getAveemissions() {
            return aveemissions;
        }

        public void setAveemissions(String aveemissions) {
            this.aveemissions = aveemissions;
        }
    }

    public static class KeyDateBean {
        private String dayOutpowerNum;
        private String dayOutpowerOfen;
        private String dayUps;
        private String monOutpowerNum;
        private String monOutpowerOfen;
        private String monUps;
        private String yearOutpowerNum;
        private String yearOutpowerOfen;
        private String yearUps;

        public String getDayOutpowerNum() {
            return dayOutpowerNum;
        }

        public void setDayOutpowerNum(String dayOutpowerNum) {
            this.dayOutpowerNum = dayOutpowerNum;
        }

        public String getDayOutpowerOfen() {
            return dayOutpowerOfen;
        }

        public void setDayOutpowerOfen(String dayOutpowerOfen) {
            this.dayOutpowerOfen = dayOutpowerOfen;
        }

        public String getDayUps() {
            return dayUps;
        }

        public void setDayUps(String dayUps) {
            this.dayUps = dayUps;
        }

        public String getMonOutpowerNum() {
            return monOutpowerNum;
        }

        public void setMonOutpowerNum(String monOutpowerNum) {
            this.monOutpowerNum = monOutpowerNum;
        }

        public String getMonOutpowerOfen() {
            return monOutpowerOfen;
        }

        public void setMonOutpowerOfen(String monOutpowerOfen) {
            this.monOutpowerOfen = monOutpowerOfen;
        }

        public String getMonUps() {
            return monUps;
        }

        public void setMonUps(String monUps) {
            this.monUps = monUps;
        }

        public String getYearOutpowerNum() {
            return yearOutpowerNum;
        }

        public void setYearOutpowerNum(String yearOutpowerNum) {
            this.yearOutpowerNum = yearOutpowerNum;
        }

        public String getYearOutpowerOfen() {
            return yearOutpowerOfen;
        }

        public void setYearOutpowerOfen(String yearOutpowerOfen) {
            this.yearOutpowerOfen = yearOutpowerOfen;
        }

        public String getYearUps() {
            return yearUps;
        }

        public void setYearUps(String yearUps) {
            this.yearUps = yearUps;
        }
    }

    public static class WeatherBean {
        private String city;
        private String cond;
        private String dir;
        private String max;
        private String temperature;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCond() {
            return cond;
        }

        public void setCond(String cond) {
            this.cond = cond;
        }

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

        public String getMax() {
            return max;
        }

        public void setMax(String max) {
            this.max = max;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }
    }
}
