package com.wangweiming.myapplication.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/10/24.
 */

public class Goodsbean extends BmobObject {
    private String type;
    private String name;
    private String price;
    private String detail;
    private String phone;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDetail() {
        return detail;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setPhone(String phone) {
        this.phone =phone;
    }
}
