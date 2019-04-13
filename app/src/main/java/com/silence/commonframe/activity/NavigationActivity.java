package com.silence.commonframe.activity;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.silence.commonframe.R;

public class NavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);


//        ConstraintLayout v = (ConstraintLayout) findViewById(R.id.ll);//找到你要设透明背景的layout 的id
//        v.getBackground().setAlpha(255);//0~255透明度值
    }
}
