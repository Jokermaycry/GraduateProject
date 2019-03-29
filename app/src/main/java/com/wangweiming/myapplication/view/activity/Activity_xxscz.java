package com.wangweiming.myapplication.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.model.Bookbean;
import com.wangweiming.myapplication.adapter.BooksListAdapter;
import com.wangweiming.myapplication.view.RefreshableView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class Activity_xxscz extends AppCompatActivity {
    ListView Books_List;
    List<Bookbean> bookbeanList =new ArrayList();
    BooksListAdapter booksListAdapter;
    RefreshableView refreshableView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xxscz);
        Bmob.initialize(this,"705250f852f808598eda658ad36535c3");
        Books_List=(ListView)findViewById(R.id.listView);
        refreshableView=(RefreshableView)findViewById(R.id.refreshable_view);
        bookbeanList =new ArrayList();
        Books_List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        booksListAdapter=new BooksListAdapter(getApplicationContext(), bookbeanList);
        Books_List.setAdapter(booksListAdapter);
        try {


            BmobQuery<Bookbean> local = new BmobQuery<Bookbean>();

            local.findObjects(new FindListener<Bookbean>() {
                @Override
                public void done(List<Bookbean> object, BmobException e) {
                    if(e==null)
                    {
                        bookbeanList = object;
                        booksListAdapter.refresh((ArrayList<Bookbean>) bookbeanList);
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
        refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try {


                    BmobQuery<Bookbean> local = new BmobQuery<Bookbean>();

                    local.findObjects(new FindListener<Bookbean>() {
                        @Override
                        public void done(List<Bookbean> object, BmobException e) {
                            if(e==null)
                            {
                                bookbeanList = object;
                               booksListAdapter.refresh((ArrayList<Bookbean>) bookbeanList);
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
                refreshableView.finishRefreshing();
            }
        });
    }
}
