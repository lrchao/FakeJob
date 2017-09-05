package com.lrchao.fakejob.network.request;


import com.lrchao.fakejob.R;
import com.lrchao.utils.ResourceUtils;

/**
 * Description: 需要显示dialog的类型请求
 *
 * @author liuranchao
 * @date 16/3/1 下午5:50
 */
public abstract class DialogRequest<T> extends BaseRequest<T> {

    //==================================
    // 子类继承
    //==================================

    /**
     * 获取Loading Dialog 显示的文本
     */
    public String getLoadingText() {
        return ResourceUtils.getString(R.string.job_dialog_loading);
    }

    /**
     * 获取请求成功结束后的提示语
     */
    public String getSuccessTipsText() {
        return "";
    }

    /**
     * 返回请求结束后，是否显示toast
     * true: 显示
     * false: 不显示
     */
    public boolean isShowSuccessToast() {
        return false;
    }

    /**
     * 返回请求结束厚哦，是否现实dialog
     */
    public boolean isShowSuccessDialog() {
        return false;
    }

    /**
     * 是否显示失败的toast
     */
    public boolean isShowFailureToast() {
        return true;
    }

    /**
     * 是否显示失败后的Dialog
     */
    public boolean isShowFailureDialog() {
        return false;
    }

    /**
     * 是否显示loading的dialog， 默认为true
     */
    public boolean isShowLoadingDialog() {
        return true;
    }

    /**
     * 弹出的dialog 是否可以cancel
     */
    public boolean isCancelableDialog() {
        return true;
    }

}
