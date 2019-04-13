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
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.silence.commonframe.ApiService;
import com.silence.commonframe.Dialog.CDialog;
import com.silence.commonframe.R;
import com.silence.commonframe.adapter.LinkmanAdapter;
import com.silence.commonframe.model.LinkmanModel;
import com.silence.commonframe.utils.Data;
import com.silence.commonframe.utils.DividerItemDecoration;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class AddlinkmanActivity extends AppCompatActivity {


    @Bind(R.id.recView_linkman)
    RecyclerView recViewLinkman;
    private List<HashMap<String, String>> listmap = new ArrayList<HashMap<String, String>>();

    private     LinkmanAdapter mAdapter;
    private  List<String>  list = new ArrayList<>();
    private  String id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlinkman);
        ButterKnife.bind(this);
        getData();
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








    private void getData() {

//        HashMap<String, String> params = new HashMap<>();
//        params.put("siteid", "7");
////        params.put("page", "1");
////    params.put("phone", "13333311338");
//
//
//        final JSONObject jsonObject = new JSONObject(params);
//        String s = Data.getToken();
//        Log.v("Tag", s);
//
//        String str = jsonObject.toString();
//
//        OkGo.post(ApiService.httpUrl + "getlinkman")
//                .tag(this)
//                .cacheKey("cachePostRegister11")
//                .cacheMode(CacheMode.DEFAULT)
//                .upJson(jsonObject.toString())
//
//                .headers("token", Data.getToken())
////                .params("format", "json")
////                .params("albumId", "Lqfme5hSolM")
////                .params("pageNo", "1")
////                .params("pageSize", "2")
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        //  mTextView2.setText(s);
//                        // System.out.println("sss"+response);
//
////                        LoginModel jsonRootBean = new Gson().fromJson(s, LoginModel.class);
////                        Data.setName(jsonRootBean.getObject().getUsername().toString());
////                        System.out.println("rst:" + jsonRootBean.getObject().getUsername().toString());
//
//
//                        LinkmanModel jsonBean = new Gson().fromJson(s, LinkmanModel.class);
//
//                        for (int i = 0; i < jsonBean.getObject().size(); i++) {
//                            HashMap<String, String> hashMap = new HashMap<>();
//                            String name = jsonBean.getObject().get(i).getName();
//                            String phone = jsonBean.getObject().get(i).getPhone();
//                            //     String place1 = siteDevice.getObject().get(i).getRegionalism();
//
//                            hashMap.put("name", name);
//                            hashMap.put("phone", phone);
//
//
//                            //   list.add(place);
//
//                            listmap.add(hashMap);
//                        }
//
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//
//
////                                MyAdapterSlide    adapter = new MyAdapterSlide(getContext(), listmap);
//
//
//                               mAdapter = new LinkmanAdapter(AddlinkmanActivity.this, listmap);
//                                recViewLinkman.setLayoutManager(new LinearLayoutManager(AddlinkmanActivity.this));
//                                recViewLinkman.setAdapter(mAdapter);
//                                recViewLinkman.addItemDecoration(new DividerItemDecoration(AddlinkmanActivity.this, DividerItemDecoration.VERTICAL_LIST));
//
//
//                                //RecyclerView点击事件
//                                mAdapter.setOnItemClickLitener(new LinkmanAdapter.OnItemClickLitener() {
//                                    @Override
//                                    public void onItemClick(View view, int position) {
//                                        Toast.makeText(AddlinkmanActivity.this, position + "click", Toast.LENGTH_SHORT).show();
//                                    }
//
//                                    @Override
//                                    public void onItemLongClick(View view, final int position) {
//                                        //这里长按定义的是删除
//                                        AlertDialog.Builder dialog = new AlertDialog.Builder(AddlinkmanActivity.this);
//                                        dialog.setTitle("是否删除");
//                                        dialog.setMessage("确定吗？");
//                                        dialog.setCancelable(false);
//                                        dialog.setPositiveButton("确认",new DialogInterface.OnClickListener(){
//
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
//                                                mAdapter.removeData(position);
//                                            }
//                                        } );
//
//                                        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
//
//                                            }
//                                        });
//                                        dialog.show();
//
//                                        Toast.makeText(AddlinkmanActivity.this, position + "Long click", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//
//
//
//
//
//
////                                mAdapter.setOnItemClickListener(new FindAdapter.MyItemClickListener() {
////                                    @Override
////                                    public void onItemClick(View view, int postion) {
////                                        Toast.makeText(getContext(), postion+"", Toast.LENGTH_SHORT).show();
////                                    }
////                                });
//
//
//                            }
//                        });
//
//
//                        //          handler.obtainMessage(1).sendToTarget();
//                    }
//
//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
//                        super.onError(call, response, e);
//                        //  mTextView2.setText(e.getMessage());
//                        //  Toast.makeText(getContext(),"NOsuccess "+e,Toast.LENGTH_SHORT).show();
//                        System.out.println("sss" + e);
//                    }
//                });








       // Data.setListid(listid);

        String  url =   ApiService.httpUrl1 +"/app/getLinkman?siteid="+ Data.getLinkmanname();
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

//                        Device device = new Gson().fromJson(s, Device.class);
//                        List<Device.DataBean.DataListBean> listBeans = device.getData().getDataList();

                  //      Toast.makeText(AddlinkmanActivity.this,"ff"+s,Toast.LENGTH_SHORT).show();




//
//
                        LinkmanModel jsonBean = new Gson().fromJson(s, LinkmanModel.class);

                        for (int i = 0; i < jsonBean.getData().size(); i++) {
                            HashMap<String, String> hashMap = new HashMap<>();
                            String name = jsonBean.getData().get(i).getName();
                            String phone = jsonBean.getData().get(i).getPhone();
                            String id = jsonBean.getData().get(i).getId();
                            //     String place1 = siteDevice.getObject().get(i).getRegionalism();

                            hashMap.put("name", name);
                            hashMap.put("phone", phone);
                            hashMap.put("id", id);


                               list.add(id);

                            listmap.add(hashMap);
                        }


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


//                                MyAdapterSlide    adapter = new MyAdapterSlide(getContext(), listmap);


                               mAdapter = new LinkmanAdapter(AddlinkmanActivity.this, listmap);
                                recViewLinkman.setLayoutManager(new LinearLayoutManager(AddlinkmanActivity.this));
                                recViewLinkman.setAdapter(mAdapter);
                                recViewLinkman.addItemDecoration(new DividerItemDecoration(AddlinkmanActivity.this, DividerItemDecoration.VERTICAL_LIST));


                                //RecyclerView点击事件
                                mAdapter.setOnItemClickLitener(new LinkmanAdapter.OnItemClickLitener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        Toast.makeText(AddlinkmanActivity.this, position + "click", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onItemLongClick(View view, final int position) {
                                        //这里长按定义的是删除
//                                        AlertDialog.Builder dialog = new AlertDialog.Builder(AddlinkmanActivity.this);
//                                        dialog.setTitle("是否删除");
//                                        dialog.setMessage("确定吗？");
//                                        dialog.setCancelable(false);
//                                        dialog.setPositiveButton("确认",new DialogInterface.OnClickListener(){
//
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
//                                                mAdapter.removeData(position);
//                                            }
//                                        } );
//
//                                        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
//
//                                            }
//                                        });
//                                        dialog.show();
//
//                                        Toast.makeText(AddlinkmanActivity.this, position + "Long click", Toast.LENGTH_SHORT).show();







                                        CDialog.Builder builder = new CDialog.Builder(AddlinkmanActivity.this);
                                        //    AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.dialog));
                                        //builder.setIcon(R.drawable.ic_launcher);
//                builder.setTitle("你确定要退出系统吗？");
                                        builder.setMessage("你确定要删除信息吗？");
                                        //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
//                View view = LayoutInflater.from(getContext()).inflate(R.layout.item_addsite, null);
//                //    设置我们自己定义的布局文件作为弹出框的Content
//                builder.setView(view);

                                        //    final EditText deviceno = (EditText)view.findViewById(R.id.deviceno);


                                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which)
                                            {


                                                mAdapter.removeData(position);
                                            id =  list.get(position);

                                            delLinkman();





                                            }
                                        });
                                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which)
                                            {

                                            }
                                        });
                                        builder.show();













                                    }
                                });






//                                mAdapter.setOnItemClickListener(new FindAdapter.MyItemClickListener() {
//                                    @Override
//                                    public void onItemClick(View view, int postion) {
//                                        Toast.makeText(getContext(), postion+"", Toast.LENGTH_SHORT).show();
//                                    }
//                                });


                            }
                        });


                    }

                });



    }

    public void myclick(View view) {
        finish();
    }

    public void myClick(View view) {
//        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
//        // builder.setIcon(R.drawable.people);
//        builder.setTitle("添加联系人");
//        //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
//        View view1 = LayoutInflater.from(this).inflate(R.layout.item_addsite, null);
//        //    设置我们自己定义的布局文件作为弹出框的Content
//        builder.setView(view1);
//
//        final EditText username = (EditText)view1.findViewById(R.id.deviceno);
//
//
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
//        {
//            @Override
//            public void onClick(DialogInterface dialog, int which)
//            {
//                String a = username.getText().toString().trim();
//                // String b = password.getText().toString().trim();
//                //    将输入的用户名和密码打印出来
//                //  Toast.makeText(SitedetailActivity.this, "用户名: "  + a, Toast.LENGTH_SHORT).show();
//
//                getdata1();
//
//
//            }
//        });
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
//        {
//            @Override
//            public void onClick(DialogInterface dialog, int which)
//            {
//
//            }
//        });
//        builder.show();

      //  Toast.makeText()


        Intent intent = new Intent(AddlinkmanActivity.this,AddlinkmandetailActivity.class);
        intent.putExtra("data","1");
        startActivity(intent);



    }





    private void getdata1() {

        HashMap<String, String> params = new HashMap<>();
        params.put("siteid", "7");//id
        //   params.put("dLocation", stringplace);
        params.put("name", "liming");
        params.put("siteid", "191");
        params.put("phone", "13124257879");
//    params.put("phone", "13333311338");


        JSONObject jsonObject = new JSONObject(params);
        String s = Data.getToken();
        Log.v("Tag",s);

        String str = jsonObject.toString();


        OkGo.post(ApiService.httpUrl+"addlinkman")
                .tag(this)
                .cacheKey("cachePostRegister11")
                .cacheMode(CacheMode.DEFAULT)
                .upJson(jsonObject.toString())

                .headers("token", Data.getToken())

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {



//                        SiteDevice siteDevice = new Gson().fromJson(s,SiteDevice.class);
//
//                        for (int i = 0;i<siteDevice.getObject().size();i++){
//                            HashMap<String,String>  hashMap = new HashMap<>();
//                            String place = siteDevice.getObject().get(i).getDeployment();
//                            String place1 = siteDevice.getObject().get(i).getRegionalism();
//                            hashMap.put("place",place);
//                            hashMap.put("place1",place1);
//
//                            list.add(place);
//
//                            listmap.add(hashMap);
//                        }
//
//
//                        Data.setList(list);
//                        Data.setListmap(listmap);
//
//
//                        getActivity().runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                myAdapter1 = new MyAdapter( listmap,getContext());
//                                listView.setAdapter(myAdapter1);
//
//
//                            }
//                        });

                        Toast.makeText(AddlinkmanActivity.this,"联系中人添加成功！"+s,Toast.LENGTH_SHORT).show();
//                    System.out.println(""+s);

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);

//                        Toast.makeText(getContext(),"NOsuccess "+e,Toast.LENGTH_SHORT).show();
                        System.out.println("sss"+e);
                    }
                });


    }
    private void delLinkman(){
        String  url =   ApiService.httpUrl1 +"/app/deleteLinkman?id="+ id;
        OkGo.get(url)
                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheGetKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .headers("token", Data.getToken())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        //   mTextView2.setText(s);

                        JSONObject jsonObject = null;

                        try {
                            jsonObject = new JSONObject(s);
                            String message = jsonObject.get("msg").toString();
                            if (message.equals("success")) {
                                    Toast.makeText(AddlinkmanActivity.this,"数据删除成功！",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(AddlinkmanActivity.this,""+s,Toast.LENGTH_SHORT).show();
                            }




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                });



    }







}
