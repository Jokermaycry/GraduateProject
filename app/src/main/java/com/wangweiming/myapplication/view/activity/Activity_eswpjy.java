package com.wangweiming.myapplication.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.model.Goodsbean;
import com.wangweiming.myapplication.adapter.GoodsListAdapter;
import com.wangweiming.myapplication.view.RefreshableView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class Activity_eswpjy extends AppCompatActivity {
    ListView Goods_List;
    List<Goodsbean> goodsbeanList =new ArrayList();
    GoodsListAdapter goodsListAdapter;
    RefreshableView refreshableView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eswpjy);
        Bmob.initialize(getApplication(), "705250f852f808598eda658ad36535c3");
        Goods_List=(ListView)findViewById(R.id.listView);
        refreshableView=(RefreshableView)findViewById(R.id.refreshable_view);
        goodsbeanList =new ArrayList();
        goodsListAdapter=new GoodsListAdapter(getApplication(), goodsbeanList);
        Goods_List.setAdapter(goodsListAdapter);
        try {


            BmobQuery<Goodsbean> local = new BmobQuery<Goodsbean>();

            local.findObjects(new FindListener<Goodsbean>() {
                @Override
                public void done(List<Goodsbean> object, BmobException e) {
                    if(e==null)
                    {
                        goodsbeanList = object;
                        goodsListAdapter.refresh((ArrayList<Goodsbean>) goodsbeanList);
                        goodsListAdapter.notifyDataSetChanged();
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


                    BmobQuery<Goodsbean> local = new BmobQuery<Goodsbean>();

                    local.findObjects(new FindListener<Goodsbean>() {
                        @Override
                        public void done(List<Goodsbean> object, BmobException e) {
                            if(e==null)
                            {
                                goodsbeanList = object;
                                goodsListAdapter.refresh((ArrayList<Goodsbean>) goodsbeanList);
                                goodsListAdapter.notifyDataSetChanged();
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
