package com.wangweiming.myapplication.model

import cn.bmob.v3.BmobObject


class Bookbean : BmobObject() {
    private var name: String? = null
    private var price: String? = null
    private var phone: String? = null

    fun getphone(): String? {
        return phone
    }

    fun setphone(phone: String) {
        this.phone = phone
    }

    fun getname(): String? {
        return name
    }

    fun setname(name: String) {
        this.name = name
    }


    fun getprice(): String? {
        return price
    }

    fun setprice(price: String) {
        this.price = price
    }


}
