package com.wangweiming.myapplication.view.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.view.activity.Activity_addbook;
import com.wangweiming.myapplication.view.activity.Activity_addserver;
import com.wangweiming.myapplication.view.activity.Activity_additems;

    /**
     * 发布页面
     */
public class Fragment_add extends Fragment {
    public Fragment_add() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_add, container, false);

        final ImageView iv1=(ImageView)v.findViewById(R.id.Add_one);
        final ImageView iv2=(ImageView)v.findViewById(R.id.Add_two);
        final ImageView iv3=(ImageView)v.findViewById(R.id.Add_three);
       iv1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(getActivity(),Activity_addbook.class);
                startActivity(i);


            }


        });
        iv2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(getActivity(),Activity_additems.class);
                startActivity(i);


            }


        });
        iv3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(getActivity(),Activity_addserver.class);
                startActivity(i);


            }


        });
        return v;
    }
}
