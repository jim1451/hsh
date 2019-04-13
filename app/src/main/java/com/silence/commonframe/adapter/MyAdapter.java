package com.silence.commonframe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.silence.commonframe.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by guichupeng on 2016/10/17.
 */
public class MyAdapter extends BaseAdapter {
    private List<String> data;
    private Context context;
    private Holder holder = null;

   private List<HashMap<String, String>> listmap;



//    public MyAdapter(List<String> data, Context context) {
//        this.data = data;
//        this.context = context;
//    }

    public MyAdapter(List<HashMap<String, String>> listmap, Context context) {
        this.listmap = listmap;
        this.context = context;
    }



    @Override
    public int getCount() {
     //  return data.size();
        return listmap.size();
    }

    @Override
    public Object getItem(int position) {
      //  return data.get(position);


        return listmap.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.listview_item, null);


            holder.textView = (TextView) convertView.findViewById(R.id.listview_item_tv);
            holder.textView1 = (TextView) convertView.findViewById(R.id.listview_item_tv1);
            holder.linearLayout = (LinearLayout) convertView.findViewById(R.id.ll);




            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
//        if (position < 4){
//
//            convertView.seth
//
//        }

     //   holder.textView.setText(data.get(position));

//        String t = listmap.get(position).get("place");
//        System.out.println("ssss"+t);

        holder.textView.setText(listmap.get(position).get("RegionName"));
        holder.textView1.setText(listmap.get(position).get("Location"));
   //     holder.textView.setText(data.get(position));





//        listView.setDivider(new ColorDrawable(Color.GRAY));


//        switch (position){
//            case 0:
//                holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDarkTrans));
//                break;
//            case 1:
//                holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDarkTrans));
//                break;
//            case 2:
//                holder.textView2.setBackgroundColor(context.getResources().getColor(R.color.divider2));
//                break;
  //      }




        return convertView;
    }
    private class Holder {
        TextView textView;
        TextView textView1;
        LinearLayout linearLayout;
    }
    //刷新list
    public void initData(List<String> data){
        this.data=data;
        notifyDataSetChanged();
    }
}
