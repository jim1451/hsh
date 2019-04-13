package com.silence.commonframe.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.silence.commonframe.ApiService;
import com.silence.commonframe.Dialog.CDialog;
import com.silence.commonframe.R;
import com.silence.commonframe.adapter.MyAdapter;
import com.silence.commonframe.model.SiteDevice;
import com.silence.commonframe.model.SiteInfo;
import com.silence.commonframe.utils.Data;
import com.silence.commonframe.utils.SensorEventHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class AddSiteActivity extends AppCompatActivity implements LocationSource,
        AMapLocationListener {

    @Bind(R.id.imageView3)
    ImageView imageView3;
    @Bind(R.id.textView_comfirm)
    TextView textViewComfirm;
//    @Bind(R.id.map)
//    MapView map;
    @Bind(R.id.edit_site)
    EditText editSite;
    @Bind(R.id.textView_site1)
    TextView textViewSite1;
    @Bind(R.id.edit_detailedsite)
    EditText editDetailedsite;
    @Bind(R.id.tv_people)
    TextView tvPeople;
    @Bind(R.id.tv_photo)
    TextView tvPhoto;
    @Bind(R.id.location_errInfo_text)
    TextView locationErrInfoText;
    private AMap aMap;
    private MapView mapView;
    private OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;

    private TextView mLocationErrText;
    private static final int STROKE_COLOR = Color.argb(180, 3, 145, 255);
    private static final int FILL_COLOR = Color.argb(10, 0, 0, 180);
    private boolean mFirstFix = false;
    private Marker mLocMarker;
    private SensorEventHelper mSensorHelper;
    private Circle mCircle;
    public static final String LOCATION_MARKER_FLAG = "mylocation";


    private String place = "";
    private String place1 = "";
    private String Latitude = "";
    private String Longitude = "";


    private AMapLocationClient locationClientSingle = null;
    private  String data1;




    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

    private static final int PERMISSON_REQUESTCODE = 0;

    private boolean isNeedCheck = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_site);
        ButterKnife.bind(this);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        init();
        startSingleLocation();
        initWindows();
        tvPeople.setText(Data.getName());
        tvPhoto.setText(Data.getPhoto());
//        editSite.setText(place);
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



    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
            setUpMap();
        }
        mSensorHelper = new SensorEventHelper(this);
        if (mSensorHelper != null) {
            mSensorHelper.registerSensorListener();
        }
        mLocationErrText = (TextView) findViewById(R.id.location_errInfo_text);
        mLocationErrText.setVisibility(View.GONE);

    }


    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        // 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
    }


    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
        if (mSensorHelper != null) {
            mSensorHelper.registerSensorListener();
        }
        if (isNeedCheck){
            checkPermissions(needPermissions);
        }


    }

    /**
     * 检查权限
     *
     * @param
     * @since 2.5.0
     */
    private void checkPermissions(String... permissions) {
        //获取权限列表
        List<String> needRequestPermissonList = findDeniedPermissions(permissions);
        if (null != needRequestPermissonList
                && needRequestPermissonList.size() > 0) {
            //list.toarray将集合转化为数组
            ActivityCompat.requestPermissions(this,
                    needRequestPermissonList.toArray(new String[needRequestPermissonList.size()]),
                    PERMISSON_REQUESTCODE);
        }
    }


    /**
     * 检测是否说有的权限都已经授权
     *
     * @param grantResults
     * @return
     * @since 2.5.0
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] paramArrayOfInt) {
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(paramArrayOfInt)) {      //没有授权
                showMissingPermissionDialog();              //显示提示信息
                isNeedCheck = false;
            }
        }
    }

    /**
     * 显示提示信息
     *
     * @since 2.5.0
     */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(R.string.notifyTitle);
//        builder.setMessage(R.string.notifyMsg);

        // 拒绝, 退出应用
        builder.setNegativeButton("退出",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        builder.setPositiveButton("设置",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                });

        builder.setCancelable(false);

        builder.show();
    }


    /**
     * 启动应用的设置
     *
     * @since 2.5.0
     */
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }







    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        //for (循环变量类型 循环变量名称 : 要被遍历的对象)
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(this,
                    perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    this, perm)) {
                needRequestPermissonList.add(perm);
            }
        }
        return needRequestPermissonList;
    }










    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (mSensorHelper != null) {
            mSensorHelper.unRegisterSensorListener();
            mSensorHelper.setCurrentMarker(null);
            mSensorHelper = null;
        }
        mapView.onPause();
        deactivate();
        mFirstFix = false;
    }


    /**
     * 启动单次客户端定位
     */
    void startSingleLocation() {
        if (null == locationClientSingle) {
            locationClientSingle = new AMapLocationClient(this.getApplicationContext());
        }

        AMapLocationClientOption locationClientOption = new AMapLocationClientOption();
        //使用单次定位
        locationClientOption.setOnceLocation(true);
        // 地址信息
        locationClientOption.setNeedAddress(true);
        locationClientOption.setLocationCacheEnable(false);
        locationClientSingle.setLocationOption(locationClientOption);
        locationClientSingle.setLocationListener(locationSingleListener);
        locationClientSingle.startLocation();
    }


    /**
     * 单次客户端的定位监听
     */
    AMapLocationListener locationSingleListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            long callBackTime = System.currentTimeMillis();
            StringBuffer sb = new StringBuffer();
            sb.append("单次定位完成\n");
//            sb.append("回调时间: " + Utils.formatUTC(callBackTime, null) + "\n");
//            if(null == location){
//                sb.append("定位失败：location is null!!!!!!!");
//            } else {
//                sb.append(Utils.getLocationStr(location));
//            }

            if (null == location) {
                Toast.makeText(AddSiteActivity.this, "定位失败！", Toast.LENGTH_SHORT).show();
            } else {
                place = location.getAddress();
                place1 = location.getDistrict();
                Latitude  = String.valueOf(location.getLatitude());
                Longitude = String.valueOf(location.getLongitude());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                      //  editSite.setText(place);
                       textViewSite1.setText(place1);
                        editDetailedsite.setText(place);


                    }
                });
            }
           mLocationErrText.setText(location.describeContents());//textViewSite1
         //   textViewSite1.setText(location.describeContents());
        }
    };


//    public void click(View view) {
//        startSingleLocation();
//    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocMarker != null) {
            mLocMarker.destroy();
        }
        mapView.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
    }

    /**
     * 定位成功后回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null
                    && amapLocation.getErrorCode() == 0) {

                mLocationErrText.setVisibility(View.GONE);
                LatLng location = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());
                if (!mFirstFix) {
                    mFirstFix = true;
                    addCircle(location, amapLocation.getAccuracy());//添加定位精度圆
                    addMarker(location);//添加定位图标
                    mSensorHelper.setCurrentMarker(mLocMarker);//定位图标旋转
                    aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 18));
                } else {
                    mCircle.setCenter(location);
                    mCircle.setRadius(amapLocation.getAccuracy());
                    mLocMarker.setPosition(location);
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(location));
                }
            } else {
                String errText = "定位失败," + amapLocation.getErrorCode() + ": " + amapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
                mLocationErrText.setVisibility(View.VISIBLE);
                mLocationErrText.setText(errText);
            }
        }
    }

    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }

    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    private void addCircle(LatLng latlng, double radius) {
        CircleOptions options = new CircleOptions();
        options.strokeWidth(1f);
        options.fillColor(FILL_COLOR);
        options.strokeColor(STROKE_COLOR);
        options.center(latlng);
        options.radius(radius);
        mCircle = aMap.addCircle(options);
    }

    private void addMarker(LatLng latlng) {
        if (mLocMarker != null) {
            return;
        }
        MarkerOptions options = new MarkerOptions();
        options.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(this.getResources(),
                R.mipmap.navi_map_gps_locked)));
        options.anchor(0.5f, 0.5f);
        options.position(latlng);
        mLocMarker = aMap.addMarker(options);
        mLocMarker.setTitle(LOCATION_MARKER_FLAG);
    }


    public void click(View view) {
        finish();
    }

    public void click1(View view) {

        getdata();
    }


    private  void getdata1(){



        CDialog.Builder builder = new CDialog.Builder(this);
        //    AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.dialog));
        //builder.setIcon(R.drawable.ic_launcher);
//                builder.setTitle("你确定要退出系统吗？");
        builder.setMessage("是否添加对应场所联系人？");
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
                Intent intent = new Intent(AddSiteActivity.this,AddlinkmandetailActivity.class);
                intent.putExtra("data",data1);
                startActivity(intent);

                finish();

                //  return;

                //  System.exit(0);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
             //   getdata();
                finish();
            }
        });
        builder.show();



    }




    private void getdata() {
//editSite




        if (TextUtils.isEmpty(editSite.getText().toString().trim())){
            Toast.makeText(this,"请填写对应的场所地址",Toast.LENGTH_SHORT).show();
            return;
        }





        HashMap<String, String> params = new HashMap<>();
        params.put("deployment", editSite.getText().toString().trim());//场所名称
//        params.put("userid", Data.getUserid());
        params.put("regionName", textViewSite1.getText().toString());//区域名称
        params.put("location", editDetailedsite.getText().toString());// 详细地址
        params.put("longtitude", Longitude);
        params.put("regionCode", "123456");
        params.put("latitude", Latitude);



//    params.put("phone", "13333311338");


        JSONObject jsonObject = new JSONObject(params);
        String s = Data.getToken();
        Log.v("Tag",s);

        String str = jsonObject.toString();


        OkGo.post(ApiService.httpUrl1+"/app/addSite")
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

//                        SiteInfo siteInfo = new Gson().fromJson(s, SiteInfo.class);
//                      if(siteInfo.getMessage().equals("ok")) {
//                          Toast.makeText(AddSiteActivity.this,"场所添加成功"+s,Toast.LENGTH_SHORT).show();
//
//                      }else {
//                          Toast.makeText(AddSiteActivity.this,"场所添加没有成功！"+s,Toast.LENGTH_SHORT).show();
//                      }



                        JSONObject jsonObject = null;

                        try {
                            jsonObject = new JSONObject(s);
                            String message = jsonObject.get("msg").toString();
                            String data = jsonObject.get("data").toString();
                            if (message.equals("success")){
                                data1 =data;
                                Toast.makeText(AddSiteActivity.this,"添加成功！",Toast.LENGTH_SHORT).show();
                                getdata1();
                                // return;
                            }else {
                                Toast.makeText(AddSiteActivity.this,""+message,Toast.LENGTH_SHORT).show();

                                return;
                            }







                        } catch (JSONException e) {
                            e.printStackTrace();
                        }





















                       // Toast.makeText(AddSiteActivity.this,"dd"+s,Toast.LENGTH_SHORT).show();

   //                   Toast.makeText(AddSiteActivity.this,"场所添加成功！"+s,Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        //  mTextView2.setText(e.getMessage());
                     //   Toast.makeText(getContext(),"NOsuccess "+e,Toast.LENGTH_SHORT).show();
                        System.out.println("sss"+e);
                    }
                });

                  //   getdata1();
    }
}
