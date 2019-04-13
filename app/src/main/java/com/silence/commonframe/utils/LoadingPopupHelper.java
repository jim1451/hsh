package com.silence.commonframe.utils;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.silence.commonframe.R;

//import com.dialog.helper.R;
//import com.dialog.helper.utils.ExceptionUtil;

/**
 * Created by yzd on 2017/9/6 0006.
 */

public class LoadingPopupHelper {
    private static PopupWindow mPopupWindow;
    private View contentView;//popup的视图
    private ImageView img;
    private TextView tv_msg;
    private int loadingType;
    private String msg;
    private int width;//popup的宽
    private int height;//popup的高
    private View parentView;//父控件
    private boolean outSideTouchable = false;//popup外部区域是否可以点击
    private boolean withShadow = true;//是否有阴影遮罩
    private OnDismissListener dismissListener;//popup消失监听器
    private static Activity mContext;

    public static final int LOADING_TYPE_DEFAULT = 0;
    public static final int LOADING_TYPE1 = 1;
    public static final int LOADING_TYPE2 = 2;
    public static final int LOADING_TYPE3 = 3;
    public static final int LOADING_TYPE4 = 4;

    private LoadingPopupHelper(Builder builder) {
        width = builder.width;
        height = builder.height;
        parentView = builder.parentView;
        outSideTouchable = builder.outSideTouchable;
        withShadow = builder.withShadow;
        loadingType = builder.loadingType;
        msg = builder.msg;
        dismissListener = builder.dismissListener;
    }

    public LoadingPopupHelper showLoading() {
        checkParams();

        if (withShadow) {
            setShadow(0.5f);
        }

        setParams();

        if (mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }

        if (contentView == null) {
            contentView = View.inflate(mContext, R.layout.loading_dialog, null);
            img = (ImageView) contentView.findViewById(R.id.img);
            tv_msg = (TextView) contentView.findViewById(R.id.tipTextView);
        }
        
        setAnimation(img,tv_msg);
        

        mPopupWindow.setContentView(contentView);

        if (loadingType == LOADING_TYPE1) {
            img.setImageResource(R.drawable.loading_point);
        } else if (loadingType == LOADING_TYPE2) {
            img.setImageResource(R.drawable.loading_circel);
            img.setPadding(12, 12, 12, 12);
        } else if (loadingType == LOADING_TYPE3) {
            img.setImageResource(R.drawable.loading_red);
        } else if (loadingType == LOADING_TYPE4) {
            img.setImageResource(R.drawable.loading_dark);
            img.setPadding(10, 10, 10, 10);
        }

        setDismissListener();

        mPopupWindow.showAtLocation(parentView, Gravity.CENTER, 0, 0);
        return this;
    }

    public void dismissLoading(){
        if (mPopupWindow.isShowing()){
            mPopupWindow.dismiss();
        }
    }

    private void setAnimation(ImageView img, TextView tv_msg) {
        //加载旋转动画
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.loading);
        img.startAnimation(animation);
        tv_msg.setText(msg);
    }

    private void setParams() {
        mPopupWindow.setWidth(width);
        mPopupWindow.setHeight(height);
        mPopupWindow.setOutsideTouchable(outSideTouchable);
    }

    private void setDismissListener() {
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setShadow(1.0f);
                if (dismissListener != null) {
                    dismissListener.onDismiss();
                }
            }
        });
    }


    private void checkParams() {
        if (width == 0) {
         //   ExceptionUtil.illegaArgument("width can not be null");
        }
        if (height == 0) {
        //    ExceptionUtil.illegaArgument("height can not be null");
        }
    }


    private void setShadow(float shadow) {
        WindowManager.LayoutParams lp = mContext.getWindow().getAttributes();
        lp.alpha = shadow;
        mContext.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        mContext.getWindow().setAttributes(lp);
    }

    public static final class Builder {
        private int width;
        private int height;
        private View parentView;
        private boolean outSideTouchable;
        private boolean withShadow;
        private int loadingType;
        private String msg;
        private OnDismissListener dismissListener;

        public Builder(Activity activity) {
            mContext = activity;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder parentView(View parentView) {
            this.parentView = parentView;
            return this;
        }

        public Builder outSideTouchable(boolean cancelAble) {
            this.outSideTouchable = cancelAble;
            return this;
        }

        public Builder withShadow(boolean withShadow) {
            this.withShadow = withShadow;
            return this;
        }

        public Builder loadingType(int loadingType) {
            this.loadingType = loadingType;
            return this;
        }

        public Builder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder dismissListener(OnDismissListener listener) {
            dismissListener = listener;
            return this;
        }

        public LoadingPopupHelper build() {
            if (mPopupWindow == null) {
                mPopupWindow = new PopupWindow();
            }
            return new LoadingPopupHelper(this);
        }
    }

    public interface OnDismissListener {
        void onDismiss();
    }
}
