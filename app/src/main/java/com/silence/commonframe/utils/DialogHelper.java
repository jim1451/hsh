package com.silence.commonframe.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.silence.commonframe.R;

public class DialogHelper {
    private static AlertDialog alertDialog;
    private static Dialog dialog;

    public static void showLoading(Context context, CharSequence msg, boolean isVertical, boolean cancleable, boolean isWhiteBg) {
        dialog = new Dialog(context, R.style.dialog);
        dialog.setCancelable(cancleable);
        View root;
        if (isVertical) {
            root = View.inflate(context, R.layout.dialogui_loading_vertical, null);
        } else {
            root = View.inflate(context, R.layout.dialogui_loading_horizontal, null);
        }

        LinearLayout llBg = (LinearLayout) root.findViewById(R.id.dialogui_ll_bg);
        ProgressBar pbBg = (ProgressBar) root.findViewById(R.id.pb_bg);
        TextView tvMsg = (TextView) root.findViewById(R.id.dialogui_tv_msg);
        tvMsg.setText(msg);
        if (isWhiteBg) {
            llBg.setBackgroundResource(R.drawable.dialogui_shape_wihte_round_corner);
            pbBg.setIndeterminateDrawable(context.getResources().getDrawable(R.drawable.dialogui_rotate_mum));
            tvMsg.setTextColor(context.getResources().getColor(R.color.text_black));
        } else {
            llBg.setBackgroundResource(R.drawable.dialogui_shape_gray_round_corner);
            pbBg.setIndeterminateDrawable(context.getResources().getDrawable(R.drawable.dialogui_rotate_mum_light));
            tvMsg.setTextColor(Color.WHITE);
        }
        dialog.setContentView(root);
        dialog.show();
    }

}
