package com.silence.commonframe.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.silence.commonframe.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import com.lmd.recviewchangecolordemo.MainActivity;
//import com.lmd.recviewchangecolordemo.R;

public class DeviceDetailRecViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * 第21-27行是：MainActivity裏面  適配器 傳過來的【上下文】和【模擬數據集合】
     */
   // private MainActivity mainActivity;
    private Context mainActivity;
    private List<String> list;
    private List<HashMap<String,String>> listmap;

//    public DeviceDetailRecViewAdapter(Context mainActivity, List<String> list) {
//        this.mainActivity = mainActivity;
//        this.list = list;
//    }


    public DeviceDetailRecViewAdapter(Context mainActivity, List<HashMap<String,String>> listmap) {
        this.mainActivity = mainActivity;
        this.listmap = listmap;
    }

    /**
     * 第33-39行是：手寫點擊事件的接口回調
     * 36行是點擊事件，    38行是長按事件。根據你的需求  寫你需要的
     */
    private OnItemClickListener onRecyclerViewItemClickListener;

    public interface OnItemClickListener {
        void onClick(int position);

        void onLongClick(int position);
    }

    /**
     * 第44-62行是點擊變色的邏輯
     */
    /////////////////////////////////////////////最後一步點擊變色///////////////////////////////////////////////////////
    //先声明一个int成员变量
    private int thisPosition;

    //再定义一个int类型的返回值方法
    public int getthisPosition() {
        return thisPosition;
    }

    //其次定义一个方法用来绑定当前参数值的方法
    //此方法是在调用此适配器的地方调用的，此适配器内不会被调用到
    public void setThisPosition(int thisPosition) {
        this.thisPosition = thisPosition;
    }

    public void setOnRecyclerViewItemClickListener(OnItemClickListener onItemClickListener) {
        this.onRecyclerViewItemClickListener = onItemClickListener;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * onCreateViewHolder渲染視圖綁定視圖
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mainActivity).inflate(R.layout.recyclerview_item_devicedetail, parent, false);
        RecViewViewHolder viewViewHolder = new RecViewViewHolder(view);
        return viewViewHolder;
    }

    /**
     * onBindViewHolder綁定/賦值  數據
     * 點擊事件   變色邏輯
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        /**
         * 這裏是常 用/見  賦值的地方
         */
       RecViewViewHolder viewViewHolder = (RecViewViewHolder) holder;
        if (viewViewHolder != null) {
         //   viewViewHolder.titel.setText( "海智园3栋" +list.get(position) + ""+"...");
  //          viewViewHolder.status.setText( list.get(position).toString());
            viewViewHolder.status.setText( listmap.get(position).get("inputtime"));

            ///////////////////////////////////點擊變色/////////////////////////////////////////////
//            if (position == getthisPosition()) {
//                //選中的顔色就設成了  黃色
//                viewViewHolder.titel.setBackgroundColor(Color.LTGRAY);//LTGRAY
//            } else {
//                //未選中的顔色 就設成了 白色
//                viewViewHolder.titel.setBackgroundColor(Color.WHITE);
//            }

            viewViewHolder.status.setBackgroundColor(Color.WHITE);
            ////////////////////////////////////點擊變色////////////////////////////////////////////
        }
        /**
         * 這裏是設置  點擊/長按  的事件的地方
         */
        if (onRecyclerViewItemClickListener != null) {
            //點擊事件
            viewViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /**
                     * 拿到上面暴露的接口  的點擊方法  裏面的值和點擊事件的position  相互賦值  保持一致
                     * 算了，越説越亂，自己去理解吧
                     */
                    onRecyclerViewItemClickListener.onClick(position);
                }
            });
            //長按事件
            viewViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onRecyclerViewItemClickListener.onLongClick(position);
                    return false;
                }
            });
            //
        }
        //
    }

    /**
     * 縂條目
     *
     * @return
     */
    @Override
    public int getItemCount() {
        /**
         * 第152行：list != null ? list.size() : 0
         * 這是三元運算符  知道吧
         * 相當於【if（）{}】判空語句
         * 二選一  衹能用一種 判空語句
         */
//        if (list != null) {
//            return list.size();
//        } else {
//            return 0;
//        }
        return list != null ? list.size() : 0;
    }

    /**
     * 手寫View Holder  優化類
     * 必須繼承【RecyclerView】包下的【ViewHolder】
     */
    class RecViewViewHolder extends RecyclerView.ViewHolder {

        private final TextView status;

        public RecViewViewHolder(View itemView) {
            super(itemView);
            status = (TextView) itemView.findViewById(R.id.item_content);
        }
    }
}
