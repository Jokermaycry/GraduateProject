package com.wangweiming.myapplication.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.model.SecondHandbean;
import com.wangweiming.myapplication.model.Userbean;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Activity_additems extends AppCompatActivity {
        Button bt;
        EditText name,price,phone,detail;
        Userbean user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtwo);

        user = BmobUser.getCurrentUser(Userbean.class);//获取登录成功后的本地用户信息
        bt=(Button)findViewById(R.id.add);
        name=(EditText)findViewById(R.id.name);
        price=(EditText)findViewById(R.id.price);
        phone=(EditText)findViewById(R.id.phone);
        detail=(EditText)findViewById(R.id.detail);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                add();
            }
        });
    }

    /**
     * 发布二手物品
     */
    private  void  add()
    {
        String getname = name.getText().toString();
        String getphone = phone.getText().toString();
        String getprice = price.getText().toString();
        String getdetail = detail.getText().toString();
        String type="2";

        SecondHandbean gd = new SecondHandbean();
        gd.setName(getname);
        gd.setPhone(getphone);
        gd.setPrice(getprice);
        gd.setDetail(getdetail);
        gd.setType(type);

        gd.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(user!=null) {
                    if (e == null) {
                        Toast.makeText(getApplication(), "发布成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplication(), "发布失败", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplication(), "请先登录", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplication(),user_denglu.class);
                    startActivity(i);
                }
            }
        });
    }
}