/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.silence.commonframe.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.aaf.myzxing.utils.Constant;
//import com.example.aaf.myzxing.zxing.ScanListener;
//import com.example.aaf.myzxing.zxing.ScanManager;
//import com.example.aaf.myzxing.zxing.decode.DecodeThread;
//import com.example.aaf.myzxing.zxing.decode.Utils;
import com.google.zxing.Result;
import com.silence.commonframe.Dialog.CDialog;
import com.silence.commonframe.R;
import com.silence.commonframe.zxing.utils.Constant;
import com.silence.commonframe.zxing.zxing.ScanListener;
import com.silence.commonframe.zxing.zxing.ScanManager;
import com.silence.commonframe.zxing.zxing.decode.DecodeThread;
import com.silence.commonframe.zxing.zxing.decode.Utils;

import static com.lzy.okgo.OkGo.getContext;
//import com.liang.scancode.utils.Constant;
//import com.liang.scancode.zxing.ScanListener;
//import com.liang.scancode.zxing.ScanManager;
//import com.liang.scancode.zxing.decode.DecodeThread;
//import com.liang.scancode.zxing.decode.Utils;

//import butterknife.Bind;
//import butterknife.ButterKnife;


/**
 * 二维码扫描使用
 *
 * @author 刘红亮  2015年4月29日  下午5:49:45
 */
public final class CommonScanActivity extends Activity implements ScanListener, View.OnClickListener {
    static final String TAG = CommonScanActivity.class.getSimpleName();
    SurfaceView scanPreview = null;
    View scanContainer;
    View scanCropView;
    ImageView scanLine;
    ScanManager scanManager;
    TextView iv_light;
    TextView qrcode_g_gallery;
    TextView qrcode_ic_back;
    final int PHOTOREQUESTCODE = 1111;

  //  @Bind(R.id.service_register_rescan)
    Button rescan;
 //   @Bind(R.id.scan_image)
    ImageView scan_image;
 //   @Bind(R.id.authorize_return)
    ImageView authorize_return;
    private int scanMode;//扫描模型（条形，二维码，全部）

   // @Bind(R.id.common_title_TV_center)
    TextView title;
 //   @Bind(R.id.scan_hint)
    TextView scan_hint;
  //  @Bind(R.id.tv_scan_result)
    TextView tv_scan_result;

    private final int PERMISSION_REQUEST = 0xa01;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_scan_code);
      //  ButterKnife.bind(this);
        rescan = (Button) findViewById(R.id.service_register_rescan);
        scan_image = (ImageView) findViewById(R.id.scan_image);
        authorize_return = (ImageView) findViewById(R.id.authorize_return);

        title = (TextView) findViewById(R.id.common_title_TV_center);
        scan_hint = (TextView) findViewById(R.id.scan_hint);
        tv_scan_result = (TextView) findViewById(R.id.tv_scan_result);


        scanMode=getIntent().getIntExtra(Constant.REQUEST_SCAN_MODE,Constant.REQUEST_SCAN_MODE_ALL_MODE);
        initView();
        initWindows();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST);
            }
        }



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




    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (permissions != null && permissions.length > 0) {
            for (String s : permissions)
                Log.d(TAG + "权限列表", s + "");
        }

        switch (requestCode) {
            case PERMISSION_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "授权获得");
                } else {
                    Log.d(TAG, "授权未获得");
                }

                break;
        }
    }






    void initView() {
        switch (scanMode){
            case DecodeThread.BARCODE_MODE:
                title.setText(R.string.scan_barcode_title);
                scan_hint.setText(R.string.scan_barcode_hint);
                break;
            case DecodeThread.QRCODE_MODE:
                title.setText(R.string.scan_qrcode_title);
                scan_hint.setText(R.string.scan_qrcode_hint);
                break;
            case DecodeThread.ALL_MODE:
                title.setText(R.string.scan_allcode_title);
                scan_hint.setText(R.string.scan_allcode_hint);
                break;
        }
        scanPreview = (SurfaceView) findViewById(R.id.capture_preview);
        scanContainer = findViewById(R.id.capture_container);
        scanCropView = findViewById(R.id.capture_crop_view);
        scanLine = (ImageView) findViewById(R.id.capture_scan_line);
        qrcode_g_gallery = (TextView) findViewById(R.id.qrcode_g_gallery);
        qrcode_g_gallery.setOnClickListener(this);
     //   qrcode_ic_back = (TextView) findViewById(R.id.qrcode_ic_back);
     //   qrcode_ic_back.setOnClickListener(this);
        iv_light = (TextView) findViewById(R.id.iv_light);
        iv_light.setOnClickListener(this);
        rescan.setOnClickListener(this);
        authorize_return.setOnClickListener(this);
        //构造出扫描管理器
        scanManager = new ScanManager(this, scanPreview, scanContainer, scanCropView, scanLine, scanMode,this);
    }

    @Override
    public void onResume() {
        super.onResume();
        scanManager.onResume();
        rescan.setVisibility(View.INVISIBLE);
        scan_image.setVisibility(View.GONE);
    }

    @Override
    public void onPause() {
        super.onPause();
        scanManager.onPause();
    }
    /**
     *
     */
    public void scanResult(Result rawResult, Bundle bundle) {
        //扫描成功后，扫描器不会再连续扫描，如需连续扫描，调用reScan()方法。
        //scanManager.reScan();
//		Toast.makeText(that, "result="+rawResult.getText(), Toast.LENGTH_LONG).show();

        if (!scanManager.isScanning()) { //如果当前不是在扫描状态
            //设置再次扫描按钮出现
            rescan.setVisibility(View.VISIBLE);
            scan_image.setVisibility(View.VISIBLE);
            Bitmap barcode = null;
            byte[] compressedBitmap = bundle.getByteArray(DecodeThread.BARCODE_BITMAP);
            if (compressedBitmap != null) {
                barcode = BitmapFactory.decodeByteArray(compressedBitmap, 0, compressedBitmap.length, null);
                barcode = barcode.copy(Bitmap.Config.ARGB_8888, true);
            }
            scan_image.setImageBitmap(barcode);
        }
        rescan.setVisibility(View.VISIBLE);
        scan_image.setVisibility(View.VISIBLE);
        tv_scan_result.setVisibility(View.VISIBLE);
        String  t = rawResult.getText();
        tv_scan_result.setText("结果："+t.substring(47,61));
        String t1 = t.substring(47,61);

//        String str = "abc,12,3yy98,0";
//        String[] strs=str.split(",");
//        for(int i=0,len=strs.length;i<len;i++){
//            System.out.println(strs[i].toString());
//        }




        Intent intent = new Intent(this,AdddevicedetailActivity.class);

        intent.putExtra("id", t1);
      //  intent.putExtra("com.xiaoluo.android_intent.name", "xiaoluo");





        startActivity(intent);
        finish();

    }

    void startScan() {
        if (rescan.getVisibility() == View.VISIBLE) {
            rescan.setVisibility(View.INVISIBLE);
            scan_image.setVisibility(View.GONE);
            scanManager.reScan();
        }
    }


//    public void click3(View view){
//
//        scanManager.reScan();
//    }




    @Override
    public void scanError(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        //相机扫描出错时
        if(e.getMessage()!=null&&e.getMessage().startsWith("相机")){
            scanPreview.setVisibility(View.INVISIBLE);
        }
    }

    public void showPictures(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String photo_path;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PHOTOREQUESTCODE:
                    String[] proj = {MediaStore.Images.Media.DATA};
                    Cursor cursor = this.getContentResolver().query(data.getData(), proj, null, null, null);
                    if (cursor.moveToFirst()) {
                        int colum_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        photo_path = cursor.getString(colum_index);
                        if (photo_path == null) {
                            photo_path = Utils.getPath(getApplicationContext(), data.getData());
                        }
                        scanManager.scanningImage(photo_path);
                    }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qrcode_g_gallery:
             //   showPictures(PHOTOREQUESTCODE);

            //    Toast.makeText(this,"fdasfda",Toast.LENGTH_SHORT).show();


           //     AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialog1);

                CDialog.Builder builder = new CDialog.Builder(this);
            //    AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.dialog));
                //builder.setIcon(R.drawable.ic_launcher);
                builder.setTitle("请输入对应的设备编号");
                //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
                View view = LayoutInflater.from(this).inflate(R.layout.item_addsite, null);
                //    设置我们自己定义的布局文件作为弹出框的Content
                builder.setView(view);

                final EditText deviceno = (EditText)view.findViewById(R.id.deviceno);


                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        String t1 = deviceno.getText().toString().trim();
                       // String b = password.getText().toString().trim();
                        //    将输入的用户名和密码打印出来
                    //    Toast.makeText(CommonScanActivity.this, "用户名: "  + t1, Toast.LENGTH_SHORT).show();


                        Intent intent = new Intent(CommonScanActivity.this,AdddevicedetailActivity.class);

                        intent.putExtra("id", t1);
                        //  intent.putExtra("com.xiaoluo.android_intent.name", "xiaoluo");





                        startActivity(intent);
                        finish();
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

















                break;
            case R.id.iv_light:
                scanManager.switchLight();
                break;
//            case R.id.qrcode_ic_back:
//                finish();
//                break;
//            case R.id.service_register_rescan://再次开启扫描
//                startScan();
//                break;
            case R.id.authorize_return:
                finish();
                break;
            default:
                break;
        }
    }

}