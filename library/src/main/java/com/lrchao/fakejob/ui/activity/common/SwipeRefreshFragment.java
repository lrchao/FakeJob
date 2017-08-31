package com.lrchao.fakejob.ui.activity.common;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.lrchao.fakejob.R;
import com.lrchao.fakejob.mvp.common.mvp_view.MvpRefreshView;
import com.lrchao.views.swipe.CustomSwipeRefreshLayout;


/**
 * Description: 下拉刷新的PageNetworkFragment
 *
 * @author liuranchao
 * @date 16/2/24 下午3:16
 */
public abstract class SwipeRefreshFragment extends PageNetworkFragment implements
        SwipeRefreshLayout.OnRefreshListener,
        MvpRefreshView {

    /**
     * 下拉刷新的view
     */
    protected CustomSwipeRefreshLayout mSwipeRefreshLayout;

    /**
     * 是否阻塞刷新
     */
    protected boolean onBlockRefresh() {
        return true;
    }

    @Override
    protected final View getLayoutView(LayoutInflater inflater, ViewGroup container, boolean attachToRoot) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_swipe_refresh, container, attachToRoot);
        mSwipeRefreshLayout = (CustomSwipeRefreshLayout) viewGroup.getChildAt(0);
        mSwipeRefreshLayout.init();
        mSwipeRefreshLayout.setOnRefreshListener(this);

        View superView = inflater.inflate(getLayoutViewId(), container, attachToRoot);
        // 下拉刷新包含的view
        FrameLayout containerFrameLayout = (FrameLayout) viewGroup.findViewById(R.id.swipe_refresh_layout_container);
        containerFrameLayout.addView(superView);
        mSwipeRefreshLayout.init();
        return viewGroup;
    }

    @Override
    public final void onRefresh() {
        if (onBlockRefresh()) {
            loadPageData();
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public final void onRefreshEnd() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
