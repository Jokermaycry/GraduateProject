package com.wangweiming.myapplication.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.adapter.QuickAdapter;
import com.wangweiming.myapplication.adapter.QuickGridRvDividerDecoration;
import com.wangweiming.myapplication.model.Bookbean;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SQLQueryListener;


public class Main_book extends AppCompatActivity {
    List<Bookbean> bookList ;
    QuickAdapter quickAdapter;
    BmobQuery<Bookbean> local ;
    RecyclerView rv;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_book);
        init();
        initdata();
        initAdapter();
        initClickListener();
        initRecyecleView();
        initSearchView();
    }
    private void initClickListener() {
        quickAdapter.setOnItemClickListener(new QuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(),"position:"+position,Toast.LENGTH_SHORT).show();

                Intent i=new Intent(getApplicationContext(),ProductDetailActivity.class);
                i.putExtra("productId",bookList.get(position).getObjectId());
                Log.e("productId",bookList.get(position).getObjectId());
                startActivity(i);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }
    private void searchBook(String target) {
        String bql ="select * from Bookbean where name = '"+target+"'";
        Log.i("smile", "target："+target);
        BmobQuery<Bookbean> query=new BmobQuery<Bookbean>();
        //设置查询的SQL语句
        query.setSQL(bql);
        query.doSQLQuery(new SQLQueryListener<Bookbean>(){

            @Override
            public void done(BmobQueryResult<Bookbean> result, BmobException e) {
                if(e ==null){

                    bookList=result.getResults();
                    quickAdapter.refresh(bookList);
                    Toast.makeText(getApplication(), "查询成功", Toast.LENGTH_SHORT).show();
                }else{
                    Log.i("smile", "错误码："+e.getErrorCode()+"，错误描述："+e.getMessage());
                }
            }
        });


    }
    private void initAdapter() {
        quickAdapter=new QuickAdapter<Bookbean>(bookList,this) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_product;
            }

            @Override
            public int getItemViewType(int position) {

                return super.getItemViewType(position);
            }


            @Override
            public void convert(VH holder, Bookbean bookbean, int position) {
                if(bookbean.getImageurl()!=null) {

                    holder.setImage(R.id.iv_product_image, bookbean.getImageurl());
                    Log.e("url",bookbean.getImageurl());
                }
                else
                {
                    holder.setImage(R.id.iv_product_image);
                }
                holder.setText(R.id.tv_product_title,bookbean.getName());
                holder.setText(R.id.tv_product_sub_title,bookbean.getDetail());
                holder.setText(R.id.tv_price,bookbean.getPrice());
            }
        };
    }
    private void setsetOnQueryTextListener() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchBook(query);
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }
    private void initSearchView() {
        setsetOnQueryTextListener();

    }
    private void initRecyecleView() {
        rv.setLayoutManager(new GridLayoutManager(this,2, OrientationHelper.VERTICAL,false));
        rv.setAdapter(quickAdapter);

        //rv.addItemDecoration(new QuickGridRvDividerDecoration(this));
    }
    private void init() {
        bookList =new ArrayList();
        local = new BmobQuery<Bookbean>();
        rv = (RecyclerView) findViewById(R.id.rv);
        searchView=(SearchView)findViewById(R.id.searchview);
    }
    private void initdata() {
        try {
            local.findObjects(new FindListener<Bookbean>() {
                @Override
                public void done(List<Bookbean> object, BmobException e) {
                    if(e==null)
                    {
                        bookList = object;
                        quickAdapter.refresh(bookList);
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