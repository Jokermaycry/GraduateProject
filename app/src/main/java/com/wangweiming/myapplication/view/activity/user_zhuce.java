package com.wangweiming.myapplication.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.model.Userbean;
import com.wangweiming.myapplication.util.SharedPreferencesUtil;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

    public class user_zhuce extends AppCompatActivity {
        Toolbar tb;
        Button zhuce;
        EditText getname, getpassword;
        String name,password;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.user_register);
            init();
            initToolBar();

        }
        private void initToolBar() {
            tb=(Toolbar)findViewById(R.id.toolbar);
            tb.setTitle("返回登录");
            tb.setNavigationIcon(R.drawable.bar_goback);
            tb.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(user_zhuce.this, user_denglu.class));
                    finish();
                }
            });
        }
        private void init(){
            zhuce = (Button) findViewById(R.id.zhuce);
            getname = (EditText) findViewById(R.id.getname);
            getpassword = (EditText) findViewById(R.id.getpassword);
            zhuce.setOnClickListener(new mClick());
        }
        private void saveData(){
            SharedPreferencesUtil.putData("username",name);
            SharedPreferencesUtil.putData("password",password);
        }
        private void register(){
            name=getname.getText().toString();
            password=getpassword.getText().toString();
            Userbean user = new Userbean();
            user.setUsername(name);
            user.setPassword(password);

            user.signUp(new SaveListener<Userbean>() {
                @Override
                public void done(Userbean bmobUser, BmobException e) {
                    if(e==null)
                    {
                        Toast.makeText(user_zhuce.this, "注册成功", Toast.LENGTH_LONG).show();
                        saveData();
                        gotoMainActivity();
                    }
                    else
                    {
                        Toast.makeText(user_zhuce.this, "请重新注册", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
        private void gotoMainActivity(){
            Intent i2=new Intent(user_zhuce.this,Activity_main.class);
            startActivity(i2);
            finish();
        }
        class mClick implements View.OnClickListener {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.zhuce://如果单击是注册
                       register();
                        break;


                }
            }
        }
    }

