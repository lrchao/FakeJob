package com.lrchao.fakejob.mvp;

/**
 * Description: Presenter接口类
 *
 * @author liuranchao
 * @date 16/5/11 上午11:44
 */
public interface MvpPresenter<V extends MvpView> {

    /**
     * 做一些初始化工作
     */
    void start();

    /**
     * 做一些数据清除工作
     */
    void end();

    /**
     * 关联View
     *
     * @param mvpView MvpView
     */
    void attachView(V mvpView);

    /**
     * 分离View
     */
    void detachView();
}
