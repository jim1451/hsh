package com.silence.commonframe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.silence.commonframe.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 曹博 on 2016/9/27.
 */

public class SitedetailAdapter extends RecyclerView.Adapter<SitedetailAdapter.MyViewHolder> {

    private List<String> mDatas1;


    private LayoutInflater mInflater;

    private List<Integer> mHeights;
    private List<HashMap<String, String>> listmap;




//    public SitedetailAdapter(Context context, List<String> datas)
//    {
//        mInflater = LayoutInflater.from(context);
//        mDatas1 = datas;
//
//
//    }

    public SitedetailAdapter(Context context, List<HashMap<String, String>> datas)
    {
        mInflater = LayoutInflater.from(context);
        listmap = datas;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
//        MyViewHolder holder = new MyViewHolder(mInflater.inflate(
//                R.layout.recyclerview_item_devicedetail, parent, false));

        MyViewHolder holder = new MyViewHolder(mInflater.inflate(
                R.layout.recyclerview_itemtest, parent, false));
        return holder;
    }



    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position)
    {


        //@2：如果要瀑布，将下面的代码放开
        /*ViewGroup.LayoutParams lp = holder.tv.getLayoutParams();
        lp.height = mHeights.get(position);

        holder.tv.setLayoutParams(lp);
       */


       // holder.tv.setText(mDatas1.get(position));
      //  SitedetailAdapter.MyViewHolder viewViewHolder = (SitedetailAdapter.MyViewHolder) holder;
        String t = listmap.get(position).get("place");

        holder.tv.setText(t);


        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    //removeData(pos);
                    return false;
                }
            });
        }


    }

//    //添加和删除方法
//    public void addData(int position)
//    {
//        listmap.add(position, "Insert One");
//        notifyItemInserted(position);
//    }

    public void removeData(int position)
    {
        listmap.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount()
    {
        return listmap.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView tv;

        public MyViewHolder(View view)
        {
            super(view);
            tv = (TextView) view.findViewById(R.id.re_item);
        }
    }


    //点击事件
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
