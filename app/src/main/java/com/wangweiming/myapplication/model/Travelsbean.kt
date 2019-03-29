package com.wangweiming.myapplication.model

import cn.bmob.v3.BmobObject
import com.wangweiming.myapplication.R

/**
 * Created by Administrator on 2018/3/28.
 */

class Travelsbean : BmobObject() {
    private var name: String? = null
    private var price: String? = null
    private var detail: String? = null
    private var phone: String? = null

   fun setNmae(name: String)
   {
       this.name=name
   }
    fun getName(): String?
    {
        return name
    }
    fun setPrice(name: String)
    {
        this.price=price
    }
    fun getPrice(): String?
    {
        return price
    }
    fun setDetail(name: String)
    {
        this.detail=detail
    }
    fun getDetail(): String?
    {
        return detail
    }

    fun setPhone(name: String)
    {
        this.phone=phone
    }
    fun getPhone(): String?
    {
        return phone
    }

}
