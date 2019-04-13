package com.silence.commonframe.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.silence.commonframe.R;

//import static cq.library.cdialog.R.style.dialog;

/**
 * Created by cqll on 2016/9/21.
 */

public class CDialog extends Dialog implements DialogInterface {
    public CController mController;
    private Context mContext;

    public CDialog(Context context) {
        super(context);
        mContext = context;
        mController=new CController(context,this,getWindow());
    }




    public void setSelectConfirmChildView(@LayoutRes int child) {
        View contentView = View.inflate(mContext, R.layout.dialog_base_confirm_cancel, null);
        View childView = View.inflate(mContext, child, null);
        FrameLayout contentFyt = (FrameLayout) contentView.findViewById(R.id.fyt_content);
        contentFyt.addView(childView);
        getWindow().setContentView(contentView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mController.installContent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        getWindow().setLayout((outMetrics.widthPixels * 8) / 10, ViewGroup.LayoutParams.WRAP_CONTENT);

    }

    public static class Builder{
        private final CController.CParams P;

        public Builder(Context context) {
            P = new CController.CParams(context);
        }

        public CDialog.Builder setTitle(@StringRes int titleId) {
            P.mTitle = P.mContext.getText(titleId);
            return this;
        }

        public CDialog.Builder setTitle(CharSequence title) {
            P.mTitle = title;
            return this;
        }

        public CDialog.Builder setMessage(@StringRes int messageId) {
            P.mMessage = P.mContext.getText(messageId);
            return this;
        }

        public CDialog.Builder setMessage(CharSequence message) {
            P.mMessage = message;
            return this;
        }
        public CDialog.Builder setPositiveButton(@StringRes int textId, final OnClickListener listener) {
            setPositiveButton(P.mContext.getText(textId),true,listener);
            return this;
        }

        public CDialog.Builder setPositiveButton(CharSequence text, final OnClickListener listener) {
            setPositiveButton(text,true,listener);
            return this;
        }

        public CDialog.Builder setPositiveButton(@StringRes int textId, boolean doDismiss, final OnClickListener listener) {
            setPositiveButton(P.mContext.getText(textId),doDismiss,listener);
            return this;
        }

        public CDialog.Builder setPositiveButton(CharSequence text, boolean doDismiss, final OnClickListener listener) {
            P.mPositiveButtonText = text;
            P.mPositiveButtonListener = listener;
            P.mPositiveDoDismiss=doDismiss;
            return this;
        }


        public CDialog.Builder setNegativeButton(@StringRes int textId, final OnClickListener listener) {
            setNegativeButton(P.mContext.getText(textId),true,listener);
            return this;
        }

        public CDialog.Builder setNegativeButton(CharSequence text, final OnClickListener listener) {
            setNegativeButton(text,true,listener);
            return this;
        }

        public CDialog.Builder setNegativeButton(@StringRes int textId, boolean doDismiss, final OnClickListener listener) {
            setNegativeButton (P.mContext.getText(textId),doDismiss,listener);
            return this;
        }

        public CDialog.Builder setNegativeButton(CharSequence text, boolean doDismiss, final OnClickListener listener) {
            P.mNegativeButtonText = text;
            P.mNegativeButtonListener = listener;
            P.mNegativeDoDismiss=doDismiss;
            return this;
        }

        public CDialog.Builder setCancelable(boolean cancelable) {
            P.mCancelable = cancelable;
            return this;
        }

        public CDialog.Builder setOnCancelListener(OnCancelListener onCancelListener) {
            P.mOnCancelListener = onCancelListener;
            return this;
        }

        public CDialog.Builder setOnDismissListener(OnDismissListener onDismissListener) {
            P.mOnDismissListener = onDismissListener;
            return this;
        }

        public CDialog.Builder setOnPrepareViewListener(CController.CParams.OnPrepareViewListener onPrepareViewListener) {
            P.mOnPrepareViewListener = onPrepareViewListener;
            return this;
        }

        public CDialog.Builder setView(int layoutResId) {
            P.mView = null;
            P.mViewLayoutResId = layoutResId;
            return this;
        }

        public CDialog.Builder setView(View view) {
            P.mView = view;
            P.mViewLayoutResId = 0;
            return this;
        }
        public CDialog create() {
            final CDialog dialog = new CDialog(P.mContext);
            P.apply(dialog.mController);
            dialog.setCancelable(P.mCancelable);
            if (P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(P.mOnCancelListener);
            dialog.setOnDismissListener(P.mOnDismissListener);
            if (P.mOnKeyListener != null) {
                dialog.setOnKeyListener(P.mOnKeyListener);
            }
            return dialog;
        }

        public CDialog show() {
            final CDialog dialog = create();
            dialog.show();
            return dialog;
        }

    }
}
