package com.silence.commonframe.Dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.silence.commonframe.R;

import java.lang.ref.WeakReference;

/**
 * Created by cqll on 2016/9/21.
 */

public class CController {
    private final Context mContext;
    private final DialogInterface mDialogInterface;
    private final Window mWindow;
    private View mContentView;

    private CharSequence mTitle;
    private CharSequence mMessage;
    public CParams.OnPrepareViewListener mOnPrepareViewListener;
    private View mView;
    private int mViewLayoutResId;

    private boolean mButtonPositiveDoDismiss =true;
    Button mButtonPositive;
    private CharSequence mButtonPositiveText;
    Message mButtonPositiveMessage;

    private boolean mButtonNegativeDoDismiss =true;
    Button mButtonNegative;
    private CharSequence mButtonNegativeText;
    Message mButtonNegativeMessage;



    private Handler mHandler;


    private final View.OnClickListener mButtonHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Message m;
            if (v == mButtonPositive && mButtonPositiveMessage != null) {
                m = Message.obtain(mButtonPositiveMessage);
            } else if (v == mButtonNegative && mButtonNegativeMessage != null) {
                m = Message.obtain(mButtonNegativeMessage);
            }  else {
                m = null;
            }

            if (m != null) {
                m.sendToTarget();
            }

            if(v==mButtonPositive&&mButtonPositiveDoDismiss||v==mButtonNegative&&mButtonNegativeDoDismiss){
                mHandler.obtainMessage(CController.ButtonHandler.MSG_DISMISS_DIALOG, mDialogInterface).sendToTarget();
            }

        }
    };

    public void setOnPrepareViewListener(CParams.OnPrepareViewListener onPrepareViewListener) {
        mOnPrepareViewListener = onPrepareViewListener;
    }

    private static final class ButtonHandler extends Handler {
        private static final int MSG_DISMISS_DIALOG = 1;

        private WeakReference<DialogInterface> mDialog;

        public ButtonHandler(DialogInterface dialog) {
            mDialog = new WeakReference<>(dialog);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DialogInterface.BUTTON_POSITIVE:
                case DialogInterface.BUTTON_NEGATIVE:
                    ((DialogInterface.OnClickListener) msg.obj).onClick(mDialog.get(), msg.what);
                    break;

                case MSG_DISMISS_DIALOG:
                    ((DialogInterface) msg.obj).dismiss();

            }
        }
    }

    public CController(Context context, DialogInterface di, Window window) {
        mContext = context;
        mDialogInterface = di;
        mWindow = window;
        mHandler = new ButtonHandler(di);
        mContentView=View.inflate(mContext,R.layout.dialog_base_confirm_cancel,null);
        window.requestFeature(Window.FEATURE_NO_TITLE);
    }


    public void installContent() {
        mWindow.setContentView(mContentView);
        setupView();
        if(mOnPrepareViewListener!=null)
        mOnPrepareViewListener.onPrepareListView(mContentView);
    }

    private void setupView() {
        setupChildView((ViewGroup)mContentView.findViewById(R.id.fyt_content));
        setupBottomButton(mContentView.findViewById(R.id.lyt_confirm_cancel));
    }

    private void setupChildView(ViewGroup group) {
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        final View customView;
        if (mView != null) {
            customView = mView;
        } else if (mViewLayoutResId != 0) {
            customView = inflater.inflate(mViewLayoutResId,group, false);
        } else {
            customView = inflater.inflate(R.layout.dialog_txt,group, false);
            if(!TextUtils.isEmpty(mTitle)){
               TextView txt= ((TextView) customView.findViewById(R.id.txt));
                txt.setText(mTitle);
                txt.setVisibility(View.VISIBLE);
            }
            if(!TextUtils.isEmpty(mMessage)){
                TextView txtTip=   ((TextView) customView.findViewById(R.id.txt_tip));
                txtTip.setText(mMessage);
                txtTip.setVisibility(View.VISIBLE);
            }

        }

        group.addView(customView);


    }

    private void setupBottomButton(View lytButton) {

        if(TextUtils.isEmpty(mButtonPositiveText)&&TextUtils.isEmpty(mButtonNegativeText)){
            lytButton.setVisibility(View.GONE);
            return;
        }
        mButtonPositive = (Button) lytButton.findViewById(R.id.btn_right);
        mButtonPositive.setOnClickListener(mButtonHandler);

        if (TextUtils.isEmpty(mButtonPositiveText)) {
            mButtonPositive.setVisibility(View.GONE);
        } else {
            mButtonPositive.setText(mButtonPositiveText);
            mButtonPositive.setVisibility(View.VISIBLE);
        }

        mButtonNegative = (Button) lytButton.findViewById(R.id.btn_left);
        mButtonNegative.setOnClickListener(mButtonHandler);

        if (TextUtils.isEmpty(mButtonNegativeText)) {
            mButtonNegative.setVisibility(View.GONE);
        } else {
            mButtonNegative.setText(mButtonNegativeText);
            mButtonNegative.setVisibility(View.VISIBLE);
        }

    }

    public void setTitle(CharSequence title) {
        if(mView!=null||mViewLayoutResId!=0){
            return;
        }
        mTitle = title;
    }

    public void setMessage(CharSequence message) {
        if(mView!=null||mViewLayoutResId!=0){
            return;
        }
        mMessage = message;
    }

    public void setView(int layoutResId) {
        mView = null;
        mViewLayoutResId = layoutResId;
    }

    public void setView(View view) {
        mView = view;
        mViewLayoutResId = 0;
    }

    public void setButton(int whichButton, CharSequence text,
                          DialogInterface.OnClickListener listener, Message msg, Boolean doDismiss) {

        if (msg == null && listener != null) {
            msg = mHandler.obtainMessage(whichButton, listener);
        }

        switch (whichButton) {

            case DialogInterface.BUTTON_POSITIVE:
                mButtonPositiveText = text;
                mButtonPositiveMessage = msg;
                mButtonPositiveDoDismiss =doDismiss;
                break;

            case DialogInterface.BUTTON_NEGATIVE:
                mButtonNegativeText = text;
                mButtonNegativeMessage = msg;
                mButtonNegativeDoDismiss =doDismiss;
                break;

            default:
                throw new IllegalArgumentException("Button does not exist");
        }
    }


    public static class CParams {
        public final Context mContext;
        public final LayoutInflater mInflater;


        public CharSequence mTitle;
        public CharSequence mMessage;
        public boolean mPositiveDoDismiss=true;
        public CharSequence mPositiveButtonText;
        public DialogInterface.OnClickListener mPositiveButtonListener;

        public boolean mNegativeDoDismiss=false;
        public CharSequence mNegativeButtonText;
        public DialogInterface.OnClickListener mNegativeButtonListener;

        public boolean mCancelable;
        public DialogInterface.OnCancelListener mOnCancelListener;
        public DialogInterface.OnDismissListener mOnDismissListener;
        public DialogInterface.OnKeyListener mOnKeyListener;

        public OnPrepareViewListener mOnPrepareViewListener;
        public int mViewLayoutResId;
        public View mView;

        public interface OnPrepareViewListener {
            void onPrepareListView(View contentView);
        }

        public CParams(Context context) {
            mContext = context;
            mCancelable = true;
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public void apply(CController dialog) {
            if (mTitle != null) {
                dialog.setTitle(mTitle);
            }
            if (mMessage != null) {
                dialog.setMessage(mMessage);
            }
            if (mPositiveButtonText != null) {
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, mPositiveButtonText,
                        mPositiveButtonListener, null,mPositiveDoDismiss);
            }
            if (mNegativeButtonText != null) {
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, mNegativeButtonText,
                        mNegativeButtonListener, null,mNegativeDoDismiss);
            }

            if (mView != null) {
                dialog.setView(mView);
            } else if (mViewLayoutResId != 0) {
                dialog.setView(mViewLayoutResId);
            }

            if (mOnPrepareViewListener != null) {
                dialog.setOnPrepareViewListener(mOnPrepareViewListener);
            }
        }


    }


}
