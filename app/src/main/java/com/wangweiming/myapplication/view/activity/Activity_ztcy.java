package com.wangweiming.myapplication.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.view.RefreshableView;
import com.wangweiming.myapplication.model.Travelsbean;
import com.wangweiming.myapplication.adapter.TravelsListAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class Activity_ztcy extends AppCompatActivity {
    ListView Travels_List;
    List<Travelsbean> travelsbeanList =new ArrayList();
    com.wangweiming.myapplication.adapter.TravelsListAdapter TravelsListAdapter;
    RefreshableView refreshableView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ztcy);
        Bmob.initialize(this,"705250f852f808598eda658ad36535c3");
        Travels_List=(ListView)findViewById(R.id.listView);
        refreshableView=(RefreshableView)findViewById(R.id.refreshable_view);
        travelsbeanList =new ArrayList();
        Travels_List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        TravelsListAdapter=new TravelsListAdapter(getApplicationContext(), travelsbeanList);
        Travels_List.setAdapter(TravelsListAdapter);
        try {


            BmobQuery<Travelsbean> local = new BmobQuery<Travelsbean>();

            local.findObjects(new FindListener<Travelsbean>() {
                @Override
                public void done(List<Travelsbean> object, BmobException e) {
                    if(e==null)
                    {
                        travelsbeanList = object;
                        TravelsListAdapter.refresh((ArrayList<Travelsbean>) travelsbeanList);
                        TravelsListAdapter.notifyDataSetChanged();
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


                    BmobQuery<Travelsbean> local = new BmobQuery<Travelsbean>();

                    local.findObjects(new FindListener<Travelsbean>() {
                        @Override
                        public void done(List<Travelsbean> object, BmobException e) {
                            if(e==null)
                            {
                                travelsbeanList = object;
                                TravelsListAdapter.refresh((ArrayList<Travelsbean>) travelsbeanList);
                                TravelsListAdapter.notifyDataSetChanged();
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