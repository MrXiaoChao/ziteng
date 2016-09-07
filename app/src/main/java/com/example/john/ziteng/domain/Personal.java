package com.example.john.ziteng.domain;

/**
 * 个人信息配置
 * Created by john on 2016/3/25.
 */
public class Personal {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getGladmin() {
        return gladmin;
    }

    public void setGladmin(String gladmin) {
        this.gladmin = gladmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public Personal(String name, int sex, String company, String gladmin, String email, String phone, String descrip) {
        this.name = name;
        this.sex = sex;
        this.company = company;
        this.gladmin = gladmin;
        this.email = email;
        this.phone = phone;
        this.descrip = descrip;
    }

    private String name;
    private int sex;
    private String company;
    private String gladmin;
    private String email;
    private String phone;
    private String descrip;

}
