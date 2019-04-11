package com.wangweiming.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.view.activity.ProductDetailActivity;

import java.util.List;

public abstract class QuickAdapter<T> extends RecyclerView.Adapter<QuickAdapter.VH>{
    private List<T> mDatas;
    private static Context mcontext;
    public QuickAdapter(List<T> datas){
        this.mDatas = datas;
    }
    public QuickAdapter(List<T> datas,Context context){

        this.mDatas = datas;
        this.mcontext=context;
    }

    private  QuickAdapter.OnItemClickListener onItemClickListener;

    public abstract int getLayoutId(int viewType);

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return VH.get(parent,getLayoutId(viewType));
    }


    @Override
    public void onBindViewHolder(final VH holder, int position) {
        convert(holder, mDatas.get(position), position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, pos);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(onItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(holder.itemView, pos);
                }
                return true;
            }
        });
    }
    // ① 定义点击回调接口
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    // ② 定义一个设置点击监听器的方法
    public void setOnItemClickListener(QuickAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void refresh (List<T> datas) {
    this.mDatas = datas;
    notifyDataSetChanged();}
    public abstract void convert(VH holder, T data, int position);

    protected static class VH extends RecyclerView.ViewHolder{
        private SparseArray<View> mViews;
        private View mConvertView;

        private VH(View v){
            super(v);
            mConvertView = v;
            mViews = new SparseArray<>();
        }

        public static VH get(ViewGroup parent, int layoutId){
            View convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            return new VH(convertView);
        }

        public <T extends View> T getView(int id){
            View v = mViews.get(id);
            if(v == null){
                v = mConvertView.findViewById(id);
                mViews.put(id, v);
            }
            return (T)v;
        }

        public void setText(int id, String value){
            TextView view = getView(id);
            view.setText(value);
        }
        public  void setImage(int id,String url)
        {
            ImageView imageView=getView(id);
            Glide.with(mcontext).load(url).into(imageView);

        }
        public  void setImage(int id)
        {
            ImageView imageView=getView(id);
            imageView.setBackgroundResource(R.mipmap.ic_launcher);
        }
    }
}