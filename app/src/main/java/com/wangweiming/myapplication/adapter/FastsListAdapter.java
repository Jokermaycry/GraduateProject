package com.wangweiming.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.model.Fastbean;
import com.wangweiming.myapplication.model.FastsHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */

public class FastsListAdapter  extends BaseAdapter {
    private Context mcontext;
    private LayoutInflater minflater=null;
    private List<Fastbean> mFasts=null;
    public FastsListAdapter(Context mcontext,List<Fastbean> mFasts)
    {
        super();
        this.mcontext=mcontext;
        this.mFasts=mFasts;

    }

    @Override
    public int getCount() {
        return mFasts.size();
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
        FastsHolder gh = null;
        if (paramView == null) {
            paramView = inflater.inflate(R.layout.goods_item, null);
            gh = new FastsHolder();
            gh.tvFastsName = (TextView) paramView.findViewById(R.id.name);
            gh.tvFastsDetail = (TextView) paramView.findViewById(R.id.detail);
            gh.tvFastsPrice = (TextView) paramView.findViewById(R.id.price);
            gh.tvFastsPhone=(TextView)paramView.findViewById(R.id.phone);
            paramView.setTag(gh);
        }
        else {
            gh= (FastsHolder) paramView.getTag();
        }
        for (; ; )
        {

            gh.tvFastsName.setText(mFasts.get(position).getName());
            gh.tvFastsDetail.setText(mFasts.get(position).getDetail());
            gh.tvFastsPrice.setText(mFasts.get(position).getPrice());
            gh.tvFastsPhone.setText(mFasts.get(position).getPhone());
            return paramView;

        }
    }
    public void refresh(ArrayList<Fastbean> paramArrayList)
    {
        this.mFasts = paramArrayList;
        notifyDataSetChanged();
    }
}
