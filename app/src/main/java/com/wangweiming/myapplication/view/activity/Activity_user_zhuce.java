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

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

    public class Activity_user_zhuce extends AppCompatActivity {
        Toolbar tb;
        Button zhuce;
        EditText getname, getpassword;
        String name,password;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_zhuce);
            zhuce = (Button) findViewById(R.id.zhuce);
            getname = (EditText) findViewById(R.id.getname);
            getpassword = (EditText) findViewById(R.id.getpassword);
            zhuce.setOnClickListener(new mClick());
            tb=(Toolbar)findViewById(R.id.toolbar);
            tb.setTitle("返回登录");
            tb.setNavigationIcon(R.drawable.bar_goback);
            tb.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(Activity_user_zhuce.this, Activity_user_denglu.class));
                    finish();
                }
            });
            Bmob.initialize(this, "705250f852f808598eda658ad36535c3");
        }
        class mClick implements View.OnClickListener {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.zhuce://如果单击是注册
                        name=getname.getText().toString();
                        password=getpassword.getText().toString();
                        BmobUser p2 = new BmobUser();
                        p2.setUsername(name);
                        p2.setPassword(password);
                     /*   p2.signUp(Activity_user_zhuce.this, new SaveListener() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(Activity_user_zhuce.this, "注册成功", Toast.LENGTH_LONG).show();
                                //通过BmobUser user = BmobUser.getCurrentUser(BmobUser.class)()获取登录成功后的本地用户信息
                                Intent i2=new Intent(Activity_user_zhuce.this,Activity_user_denglu.class);
                                startActivity(i2);
                                finish();
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                Toast.makeText(Activity_user_zhuce.this, s+"请重新注册", Toast.LENGTH_LONG).show();
                            }
                        });*/
                     p2.signUp(new SaveListener<BmobUser>() {
                         @Override
                         public void done(BmobUser bmobUser, BmobException e) {
                             if(e==null)
                             {
                                 Toast.makeText(Activity_user_zhuce.this, "注册成功", Toast.LENGTH_LONG).show();
                                 //通过BmobUser user = BmobUser.getCurrentUser(BmobUser.class)()获取登录成功后的本地用户信息
                                 Intent i2=new Intent(Activity_user_zhuce.this,Activity_user_denglu.class);
                                 startActivity(i2);
                                 finish();
                             }
                             else
                             {
                                 Toast.makeText(Activity_user_zhuce.this, "请重新注册", Toast.LENGTH_LONG).show();
                             }
                         }
                     });

                        break;


                }
            }
        }
    }
/*
创建表
  Person p2=new Person();
  p2.setName("lucy");
   p2.setAddress("beijing");
  p2.save(Activity_main.this,new SaveListener() {
       @Override
      public void onSuccess() {
           Toast.makeText(Activity_main.this, "注册成功", Toast.LENGTH_SHORT).show();
        }

       @Override
       public void onFailure(int i, String s) {
           Toast.makeText(Activity_main.this, "注册失败", Toast.LENGTH_SHORT).show();
       }
   });
*/


       /*case R.id.denglu://如果单击是登录
                        name=getname.getText().toString();
                        password=getpassword.getText().toString();
                        email=getemail.getText().toString();
                        BmobUser bu2=new BmobUser();
                        bu2.setUsername(name);
                        bu2.setPassword(password);
                        bu2.login(Activity_main.this,new SaveListener() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(Activity_main.this, "登录成功", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                Toast.makeText(Activity_main.this, s+"请重新登录", Toast.LENGTH_LONG).show();
                            }
                        });
                        break;*/