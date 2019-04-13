package com.silence.commonframe.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
import com.silence.commonframe.adapter.MyAdapter;
import com.silence.commonframe.adapter.SitedetailAdapter;
import com.silence.commonframe.adapter.SitedetailAdapter1;
import com.silence.commonframe.model.SiteDevice;
import com.silence.commonframe.model.SiteModel;
import com.silence.commonframe.utils.Data;
import com.silence.commonframe.utils.DividerItemDecoration;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class FireMessageActivity extends AppCompatActivity {


    @Bind(R.id.my_recycler_view)
    RecyclerView myRecyclerView;
    private List<HashMap<String, String>> listmap = new ArrayList<HashMap<String, String>>();

    private ArrayList<String> list = new ArrayList<String>(); //数据源
    private ArrayList<String>  listId  = new ArrayList<String>(); //场所id
    private SitedetailAdapter1 myAdapter1;

    private String siteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_message);
        ButterKnife.bind(this);
        getdata1();
        initWindows();
    }

    public void myclick(View view) {
        finish();
    }



    private void getdata1() {
        String  url =   ApiService.httpUrl1 +"/app/getSite?page=1&pagesize=10";
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

                        SiteModel siteModel = new Gson().fromJson(s,SiteModel.class);
                        List<SiteModel.DataBean.DataListBean>  listBeans =  siteModel.getData().getDataList();
                        System.out.println("ss"+listBeans);





//                        if(verifyModel.getCode() == 0){
//                            Toast.makeText(RegisterActivity.this,"验证码已经发送成功！",Toast.LENGTH_SHORT).show();
//                        }

                        for (int i = 0;i<listBeans.size();i++){
                            HashMap<String,String>  hashMap = new HashMap<>();
                            String RegionName = listBeans.get(i).getRegionName();
                            String Location = listBeans.get(i).getLocation();
                            String id = listBeans.get(i).getId();
                            String  deployment  = listBeans.get(i).getDeployment();
                            String  isTel  = listBeans.get(i).getIsTel();
                            String  isMsg  = listBeans.get(i).getIsMsg();


                            hashMap.put("RegionName",RegionName);
                            hashMap.put("Location",Location);
                            hashMap.put("id",id);
                            hashMap.put("deployment",deployment);
                            hashMap.put("isTel",isTel);
                            hashMap.put("isMsg",isMsg);


//                            listid.add(id);
                            list.add(deployment);

                            listmap.add(hashMap);
                        }

                        Data.setListmap(listmap);

        //                Data.setList(list);


     //                   Data.setListid(listid);  //场所id1

                runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                myAdapter1 = new MyAdapter( listmap,getContext());
//                                listView.setAdapter(myAdapter1);


                                myAdapter1 = new SitedetailAdapter1(FireMessageActivity.this, listmap);
                                myRecyclerView.setLayoutManager(new LinearLayoutManager(FireMessageActivity.this));
                                myRecyclerView.setAdapter(myAdapter1);
                                //设置分割线
                                myRecyclerView.addItemDecoration(new DividerItemDecoration(FireMessageActivity.this, DividerItemDecoration.VERTICAL_LIST));




                                //RecyclerView点击事件
                                myAdapter1.setOnItemClickLitener(new SitedetailAdapter1.OnItemClickLitener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        //  Toast.makeText(SitedetailActivity.this, position + "click", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(FireMessageActivity.this, SiteManagement.class);
                                        startActivity(intent);


                                    }

                                    @Override
                                    public void onItemLongClick(View view, final int position) {
                                        //这里长按定义的是删除
                                        AlertDialog.Builder dialog = new AlertDialog.Builder(FireMessageActivity.this);
                                        dialog.setTitle("是否删除");
                                        dialog.setMessage("确定吗？");
                                        dialog.setCancelable(false);
                                        dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {

                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                myAdapter1.removeData(position);
                                            }
                                        });

                                        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        });
                                        dialog.show();

                                        Toast.makeText(FireMessageActivity.this, position + "Long click", Toast.LENGTH_SHORT).show();
                                    }
                                });


                                myAdapter1.buttonSetOnclick(new SitedetailAdapter1.ButtonInterface() {
                                    @Override
                                    public void onclick(View view, int position) {

                                      //  Data.setListid(listid);  //场所id1
                                    siteId =    Data.getListid().get(position);
                                    //    Toast.makeText(FireMessageActivity.this, "点击条目上的按钮"+  Data.getListid().get(position), Toast.LENGTH_SHORT).show();
                                    //    Toast.makeText(FireMessageActivity.this, "点击条目上的按钮"+  Data.getCb_photo(), Toast.LENGTH_SHORT).show();
                                        getByOkGo();
                                    }
                                });


                            }
                        });





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












    private void getByOkGo() {
//        OkGo.get("http://wthrcdn.etouch.cn/weather_mini?citykey=101180101")  http://www.hsh-iot.com/hsh-app/app/getSite?page=1&pagesize=10                          // 请求方式和请求url

        //http://www.hsh-iot.com/hsh-app/app/updatePhonePush?siteId=1223&isTel=1&isMsg=0
        String  url =   ApiService.httpUrl1 +"/app/updatePhonePush?siteId="+siteId+"&isTel="+Data.getCb_photo()+"&isMsg="+Data.getCb_message();
        OkGo.get(url)
                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheGetKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .headers("token", Data.getToken())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Toast.makeText(FireMessageActivity.this,"设置成功！",Toast.LENGTH_SHORT).show();
                        //   mTextView2.setText(s);
                        //   System.out.println("sss"+s);
                        //    Toast.makeText(getContext(),"s"+s,Toast.LENGTH_SHORT).show();

//                        SiteModel siteModel = new Gson().fromJson(s,SiteModel.class);
//                        List<SiteModel.DataBean.DataListBean>  listBeans =  siteModel.getData().getDataList();
//                        System.out.println("ss"+listBeans);


//                        for (int i = 0;i<listBeans.size();i++){
//                            HashMap<String,String>  hashMap = new HashMap<>();
//                            String RegionName = listBeans.get(i).getRegionName();
//                            String Location = listBeans.get(i).getLocation();
//                            String id = listBeans.get(i).getId();
//                            String  deployment  = listBeans.get(i).getDeployment();
//
//
//                            hashMap.put("RegionName",RegionName);
//                            hashMap.put("Location",Location);
//                            hashMap.put("id",id);
//
//
//
//
//                            listid.add(id);
//                            list.add(deployment);
//
//                            listmap.add(hashMap);
//                        }
//
//                        Data.setListmap(listmap);
//
//                        Data.setList(list);
//
//
//                        Data.setListid(listid);  //场所id1
//
//                        getActivity().runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                myAdapter1 = new MyAdapter( listmap,getContext());
//                                listView.setAdapter(myAdapter1);
//
//
//
//
//                            }
//                        });





                    }

                });
    }













    private void getdata() {

        HashMap<String, String> params = new HashMap<>();
        params.put("userid", "22");
        params.put("page", "1");



        JSONObject jsonObject = new JSONObject(params);
        String s = Data.getToken();
        Log.v("Tag", s);

        String str = jsonObject.toString();


        OkGo.post(ApiService.httpUrl + "getsite")
                .tag(this)
                .cacheKey("cachePostRegister11")
                .cacheMode(CacheMode.DEFAULT)
                .upJson(jsonObject.toString())

                .headers("token", Data.getToken())

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        SiteDevice siteDevice = new Gson().fromJson(s, SiteDevice.class);

                        for (int i = 0; i < siteDevice.getObject().size(); i++) {
                            HashMap<String, String> hashMap = new HashMap<>();
                            String place = siteDevice.getObject().get(i).getDeployment();
                            String place1 = siteDevice.getObject().get(i).getRegionalism();
                         //   String id = String.valueOf(siteDevice.getObject().get(i).getId());
                            hashMap.put("place", place);
                            hashMap.put("place1", place1);

                            list.add(place);
                          //  listId.add(id);

                            listmap.add(hashMap);
                        }


                        Data.setList(list);
                        Data.setListmap(listmap);
                      //  Data.setListSiteid(listId);
                      //  Data.setListid(listId);


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                                myAdapter1 = new SitedetailAdapter1(FireMessageActivity.this, listmap);
                                myRecyclerView.setLayoutManager(new LinearLayoutManager(FireMessageActivity.this));
                                myRecyclerView.setAdapter(myAdapter1);
                                //设置分割线
                                myRecyclerView.addItemDecoration(new DividerItemDecoration(FireMessageActivity.this, DividerItemDecoration.VERTICAL_LIST));


                                //RecyclerView点击事件
                                myAdapter1.setOnItemClickLitener(new SitedetailAdapter1.OnItemClickLitener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        //  Toast.makeText(SitedetailActivity.this, position + "click", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(FireMessageActivity.this, SiteManagement.class);
                                        startActivity(intent);


                                    }

                                    @Override
                                    public void onItemLongClick(View view, final int position) {
                                        //这里长按定义的是删除
                                        AlertDialog.Builder dialog = new AlertDialog.Builder(FireMessageActivity.this);
                                        dialog.setTitle("是否删除");
                                        dialog.setMessage("确定吗？");
                                        dialog.setCancelable(false);
                                        dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {

                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                myAdapter1.removeData(position);
                                            }
                                        });

                                        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        });
                                        dialog.show();

                                        Toast.makeText(FireMessageActivity.this, position + "Long click", Toast.LENGTH_SHORT).show();
                                    }
                                });


                                //    myAdapter1 = new MyAdapter( data,getContext());


                                //   myAdapter1 = new MyAdapter(listmap,getContext());
                                //   listView.setAdapter(myAdapter1);
                                //   myAdapter1.notifyDataSetChanged();

                                //  pullToRefreshListView.onRefreshComplete();
                            }
                        });


                        //          handler.obtainMessage(1).sendToTarget();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        //  mTextView2.setText(e.getMessage());
                        Toast.makeText(FireMessageActivity.this, "NOsuccess " + e, Toast.LENGTH_SHORT).show();
                        System.out.println("sss" + e);
                    }
                });


    }
}
