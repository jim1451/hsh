package com.silence.commonframe.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.silence.commonframe.adapter.FindAdapter;
import com.silence.commonframe.adapter.FireAlarmAdapter;
import com.silence.commonframe.adapter.SitedetailAdapter1;
import com.silence.commonframe.bean.JsonBean;
import com.silence.commonframe.model.FireAlarmModel;
import com.silence.commonframe.utils.Data;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class HistoryActivity extends AppCompatActivity {

    @Bind(R.id.my_recycler_view)
    RecyclerView myRecyclerView;
    private JsonBean jsonBean;
    private List<HashMap<String, String>> listmap = new ArrayList<HashMap<String, String>>();
    private FireAlarmAdapter mAdapter;
    private ArrayList<String> listIdKnown = new ArrayList<String>();//我知道的ID
    private  String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
       // getData();
        getData1();
        initWindows();
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












    private void getData1(){

        String  url =   ApiService.httpUrl1 +"/app/getFireAlarm?page=1&pageSize=11";
        OkGo.get(url)
                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheGetKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .headers("token", Data.getToken())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                    //    System.out.println("jj"+s);
                 FireAlarmModel fireAlarmModel = new Gson().fromJson(s, FireAlarmModel.class);

                        for (int i = 0; i < fireAlarmModel.getData().size(); i++) {
                            HashMap<String, String> hashMap = new HashMap<>();
//                            String location = jsonBean.getObject().get(i).getLocation();
                            String  deviceName = fireAlarmModel.getData().get(i).getDeviceName();
                            String  deployment = fireAlarmModel.getData().get(i).getDeployment();
                            String  troubleType = fireAlarmModel.getData().get(i).getTroubleType();
                            String  deviceId = fireAlarmModel.getData().get(i).getDeviceId();
                            String  regionName = fireAlarmModel.getData().get(i).getRegionName();
                            String  gmtCreate = fireAlarmModel.getData().get(i).getGmtCreate();
                            String  location = fireAlarmModel.getData().get(i).getLocation();
                            String  ifRead = fireAlarmModel.getData().get(i).getIfRead();
                            String  id = fireAlarmModel.getData().get(i).getId();



                            hashMap.put("deviceName", deviceName);
                            hashMap.put("deployment", deployment);
                            hashMap.put("troubleType", troubleType);
                            hashMap.put("deviceId", deviceId);
                            hashMap.put("regionName", regionName);
                            hashMap.put("gmtCreate", gmtCreate);
                            hashMap.put("location", location);
                            hashMap.put("ifRead", ifRead);
                            hashMap.put("id", id);


                            //   list.add(place);

                            listmap.add(hashMap);
                            listIdKnown.add(id);
                        }
                        Data.setListIdKnowid(listIdKnown);

                        mAdapter = new FireAlarmAdapter(HistoryActivity.this, listmap);
                        myRecyclerView.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));
                        myRecyclerView.setAdapter(mAdapter);

                        mAdapter.setOnItemClickListener(new FireAlarmAdapter.MyItemClickListener() {
                            @Override
                            public void onItemClick(View view, int postion) {
//                                        Toast.makeText(HistoryActivity.this, postion + "", Toast.LENGTH_SHORT).show();


//                Intent intent = new Intent(getContext(),NewsdetailActivity.class);
//                startActivity(intent);
                            }
                        });


                        mAdapter.buttonSetOnclick(new FireAlarmAdapter.ButtonInterface() {
                            @Override
                            public void onclick(View view, int position) {

                                //  Data.setListid(listid);  //场所id1
//                                siteId =    Data.getListid().get(position);
//                                //    Toast.makeText(FireMessageActivity.this, "点击条目上的按钮"+  Data.getListid().get(position), Toast.LENGTH_SHORT).show();
//                                //    Toast.makeText(FireMessageActivity.this, "点击条目上的按钮"+  Data.getCb_photo(), Toast.LENGTH_SHORT).show();
//                                getByOkGo();


                                    id =  Data.getListIdKnowid().get(position);
                                getKnowMessage();

                            }
                        });







                    }

                });




    }

    private void getKnowMessage(){

        String  url =   ApiService.httpUrl1 +"/app/readFireAlarm?processId="+id;
        OkGo.get(url)
                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheGetKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .headers("token", Data.getToken())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                    //    siteId =    Data.getListid().get(position);

                    Toast.makeText(HistoryActivity.this,"信息提交成功！"+s,Toast.LENGTH_SHORT).show();



                    }

                });








    }











    private void getData() {

        HashMap<String, String> params = new HashMap<>();
        params.put("userid", "41");
        params.put("page", "1");
//    params.put("phone", "13333311338");


        final JSONObject jsonObject = new JSONObject(params);
        String s = Data.getToken();
        Log.v("Tag", s);

        String str = jsonObject.toString();

        OkGo.post(ApiService.httpUrl + "gettroubledevice")
                .tag(this)
                .cacheKey("cachePostRegister11")
                .cacheMode(CacheMode.DEFAULT)
                .upJson(jsonObject.toString())

                .headers("token", Data.getToken())
//                .params("format", "json")
//                .params("albumId", "Lqfme5hSolM")
//                .params("pageNo", "1")
//                .params("pageSize", "2")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        //  mTextView2.setText(s);
                        // System.out.println("sss"+response);

//                        LoginModel jsonRootBean = new Gson().fromJson(s, LoginModel.class);
//                        Data.setName(jsonRootBean.getObject().getUsername().toString());
//                        System.out.println("rst:" + jsonRootBean.getObject().getUsername().toString());


                        jsonBean = new Gson().fromJson(s, JsonBean.class);

                        for (int i = 0; i < jsonBean.getObject().size(); i++) {
                            HashMap<String, String> hashMap = new HashMap<>();
                            String location = jsonBean.getObject().get(i).getLocation();
                            String id = jsonBean.getObject().get(i).getDeviceid();
                            //     String place1 = siteDevice.getObject().get(i).getRegionalism();

                            hashMap.put("location", location);
                            hashMap.put("id", id);


                            //   list.add(place);

                            listmap.add(hashMap);
                        }


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


//                                MyAdapterSlide    adapter = new MyAdapterSlide(getContext(), listmap);


                                mAdapter = new FireAlarmAdapter(HistoryActivity.this, listmap);
                                myRecyclerView.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));
                                myRecyclerView.setAdapter(mAdapter);

                                mAdapter.setOnItemClickListener(new FireAlarmAdapter.MyItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int postion) {
//                                        Toast.makeText(HistoryActivity.this, postion + "", Toast.LENGTH_SHORT).show();


//                Intent intent = new Intent(getContext(),NewsdetailActivity.class);
//                startActivity(intent);
                                    }
                                });


                            }
                        });
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        //  mTextView2.setText(e.getMessage());
                        Toast.makeText(HistoryActivity.this, "NOsuccess " + e, Toast.LENGTH_SHORT).show();
                        System.out.println("sss" + e);
                    }
                });


    }


    public void myclick(View view) {
        finish();
    }
}
