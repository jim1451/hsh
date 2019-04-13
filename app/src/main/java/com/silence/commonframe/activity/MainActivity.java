package com.silence.commonframe.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.silence.commonframe.App;
import com.silence.commonframe.Dialog.CDialog;
import com.silence.commonframe.R;
import com.silence.commonframe.fragment.DeviceFragment;
import com.silence.commonframe.fragment.FindFragment1;

import com.silence.commonframe.fragment.FirstFragment;
import com.silence.commonframe.fragment.MineFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

import android.os.Handler;

import java.util.Set;

public class MainActivity extends BaseActivity {
    @Bind(R.id.btn_tab_chat)
    Button mBtnTabChat;
    @Bind(R.id.btn_tab_contact)
    Button mBtnTabContact;
    @Bind(R.id.btn_tab_find)
    Button mBtnTabFind;
    @Bind(R.id.btn_tab_mine)
    Button mBtnTabMine;
    private FirstFragment firstFragment;
    private DeviceFragment deviceFragment;
    private FindFragment1 mFindFragment;
    private MineFragment mMineFragment;
    private FragmentManager mFragmentManager;
    public static MainActivity   mainActivity;




    private static final int MSG_SET_ALIAS = 101;
    private EditText msgText;
    public static boolean isForeground = false;
    private MediaPlayer mMediaPlayer;


    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 1:
                    System.exit(0);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //showDivider(true);
        mFragmentManager = getSupportFragmentManager();
        mBtnTabChat.setSelected(true);
        setTitle(getString(R.string.text_tab_chat));
        firstFragment = new FirstFragment();
        mFragmentManager.beginTransaction().add(R.id.fragment_container, firstFragment).commit();

        initWindows();
        addActivity(this);

        /**
         * 极光推送
         */
     //   registerMessageReceiver();


    //    mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS,"a318d81cabd14e118197777ea55c4c2c"));//fc9b1aeb8e5c4debb0b8ae79569d99a6
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






    private void switchState(Button selectedButton) {
        mBtnTabChat.setSelected(false);
        mBtnTabContact.setSelected(false);
        mBtnTabFind.setSelected(false);
        mBtnTabMine.setSelected(false);
        selectedButton.setSelected(true);
    }

    private void switchFragment(Fragment newFragment) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (!newFragment.isAdded()) {
            transaction.add(R.id.fragment_container, newFragment);
        }
        if (firstFragment != null && firstFragment.isVisible()) {
            transaction.hide(firstFragment);
        }
        if (deviceFragment != null && deviceFragment.isVisible()) {
            transaction.hide(deviceFragment);
        }
        if (mFindFragment != null && mFindFragment.isVisible()) {
            transaction.hide(mFindFragment);
        }
        if (mMineFragment != null && mMineFragment.isVisible()) {
            transaction.hide(mMineFragment);
        }
        transaction.show(newFragment);
        transaction.commit();
    }


    private long exitiem = 0;
    boolean isExit;
    private long lastTime=0; //记录上次点击的时间
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

//         if (keyCode == KeyEvent.KEYCODE_BACK) {
////            if (System.currentTimeMillis() - exitiem > 2000) {
////                exitiem = System.currentTimeMillis();
////             //   APP.mToast("再按一下退出");
////             //   Toast.makeText(MainActivity.this, "再按一下退出", Toast.LENGTH_SHORT).show();
////
////                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.dialog));
////                //    设置Title的图标
////                builder.setIcon(R.drawable.pipe);
////                //    设置Title的内容
////                builder.setTitle("退出系统吗？");
////                //    设置Content来显示一个信息
////                builder.setMessage("确定退出！");
////                //    设置一个PositiveButton
////                builder.setPositiveButton("忽略", new DialogInterface.OnClickListener()
////                {
////                    @Override
////                    public void onClick(DialogInterface dialog, int which)
////                    {
////                        Toast.makeText(MainActivity.this, "positive: " + which, Toast.LENGTH_SHORT).show();
////                    }
////                });
////
////
////                //    设置一个NeutralButton
////                builder.setNeutralButton("确定", new DialogInterface.OnClickListener()
////                {
////                    @Override
////                    public void onClick(DialogInterface dialog, int which)
////                    {
////                        //  Toast.makeText(getContext(), "neutral: " + which, Toast.LENGTH_SHORT).show();
////                        System.exit(0);
////                    }
////                });
////                //    显示出该对话框
////                builder.show();
////
////
////                return true;
////            } else {
////                System.exit(1);
////            }
////        }




//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//
//            if (isExit) {
//                this.finish();
//
//            } else {
//                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
//                isExit = true;
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        isExit= false;
//                    }
//                }, 2000);
//            }
//            return true;
//        }



        if(keyCode== KeyEvent.KEYCODE_BACK&&event.getAction()==KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-lastTime)>2000){
               // Toast.makeText(MainActivity.this, "在按一次退出程序", Toast.LENGTH_SHORT).show();
                lastTime=System.currentTimeMillis();
            }else {



                CDialog.Builder builder = new CDialog.Builder(this);
                //    AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.dialog));
                //builder.setIcon(R.drawable.ic_launcher);
//                builder.setTitle("你确定要退出系统吗？");
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




                        System.exit(0);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.show();




            }
            return  true;
        }



        return super.onKeyDown(keyCode, event);
    }


    //    private void switchTitleBar(String title, boolean showBackward, boolean showForward) {
//        setTitle(title);
//        if (showBackward) {
//            showBackwardView(true);
//        } else {
//            showBackwardView(false);
//        }
//        if (showForward) {
//            showForwardView(true);
//        } else {
//            showForwardView(false);
//        }
//    }

    @OnClick({R.id.btn_tab_chat, R.id.btn_tab_contact, R.id.btn_tab_find, R.id.btn_tab_mine})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_tab_chat: {
                if (firstFragment == null) {
                    firstFragment = new FirstFragment();
                }

                switchFragment(firstFragment);
                switchState(mBtnTabChat);
            }
            break;
            case R.id.btn_tab_contact: {
                if (deviceFragment == null) {
                    deviceFragment = new DeviceFragment();
                }
             //   switchTitleBar(getString(R.string.text_tab_contact), true, false);
                switchFragment(deviceFragment);
                switchState(mBtnTabContact);
            }
            break;
            case R.id.btn_tab_find: {
                if (mFindFragment == null) {
                    mFindFragment = new FindFragment1();
                }
             //   switchTitleBar(getString(R.string.text_tab_find), false, false);
                switchFragment(mFindFragment);
                switchState(mBtnTabFind);

            }
            break;
            case R.id.btn_tab_mine: {
                if (mMineFragment == null) {
                    mMineFragment = new MineFragment();
                }
           //     switchTitleBar(getString(R.string.text_tab_mine), false, false);
                switchFragment(mMineFragment);
                switchState(mBtnTabMine);
            }
            break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        isForeground = true;
    }

    /**
     * 极光推送
     */




    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {

        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    //    Logger.i("TAG", logs);
                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    //    Logger.i("TAG", logs);
                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 100 * 60);
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
                    //    Logger.e("TAG", logs);
            }

        }
    };
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    //   Logger.d("TAG", "Set alias in handler.");
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAliasAndTags(getApplicationContext(),
                            (String) msg.obj,
                            null,
                            mAliasCallback);
                    break;
                default:
                    //      Logger.i("TAG", "Unhandled msg - " + msg.what);
            }
        }
    };

    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

//    public void click(View view) {
//        mMediaPlayer.stop();
//    }


    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
//                    if (!ExampleUtil.isEmpty(extras)) {
//                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
//                    }
                    setCostomMsg(showMsg.toString());
                }
            } catch (Exception e){
            }
        }
    }
    private void setCostomMsg(String msg){
//        if (null != msgText) {
//            msgText.setText(msg);
//            msgText.setVisibility(android.view.View.VISIBLE);
//
//            Toast.makeText(this,"jj"+msg,Toast.LENGTH_SHORT).show();
//
//
//
//
//
//            mMediaPlayer  = MediaPlayer.create(this, R.raw.jcs); //
//            mMediaPlayer.setLooping(true); // 设置循环播放
//            mMediaPlayer.start(); // 开始播放
//
//
//
//
//
//
//        }

        Toast.makeText(this,"jj"+msg,Toast.LENGTH_SHORT).show();
       // String b = "abcabcabc";
   //   String  test=msg.replace("\\","");

        String test =  "\""+ msg + "\"";






        System.out.println("kk"+test);
        Intent intent = new Intent(this,FireAlarmActivity.class);
        startActivity(intent);





        mMediaPlayer  = MediaPlayer.create(this, R.raw.jcs); //
        mMediaPlayer.setLooping(true); // 设置循环播放
        mMediaPlayer.start(); // 开始播放
    }



    /*** 添加Activity*/
    public void addActivity(Activity activity) {// 判断当前集合中不存在该Activity
        if (!App.oList.contains(activity)) {
            App.oList.add(activity);//把当前Activity添加到集合中}

        }

    }



}
