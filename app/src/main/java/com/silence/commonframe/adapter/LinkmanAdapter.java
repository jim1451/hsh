package com.silence.commonframe.adapter;





import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.silence.commonframe.R;
import com.silence.commonframe.bean.JsonBean;

import java.util.HashMap;
import java.util.List;

public class LinkmanAdapter extends RecyclerView.Adapter<LinkmanAdapter.MyViewHolder> {
    private Context context;
    private List<JsonBean> mList;
    private LayoutInflater mInflater;
    private View view;
    private List<HashMap<String, String>> listmap1;

    public LinkmanAdapter(Context context, List<HashMap<String, String>> listmap) {


        this.context = context;
        listmap1 = listmap;
        mInflater = LayoutInflater.from(context);
    }




    @Override
    public int getItemCount() {
        return listmap1.size();

    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        view = mInflater.inflate(R.layout.linkmanrececlyview_item, parent, false);
        LinkmanAdapter.MyViewHolder  viewHolder1 = new MyViewHolder(view);
        return viewHolder1;


    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {
        holder.tv.setText(listmap1.get(position).get("name"));
        holder.tv1.setText(listmap1.get(position).get("phone"));


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



    public void removeData(int position)
    {
        listmap1.remove(position);
        notifyItemRemoved(position);
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        View itemView;
        TextView tv;
        TextView tv1;
    //    MyItemClickListener mListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
             // iv = (ImageView) itemView.findViewById(R.id.number);
            tv = (TextView) itemView.findViewById(R.id.tv_name);//tv_tel
            tv1 = (TextView) itemView.findViewById(R.id.tv_tel);


        }

    }



    //点击事件
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }



}
