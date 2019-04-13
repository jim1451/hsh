package com.silence.commonframe.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.silence.commonframe.ApiService;
import com.silence.commonframe.R;
import com.silence.commonframe.utils.Data;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class CheckActivity extends AppCompatActivity {

    @Bind(R.id.tv_place)
    TextView tvPlace;
    @Bind(R.id.tv_sitename)
    TextView tvSitename;
    @Bind(R.id.tv_sitepalce)
    TextView tvSitepalce;
    @Bind(R.id.tv_temp)
    TextView tvTemp;
    @Bind(R.id.tv_smoke)
    TextView tvSmoke;
    @Bind(R.id.tv_type)
    TextView tvType;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.bt_typetest)
    Button btTypetest;
//    @Bind(R.id.bt_typetest1)
//    Button btTypetest1;



    @Bind(R.id.bt_typeerror)
    Button btTypeerror;
//    @Bind(R.id.bt_typeerror1)
//    Button btTypeerror1;


    @Bind(R.id.bt_typereal)
    Button btTypereal;
//    @Bind(R.id.bt_typereal1)
//    Button btTypereal1;


    @Bind(R.id.button8)
    Button button8;
    @Bind(R.id.linearLayout)
    LinearLayout linearLayout;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        ButterKnife.bind(this);
        initWindows();

//        btTypetest.setBackground(getResources().getDrawable(R.drawable.blue1));
//        btTypetest.setTextColor(Color.parseColor("#FFF"));

//       btTypeerror.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//
//               btTypetest.setVisibility(View.GONE);
//               btTypetest1.setVisibility(View.VISIBLE);
//
//               btTypeerror.setVisibility(View.GONE);
//               btTypeerror1.setVisibility(View.VISIBLE);
//
//               btTypereal.setVisibility(View.VISIBLE);
//               btTypereal1.setVisibility(View.GONE);
//
//           }
//       });
//
//        btTypeerror1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                btTypetest1.setVisibility(View.GONE);
//              btTypetest.setVisibility(View.VISIBLE);
//
//
//
//            }
//        });




//        btTypetest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                btTypetest.setVisibility(View.GONE);
//                btTypetest1.setVisibility(View.VISIBLE);
//
//                btTypeerror1.setVisibility(View.GONE);
//                btTypeerror.setVisibility(View.VISIBLE);
//
//                btTypereal.setVisibility(View.VISIBLE);
//                btTypereal1.setVisibility(View.GONE);
//
//            }
//        });


//        btTypetest1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                btTypetest1.setVisibility(View.GONE);
//                btTypetest.setVisibility(View.VISIBLE);
//
//                btTypeerror.setVisibility(View.GONE);
//                btTypeerror1.setVisibility(View.VISIBLE);
//
//                btTypereal.setVisibility(View.VISIBLE);
//                btTypereal1.setVisibility(View.GONE);
//
//            }
//        });














//        btTypeerror.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                btTypetest.setVisibility(View.GONE);
//                btTypetest1.setVisibility(View.VISIBLE);
//
//                btTypeerror.setVisibility(View.GONE);
//                btTypeerror1.setVisibility(View.VISIBLE);
//
//                btTypereal.setVisibility(View.VISIBLE);
//                btTypereal1.setVisibility(View.GONE);
//
//            }
//        });








    }

    public void myclick(View view) {
        finish();
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

    public void click(View view) {

        geCheck();
        Intent intent = new Intent(this, SubmitActivity.class);
        startActivity(intent);

    }

    public void click1(View view) {
        finish();
    }


    private void geCheck() {
//        OkGo.get("http://wthrcdn.etouch.cn/weather_mini?citykey=101180101")                            // 请求方式和请求url
        String url = ApiService.httpUrl1 + "/app/reCheckTrouble?processId=0716e10bab0742d697a22b63c889455e&recheckType=1";
        OkGo.get(url)
                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheGetKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .headers("token", Data.getToken())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            String message = jsonObject.get("msg").toString();

                            if (message.equals("success")) {

                                Toast.makeText(CheckActivity.this, "复核确认成功！", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(CheckActivity.this, "复核确认没成功，请重新确认！", Toast.LENGTH_SHORT).show();
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


}
