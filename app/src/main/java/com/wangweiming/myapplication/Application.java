package com.wangweiming.myapplication;

import android.content.Context;

import com.wangweiming.myapplication.util.SharedPreferencesUtil;

import cn.bmob.v3.Bmob;

public class Application extends android.app.Application {
    private static Context mContext = null;
    private static  final  String BMOBKEY="705250f852f808598eda658ad36535c3";

    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext=this;
        Bmob.initialize(this,BMOBKEY);
        SharedPreferencesUtil.getInstance(getApplicationContext(),"user");

    }
}
