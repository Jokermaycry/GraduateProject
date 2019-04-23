package com.wangweiming.myapplication.view.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;

import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wangweiming.myapplication.R;

import com.wangweiming.myapplication.model.Advertisement;
import com.wangweiming.myapplication.view.activity.Main_book;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class Fragment_main extends Fragment  implements OnBannerListener{
    private Banner banner;
    private List<String> list_path;
    private List<String> list_title;

    private List<String> Bmob_list_path;
    private List<String> Bmob_list_title;
    private List<String>  Bmob_list_position;
    private static  final String IMGURL="http://bmob-cdn-16487.b0.upaiyun.com/2019/04/12/d9ae4ea8404d51ab80c761032f094d9e.jpg";
    TextView bookbtn;
    ViewPager mViewPager;
    SearchView searchView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v=inflater.inflate(R.layout.activity_first,container,false);


        initData(v);



        return v;
    }
    private void initData(final View v)
    {
        list_path = new ArrayList<>();
        list_title = new ArrayList<>();




        Bmob_list_path = new ArrayList<>();
        Bmob_list_title = new ArrayList<>();
        Bmob_list_position=new ArrayList<>();

        BmobQuery<Advertisement> query = new BmobQuery<Advertisement>();


        query.findObjects(new FindListener<Advertisement>() {

            @Override
            public void done(List<Advertisement> object, BmobException e) {
                if(e==null){

                    Log.d("bmob","查询成功：共" + object.size() + "条数据。");


                    for(Advertisement a  : object) {
                        Log.d("bmob","查询成功：" +a.getAd_image_url());


                        Bmob_list_path.add(a.getAd_image_url());
                        Bmob_list_title.add(a.getAd_title());

                    }
                    Log.d("bmob","查询成功：" +Bmob_list_path.size());
                    list_path=Bmob_list_path;
                    list_title=Bmob_list_title;
                    initView(v);
                    setOnClickListener();
                    setsetOnQueryTextListener();

                }else{
                    Log.i("bmob","失败："+e.getMessage());
                }
            }

        });




    }


    private void initView(View v) {
        bookbtn=v.findViewById(R.id.bookbtn);
        banner = v.findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImageLoader(new MyLoader());
        banner.setBannerAnimation(Transformer.Default);
        banner.setBannerTitles(list_title);
        banner.setDelayTime(3000);
        banner.isAutoPlay(true);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImages(list_path)
                .setOnBannerListener(this)
                .start();
        searchView=v.findViewById(R.id.searchview);
        bookbtn.requestFocus();


    }

    private void setOnClickListener() {
        bookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),Main_book.class);
                startActivity(i);
            }
        });
    }

    private void setsetOnQueryTextListener() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getContext(),"click",Toast.LENGTH_SHORT).show();
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(getContext(), "你点了第" + (position + 1) + "张轮播图", Toast.LENGTH_SHORT).show();

    }
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);
        }
    }

}
