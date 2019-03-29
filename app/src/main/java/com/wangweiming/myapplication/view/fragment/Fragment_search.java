package com.wangweiming.myapplication.view.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.model.Goodsbean;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class Fragment_search extends Fragment {


    public Fragment_search() {
        // Required empty public constructor
    }

    private void uesless() {
        //        final ImageButton searchBtn=(ImageButton) v.findViewById(R.id.searchBtn);
//        final EditText searchEdit=(EditText)v.findViewById(R.id.searchEdit);//输入框\
//        final TextView tv=(TextView)v.findViewById(R.id.tv);
//
//        searchBtn.setOnClickListener(new View.OnClickListener() {
//
//
//         @Override
//         public void onClick(View v) {
//             String getdate = searchEdit.getText().toString();
//
//                  final BmobQuery<Goodsbean> query =new BmobQuery<Goodsbean>();
//        query.addWhereEqualTo("name",getdate);
//        query.setLimit(50);
//
//     query.findObjects(new FindListener<Goodsbean>() {
//         @Override
//         public void done(List<Goodsbean> object, BmobException e) {
//             if(e==null)
//             {
//                 tv.setText("查询成功"+object.size()+"条数据");
//                 for(Goodsbean ggd:object)
//                 {
//                     tv.setText(ggd.getObjectId()+"\n"+ggd.getPhone()+"\n"+ggd.getDetail()+"\n"+ggd.getName()+"\n"+ggd.getPrice());
//
//                 }
//             }
//             else
//             {
//                 Log.e("error",e.toString());
//                 tv.setText("查询失败");
//             }
//         }
//     });
//
//
//                                         }
//                                     });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v=inflater.inflate(R.layout.activity_search,container,false);


        // Inflate the layout for this fragment
        return v;
    }
    }










