package com.silence.commonframe.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.silence.commonframe.ApiService;
import com.silence.commonframe.R;
import com.silence.commonframe.adapter.FindAdapter;
import com.silence.commonframe.bean.JsonBean;
import com.silence.commonframe.model.TroubleDeviceModel;
import com.silence.commonframe.utils.Data;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class SearchResultActivity extends AppCompatActivity {

    private JsonBean jsonBean;
    private List<HashMap<String, String>> listmap = new ArrayList<HashMap<String, String>>() ;
    private FindAdapter mAdapter;


    @Bind(R.id.textView3)
    TextView textView3;
//    @Bind(R.id.linearLayout3)
//    LinearLayout linearLayout3;
    @Bind(R.id.my_recycler_view)
    RecyclerView myRecyclerView;

    private String  type1;
    private String  type2;
    private String  type3;
    private String  type;
    private String  timestart;
    private String  overtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Intent intent = getIntent();
        type1   =  intent.getStringExtra("type1");
        type2   =  intent.getStringExtra("type2");
        type3   =  intent.getStringExtra("type3");
        timestart   =  intent.getStringExtra("timestart");
        overtime   =  intent.getStringExtra("overtime");

        ButterKnife.bind(this);
        initWindows();
        getType();//获取警情的类型
        getData1();

    }

    public void click(View view) {
        finish();
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



    private void getData() {

        HashMap<String, String> params = new HashMap<>();
        params.put("userid", "41");
        params.put("page", "1");
//    params.put("phone", "13333311338");


        final JSONObject jsonObject = new JSONObject(params);
        String s = Data.getToken();
        Log.v("Tag",s);

        String str = jsonObject.toString();

        OkGo.post(ApiService.httpUrl+"gettroubledevice")
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


                        jsonBean = new Gson().fromJson(s,JsonBean.class);

                        for (int i = 0;i<jsonBean.getObject().size();i++){
                            HashMap<String,String>  hashMap = new HashMap<>();
                            String location = jsonBean.getObject().get(i).getLocation();
                            String id = jsonBean.getObject().get(i).getDeviceid();
                            //     String place1 = siteDevice.getObject().get(i).getRegionalism();

                            hashMap.put("location",location);
                            hashMap.put("id",id);


                            //   list.add(place);

                            listmap.add(hashMap);
                        }





                      runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


//                                MyAdapterSlide    adapter = new MyAdapterSlide(getContext(), listmap);


                                mAdapter = new FindAdapter(SearchResultActivity.this, listmap);
                                myRecyclerView.setLayoutManager(new LinearLayoutManager(SearchResultActivity.this));
                                myRecyclerView.setAdapter(mAdapter);

                                mAdapter.setOnItemClickListener(new FindAdapter.MyItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int postion) {
                                        Toast.makeText(SearchResultActivity.this, postion+"", Toast.LENGTH_SHORT).show();


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
                        Toast.makeText(SearchResultActivity.this,"NOsuccess "+e,Toast.LENGTH_SHORT).show();
                        System.out.println("sss"+e);
                    }
                });




    }




    private void getData1() {

//        HashMap<String, Integer> params1 = new HashMap<>();
//        params1.put("page", 1);




        HashMap<String, Object> params = new HashMap<>();
        params.put("pagesize",100);
        params.put("page", 1);
        params.put("type", type);
        params.put("fromTime", timestart);
        params.put("toTime", overtime);


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

                            hashMap.put("deviceId",deviceId);
                            hashMap.put("deployment",deployment);
                            hashMap.put("location",location);
                            hashMap.put("regionName",regionName);
                            hashMap.put("deviceLocation",deviceLocation);
                            hashMap.put("gmtCreate",gmtCreate);
                            if (TextUtils.isEmpty(recheckId)){
                                recheckId = "11";
                            }
                            hashMap.put("recheckId",recheckId);
                            listmap.add(hashMap);
                        }


                     runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAdapter = new FindAdapter(SearchResultActivity.this, listmap);
                                myRecyclerView.setLayoutManager(new LinearLayoutManager(SearchResultActivity.this));
                                myRecyclerView.setAdapter(mAdapter);


//                                mAdapter.setOnItemClickListener(new FindAdapter.MyItemClickListener() {
//                                    @Override
//                                    public void onItemClick(View view, int postion) {
//                                        Intent intent = new Intent(SearchResultActivity.this, NewsdetailActivity.class);
//                                        Bundle bundle = new Bundle();
//                                        // List<String>  list = new ArrayList<>();
//                                        bundle.putSerializable("listmap", (Serializable) listmap.get(postion));
//                                        intent.putExtras(bundle);
//
//                                        startActivity(intent);
//                                    }
//                                });


                            }
                        });

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        //  mTextView2.setText(e.getMessage());
                    //    Toast.makeText(getContext(),"NOsuccess "+e,Toast.LENGTH_SHORT).show();
                        System.out.println("sss"+e);
                    }
                });

    }


    private  void  getType(){
        if ((type1.equals("ok"))&&(type2.equals("ok"))&&(type3.equals("ok"))){
            type = "";
        }
        if ((type1.equals("1"))&&(type2.equals("ok"))&&(type3.equals("ok"))){
            type = "1";
        }
        if ((type1.equals("ok"))&&(type2.equals("1"))&&(type3.equals("ok"))){
            type = "0";
        }
        if ((type1.equals("ok"))&&(type2.equals("ok"))&&(type3.equals("1"))){
            type = "2";
        }


        if ((type1.equals("1"))&&(type2.equals("1"))&&(type3.equals("ok"))){
            type = "1,0";
        }
        if ((type1.equals("1"))&&(type2.equals("1"))&&(type3.equals("1"))){
            type = "1,0,2";
        }


    }


}
