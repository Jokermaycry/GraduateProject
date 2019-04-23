package com.wangweiming.myapplication.view.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class user_denglu extends AppCompatActivity {
    Button zhuce, denglu,forget;
    EditText getname, getpassword;
    String name,password;
    Toolbar tb;
    Userbean user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);
        init();
        initClickListener();
        initToolBar();

    }
    private void initToolBar() {
        tb=(Toolbar)findViewById(R.id.toolbar);
        tb.setTitle("返回首页");
        tb.setNavigationIcon(R.drawable.bar_goback);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(user_denglu.this, Activity_main.class));
                finish();
            }
        });
    }
    private void init() {
        forget=(Button)findViewById(R.id.forget);
        zhuce = (Button) findViewById(R.id.zhuce);
        denglu = (Button) findViewById(R.id.denglu);
        getname = (EditText) findViewById(R.id.getname);
        getpassword = (EditText) findViewById(R.id.getpassword);
        String checkusername=(String)SharedPreferencesUtil.getData("username","");
        String checkpassword=(String)SharedPreferencesUtil.getData("password","");
        if(checkusername!=null&&checkpassword!=null)
        {
            getname.setText(checkusername);
            getpassword.setText(checkpassword);
        }
    }
    private void initClickListener(){
        zhuce.setOnClickListener(new mClick());
        denglu.setOnClickListener(new mClick());
        forget.setOnClickListener(new mClick());
    }
    private void register() {
        Intent i=new Intent(user_denglu.this,user_zhuce.class);
        startActivity(i);
    }
    private void saveData() {
        SharedPreferencesUtil.putData("username",name);
        SharedPreferencesUtil.putData("password",password);

    }
    private void gotoMainActivity() {
        Intent i2=new Intent(user_denglu.this,Activity_main.class);
        startActivity(i2);
        finish();
    }
    private void forgetPassword() {
        Intent i1=new Intent(user_denglu.this,user_forgetpwd.class);
        startActivity(i1);
    }
    private void login() {
        name=getname.getText().toString();
        password=getpassword.getText().toString();

        user=new Userbean();
        user.setUsername(name);
        user.setPassword(password);
        user.login(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if(e==null)
                {
                    Toast.makeText(user_denglu.this, "登录成功", Toast.LENGTH_LONG).show();
                    saveData();
                    gotoMainActivity();
                }
                else
                {
                    Toast.makeText(user_denglu.this, "请重新登录", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    class mClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.denglu://如果单击是登录
                    login();
                    break;
                case R.id.zhuce://如果单击是注册
                    register();
                    break;
                case R.id.forget:
                    forgetPassword();
                   

            }
        }
    }
}
