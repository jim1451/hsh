package com.silence.commonframe.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.silence.commonframe.App;
import com.silence.commonframe.Dialog.CDialog;
import com.silence.commonframe.R;

public class SetupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        initWindows();
    }

    public void click(View view) {
        Intent intent = new Intent(this,FireMessageActivity.class);
                startActivity(intent);
    }

    public void click1(View view) {
        Intent intent = new Intent(this,AboutActivity.class);
        startActivity(intent);
    }
    public void click3(View view) {
        Intent intent = new Intent(this,UserAgreementActivity.class);
        startActivity(intent);
    }

    public void click6(View view) {
//        Intent intent = new Intent(this,UserAgreementActivity.class);
//        startActivity(intent);



//        Intent intent = new Intent(this,FireAlarmActivity.class);
//
//
//
//
//        startActivity(intent);


        CDialog.Builder builder = new CDialog.Builder(this);
        //    AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.dialog));
        //builder.setIcon(R.drawable.ic_launcher);
        //   builder.setTitle("你确定要退出系统吗？");
        builder.setMessage("你确定要退出系统吗？");
        //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
//                View view = LayoutInflater.from(getContext()).inflate(R.layout.item_addsite, null);
//                //    设置我们自己定义的布局文件作为弹出框的Content
//                builder.setView(view);

        //    final EditText deviceno = (EditText)view.findViewById(R.id.deviceno);


        builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

//                android.os.Process.killProcess(android.os.Process.myPid());   //获取PID
//                removeALLActivity_();
//                System.exit(0);


//                ActivityManager am = (ActivityManager)getSystemService (Context.ACTIVITY_SERVICE);
//                am.restartPackage(getPackageName());

//                Intent intent = new Intent();
//                intent.putExtra("data","data");
//                startActivity(intent);
//                finish();

                finish();

                MainActivity.mainActivity.handler.obtainMessage(1).sendToTarget();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

//                Intent intent = new Intent(SetupActivity.this,SetupActivity.class);
//                startActivity(intent);
            }
        });
        builder.show();

//        finish();

    }

    public void removeALLActivity_() {
        //通过循环，把集合中的所有Activity销毁
        for (Activity activity : App.oList) {
            activity.finish();
        }
    }

    public void myclick1(View view){
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











}
