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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.view.activity.user_denglu;

import cn.bmob.v3.BmobUser;


public class Fragment_user extends Fragment {

    Toolbar tb;
    ImageView ivService;

    LinearLayout llOrder;

    TextView tvUserName;

    TextView tvWaitPay;

    TextView tvWaitFh;

    TextView tvWaitSh;

    TextView tvYiFh;



    TextView tvAddress;

    TextView tvLogout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_me, container, false);

        tb = (Toolbar) v.findViewById(R.id.toolbar);
        tvUserName = (TextView) v.findViewById(R.id.tv_user_name);
        tvWaitPay = (TextView) v.findViewById(R.id.tv_wait_pay);
        tvWaitFh = (TextView) v.findViewById(R.id.tv_wait_fh);
        tvWaitSh = (TextView) v.findViewById(R.id.tv_wait_sh);
        tvYiFh = (TextView) v.findViewById(R.id.tv_yi_fh);
        tvAddress = (TextView) v.findViewById(R.id.tv_address);
        tvLogout = (TextView) v.findViewById(R.id.tv_logout);
        llOrder=(LinearLayout)v.findViewById(R.id.ll_order) ;
        ivService=(ImageView)v.findViewById(R.id.iv_service);
        checkIsLogIn();
        registClickListener();
        return v;
    }
    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          switch (v.getId())
          {
              case R.id.tv_user_name:
                  login();
                  break;
              case R.id.tv_wait_pay:
                  break;
              case R.id.tv_wait_fh:
                  break;
              case R.id.tv_wait_sh:
                  break;
              case R.id.tv_yi_fh:
                  break;
              case R.id.tv_address:
                  break;
              case R.id.tv_logout:
                  logout();
                  break;
              case R.id.ll_order:
                  break;
              case R.id.iv_service:
                  Toast.makeText(getContext(),"该功能待开发",Toast.LENGTH_SHORT).show();
                  break;
          }
        }
    };

    private void logout() {
        if (BmobUser.isLogin()) {
            tvUserName.setText("点击登陆");
            BmobUser.logOut();
            Toast.makeText(getContext(),"已注销",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(),"请先登陆",Toast.LENGTH_SHORT).show();
        }

    }
    private void login() {
        if (!BmobUser.isLogin()) {
            startActivity(new Intent(getActivity(), user_denglu.class));
        }
    }

    private void registClickListener() {
        tvUserName.setOnClickListener(onClickListener);
        tvWaitPay.setOnClickListener(onClickListener);
        tvWaitFh.setOnClickListener(onClickListener);
        tvWaitSh.setOnClickListener(onClickListener);
        tvYiFh.setOnClickListener(onClickListener);
        tvAddress.setOnClickListener(onClickListener);
        tvLogout.setOnClickListener(onClickListener);
        llOrder.setOnClickListener(onClickListener);
        ivService.setOnClickListener(onClickListener);
    }
    private void checkIsLogIn() {
        if (BmobUser.isLogin()) {
            tvUserName.setText(BmobUser.getCurrentUser(BmobUser.class).getUsername());

        } else {

            tvUserName.setText("请先登陆");
        }
    }
    private void showNormalDialog() {
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
                        startActivity(new Intent(getActivity(), user_denglu.class));
                    }
                });

        // 显示
        normalDialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
