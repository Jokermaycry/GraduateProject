package com.wangweiming.myapplication.view.fragment;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.view.activity.Activity_Goods;
import com.wangweiming.myapplication.view.RefreshableView;
import com.wangweiming.myapplication.adapter.GoodsListAdapter;
import com.wangweiming.myapplication.model.Goodsbean;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 查询数据
 */

public class Fragment_item extends Fragment {
    ListView Goods_List;
    List<Goodsbean> goodsbeanList =new ArrayList();
    GoodsListAdapter goodsListAdapter;
    RefreshableView refreshableView;
    public Fragment_item() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
         View v=inflater.inflate(R.layout.activity_classify,container,false);
         Bmob.initialize(getActivity(), "705250f852f808598eda658ad36535c3");
         Goods_List=(ListView)v.findViewById(R.id.listView);
         goodsbeanList =new ArrayList();
         Goods_List.setOnItemClickListener(new AdapterView.OnItemClickListener()
         {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent i2=new Intent(getActivity(),Activity_Goods.class);
        i2.putExtra("tvGoodsName", goodsbeanList.get(i).getName());
        i2.putExtra("tvGoodsDetail", goodsbeanList.get(i).getDetail());
        i2.putExtra("tvGoodsPrice", goodsbeanList.get(i).getPrice());
        startActivity(i2);
    }
});
        goodsListAdapter=new GoodsListAdapter(getContext(), goodsbeanList);
        Goods_List.setAdapter(goodsListAdapter);

        refreshableView=(RefreshableView)v.findViewById(R.id.refreshable_view);

        refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                find();
                refreshableView.finishRefreshing();
            }
        });



        return v;
    }

    /**
     * 从bmob后台查找items
     */
    private  void find()
    {
        try {
            /*Thread.sleep(3000);*/
            BmobQuery<Goodsbean> local = new BmobQuery<Goodsbean>();
            local.findObjects(new FindListener<Goodsbean>() {
                @Override
                public void done(List<Goodsbean> object, BmobException e) {
                    if(e==null)
                    {
                        goodsbeanList = object;
                        goodsListAdapter.refresh((ArrayList<Goodsbean>) goodsbeanList);
                        goodsListAdapter.notifyDataSetChanged();
                    }
                    else
                    {
                        Log.e("error",e.toString());
                        Toast.makeText(getActivity(), "查询失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }





    }









