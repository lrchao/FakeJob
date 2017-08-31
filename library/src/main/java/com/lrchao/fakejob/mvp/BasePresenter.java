package com.lrchao.fakejob.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import com.lrchao.fakejob.MyApplication;
import com.lrchao.fakejob.eventbus.receiver.BaseEventReceiverModel;
import com.lrchao.fakejob.mvp.common.ListPresenter;
import com.lrchao.fakejob.mvp.common.NetworkPresenter;
import com.lrchao.fakejob.network.request.BaseRequest;
import com.lrchao.fakejob.network.request_failure.RequestFailure;
import com.lrchao.fakejob.ui.activity.common.BaseFragment;
import com.lrchao.utils.EventBusUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Description: Presenter的基类
 *
 * @author liuranchao
 * @date 16/4/19 下午2:17
 */
public abstract class BasePresenter<T extends MvpView> implements MvpPresenter<T>,
        NetworkPresenter.OnRequestListener {

    /**
     * Handler
     */
    protected MainHandler mMainHandler;
    /**
     * Mvp的view
     */
    private T mMvpView;
    /**
     * 公共的Presenter
     */
    private ListPresenter mNetworkPresenter;

    /**
     * EventBus Receiver集合
     */
    private ArrayList<BaseEventReceiverModel> mEventReceiverModels = new ArrayList<>();

    //=============================================
    // 外部、子类可调用的
    //=============================================


    public final ListPresenter getNetworkPresenter() {
        return mNetworkPresenter;
    }


    /**
     * 页面统一发起请求的入口
     *
     * @param intent 包含request action 等信息
     */
    protected final void sendRequest(Intent intent) {
        mNetworkPresenter.sendRequest(intent);
    }

    /**
     * Handler的回调方法
     *
     * @param msg Message
     */
    @CallSuper
    protected void handleMainMessage(Message msg) {

    }

    /**
     * 请求成功回调
     *
     * @param request BaseRequest
     * @param model   返回的对象
     */
    @CallSuper
    @Override
    public void onRequestResultSuccess(BaseRequest request, Object model) {

    }

    /**
     * 请求失败的回调
     *
     * @param request              BaseRequest
     * @param responseFailedReason RequestFailure
     */
    @CallSuper
    @Override
    public void onRequestResultFailed(BaseRequest request, RequestFailure responseFailedReason) {

    }

    /**
     * 获取Activity
     */
    @Nullable
    protected final Activity getActivity() {
        if (getMvpView() != null && (getMvpView() instanceof BaseFragment)) {
            BaseFragment baseFragment = (BaseFragment) getMvpView();
            return baseFragment.getActivity();
        }
        return null;
    }

    /**
     * 获取Context
     */
    protected final Context getContext() {
        return MyApplication.getApplication();
    }

    /**
     * 子类负责填充EventBus Receiver
     *
     * @param eventReceiverModels ArrayList<BaseEventReceiverModel>
     */
    protected void initEventBusReceivers(ArrayList<BaseEventReceiverModel> eventReceiverModels) {

    }

    //=============================================
    // 内部的
    //=============================================

    /**
     * 添加MvpView
     *
     * @param mvpView MvpView
     */
    @Override
    public final void attachView(T mvpView) {
        mMvpView = mvpView;
    }

    /**
     * 移除MvpView
     */
    @Override
    public final void detachView() {
        mMvpView = null;
    }

    /**
     * 获取MvpView
     */
    public final T getMvpView() {
        return mMvpView;
    }

    public boolean isEmptyView() {
        return mMvpView == null;
    }

    @CallSuper
    @Override
    public void start() {
        mMainHandler = new MainHandler(new WeakReference(this));
        initEventBusReceivers(mEventReceiverModels);
        registerEventBus();

        mNetworkPresenter = new ListPresenter(getMvpView(), this);
        mNetworkPresenter.setOnRequestListener(this);
        mNetworkPresenter.start();
    }

    private void registerEventBus() {
        if (mEventReceiverModels.size() > 0) {
            for (BaseEventReceiverModel receiverModel : mEventReceiverModels) {
                EventBusUtils.register(receiverModel);
            }
        }
    }

    private void unregisterEventBus() {
        if (mEventReceiverModels.size() > 0) {
            for (BaseEventReceiverModel receiverModel : mEventReceiverModels) {
                EventBusUtils.unregister(receiverModel);
            }
        }
    }

    @CallSuper
    @Override
    public void end() {
        unregisterEventBus();
        mNetworkPresenter.end();
    }

    /**
     * 全局静态的handler
     */
    public static final class MainHandler extends Handler {

        private final WeakReference<BasePresenter> mOuterContext;

        public MainHandler(WeakReference<BasePresenter> context) {
            this.mOuterContext = context;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            BasePresenter presenter = mOuterContext.get();
            if (presenter != null) {
                presenter.handleMainMessage(msg);
            }
        }

        protected void onDestroy() {
            if (mOuterContext != null) {
                BasePresenter presenter = mOuterContext.get();
                if (presenter != null) {
                    presenter.end();
                }
                mOuterContext.clear();
            }
        }
    }
}
