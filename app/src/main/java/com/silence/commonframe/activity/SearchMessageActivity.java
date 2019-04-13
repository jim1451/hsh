package com.silence.commonframe.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.silence.commonframe.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchMessageActivity extends AppCompatActivity {

    @Bind(R.id.textView3)
    TextView textView3;
//    @Bind(R.id.linearLayout3)
//    LinearLayout linearLayout3;
    @Bind(R.id.checkBox_type)
    CheckBox checkBoxType;
    @Bind(R.id.checkBox_type1)
    CheckBox checkBoxType1;
    @Bind(R.id.checkBox_type2)
    CheckBox checkBoxType2;
    @Bind(R.id.checkBox_type3)
    CheckBox checkBoxType3;
    @Bind(R.id.checkBox_type4)
    CheckBox checkBoxType4;
    @Bind(R.id.timestart)
    TextView timestart;
//    @Bind(R.id.timestart1)
//    TextView timestart1;


    @Bind(R.id.overtime)
    TextView overtime;
//    @Bind(R.id.overtime1)
//    TextView overtime1;
//    @Bind(R.id.linearLayout4)//search
//    LinearLayout linearLayout4;

    @Bind(R.id.search)//search
     Button bt;

    private String  type1 ="ok";
    private String  type2  ="ok";
    private String  type3  ="ok";






    private TextView tv1;
    private Calendar calendar;  //日期类
     private int Year;       //年
      private int month1;      //月
     private int day1;        //日
     private int hour;       //时
     private int minute;     //分
     private int seconds;    //秒

    private String monthOfYear1;
    private String dayOfMonth1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_message);
        ButterKnife.bind(this);


        calendar = Calendar.getInstance();
//      //  calendar.set(Calendar.HOUR_OF_DAY, 23);
//        Year = calendar.get(Calendar.YEAR);//获取当前年
//         month1 = calendar.get(Calendar.MONTH)+1;//获取月份，加1是因为月份是从0开始计算的
//         day1 = calendar.get(Calendar.DATE);//获取日
//         hour = calendar.get(Calendar.HOUR);//获取小时
//         minute = calendar.get(Calendar.MINUTE);//获取分钟
//         seconds = calendar.get(Calendar.SECOND);//获取秒钟



     //   OnCheckBoxClickListener listener = new OnCheckBoxClickListener();



        timestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //  Calendar c = Calendar.getInstance(Locale.CHINA);
              //  Calendar c =  DateFormat.getDatelnstance();

                calendar.set(Calendar.HOUR_OF_DAY, 23);
                int year=calendar.get(Calendar.YEAR);
                final int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                DatePickerDialog dialog = new DatePickerDialog(SearchMessageActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {

                        if (monthOfYear<9){
                            monthOfYear++;
                            monthOfYear1 ="0"+monthOfYear;
                        }else {
                            monthOfYear++;
                            monthOfYear1 = ""+monthOfYear;
                        }
                        if (dayOfMonth<10){

                            dayOfMonth1 = "0"+dayOfMonth;
                        }else {
                            dayOfMonth1 = ""+dayOfMonth;
                        }


                      //  timestart.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                        timestart.setText(year+"-"+monthOfYear1+"-"+dayOfMonth1);
                    }
                }, year, month, day);
                dialog.show();
            }
        });

//        private String calendarToString(Calendar calendar) throws Exception {
//            if (calendar == null) {
//                return null;
//            }
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");// 设置你想要的格式
//            String dateStr = df.format(calendar.getTime());
//            return dateStr;
//        }
//





//        timestart1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                TimePickerDialog timeDialog = new TimePickerDialog(SearchMessageActivity.this,
//                        new TimePickerDialog.OnTimeSetListener() {
//                            //从这个方法中取得获得的时间
//                            @Override
//                            public void onTimeSet(TimePicker view, int hourOfDay,
//                                                  int minute) {
//
//
//                                timestart1.setText(hourOfDay+":"+minute);
//                            }
//                        }, 0, 0, true);
//                timeDialog.show();
//
//            }
//        });




        overtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance(Locale.CHINA);
                int year=c.get(Calendar.YEAR);
                int month=c.get(Calendar.MONTH);
                int day=c.get(Calendar.DAY_OF_MONTH);
                int hour = c.get(Calendar.HOUR_OF_DAY);
                DatePickerDialog dialog = new DatePickerDialog(SearchMessageActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {

                        if (monthOfYear<9){
                            monthOfYear++;
                            monthOfYear1 ="0"+monthOfYear;
                        }else {
                            monthOfYear++;
                            monthOfYear1 = ""+monthOfYear;
                        }
                        if (dayOfMonth<10){

                            dayOfMonth1 = "0"+dayOfMonth;
                        }else {
                            dayOfMonth1 = ""+dayOfMonth;
                        }




                      //  overtime.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                        overtime.setText(year+"-"+monthOfYear1+"-"+dayOfMonth1);
                    }
                }, year, month, day);
                dialog.show();
            }
        });


//        overtime1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                TimePickerDialog timeDialog = new TimePickerDialog(SearchMessageActivity.this,
//                        new TimePickerDialog.OnTimeSetListener() {
//                            //从这个方法中取得获得的时间
//                            @Override
//                            public void onTimeSet(TimePicker view, int hourOfDay,
//                                                  int minute) {
//
//                                overtime1.setText(hourOfDay+":"+minute);
//                            }
//                        }, 0, 0, true);
//                timeDialog.show();
//
//            }
//        });


        initWindows();


        checkBoxType1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBoxType1.isChecked()){
                  //  Toast.makeText(SearchMessageActivity.this,"heee",Toast.LENGTH_SHORT).show();
                    type1 = "1";

                }else {
                    type1 = "ok";
                }
                Toast.makeText(SearchMessageActivity.this,""+type1,Toast.LENGTH_SHORT).show();
            }
        });
        checkBoxType2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBoxType2.isChecked()){
                  //  Toast.makeText(SearchMessageActivity.this,"heee",Toast.LENGTH_SHORT).show();
                    type2 = "1";
                }
                else {
                    type2 = "ok";
                }
                Toast.makeText(SearchMessageActivity.this,""+type2,Toast.LENGTH_SHORT).show();
            }
        });


        checkBoxType3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBoxType3.isChecked()){
                 //   Toast.makeText(SearchMessageActivity.this,"heee",Toast.LENGTH_SHORT).show();
                    type3 = "1";
                } else {
                    type3 = "ok";
                }
                Toast.makeText(SearchMessageActivity.this,""+type3,Toast.LENGTH_SHORT).show();
            }
        });





       // initWindows();

    }

    public void click(View view) {
        finish();
    }

//    @OnClick({R.id.timestart, R.id.overtime})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.timestart:
//                break;
//            case R.id.overtime:
//                break;
//        }
//    }

    public void searchMessage(View view) {
        Intent intent = new Intent(this,SearchResultActivity.class);
        intent.putExtra("type1",type1);
        intent.putExtra("type2",type2);
        intent.putExtra("type3",type3);
        intent.putExtra("timestart",timestart.getText().toString());
        intent.putExtra("overtime",overtime.getText().toString());
        startActivity(intent);
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











//    public void myclick(View view) {
//        finish();
//    }
}
