package com.wangweiming.myapplication.model

import cn.bmob.v3.BmobObject


class Bookbean : BmobObject() {
    private var imageurl: String? = null
    private var name: String? = null
    private var price: String? = null
    private var phone: String? = null
    private var detail: String? = null
    fun getImageurl(): String? {
        return imageurl
    }

    fun setImageurl(imageurl: String) {
        this.imageurl = imageurl
    }



    fun getDetail(): String? {
        return detail
    }

    fun setDetail(detail: String) {
        this.detail = detail
    }


    fun getPhone(): String? {
        return phone
    }

    fun setPhone(phone: String) {
        this.phone = phone
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }


    fun getPrice(): String? {
        return price
    }

    fun setPrice(price: String) {
        this.price = price
    }


}
