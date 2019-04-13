package com.silence.commonframe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.silence.commonframe.R;
import com.silence.commonframe.utils.Data;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 曹博 on 2016/9/27.
 */

public class SitedetailAdapter1 extends RecyclerView.Adapter<SitedetailAdapter1.MyViewHolder> {

    private List<String> mDatas1;


    private LayoutInflater mInflater;

    private List<Integer> mHeights;
    private List<HashMap<String, String>> listmap;
    private ButtonInterface buttonInterface;




//    public SitedetailAdapter(Context context, List<String> datas)
//    {
//        mInflater = LayoutInflater.from(context);
//        mDatas1 = datas;
//
//
//    }

    public SitedetailAdapter1(Context context, List<HashMap<String, String>> datas)
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
                R.layout.recyclerview_itemtest1, parent, false));
        return holder;
    }



    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position)
    {
        String deployment = listmap.get(position).get("deployment");

        holder.tv.setText(deployment);

        String isTel = listmap.get(position).get("isTel");
        String isMsg = listmap.get(position).get("isMsg");

        if (isTel.equals("1")){
            holder.cb_message.setChecked(true);
        }

        if (isMsg.equals("1")){
            holder.cb_photo.setChecked(true);
        }

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
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

        holder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (buttonInterface != null){
                        buttonInterface.onclick(v,position);
                    }
            }
        });



        holder.cb_photo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    Data.setCb_photo("1");
                } else {
                    Data.setCb_photo("0");
                }
            }
        });

        holder.cb_message.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    Data.setCb_message("1");
                } else {
                    Data.setCb_message("0");
                }
            }
        });




    }


    public  void buttonSetOnclick(ButtonInterface buttonInterface){
        this.buttonInterface = buttonInterface;
    }


    public interface ButtonInterface{

        public void onclick( View view,int position);

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
        TextView submit;

        CheckBox  cb_photo;
        CheckBox  cb_message;
        public MyViewHolder(View view)
        {
            super(view);
            tv = (TextView) view.findViewById(R.id.re_item);
            submit = (TextView) view.findViewById(R.id.submit);
            cb_photo = (CheckBox) view.findViewById(R.id.cb_photo);
            cb_message = (CheckBox) view.findViewById(R.id.cb_message);
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
