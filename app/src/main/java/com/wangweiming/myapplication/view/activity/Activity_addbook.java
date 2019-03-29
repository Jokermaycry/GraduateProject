package com.wangweiming.myapplication.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.model.Goodsbean;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Activity_addbook extends AppCompatActivity {
    Button bt;
    EditText name,price,phone;
    BmobUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addone);
        Bmob.initialize(this,"705250f852f808598eda658ad36535c3");
        user = BmobUser.getCurrentUser(BmobUser.class);
        bt=(Button)findViewById(R.id.add);
        name=(EditText)findViewById(R.id.name);
        price=(EditText)findViewById(R.id.price);
        phone=(EditText)findViewById(R.id.phone);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                add();
            }
        });
    }

    /**
     * 发布二手书
     */
    private void add()
    {


        String getname = name.getText().toString();
        String getphone = phone.getText().toString();
        String getprice = price.getText().toString();
        String type="1";

        Goodsbean bk = new Goodsbean();
        bk.setName(getname);
        bk.setPhone(getphone);
        bk.setPrice(getprice);
        bk.setType(type);

        bk.save(new SaveListener<String>() {
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
                    Intent i=new Intent(getApplication(),Activity_user_denglu.class);
                    startActivity(i);
                }
            }
        });
    }
}
