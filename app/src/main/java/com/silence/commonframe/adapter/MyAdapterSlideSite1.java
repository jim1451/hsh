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

public class MyAdapterSlideSite1 extends RecyclerView.Adapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<String> mList;
    private List<HashMap<String, String>> listmap1;







    public MyAdapterSlideSite1(Context context, List<HashMap<String, String>> listmap) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        listmap1 = listmap;
    }


    public MyAdapterSlideSite1(Context context, ArrayList<String> list) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.recyclerview_item_devicedetail, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyViewHolder viewHolder = (MyViewHolder) holder;
        //viewHolder.content.setText(mList.get(position));
        viewHolder.time.setText(listmap1.get(position).get("inputtime"));
        if (listmap1.get(position).get("deviceStatus").equals("0")){
            viewHolder.tv_status.setText("状态：正常");
        }else {
            viewHolder.tv_status.setText("状态：火警");
        }
        viewHolder.tv_batteryvoltage.setText("当前电池电压："+listmap1.get(position).get("batteryVoltage")+"V");
        viewHolder.tv_signalstrength.setText("信号强度："+listmap1.get(position).get("signalStrength"));
        viewHolder.tv_cputem.setText("CPU温度："+listmap1.get(position).get("mcuTemp"));
//        viewHolder.content.setText("编号"+listmap1.get(position).get("id"));
//        viewHolder.content1.setText("位置："+listmap1.get(position).get("deviceLocation"));
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
        public TextView time;
        public TextView tv_status;
        public TextView tv_batteryvoltage;
        public TextView tv_signalstrength;
        public TextView tv_cputem;

        public MyViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.tv_time);//tv_status
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
            tv_batteryvoltage = (TextView) itemView.findViewById(R.id.tv_batteryvoltage);//tv_signalstrength
            tv_signalstrength = (TextView) itemView.findViewById(R.id.tv_signalstrength);//tv_cputem
            tv_cputem = (TextView) itemView.findViewById(R.id.tv_cputem);
      //      layout = (LinearLayout) itemView.findViewById(R.id.item_layout);
        }
    }
}
