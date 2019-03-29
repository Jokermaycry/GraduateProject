package com.wangweiming.myapplication.view.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.view.activity.Activity_user_denglu;

import cn.bmob.v3.BmobUser;


public class Fragment_user extends Fragment {


    public Fragment_user() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user, container, false);

        final Toolbar tb=(Toolbar)v.findViewById(R.id.toolbar);

        check();



//        //这是注销按钮
//        Button bt=(Button)v.findViewById(R.id.zhuxiao);
//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            if(user!=null) {
//                user.logOut();
//                Toast.makeText(getActivity(), "注销成功", Toast.LENGTH_LONG).show();
//            }
//            else {
//                Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_LONG).show();
//            }
//            }
//        });





        return v;
    }
    private void check( )
    {
        if(BmobUser.isLogin())
        {


        }
        else {
            showNormalDialog();
        }
    }

    private void showNormalDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(getContext());
        normalDialog.setIcon(R.drawable.ic_launcher);
        normalDialog.setTitle("未登录");
        normalDialog.setMessage("请先登陆");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        startActivity(new Intent(getActivity(), Activity_user_denglu.class));
                    }
                });

        // 显示
        normalDialog.show();
    }
}
