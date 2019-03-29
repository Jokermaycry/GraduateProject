package com.wangweiming.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.model.Computerbean;
import com.wangweiming.myapplication.model.ComputersHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */

public class ComputersListAdapter  extends BaseAdapter {
    private Context mcontext;
    private LayoutInflater minflater=null;
    private List<Computerbean> mComputers=null;
    public ComputersListAdapter(Context mcontext,List<Computerbean> mComputers)
    {
        super();
        this.mcontext=mcontext;
        this.mComputers=mComputers;

    }

    @Override
    public int getCount() {
        return mComputers.size();
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
        ComputersHolder gh = null;
        if (paramView == null) {
            paramView = inflater.inflate(R.layout.goods_item, null);
            gh = new ComputersHolder();
            gh.tvComputersName = (TextView) paramView.findViewById(R.id.name);
            gh.tvComputersDetail = (TextView) paramView.findViewById(R.id.detail);
            gh.tvComputersPrice = (TextView) paramView.findViewById(R.id.price);
            gh.tvComputersPhone=(TextView)paramView.findViewById(R.id.phone);
            paramView.setTag(gh);
        }
        else {
            gh= (ComputersHolder) paramView.getTag();
        }
        for (; ; )
        {

            gh.tvComputersName.setText(mComputers.get(position).getName());
            gh.tvComputersDetail.setText(mComputers.get(position).getDetail());
            gh.tvComputersPrice.setText(mComputers.get(position).getPrice());
            gh.tvComputersPhone.setText(mComputers.get(position).getPhone());
            return paramView;

        }
    }
    public void refresh(ArrayList<Computerbean> paramArrayList)
    {
        this.mComputers = paramArrayList;
        notifyDataSetChanged();
    }
}