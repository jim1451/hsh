package com.silence.commonframe.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.silence.commonframe.ApiService;
import com.silence.commonframe.Dialog.CDialog;
import com.silence.commonframe.R;
import com.silence.commonframe.activity.AddSiteActivity;
import com.silence.commonframe.activity.AdddeviceActivity;
import com.silence.commonframe.activity.Devicedetail3Activity;
import com.silence.commonframe.activity.FireAlarmActivity;
import com.silence.commonframe.activity.MainActivity;
import com.silence.commonframe.activity.NavigationActivity;
import com.silence.commonframe.activity.NewsdetailActivity;
import com.silence.commonframe.activity.RegisterActivity;
import com.silence.commonframe.adapter.MyAdapter;
import com.silence.commonframe.adapter.BaseAdapter;
import com.silence.commonframe.bean.JsonBean;
import com.silence.commonframe.bean.Person;
import com.silence.commonframe.model.DeviceInfo;
import com.silence.commonframe.model.LoginFromPasswordModel;
import com.silence.commonframe.model.SiteDevice;
import com.silence.commonframe.model.SiteModel;
import com.silence.commonframe.model.TroubleSiteModel;
import com.silence.commonframe.model.TuisongModel;
import com.silence.commonframe.model.VerifyModel;
import com.silence.commonframe.utils.Constants;
import com.silence.commonframe.utils.Data;
import com.silence.commonframe.utils.TitlePopupMenu;
import com.silence.commonframe.websocket.WebSocket;
import com.silence.commonframe.websocket.WebSocketConnection;
import com.silence.commonframe.websocket.WebSocketConnectionHandler;
import com.silence.commonframe.websocket.WebSocketException;


import org.json.JSONException;
import org.json.JSONObject;

import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import okhttp3.Call;
import okhttp3.Response;

//import android.support.v7.widget.DividerItemDecoration;

/**
 * Created by Silence on 2016/4/5.
 */
public class FirstFragment extends Fragment  {

   /* @Bind(R.id.btn_menu)
    TextView btnMenu;*/

    private TitlePopupMenu titlePopup;
    private TextView tv;
    private   TextView btnMenu;
    private ImageView  imageView;



    private ArrayList<Person> mData = new ArrayList<>();

    private BaseAdapter adapter;

    Context mContext;


    private PullToRefreshListView pullToRefreshListView;
    private View headerview_in;
    private View headerview1;
    private View headerview2;
    private List<String> data;

    List<HashMap<String, String>> listmap = new ArrayList<HashMap<String, String>>() ;
    List<HashMap<String, String>> listmap1 = new ArrayList<HashMap<String, String>>() ;


    private int a=10;
    private int b;
    private MyAdapter myAdapter;
    private static boolean isFirstEnter = true;

    private LinearLayout invis;
    private MyAdapter myAdapter1;
    private PullToRefreshListView mPullToRefreshListView;   //一个可以下拉刷新的listView对象

    private ArrayList<String> list = new ArrayList<String>(); //数据源  Location
    private ArrayList<String> listlocation = new ArrayList<String>();
    private ArrayList<String> listregionalism = new ArrayList<String>();
    private ArrayList<String> listid = new ArrayList<String>();//场所设备的ID
    private ArrayList<String> listSiteid = new ArrayList<String>();//场所设备的ID
    private ArrayList<String> listSiteid1 = new ArrayList<String>();//场所设备的ID

    private ListView listView; //普通的listView对象

    final WebSocketConnection wsc = new WebSocketConnection();

    private    ImageView  imageView1;

    private LinearLayout LL;


    private WebSocket ws;
    private int page = 1;
    private String firstData;
    private String  url1;





    private FrameLayout mRefreshableViewWrapper;

    Handler handler=new Handler();







    private static final int MSG_SET_ALIAS = 101;
  //  private EditText msgText;
    public static boolean isForeground = false;
    private MediaPlayer mMediaPlayer;

    private Boolean  flag = false;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first1, container, false);
        data = new ArrayList<>();


        invis = (LinearLayout) view.findViewById(R.id.invis);

        mPullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.refreshListView);

        listView = mPullToRefreshListView.getRefreshableView();


        listView.setDividerHeight(0);



        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);//两端刷新
     //   mPullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);//下拉刷新







        //头部内容,会隐藏的部分

        View header1 = View.inflate(getContext(), R.layout.header1, null);

        listView.addHeaderView(header1);  //添加头部，会隐藏的部分



        init();
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                titlePopup.show(view, TitlePopupMenu.STYLE_ALIGN_THIS_BOTTOM_WINDOW_RIGHT);
//            }
//        });

       ImageView imageView = (ImageView) header1.findViewById(R.id.btn_menu);
       imageView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               titlePopup.show(v, TitlePopupMenu.STYLE_ALIGN_THIS_BOTTOM_WINDOW_RIGHT);
           }
       });


//        ImageView imageView1 = (ImageView) header1.findViewById(R.id.question);
//        imageView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               // titlePopup.show(v, TitlePopupMenu.STYLE_ALIGN_THIS_BOTTOM_WINDOW_RIGHT);
////                Intent intent = new Intent(getContext(),NavigationActivity.class);
////                startActivity(intent);
//            }
//        });






        LL = (LinearLayout) header1.findViewById(R.id.ll_header);

      imageView1 = (ImageView) header1.findViewById(R.id.imageView);
       // final ImageView finalImageView = imageView;



//        final ImageView finalImageView = imageView1;
//        imageView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mPullToRefreshListView == null ){ return; }
//                // 如添加headerview后 firstview就是hearderview
//                // 所有索引+1 取第一个view
//                // 获取点击的view
//
//                int visiblePosition = mPullToRefreshListView.getRefreshableView()
//                        .getFirstVisiblePosition();
//
//                View view1 = mPullToRefreshListView.getRefreshableView().getChildAt(
//                        2 - visiblePosition + 2);//这里+2是因为PullToRefreshListView  刷新布局 算一个Headview，而我本身需求又添加了一个headview， 如果仅仅只是PullToRefreshListView的刷新布局  这里+1就行了
//
//                if (view1 != null)//对View进行操作，这里换成你自己的需求
//                {
//
//                  //  finalImageView.setImageResource(R.drawable.flashlight);
//                    finalImageView.setImageResource(R.drawable.guzhang);
//                    LL.setBackgroundColor(Color.parseColor("#ff3333"));
//
//                }
//
//
//
//            }
//        });





        //头部内容,悬浮的部分

        View header2 = View.inflate(getContext(), R.layout.header2, null);

        listView.addHeaderView(header2);  //添加头部，ListView条目中的悬浮部分




    //   getdata();  //获取数据

        getByOkGo1();//获取全部的场所信息


        getByOkGo3();//获取发生异常场所的信息




//        Toast.makeText(getContext(),""+myData,Toast.LENGTH_SHORT).show();

        String   data1 = String.valueOf(System.currentTimeMillis());


        url1 = Data.getUserid()+":"+data1;




       // getWebsocketdata();//websocket



        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mPullToRefreshListView.getRefreshableViewWrapper().getLayoutParams();
        layoutParams.height=1900;
        mPullToRefreshListView.getRefreshableViewWrapper().setLayoutParams(layoutParams);




        listView.setOnScrollListener(new AbsListView.OnScrollListener() {



            @Override

            public void onScrollStateChanged ( AbsListView view, int scrollState ) {



            }



            @Override

            public void onScroll ( AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount ) {

                if (firstVisibleItem >= 3) {  //第二个条目内容已经不显示

                    invis.setVisibility(View.VISIBLE);

                } else {

                    invis.setVisibility(View.GONE);

                }

            }

        });





        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick ( AdapterView<?> parent, View view, int position, long id ) {
                if (position  == 1|| position == 2) {
                    //Toast.makeText(getContext(), "第" + position + "item", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                  // Toast.makeText(getContext(), "第" + (position-3) + "item", Toast.LENGTH_SHORT).show();
                    int position1 = position-3;
                    Intent intent = new Intent(getContext(),Devicedetail3Activity.class);
                 //   intent.putExtra("deviceid",listid.get(position1));  //listSiteid
                    intent.putExtra("deviceid",listSiteid1.get(position1));
                    startActivity(intent);
                }
            }

        });




        ButterKnife.bind(this, view);


        /**
         * 极光推送
         */
        registerMessageReceiver();
        isForeground = true;
//        //     DevBeep.init(this);
//
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS,Data.getLoginId()));//fc9b1aeb8e5c4debb0b8ae79569d99a6





        return view;
    }

    private void getWebsocketdata() {
        try {
            //"ws://192.168.1.106:9090/websocket01/chat.ws?username=server11"
         //   String url = "ws://192.168.1.106:8080/YunGanKeJi/websocket/40:1548637861185";

          //    String url = "ws://www.hsh-iot.com/YunGanKeJi/websocket/40:1548637861185";//1552470466

            String url = "ws://www.hsh-iot.com/YunGanKeJi/websocket/"+url1;//1552470466
          //  String url = "ws://http://47.100.29.244:8080/YunGanKeJi/websocket/40:1548637861185";

            wsc.connect(url,
                    new WebSocketConnectionHandler() {

                        @Override
                        public void onBinaryMessage(byte[] payload) {
                            System.out.println("onBinaryMessage size="
                                    + payload.length);
                        }

                        @Override
                        public void onClose(int code, String reason) {
                            System.out.println("onClose reason="
                                    + reason);
                        }

                        @Override
                        public void onOpen() {
                            System.out.println("onOpen");
                            wsc.sendTextMessage("Hello!");
                            // wsc.disconnect();
                        }

                        @Override
                        public void onRawTextMessage(byte[] payload) {
                            System.out.println("onRawTextMessage size="
                                    + payload.length);

                        }

                        @Override
                        public void onTextMessage(String payload) {//获取的信息！
                            System.out.println("onTextMessage"
                                    + payload);

                            if (payload.equals("login")){
                                Toast.makeText(getContext(),"已经在其他地方登陆！",Toast.LENGTH_SHORT).show();

                         //       handler.postDelayed(new splashhandler(), 2000);





                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        CDialog.Builder builder = new CDialog.Builder(getContext());

                                        builder.setMessage("您的账户已经在其它地方登陆,如果不是您本人操作，你的账户密码已经泄露，请尽快修改密码！");



                                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which)
                                            {
                                                System.exit(0);
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

                                wsc.disconnect();
                                //Toast.makeText(getContext(),"heiiiiii",Toast.LENGTH_SHORT).show();
                                return;

                            }
                            DeviceInfo    deviceInfo = new Gson().fromJson(payload,DeviceInfo.class);
                            final String   FireAlarm = String.valueOf(deviceInfo.getService().getData().getFireAlarm());


                        //    edt.setText(payload);
                         //   Toast.makeText(getContext(),"s"+payload,Toast.LENGTH_SHORT).show();
                            getActivity().runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {

                                                                final ImageView finalImageView = imageView1;
                                                                int visiblePosition = mPullToRefreshListView.getRefreshableView()
                                                                        .getFirstVisiblePosition();

                                                                View view1 = mPullToRefreshListView.getRefreshableView().getChildAt(
                                                                        2 - visiblePosition + 2);//这里+2是因为PullToRefreshListView  刷新布局 算一个Headview，而我本身需求又添加了一个headview， 如果仅仅只是PullToRefreshListView的刷新布局  这里+1就行了

                                                                if (view1 != null)//对View进行操作，这里换成你自己的需求
                                                                {
                                                                    if (FireAlarm.equals("0")){
                                                                        //  finalImageView.setImageResource(R.drawable.flashlight);
                                                                        finalImageView.setImageResource(R.drawable.guzhang);
                                                                        LL.setBackgroundColor(Color.parseColor("#ff3333"));
                                                                    }


                                                                }




                                                            }
                                                        }

                            );


                        }
                    });
        } catch (WebSocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


    protected FrameLayout getRefreshableViewWrapper() {

        return mRefreshableViewWrapper;

    }


    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }




    /**
     22      * 日期格式字符串转换成时间戳
     23      * @param date 字符串日期
     24      * @param format 如：yyyy-MM-dd HH:mm:ss
     25      * @return
     26      */
    public static String date2TimeStamp(String date_str,String format){
               try {
                         SimpleDateFormat sdf = new SimpleDateFormat(format);
                        return String.valueOf(sdf.parse(date_str).getTime()/1000);
                    } catch (Exception e) {
                         e.printStackTrace();
                     }
                 return "";
             }





    private void getByOkGo() {
//        OkGo.get("http://wthrcdn.etouch.cn/weather_mini?citykey=101180101")  http://www.hsh-iot.com/hsh-app/app/getSite?page=1&pagesize=10                          // 请求方式和请求url
        String  url =   ApiService.httpUrl1 +"/app/getSite?page="+page+"&pagesize=10";
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

                    Data.setTestId(listid.size());

                        Toast.makeText(getContext(),""+listid.size(),Toast.LENGTH_SHORT).show();


                       Data.setListmap(listmap);



                        Data.setList(list);


                        Data.setListid(listid);  //场所id1

                        System.out.println("kkk"+Data.getListId());

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                myAdapter1 = new MyAdapter(listmap,getContext());
                                listView.setAdapter(myAdapter1);



                                mPullToRefreshListView.onRefreshComplete();


                                mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

                                    @Override
                                    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                                        mPullToRefreshListView.postDelayed(new Runnable() {

                                            @Override
                                            public void run() {
                                                mPullToRefreshListView.onRefreshComplete();
                                            }
                                        }, 1000);



                                      //  Toast.makeText(getContext(),"hello",Toast.LENGTH_SHORT).show();
                                    //    myAdapter1.notifyDataSetChanged();


                                        //  mPullToRefreshListView.onRefreshComplete();
                                    }

                                    @Override
                                    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                                        mPullToRefreshListView.postDelayed(new Runnable() {

                                            @Override
                                            public void run() {
                                                mPullToRefreshListView.onRefreshComplete();
                                            }
                                        }, 1000);

                                        //     getdata1();
//                                        page++;



                                 //    myAdapter1.notifyDataSetChanged();

                                      // Toast.makeText(getContext(),"hello1",Toast.LENGTH_SHORT).show();

                                        //   mPullToRefreshListView.onRefreshComplete();
                                  //      getByOkGo1();
                                    }
                                });









                            }
                        });





                    }

                });
    }











    private void getByOkGo3() {
//        OkGo.get("http://wthrcdn.etouch.cn/weather_mini?citykey=101180101")  http://www.hsh-iot.com/hsh-app/app/getSite?page=1&pagesize=10                          // 请求方式和请求url
        String  url =   ApiService.httpUrl1 +"/app/getTroubleSite?page=1&pagesize=20";
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



                        TroubleSiteModel troubleSiteModel = new Gson().fromJson(s,TroubleSiteModel.class);
                        List<TroubleSiteModel.DataBean> listBeans =  troubleSiteModel.getData();
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
                            String siteId = listBeans.get(i).getSiteId();



                            hashMap.put("RegionName",RegionName);
                            hashMap.put("Location",Location);
                            hashMap.put("id",id);




//                            listid.add(id);
                           listSiteid1.add(siteId);
//                            list.add(deployment);

                            listmap1.add(hashMap);
                        }

//                        Data.setTestId(listid.size());
//
//
//                        Data.setListmap(listmap);
//
//
//
//                        Data.setList(list);
//
//
//                        Data.setListid(listid);  //场所id1

                        System.out.println("kkk"+Data.getListId());

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                myAdapter1 = new MyAdapter( listmap1,getContext());
                                listView.setAdapter(myAdapter1);






                                final ImageView finalImageView = imageView1;
                                int visiblePosition = mPullToRefreshListView.getRefreshableView()
                                        .getFirstVisiblePosition();

                                View view1 = mPullToRefreshListView.getRefreshableView().getChildAt(
                                        2 - visiblePosition + 2);//这里+2是因为PullToRefreshListView  刷新布局 算一个Headview，而我本身需求又添加了一个headview， 如果仅仅只是PullToRefreshListView的刷新布局  这里+1就行了

                                if (listmap1.size()!=0)//对View进行操作，这里换成你自己的需求
                                {
//                                                if (FireAlarm.equals("0")){
//                                                    //  finalImageView.setImageResource(R.drawable.flashlight);
//                                                    finalImageView.setImageResource(R.drawable.guzhang);
//                                                    LL.setBackgroundColor(Color.parseColor("#ff3333"));
//                                                }



                                    finalImageView.setImageResource(R.drawable.guzhang);
                                    LL.setBackgroundColor(Color.parseColor("#ff3333"));

                                }







                                mPullToRefreshListView.onRefreshComplete();


                                mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

                                    @Override
                                    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                                        mPullToRefreshListView.postDelayed(new Runnable() {

                                            @Override
                                            public void run() {
                                                mPullToRefreshListView.onRefreshComplete();
                                            }
                                        }, 1000);



                                        //  Toast.makeText(getContext(),"hello",Toast.LENGTH_SHORT).show();
                                        //    myAdapter1.notifyDataSetChanged();

                                        getByOkGo4();//获取全部的场所信息
                                        //  mPullToRefreshListView.onRefreshComplete();
                                    }

                                    @Override
                                    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                                        mPullToRefreshListView.postDelayed(new Runnable() {

                                            @Override
                                            public void run() {
                                                mPullToRefreshListView.onRefreshComplete();
                                            }
                                        }, 1000);

                                        //     getdata1();
//                                        page++;
                                   //     myAdapter1.notifyDataSetChanged();

                                        // Toast.makeText(getContext(),"hello1",Toast.LENGTH_SHORT).show();

                                        //   mPullToRefreshListView.onRefreshComplete();
                                       // getByOkGo1();
                                        getByOkGo4();//获取全部的场所信息
                                    }
                                });









                            }
                        });





                    }

                });
    }

































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


                            listlocation.add(Location);//listregionalism
                            listmap.add(hashMap);
                        }



                        Data.setTestId(listid.size());
                        Data.setListmap(listmap);

                        Data.setList(list);//注册场所的位置

                        Data.setListlocation(listlocation);//场所注册的地址在设备页面提提使用
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

    /**
     * 重新刷新获取场所数据
     */
    private void getByOkGo4() {
//        OkGo.get("http://wthrcdn.etouch.cn/weather_mini?citykey=101180101")  http://www.hsh-iot.com/hsh-app/app/getSite?page=1&pagesize=10                          // 请求方式和请求url



//        listid.clear();
//        list.clear();
//        listmap.clear();



        listid.clear();
        list.clear();
        listlocation.clear();//listregionalism
        listmap.clear();;











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


                            listlocation.add(Location);//listregionalism
                            listmap.add(hashMap);
                        }



                        Data.setTestId(listid.size());
                        Data.setListmap(listmap);

                        Data.setList(list);//注册场所的位置

                        Data.setListlocation(listlocation);//场所注册的地址在设备页面提提使用
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








    private void getdata() {

        final HashMap<String, String> params = new HashMap<>();
        params.put("userid", "40");
        params.put("page", String.valueOf(page));
//    params.put("phone", "13333311338");


        JSONObject jsonObject = new JSONObject(params);
        String s = Data.getToken();
 //       Log.v("Tag",s);

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
                        String message = siteDevice.getMessage();
                        if (message.equals("ok")) {


                            for (int i = 0; i < siteDevice.getObject().size(); i++) {
                                HashMap<String, String> hashMap = new HashMap<>();
                                String place = siteDevice.getObject().get(i).getDeployment();
                                String place1 = siteDevice.getObject().get(i).getRegionalism();
                                String id = String.valueOf(siteDevice.getObject().get(i).getId());
                                String location = String.valueOf(siteDevice.getObject().get(i).getLocation());
                                String regionalism = String.valueOf(siteDevice.getObject().get(i).getRegionalism());
                                hashMap.put("place", place);
                                hashMap.put("place1", place1);
                                hashMap.put("id", id);

                                list.add(place);
                                listlocation.add(location);//listregionalism
                                listregionalism.add(regionalism);
                                listid.add(id);

                                listmap.add(hashMap);
                            }


                            Data.setList(list);//场所的名称
                            Data.setListid(listid);
                            Data.setListlocation(listlocation);
                            Data.setListregionalism(listregionalism);


                            Data.setListmap(listmap);
                        }else {
                            Toast.makeText(getContext(),"数据已经加载完毕！",Toast.LENGTH_SHORT).show();
                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                               myAdapter1 = new MyAdapter( listmap,getContext());
                               listView.setAdapter(myAdapter1);
                                mPullToRefreshListView.onRefreshComplete();


                                mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

                                    @Override
                                    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                                        mPullToRefreshListView.postDelayed(new Runnable() {

                                            @Override
                                            public void run() {
                                                mPullToRefreshListView.onRefreshComplete();
                                            }
                                        }, 1000);



//                                        Toast.makeText(getContext(),"hello",Toast.LENGTH_SHORT).show();


                                      //  mPullToRefreshListView.onRefreshComplete();
                                    }

                                    @Override
                                    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                                        mPullToRefreshListView.postDelayed(new Runnable() {

                                            @Override
                                            public void run() {
                                                mPullToRefreshListView.onRefreshComplete();
                                            }
                                        }, 1000);

                                   //     getdata1();
//                                        page++;
                                        getdata1();

//                                        Toast.makeText(getContext(),"hello1",Toast.LENGTH_SHORT).show();

                                     //   mPullToRefreshListView.onRefreshComplete();

                                    }
                                });

                            }
                        });


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



    private void getdata1() {
        page++;
        final HashMap<String, String> params = new HashMap<>();
        params.put("userid", "40");
        params.put("page", String.valueOf(page));
//    params.put("phone", "13333311338");


        JSONObject jsonObject = new JSONObject(params);
        String s = Data.getToken();
        //       Log.v("Tag",s);

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
                        String message = siteDevice.getMessage();
                        if (message.equals("ok")) {


                            for (int i = 0; i < siteDevice.getObject().size(); i++) {
                                HashMap<String, String> hashMap = new HashMap<>();
                                String place = siteDevice.getObject().get(i).getDeployment();
                                String place1 = siteDevice.getObject().get(i).getRegionalism();
                                String id = String.valueOf(siteDevice.getObject().get(i).getId());
                                String location = String.valueOf(siteDevice.getObject().get(i).getLocation());
                                String regionalism = String.valueOf(siteDevice.getObject().get(i).getRegionalism());
                                hashMap.put("place", place);
                                hashMap.put("place1", place1);
                                hashMap.put("id", id);

                                list.add(place);
                                listlocation.add(location);//listregionalism
                                listregionalism.add(regionalism);
                                listid.add(id);

                                listmap.add(hashMap);
                            }


                            Data.setList(list);//场所的名称
                            Data.setListid(listid);  //场所id1
                            Data.setListlocation(listlocation);
                            Data.setListregionalism(listregionalism);


                            Data.setListmap(listmap);
                        }else {
                            Toast.makeText(getContext(),"数据已经加载完毕！",Toast.LENGTH_SHORT).show();
                        }




                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                myAdapter1.notifyDataSetChanged();
                                mPullToRefreshListView.onRefreshComplete();

                          //      mPullToRefreshListView.onRefreshComplete();




                            }
                        });


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


    private void initData(){
        for(int a=0;a<3;a++){
            data.add("场所区域"+a);
        };
    }





    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }









    /**
     * 极光推送
     */

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {

        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    //    Logger.i("TAG", logs);
                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    //    Logger.i("TAG", logs);
                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 100 * 60);
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
                    //    Logger.e("TAG", logs);
            }

        }
    };
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    //   Logger.d("TAG", "Set alias in handler.");
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAliasAndTags(getContext(),
                            (String) msg.obj,
                            null,
                            mAliasCallback);
                    break;
                default:
                    //      Logger.i("TAG", "Unhandled msg - " + msg.what);
            }
        }
    };

    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mMessageReceiver, filter);
    }

//    public void click(View view) {
//        mMediaPlayer.stop();
//    }


    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
//                    if (!ExampleUtil.isEmpty(extras)) {
//                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
//                    }
//                    Toast.makeText(getContext(),"kk"+siteName,Toast.LENGTH_SHORT).show();

                    setCostomMsg(showMsg.toString());
                }
            } catch (Exception e){
            }
        }
    }
    private void setCostomMsg(String msg){
//        if (null != msgText) {
//            msgText.setText(msg);
//            msgText.setVisibility(android.view.View.VISIBLE);
//


      //     System.out.println("jj"+msg);

      //  String str1 = msg.replaceAll("\\\\","");
       // String  strings11=msg.replaceAll("\\\","");


    //    String  test=msg.replace("\\","");

//        JSONObject   jsonObject = null;
//        try {
//            jsonObject = new JSONObject(msg);
//            String message = jsonObject.get("data").toString();
//            Toast.makeText(getContext(),""+message,Toast.LENGTH_SHORT).show();
//
//            System.out.println("mmk"+message);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


        String  test=msg.replace("\\","");  //"{

        String  test1=test.replace("\"{","{");


        String  test3=test1.replace("}\"","}");


        String  test4=test3.replace("message :","");
        System.out.println("kkl"+test4);
        TuisongModel tuisongModel = new Gson().fromJson(test4,TuisongModel.class);

       // TuisongModel.DataBean dataBeans= tuisongModel.getData().;



        String deviceName = tuisongModel.getData().getDeviceName();
        String siteName = tuisongModel.getData().getSiteName();









//            mMediaPlayer  = MediaPlayer.create(this, R.raw.jcs); //
//            mMediaPlayer.setLooping(true); // 设置循环播放
//            mMediaPlayer.start(); // 开始播放
//
//
//
//
//
//
//        }


//
//
//        String test =  "\""+ msg + "\"";
//
//
//
//
//
//
//        System.out.println("kk"+test);
        Intent intent = new Intent(getContext(),FireAlarmActivity.class);


        String deviceName1 = deviceName;
        intent.putExtra("deviceName1", deviceName1);
        String siteName1 = siteName;
        intent.putExtra("siteName1", siteName1);

        startActivity(intent);





        getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {

                                            final ImageView finalImageView = imageView1;
                                            int visiblePosition = mPullToRefreshListView.getRefreshableView()
                                                    .getFirstVisiblePosition();

                                            View view1 = mPullToRefreshListView.getRefreshableView().getChildAt(
                                                    2 - visiblePosition + 2);//这里+2是因为PullToRefreshListView  刷新布局 算一个Headview，而我本身需求又添加了一个headview， 如果仅仅只是PullToRefreshListView的刷新布局  这里+1就行了

                                            if (view1 != null)//对View进行操作，这里换成你自己的需求
                                            {
//                                                if (FireAlarm.equals("0")){
//                                                    //  finalImageView.setImageResource(R.drawable.flashlight);
//                                                    finalImageView.setImageResource(R.drawable.guzhang);
//                                                    LL.setBackgroundColor(Color.parseColor("#ff3333"));
//                                                }



                                                finalImageView.setImageResource(R.drawable.guzhang);
                                                LL.setBackgroundColor(Color.parseColor("#ff3333"));

                                            }




                                        }
                                    }

        );
































//        mMediaPlayer  = MediaPlayer.create(getContext(), R.raw.jcs); //
//        mMediaPlayer.setLooping(true); // 设置循环播放
//        mMediaPlayer.start(); //开始播放
    }


//    @Override
//    public void onResume() {
//        super.onResume();
//      //  myAdapter1.notifyDataSetChanged();
//     //   Toast.makeText(getContext(),"kkkkk",Toast.LENGTH_SHORT).show();
//
//      //  getByOkGo();
//        if (flag){
//            myAdapter1.notifyDataSetChanged();
//        }
//        flag = true;
//    }

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        // TODO Auto-generated method stub
//        super.onHiddenChanged(hidden);
//        if (fragmentView != null && !hidden) {
//            showToast("刷新数据2");
//        }
//    }



}
