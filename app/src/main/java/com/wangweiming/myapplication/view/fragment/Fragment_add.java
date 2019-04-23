package com.wangweiming.myapplication.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.airsaid.pickerviewlibrary.OptionsPickerView;
import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.view.activity.Add_isell;
import com.wangweiming.myapplication.view.activity.Add_iwant;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 发布页面
 */
public class Fragment_add extends Fragment {

    @BindView(R.id.iwant)
    TextView iwant;
    @BindView(R.id.iadd)
    TextView iadd;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_add, container, false);


        unbinder = ButterKnife.bind(this, v);
        iwant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),Add_iwant.class);
                startActivity(i);
            }
        });
        iadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionsPickerView<String> mOptionsPickerView = new OptionsPickerView<>(getContext());
                final ArrayList<String> list = new ArrayList<>();
                list.add("闲置课本");
                list.add("闲置物品");
                list.add("包车服务");
                list.add("电动车出租服务");
                list.add("场地出租服务");
                list.add("电脑维修服务");
                list.add("代领快递服务");
                // 设置数据
                mOptionsPickerView.setPicker(list);
                // 设置选项单位
                //mOptionsPickerView.setLabels("性");
                mOptionsPickerView.setOnOptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int option1, int option2, int option3) {
                        String sex = list.get(option1);
                        Toast.makeText(getContext(), sex, Toast.LENGTH_LONG).show();
                        Intent i=new Intent(getContext(),Add_isell.class);
                        startActivity(i);
                    }
                });
                mOptionsPickerView.show();

            }
        });
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
