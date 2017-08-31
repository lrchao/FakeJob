package com.lrchao.fakejob.mvp.common;

/**
 * Description: 页面请求的Presenter接口
 *
 * @author liuranchao
 * @date 16/5/12 上午11:30
 */
public interface MvpRequestPagePresenter {

    /**
     * 加载页面请求
     */
    void load();

    /**
     * 是否自动加载
     */
    boolean isAutoLoadPage();
}
