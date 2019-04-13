package com.silence.commonframe.adapter;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.silence.commonframe.R;
import com.silence.commonframe.bean.JsonBean;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FindAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private List<JsonBean> mList;
    private LayoutInflater mInflater;
    private View view;
    private MyItemClickListener listener;


    private List<HashMap<String, String>> listmap1;
    private MyViewHolder viewHolder1;

//    public FindAdapter(Context context, List<JsonBean> mList) {
//        this.context = context;
//        this.mList = mList;
//        mInflater = LayoutInflater.from(context);
//    }

    public FindAdapter(Context context, List<HashMap<String, String>> listmap) {
//        mContext = context;
//        mInflater = LayoutInflater.from(context);
//        listmap1 = listmap;


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
        view = mInflater.inflate(R.layout.item_recylerview, parent, false);
        viewHolder1 = new MyViewHolder(view, listener);

        return viewHolder1;


        // return new FindAdapter.MyViewHolder(mInflater.inflate(R.layout.item_recylerview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


        //  viewHolder = (FindAdapter.MyViewHolder) holder;
        //    viewHolder1.iv.setText(mList.get(position));

//        viewHolder.content.setText("编号："+listmap1.get(position).get("id"));
        viewHolder1.tv.setText("编号："+listmap1.get(i).get("deviceId"));//tv_devicename
     //   viewHolder1.tv_devicename.setText("设备："+listmap1.get(i).get("devicename"));//tv_time
        viewHolder1.tv_time.setText("时间："+listmap1.get(i).get("gmtCreate"));
        viewHolder1.tv_place.setText("区域名："+listmap1.get(i).get("deployment"));
        viewHolder1.tv_place1.setText("位置："+listmap1.get(i).get("deviceLocation"));
        viewHolder1.tv_place3.setText("详细地址："+listmap1.get(i).get("location"));//deployment
        System.out.println("pp1"+listmap1.get(i).get("recheckId"));

        if (!listmap1.get(i).get("recheckId").equals("11")){

            viewHolder1.imageView.setImageResource(R.mipmap.unaudited3);
        }
        if (listmap1.get(i).get("troubleType").equals("1")){
            viewHolder1.fire.setText("设备状态：火警");
            viewHolder1.fire.setTextColor(Color.rgb(250,85,58));//imageViewfire
            viewHolder1.imageViewfire.setImageResource(R.mipmap.redlinght);
       //     finalImageView.setImageResource(R.drawable.guzhang);
        }else {
            viewHolder1.imageViewfire.setImageResource(R.mipmap.light);
        }


   //  viewHolder1.imageView.setImageResource(R.mipmap.unaudited3);
    }

//    @Override
//    public void onBindViewHolder(final MyViewHolder holder, final int position) {
////        holder.tv.setText(mList.get(position).getText());
////        holder.iv.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Toast.makeText(context, position + "" + "abc", Toast.LENGTH_SHORT).show();
////            }
////        });
//    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        // ImageView iv;




        View itemView;
        TextView tv;
        TextView tv_devicename;
        TextView tv_time;
        TextView tv_place;
        TextView tv_place1;
        TextView tv_place3;
        MyItemClickListener mListener;
        ImageView  imageView;
        ImageView  imageViewfire;
        TextView  fire;

        public MyViewHolder(View itemView, final MyItemClickListener mListener) {
            super(itemView);
            this.itemView = itemView;
            // iv = (ImageView) itemView.findViewById(R.id.number);
            tv = (TextView) itemView.findViewById(R.id.number);//recycler_view_test_item_person_name_tv
            tv_devicename = (TextView) itemView.findViewById(R.id.recycler_view_test_item_person_name_tv);//recycler_view_place

            tv_time = (TextView) itemView.findViewById(R.id.time);
            tv_place = (TextView) itemView.findViewById(R.id.recycler_view_place);
            tv_place1 = (TextView) itemView.findViewById(R.id.place);
            tv_place3 = (TextView) itemView.findViewById(R.id.place1);//imageView_check  fire
            fire = (TextView) itemView.findViewById(R.id.fire);
            imageView = (ImageView) itemView.findViewById(R.id.imageView_check); //imageView_fire
            imageViewfire = (ImageView) itemView.findViewById(R.id.imageView_fire);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(v, getAdapterPosition());
                }
            });
        }

    }

    /**
     * 回调接口
     */
    public interface MyItemClickListener {
        void onItemClick(View view, int postion);
    }

    /**
     * 设置Item点击监听
     *
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener) {
        this.listener = listener;
    }


}
