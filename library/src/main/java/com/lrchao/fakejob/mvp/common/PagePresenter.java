package com.lrchao.fakejob.mvp.common;

import android.content.SharedPreferences;
import android.support.annotation.CallSuper;

import com.lrchao.fakejob.R;
import com.lrchao.fakejob.constant.SharedPreferenceKey;
import com.lrchao.fakejob.manager.shared_preference.CommonSharedPreference;
import com.lrchao.fakejob.mvp.BasePresenter;
import com.lrchao.fakejob.mvp.MvpView;
import com.lrchao.fakejob.mvp.common.mvp_view.MvpPageView;
import com.lrchao.fakejob.mvp.common.mvp_view.MvpRefreshView;
import com.lrchao.fakejob.network.request.BaseRequest;
import com.lrchao.fakejob.network.request.PageRequest;
import com.lrchao.fakejob.network.request_failure.LogoutRequestFailure;
import com.lrchao.fakejob.network.request_failure.RequestFailure;
import com.lrchao.utils.ResourceUtils;


/**
 * Description:  页面请求的Presenter
 *
 * @author liuranchao
 * @date 16/5/12 下午10:56
 */
public class PagePresenter extends NetworkPresenter implements
        SharedPreferences.OnSharedPreferenceChangeListener {

    /**
     * 页面是否已经加载出过, 防止已经加载出来过，又刷新失败，出现加载错误的页面
     */
    private boolean mIsShownSuccess = false;

    /**
     * 是否请求加载, 防止没有请求加载的页面 也刷新
     */
    private boolean mIsRequestLoad = false;

    /**
     * 监听到登陆后 是否需要刷新标示
     */
    private boolean mIsNeedLoginRefresh;

    public PagePresenter(MvpView mvpView, BasePresenter basePresenter) {
        super(mvpView, basePresenter);
    }

    /**
     * 加载页面数据
     */
    public final void loadPageData() {
        if (mBasePresenter instanceof MvpRequestPagePresenter) {
            MvpRequestPagePresenter mvpRequestPageView = (MvpRequestPagePresenter) mBasePresenter;
            if (mvpRequestPageView.isAutoLoadPage()) {
                mIsRequestLoad = true;
                mvpRequestPageView.load();
            }
        }
    }

    @CallSuper
    @Override
    public void start() {
        super.start();
        CommonSharedPreference.getsInstance().registerOnChangedListener(this);
    }

    @CallSuper
    @Override
    public void end() {
        super.end();
        CommonSharedPreference.getsInstance().unregisterOnChangedListener(this);
    }

    @CallSuper
    @Override
    public void onRequestWhenStart(BaseRequest request) {
        super.onRequestWhenStart(request);
        if (request instanceof PageRequest && !mIsShownSuccess && mIsRequestLoad) {
            if (mMvpView instanceof MvpPageView) {
                MvpPageView mvpRequestPageView = (MvpPageView) mMvpView;
                mvpRequestPageView.showPageLoading();
            }
        }

    }

    @CallSuper
    @Override
    public void onRequestWhenEnd(BaseRequest request) {
        super.onRequestWhenEnd(request);
        if (request instanceof PageRequest) {

            if (mMvpView instanceof MvpPageView) {
                MvpPageView mvpRequestPageView = (MvpPageView) mMvpView;
                mvpRequestPageView.dismissPageLoading();
            }

            if (mMvpView instanceof MvpRefreshView) {
                MvpRefreshView mvpSwipeRefreshView = (MvpRefreshView) mMvpView;
                mvpSwipeRefreshView.onRefreshEnd();
            }
            mIsRequestLoad = false;
        }
    }

    @CallSuper
    @Override
    public void onRequestResultSuccess(BaseRequest request, Object model) {
        super.onRequestResultSuccess(request, model);
        if (request instanceof PageRequest) {
            mIsShownSuccess = true;
            mIsNeedLoginRefresh = false;
            if (mMvpView instanceof MvpPageView) {
                MvpPageView mvpRequestPageView = (MvpPageView) mMvpView;
                mvpRequestPageView.showPageView(model);
            }
        }
    }

    @CallSuper
    @Override
    public void onRequestResultFailed(BaseRequest request, RequestFailure requestFailure) {
        super.onRequestResultFailed(request, requestFailure);
        if (request instanceof PageRequest) {
            // 如果返回登陆失效 则 表示需要登陆，并且监听登陆成功后刷新页面
            if (requestFailure instanceof LogoutRequestFailure) {
                mIsNeedLoginRefresh = true;
            }
            if (request instanceof PageRequest) {
                if (mMvpView instanceof MvpPageView) {
                    MvpPageView mvpRequestPageView = (MvpPageView) mMvpView;
                    mvpRequestPageView.showPageFailed(requestFailure.getFriendlyMsg(),
                            ResourceUtils.getString(R.string.page_click_retry));
                }
            }
        }
    }

    @CallSuper
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (SharedPreferenceKey.PREF_FAKE_IS_LOGIN.getKey().equals(key) && mIsNeedLoginRefresh) {
            boolean isLogin = CommonSharedPreference.getsInstance().getBooleanValue(SharedPreferenceKey.PREF_FAKE_IS_LOGIN);
            if (isLogin) {
                loadPageData();
            }
        }
    }

}
