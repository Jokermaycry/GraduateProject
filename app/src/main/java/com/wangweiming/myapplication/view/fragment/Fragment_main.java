package com.wangweiming.myapplication.view.fragment;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.view.activity.Activity_bcfw;
import com.wangweiming.myapplication.view.activity.Activity_cdcz;
import com.wangweiming.myapplication.view.activity.Activity_ddccz;
import com.wangweiming.myapplication.view.activity.Activity_dlkd;
import com.wangweiming.myapplication.view.activity.Activity_dnwx;
import com.wangweiming.myapplication.view.activity.Activity_eswpjy;
import com.wangweiming.myapplication.view.activity.Activity_xxscz;
import com.wangweiming.myapplication.view.activity.Activity_ztcy;
import com.wangweiming.myapplication.view.webview.WebView_xlcx;
import com.wangweiming.myapplication.view.webview.WebView_xyfw;
import com.wangweiming.myapplication.view.webview.Webview_dfcz;


public class Fragment_main extends Fragment  {
    public Fragment_main(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v=inflater.inflate(R.layout.activity_first,container,false);


        final ImageButton xiaoyuanfuwuBtn=(ImageButton) v.findViewById(R.id.homeBtn);
        final ImageButton toushuBtn=(ImageButton) v.findViewById(R.id.toushuBtn);
        final ImageButton findlostBtn=(ImageButton) v.findViewById(R.id.findlostBtn);
        final ImageButton quanyiBtn=(ImageButton) v.findViewById(R.id.quanyizhongxinBtn);
        final ImageButton shiwuBtn=(ImageButton) v.findViewById(R.id.shiwudatingBtn);
        final ImageButton tiaozaoBtn=(ImageButton) v.findViewById(R.id.tiaozaoshichangBtn);
        final ImageButton jiangzuoBtn=(ImageButton) v.findViewById(R.id.jiangzuoBtn);
        final ImageButton cetBtn=(ImageButton) v.findViewById(R.id.CETBtn);
        final ImageButton jianzhiBtn=(ImageButton) v.findViewById(R.id.jianzhiBtn);
        final ImageButton xiaoliBtn=(ImageButton) v.findViewById(R.id.xiaoliBtn);
        final ImageButton dianfeiBtn=(ImageButton) v.findViewById(R.id.dianfeiBtn);
        final ImageButton biaobaiqiangBtn=(ImageButton) v.findViewById(R.id.biaobaiqiangBtn);





        xiaoyuanfuwuBtn .setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
    Intent I=new Intent(getActivity(),WebView_xyfw.class);
    startActivity(I);

        }

    });
        toushuBtn .setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent I=new Intent(getActivity(),Activity_ddccz.class);
            startActivity(I);

        }

    });
        findlostBtn.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent I=new Intent(getActivity(),Activity_dlkd.class);
            startActivity(I);

        }

    });
        quanyiBtn .setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent I=new Intent(getActivity(),Activity_dnwx.class);
            startActivity(I);

        }

    });
        shiwuBtn.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent I=new Intent(getActivity(),Activity_xxscz.class);
            startActivity(I);

        }

    });
        tiaozaoBtn.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent I=new Intent(getActivity(),Activity_cdcz.class);
            startActivity(I);

        }

    });
        jiangzuoBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent I=new Intent(getActivity(),Activity_ztcy.class);
                startActivity(I);

            }

        });
        cetBtn.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent I=new Intent(getActivity(),Activity_bcfw.class);
            startActivity(I);

        }

    });
        jianzhiBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent I=new Intent(getActivity(),Activity_eswpjy.class);
                startActivity(I);

            }

        });
        xiaoliBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent I=new Intent(getActivity(),WebView_xlcx.class);
                startActivity(I);

            }

        });
        dianfeiBtn.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent I=new Intent(getActivity(),Webview_dfcz.class);
            startActivity(I);

        }

    });
        biaobaiqiangBtn.setVisibility(View.GONE);
        biaobaiqiangBtn.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {


        }

    });

        return v;
    }





}
