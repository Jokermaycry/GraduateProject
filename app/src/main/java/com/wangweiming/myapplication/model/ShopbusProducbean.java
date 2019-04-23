package com.wangweiming.myapplication.model;

import cn.bmob.v3.BmobObject;

public class ShopbusProducbean extends BmobObject {

        private Userbean user;

        private Bookbean bookbean;

        private int num;

        public ShopbusProducbean(Userbean user,Bookbean bookbean, int num) {
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

        public Userbean getUser() {
            return user;
        }

        public void setUser(Userbean user) {
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
