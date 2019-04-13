package com.silence.commonframe.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.silence.commonframe.ApiService;
import com.silence.commonframe.R;
import com.silence.commonframe.activity.AddSiteActivity;
import com.silence.commonframe.activity.AdddeviceActivity;
import com.silence.commonframe.activity.CommonScanActivity;
import com.silence.commonframe.activity.DevicedetailActivity;
import com.silence.commonframe.adapter.ListViewAdapter;
import com.silence.commonframe.adapter.MyAdapter;
import com.silence.commonframe.adapter.MyAdapterSlide;
import com.silence.commonframe.adapter.RecViewAdapter;
import com.silence.commonframe.bean.TestBean;
import com.silence.commonframe.model.Device;
import com.silence.commonframe.model.Device1;
import com.silence.commonframe.model.SiteDevice;
import com.silence.commonframe.model.SiteModel;
import com.silence.commonframe.utils.CommonAdapter;
import com.silence.commonframe.utils.CommonAdapter1;
import com.silence.commonframe.utils.Data;
import com.silence.commonframe.utils.SlideRecyclerView;
import com.silence.commonframe.utils.TitlePopupMenu;
import com.silence.commonframe.utils.ViewHolder;
import com.silence.commonframe.view.SwipeMenuLayout;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Silence on 2016/4/5.
 */
public class DeviceFragment extends Fragment {


    private ListView listView;
    private MyAdapterSlide adapter;

  //  private   final MyAdapterSlide adapter;
    private List<TestBean> lists;
    private List<Device>   list1;

    private SwipeMenuLayout swipeMenuLayout;

    private SlideRecyclerView recyclerView;

   private     Device jsonBean;
   private  Device1 siteDevice;



    private RecViewAdapter adapter1;
    private TextView rightTitel;
    private List<String> list;//listid
    private List<String> listid;
    private View view;
   private List<HashMap<String, String>> listmap = new ArrayList<HashMap<String, String>>() ;
    private ImageView imageView;
    private TextView   textView;
    private Button  bt;
    private TextView tv;
    private TextView tv1;
    private int  position1;
    private  String  position3; //位置
    private int position;
    private String place;
    private String place1;
    private  int number;
    private static final String TAG = "palce";
    private String siteid;
    private int page = 1;


    private TitlePopupMenu titlePopup;
    private int position4;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_device, container, false);


        recyclerView = (SlideRecyclerView) view.findViewById(R.id.id_item_remove_recyclerview);

        imageView = (ImageView) view.findViewById(R.id.imageViewdevice);//textdis
        textView = (TextView) view.findViewById(R.id.textdis);

        bt = (Button) view.findViewById(R.id.btdevice);//device_no
        tv = (TextView) view.findViewById(R.id.device_no);
        tv1 = (TextView) view.findViewById(R.id.device_no1);


        //  listView = (ListView) view.findViewById(R.id.listView);
        //模拟数据
        list = new ArrayList<>();
        listid = new ArrayList<>();
     //   getByOkGo1();
        Toast.makeText(getContext(),""+Data.getTestId(),Toast.LENGTH_SHORT).show();
        if(Data.getTestId() == 0) {


            return view;
        }else {


            getDevice();

            recyclerView.setOnItemClickListener(new SlideRecyclerView.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    //   Toast.makeText(MainActivity.this, "** " + mList.get(position) + " **", Toast.LENGTH_SHORT).show();

                    Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(),DevicedetailActivity.class);
                    startActivity(intent);


                }

                @Override
                public void onDeleteClick(int position) {

                    siteid =  Data.getListid().get(position);

                    adapter.removeItem(position);

                    Toast.makeText(getContext(), "Delitem！"+siteid, Toast.LENGTH_SHORT).show();

                    position4 = position;
                    delDevices1();


                }

                @Override
                public void onNavieteClick(int position) {
                    Toast.makeText(getContext(), "详细item！", Toast.LENGTH_SHORT).show();
                }
            });


            //   initData();//初始數據

            initView();//场所名称的的获取
            initListener();

            init();
            ImageView imageView = (ImageView) view.findViewById(R.id.btn_menu);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    titlePopup.show(v, TitlePopupMenu.STYLE_ALIGN_THIS_BOTTOM_WINDOW_RIGHT);
                }
            });

            return view;
        }






    }



    private void init() {

        titlePopup = new TitlePopupMenu(getActivity(), ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titlePopup.setItemOnClickListener(new TitlePopupMenu.OnItemOnClickListener() {

            @Override
            public void onItemClick(String desc) {
                showToast(desc);
            }
        });
        // titlePopup.addItem(null, "添加朋友");
        titlePopup.addItem(getResources().getDrawable(R.mipmap.homeadd1),
                "添加设备");
        //  titlePopup.addItem(null, "收付款");
        titlePopup.addItem(getResources().getDrawable(R.mipmap.homeadd1),
                "添加场所");
    }



    private void showToast(String msg) {
        //  Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        if (msg.equals("添加设备")) {
//            Intent intent = new Intent(getContext(), WelcomeActivity.class);
//            startActivity(intent);
            //   Toast.makeText(getContext(),"添加对应的设备！",Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getContext(),AdddeviceActivity.class);
            startActivity(intent);

        }else if (msg.equals("添加场所")){
            Intent intent = new Intent(getContext(),AddSiteActivity.class);
            startActivity(intent);
        }


    }


    private void  getDevice(){

      //  Data.setList(list);
    //    System.out.println("pp"+Data.getListId());


                        //  http://www.hsh-iot.com/hsh-app/app/getDevice?page=1&pagesize=11&siteid=6204a38e285746cda8b329165f559126
        String  url =   ApiService.httpUrl1 +"/app/getDevice?page="+page+"&pagesize=10&siteid="+ Data.getListid().get(position1);
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
                                String id = listBeans.get(i).getDeviceId();
                                String deviceLocation = listBeans.get(i).getDeviceLocation();
//                                String id1 = String.valueOf(siteDevice.getObject().get(i).getId());
//                                String dLocation = siteDevice.getObject().get(i).getDLocation();
//                                String siteid = String.valueOf(siteDevice.getObject().get(i).getId());





                                //     String place1 = siteDevice.getObject().get(i).getRegionalism();
                                hashMap.put("id",id);
                                hashMap.put("deviceLocation",deviceLocation);

                                   list.add(id);

                                listmap.add(hashMap);
                            }


                            if (list.size()==0){
                                return;
                            }


                            getActivity().runOnUiThread(new Runnable() {


                                @Override
                                public void run() {

                                   // number = listBeans.size();
                                    imageView.setVisibility(View.GONE);
                                 textView.setVisibility(View.GONE);
                                    tv.setVisibility(View.VISIBLE);
                                    tv1.setVisibility(View.VISIBLE);

                                    bt.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.VISIBLE);

                                    number = listmap.size();
                                            adapter = new MyAdapterSlide(getContext(), listmap);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                    recyclerView.setAdapter(adapter);


                                    //       Log.v(TAG,place);
//                                    if (place == null){
//                                    //    tv.setText("(设备的数量"+number+")");
//                                        tv1.setText(Data.getListlocation().get(position1));
//                                    }
//                                    else {
//                                        String p = place.substring(0,2);
//                                        tv.setText(place+"(设备的数量"+number+")");
//                                    }

                               //     tv1.setText(place1);

                                    tv1.setText(Data.getListlocation().get(position1));
                                    tv.setText("设备的数量:"+number+"");
                                    //  String p = place.substring(0,4);
                                    //  tv.setText(place+"设备的数量"+number);
                                }
                            });


                        }else {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    imageView.setVisibility(View.VISIBLE);
                                 textView.setVisibility(View.VISIBLE);
                                    bt.setVisibility(View.VISIBLE);
                                    recyclerView.setVisibility(View.GONE);
                                    tv1.setVisibility(View.GONE);
                                    tv.setVisibility(View.VISIBLE);
//                                    String p = place.substring(0,9);
                                    tv.setText("区域设备的总数量:0");
                     //               tv1.setText(place1);
                                    bt.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            //  Toast.makeText(getContext(),"helll",Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getContext(),CommonScanActivity.class);
                                            startActivity(intent);

                                        }
                                    });
                                }










                                // }
                            });

                        }




                    }

                });






    }

    private void delDevices1(){

                        //http://www.hsh-iot.com/hsh-app/app/deleteDevice?ids=00000000006
        String  url =   ApiService.httpUrl1 +"/app/deleteDevice?ids="+ list.get(position4);
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

                        Toast.makeText(getContext(),"ff"+s,Toast.LENGTH_SHORT).show();

                    }

                });

    }











    private void delDevices() {


            final HashMap<String, String> params = new HashMap<>();
            params.put("id", siteid);




            JSONObject jsonObject = new JSONObject(params);
            String s = Data.getToken();
            //       Log.v("Tag",s);

            String str = jsonObject.toString();


            OkGo.post(ApiService.httpUrl+"deletedevice")
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


                         //   SiteDevice siteDevice = new Gson().fromJson(s,SiteDevice.class);

                         Toast.makeText(getContext(),"success"+s,Toast.LENGTH_SHORT).show();



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





    private void getData() {
        HashMap<String, String> params = new HashMap<>();
        params.put("userid", "40");
        params.put("page", "1");
        params.put("siteid", position3);
//    params.put("phone", "13333311338");


        JSONObject jsonObject = new JSONObject(params);
        String s = Data.getToken();
        Log.v("Tag",s);

        String str = jsonObject.toString();

        OkGo.post(ApiService.httpUrl+"getdevice")
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







                       siteDevice = new Gson().fromJson(s,Device1.class);

                       String message = siteDevice.getMessage();

                       if (message.equals("ok")){
                           for (int i = 0;i<siteDevice.getObject().size();i++){
                               HashMap<String,String>  hashMap = new HashMap<>();
                               String id = siteDevice.getObject().get(i).getDeviceid();
                               String id1 = String.valueOf(siteDevice.getObject().get(i).getId());
                               String dLocation = siteDevice.getObject().get(i).getDLocation();
                               String siteid = String.valueOf(siteDevice.getObject().get(i).getId());
                               //     String place1 = siteDevice.getObject().get(i).getRegionalism();
                               hashMap.put("id",id);
                               hashMap.put("id1",id1);
                               hashMap.put("dLocation",dLocation);
                               hashMap.put("siteid",siteid);
                               //   list.add(place);

                               listmap.add(hashMap);
                           }


                           getActivity().runOnUiThread(new Runnable() {


                               @Override
                               public void run() {

                                   number = siteDevice.getObject().size();
                                   imageView.setVisibility(View.GONE);
                                   bt.setVisibility(View.GONE);
                                   recyclerView.setVisibility(View.VISIBLE);




                                   adapter = new MyAdapterSlide(getContext(), listmap);
                                   recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                   recyclerView.setAdapter(adapter);


                            //       Log.v(TAG,place);
                                   if (place == null){
                                       tv.setText(place+"(设备的数量"+number+")");
                                   }else {
                                         String p = place.substring(0,2);
                                       tv.setText(place+"(设备的数量"+number+")");
                                   }

                                   tv1.setText(place1);
                              //  String p = place.substring(0,4);
                              //  tv.setText(place+"设备的数量"+number);
                               }
                           });
                       }else {
                           getActivity().runOnUiThread(new Runnable() {
                               @Override
                               public void run() {

                                   imageView.setVisibility(View.VISIBLE);
                                   bt.setVisibility(View.VISIBLE);
                                   recyclerView.setVisibility(View.GONE);
                                   String p = place.substring(0,9);
                                   tv.setText(p+"(设备的数量:0)");
                                   tv1.setText(place1);
                                   bt.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           //  Toast.makeText(getContext(),"helll",Toast.LENGTH_SHORT).show();
                                           Intent intent = new Intent(getContext(),CommonScanActivity.class);
                                           startActivity(intent);

                                       }
                                   });
                               }










                               // }
                           });
                       }





                        //          handler.obtainMessage(1).sendToTarget();
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




    /**
     * 场所名称的的获取
     */
    private void initView() {
        RecyclerView recView = (RecyclerView) view.findViewById(R.id.recView);
      //  rightTitel = view.findViewById(R.id.right_titel);
        //設置RecyclerView的獨特顯示風格  （注：必寫。不寫不顯示視圖）
        recView.setLayoutManager(new LinearLayoutManager(getContext()));
        //把上下文和list數據集合傳給適配器
     //   adapter1 = new RecViewAdapter(getContext(), list);

        if (Data.getList().size()==0){
            return;
        }
        adapter1 = new RecViewAdapter(getContext(), Data.getList());
        //控件綁定適配器
        recView.setAdapter(adapter1);
        //刷新適配器
    //   adapter1.notifyDataSetChanged();

    }

    /**
     * 點擊事件
     */
    private void initListener() {
        /**
         * 使用  適配器我們剛寫好的  暴露的接口
         */
        adapter1.setOnRecyclerViewItemClickListener(new RecViewAdapter.OnItemClickListener() {
            //帶出來的【點擊】重寫方法
            @Override
            public void onClick(int position) {
//                Toast.makeText(getContext(), "點擊了" + (position ), Toast.LENGTH_SHORT).show();
                position1 =  position;  //位置


                   place = Data.getListlocation().get(position);
//                place1 = Data.getListregionalism().get(position);





                listmap.clear();
                getDevice();


          //      Toast.makeText(getContext(), "點擊了" + position1, Toast.LENGTH_SHORT).show();



              adapter1.setThisPosition(position);
//                //嫑忘记刷新适配器
                adapter1.notifyDataSetChanged();
            }

            //帶出來的【長按】重寫方法
            @Override
            public void onLongClick(int position) {
                Log.e("tag", "獲取到的左側長按點擊值為：" + (position + 1));
            }
        });
    }
//    private void getData3(){
//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                    listmap.clear();
//                    getData();
//            }
//        });
//
//
//
//    }


    private void  getData1(){



        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

//                if (position1 == 3){
//                    listmap.clear();
//                    getData();
//                }else

                   // {
                    imageView.setVisibility(View.VISIBLE);
                    bt.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    bt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //  Toast.makeText(getContext(),"helll",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getContext(),CommonScanActivity.class);
                            startActivity(intent);

                        }
                    });
                }




           // }
        });

    }

// public void clickDevice(View view){
//
//        Toast.makeText(getContext(),"helll",Toast.LENGTH_SHORT).show();
// }
//  class  Threadmy extends Thread{
//    @Override
//    public void run() {
//      // getData();
//
//        if (position1 == 0){
//            listmap.clear();
//            getData();
//        }else {
//            imageView.setVisibility(View.VISIBLE);
//            bt.setVisibility(View.VISIBLE);
//            recyclerView.setVisibility(View.GONE);
//            bt.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //  Toast.makeText(getContext(),"helll",Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getContext(),CommonScanActivity.class);
//                    startActivity(intent);
//
//                }
//            });
//        }
//    }
//}


    private void getByOkGo1() {
//        OkGo.get("http://wthrcdn.etouch.cn/weather_mini?citykey=101180101")  http://www.hsh-iot.com/hsh-app/app/getSite?page=1&pagesize=10                          // 请求方式和请求url



//        listid.clear();
//        list.clear();
//        listmap.clear();

        String  url =   ApiService.httpUrl1 +"/app/getSite?page="+page+"&pagesize=100";
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


                            hashMap.put("RegionName",RegionName);
                            hashMap.put("Location",Location);
                            hashMap.put("id",id);




                            listid.add(id);
                            list.add(deployment);

                            listmap.add(hashMap);
                        }

                        //     Data.setListmap(listmap);

                        Data.setList(list);//注册场所的位置


                               Data.setListid(listid);  //场所id1

//                        getActivity().runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                myAdapter1 = new MyAdapter( listmap,getContext());
//                                listView.setAdapter(myAdapter1);
//
//
//                            }
//                        });





                    }

                });
    }






















}
