package com.wangweiming.myapplication.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.model.Computerbean;
import com.wangweiming.myapplication.adapter.ComputersListAdapter;
import com.wangweiming.myapplication.view.RefreshableView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class Activity_dnwx extends AppCompatActivity {
    ListView Computers_List;
    List<Computerbean> computerbeanList =new ArrayList();
    com.wangweiming.myapplication.adapter.ComputersListAdapter ComputersListAdapter;
    RefreshableView refreshableView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dnwx);
        Bmob.initialize(this,"705250f852f808598eda658ad36535c3");
        Computers_List=(ListView)findViewById(R.id.listView);
        refreshableView=(RefreshableView)findViewById(R.id.refreshable_view);
        computerbeanList =new ArrayList();
        Computers_List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        ComputersListAdapter=new ComputersListAdapter(getApplicationContext(), computerbeanList);
        Computers_List.setAdapter(ComputersListAdapter);
        try {

            BmobQuery<Computerbean> local = new BmobQuery<Computerbean>();

            local.findObjects(new FindListener<Computerbean>() {
                @Override
                public void done(List<Computerbean> object, BmobException e) {
                    if(e==null)
                    {
                        computerbeanList = object;
                        ComputersListAdapter.refresh((ArrayList<Computerbean>) computerbeanList);
                        ComputersListAdapter.notifyDataSetChanged();
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

                    BmobQuery<Computerbean> local = new BmobQuery<Computerbean>();

                    local.findObjects(new FindListener<Computerbean>() {
                        @Override
                        public void done(List<Computerbean> object, BmobException e) {
                            if(e==null)
                            {
                                computerbeanList = object;
                                ComputersListAdapter.refresh((ArrayList<Computerbean>) computerbeanList);
                                ComputersListAdapter.notifyDataSetChanged();
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