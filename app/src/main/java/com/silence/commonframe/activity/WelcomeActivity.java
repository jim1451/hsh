package com.silence.commonframe.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.silence.commonframe.R;
import com.silence.commonframe.model.SiteDevice;
import com.silence.commonframe.utils.Data;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class WelcomeActivity extends Activity {
    private static final int Guide = 1001;
    private static final int GO_GUIDE = 1001;
    private static final int TIME = 1500;




    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){

                case GO_GUIDE:
                    goGuide();
                    break;
            }

        }
    };

    private void goGuide() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.welcome_acticity);
        init();
    }

    private void init() {
        handler.sendEmptyMessageDelayed(GO_GUIDE,TIME);


    }









}
