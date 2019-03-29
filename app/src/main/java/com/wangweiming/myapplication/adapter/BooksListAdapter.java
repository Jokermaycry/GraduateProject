package com.wangweiming.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.model.Bookbean;
import com.wangweiming.myapplication.model.Bookholder;

import java.util.ArrayList;
import java.util.List;



public class BooksListAdapter extends BaseAdapter {
    private Context mcontext;
    private LayoutInflater minflater=null;
    private List<Bookbean> mBooks=null;
    public BooksListAdapter(Context mcontext,List<Bookbean> mBooks)
    {
        super();
        this.mcontext=mcontext;
        this.mBooks=mBooks;

    }
    @Override
    public int getCount() {
       return  mBooks.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View paramView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        Bookholder bh = null;
        if (paramView == null) {
            paramView = inflater.inflate(R.layout.books_item, null);
            bh = new Bookholder();
            bh.setTvBooksName((TextView) paramView.findViewById(R.id.tv_books_name));
            bh.setTvBooksPhone((TextView) paramView.findViewById(R.id.tv_books_phone));
            bh.setTvBooksPrice((TextView) paramView.findViewById(R.id.tv_books_price));
            paramView.setTag(bh);
        }
        else {
            bh= (Bookholder) paramView.getTag();
        }
        for (; ; )
        {

            bh.getTvBooksName().setText(mBooks.get(position).getname());
            bh.getTvBooksPhone().setText(mBooks.get(position).getphone());
            bh.getTvBooksPrice().setText(mBooks.get(position).getprice());
            return paramView;

        }
    }
    public void refresh(ArrayList<Bookbean> paramArrayList)
    {
        this.mBooks = paramArrayList;
        notifyDataSetChanged();
    }
}
