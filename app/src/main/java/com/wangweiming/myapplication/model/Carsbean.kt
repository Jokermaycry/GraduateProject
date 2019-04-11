package com.wangweiming.myapplication.model

import cn.bmob.v3.BmobObject

/**
 * Created by Administrator on 2018/3/28.
 */

class Carsbean : BmobObject() {
    private var name: String? = null
    private var price: String? = null
    private var detail: String? = null
    private var phone: String? = null
    fun setName (name: String)
    {
        this.name=name
    }
    fun getName(): String?
    {
        return name
    }


    fun setPrice (price: String)
    {
        this.price=price
    }
    fun getPrice(): String?
    {
        return price
    }


    fun setDetail (detail: String)
    {
        this.detail=detail
    }
    fun getDetail(): String?
    {
        return detail
    }


    fun setPhone (phone: String)
    {
        this.phone=phone
    }
    fun getPhone(): String?
    {
        return phone
    }
}
