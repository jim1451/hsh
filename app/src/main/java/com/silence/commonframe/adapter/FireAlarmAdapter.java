package com.silence.commonframe.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.silence.commonframe.R;
import com.silence.commonframe.bean.JsonBean;

import java.util.HashMap;
import java.util.List;

public class FireAlarmAdapter extends RecyclerView.Adapter<FireAlarmAdapter.MyViewHolder> {


    private Context context;
    private List<JsonBean> mList;
    private LayoutInflater mInflater;
    private View view;
    private MyItemClickListener listener;


    private List<HashMap<String, String>> listmap1;
    private MyViewHolder viewHolder1;

    private ButtonInterface buttonInterface;

//    public FindAdapter(Context context, List<JsonBean> mList) {
//        this.context = context;
//        this.mList = mList;
//        mInflater = LayoutInflater.from(context);
//    }

    public FireAlarmAdapter(Context context, List<HashMap<String, String>> listmap) {
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
        view = mInflater.inflate(R.layout.item_item_firealarm, parent, false);
        viewHolder1 = new MyViewHolder(view, listener);

        return viewHolder1;


        // return new FindAdapter.MyViewHolder(mInflater.inflate(R.layout.item_recylerview, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position) {



        viewHolder1.tv.setText("编号："+listmap1.get(position).get("deviceId"));//tv_devicename
     //   viewHolder1.tv_devicename.setText("设备："+listmap1.get(i).get("devicename"));//tv_time
        viewHolder1.tv_time.setText("时间："+listmap1.get(position).get("gmtCreate"));
        viewHolder1.tv_place.setText("区域名："+listmap1.get(position).get("deployment"));
        viewHolder1.tv_place1.setText("位置："+listmap1.get(position).get("deviceLocation"));
        viewHolder1.tv_place3.setText("详细地址："+listmap1.get(position).get("location"));//deployment


        holder.bt_know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonInterface != null){
                    buttonInterface.onclick(v,position);
                }
            }
        });
        if (listmap1.get(position).get("ifRead").equals("1")){
            viewHolder1.bt_know.setVisibility(View.INVISIBLE);
        }


    }

//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
//
//
//        //  viewHolder = (FindAdapter.MyViewHolder) holder;
//        //    viewHolder1.iv.setText(mList.get(position));
//
////        viewHolder.content.setText("编号："+listmap1.get(position).get("id"));
//        viewHolder1.tv.setText("编号："+listmap1.get(i).get("deviceId"));//tv_devicename
//     //   viewHolder1.tv_devicename.setText("设备："+listmap1.get(i).get("devicename"));//tv_time
//        viewHolder1.tv_time.setText("时间："+listmap1.get(i).get("gmtCreate"));
//        viewHolder1.tv_place.setText("区域名："+listmap1.get(i).get("deployment"));
//        viewHolder1.tv_place1.setText("位置："+listmap1.get(i).get("deviceLocation"));
//        viewHolder1.tv_place3.setText("详细地址："+listmap1.get(i).get("location"));//deployment
//
//
//
//
//
//        holder.submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (buttonInterface != null){
//                    buttonInterface.onclick(v,position);
//                }
//            }
//        });
//    }








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
        Button   bt_know;
        MyItemClickListener mListener;

        public MyViewHolder(View itemView, final MyItemClickListener mListener) {
            super(itemView);
            this.itemView = itemView;
            // iv = (ImageView) itemView.findViewById(R.id.number);
            tv = (TextView) itemView.findViewById(R.id.number);//recycler_view_test_item_person_name_tv
            tv_devicename = (TextView) itemView.findViewById(R.id.recycler_view_test_item_person_name_tv);//recycler_view_place

            tv_time = (TextView) itemView.findViewById(R.id.time);
            tv_place = (TextView) itemView.findViewById(R.id.recycler_view_place);
            tv_place1 = (TextView) itemView.findViewById(R.id.place);
            tv_place3 = (TextView) itemView.findViewById(R.id.place1);
            bt_know = (Button) itemView.findViewById(R.id.id_know);



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


    public  void buttonSetOnclick(ButtonInterface buttonInterface){
        this.buttonInterface = buttonInterface;
    }

    public interface ButtonInterface{

        public void onclick( View view,int position);

    }


}
