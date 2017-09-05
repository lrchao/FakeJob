package com.lrchao.fakejob.mvp.common;

import android.content.Intent;
import android.support.annotation.CallSuper;

import com.lrchao.fakejob.JobApp;
import com.lrchao.fakejob.constant.BundleKey;
import com.lrchao.fakejob.eventbus.receiver.mvp.RequestResultEventReceiverMvp;
import com.lrchao.fakejob.eventbus.receiver.mvp.RequestWhenEventReceiverMvp;
import com.lrchao.fakejob.exception.IllegalParamException;
import com.lrchao.fakejob.mvp.BasePresenter;
import com.lrchao.fakejob.mvp.MvpPresenter;
import com.lrchao.fakejob.mvp.MvpView;
import com.lrchao.fakejob.mvp.common.mvp_view.MvpDialogView;
import com.lrchao.fakejob.network.request.BaseRequest;
import com.lrchao.fakejob.network.request.DialogRequest;
import com.lrchao.fakejob.network.request_failure.RequestFailure;
import com.lrchao.utils.EventBusUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Description: 网络请求处理的Presenter
 *
 * @author liuranchao
 * @date 16/5/12 下午10:19
 */
public class NetworkPresenter implements MvpPresenter,
        RequestResultEventReceiverMvp.OnRequestResultListener,
        RequestWhenEventReceiverMvp.OnRequestWhenListener {

    /**
     * MvpView
     */
    protected MvpView mMvpView;
    /**
     * Presenter
     */
    protected BasePresenter mBasePresenter;
    /**
     * 本页面发起的请求的action集合
     */
    private List<Integer> mRequestActionList = new ArrayList<>();
    /**
     * 本页面发起的请求的action集合
     */
    private List<String> mRequestUrlList = new ArrayList<>();
    /**
     * 请求结果的EventBus接受
     */
    private RequestResultEventReceiverMvp mResultReceiver;
    /**
     * 请求时机的EventBus接受
     */
    private RequestWhenEventReceiverMvp mWhenReceiver;
    /**
     * 请求结果监听
     */
    private OnRequestListener mOnRequestListener;

    public NetworkPresenter(MvpView mvpView, BasePresenter basePresenter) {
        mMvpView = mvpView;
        mBasePresenter = basePresenter;
    }

    //====================================
    // Public
    //====================================

    /**
     * 设置请求结果监听
     *
     * @param onRequestListener OnRequestListener
     */
    public final void setOnRequestListener(OnRequestListener onRequestListener) {
        mOnRequestListener = onRequestListener;
    }

    /**
     * 页面统一发起请求的入口
     *
     * @param intent 包含request action 等信息
     */
    public final void sendRequest(Intent intent) {
        int action = intent.getIntExtra(BundleKey.INTENT_EXTRA_REQUEST_ACTION, 0);
        if (action <= 0) {
            throw new IllegalParamException("send request intent no has action. intent:" + intent);
        }
        mRequestActionList.add(action);

        JobApp.getInstance().getContext().startService(intent);
    }

    @CallSuper
    @Override
    public void start() {
        mResultReceiver = new RequestResultEventReceiverMvp();
        mResultReceiver.setRequestActionList(mRequestActionList);
        mResultReceiver.setRequestUrlList(mRequestUrlList);
        mResultReceiver.setOnRequestResultListener(this);
        EventBusUtils.register(mResultReceiver);

        mWhenReceiver = new RequestWhenEventReceiverMvp();
        mWhenReceiver.setRequestActionList(mRequestActionList);
        mWhenReceiver.setOnRequestWhenListener(this);
        EventBusUtils.register(mWhenReceiver);
    }

    @CallSuper
    @Override
    public void end() {
        mResultReceiver.clear();
        mResultReceiver.setOnRequestResultListener(null);
        mWhenReceiver.setOnRequestWhenListener(null);
        EventBusUtils.unregister(mResultReceiver);
        EventBusUtils.unregister(mWhenReceiver);
    }

    @CallSuper
    @Override
    public void attachView(MvpView mvpView) {

    }

    @CallSuper
    @Override
    public void detachView() {

    }

    @CallSuper
    @Override
    public void onRequestResultSuccess(BaseRequest request, Object model) {
        if (mOnRequestListener != null) {
            mOnRequestListener.onRequestResultSuccess(request, model);
        }

    }

    @CallSuper
    @Override
    public void onRequestResultFailed(BaseRequest request, RequestFailure responseFailedReason) {
        if (mOnRequestListener != null) {
            mOnRequestListener.onRequestResultFailed(request, responseFailedReason);
        }
    }

    @CallSuper
    @Override
    public void onRequestWhenStart(BaseRequest request) {
        if (isDialogRequest(request)) {
            DialogRequest dialogRequest = (DialogRequest) request;
            if (mMvpView instanceof MvpDialogView) {
                MvpDialogView mvpRequestView = (MvpDialogView) mMvpView;
                mvpRequestView.showRequestDialog(dialogRequest.getLoadingText(), dialogRequest.isCancelableDialog());
            }
        }
    }

    @CallSuper
    @Override
    public void onRequestWhenEnd(BaseRequest request) {
        // 如果是需要显示加载中的dialog的
        if (isDialogRequest(request)) {
            // 消失loading dialog
            if (mMvpView instanceof MvpDialogView) {
                MvpDialogView mvpRequestView = (MvpDialogView) mMvpView;
                mvpRequestView.dismissRequestDialog();
            }
        }
    }

    //====================================
    // 内部
    //====================================

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


    public interface OnRequestListener {
        void onRequestResultSuccess(BaseRequest request, Object model);

        void onRequestResultFailed(BaseRequest request, RequestFailure responseFailedReason);
    }
}
