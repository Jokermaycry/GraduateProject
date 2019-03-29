package com.wangweiming.myapplication.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.model.Motorsbean;
import com.wangweiming.myapplication.adapter.MotorsListAdapter;
import com.wangweiming.myapplication.view.RefreshableView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 电动车出租
 */
public class Activity_ddccz extends AppCompatActivity {
    ListView Books_List;
    List<Motorsbean> booksList=new ArrayList();
    MotorsListAdapter booksListAdapter;
    RefreshableView refreshableView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ddccz);
        Bmob.initialize(this,"705250f852f808598eda658ad36535c3");
        Books_List=(ListView)findViewById(R.id.listView);

        booksList=new ArrayList();
        Books_List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        booksListAdapter=new MotorsListAdapter(getApplicationContext(),booksList);
        Books_List.setAdapter(booksListAdapter);
        BmobQuery<Motorsbean> local = new BmobQuery<Motorsbean>();
        try {
            local.findObjects(new FindListener<Motorsbean>() {
                @Override
                public void done(List<Motorsbean> object, BmobException e) {
                    if(e==null)
                    {
                        booksList = object;
                        booksListAdapter.refresh((ArrayList<Motorsbean>) booksList);
                        booksListAdapter.notifyDataSetChanged();
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

        refreshableView=(RefreshableView)findViewById(R.id.refreshable_view);
        refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
              initdata();
                refreshableView.finishRefreshing();
            }
        });
    }
    public void initdata()
    {
        BmobQuery<Motorsbean> local = new BmobQuery<Motorsbean>();
        try {
        local.findObjects(new FindListener<Motorsbean>() {
            @Override
            public void done(List<Motorsbean> object, BmobException e) {
                if(e==null)
                {
                    booksList = object;
                    booksListAdapter.refresh((ArrayList<Motorsbean>) booksList);
                    booksListAdapter.notifyDataSetChanged();
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
    }
}