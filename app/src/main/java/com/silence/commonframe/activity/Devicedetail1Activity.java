package com.silence.commonframe.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.silence.commonframe.ApiService;
import com.silence.commonframe.R;
import com.silence.commonframe.adapter.MyAdapterSlide;
import com.silence.commonframe.adapter.MyAdapterSlideMine;
import com.silence.commonframe.adapter.SitedetailMineAdapter;
import com.silence.commonframe.model.Device1;
import com.silence.commonframe.utils.Data;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class Devicedetail1Activity extends AppCompatActivity {
    @Bind(R.id.recView1)
    RecyclerView recView1;
    private Device1 siteDevice;
    private List<HashMap<String, String>> listmap = new ArrayList<HashMap<String, String>>();


    private String json = "{\n" +
            "  \"count\": 2,\n" +
            "  \"object\": [\n" +
            "    {\n" +
            "      \"id\": 2,\n" +
            "      \"deviceid\": \"0030452AA09464AA\",\n" +
            "      \"dLongtitude\": \"\",\n" +
            "      \"lonType\": null,\n" +
            "      \"dLatitude\": \"\",\n" +
            "      \"latType\": null,\n" +
            "      \"dLocation\": \"hjkl;jhk;uo6r87\",\n" +
            "      \"inputtime\": null,\n" +
            "      \"addtime\": 1550540614000,\n" +
            "      \"devicetypeid\": 1,\n" +
            "      \"siteid\": 182,\n" +
            "      \"userid\": 41,\n" +
            "      \"type_exp\": \"烟感探测器Tx3190\",\n" +
            "      \"state\": 1,\n" +
            "      \"deployment\": \"海盛海智联\",\n" +
            "      \"location\": \"浙江省杭州市余杭区龙王塘路61号\",\n" +
            "      \"deviceStatus\": 0,\n" +
            "      \"fireAlarm\": 0,\n" +
            "      \"batteryVoltage\": null,\n" +
            "      \"typepic\": \"picture/111.png\",\n" +
            "      \"deviceName\": \"独立式光电感烟探测报警器\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"deviceid\": \"0030452AA09485AA\",\n" +
            "      \"dLongtitude\": \"\",\n" +
            "      \"lonType\": null,\n" +
            "      \"dLatitude\": \"\",\n" +
            "      \"latType\": null,\n" +
            "      \"dLocation\": \"海盛海南墙上\",\n" +
            "      \"inputtime\": null,\n" +
            "      \"addtime\": 1550562353000,\n" +
            "      \"devicetypeid\": 2,\n" +
            "      \"siteid\": 182,\n" +
            "      \"userid\": 41,\n" +
            "      \"type_exp\": \"物联网网关Tx3252\",\n" +
            "      \"state\": 1,\n" +
            "      \"deployment\": \"海盛海智联\",\n" +
            "      \"location\": \"浙江省杭州市余杭区龙王塘路61号\",\n" +
            "      \"deviceStatus\": 0,\n" +
            "      \"fireAlarm\": 0,\n" +
            "      \"batteryVoltage\": null,\n" +
            "      \"typepic\": \"picture/222.png\",\n" +
            "      \"deviceName\": \"烟感\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"message\": \"ok\"\n" +
            "}";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devicedetail1);
        ButterKnife.bind(this);
        getData();

    }

    private void getData() {
        HashMap<String, String> params = new HashMap<>();
//        params.put("userid", "41");
//        params.put("page", "1");
//        params.put("siteid", "182");

        params.put("userid", "40");
        params.put("page", "1");
        params.put("siteid", "191");

//    params.put("phone", "13333311338");


        JSONObject jsonObject = new JSONObject(params);
        String s = Data.getToken();
        Log.v("Tag", s);

        String str = jsonObject.toString();

        OkGo.post(ApiService.httpUrl + "getdevice")
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


                        siteDevice = new Gson().fromJson(s, Device1.class);

                        for (int i = 0; i < siteDevice.getObject().size(); i++) {
                            HashMap<String, String> hashMap = new HashMap<>();
                            String id = siteDevice.getObject().get(i).getDeviceid();
                            String id1 = String.valueOf(siteDevice.getObject().get(i).getId());
                            String dLocation = siteDevice.getObject().get(i).getDLocation();
                            //     String place1 = siteDevice.getObject().get(i).getRegionalism();
                            hashMap.put("id", id);
                            hashMap.put("id1", id1);
                            hashMap.put("dLocation", dLocation);
                            //   list.add(place);

                            listmap.add(hashMap);
                        }


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                                final MyAdapterSlideMine adapter = new MyAdapterSlideMine(Devicedetail1Activity.this, listmap);
                                recView1.setLayoutManager(new LinearLayoutManager(Devicedetail1Activity.this));
                                recView1.setAdapter(adapter);




                                //RecyclerView点击事件
                                adapter.setOnItemClickLitener(new SitedetailMineAdapter.OnItemClickLitener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        //  Toast.makeText(SitedetailActivity.this, position + "click", Toast.LENGTH_SHORT).show();



                                        Intent intent = new Intent(Devicedetail1Activity.this,SiteManagement.class);
                                        startActivity(intent);


                                    }

                                    @Override
                                    public void onItemLongClick(View view, final int position) {
                                        //这里长按定义的是删除
                                        AlertDialog.Builder dialog = new AlertDialog.Builder(Devicedetail1Activity.this);
                                        dialog.setTitle("是否删除");
                                        dialog.setMessage("确定吗？");
                                        dialog.setCancelable(false);
                                        dialog.setPositiveButton("确认",new DialogInterface.OnClickListener(){

                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                adapter.removeData(position);
                                            }
                                        } );

                                        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        });
                                        dialog.show();

                                        Toast.makeText(Devicedetail1Activity.this, position + "Long click", Toast.LENGTH_SHORT).show();
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
                     //   Toast.makeText(ge, "NOsuccess " + e, Toast.LENGTH_SHORT).show();
                        System.out.println("sss" + e);
                    }
                });


    }


}
