package com.wangweiming.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.model.CarsHolder;
import com.wangweiming.myapplication.model.Carsbean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */

public class CarsListAdapter extends BaseAdapter {
    private Context mcontext;
    private LayoutInflater minflater=null;
    private List<Carsbean> mCars=null;
    public CarsListAdapter(Context mcontext, List<Carsbean> mCars)
    {
        super();
        this.mcontext=mcontext;
        this.mCars=mCars;

    }

    @Override
    public int getCount() {
        return mCars.size();
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
        CarsHolder gh = null;
        if (paramView == null) {
            paramView = inflater.inflate(R.layout.goods_item, null);
            gh = new CarsHolder();
            gh.setTvCarsName((TextView) paramView.findViewById(R.id.name));
            gh.setTvCarsDetail((TextView) paramView.findViewById(R.id.detail));
            gh.setTvCarsPrice((TextView) paramView.findViewById(R.id.price));
            gh.setTvCarsPhone((TextView) paramView.findViewById(R.id.phone));
            paramView.setTag(gh);
        }
        else {
            gh= (CarsHolder) paramView.getTag();
        }
        for (; ; )
        {

            gh.getTvCarsName().setText(mCars.get(position).getName());
            gh.getTvCarsDetail().setText(mCars.get(position).getDetail());
            gh.getTvCarsPrice().setText(mCars.get(position).getPrice());
            gh.getTvCarsPhone().setText(mCars.get(position).getPhone());
            return paramView;

        }
    }
    public void refresh(ArrayList<Carsbean> paramArrayList)
    {
        this.mCars = paramArrayList;
        notifyDataSetChanged();
    }
}