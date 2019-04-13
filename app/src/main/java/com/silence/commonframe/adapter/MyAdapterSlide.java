package com.silence.commonframe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.silence.commonframe.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyAdapterSlide extends RecyclerView.Adapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<String> mList;
    private List<HashMap<String, String>> listmap1;







    public MyAdapterSlide(Context context, List<HashMap<String, String>> listmap) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        listmap1 = listmap;
    }


    public MyAdapterSlide(Context context, ArrayList<String> list) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.recyclerview_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyViewHolder viewHolder = (MyViewHolder) holder;
        //viewHolder.content.setText(mList.get(position));

        viewHolder.content.setText("编号："+listmap1.get(position).get("id"));
        viewHolder.content1.setText("位置："+listmap1.get(position).get("deviceLocation"));
    }

    @Override
    public int getItemCount() {
     //   return mList != null ? mList.size() : 0;
        return listmap1 != null ? listmap1.size() : 0;
    }

    public void removeItem(int position) {
        listmap1.remove(position);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView content;
        public TextView content1;
        public TextView delete;
        public TextView navi;
        public LinearLayout layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.item_content);
            content1 = (TextView) itemView.findViewById(R.id.item_content1);
            delete = (TextView) itemView.findViewById(R.id.item_delete);
            navi = (TextView) itemView.findViewById(R.id.item_navi);
            layout = (LinearLayout) itemView.findViewById(R.id.item_layout);
        }
    }
}
