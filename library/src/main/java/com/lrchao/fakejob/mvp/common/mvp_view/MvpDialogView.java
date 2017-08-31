package com.lrchao.fakejob.mvp.common.mvp_view;


import com.lrchao.fakejob.mvp.MvpView;

/**
 * Description: 请求的控制view
 *
 * @author liuranchao
 * @date 16/5/11 下午2:56
 */
public interface MvpDialogView extends MvpView {

    /**
     * 显示请求发起后的loading dialog
     *
     * @param loadingText      loading的内容
     * @param cancelableDialog 是否可以cancel
     */
    void showRequestDialog(String loadingText, boolean cancelableDialog);

    /**
     * 消失请求结束后的loading dialog
     */
    void dismissRequestDialog();
}
