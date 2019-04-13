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
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.silence.commonframe.ApiService;

import com.silence.commonframe.R;
import com.silence.commonframe.adapter.FindAdapter;
import com.silence.commonframe.adapter.MyAdapter;
import com.silence.commonframe.adapter.SitedetailAdapter;
import com.silence.commonframe.adapter.SitedetailMineAdapter;
import com.silence.commonframe.bean.JsonBean;
import com.silence.commonframe.model.IsChoose;
import com.silence.commonframe.model.SiteDevice;
import com.silence.commonframe.model.SiteModel;
import com.silence.commonframe.model.TroubleDeviceModel;
import com.silence.commonframe.utils.Data;
import com.silence.commonframe.utils.DividerItemDecoration;
import com.silence.commonframe.utils.TitlePopupMenu;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class SitedetailActivity extends AppCompatActivity {//implements View.OnClickListener



    private JsonBean jsonBean;
    private List<HashMap<String, String>> listmap = new ArrayList<HashMap<String, String>>() ;

    private ArrayList<String> list = new ArrayList<String>(); //数据源
    private SitedetailMineAdapter myAdapter1;
    private    MyAdapter myAdapter3;

    private TitlePopupMenu titlePopup;
    private ArrayList<Integer>  listChose = new ArrayList<Integer>();
    private IsChoose isChoose;
    private ArrayList<IsChoose> arrayList;
    private ArrayList<IsChoose> deleteArrayList;
  //  private    String siteId = "";

   private String strId = "";
    StringBuilder str = new StringBuilder();

    @Bind(R.id.my_recycler_view)
    RecyclerView  myRecyclerView;


    @Bind(R.id.edit)   //delete
    Button bt;
    @Bind(R.id.delete)   //delete
     Button btdel;





    private int iSSHOW = GONECHECK;
    private static final int GONECHECK = 0;
    private static final int SHOWCHECK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitedetail);
        ButterKnife.bind(this);


//        myAdapter1 = new SitedetailAdapter(SitedetailActivity.this,Data.getListmap());
//        myRecyclerView.setAdapter(myAdapter1);
        initWindows();
      //  getdata();



        getSite();
        arrayList = new ArrayList<>();
        deleteArrayList = new ArrayList<>();
//        SitedetailAdapter    mAdapter = new SitedetailAdapter(this, listmap);
//        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        myRecyclerView.setAdapter(mAdapter);
        init();
//        ImageView imageView = (ImageView) this.findViewById(R.id.imageView_daohang);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                titlePopup.show(v, TitlePopupMenu.STYLE_ALIGN_THIS_BOTTOM_WINDOW_RIGHT);
//            }
//        });


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iSSHOW = iSSHOW == GONECHECK ? SHOWCHECK : GONECHECK;
                myAdapter1.setMode(iSSHOW);
            }
        });

        btdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < deleteArrayList.size(); i++) {
                    myAdapter1.remove(deleteArrayList.get(i));


                  //  myAdapter1.removeData(i);
                }
                if (deleteArrayList.size() == 0){
                    Toast.makeText(SitedetailActivity.this,"请选中对应删除的场所！",Toast.LENGTH_SHORT).show();
                    return;
                }


           //     System.out.println("ss"+Data.getListSiteid());
           //     Toast.makeText(SitedetailActivity.this,"ttt"+Data.getListSiteid(),Toast.LENGTH_SHORT).show();

             //   delSite();
                getSiteId();
                Toast.makeText(SitedetailActivity.this,"ttt"+strId,Toast.LENGTH_SHORT).show();
           //     str.delete(0,str.length());
                delSite();
            }
        });






    }

    /**
     * 对设备的号码进行对应的拼接
     */

    private  void getSiteId(){
      //  String stringId ;

       for(int i= 0;i<Data.getListSiteid().size();i++){

           str.append(Data.getListSiteid().get(i));
           str.append(",");
        //  Data.getListSiteid().remove(i);
       }
        Data.getListSiteid().clear();
         strId = str.substring(0, str.length() - 1);
        str.setLength(0);



    }



    private void delSite() {
//        HashMap<String, String> params = new HashMap<>();
//        params.put("id", strId);//id
//
//
//        JSONObject jsonObject = new JSONObject(params);
//        String s = Data.getToken();
//        Log.v("Tag",s);
//
//        String str = jsonObject.toString();
//
//
//        OkGo.post(ApiService.httpUrl+"deletesite")
//                .tag(this)
//                .cacheKey("cachePostRegister11")
//                .cacheMode(CacheMode.DEFAULT)
//                .upJson(jsonObject.toString())
//
//                .headers("token", Data.getToken())
//
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//
//
//
//
//
//                        Toast.makeText(SitedetailActivity.this,"删除场所成功！"+s,Toast.LENGTH_SHORT).show();
//
//
//                    }
//
//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
//                        super.onError(call, response, e);
//                        System.out.println("sss"+e);
//                    }
//                });












        String  url =   ApiService.httpUrl1 +"/app/deleteSite?ids="+ strId;
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

                        Toast.makeText(SitedetailActivity.this,"ff"+s,Toast.LENGTH_SHORT).show();

                    }

                });















    }


    private void init() {

        titlePopup = new TitlePopupMenu(this, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titlePopup.setItemOnClickListener(new TitlePopupMenu.OnItemOnClickListener() {

            @Override
            public void onItemClick(String desc) {
                showToast(desc);
            }
        });
        // titlePopup.addItem(null, "添加朋友");
        titlePopup.addItem(getResources().getDrawable(R.mipmap.homeadd),
                "添加联系人");
        titlePopup.addItem(getResources().getDrawable(R.mipmap.homeadd),
                "删除联系人");
        //     titlePopup.addItem(getResources().getDrawable(R.mipmap.homeadd),
//                "添加场所");
    }


    private void showToast(String msg) {
        //  Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        if (msg.equals("添加联系人")) {
//            Intent intent = new Intent(getContext(), WelcomeActivity.class);
//            startActivity(intent);
            Toast.makeText(this,"添加联系人！",Toast.LENGTH_SHORT).show();

//            Intent intent = new Intent(this,AdddeviceActivity.class);
//            startActivity(intent);

            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
           // builder.setIcon(R.drawable.people);
            builder.setTitle("添加联系人");
            //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
            View view = LayoutInflater.from(this).inflate(R.layout.item_addsite, null);
            //    设置我们自己定义的布局文件作为弹出框的Content
            builder.setView(view);

            final EditText username = (EditText)view.findViewById(R.id.deviceno);


            builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    String a = username.getText().toString().trim();
                    // String b = password.getText().toString().trim();
                    //    将输入的用户名和密码打印出来
                  //  Toast.makeText(SitedetailActivity.this, "用户名: "  + a, Toast.LENGTH_SHORT).show();

                    getdata1();


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

        } else if (msg.equals("删除联系人")){
            Intent intent = new Intent(this,AddSiteActivity.class);
            startActivity(intent);
        }

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

                        Toast.makeText(SitedetailActivity.this,"联系中人添加成功！"+s,Toast.LENGTH_SHORT).show();
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

    private void getdata() {

        HashMap<String, String> params = new HashMap<>();
        params.put("userid", "40");
        params.put("page", "1");
//    params.put("phone", "13333311338");


        JSONObject jsonObject = new JSONObject(params);
        String s = Data.getToken();
        Log.v("Tag",s);

        String str = jsonObject.toString();


        OkGo.post(ApiService.httpUrl+"getsite")
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


                        SiteDevice siteDevice = new Gson().fromJson(s,SiteDevice.class);

                        for (int i = 0;i<siteDevice.getObject().size();i++){

                            isChoose = new IsChoose();
                            HashMap<String,String>  hashMap = new HashMap<>();
                            String place = siteDevice.getObject().get(i).getDeployment();
                            String place1 = siteDevice.getObject().get(i).getRegionalism();
                            String id = String.valueOf(siteDevice.getObject().get(i).getId());
                           // String chose = String.valueOf(isChoose.isSelect);
                          //  String chose = isChoose.setIsSelect(1);
                            hashMap.put("place",place);
                            hashMap.put("place1",place1);
                            hashMap.put("id",id);
                        //   isChoose.setIsSelect(0);
                        //    hashMap.put("chose",chose);
                            list.add(place);

                            listmap.add(hashMap);

                            arrayList.add(isChoose);
                        //    Data.getArrayList().add(isChoose);

                        }


                        Data.setList(list);
                        Data.setListmap(listmap);


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {




                                myAdapter1 = new SitedetailMineAdapter(SitedetailActivity.this,listmap,arrayList);
                                myRecyclerView.setLayoutManager(new LinearLayoutManager(SitedetailActivity.this));
                                myRecyclerView.setAdapter(myAdapter1);
                                //设置分割线
                                myRecyclerView.addItemDecoration(new DividerItemDecoration(SitedetailActivity.this, DividerItemDecoration.VERTICAL_LIST));



                                //RecyclerView点击事件
                                myAdapter1.setOnItemClickLitener(new SitedetailMineAdapter.OnItemClickLitener() {
                                    private IsChoose e;



                                    @Override
                                    public void onItemClick(View view, int position) {
                                      //  Toast.makeText(SitedetailActivity.this, position + "click", Toast.LENGTH_SHORT).show();

                                        //判断是否显示选择筐
                                        if (iSSHOW == 1) {
                                            //获取当前条目然后设置是否为选中，这里没有直接用boolean值判断

                                            e = arrayList.get(position);

                                            if (e.getIsSelect() == 0) {
                                              //  e.setSelect(1);
                                                e.setIsSelect(1);
                                            } else {

                                                e.setIsSelect(0);
                                            }
                                            deleteArrayList.add(e);
                                            myAdapter1.notifyDataSetChanged();
                                        } else {
                                        //    Toast.makeText(SitedetailActivity.this, "当前是第" + position + "个条目", Toast.LENGTH_SHORT).show();





                                            Intent intent = new Intent(SitedetailActivity.this,SiteManagement.class);
                                            startActivity(intent);
                                        }





//                                        Intent intent = new Intent(SitedetailActivity.this,SiteManagement.class);
//                                        startActivity(intent);


                                    }

                                    @Override
                                    public void onItemLongClick(View view, final int position) {
                                        //这里长按定义的是删除
                                        AlertDialog.Builder dialog = new AlertDialog.Builder(SitedetailActivity.this);
                                        dialog.setTitle("是否删除");
                                        dialog.setMessage("确定吗？");
                                        dialog.setCancelable(false);
                                        dialog.setPositiveButton("确认",new DialogInterface.OnClickListener(){

                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                myAdapter1.removeData(position);
                                            }
                                        } );

                                        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        });
                                        dialog.show();

                                        Toast.makeText(SitedetailActivity.this, position + "Long click", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(SitedetailActivity.this,"NOsuccess "+e,Toast.LENGTH_SHORT).show();
                        System.out.println("sss"+e);
                    }
                });


    }


  private void   getSite(){

          //http://www.hsh-iot.com/hsh-app/app/deleteDevice?ids=00000000006
          String url = ApiService.httpUrl1 + "/app/getSite?page=1&pagesize=11";
          OkGo.get(url)
                  .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                  .cacheKey("cacheGetKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                  .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                  .headers("token", Data.getToken())
                  .execute(new StringCallback() {
                      @Override
                      public void onSuccess(String s, Call call, Response response) {



                          SiteModel siteModel = new Gson().fromJson(s,SiteModel.class);
                          List<SiteModel.DataBean.DataListBean>  listBeans =  siteModel.getData().getDataList();
                          System.out.println("ss"+listBeans);





//                        if(verifyModel.getCode() == 0){
//                            Toast.makeText(RegisterActivity.this,"验证码已经发送成功！",Toast.LENGTH_SHORT).show();
//                        }

                          for (int i = 0;i<listBeans.size();i++){
                              isChoose = new IsChoose();
                              HashMap<String,String>  hashMap = new HashMap<>();
                              String RegionName = listBeans.get(i).getRegionName();//区域
                              String Location = listBeans.get(i).getLocation();//详细地址
                              String id = listBeans.get(i).getId();//设备id
                              String  deployment  = listBeans.get(i).getDeployment();//位置


                              hashMap.put("RegionName",RegionName);
                              hashMap.put("Location",Location);
                              hashMap.put("id",id);
                              hashMap.put("deployment",deployment);




                           //   listid.add(id);
                              list.add(deployment);

                             listmap.add(hashMap);
                              arrayList.add(isChoose);
                          }



                          runOnUiThread(new Runnable() {
                              @Override
                              public void run() {




                                  myAdapter1 = new SitedetailMineAdapter(SitedetailActivity.this,listmap,arrayList);
                                  myRecyclerView.setLayoutManager(new LinearLayoutManager(SitedetailActivity.this));
                                  myRecyclerView.setAdapter(myAdapter1);
                                  //设置分割线
                                  myRecyclerView.addItemDecoration(new DividerItemDecoration(SitedetailActivity.this, DividerItemDecoration.VERTICAL_LIST));



                                  //RecyclerView点击事件
                                  myAdapter1.setOnItemClickLitener(new SitedetailMineAdapter.OnItemClickLitener() {
                                      private IsChoose e;



                                      @Override
                                      public void onItemClick(View view, int position) {
                                          //  Toast.makeText(SitedetailActivity.this, position + "click", Toast.LENGTH_SHORT).show();

                                          //判断是否显示选择筐
                                          if (iSSHOW == 1) {
                                              //获取当前条目然后设置是否为选中，这里没有直接用boolean值判断

                                              e = arrayList.get(position);

                                              if (e.getIsSelect() == 0) {
                                                  //  e.setSelect(1);
                                                  e.setIsSelect(1);
                                              } else {

                                                  e.setIsSelect(0);
                                              }
                                              deleteArrayList.add(e);
                                              myAdapter1.notifyDataSetChanged();
                                          } else {
                                              //    Toast.makeText(SitedetailActivity.this, "当前是第" + position + "个条目", Toast.LENGTH_SHORT).show();




                                              Data.setLinkmanname( Data.getListid().get(position));                    ;
                                              Intent intent = new Intent(SitedetailActivity.this,SiteManagement.class);
                                              startActivity(intent);
                                          }





//                                        Intent intent = new Intent(SitedetailActivity.this,SiteManagement.class);
//                                        startActivity(intent);


                                      }

                                      @Override
                                      public void onItemLongClick(View view, final int position) {
                                          //这里长按定义的是删除
                                          AlertDialog.Builder dialog = new AlertDialog.Builder(SitedetailActivity.this);
                                          dialog.setTitle("是否删除");
                                          dialog.setMessage("确定吗？");
                                          dialog.setCancelable(false);
                                          dialog.setPositiveButton("确认",new DialogInterface.OnClickListener(){

                                              @Override
                                              public void onClick(DialogInterface dialog, int which) {
                                                  myAdapter1.removeData(position);
                                              }
                                          } );

                                          dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                              @Override
                                              public void onClick(DialogInterface dialog, int which) {

                                              }
                                          });
                                          dialog.show();

                                          Toast.makeText(SitedetailActivity.this, position + "Long click", Toast.LENGTH_SHORT).show();
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













    public void myclick(View view) {
        finish();
    }



//    private int iSSHOW = GONECHECK;
//    private static final int GONECHECK = 0;
//    private static final int SHOWCHECK = 1;
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.edit:
//                iSSHOW = iSSHOW == GONECHECK ? SHOWCHECK : GONECHECK;
//                myAdapter1.setMode(iSSHOW);
//                break;
//
//            case R.id.delete:
//                for (int i = 0; i < listmap.size(); i++) {
//                   // myAdapter1.remove(deleteArrayList.get(i));
//                  //  myAdapter1.removeData(position);
//                }
//
//                break;
//
//        }
//    }
}
