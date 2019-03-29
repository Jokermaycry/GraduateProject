package com.wangweiming.myapplication.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.view.RefreshableView;
import com.wangweiming.myapplication.model.Carsbean;
import com.wangweiming.myapplication.adapter.CarsListAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class Activity_bcfw extends AppCompatActivity {
    ListView Cars_List;
    List<Carsbean> carsbeanList =new ArrayList();
    CarsListAdapter CarsListAdapter;
    RefreshableView refreshableView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bcfw);
        Bmob.initialize(this,"705250f852f808598eda658ad36535c3");
        Cars_List=(ListView)findViewById(R.id.listView);
        refreshableView=(RefreshableView)findViewById(R.id.refreshable_view);
        carsbeanList =new ArrayList();
        Cars_List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        CarsListAdapter=new CarsListAdapter(getApplicationContext(), carsbeanList);
        Cars_List.setAdapter(CarsListAdapter);
        try {


            BmobQuery<Carsbean> local = new BmobQuery<Carsbean>();

            local.findObjects(new FindListener<Carsbean>() {
                @Override
                public void done(List<Carsbean> object, BmobException e) {
                    if(e==null)
                    {
                        carsbeanList = object;
                        CarsListAdapter.refresh((ArrayList<Carsbean>) carsbeanList);
                        CarsListAdapter.notifyDataSetChanged();
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


                    BmobQuery<Carsbean> local = new BmobQuery<Carsbean>();

                    local.findObjects(new FindListener<Carsbean>() {
                        @Override
                        public void done(List<Carsbean> object, BmobException e) {
                            if(e==null)
                            {
                                carsbeanList = object;
                                CarsListAdapter.refresh((ArrayList<Carsbean>) carsbeanList);
                                CarsListAdapter.notifyDataSetChanged();
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