package com.silence.commonframe.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.silence.commonframe.ApiService;
import com.silence.commonframe.R;
import com.silence.commonframe.activity.AddlinkmanActivity;
import com.silence.commonframe.activity.NewsdetailActivity;
import com.silence.commonframe.activity.SearchMessageActivity;
import com.silence.commonframe.adapter.FindAdapter;
import com.silence.commonframe.adapter.MyAdapterSlide;
import com.silence.commonframe.adapter.SimpleAdapter;
import com.silence.commonframe.bean.JsonBean;
import com.silence.commonframe.bean.Person1;
import com.silence.commonframe.model.Device1;
import com.silence.commonframe.model.TroubleDeviceModel;
import com.silence.commonframe.utils.Data;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Silence on 2016/4/5.
 */
public class FindFragment1 extends Fragment {
    RecyclerView recyclerView;
 //   SimpleAdapter adapter;
 //   MyAdapterSlide    adapter;
    List<Person1> personList = new ArrayList<Person1>();
    XRefreshView xRefreshView;
    int lastVisibleItem = 0;
    //    GridLayoutManager layoutManager;
    LinearLayoutManager layoutManager;
    @Bind(R.id.my_recycler_view)
    RecyclerView myRecyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind({R.id.imageView4})
    ImageView imageView;


    private boolean isBottom = false;
    private int mLoadCount = 0;
    private View view;

    private boolean isList = true;//false 为grid布局


    private FindAdapter mAdapter;
    private List<JsonBean> mData;
    private List list;
   private List<HashMap<String, String>> listmap = new ArrayList<HashMap<String, String>>() ;
   private long time;



    private JsonBean jsonBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_find, container, false);
//        TextView textView = (TextView) view.findViewById(R.id.text_content);
//        textView.setText(getString(R.string.text_tab_find));


        ButterKnife.bind(this, view);

    //    mAdapter = new FindAdapter(getContext(),mData);
        mSwipeRefreshLayout.setColorSchemeColors( Color.GRAY);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        List<String>  lists=new ArrayList<String>();
//                        for (int i = 25; i <30; i++) {
//                            lists.add("下拉刷新数据"+i);
//                        }
//                        // adapter.addHeaderData(lists);
//                        mSwipeRefreshLayout.setRefreshing(false);
//                    }
//                },1500);

                Toast.makeText(getContext(),"数据添加完毕",Toast.LENGTH_SHORT).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),SearchMessageActivity.class);
                startActivity(intent);
            }
        });

        //初始化数据
      //  initData();
        //初始化布局
    //    initView();


      //  getData();


       // getTroubleDevice();
        getData();
        return view;
    }


    private void initData() {
        mData = new ArrayList<>();
//        for (int i = 1; i < 31; i++) {
//            JsonBean jsonBean = new JsonBean("第"+i +"张订单");
//            mData.add(jsonBean);
//        }
    }









    private void getData() {

//        HashMap<String, Integer> params1 = new HashMap<>();
//        params1.put("page", 1);

        HashMap<String, Object> params = new HashMap<>();
        params.put("pagesize",25);
        params.put("page", 1);
        params.put("type", "1");


        final JSONObject jsonObject = new JSONObject(params);




        String s = Data.getToken();
        Log.v("Tag",s);

        String str = jsonObject.toString();

        OkGo.post(ApiService.httpUrl1+"/app/getTroubleDevice")
                .tag(this)
                .cacheKey("cachePostRegister11")
                .cacheMode(CacheMode.DEFAULT)
                .upJson(jsonObject.toString())

                .headers("token", Data.getToken())

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {



                      //  Toast.makeText(getContext(), "ff" + s, Toast.LENGTH_SHORT).show();

                        TroubleDeviceModel troubleDeviceModel = new TroubleDeviceModel();
                        troubleDeviceModel = new Gson().fromJson(s,TroubleDeviceModel.class);
                        List<TroubleDeviceModel.DataBean>  list= troubleDeviceModel.getData();

                        for (int i = 0;i<list.size();i++){
                            HashMap<String,String>  hashMap = new HashMap<>();
                            String deviceId = list.get(i).getDeviceId();//设备id
                            String deployment = list.get(i).getDeployment();//区域名称
                            String location = list.get(i).getLocation();//详细地址
                            String regionName = list.get(i).getRegionName();//位置
                            String deviceLocation = list.get(i).getRegionName();//设备位置
                            String gmtCreate = list.get(i).getGmtCreate();//设备位置
                            String recheckId = list.get(i).getRecheckId();//有无复核
                            System.out.println("kk1"+recheckId);
                            String  troubleType = list.get(i).getTroubleType();

                            hashMap.put("deviceId",deviceId);
                            hashMap.put("deployment",deployment);
                            hashMap.put("location",location);
                            hashMap.put("regionName",regionName);
                            hashMap.put("deviceLocation",deviceLocation);
                            hashMap.put("gmtCreate",gmtCreate);
                            hashMap.put("troubleType",troubleType);


                            if (TextUtils.isEmpty(recheckId)){
                                recheckId = "11";
                            }
                            hashMap.put("recheckId",recheckId);
                            listmap.add(hashMap);
                        }


                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAdapter = new FindAdapter(getContext(), listmap);
                                myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                myRecyclerView.setAdapter(mAdapter);


                                mAdapter.setOnItemClickListener(new FindAdapter.MyItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int postion) {
                                        Intent intent = new Intent(getContext(), NewsdetailActivity.class);
                                        Bundle bundle = new Bundle();
                                        // List<String>  list = new ArrayList<>();
                                        bundle.putSerializable("listmap", (Serializable) listmap.get(postion));
                                        intent.putExtras(bundle);

                                        startActivity(intent);
                                    }
                                });


                            }
                        });
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        //  mTextView2.setText(e.getMessage());
                        Toast.makeText(getContext(),"NOsuccess "+e,Toast.LENGTH_SHORT).show();
                        System.out.println("sss"+e);
                    }
                });




    }






private   String getTime(){
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(time);

    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    int hour = calendar.get(Calendar.HOUR_OF_DAY);//24小时制
//  int hour = calendar.get(Calendar.HOUR);//12小时制
    int minute = calendar.get(Calendar.MINUTE);
    int second = calendar.get(Calendar.SECOND);

    System.out.println(year + "-" + (month + 1) + "-" + day + " "
            + hour + ":" + minute + ":" + second);
        String time = year + "-" + (month + 1) + "-" + day + " "
                + hour + ":" + minute + ":" + second;

        return time;
}








    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    private void getTroubleDevice() {
        //http://www.hsh-iot.com/hsh-app/app/deleteDevice?ids=00000000006
        String url = ApiService.httpUrl1 + "/app/getTroubleDevice?page=1&pagesize=11";
        OkGo.get(url)
                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheGetKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .headers("token", Data.getToken())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {


                        Toast.makeText(getContext(), "ff" + s, Toast.LENGTH_SHORT).show();

                        TroubleDeviceModel troubleDeviceModel = new TroubleDeviceModel();
                        troubleDeviceModel = new Gson().fromJson(s,TroubleDeviceModel.class);
                        List<TroubleDeviceModel.DataBean>  list= troubleDeviceModel.getData();

                        for (int i = 0;i<list.size();i++){
                            HashMap<String,String>  hashMap = new HashMap<>();
                            String deviceId = list.get(i).getDeviceId();//设备id
                            String deployment = list.get(i).getDeployment();//区域名称
                            String location = list.get(i).getLocation();//详细地址
                            String regionName = list.get(i).getRegionName();//位置
                            String deviceLocation = list.get(i).getRegionName();//设备位置
                            String gmtCreate = list.get(i).getGmtCreate();//设备位置

                            hashMap.put("deviceId",deviceId);
                            hashMap.put("deployment",deployment);
                            hashMap.put("location",location);
                            hashMap.put("regionName",regionName);
                            hashMap.put("deviceLocation",deviceLocation);
                            hashMap.put("gmtCreate",gmtCreate);

                            listmap.add(hashMap);
                        }


                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAdapter = new FindAdapter(getContext(), listmap);
                                myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                myRecyclerView.setAdapter(mAdapter);


                                mAdapter.setOnItemClickListener(new FindAdapter.MyItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int postion) {
                                        Intent intent = new Intent(getContext(), NewsdetailActivity.class);
                                        Bundle bundle = new Bundle();
                                        // List<String>  list = new ArrayList<>();
                                        bundle.putSerializable("listmap", (Serializable) listmap.get(postion));
                                        intent.putExtras(bundle);

                                        startActivity(intent);
                                    }
                                });







                            }
                        });


                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });


    }







}
