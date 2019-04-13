package com.silence.commonframe.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.silence.commonframe.R;
import com.silence.commonframe.activity.CommonScanActivity;
import com.silence.commonframe.utils.MyGridView;
import com.silence.commonframe.zxing.utils.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ListViewAdapter3 extends BaseExpandableListAdapter implements
		OnItemClickListener
{
	public static final int ItemHeight = 48;// 每项的高度
	public static final int PaddingLeft = 36;// 每项的高度

//	public static final int ItemHeight = 96;// 每项的高度
//	public static final int PaddingLeft = ;// 每项的高度
	private String url = "http://img1.dzwww.com:8080/tupian_pl/20150813/16/7858995348613407436.jpg";

	private int myPaddingLeft = 0;

	private MyGridView toolbarGrid;


	Bitmap myBitmap1;

//	private String menu_toolbar_name_array[] = { "存储卡", "我的下载", "图书导入", "系统备份",
//			"系统恢复", "清除全部", "在线升级", "快速入门", "关于开卷", "退出系统", "在线升级", "快速入门",
//			"关于开卷", "退出系统", "关于开卷", "退出系统", "关于开卷", "退出系统", "关于开卷", "退出系统" };

	private String menu_toolbar_name_array[] = { "存储卡", "我的下载", "图书导入", "系统备份",
			"系统恢复"};
//	private int menu_toolbar_image_array[] = { R.drawable.icon_sdcard,
//			R.drawable.icon_sdcard, R.drawable.icon_sdcard,
//			R.drawable.icon_sdcard, R.drawable.icon_sdcard,
//			R.drawable.icon_sdcard, R.drawable.icon_sdcard,
//			R.drawable.icon_sdcard, R.drawable.icon_sdcard,
//			R.drawable.icon_sdcard, R.drawable.icon_sdcard,
//			R.drawable.icon_sdcard, R.drawable.icon_sdcard,
//			R.drawable.icon_sdcard, R.drawable.icon_sdcard,
//			R.drawable.icon_sdcard, R.drawable.icon_sdcard,
//			R.drawable.icon_sdcard, R.drawable.icon_sdcard,
//			R.drawable.icon_sdcard };


//	String url = "http://img1.dzwww.com:8080/tupian_pl/20150813/16/7858995348613407436.jpg";
//	//ImageView imageView = (ImageView) findViewById(R.id.imageView);
//Glide.with(context)
//		.load(url)
//    .into(imageView);





	private int menu_toolbar_image_array[] = { R.drawable.kk,
			R.drawable.ll, R.drawable.kk,
			R.drawable.ll, R.drawable.kk
			 };


	Bitmap myBitmap;


	private List<TreeNode> treeNodes = new ArrayList<TreeNode>();

	private Context parentContext;

	private LayoutInflater layoutInflater;

	static public class TreeNode
	{
		public Object parent;
		public List<Object> childs = new ArrayList<Object>();
	}



	public ListViewAdapter3(Context view, int myPaddingLeft)
	{
		parentContext = view;
		this.myPaddingLeft = myPaddingLeft;
//		myBitmap = Glide.with(parentContext)
//
//				.load(yourUrl)
//
//				.asBitmap() //必须
//
//				.centerCrop()
//
//				.into(500, 500)
//
//				.get();
	}

	public ListViewAdapter3(Context view, int myPaddingLeft, Bitmap bitmap)
	{
		parentContext = view;
		this.myPaddingLeft = myPaddingLeft;
		this.myBitmap1 = bitmap;
//		myBitmap = Glide.with(parentContext)
//
//				.load(yourUrl)
//
//				.asBitmap() //必须
//
//				.centerCrop()
//
//				.into(500, 500)
//
//				.get();
	}

	public List<TreeNode> GetTreeNode()
	{
		return treeNodes;
	}

	public void UpdateTreeNode(List<TreeNode> nodes)
	{
		treeNodes = nodes;
	}

	public void RemoveAll()
	{
		treeNodes.clear();
	}

	public Object getChild(int groupPosition, int childPosition)
	{
		return treeNodes.get(groupPosition).childs.get(childPosition);
	}

	public int getChildrenCount(int groupPosition)
	{
		return treeNodes.get(groupPosition).childs.size();
	}

	static public TextView getTextView(Context context)
	{
		AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT, ItemHeight);

		TextView textView = new TextView(context);
		textView.setLayoutParams(lp);
		textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
//		textView.setBackgroundColor(#fff);
		return textView;
	}

	/**
	 * 可自定义ExpandableListView
	 */
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent)
	{
		if (convertView == null)
		{
			layoutInflater = (LayoutInflater) parentContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = layoutInflater.inflate(R.layout.view, null);
			toolbarGrid = (MyGridView) convertView
					.findViewById(R.id.GridView_toolbar);
			toolbarGrid.setNumColumns(4);// 设置每行列数
			toolbarGrid.setGravity(Gravity.CENTER);// 位置居中
			toolbarGrid.setHorizontalSpacing(10);// 水平间隔
			toolbarGrid.setAdapter(getMenuAdapter(menu_toolbar_name_array,
					menu_toolbar_image_array));// 设置菜单Adapter
			toolbarGrid.setOnItemClickListener(this);


            toolbarGrid.setSelector(new ColorDrawable(Color.TRANSPARENT));//设置点击事件的背景颜色
		}
		return convertView;
	}

	/**
	 * 可自定义list
	 */
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent)
	{
		TextView textView = getTextView(this.parentContext);
		textView.setText(getGroup(groupPosition).toString());
		textView.setPadding(myPaddingLeft + PaddingLeft, 0, 0, 0);
//		textView.setText("hello");
		textView.setBackgroundColor(Color.WHITE);//导航背景颜色的设置
		return textView;
	}

	public long getChildId(int groupPosition, int childPosition)
	{
		return childPosition;
	}

	public Object getGroup(int groupPosition)
	{
		return treeNodes.get(groupPosition).parent;
	}

	public int getGroupCount()
	{
		return treeNodes.size();
	}

	public long getGroupId(int groupPosition)
	{
		return groupPosition;
	}

	public boolean isChildSelectable(int groupPosition, int childPosition)
	{
		return true;
	}

	public boolean hasStableIds()
	{
		return true;
	}

	/**
	 * 构造菜单Adapter
	 * 
	 * @param menuNameArray
	 *            名称
	 * @param imageResourceArray
	 *            图片
	 * @return SimpleAdapter
	 */
	private SimpleAdapter getMenuAdapter(String[] menuNameArray,
			int[] imageResourceArray)
	{
		ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		//getMyBitmap();
		for (int i = 0; i < menuNameArray.length; i++)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemImage", imageResourceArray[i]);
//			map.put("itemImage", myBitmap1);

			map.put("itemText", menuNameArray[i]);
			data.add(map);
		}
		SimpleAdapter simperAdapter = new SimpleAdapter(parentContext, data,
				R.layout.item_menu, new String[] { "itemImage", "itemText" },
				new int[] { R.id.item_image, R.id.item_text });
		return simperAdapter;
	}





//	private Bitmap getMyBitmap(){
//
//			try {
//				myBitmap1 = Glide.with(parentContext)
//						.load(url)
//						.asBitmap() //必须
//						.centerCrop()
//						.into(500, 500)
//						.get();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			} catch (ExecutionException e) {
//				e.printStackTrace();
//			}
//		return myBitmap1;
//	}



	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{
		Toast.makeText(parentContext, "当前选中的是:" + id, Toast.LENGTH_SHORT)
				.show();

		Intent intent=new Intent(parentContext,CommonScanActivity.class);
		intent.putExtra(Constant.REQUEST_SCAN_MODE,Constant.REQUEST_SCAN_MODE_QRCODE_MODE);
			parentContext.startActivity(intent);



	}
}