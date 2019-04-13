package com.silence.commonframe.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.silence.commonframe.ApiService;
import com.silence.commonframe.R;
import com.silence.commonframe.adapter.DeviceDetailRecViewAdapter;
import com.silence.commonframe.adapter.MyAdapterSlideSite1;
import com.silence.commonframe.model.DeviceDataChangedModel;
import com.silence.commonframe.utils.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class DevicedetailActivity extends AppCompatActivity {


    @Bind(R.id.imageView4)
    ImageView imageView4;
    @Bind(R.id.tv_id)
    TextView tvId;
    @Bind(R.id.textView9)
    TextView textView9;
    @Bind(R.id.tv_place)
    TextView tvPlace;
    @Bind(R.id.tv_place1)
    TextView tvPlace1;
    @Bind(R.id.recView)
    RecyclerView recView;
    private String deviceid = "0000000000000051";
    private List<HashMap<String, String>> listmap = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devicedetail);
        ButterKnife.bind(this);

        initWindows();

//        recView.setLayoutManager(new LinearLayoutManager(this));
//
//        DeviceDetailRecViewAdapter adapter1 = new DeviceDetailRecViewAdapter(this, Data.getList());
//        //控件綁定適配器
//        recView.setAdapter(adapter1);


        getDeviceDataChanged();
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

    private void getDeviceDataChanged() {

        //  http://www.hsh-iot.com/hsh-app/app/getDevice?page=1&pagesize=11&siteid=6204a38e285746cda8b329165f559126
        //  String  url =   ApiService.httpUrl1 +"/app/getDevice?page="+page+"&pagesize=10&siteid="+ Data.getListid().get(position1);
        String url = ApiService.httpUrl1 + "/app/getDeviceDataChanged?page=1&pagesize=10&devideid=" + deviceid;
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

                        DeviceDataChangedModel deviceDataChangedModel = new Gson().fromJson(s, DeviceDataChangedModel.class);
                        List<DeviceDataChangedModel.DataBean> listBeans = deviceDataChangedModel.getData();
                        System.out.println("ss" + listBeans);
                    //    if (listBeans.size() != 0) {
                            for (int i = 0; i < listBeans.size(); i++) {


                                HashMap<String, String> hashMap = new HashMap<>();
                                String inputtime = listBeans.get(i).getInputtime();
                                String deviceStatus = listBeans.get(i).getDeviceStatus() + "";
                                String signalStrength = listBeans.get(i).getSignalStrength() + "";
                                String batteryVoltage = listBeans.get(i).getBatteryVoltage() + "";
                                String mcuTemp = listBeans.get(i).getMcuTemp() + "";


                                hashMap.put("inputtime", inputtime);
                                hashMap.put("deviceStatus", deviceStatus);
                                hashMap.put("signalStrength", signalStrength);
                                hashMap.put("batteryVoltage", batteryVoltage);
                                hashMap.put("mcuTemp", mcuTemp);

                                listmap.add(hashMap);
                            }


                            runOnUiThread(new Runnable() {


                                @Override
                                public void run() {

                                    // number = listBeans.size();
//                                    imageView.setVisibility(View.GONE);
//                                    bt.setVisibility(View.GONE);
//                                    recyclerView.setVisibility(View.VISIBLE);


//                                    adapter = new MyAdapterSlide(getContext(), listmap);
//                                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//                                    recyclerView.setAdapter(adapter);


//                                    adapter1 = new MyAdapterSlideSite(Devicedetail3Activity.this, listmap);
//                                    recyclerView.setLayoutManager(new LinearLayoutManager(Devicedetail3Activity.this));
//                                    recyclerView.setAdapter(adapter1);


                            //        recView.setLayoutManager(new LinearLayoutManager(this));
//
//        DeviceDetailRecViewAdapter adapter1 = new DeviceDetailRecViewAdapter(this, Data.getList());
//        //控件綁定適配器
//        recView.setAdapter(adapter1);

                                    recView.setLayoutManager(new LinearLayoutManager(DevicedetailActivity.this));
                               //      DeviceDetailRecViewAdapter adapter1 = new DeviceDetailRecViewAdapter(DevicedetailActivity.this, listmap);
                                    MyAdapterSlideSite1 adapter1 = new MyAdapterSlideSite1(DevicedetailActivity.this, listmap);

                                    recView.setAdapter(adapter1);
                                }
                            });


                    //    }


                    }

                });


    }


    public void myclick(View view) {
        finish();
    }
}
