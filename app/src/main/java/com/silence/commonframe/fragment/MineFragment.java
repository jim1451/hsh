package com.silence.commonframe.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silence.commonframe.Dialog.CDialog;
import com.silence.commonframe.R;
import com.silence.commonframe.activity.AdddevicedetailActivity;
import com.silence.commonframe.activity.CommonScanActivity;
import com.silence.commonframe.activity.CoustomerActivity;
import com.silence.commonframe.activity.HistoryActivity;
import com.silence.commonframe.activity.QuesstionActivity;
import com.silence.commonframe.activity.SetupActivity;
import com.silence.commonframe.activity.SitedetailActivity;
import com.silence.commonframe.utils.Data;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Silence on 2016/4/5.
 */
public class MineFragment extends Fragment {
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.tel)
    TextView tel;//btn_menu
    @Bind(R.id.btn_menu)
    ImageView imageViewset;//btn_menu

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
      /*  TextView textView = (TextView) view.findViewById(R.id.text_content);
        textView.setText(getString(R.string.text_tab_mine));*/
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.rl);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Intent intent = new Intent(getContext(),SiteManagement.class);
                Intent intent = new Intent(getContext(), SitedetailActivity.class);
                startActivity(intent);
            }
        });
//        RelativeLayout   relativeLayout1 = (RelativeLayout) view.findViewById(R.id.rl1);//customer
//        relativeLayout1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //  Intent intent = new Intent(getContext(),SiteManagement.class);
//                Intent intent = new Intent(getContext(),Devicedetail1Activity.class);
//                startActivity(intent);
//            }
//        });
        RelativeLayout relativeLayoutcustomer = (RelativeLayout) view.findViewById(R.id.customer);//customer  ll_tuisong
        relativeLayoutcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Intent intent = new Intent(getContext(),SiteManagement.class);
                Intent intent = new Intent(getContext(), CoustomerActivity.class);
                startActivity(intent);
            }
        });


//        RelativeLayout   relativeLayoutcustomer1 = (RelativeLayout) view.findViewById(R.id.ll_tuisong);//customer  ll_tuisong  rl_history
//        relativeLayoutcustomer1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //  Intent intent = new Intent(getContext(),SiteManagement.class);
//                Intent intent = new Intent(getContext(),FireMessageActivity.class);
//                startActivity(intent);
//            }
//        });

        RelativeLayout relativeLayoutcustomer3 = (RelativeLayout) view.findViewById(R.id.rl_fire);//customer  ll_tuisong  rl_history   rl_qusstion
        relativeLayoutcustomer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Intent intent = new Intent(getContext(),SiteManagement.class);
                Intent intent = new Intent(getContext(), HistoryActivity.class);
                startActivity(intent);
            }
        });


        RelativeLayout relativeLayoutcustomer4 = (RelativeLayout) view.findViewById(R.id.rl_qusstion);//customer  ll_tuisong  rl_history   rl_qusstion
        relativeLayoutcustomer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Intent intent = new Intent(getContext(),SiteManagement.class);
                Intent intent = new Intent(getContext(), QuesstionActivity.class);
                startActivity(intent);
            }
        });


//        Button bt = (Button) view.findViewById(R.id.btn_fgpw);
//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//
//
//                CDialog.Builder builder = new CDialog.Builder(getContext());
//
//                builder.setMessage("你确定要退出系统吗？");
//
//
//                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
//                {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which)
//                    {
//                        System.exit(0);
//                    }
//                });
//                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
//                {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which)
//                    {
//
//                        Intent intent = new Intent(getContext(),SetupActivity.class);
//                        startActivity(intent);
//                    }
//                });
//                builder.show();
//
//
//            }
//        });


        ButterKnife.bind(this, view);
        name.setText(Data.getName());
        tel.setText(Data.getPhoto());


        imageViewset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),SetupActivity.class);
                  startActivity(intent);
            }
        });
        return view;
    }

//    public void clickSet(View v) {
//       Intent intent = new Intent(getContext(),SetupActivity.class);
//       startActivity(intent);
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
