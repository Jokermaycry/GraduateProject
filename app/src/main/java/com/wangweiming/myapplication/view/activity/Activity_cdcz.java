package com.wangweiming.myapplication.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.model.Fieldbean;
import com.wangweiming.myapplication.adapter.FieldsListAdapter;
import com.wangweiming.myapplication.view.RefreshableView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class Activity_cdcz extends AppCompatActivity {
    ListView Fields_List;
    List<Fieldbean> fieldbeanList =new ArrayList();
    com.wangweiming.myapplication.adapter.FieldsListAdapter FieldsListAdapter;
    RefreshableView refreshableView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cdcz);
        Bmob.initialize(this,"705250f852f808598eda658ad36535c3");
        Fields_List=(ListView)findViewById(R.id.listView);
        refreshableView=(RefreshableView)findViewById(R.id.refreshable_view);
        fieldbeanList =new ArrayList();
        Fields_List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        FieldsListAdapter=new FieldsListAdapter(getApplicationContext(), fieldbeanList);
        Fields_List.setAdapter(FieldsListAdapter);
        try {


            BmobQuery<Fieldbean> local = new BmobQuery<Fieldbean>();

            local.findObjects(new FindListener<Fieldbean>() {
                @Override
                public void done(List<Fieldbean> object, BmobException e) {
                    if(e==null)
                    {
                        fieldbeanList = object;
                        FieldsListAdapter.refresh((ArrayList<Fieldbean>) fieldbeanList);
                        FieldsListAdapter.notifyDataSetChanged();
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


                    BmobQuery<Fieldbean> local = new BmobQuery<Fieldbean>();

                    local.findObjects(new FindListener<Fieldbean>() {
                        @Override
                        public void done(List<Fieldbean> object, BmobException e) {
                            if(e==null)
                            {
                                fieldbeanList = object;
                                FieldsListAdapter.refresh((ArrayList<Fieldbean>) fieldbeanList);
                                FieldsListAdapter.notifyDataSetChanged();
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