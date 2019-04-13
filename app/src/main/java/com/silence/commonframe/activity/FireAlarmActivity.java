package com.silence.commonframe.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.silence.commonframe.R;

public class FireAlarmActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     //   getSupportActionBar().hide();//隐藏标题栏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN); // 隐藏状态栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_fire_alarm);


        Intent intent = getIntent();
        String data = intent.getStringExtra("deviceName1");
        String data1 = intent.getStringExtra("siteName1");


        TextView dataTextView = (TextView) findViewById(R.id.textView17);
        TextView dataTextView1 = (TextView) findViewById(R.id.textView19);


        dataTextView.setText("设备名称"+data);
        dataTextView1.setText("场所名称"+data1);


        mMediaPlayer  = MediaPlayer.create(this, R.raw.jcs); //
        mMediaPlayer.setLooping(true); // 设置循环播放
        mMediaPlayer.start(); //开始播放


        Button backButton = (Button) findViewById(R.id.button9);
                backButton.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                mMediaPlayer.stop();

                finish();

            }
         });
    }
}
