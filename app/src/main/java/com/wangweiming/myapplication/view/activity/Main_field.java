package com.wangweiming.myapplication.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.adapter.QuickAdapter;
import com.wangweiming.myapplication.adapter.QuickGridRvDividerDecoration;
import com.wangweiming.myapplication.model.Fieldbean;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class Main_field extends AppCompatActivity {
    List<Fieldbean> fieldList ;
    QuickAdapter quickAdapter;
    BmobQuery<Fieldbean> local ;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_field);
        init();
        initdata();
        initAdapter();
        initClickListener();
        initRecyecleView();
    }
    private void initClickListener() {
        quickAdapter.setOnItemClickListener(new QuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }
    private void initAdapter() {
        quickAdapter=new QuickAdapter<Fieldbean>(fieldList) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_1;
            }

            @Override
            public int getItemViewType(int position) {

                return super.getItemViewType(position);
            }


            @Override
            public void convert(VH holder, Fieldbean fieldbean, int position) {
                holder.setText(R.id.title,fieldbean.getName());
                holder.setImage(R.id.img,fieldbean.getPrice());
            }
        };
    }
    private void initRecyecleView() {
        rv.setLayoutManager(new GridLayoutManager(this,1, OrientationHelper.VERTICAL,false));
        rv.setAdapter(quickAdapter);
        rv.addItemDecoration(new QuickGridRvDividerDecoration(this));
    }
    private void init() {
        fieldList =new ArrayList();
        local = new BmobQuery<Fieldbean>();
        rv = (RecyclerView) findViewById(R.id.rv);
    }
    private void initdata() {
        try {
            local.findObjects(new FindListener<Fieldbean>() {
                @Override
                public void done(List<Fieldbean> object, BmobException e) {
                    if(e==null)
                    {
                        fieldList = object;
                        quickAdapter.refresh(fieldList);
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