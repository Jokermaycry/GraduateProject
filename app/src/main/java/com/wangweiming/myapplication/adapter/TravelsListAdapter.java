package com.wangweiming.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.model.TravelsHolder;
import com.wangweiming.myapplication.model.Travelsbean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */

public class TravelsListAdapter  extends BaseAdapter {
    private Context mcontext;
    private LayoutInflater minflater=null;
    private List<Travelsbean> mTravels=null;
    public TravelsListAdapter(Context mcontext,List<Travelsbean> mTravels)
    {
        super();
        this.mcontext=mcontext;
        this.mTravels=mTravels;

    }

    @Override
    public int getCount() {
        return mTravels.size();
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
        TravelsHolder gh = null;
        if (paramView == null) {
            paramView = inflater.inflate(R.layout.goods_item, null);
            gh = new TravelsHolder();
            gh.setTvTravelsName((TextView) paramView.findViewById(R.id.name));
            gh.setTvTravelsDetail((TextView) paramView.findViewById(R.id.detail));
            gh.setTvTravelsPrice((TextView) paramView.findViewById(R.id.price));
            gh.setTvTravelsPhone((TextView) paramView.findViewById(R.id.phone));
            paramView.setTag(gh);
        }
        else {
            gh= (TravelsHolder) paramView.getTag();
        }
        for (; ; )
        {

            gh.getTvTravelsName().setText(mTravels.get(position).getName());
            gh.getTvTravelsDetail().setText(mTravels.get(position).getDetail());
            gh.getTvTravelsPrice().setText(mTravels.get(position).getPrice());
            gh.getTvTravelsPhone().setText(mTravels.get(position).getPhone());
            return paramView;

        }
    }
    public void refresh(ArrayList<Travelsbean> paramArrayList)
    {
        this.mTravels = paramArrayList;
        notifyDataSetChanged();
    }
}