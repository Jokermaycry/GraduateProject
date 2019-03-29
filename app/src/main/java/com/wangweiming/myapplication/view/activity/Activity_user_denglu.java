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

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Activity_user_denglu extends AppCompatActivity {
    Button zhuce, denglu,forget;
    EditText getname, getpassword;
    String name,password;
Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        forget=(Button)findViewById(R.id.forget);
        zhuce = (Button) findViewById(R.id.zhuce);
        denglu = (Button) findViewById(R.id.denglu);
        getname = (EditText) findViewById(R.id.getname);
        getpassword = (EditText) findViewById(R.id.getpassword);
        zhuce.setOnClickListener(new mClick());
        denglu.setOnClickListener(new mClick());
        forget.setOnClickListener(new mClick());
        tb=(Toolbar)findViewById(R.id.toolbar);
        tb.setTitle("返回首页");
        tb.setNavigationIcon(R.drawable.bar_goback);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Activity_user_denglu.this, Activity_main.class));
                finish();
            }
        });
        //Bmob 初始化
        Bmob.initialize(this, "705250f852f808598eda658ad36535c3");


        /*BmobUser user = BmobUser.getCurrentUser(BmobUser.class)(this,BmobUser.class);//获取登录成功后的本地用户信息
        if(user != null){
            Intent i2=new Intent(Activity_user_denglu.this,Activity_main.class);
            Bundle b=new Bundle();
            String ii=user.getObjectId();
            String uname=user.getUsername();
            String word=user.getEmail();
            String createdate=user.getCreatedAt();
            String updateAt=user.getUpdatedAt();
            b.putString("id",ii);
            b.putString("name",uname);
            b.putString("email",word);
            b.putString("create",createdate);
            b.putString("update",updateAt);
            i2.putExtras(b);
            startActivity(i2);


            // 允许用户使用应用
        }else{
            //缓存用户对象为空时， 可打开用户注册界面…
        }*/
    }
    class mClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.denglu://如果单击是登录
                    name=getname.getText().toString();
                    password=getpassword.getText().toString();

                    BmobUser bu2=new BmobUser();
                    bu2.setUsername(name);
                    bu2.setPassword(password);
                   /* bu2.login(Activity_user_denglu.this,new SaveListener() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(Activity_user_denglu.this, "登录成功", Toast.LENGTH_LONG).show();
                            Intent i2=new Intent(Activity_user_denglu.this,Activity_main.class);
                            startActivity(i2);
                            finish();
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            Toast.makeText(Activity_user_denglu.this, s+"请重新登录", Toast.LENGTH_LONG).show();
                        }
                    });*/
                   bu2.login(new SaveListener<BmobUser>() {
                       @Override
                       public void done(BmobUser bmobUser, BmobException e) {
                           if(e==null)
                           {
                               Toast.makeText(Activity_user_denglu.this, "登录成功", Toast.LENGTH_LONG).show();
                               Intent i2=new Intent(Activity_user_denglu.this,Activity_main.class);
                               startActivity(i2);
                               finish();
                           }
                           else
                           {
                               Toast.makeText(Activity_user_denglu.this, "请重新登录", Toast.LENGTH_LONG).show();
                           }
                       }
                   });
                    break;
                case R.id.zhuce://如果单击是注册
                    Intent i=new Intent(Activity_user_denglu.this,Activity_user_zhuce.class);
                    startActivity(i);
                    break;
                case R.id.forget:
                    Intent i1=new Intent(Activity_user_denglu.this,Activity_user_forgetpwd.class);
                    startActivity(i1);

            }
        }
    }
}
//创建表
//  Person p2=new Person();
//  p2.setName("lucy");
//   p2.setAddress("beijing");
//  p2.save(Activity_main.this,new SaveListener() {
//       @Override
//      public void onSuccess() {
//           Toast.makeText(Activity_main.this, "注册成功", Toast.LENGTH_SHORT).show();
//        }
//
//       @Override
//       public void onFailure(int i, String s) {
//           Toast.makeText(Activity_main.this, "注册失败", Toast.LENGTH_SHORT).show();
//       }
//   });

//