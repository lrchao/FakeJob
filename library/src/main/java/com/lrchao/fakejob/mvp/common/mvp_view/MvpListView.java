package com.lrchao.fakejob.mvp.common.mvp_view;

import java.util.List;

/**
 * Description: 列表的view
 *
 * @author liuranchao
 * @date 16/5/13 上午10:18
 */
public interface MvpListView {

    boolean initAdapter(List data);

    void initFooter();

    void showFooterLoading();

    void clearDataOnRefresh();

    void setAdapterData(List data);

    void showFooterNoMoreData();

    void showPageLoadingEmptyView();
}
