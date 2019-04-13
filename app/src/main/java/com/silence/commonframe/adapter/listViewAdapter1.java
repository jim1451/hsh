package com.silence.commonframe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.silence.commonframe.R;
import com.silence.commonframe.bean.iconInformation;

import java.util.List;

/**
 * Created by ChuPeng on 2017/3/25.
 */

public class listViewAdapter1 extends BaseAdapter
{
    private Context context;
    private List<iconInformation> list;
    private LayoutInflater layoutInflater;

    public listViewAdapter1(Context context, List<iconInformation> list)
    {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    public int getCount()
    {
        return list.size();
    }
    public Object getItem(int position)
    {
        return list.get(position);
    }

    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup viewGroup)
    {
        View view;
        ViewHolder viewHolder;
        iconInformation iconInformation = list.get(position);
        if(convertView == null)
        {
            view = layoutInflater.inflate(R.layout.listview_item1, null);
            viewHolder = new ViewHolder();
            viewHolder.iconImage = (ImageView) view.findViewById(R.id.iconImage);
            viewHolder.iconName = (TextView) view.findViewById(R.id.iconName);
            view.setTag(viewHolder);
        }
        else
        {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.iconImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"点击了：",Toast.LENGTH_SHORT).show();
            }
        });



        viewHolder.iconImage.setImageBitmap(iconInformation.getIcon());
        viewHolder.iconName.setText(iconInformation.getName());
        return view;
    }
    private class ViewHolder
    {
        ImageView iconImage;
        TextView iconName;
    }
}
