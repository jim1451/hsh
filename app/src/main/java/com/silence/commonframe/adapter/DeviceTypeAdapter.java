package com.silence.commonframe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.silence.commonframe.R;

public class DeviceTypeAdapter  extends RecyclerView.Adapter<DeviceTypeAdapter.ViewHolder> {

    private Context mContext;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //获取item_layout的布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_devicetype, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
