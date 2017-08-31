package com.lrchao.fakejob.mvp.common.mvp_view;

/**
 * Description: 页面加载的Mvp View
 *
 * @author liuranchao
 * @date 16/5/12 上午9:40
 */
public interface MvpPageView {

    /**
     * 显示页面的loading
     */
    void showPageLoading();

    /**
     * 消失页面的loading
     */
    void dismissPageLoading();

    /**
     * 显示页面加载后的数据
     */
    void showPageView(Object obj);

    /**
     * 显示页面加载失败
     *
     * @param msg     提示文本
     * @param btnText 按钮文本
     */
    void showPageFailed(String msg, String btnText);


}
