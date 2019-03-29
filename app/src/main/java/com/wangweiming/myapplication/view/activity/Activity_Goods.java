package com.wangweiming.myapplication.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.wangweiming.myapplication.R;

public class Activity_Goods extends AppCompatActivity {
TextView tv1,tv2,tv3;
String s1,s2,s3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodspage);
        tv1=(TextView)findViewById(R.id.tv1);
        tv2=(TextView)findViewById(R.id.tv2);
        tv3=(TextView)findViewById(R.id.tv3);
        s1=getIntent().getStringExtra("tvGoodsName");
        s2=getIntent().getStringExtra("tvGoodsDetail");
        s3=getIntent().getStringExtra("tvGoodsPrice");
        tv1.setText(s1);
        tv2.setText(s2);
        tv3.setText(s3);

    }

}
