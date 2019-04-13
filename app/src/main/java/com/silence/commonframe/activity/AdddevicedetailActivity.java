package com.silence.commonframe.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.silence.commonframe.ApiService;
import com.silence.commonframe.R;
import com.silence.commonframe.adapter.MyAdapter;
import com.silence.commonframe.model.SiteDevice;
import com.silence.commonframe.utils.Data;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class AdddevicedetailActivity extends AppCompatActivity {

    @Bind(R.id.imageView4)
    ImageView imageView4;
    @Bind(R.id.textView3)
    TextView textView3;
    @Bind(R.id.device_id)
    TextView deviceId;
    @Bind(R.id.device_name)
    TextView deviceName;
    @Bind(R.id.device_place)
    EditText devicePlace;
    //    @Bind(R.id.device_place1)
//    TextView devicePlace1;
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.spinner)
    Spinner spinner;

   private String stringplace = "";
   private String id = "";

   private String  palceId = "";
   private int position1;
   private   String  str1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddevicedetail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
         id = intent.getStringExtra("id");

        deviceId.setText(id);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                     this, android.R.layout.simple_spinner_item,
                Data.getList());
        spinner.setAdapter(adapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
             public void onItemSelected(AdapterView<?> parent, View view,
                     int position, long id) {
                                // 在选中之后触发
//                           Toast.makeText(AdddevicedetailActivity.this,
//                                             parent.getItemAtPosition(position).toString(),
//                                            Toast.LENGTH_SHORT).show();
                            stringplace  =  parent.getItemAtPosition(position).toString();
                             position1   = position;
//
//
                Toast.makeText(AdddevicedetailActivity.this,
                        ""+position1,
                        Toast.LENGTH_SHORT).show();

                        }

                    @Override
             public void onNothingSelected(AdapterView<?> parent) {
                               // 这个一直没有触发，我也不知道什么时候被触发。
                              //在官方的文档上说明，为back的时候触发，但是无效，可能需要特定的场景
                            }
         });
        initWindows();
    }

    public void myclick(View view) {
        finish();
    }

    public void click(View view) {
       str1 = devicePlace.getText().toString();
        if (TextUtils.isEmpty(str1)){
            Toast.makeText(AdddevicedetailActivity.this,"请添加对应的安装位置！",Toast.LENGTH_SHORT).show();
            return;
        }
        if ( Data.getList().size() == 0){
            Toast.makeText(AdddevicedetailActivity.this,"请添加对应设备的安装场地！",Toast.LENGTH_SHORT).show();
            return;
        }

       getdata();
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

//        if (stringplace.equals(""))
//        {
//            Toast.makeText(this,"请选择对应的场所",Toast.LENGTH_SHORT).show();
//            return;
//        }


        HashMap<String, String> params = new HashMap<>();
//        params.put("deviceid", "0030452AA09488AA");//id

        //  String  id   = Data.getListId().get(position1);
        String id1 = Data.getListmap().get(position1).get("id");
        params.put("deviceId", id);  //0000000000000004  0030452AA09472AA  0000000000000010
      //  params.put("deviceLocation", stringplace);
        params.put("deviceLatitude", "1123");
        params.put("deviceLocation", "1123");
        params.put("deviveLongtitude", "1123");
        params.put("siteId", id1);



        JSONObject jsonObject = new JSONObject(params);
        String s = Data.getToken();
        Log.v("Tag",s);

        String str = jsonObject.toString();


        OkGo.post(ApiService.httpUrl1+"/app/addDevice")
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


                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            String message = jsonObject.get("msg").toString();
                            String code = jsonObject.get("code").toString();

                            if (code.equals("0")) {

                                Toast.makeText(AdddevicedetailActivity.this, "设备添加成功！", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(AdddevicedetailActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

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
}
