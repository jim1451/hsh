package com.silence.commonframe.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.silence.commonframe.ApiService;
import com.silence.commonframe.R;
import com.silence.commonframe.adapter.MyAdapterSlide;
import com.silence.commonframe.adapter.MyAdapterSlideSite;
import com.silence.commonframe.model.Device;
import com.silence.commonframe.model.Device1;
import com.silence.commonframe.utils.Data;
import com.silence.commonframe.utils.SlideRecyclerView;
import com.silence.commonframe.utils.SlideRecyclerViewDevice;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class Devicedetail3Activity extends AppCompatActivity {

    private SlideRecyclerViewDevice recyclerView;

    private List<HashMap<String, String>> listmap = new ArrayList<HashMap<String, String>>();
    private List<String>  list = new ArrayList<>();
    private List<String>  list1 = new ArrayList<>();
    private  MyAdapterSlide  adapter;
    private MyAdapterSlideSite adapter1;
    private  String deviceid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devicedetail3);

        recyclerView = (SlideRecyclerViewDevice) this.findViewById(R.id.id_item_remove_recyclerview);


        initWindows();


        Intent intent = getIntent();
        deviceid = intent.getStringExtra("deviceid");

      //  getData();
        getDevice();

        recyclerView.setOnItemClickListener(new SlideRecyclerViewDevice.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //   Toast.makeText(MainActivity.this, "** " + mList.get(position) + " **", Toast.LENGTH_SHORT).show();






            //    Toast.makeText(Devicedetail3Activity.this, "hello", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Devicedetail3Activity.this,DevicedetailActivity.class);
                intent.putExtra("id",deviceid);
                startActivity(intent);


            }

            @Override
            public void onDeleteClick(int position) {
              //  adapter.removeItem(position);
                adapter1.removeItem(position);
                String id =  list.get(position);
                delDevices(id);




            }

            @Override
            public void onNavieteClick(int position) {
                Toast.makeText(Devicedetail3Activity.this, "详细item！", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initWindows() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.BLACK);
        }
    }

    private void  getDevice(){

        //  http://www.hsh-iot.com/hsh-app/app/getDevice?page=1&pagesize=11&siteid=6204a38e285746cda8b329165f559126
        //  String  url =   ApiService.httpUrl1 +"/app/getDevice?page="+page+"&pagesize=10&siteid="+ Data.getListid().get(position1);
        String  url =   ApiService.httpUrl1 +"/app/getDevice?page=1&pagesize=65&siteid="+ deviceid;
        OkGo.get(url)
                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheGetKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .headers("token", Data.getToken())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        //   mTextView2.setText(s);
                        //   System.out.println("sss"+s);
                        //    Toast.makeText(getContext(),"s"+s,Toast.LENGTH_SHORT).show();

                        Device device = new Gson().fromJson(s,Device.class);
                        List<Device.DataBean.DataListBean>  listBeans =  device.getData().getDataList();
                        System.out.println("ss"+listBeans);
                        if (listBeans.size() != 0 ){
                            for (int i = 0;i<listBeans.size();i++){
                                HashMap<String,String>  hashMap = new HashMap<>();
                                String name = listBeans.get(i).getDeviceName();
                                String id = listBeans.get(i).getDeviceId();
                                String deviceLocation= listBeans.get(i).getDeviceLocation();


                              //  String deviceLocation = listBeans.get(i).getDeviceLocation();
//                                String id1 = String.valueOf(siteDevice.getObject().get(i).getId());
//                                String dLocation = siteDevice.getObject().get(i).getDLocation();
//                                String siteid = String.valueOf(siteDevice.getObject().get(i).getId());





                                //     String place1 = siteDevice.getObject().get(i).getRegionalism();
                                hashMap.put("name",name);
                                hashMap.put("id",id);
                                hashMap.put("deviceLocation",deviceLocation);

                                list.add(id);

                                listmap.add(hashMap);
                            }


                            runOnUiThread(new Runnable() {


                                @Override
                                public void run() {




                                    adapter1 = new MyAdapterSlideSite(Devicedetail3Activity.this, listmap);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(Devicedetail3Activity.this));
                                    recyclerView.setAdapter(adapter1);
                                }
                            });


                        }




                    }

                });






    }






    private void delDevices(String id) {
        String  url =   ApiService.httpUrl1 +"/app/deleteDevice?ids="+ id;
        OkGo.get(url)
                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheGetKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .headers("token", Data.getToken())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        //   mTextView2.setText(s);
                        //   System.out.println("sss"+s);
                        //    Toast.makeText(getContext(),"s"+s,Toast.LENGTH_SHORT).show();

//                        Device device = new Gson().fromJson(s,Device.class);
//                        List<Device.DataBean.DataListBean>  listBeans =  device.getData().getDataList();
//                        System.out.println("ss"+listBeans);


                        try {
                            JSONObject object = new JSONObject(s);
                            String code = object.getString("code");
                            if (code.equals("0")){
                                Toast.makeText(Devicedetail3Activity.this,"恭喜，删除设备成功！",Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                });





    }










    public void myclick(View view) {
        finish();
    }
}
