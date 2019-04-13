package com.silence.commonframe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.silence.commonframe.R;
import com.silence.commonframe.model.IsChoose;
import com.silence.commonframe.utils.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 曹博 on 2016/9/27.
 */

public class SitedetailMineAdapter extends RecyclerView.Adapter<SitedetailMineAdapter.MyViewHolder> {

    private List<String> mDatas1;


    private LayoutInflater mInflater;

    private List<Integer> mHeights;
    private List<HashMap<String, String>> listmap;
    private ArrayList<IsChoose> MyarrayList = null;
    private IsChoose isChoose;
    private int VISIBLE = 0;
    private int ISCHOOSE = 0;
    private int isshow = 0;
    private int isSelect;
    private ArrayList<String>  listid = new ArrayList<>();
//    private ArrayList<String> listregionalism = new ArrayList<String>();




//    public SitedetailAdapter(Context context, List<String> datas)
//    {
//        mInflater = LayoutInflater.from(context);
//        mDatas1 = datas;
//
//
//    }

    public SitedetailMineAdapter(Context context, List<HashMap<String, String>> datas)
    {
        mInflater = LayoutInflater.from(context);
        listmap = datas;



    }


    public SitedetailMineAdapter(Context context, List<HashMap<String, String>> datas, ArrayList arrayList )
    {
        mInflater = LayoutInflater.from(context);
        listmap = datas;
        this.MyarrayList = arrayList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
//        MyViewHolder holder = new MyViewHolder(mInflater.inflate(
//                R.layout.recyclerview_item_devicedetail, parent, false));

        MyViewHolder holder = new MyViewHolder(mInflater.inflate(
                R.layout.recyclerview_item_minedevice, parent, false));

        holder.mCheckBox.setVisibility(View.GONE);
        return holder;
    }



    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position)
    {



        String t = listmap.get(position).get("deployment");
        String t1 = listmap.get(position).get("Location");
        holder.tv.setText(t);
        holder.tv1.setText(t1);

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



        isChoose = MyarrayList.get(position);
        if (isshow == VISIBLE) {
            holder.mCheckBox.setVisibility(View.GONE);
        } else {
            holder.mCheckBox.setVisibility(View.VISIBLE);
            isSelect = isChoose.isSelect;
     //       isSelect = Data.getArrayList().get(position).isSelect;
            if (isSelect != ISCHOOSE) {
                holder.mCheckBox.setImageResource(R.mipmap.ic_checked);
            } else {
                holder.mCheckBox.setImageResource(R.mipmap.ic_uncheck);
            }
        }



    }



    /**
     *是否显示选择筐
     */
    public void setMode(int isshow) {
        this.isshow = isshow;
        notifyDataSetChanged();
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




    public void remove(IsChoose deleteItem) {
        for (int i = 0; i < MyarrayList.size(); i++) {
            if (MyarrayList.contains(deleteItem)) {
                int i1 = MyarrayList.indexOf(deleteItem);
                MyarrayList.remove(deleteItem);

                String id = listmap.get(i1).get("id");
                   listid.add(id);
                listmap.remove(i1);

                //listmap.get(position).get("place")
             //   Data.setListSiteid(id);
                notifyItemRemoved(i1);
            }
        }
        Data.setListSiteid(listid);
    }

    @Override
    public int getItemCount()
    {
        return listmap.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView tv;
        TextView tv1;
        private final ImageView mCheckBox;
        public MyViewHolder(View view)
        {
            super(view);
            tv = (TextView) view.findViewById(R.id.re_item);
            tv1 = (TextView) view.findViewById(R.id.re_item1);



            mCheckBox = (ImageView) view.findViewById(R.id.check_box);

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
