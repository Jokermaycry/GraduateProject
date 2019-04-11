package com.wangweiming.myapplication.view.fragment;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.view.activity.Main_car;
import com.wangweiming.myapplication.view.activity.Main_field;
import com.wangweiming.myapplication.view.activity.Main_kuaidi;
import com.wangweiming.myapplication.view.activity.Main_fixcomputer;
import com.wangweiming.myapplication.view.activity.Main_secondHand;
import com.wangweiming.myapplication.view.activity.Main_book;
import com.wangweiming.myapplication.view.activity.Main_travel;


public class Fragment_main extends Fragment  {
    ImageButton bookbtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v=inflater.inflate(R.layout.activity_first,container,false);
        bookbtn=(ImageButton) v.findViewById(R.id.bookbtn);
        bookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),Main_book.class);
                startActivity(i);
            }
        });

        return v;
    }





}
