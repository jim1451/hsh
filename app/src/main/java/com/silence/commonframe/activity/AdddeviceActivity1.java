package com.silence.commonframe.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.silence.commonframe.ApiService;
import com.silence.commonframe.R;
import com.silence.commonframe.model.DeviceType;
import com.silence.commonframe.utils.Data;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class AdddeviceActivity1 extends AppCompatActivity {


    private List<HashMap<String,String>>  listmap = new ArrayList<HashMap<String, String>>();

    @Bind(R.id.recycleAdd)
    RecyclerView recycleAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddevice3);
        ButterKnife.bind(this);
        getdata();
    }

    private void getdata() {

        HashMap<String, String> params = new HashMap<>();
      //  params.put("siteid", "7");//id
        //   params.put("dLocation", stringplace);
//        params.put("name", "liming");
//        params.put("siteid", "191");
//        params.put("phone", "13124257879");
//    params.put("phone", "13333311338");


        JSONObject jsonObject = new JSONObject(params);
        String s = Data.getToken();
        Log.v("Tag",s);

        String str = jsonObject.toString();


        OkGo.post(ApiService.httpUrl+"getdevicetype")
                .tag(this)
                .cacheKey("cachePostRegister11")
                .cacheMode(CacheMode.DEFAULT)
                .upJson(jsonObject.toString())

                .headers("token", Data.getToken())

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {



                        DeviceType deviceType = new Gson().fromJson(s,DeviceType.class);

                        for (int i = 0;i<deviceType.getObject().size();i++){
                            HashMap<String,String>  hashMap = new HashMap<>();
                            String typeExp = deviceType.getObject().get(i).getTypeExp();
                            String typePic = deviceType.getObject().get(i).getTypePic();
                            String getGroupId = String.valueOf(deviceType.getObject().get(i).getGroup_id());
                            String explain = String.valueOf(deviceType.getObject().get(i).getExplain());
                            hashMap.put("typeExp",typeExp);
                            hashMap.put("typePic",typePic);
                            hashMap.put("getGroupId",getGroupId);
                            hashMap.put("explain",explain);




                          //  list.add(place);

                            listmap.add(hashMap);
                        }
//
//
//                        Data.setList(list);
//                        Data.setListmap(listmap);
//
//
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                myAdapter1 = new MyAdapter( listmap,getContext());
//                                listView.setAdapter(myAdapter1);


                            }
                        });

                        Toast.makeText(AdddeviceActivity1.this,"联系中人添加成功！"+s,Toast.LENGTH_SHORT).show();
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
