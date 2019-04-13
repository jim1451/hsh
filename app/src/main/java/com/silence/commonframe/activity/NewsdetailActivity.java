package com.silence.commonframe.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.silence.commonframe.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsdetailActivity extends AppCompatActivity {


    @Bind(R.id.imageView4)
    ImageView imageView4;
    @Bind(R.id.textView15)
    TextView textView15;
    @Bind(R.id.tv6)
    TextView tv6;
    @Bind(R.id.textView_comfirm)
    TextView textViewComfirm;
    @Bind(R.id.tv_sitename)
    TextView tvSitename;
    @Bind(R.id.tv_devicetype)
    TextView tvDevicetype;
    @Bind(R.id.tv_no)
    TextView tvNo;
    @Bind(R.id.tv_firetype)
    TextView tvFiretype;
    @Bind(R.id.tv_batteryvoltage)
    TextView tvBatteryvoltage;
    @Bind(R.id.tv_signalstrength)
    TextView tvSignalstrength;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_place)
    TextView tvPlace;
    @Bind(R.id.tv_result)
    TextView tvResult;
    @Bind(R.id.tv_time1)
    TextView tvTime1;
    private List<HashMap<String,String>> list;
    private HashMap<String,String> list1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdetail);
        ButterKnife.bind(this);
        initWindows();
        list = new ArrayList<HashMap<String, String>>();
        list1 = new HashMap<>();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
      //  list1 = (List<HashMap<String, String>>) bundle.getSerializable("listmap");
        list1 = (HashMap<String, String>) bundle.getSerializable("listmap");
        //System.out.println("jj" + list);
        tvPlace.setText(list1.get("location"));
        tvTime.setText(list1.get("time1"));
        tvNo.setText(list1.get("id"));//deployment
        tvSitename.setText(list1.get("deployment"));
        tvBatteryvoltage.setText(list1.get("batteryvoltage")+"V");
        tvSignalstrength.setText(list1.get("signalstrength")+"Db");


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
        finish();
    }

    public void check(View view) {
        Intent intent = new Intent(this, CheckActivity.class);
        startActivity(intent);
    }
}
