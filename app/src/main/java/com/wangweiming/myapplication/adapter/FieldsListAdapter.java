package com.wangweiming.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.model.Fieldbean;
import com.wangweiming.myapplication.model.FieldsHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */

public class FieldsListAdapter  extends BaseAdapter {
    private Context mcontext;
    private LayoutInflater minflater=null;
    private List<Fieldbean> mFields=null;
    public FieldsListAdapter(Context mcontext,List<Fieldbean> mFields)
    {
        super();
        this.mcontext=mcontext;
        this.mFields=mFields;

    }

    @Override
    public int getCount() {
        return mFields.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View paramView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        FieldsHolder gh = null;
        if (paramView == null) {
            paramView = inflater.inflate(R.layout.goods_item, null);
            gh = new FieldsHolder();
            gh.tvFieldsName = (TextView) paramView.findViewById(R.id.name);
            gh.tvFieldsDetail = (TextView) paramView.findViewById(R.id.detail);
            gh.tvFieldsPrice = (TextView) paramView.findViewById(R.id.price);
            gh.tvFieldsPhone=(TextView)paramView.findViewById(R.id.phone);
            paramView.setTag(gh);
        }
        else {
            gh= (FieldsHolder) paramView.getTag();
        }
        for (; ; )
        {

            gh.tvFieldsName.setText(mFields.get(position).getName());
            gh.tvFieldsDetail.setText(mFields.get(position).getDetail());
            gh.tvFieldsPrice.setText(mFields.get(position).getPrice());
            gh.tvFieldsPhone.setText(mFields.get(position).getPhone());
            return paramView;

        }
    }
    public void refresh(ArrayList<Fieldbean> paramArrayList)
    {
        this.mFields = paramArrayList;
        notifyDataSetChanged();
    }
}