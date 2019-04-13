package com.silence.commonframe.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.text.TextUtils;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.silence.commonframe.ApiService;
import com.silence.commonframe.R;
import com.silence.commonframe.model.LoginFromPasswordModel;
import com.silence.commonframe.model.LoginModel;
import com.silence.commonframe.model.SiteDevice;
import com.silence.commonframe.model.VerifyModel;
import com.silence.commonframe.utils.Data;
import com.silence.commonframe.utils.LoadingPopupHelper;

import android.content.SharedPreferences.Editor;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class LoginActivity extends Activity {
    private static final int GO_GUIDE = 1;
    private static final long TIME = 1000;
    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.edit_password)
    EditText password;


    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.checkBox)
    CheckBox checkBox;
    @Bind(R.id.textView2)
    TextView textView2;
    @Bind(R.id.tv_register)
    TextView tv_register;
    @Bind(R.id.button2)
    Button button2;


    private LoadingPopupHelper loadingPopupHelper;

    private SharedPreferences sp;
    private SharedPreferences sp1 = null;
   private String mac;


    private List<HashMap<String, String>> listmap = new ArrayList<HashMap<String, String>>() ;
    Handler handler = new Handler(){
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case  GO_GUIDE:
                    goGuide();
                    break;
            }
        }
    };
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void  goGuide(){
        loadingPopupHelper.dismissLoading();

//        Explode explode = new Explode();
//        explode.setDuration(500);
//
//        getWindow().setExitTransition(explode);
//        getWindow().setEnterTransition(explode);
//        ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(LoginActivity.this);


        Intent  intent = new Intent(this,MainActivity.class);
        startActivity(intent);


     //   startActivity(intent, oc2.toBundle());



          finish();
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.register_acticity);
        ButterKnife.bind(this);


       // sp = this.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);

        sp = this.getSharedPreferences("userinfo", Context.MODE_PRIVATE);



        if (sp.getBoolean("checkboxBoolean", false))//sp.getBoolean("checkboxBoolean", false)
        {
            name.setText(sp.getString("uname", null));
            password.setText(sp.getString("upswd", null));
            checkBox.setChecked(true);

        }

       // getDeviceIDByMac(this);
       mac =  getNewMac();
       // Toast.makeText(this,"ss"+mac,Toast.LENGTH_SHORT).show();

    }

    public void loading1() {
        loadingPopupHelper = new LoadingPopupHelper.Builder(this)
                .width(400)
                .height(400)
                .outSideTouchable(true)
                .parentView(button2)
                .withShadow(true)
                .msg("登录加载中...")
                .loadingType(LoadingPopupHelper.LOADING_TYPE_DEFAULT)
                .build()
                .showLoading();

    }

    /**
     * 通过wan口获取mac
     * @return
     */


    private static String getNewMac() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return null;
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }




    private void getLogin() {
//        OkGo.get("http://wthrcdn.etouch.cn/weather_mini?citykey=101180101")                            // 请求方式和请求url
        String  url = ApiService.httpUrl1+"/app/loginFromPassword?phone="+name.getText().toString()+"&password="+password.getText().toString()+"&clientId="+mac+"1&appType=2";
        OkGo.get(url)
                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheGetKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            String message = jsonObject.get("msg").toString();

                            if (message.equals("success")){
                                loadingPopupHelper.dismissLoading();
                                //Toast.makeText(LoginActivity.this,""+s,Toast.LENGTH_SHORT).show();
                                LoginFromPasswordModel loginFromPasswordModel = new Gson().fromJson(s,LoginFromPasswordModel.class);
//                                List<LoginFromPasswordModel.DataBean>  list = (List<LoginFromPasswordModel.DataBean>) loginFromPasswordModel.getData();
                                Data.setName(loginFromPasswordModel.getData().getUsername().toString());
                                Data.setToken(loginFromPasswordModel.getData().getToken().toString());
                                //   Data.setUserid(loginFromPasswordModel.getData()..toString());
                                Data.setPhoto(loginFromPasswordModel.getData().getPhone().toString());


                                Data.setLoginId(loginFromPasswordModel.getData().getLoginId().toString());

                                System.out.println("kk"+Data.getLoginId().toString());
                                handler.sendEmptyMessageDelayed(GO_GUIDE, TIME);

                               // return;
                            }else {
                                Toast.makeText(LoginActivity.this,""+message,Toast.LENGTH_SHORT).show();
                                loadingPopupHelper.dismissLoading();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
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
        params.put("phone", name.getText().toString());//aaa
        params.put("password", password.getText().toString());//123456

        final JSONObject jsonObject = new JSONObject(params);




     //   OkGo.post("https://www.hsh-iot.cn/YunGanKeJi/login")
        OkGo.post(ApiService.httpUrl+"login")
                .tag(this)
                .cacheKey("cachePostRegister")
                .cacheMode(CacheMode.DEFAULT)
                .upJson(jsonObject.toString())
 //               .headers("Connection", "close")

//                .params("format", "json")
//                .params("albumId", "Lqfme5hSolM")
//                .params("pageNo", "1")
//                .params("pageSize", "2")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        //  mTextView2.setText(s);
                        // System.out.println("sss"+response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            String message = jsonObject.get("message").toString();
                            String object = jsonObject.get("object").toString();
                           if (message.equals("error")){
                               loadingPopupHelper.dismissLoading();
                               Toast.makeText(LoginActivity.this,""+object,Toast.LENGTH_SHORT).show();
                                return;
                           }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        LoginModel jsonRootBean = new Gson().fromJson(s, LoginModel.class);
                        if (jsonRootBean.getMessage().equals("error")){

                            Toast.makeText(LoginActivity.this,""+jsonRootBean.getObject(),Toast.LENGTH_SHORT).show();

                        }


                        Data.setName(jsonRootBean.getObject().getUsername().toString());
                        Data.setToken(jsonRootBean.getObject().getToken().toString());
                        Data.setUserid(String.valueOf(jsonRootBean.getObject().getId()));
                        Data.setPhoto(jsonRootBean.getObject().getPhone());


//                        System.out.println("rst:" + jsonRootBean.getObject().getUsername().toString());

                        String s1 = Data.getToken().toString();
                        System.out.println("rst:" + s1);
                        handler.sendEmptyMessageDelayed(GO_GUIDE, TIME);
                      //  Toast.makeText(LoginActivity.this,"success",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        //  mTextView2.setText(e.getMessage());
                        Toast.makeText(LoginActivity.this,"NOsuccess "+e,Toast.LENGTH_SHORT).show();
                        System.out.println("sss"+e);
                    }
                });


    }





    public void  forgetPassWord(View view){
        Intent intent = new Intent(this,ForgetPasswordActivity.class);
        startActivity(intent);



    }








    public void click(View view){
       // Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show();
      //  DialogHelper.showLoading(this, "登录加载中...", true, true, false);



        loading1();
        boolean CheckBoxLogin = checkBox.isChecked();
        //记住密码
        if (CheckBoxLogin)
        {
            Editor editor = sp.edit();
            editor.putString("uname", name.getText().toString());
            editor.putString("upswd", password.getText().toString());
            editor.putBoolean("checkboxBoolean", true);
            editor.commit();
        }
        else
        {
            Editor editor = sp.edit();
            editor.putString("uname", null);
            editor.putString("upswd", null);
            editor.putBoolean("checkboxBoolean", false);
            editor.commit();
        }


  //   postdate();

        getLogin();


    }






    public void MyClick(View view){
        Intent   intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);

    }
}
