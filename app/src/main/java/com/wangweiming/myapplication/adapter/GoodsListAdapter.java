package com.wangweiming.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.model.Goodsbean;
import com.wangweiming.myapplication.model.GoodsHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/4.
 */

public class GoodsListAdapter  extends BaseAdapter{
    private Context mcontext;
    private LayoutInflater minflater=null;
    private List<Goodsbean> mGoods=null;
    public GoodsListAdapter(Context mcontext,List<Goodsbean> mGoods)
    {
        super();
        this.mcontext=mcontext;
        this.mGoods=mGoods;

    }

    @Override
    public int getCount() {
        return mGoods.size();
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
       GoodsHolder gh = null;
        if (paramView == null) {
            paramView = inflater.inflate(R.layout.goods_item, null);
            gh = new GoodsHolder();
            gh.tvGoodsName = (TextView) paramView.findViewById(R.id.name);
            gh.tvGoodsDetail = (TextView) paramView.findViewById(R.id.detail);
            gh.tvGoodsPrice = (TextView) paramView.findViewById(R.id.price);
            gh.tvGoodsPhone=(TextView)paramView.findViewById(R.id.phone);
            paramView.setTag(gh);
        }
        else {
            gh= (GoodsHolder) paramView.getTag();
        }
        for (; ; )
        {

            gh.tvGoodsName.setText(mGoods.get(position).getName());
            gh.tvGoodsDetail.setText(mGoods.get(position).getDetail());
            gh.tvGoodsPrice.setText(mGoods.get(position).getPrice());
            gh.tvGoodsPhone.setText(mGoods.get(position).getPhone());
            return paramView;

        }
    }
    public void refresh(ArrayList<Goodsbean> paramArrayList)
    {
        this.mGoods = paramArrayList;
        notifyDataSetChanged();
    }
}
