package com.silence.commonframe.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.silence.commonframe.ApiService;
import com.silence.commonframe.R;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;

import com.silence.commonframe.model.DeviceType;
import com.silence.commonframe.model.VerifyModel;
import com.silence.commonframe.utils.Data;
import com.silence.commonframe.utils.TimeButton;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class RegisterActivity extends Activity {
    @Bind(R.id.edit_name)
    EditText editName;
    @Bind(R.id.edit_photo)
    EditText editPhoto;
    @Bind(R.id.edit_identifying)
    EditText editIdentifying;
    @Bind(R.id.btn_identifying)
    TimeButton btnIdentifying;
    @Bind(R.id.edit_password)
    EditText editPassword;
    @Bind(R.id.edit_password1)//edit_password
    EditText editPassword1;




    @Bind(R.id.btn_register)
    Button btnRegister;

   // private  String data="";
   // private TimeCount time = new TimeCount();
 //  private OkHttpClient client;


//    public static final String POST_URL1 = "http://116.62.152.51:9005/api/teacher/midify/password";
//    public static final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
//    private String AuthorizationString="tetsAuthorizationString";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.register_acticity1);
        ButterKnife.bind(this);


        /**
         * 注册功能
         */


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (editPhoto.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this,"电话号码不能为空！",Toast.LENGTH_SHORT).show();
                    return;
                }else if(editPhoto.getText().length()!=11){
                    Toast.makeText(RegisterActivity.this,"电话号码位数有问题，请重新确认！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (editPassword.getText().toString().trim().equals(editPassword.getText().toString().trim())== false){
                    Toast.makeText(RegisterActivity.this,"两次填写密码有误，请重新查证！",Toast.LENGTH_SHORT).show();
                    return;
                }




                 Data.setPhoto(editPhoto.getText().toString());

                getdate1();




            }
        });



        //按钮的touch触摸事件
        btnIdentifying.setOnTouchListener(new View.OnTouchListener() {
           @Override
           public boolean onTouch(View v, MotionEvent event) {
                            switch (event.getAction()) {
                              case MotionEvent.ACTION_DOWN: //按下的动作
                                      if(editPhoto.getText().toString().equals("")){
                                          Toast.makeText(RegisterActivity.this,"电话号码不能为空！",Toast.LENGTH_SHORT).show();
                                            return true;
                                      }
                                  if(editPhoto.getText().length() != 11){
                                      Toast.makeText(RegisterActivity.this,"手机号位数不对！",Toast.LENGTH_SHORT).show();
                                      return true;
                                  }




                                        break;
//                               case MotionEvent.ACTION_MOVE: //滑动的动作
//                                       Log.d(TAG, "btn is MotionEvent.ACTION_MOVE");
//                                        break;
//                                  case MotionEvent.ACTION_UP: //离开的动作
//                                     Log.d(TAG, "btn is MotionEvent.ACTION_UP");
//                                  break;
                            }

                           return false;  //默认的返回值
                      }
       });





        btnIdentifying.onCreate(savedInstanceState);
        btnIdentifying.setTextAfter("").setTextBefore("获取验证码").setLenght(30 * 1000);//秒后重新获取
         btnIdentifying.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
               //  getRegist();


             //    OkGoGet();
                 getByOkGo();
             }
         });
    }




    private void   permitget(){


        Acp.getInstance(this).request(new AcpOptions.Builder()
                        .setPermissions(Manifest.permission.INTERNET
                               )
                        /*以下为自定义提示语、按钮文字
                        .setDeniedMessage()
                        .setDeniedCloseBtn()
                        .setDeniedSettingBtn()
                        .setRationalMessage()
                        .setRationalBtn()*/
                        .build(),
                new AcpListener() {
                    @Override
                    public void onGranted() {
//                        writeSD();
//                        getIMEI();
                        postdate();
                    }

                    @Override
                    public void onDenied(List<String> permissions) {
//                        makeText(permissions.toString() + "权限拒绝");
                    }
                });
    }



//private void   okGoGet(){
//    OkGo.<String>get(url)
//            .tag(this)
//            .headers("header1", "headerValue1")
//            .params("param1", "paramValue1")
//            .execute(new StringCallback() {
//                @Override
//                public void onSuccess(String s, Call call, Response response) {
//
//                }
//
//                @Override
//                public void onSuccess(Response<String> response)
//                {
//
//                    String data = response.body();
//
//                }
//                @Override
//                public void onError(Response<String> response)
//                {
//                    super.onError(response);}
//            });
//}


    private void OkGoGet() {

        OkGo.get(ApiService.httpUrl1+"/app/phoneCodeVerify")                            // 请求方式和请求url
                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .params("phone", "13027210708")
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Toast.makeText(RegisterActivity.this,"success",Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                      //  Log.i("数据：","==2"+s);

                    }
                });


    }











    private void  getRegist(){
        HashMap<String, String> params = new HashMap<>();
    params.put("phonenumber", editPhoto.getText().toString().trim());
    params.put("type", "regist");
//    params.put("phone", "13333311338");


        JSONObject jsonObject = new JSONObject(params);




        OkGo.post(ApiService.httpUrl+"getphonecode")
                .tag(this)
                .cacheKey("cachePostRegister")
                .cacheMode(CacheMode.DEFAULT)
                .upJson(jsonObject.toString())
 //               .headers("token", Data.getToken())
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



                        Toast.makeText(RegisterActivity.this,"验证码发送成功，请稍等！"+s,Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        //  mTextView2.setText(e.getMessage());
                        Toast.makeText(RegisterActivity.this,"NOsuccess "+e,Toast.LENGTH_SHORT).show();
                        System.out.println("sss"+e);
                    }
                });

    }


    private void  getdate1(){
        HashMap<String, String> params = new HashMap<>();
    params.put("username", editName.getText().toString().trim());
    params.put("phonecode", editIdentifying.getText().toString().trim());
    params.put("password1", editPassword.getText().toString().trim());
    params.put("password2", editPassword.getText().toString().trim());
    params.put("phone", editPhoto.getText().toString().trim());


        JSONObject jsonObject = new JSONObject(params);


        String url = ApiService.httpUrl1+"/app/registUser";

        OkGo.post(url)
                .tag(this)
                .cacheKey("cachePostRegister")
                .cacheMode(CacheMode.DEFAULT)
                .upJson(jsonObject.toString())
         //       .headers("token", Data.getToken())
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



                        JSONObject jsonObject = null;

                        try {
                            jsonObject = new JSONObject(s);
                            String message = jsonObject.get("msg").toString();
                            String data = jsonObject.get("data").toString();
                            if (message.equals("success")){

                                Toast.makeText(RegisterActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();

                                // return;
                            }else {
                                Toast.makeText(RegisterActivity.this,""+message,Toast.LENGTH_SHORT).show();

                                return;
                            }







                        } catch (JSONException e) {
                            e.printStackTrace();
                        }









                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        //  mTextView2.setText(e.getMessage());
                        Toast.makeText(RegisterActivity.this,"NOsuccess "+e,Toast.LENGTH_SHORT).show();
                        System.out.println("sss"+e);
                    }
                });

    }















    private void  getdate(){
    HashMap<String, String> params = new HashMap<>();
//    params.put("username", "kkll");
//    params.put("password", "123456");
//    params.put("phone", "13333311338");


    JSONObject jsonObject = new JSONObject(params);




    OkGo.post("https://www.hsh-iot.cn/YunGanKeJi/getdevicetype")
            .tag(this)
            .cacheKey("cachePostRegister")
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



                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(Call call, Response response, Exception e) {
                    super.onError(call, response, e);
                    //  mTextView2.setText(e.getMessage());
                    Toast.makeText(RegisterActivity.this,"NOsuccess "+e,Toast.LENGTH_SHORT).show();
                    System.out.println("sss"+e);
                }
            });

}








    private void postdate(){
//        if (TextUtils.isEmpty(editName.getText().toString())||TextUtils.isEmpty(editPhoto.getText().toString())){
//            Toast.makeText(RegisterActivity.this,"用户名或者密码不能为空！",Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(editIdentifying.getText().toString())){
//            Toast.makeText(RegisterActivity.this,"手机验证码，不能为空！",Toast.LENGTH_SHORT).show();
//            return;
//        }else if(editIdentifying.getText().length() != 11){
//            Toast.makeText(RegisterActivity.this,"手机号位数不对！",Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        if (!editPassword.getText().toString().trim().equals(editPassword1.getText().toString().trim())){
//            Toast.makeText(RegisterActivity.this,"输入的密码不一致，请重新输入！",Toast.LENGTH_SHORT).show();
//            return;
//        }


        HashMap<String, String> params = new HashMap<>();
        params.put("password1", "kkll");
        params.put("password2", "123456");
        params.put("phonecode", "13333311338");
        params.put("username", "13333311338");

        JSONObject jsonObject = new JSONObject(params);




        OkGo.post(ApiService.httpUrl1+"/app/registUser")
                .tag(this)
                .cacheKey("cachePostRegister")
                .cacheMode(CacheMode.DEFAULT)
                .upJson(jsonObject.toString())
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



                        Toast.makeText(RegisterActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        //  mTextView2.setText(e.getMessage());
                        Toast.makeText(RegisterActivity.this,"NOsuccess "+e,Toast.LENGTH_SHORT).show();
                        System.out.println("sss"+e);
                    }
                });
    }


//  ApiService.httpUrl1+"/app/phoneCodeVerify"

    private void getByOkGo() {
//        OkGo.get("http://wthrcdn.etouch.cn/weather_mini?citykey=101180101")                            // 请求方式和请求url
        String  url = ApiService.httpUrl1+"/app/phoneCodeVerify?phone="+editPhoto.getText().toString().trim();
        OkGo.get(url)
                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheGetKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                     //   mTextView2.setText(s);
                     //   System.out.println("sss"+s);
                        Toast.makeText(RegisterActivity.this,"s"+s,Toast.LENGTH_SHORT).show();

                        VerifyModel verifyModel = new Gson().fromJson(s,VerifyModel.class);

                          if(verifyModel.getCode() == 0){
                              Toast.makeText(RegisterActivity.this,"验证码已经发送成功！",Toast.LENGTH_SHORT).show();
                          }else {
                              Toast.makeText(RegisterActivity.this,"验证码发送有误，请重新发送",Toast.LENGTH_SHORT).show();
                          }

//                        for (int i = 0;i<verifyModel.getObject().size();i++){
//                            HashMap<String,String>  hashMap = new HashMap<>();
//                            String typeExp = deviceType.getObject().get(i).getTypeExp();
//                            String typePic = deviceType.getObject().get(i).getTypePic();
//                            String getGroupId = String.valueOf(deviceType.getObject().get(i).getGroup_id());
//                            String explain = String.valueOf(deviceType.getObject().get(i).getExplain());
//                            hashMap.put("typeExp",typeExp);
//                            hashMap.put("typePic",typePic);
//                            hashMap.put("getGroupId",getGroupId);
//                            hashMap.put("explain",explain);
//
//
//
//
//                            //  list.add(place);
//
//                            listmap.add(hashMap);
//                        }
                    }

                });
    }


    /**
     * post请求
     *
     *
     */


   /* private void postByOkGo(String url) {
        OkGo.post(url)
                .tag(this)
                .cacheKey("cachePostRegister")
                .cacheMode(CacheMode.DEFAULT)
                .params("method", "album.item.get")
                .params("appKey", "myKey")
                .params("format", "json")
                .params("albumId", "Lqfme5hSolM")
                .params("pageNo", "1")
                .params("pageSize", "2")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                      //  mTextView2.setText(s);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                      //  mTextView2.setText(e.getMessage());
                    }
                });
    }*/




    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        btnIdentifying.onDestroy();
        super.onDestroy();
    }

    public void click(View v){


        finish();
    }


    public void click4(View v){
        Intent intent = new Intent(this,PrivacyPolicyActivity.class);
        startActivity(intent);


    }

    public void click5(View v){
        Intent intent = new Intent(this,UserAgreementActivity.class);
        startActivity(intent);


    }


   /* class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btnIdentifying.setBackgroundColor(Color.parseColor("#B6B6D8"));
            btnIdentifying.setClickable(false);
            btnIdentifying.setText("("+millisUntilFinished / 1000 +") 秒后可重新发送");
        }

        @Override
        public void onFinish() {
            btnIdentifying.setText("重新获取验证码");
            btnIdentifying.setClickable(true);
            btnIdentifying.setBackgroundColor(Color.parseColor("#4EB84A"));

        }
    }*/

}
