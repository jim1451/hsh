package com.silence.commonframe.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.silence.commonframe.R;
import com.silence.commonframe.adapter.ListViewAdapter3;
import com.silence.commonframe.zxing.utils.Constant;

import java.util.List;

public class AdddeviceActivity extends AppCompatActivity {

    ExpandableListView expandableListView;

    ListViewAdapter3 treeViewAdapter;

    private Bitmap myBitmap;

    private Context context;

    public String[] groups = { "独立探测器", "组合式网关", "列表3"};

    //public String[][] child = { { "" }, { "" }, { "", "" } };
    public String[][] child = { { "" }, { "" }, { "" } };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddevice1);


        initWindows();


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



    public void myClick(View view) {
//        Intent intent = new Intent();
//        Toast.makeText(this,"ddssd",Toast.LENGTH_SHORT).show();
//
//        Intent intent = new Intent(this,DeviceActivity.class);
//        startActivity(intent);


        Intent intent=new Intent(AdddeviceActivity.this,CommonScanActivity.class);
        intent.putExtra(Constant.REQUEST_SCAN_MODE,Constant.REQUEST_SCAN_MODE_QRCODE_MODE);
        startActivity(intent);
    }

    public void click(View view) {
        finish();
    }

    public void myclick(View view) {
        finish();
    }

    // public void myclick(View view) {
//        finish();
//    }
}
