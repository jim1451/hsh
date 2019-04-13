package com.silence.commonframe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.silence.commonframe.R;
import com.silence.commonframe.model.Device1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyAdapterSlideMine extends RecyclerView.Adapter<MyAdapterSlideMine.MyViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<String> mList;
    private List<HashMap<String, String>> listmap1;


    private static final int MYLIVE_MODE_CHECK = 0;
    int mEditMode = MYLIVE_MODE_CHECK;




    public MyAdapterSlideMine(Context context, List<HashMap<String, String>> listmap) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        listmap1 = listmap;
    }


    public MyAdapterSlideMine(Context context, ArrayList<String> list) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.recyclerview_item_minedevice1, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
     //  final List<HashMap<String, String>> listmap3 = (List<HashMap<String, String>>) listmap1.get(holder.getAdapterPosition());

        final MyViewHolder viewHolder = (MyViewHolder) holder;
        //viewHolder.content.setText(mList.get(position));

        viewHolder.content.setText("编号："+listmap1.get(position).get("id"));
        viewHolder.content1.setText("位置："+listmap1.get(position).get("dLocation"));


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
        public ImageView mCheckBox;


        public MyViewHolder(View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.item_content);
            content1 = (TextView) itemView.findViewById(R.id.item_content1);
            delete = (TextView) itemView.findViewById(R.id.item_delete);
            navi = (TextView) itemView.findViewById(R.id.item_navi);
            layout = (LinearLayout) itemView.findViewById(R.id.item_layout);
            mCheckBox = (ImageView) itemView.findViewById(R.id.check_box);
        }
    }



    //点击事件
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    private SitedetailMineAdapter.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(SitedetailMineAdapter.OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
