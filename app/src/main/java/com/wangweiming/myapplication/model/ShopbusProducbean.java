package com.wangweiming.myapplication.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

public class ShopbusProducbean extends BmobObject {

        private BmobUser user;

        private Bookbean bookbean;

        private int num;

        public ShopbusProducbean(BmobUser user,Bookbean bookbean, int num) {
            this.bookbean = bookbean;
            this.num = num;
            this.user = user;
        }
        public ShopbusProducbean() {
        }

        public ShopbusProducbean(Bookbean bookbean, int num) {
            this.bookbean = bookbean;
            this.num = num;
        }

        public BmobUser getUser() {
            return user;
        }

        public void setUser(BmobUser user) {
            this.user = user;
        }

        public Bookbean getBookbean() {
            return bookbean;
        }

        public void setBookbean(Bookbean bookbean) {
            this.bookbean = bookbean;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

}
