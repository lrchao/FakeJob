package com.lrchao.fakejob.mvp.common;

import com.lrchao.fakejob.exception.InitializationNotCompleteException;
import com.lrchao.fakejob.mvp.BasePresenter;
import com.lrchao.fakejob.mvp.MvpView;
import com.lrchao.fakejob.mvp.common.mvp_view.MvpListView;

import java.util.List;

/**
 * Description: 列表的Presenter
 *
 * @author liuranchao
 * @date 16/5/12 下午11:55
 */
public class ListPresenter extends PagePresenter {

    /**
     * 分页的第一页
     */
    private static final int PAGE_FIRST_NUM = 0;

    /**
     * 分页 默认的页数
     */
    private int mPageNum = PAGE_FIRST_NUM;

    /**
     * 是否加载更多可用
     */
    private boolean mEnableLoadmore;

    //========================================
    // public
    //========================================

    public ListPresenter(MvpView mvpView, BasePresenter basePresenter) {
        super(mvpView, basePresenter);
    }

    /**
     * 获取页码
     */
    public int getPageNum() {
        return mPageNum;
    }

    /**
     * @param enableLoadmore 设置是否可以加载更多
     */
    public final void setEnableLoadmore(boolean enableLoadmore) {
        mEnableLoadmore = enableLoadmore;
    }

    /**
     * 进行刷新
     */
    public final void refresh() {
        mPageNum = PAGE_FIRST_NUM;
        loadPageData();
    }

    /**
     * 进行加载更多
     */
    public final void loadMore() {
        if (mEnableLoadmore) {
            mPageNum++;
            loadPageData();
        }
    }

    //========================================
    // 内部
    //========================================

    /**
     * 设置数据
     *
     * @param data List
     */
    public final void setListData(List data) {
        // 检查data为null的情况
        boolean isDataNull = isNoData(data);

        MvpListView mvpSwipeRefreshListView;

        if (mMvpView instanceof MvpListView) {
            mvpSwipeRefreshListView = (MvpListView) mMvpView;
        } else {
            throw new InitializationNotCompleteException("MvpView must instanceof MvpSwipeRefreshListView");
        }

        if (!isDataNull) {
            // 检查 adapter 为 null
            boolean isAdapterNull = mvpSwipeRefreshListView.initAdapter(data);
            // 设置footer

            if (isAdapterNull) {
                if (mEnableLoadmore) {
                    mvpSwipeRefreshListView.initFooter();
                }

            } else {
                if (mEnableLoadmore && mPageNum == PAGE_FIRST_NUM) {
                    mvpSwipeRefreshListView.showFooterLoading();
                }
            }

            if (!isAdapterNull) {

                if (mPageNum == PAGE_FIRST_NUM) {
                    mvpSwipeRefreshListView.clearDataOnRefresh();
                }

                mvpSwipeRefreshListView.setAdapterData(data);
            }
        } else {

            if (mPageNum == PAGE_FIRST_NUM) {
                mvpSwipeRefreshListView.showPageLoadingEmptyView();
            } else {
                mvpSwipeRefreshListView.showFooterNoMoreData();
            }
        }
    }

    /**
     * 当传入的List数据为null的情况
     * 1. 请求第一页为null
     * 2. 请求最后一页为null
     *
     * @param data List
     */
    private boolean isNoData(List data) {
        return data == null || data.size() <= 0;
    }

}
