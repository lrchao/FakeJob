package com.lrchao.fakejob.ui.activity.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lrchao.fakejob.R;
import com.lrchao.fakejob.exception.InitializationNotCompleteException;
import com.lrchao.fakejob.mvp.common.mvp_view.MvpPageView;
import com.lrchao.utils.ResourceUtils;
import com.lrchao.views.progressbar.PageLoadingView;
import com.lrchao.views.scrollview.MyScrollView;


/**
 * Description: 页面加载网络数据的fragment
 * 带有loading view
 * 子类需要实现的：
 * 子类的布局必须为FragmentLayout LinearLayout RelativeLayout
 *
 * @author liuranchao
 * @date 16/2/17 上午9:45
 */
public abstract class PageNetworkFragment extends NetworkFragment
        implements PageLoadingView.OnPageLoadingViewClickListener,
        MvpPageView {

    /**
     * 页面的loading view
     */
    private PageLoadingView mPageLoadingView;

    /**
     * activity是否已经创建
     */
    private boolean mIsActivityCreated = false;


    //======================================
    // 子类调用
    //======================================

    /**
     * 空页面的显示
     */
    protected String getEmptyPageStr() {
        return "";
    }

    /**
     * 显示无数据页面
     */
    protected final void showPageLoadingViewEmpty() {
        if (TextUtils.isEmpty(getEmptyPageStr())) {
            mPageLoadingView.show(ResourceUtils.getString(R.string.page_empty), "");
        } else {
            mPageLoadingView.show(getEmptyPageStr(), "");
        }
    }

    @Override
    public final void onPageLoadingViewClick() {
        loadPageData();
    }

    protected final void loadPageData() {
        mBasePresenter.getNetworkPresenter().loadPageData();
    }

    protected void showPageLoadingCustomView(View customPageView) {
        mPageLoadingView.setCustomResultView(customPageView);
    }

    //======================================
    // 内部的
    //======================================

    @Override
    public final void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!mIsActivityCreated) {
            mIsActivityCreated = true;
            loadPageData();
        }
    }

    @Override
    protected final void attachView(ViewGroup viewGroup) {

        if (viewGroup instanceof MyScrollView) {
            throw new InitializationNotCompleteException("fragment root view can not be scrollview");
        }

        // 如果界面已经创建，不添加PageLoadingView
        if (mIsActivityCreated) {
            return;
        }

        // 防止onCreateView多次调用
        mPageLoadingView = new PageLoadingView(getActivity());
        mPageLoadingView.setOnPageLoadingViewClickListener(this);
        if (viewGroup.getChildAt(0) != viewGroup) {
            ViewGroup.LayoutParams mLayoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            if (viewGroup instanceof LinearLayout) {
                viewGroup.addView(mPageLoadingView, 0, mLayoutParams);
            } else if (viewGroup instanceof RelativeLayout) {
                RelativeLayout relativeView = (RelativeLayout) viewGroup;
                relativeView.addView(mPageLoadingView, mLayoutParams);
            } else {
                viewGroup.addView(mPageLoadingView, mLayoutParams);
            }
        }
    }

    @Override
    public final void showPageLoading() {
        mPageLoadingView.showLoading();
    }

    @Override
    public final void dismissPageLoading() {
        mPageLoadingView.dismiss();
    }

    @Override
    public final void showPageFailed(String msg, String btnText) {
        mPageLoadingView.show(msg, btnText);
    }

}
