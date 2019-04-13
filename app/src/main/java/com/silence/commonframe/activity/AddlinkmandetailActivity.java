package com.silence.commonframe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.silence.commonframe.ApiService;
import com.silence.commonframe.R;
import com.silence.commonframe.model.LoginFromPasswordModel;
import com.silence.commonframe.utils.Data;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

public class AddlinkmandetailActivity extends AppCompatActivity {

    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.tv_cancel)
    TextView tvCancel;
    @Bind(R.id.tv_submit)
    TextView tvSubmit;


    private String data1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_addlinkmandetail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        final String data = intent.getStringExtra("data");
        data1 = data;


        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (data.equals("1")){
                    getData();
                }else {
                    getData1();
                }



            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });

    }



    private void getData() {


        HashMap<String, String> params = new HashMap<>();
        params.put("name", etName.getText().toString().trim());
        params.put("siteid", Data.getLinkmanname());
        params.put("phone", etPhone.getText().toString().trim());


        JSONObject jsonObject = new JSONObject(params);
        String s = Data.getToken();
        //       Log.v("Tag",s);

        String str = jsonObject.toString();


        OkGo.post(ApiService.httpUrl1+"/app/insertLinkMan")
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

                        JSONObject jsonObject = null;

                        try {
                            jsonObject = new JSONObject(s);
                            String message = jsonObject.get("msg").toString();
                            if (message.equals("success")){

                                Toast.makeText(AddlinkmandetailActivity.this,"添加成功！",Toast.LENGTH_SHORT).show();
                                // return;
                            }else {
                                Toast.makeText(AddlinkmandetailActivity.this,""+message,Toast.LENGTH_SHORT).show();

                                return;
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                   //     Toast.makeText(AddlinkmandetailActivity.this,"联系人添加成功！",Toast.LENGTH_SHORT).show();

                        finish();

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        //  mTextView2.setText(e.getMessage());
                        Toast.makeText(AddlinkmandetailActivity.this,"NOsuccess "+e,Toast.LENGTH_SHORT).show();
                        System.out.println("sss"+e);
                    }
                });


    }





    private void getData1() {


        HashMap<String, String> params = new HashMap<>();
        params.put("name", etName.getText().toString().trim());
        params.put("siteid", data1);
        params.put("phone", etPhone.getText().toString().trim());


        JSONObject jsonObject = new JSONObject(params);
        String s = Data.getToken();
        //       Log.v("Tag",s);

        String str = jsonObject.toString();


        OkGo.post(ApiService.httpUrl1+"/app/insertLinkMan")
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

                        JSONObject jsonObject = null;

                        try {
                            jsonObject = new JSONObject(s);
                            String message = jsonObject.get("msg").toString();
                            if (message.equals("success")){

                                Toast.makeText(AddlinkmandetailActivity.this,"添加成功！",Toast.LENGTH_SHORT).show();
                                // return;
                            }else {
                                Toast.makeText(AddlinkmandetailActivity.this,""+message,Toast.LENGTH_SHORT).show();

                                return;
                            }







                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                        //     Toast.makeText(AddlinkmandetailActivity.this,"联系人添加成功！",Toast.LENGTH_SHORT).show();

                        finish();

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        //  mTextView2.setText(e.getMessage());
                        Toast.makeText(AddlinkmandetailActivity.this,"NOsuccess "+e,Toast.LENGTH_SHORT).show();
                        System.out.println("sss"+e);
                    }
                });


    }
































}
