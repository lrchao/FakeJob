package com.lrchao.fakejob.ui.activity.common;


import com.lrchao.fakejob.mvp.common.mvp_view.MvpDialogView;

/**
 * Description: 有网络请求的fragment
 * 1.DialogRequest子类 请求开始弹出dialog,请求结束后关闭
 * 2.
 *
 * @author liuranchao
 * @date 16/1/8 上午9:53
 */
public abstract class NetworkFragment extends BaseFragment implements
        MvpDialogView {

    @Override
    public final void showRequestDialog(String loadingText, boolean cancelableDialog) {
        showLoadingDialog(loadingText, cancelableDialog);
    }

    @Override
    public final void dismissRequestDialog() {
        dismissLoadingDialog();
    }
}
