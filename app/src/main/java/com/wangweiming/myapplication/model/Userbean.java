package com.wangweiming.myapplication.model;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

public class Userbean  extends BmobUser{
    BmobFile avatar;



    public BmobFile getAvatar() {
        return avatar;
    }

    public void setAvatar(BmobFile avatar) {
        this.avatar = avatar;
    }
}
