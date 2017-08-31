package com.lrchao.fakejob.eventbus.receiver.mvp;

import android.support.annotation.MainThread;
import android.text.TextUtils;

import com.lrchao.fakejob.eventbus.poster.network.RequestResultEventPoster;
import com.lrchao.fakejob.eventbus.receiver.BaseEventReceiverModel;
import com.lrchao.fakejob.network.request.BaseRequest;
import com.lrchao.fakejob.network.request.DialogRequest;
import com.lrchao.fakejob.network.request.PageRequest;
import com.lrchao.fakejob.network.request_failure.RequestFailure;
import com.lrchao.utils.ToastUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


/**
 * Description: 请求结果的event receiver
 *
 * @author liuranchao
 * @date 16/5/5 下午8:50
 */
public class RequestResultEventReceiverMvp implements BaseEventReceiverModel {

    /**
     * 本页面发起的请求的action集合
     */
    private List<Integer> mRequestActionList;

    private List<String> mRequestUrlList;

    /**
     * 需要反馈UI的request的result集合
     */
    private List<RequestResultEventPoster> mUIRequestResultEventList = new ArrayList<>();

    private OnRequestResultListener mOnRequestResultListener;

    /**
     * 设置请求的Action
     *
     * @param requestActionList List<Integer>
     */
    public void setRequestActionList(List<Integer> requestActionList) {
        mRequestActionList = requestActionList;
    }

    public void setRequestUrlList(List<String> requestUrlList) {
        mRequestUrlList = requestUrlList;
    }

    public void setOnRequestResultListener(OnRequestResultListener onRequestResultListener) {
        mOnRequestResultListener = onRequestResultListener;
    }

    /**
     * 清空数据
     */
    public void clear() {
        for (RequestResultEventPoster event : mUIRequestResultEventList) {
            event.clear();
        }
        mUIRequestResultEventList.clear();
    }

    /**
     * 请求结果的Event回调
     *
     * @param event RequestResultEvent
     */
    @MainThread
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RequestResultEventPoster event) {
        if (event != null && event.getRequest() != null &&
                mRequestActionList.contains(event.getRequest().getAction())) {

            // 失败的情况
            if (event.getRequestFailure() != null) {

                if (mOnRequestResultListener != null) {
                    mOnRequestResultListener.onRequestResultFailed(event.getRequest(), event.getRequestFailure());
                }
                onDialogRequestFailure(event);


            } else {

                // 如果是UI的request，则加入到
                if (isUIRequest(event.getRequest())) {
                    mUIRequestResultEventList.add(event);
                }

                // 成功的情况
                if (mOnRequestResultListener != null) {
                    mOnRequestResultListener.onRequestResultSuccess(event.getRequest(), event.getResultModel());
                }

                onDialogRequestSuccess(event);


            }
        }
    }

    /**
     * 是否为UI的请求
     *
     * @param baseRequest BaseRequest
     */
    private boolean isUIRequest(BaseRequest baseRequest) {
        return isDialogRequest(baseRequest) || baseRequest instanceof PageRequest;
    }

    /**
     * Dialog request 失败的处理
     */
    private void onDialogRequestFailure(RequestResultEventPoster event) {
        BaseRequest baseRequest = event.getRequest();
        if (isDialogRequest(baseRequest)) {
            DialogRequest dialogRequest = (DialogRequest) baseRequest;

            if (dialogRequest.isShowFailureToast()) {
                ToastUtils.show(event.getRequestFailure().getFriendlyMsg());
            } else if (dialogRequest.isShowFailureDialog()) {
                ToastUtils.show(event.getRequestFailure().getFriendlyMsg());
            }
        }
    }

    /**
     * 请求结束成功后，dialog request 成功的处理
     *
     * @param event RequestResultEvent
     */
    private void onDialogRequestSuccess(RequestResultEventPoster event) {
        BaseRequest baseRequest = event.getRequest();
        if (isDialogRequest(baseRequest)) {
            DialogRequest dialogRequest = (DialogRequest) baseRequest;

            if (dialogRequest.isShowSuccessDialog()) {

                if (TextUtils.isEmpty(event.getResponseMessage())) {
                    //  统一弹出dialog
                } else {

                }

            } else if (dialogRequest.isShowSuccessToast()) {

                // 如果是服务端返回的空，则显示默认的
                if (TextUtils.isEmpty(event.getResponseMessage())) {
                    ToastUtils.show(dialogRequest.getSuccessTipsText());
                } else {
                    ToastUtils.show(event.getResponseMessage());
                }
            }
        }
    }

    /**
     * 是否为DialogRequest
     *
     * @param baseRequest BaseRequest
     */
    private boolean isDialogRequest(BaseRequest baseRequest) {
        if (baseRequest instanceof DialogRequest) {
            DialogRequest dialogRequest = (DialogRequest) baseRequest;
            if (dialogRequest.isShowLoadingDialog()) {
                return true;
            }
        }
        return false;
    }

    public interface OnRequestResultListener {

        void onRequestResultSuccess(BaseRequest request, Object model);

        void onRequestResultFailed(BaseRequest request, RequestFailure responseFailedReason);


    }
}
