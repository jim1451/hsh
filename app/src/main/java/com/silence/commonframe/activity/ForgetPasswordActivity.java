package com.silence.commonframe.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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
import com.silence.commonframe.model.ForgetPassWord;
import com.silence.commonframe.model.VerifyModel;
import com.silence.commonframe.utils.TimeButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ForgetPasswordActivity extends Activity {


    @Bind(R.id.edit_fgpwphoto)
    EditText editFgpwphoto;
    @Bind(R.id.edit_fgpwidentifying)
    EditText editFgpwidentifying;
    @Bind(R.id.btn_identifying)
    TimeButton btnIdentifying;
    @Bind(R.id.edit_fgpwpassword)
    EditText editFgpwpassword;
    @Bind(R.id.edit_fgpwpassword1)
    EditText editFgpwpassword1;
    @Bind(R.id.btn_fgpw)
    Button btnFgpw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.forgetpw_acticity);
        ButterKnife.bind(this);


        btnIdentifying.onCreate(savedInstanceState);
        btnIdentifying.setTextAfter("").setTextBefore("获取验证码").setLenght(30 * 1000);//秒后重新获取
        btnIdentifying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editFgpwphoto.getText().toString().equals("")){
                    Toast.makeText(ForgetPasswordActivity.this,"电话号码不能为空！",Toast.LENGTH_SHORT).show();
                    return;
                }else if(editFgpwphoto.getText().length()!=11){
                    Toast.makeText(ForgetPasswordActivity.this,"电话号码位数有问题，请重新确认！",Toast.LENGTH_SHORT).show();
                    return;
                }


               // getCode();
                getByOkGo();
            }
        });



        //按钮的touch触摸事件
        btnIdentifying.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: //按下的动作
                        if(editFgpwphoto.getText().toString().equals("")){
                            Toast.makeText(ForgetPasswordActivity.this,"电话号码不能为空！",Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        if(editFgpwphoto.getText().length() != 11){
                            Toast.makeText(ForgetPasswordActivity.this,"手机号位数不对！",Toast.LENGTH_SHORT).show();
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



        btnFgpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   getPassWord();
                UpdataPassword();
            }
        });


    }

    /**
     * 获取短息验证码
     */

    private void getByOkGo() {
//        OkGo.get("http://wthrcdn.etouch.cn/weather_mini?citykey=101180101")                            // 请求方式和请求url
        String  url = ApiService.httpUrl1+"/app/phoneCodeVerify?phone="+editFgpwphoto.getText().toString().trim();
        OkGo.get(url)
                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheGetKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        //   mTextView2.setText(s);
                        //   System.out.println("sss"+s);
                      //  Toast.makeText(ForgetPasswordActivity.this,"s"+s,Toast.LENGTH_SHORT).show();

                        VerifyModel verifyModel = new Gson().fromJson(s,VerifyModel.class);

                        if(verifyModel.getCode() == 0){
                            Toast.makeText(ForgetPasswordActivity.this,"验证码已经发送成功！",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(ForgetPasswordActivity.this,"验证码发送有误，请重新发送",Toast.LENGTH_SHORT).show();
                        }



                    }

                });
    }







    public void click(View view){

        finish();
    }

    private void  getCode(){
        HashMap<String, String> params = new HashMap<>();
        params.put("phonenumber", editFgpwphoto.getText().toString().trim());
        params.put("type", "update");
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



                        Toast.makeText(ForgetPasswordActivity.this,"验证码发送成功，请稍等！",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        //  mTextView2.setText(e.getMessage());
//                        Toast.makeText(ForgetPasswordActivity.this,"NOsuccess "+e,Toast.LENGTH_SHORT).show();
//                        System.out.println("sss"+e);

                        Toast.makeText(ForgetPasswordActivity.this,"验证码发送没有成功！ ",Toast.LENGTH_SHORT).show();
                    }
                });

    }



    private void UpdataPassword(){
        if (TextUtils.isEmpty(editFgpwphoto.getText().toString())){
            Toast.makeText(ForgetPasswordActivity.this,"用户手机不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }
        if(editFgpwphoto.getText().length() != 11){
            Toast.makeText(ForgetPasswordActivity.this,"手机号位数不对！",Toast.LENGTH_SHORT).show();
            return;
        }

        if (!editFgpwpassword.getText().toString().trim().equals(editFgpwpassword1.getText().toString().trim())){
            Toast.makeText(ForgetPasswordActivity.this,"输入的密码不一致，请重新输入！",Toast.LENGTH_SHORT).show();
            return;
        }

                //http://www.hsh-iot.com/hsh-app/app/updatePassword?phone=13027210708&phoneCode=265173&password1=123456&password2=123456
        String  url = ApiService.httpUrl1+"/app/updatePassword?phone="+editFgpwphoto.getText().toString().trim()+"&phoneCode="+editFgpwidentifying.getText().toString().trim()+"&password1="+editFgpwpassword.getText().toString().trim()+"&password2="+editFgpwpassword1.getText().toString().trim();
        OkGo.get(url)
                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheGetKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        //   mTextView2.setText(s);
                        //   System.out.println("sss"+s);
                     //   Toast.makeText(ForgetPasswordActivity.this,"s"+s,Toast.LENGTH_SHORT).show();

//                        VerifyModel verifyModel = new Gson().fromJson(s,VerifyModel.class);
//
//                        if(verifyModel.getCode() == 0){
//                            Toast.makeText(ForgetPasswordActivity.this,"修改密码成功！",Toast.LENGTH_SHORT).show();
//                        }else {
//                            Toast.makeText(ForgetPasswordActivity.this,"由于其他原因修改密码不成功，请重新发送",Toast.LENGTH_SHORT).show();
//                        }


//                        JSONObject jsonObject = null;
//
//                        try {
//                            jsonObject = new JSONObject(s);
//                            String message = jsonObject.get("message").toString();
//                         //   String object = jsonObject.get("object").toString();
//
//                            if (message.equals("success")) {
//                                Toast.makeText(ForgetPasswordActivity.this,"修改成功！",Toast.LENGTH_SHORT).show();
//                            }else {
//                                Toast.makeText(ForgetPasswordActivity.this,""+message,Toast.LENGTH_SHORT).show();
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }




                        JSONObject jsonObject = null;

                        try {
                            jsonObject = new JSONObject(s);
                            String message = jsonObject.get("msg").toString();
                            String data = jsonObject.get("data").toString();
                            if (message.equals("success")){

                                Toast.makeText(ForgetPasswordActivity.this,"添加成功！",Toast.LENGTH_SHORT).show();

                                // return;
                            }else {
                                Toast.makeText(ForgetPasswordActivity.this,""+message,Toast.LENGTH_SHORT).show();

                                return;
                            }







                        } catch (JSONException e) {
                            e.printStackTrace();
                        }





                    }

                });









    }







    private void getPassWord(){
        if (TextUtils.isEmpty(editFgpwphoto.getText().toString())){
            Toast.makeText(ForgetPasswordActivity.this,"用户手机不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }
//        if (TextUtils.isEmpty(editIdentifying.getText().toString())){
//            Toast.makeText(RegisterActivity.this,"手机验证码，不能为空！",Toast.LENGTH_SHORT).show();
//            return;
//        }
         if(editFgpwphoto.getText().length() != 11){
            Toast.makeText(ForgetPasswordActivity.this,"手机号位数不对！",Toast.LENGTH_SHORT).show();
            return;
        }

        if (!editFgpwpassword.getText().toString().trim().equals(editFgpwpassword1.getText().toString().trim())){
            Toast.makeText(ForgetPasswordActivity.this,"输入的密码不一致，请重新输入！",Toast.LENGTH_SHORT).show();
            return;
        }


        HashMap<String, String> params = new HashMap<>();
        params.put("phone", editFgpwphoto.getText().toString().trim());
        params.put("password", editFgpwpassword.getText().toString().trim());
        params.put("code", editFgpwidentifying.getText().toString().trim());


        JSONObject jsonObject = new JSONObject(params);




        OkGo.post(ApiService.httpUrl+"updatepassword")
                .tag(this)
                .cacheKey("cacheForgetPassword")
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


                        ForgetPassWord  forgetPassWord = new Gson().fromJson(s,ForgetPassWord.class);
                        if (forgetPassWord.getMessage().equals("ok")){
                            Toast.makeText(ForgetPasswordActivity.this,"修改成功！",Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(ForgetPasswordActivity.this,"手机号码或密码有误！",Toast.LENGTH_SHORT).show();
                        }



                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        //  mTextView2.setText(e.getMessage());
                        Toast.makeText(ForgetPasswordActivity.this,"修改不成功！ "+e,Toast.LENGTH_SHORT).show();

                    }
                });
    }

}



