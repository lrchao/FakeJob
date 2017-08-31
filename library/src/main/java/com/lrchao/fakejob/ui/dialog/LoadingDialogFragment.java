package com.lrchao.fakejob.ui.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lrchao.fakejob.R;
import com.lrchao.fakejob.constant.BundleKey;


/**
 * Description: 显示加载中的Dialog
 *
 * @author liuranchao
 * @date 16/3/21 下午1:39
 */
public class LoadingDialogFragment extends MyBaseDialogFragment {

    public TextView mTvMessage;
    /**
     * Dialog 显示的文本
     */
    private String mMessage;

    /**
     * 是否可以cancel
     */
    private boolean mIsCancelable;

    public static LoadingDialogFragment newInstance(String message, boolean cancelable) {
        LoadingDialogFragment f = new LoadingDialogFragment();
        Bundle args = new Bundle();
        args.putString(BundleKey.INTENT_EXTRA_DIALOG_MESSAGE, message);
        args.putBoolean(BundleKey.INTENT_EXTRA_DIALOG_DIALOG_IS_CANCELABLE, cancelable);
        f.setArguments(args);
        return f;
    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.dialog_loading;
    }

    @Override
    protected void initView(View parentView) {

        mTvMessage = parentView.findViewById(R.id.tv_loading_msg);
        mTvMessage.setText(mMessage);
        setCancelable(mIsCancelable);
    }

    @Override
    protected void initArgumentsData(Bundle bundle) {
        mMessage = bundle.getString(BundleKey.INTENT_EXTRA_DIALOG_MESSAGE);
        mIsCancelable = bundle.getBoolean(BundleKey.INTENT_EXTRA_DIALOG_DIALOG_IS_CANCELABLE, true);
    }

}
