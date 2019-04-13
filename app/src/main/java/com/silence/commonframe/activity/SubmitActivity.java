package com.silence.commonframe.activity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


//import com.dmcbig.mediapicker.PickerActivity;
//import com.dmcbig.mediapicker.PickerConfig;
//import com.dmcbig.mediapicker.entity.Media;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.silence.commonframe.ApiService;
import com.silence.commonframe.R;
import com.silence.commonframe.adapter.ImageAdapter;
import com.silence.commonframe.adapter.MyAdapter;
import com.silence.commonframe.model.SiteDevice;
import com.silence.commonframe.model.SiteModel;
import com.silence.commonframe.model.UploadModel;
import com.silence.commonframe.soundrecording.AudioRecoderUtils;
import com.silence.commonframe.soundrecording.PopupWindowFactory;
import com.silence.commonframe.soundrecording.TimeUtils;
import com.silence.commonframe.utils.Data;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class SubmitActivity extends AppCompatActivity {


    private static final int REQUEST_CODE = 0x00000011;


    private static final int VIDEO_WITH_CAMERA = 0x00000014;

    private RecyclerView rvImage;
    private ImageAdapter mAdapter;
    private   ArrayList<String> images1;





    static final int VOICE_REQUEST_CODE = 66;

    private Button mButton;
    private ImageView mImageView;
    private TextView mTextView;
    private AudioRecoderUtils mAudioRecoderUtils;
    private Context context;
    private PopupWindowFactory mPop;
    private LinearLayout rl;
    private  File pictureFile;
    List<File> files = new ArrayList<File>();
    private  String    video;


   private String  fileId;
  private   String  videoId;
  private String soundId;

//    ArrayList<Media> select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);


        rvImage = (RecyclerView) findViewById(R.id.rv_image);
        rvImage.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter = new ImageAdapter(this);
        rvImage.setAdapter(mAdapter);








        context = this;

        rl = (LinearLayout) findViewById(R.id.rl);

        mButton = (Button) findViewById(R.id.button);

        //PopupWindow的布局文件
        final View view = View.inflate(this, R.layout.layout_microphone, null);

        mPop = new PopupWindowFactory(this,view);

        //PopupWindow布局文件里面的控件
        mImageView = (ImageView) view.findViewById(R.id.iv_recording_icon);
        mTextView = (TextView) view.findViewById(R.id.tv_recording_time);

        mAudioRecoderUtils = new AudioRecoderUtils();

        //录音回调
        mAudioRecoderUtils.setOnAudioStatusUpdateListener(new AudioRecoderUtils.OnAudioStatusUpdateListener() {

            //录音中....db为声音分贝，time为录音时长
            @Override
            public void onUpdate(double db, long time) {
                mImageView.getDrawable().setLevel((int) (3000 + 6000 * db / 100));
                mTextView.setText(TimeUtils.long2String(time));
            }

            //录音结束，filePath为保存路径
            @Override
            public void onStop(String filePath) {
                Toast.makeText(SubmitActivity.this, "录音保存在：" + filePath, Toast.LENGTH_SHORT).show();
                mTextView.setText(TimeUtils.long2String(0));

                final File file = new File(filePath);
               // String  mAbsolutePath = file.getAbsolutePath();

                List<File> files = new ArrayList<File>();
                files.add(file);


                uploadSound("https://www.hsh-iot.com/hsh_app/sysCore/fileUpload?attachType=audio", "audio", files);

            }
        });


        //6.0以上需要权限申请
        requestPermissions();

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














    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            images1 = data.getStringArrayListExtra(ImageSelectorUtils.SELECT_RESULT);
            mAdapter.refresh(images1);




            for (int j= 0;j<images1.size();j++){
                pictureFile = new File(images1.get(j));

                files.add(pictureFile);
            }


            //String  mAbsolutePath = file.getAbsolutePath();

//        List<File> files = new ArrayList<File>();
//        files.add(pictureFile);

            uploadFiles("https://www.hsh-iot.com/hsh_app/sysCore/fileUpload?attachType=picture", "picture", files);


        }








//        if(requestCode==200&&resultCode==PickerConfig.RESULT_CODE){
//            select=data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT);
//            Log.i("select","select.size"+select.size());
//            for(Media media:select){
//                Log.i("media",media.path);
//                Toast.makeText(this,"yy"+media.path,Toast.LENGTH_SHORT).show();
//                Log.e("media","s:"+media.size);
//              //  imageView.setImageURI(Uri.parse(media.path));
//            }
//        }



        try{
            if (resultCode == Activity.RESULT_OK && requestCode == VIDEO_WITH_CAMERA){
                Uri uri = data.getData();
         //        String test =     uri.getPath();
     //           Log.e(TAG, "onActivityResult: " + uri.toString());
        //        Toast.makeText(this,"kk"+test,Toast.LENGTH_SHORT).show();







//                if ( null == uri ) return null;null
                final String scheme = uri.getScheme();
              if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
                    Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
                    if ( null != cursor ) {
                        if ( cursor.moveToFirst() ) {
                            int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                            if ( index > -1 ) {
                                 video = cursor.getString( index );
                              //  Toast.makeText(this,"kk"+kk,Toast.LENGTH_SHORT).show();
                            }
                        }
                        cursor.close();

                    }
                }



//                for (int j= 0;j<images1.size();j++){
//                    pictureFile = new File(images1.get(j));
//
//                    files.add(pictureFile);
//                }


                pictureFile = new File(video);

                files.add(pictureFile);
                uploadVideo("https://www.hsh-iot.com/hsh_app/sysCore/fileUpload?attachType=video", "video", files);




            }
        }catch (Exception e){
            e.printStackTrace();
        }




    }



    public void myclick(View view) {
        finish();
    }

    public void click1(View view) {
        ImageSelectorUtils.openPhoto(SubmitActivity.this, REQUEST_CODE, false, 9);
    }




    private void uploadFiles(String url, String keyName, List<File> files){
        OkGo.post(url)//
                .tag(this)//
                //.isMultipart(true)       // 强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
                //.params("param1", "paramValue1")        // 这里可以上传参数
              //  .params("file1", new File("filepath1"))   // 可以添加文件上传
                //.params("file2", new File("filepath2"))     // 支持多文件同时添加上传
                .addFileParams(keyName, files)    // 这里支持一个key传多个文件
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        //上传成功
                    //    Toast.makeText(getApplicationContext(), "上传成功"+s, Toast.LENGTH_SHORT).show();

                        UploadModel uploadModel = new Gson().fromJson(s,UploadModel.class);
                        List<UploadModel.DataBean>  listBeans =  uploadModel.getData();

                        System.out.println("ss"+listBeans);
                      for (int j = 0;j<listBeans.size();j++){
                           fileId=  listBeans.get(j).getAttachId();
                      }
                        if (uploadModel.getCode()==0){
                          Toast.makeText(SubmitActivity.this,"图片上传成功！",Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        //这里回调上传进度(该回调在主线程,可以直接更新ui)
//                        mProgressBar.setProgress((int) (100 * progress));
//                        mTextView2.setText("已上传" + currentSize/1024/1024 + "MB, 共" + totalSize/1024/1024 + "MB;");
                    }
                });
    }




    private void uploadVideo(String url, String keyName, List<File> files){
        OkGo.post(url)//
                .tag(this)//
                //.isMultipart(true)       // 强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
                //.params("param1", "paramValue1")        // 这里可以上传参数
                //  .params("file1", new File("filepath1"))   // 可以添加文件上传
                //.params("file2", new File("filepath2"))     // 支持多文件同时添加上传
                .addFileParams(keyName, files)    // 这里支持一个key传多个文件
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        //上传成功
                    //    Toast.makeText(getApplicationContext(), "上传成功"+s, Toast.LENGTH_SHORT).show();

                        UploadModel uploadModel = new Gson().fromJson(s,UploadModel.class);
                        List<UploadModel.DataBean>  listBeans =  uploadModel.getData();

                     //   System.out.println("ss"+listBeans);
                        for (int j = 0;j<listBeans.size();j++){
                             videoId=  listBeans.get(j).getAttachId();
                        }
                        if (uploadModel.getCode()==0){
                            Toast.makeText(SubmitActivity.this,"视屏上传成功！",Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        //这里回调上传进度(该回调在主线程,可以直接更新ui)
//                        mProgressBar.setProgress((int) (100 * progress));
//                        mTextView2.setText("已上传" + currentSize/1024/1024 + "MB, 共" + totalSize/1024/1024 + "MB;");
                    }
                });
    }









    private void uploadSound(String url, String keyName, List<File> files){
        OkGo.post(url)//
                .tag(this)//
                //.isMultipart(true)       // 强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
                //.params("param1", "paramValue1")        // 这里可以上传参数
                //  .params("file1", new File("filepath1"))   // 可以添加文件上传
                //.params("file2", new File("filepath2"))     // 支持多文件同时添加上传
                .addFileParams(keyName, files)    // 这里支持一个key传多个文件
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        //上传成功
                    //    Toast.makeText(getApplicationContext(), "上传成功"+s, Toast.LENGTH_SHORT).show();

                        UploadModel uploadModel = new Gson().fromJson(s,UploadModel.class);
                        List<UploadModel.DataBean>  listBeans =  uploadModel.getData();

                        //   System.out.println("ss"+listBeans);
                        for (int j = 0;j<listBeans.size();j++){
                            soundId=  listBeans.get(j).getAttachId();
                        }
                        if (uploadModel.getCode()==0){
                            Toast.makeText(SubmitActivity.this,"语音上传成功！",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        //这里回调上传进度(该回调在主线程,可以直接更新ui)
//                        mProgressBar.setProgress((int) (100 * progress));
//                        mTextView2.setText("已上传" + currentSize/1024/1024 + "MB, 共" + totalSize/1024/1024 + "MB;");
                    }
                });
    }





    /**
     * 开启扫描之前判断权限是否打开
     */
    private void requestPermissions() {
        //判断是否开启摄像头权限
        if ((ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)
                ) {
            StartListener();

            //判断是否开启语音权限
        } else {
            //请求获取摄像头权限
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, VOICE_REQUEST_CODE);
        }

    }

    /**
     * 请求权限回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == VOICE_REQUEST_CODE) {
            if ((grantResults[0] == PackageManager.PERMISSION_GRANTED) && (grantResults[1] == PackageManager.PERMISSION_GRANTED) ) {
                StartListener();
            } else {
                Toast.makeText(context, "已拒绝权限！", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void StartListener(){
        //Button的touch监听
        mButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){

                    case MotionEvent.ACTION_DOWN:

                        mPop.showAtLocation(rl, Gravity.CENTER, 0, 0);

                        mButton.setText("松开保存");
                        mAudioRecoderUtils.startRecord();


                        break;

                    case MotionEvent.ACTION_UP:

                        mAudioRecoderUtils.stopRecord();        //结束录音（保存录音文件）
//                        mAudioRecoderUtils.cancelRecord();    //取消录音（不保存录音文件）
                        mPop.dismiss();
                        mButton.setText("按住说话");

                        break;
                }
                return true;
            }
        });
    }

    public void click(View view) {
       // go();


        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//设置视频录制的最长时间
        intent.putExtra (MediaStore.EXTRA_DURATION_LIMIT,30);
//设置视频录制的画质
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult (intent, VIDEO_WITH_CAMERA);


    }

    public void uploadClick(View view) {
        getdata();

    }



    private void getdata() {

        final HashMap<String, String> params = new HashMap<>();
        params.put("auIds", videoId);
        params.put("explain", "描述dddd");
        params.put("mvIds", soundId);
        params.put("picIds", fileId);
        params.put("proType", "1");
        params.put("processId", "789654");

//    params.put("phone", "13333311338");


        JSONObject jsonObject = new JSONObject(params);
        String s = Data.getToken();
        //       Log.v("Tag",s);

        String str = jsonObject.toString();


        OkGo.post(ApiService.httpUrl1+"/app/processDeviceTrouble")
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
                        Toast.makeText(SubmitActivity.this,"数据提交成功！",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        //  mTextView2.setText(e.getMessage());
                       // Toast.makeText(getContext(),"NOsuccess "+e,Toast.LENGTH_SHORT).show();
                        //System.out.println("sss"+e);
                         Toast.makeText(SubmitActivity.this,"NOsuccess "+e,Toast.LENGTH_SHORT).show();
                    }
                });


    }























//    ArrayList<Media> select;
//   private void go(){
//        Intent intent =new Intent(SubmitActivity.this, PickerActivity.class);
//        intent.putExtra(PickerConfig.SELECT_MODE,PickerConfig.PICKER_IMAGE_VIDEO);//default image and video (Optional)
//
//        //   intent.putExtra(PickerConfig.SELECT_MODE,PickerConfig.PICKER_VIDEO);//default image and video (Optional)
//        long maxSize=188743680L;//long long long
//        intent.putExtra(PickerConfig.MAX_SELECT_SIZE,maxSize); //default 180MB (Optional)
//        intent.putExtra(PickerConfig.MAX_SELECT_COUNT,1);  //default 40 (Optional)
//        intent.putExtra(PickerConfig.DEFAULT_SELECTED_LIST,select); // (Optional)
//        SubmitActivity.this.startActivityForResult(intent,200);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==200&&resultCode==PickerConfig.RESULT_CODE){
//            select=data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT);
//            Log.i("select","select.size"+select.size());
//            for(Media media:select){
//                Log.i("media",media.path);
//                Toast.makeText(this,"yy"+media.path,Toast.LENGTH_SHORT).show();
//                Log.e("media","s:"+media.size);
//                imageView.setImageURI(Uri.parse(media.path));
//            }
//        }
//    }
}
