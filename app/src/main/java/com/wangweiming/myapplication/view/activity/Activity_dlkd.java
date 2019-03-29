package com.wangweiming.myapplication.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.model.Fastbean;
import com.wangweiming.myapplication.adapter.FastsListAdapter;
import com.wangweiming.myapplication.view.RefreshableView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 代领快递
 */
public class Activity_dlkd extends AppCompatActivity {
    ListView Fasts_List;
    List<Fastbean> fastbeanList =new ArrayList();
    com.wangweiming.myapplication.adapter.FastsListAdapter FastsListAdapter;
    RefreshableView refreshableView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dlkd);
        Bmob.initialize(this,"705250f852f808598eda658ad36535c3");
        Fasts_List=(ListView)findViewById(R.id.listView);
        refreshableView=(RefreshableView)findViewById(R.id.refreshable_view);
        fastbeanList =new ArrayList();
        Fasts_List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        FastsListAdapter=new FastsListAdapter(getApplicationContext(), fastbeanList);
        Fasts_List.setAdapter(FastsListAdapter);
        try {


            BmobQuery<Fastbean> local = new BmobQuery<Fastbean>();

            local.findObjects(new FindListener<Fastbean>() {
                @Override
                public void done(List<Fastbean> object, BmobException e) {
                    if(e==null)
                    {
                        fastbeanList = object;
                        FastsListAdapter.refresh((ArrayList<Fastbean>) fastbeanList);
                        FastsListAdapter.notifyDataSetChanged();
                        Toast.makeText(getApplication(), "查询成功", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplication(), "查询失败", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try {


                    BmobQuery<Fastbean> local = new BmobQuery<Fastbean>();

                    local.findObjects(new FindListener<Fastbean>() {
                        @Override
                        public void done(List<Fastbean> object, BmobException e) {
                            if(e==null)
                            {
                                fastbeanList = object;
                                FastsListAdapter.refresh((ArrayList<Fastbean>) fastbeanList);
                                FastsListAdapter.notifyDataSetChanged();
                                Toast.makeText(getApplication(), "查询成功", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(getApplication(), "查询失败", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                refreshableView.finishRefreshing();
            }
        });
    }
}