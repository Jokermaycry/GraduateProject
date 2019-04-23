package com.wangweiming.myapplication.view.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.image_selector.ImageSelector;
import com.wangweiming.myapplication.image_selector.MultiImageSelector;
import com.wangweiming.myapplication.image_selector.multi_image_selector.utils.BmobUtils;
import com.wangweiming.myapplication.model.Userbean;
import com.wangweiming.myapplication.view.activity.user_denglu;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

import static android.app.Activity.RESULT_OK;


public class Fragment_user extends Fragment {

    Toolbar tb;
    ImageView ivService,userImage;

    LinearLayout llOrder;

    TextView tvUserName;

    TextView tvWaitPay;

    TextView tvWaitFh;

    TextView tvWaitSh;

    TextView tvYiFh;



    TextView tvAddress;

    TextView tvLogout;

    public static final int REQUEST_CODE_SELECT_IMAGE = 0;
    public static final int REQUEST_READ_EXTERNAL_STORAGE = 1;
    public static final int REQUEST_WRITE_EXTERNAL_STORAGE = 2;
    public static final int REQUEST_CAMERA = 3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_me, container, false);
        Log.e("test","create");
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
        userImage=(ImageView)v.findViewById(R.id.user_img);
        checkIsLogIn();
        setUserImage();
        registClickListener();
        return v;
    }
    private void setUserImage() {
        if(Userbean.isLogin()) {
            Log.e("test","islogin");

                Userbean user=Userbean.getCurrentUser(Userbean.class);

                if(user.getAvatar()!=null)
                {
                    Log.e("test","notnull");
                    Glide.with(this).load(user.getAvatar().getFileUrl()).into(userImage);
                }
                else {
                    Log.e("test","null");
                    Glide.with(this).load(user.getAvatar().getFileUrl()).into(userImage);
                }



        }
    }
    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          switch (v.getId())
          {
              case R.id.user_img:
                  select();
                  break;
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
        if (Userbean.isLogin()) {
            tvUserName.setText("点击登陆");
            Userbean.logOut();
            Glide.with(this).load(R.drawable.plus).into(userImage);
            //userImage.setBackgroundResource(R.drawable.plus);
            Toast.makeText(getContext(),"已注销",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(),"请先登陆",Toast.LENGTH_SHORT).show();
            Glide.with(this).load(R.drawable.plus).into(userImage);
            //userImage.setBackgroundResource(R.drawable.plus);
        }

    }
    private void login() {
        if (!Userbean.isLogin()) {
            startActivity(new Intent(getActivity(), user_denglu.class));
        }
    }

    private void registClickListener() {
        userImage.setOnClickListener(onClickListener);
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
        if (Userbean.isLogin()) {
            tvUserName.setText(BmobUser.getCurrentUser(Userbean.class).getUsername());

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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case REQUEST_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getContext(), "您已经同意了读取外置存储器权限", Toast.LENGTH_SHORT).show();

                    MultiImageSelector.create()
                            .showCamera(true) // show camera or not. true by default
                            .count(9) // max select image size, 9 by default. used width #.multi()
                            .multi() // multi mode, default mode;
                            .start(this, REQUEST_CODE_SELECT_IMAGE);

                } else {
                    Toast.makeText(getContext(), "您已经拒绝了读取外置存储器权限", Toast.LENGTH_SHORT).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            case REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getContext(), "您已经同意了写入外置存储器权限", Toast.LENGTH_SHORT).show();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    Toast.makeText(getContext(), "您已经拒绝了写入外置存储器权限", Toast.LENGTH_SHORT).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            case REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getContext(), "您已经同意了照相机权限", Toast.LENGTH_SHORT).show();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    Toast.makeText(getContext(), "您已经拒绝了照相机权限", Toast.LENGTH_SHORT).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_SELECT_IMAGE) {
            if (resultCode == RESULT_OK) {
                final List<String> paths = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                Glide.with(this).load(paths.get(0)).into(userImage);


                final BmobFile bmobFile = new BmobFile(new File(paths.get(0)));


                bmobFile.upload(new UploadFileListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            BmobUtils.toast(getContext(),"上传头像成功");
                            Userbean user = BmobUser.getCurrentUser(Userbean.class);
                            if (user == null)
                                return;

                            user.setAvatar(bmobFile);
                            user.update(new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e == null) {
                                        Log.e("success", e.getMessage());
                                    }
                                        else {
                                        Log.e("error", e.getMessage());
                                    }
                                }
                            });
                        } else {

                            BmobUtils.toast(getContext(),"上传头像失败：" + e.getMessage());

                        }

                    }
                });

                for (String s : paths) {
                    Log.e("path", s);
                }
            }
        }
    }

    public void select() {
        if(Userbean.isLogin()) {
            if (ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                MultiImageSelector.create()
                        .showCamera(true) // show camera or not. true by default
                        .count(9) // max select image size, 9 by default. used width #.multi()
                        .multi() // multi mode, default mode;
                        .start(this, REQUEST_CODE_SELECT_IMAGE);
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_READ_EXTERNAL_STORAGE);
            }

            if (ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_WRITE_EXTERNAL_STORAGE);
            }

            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CAMERA},
                        REQUEST_CAMERA);
            }
        }
        else {
            Toast.makeText(getContext(), "请先登陆", Toast.LENGTH_SHORT).show();
            login();
        }


    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
