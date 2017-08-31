package com.lrchao.fakejob.ui.activity.login;


import com.lrchao.fakejob.mvp.MvpView;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/26 上午11:03
 */

public interface LoginContract {

    interface View extends MvpView {
        void showToast(int toast);

        void finishPage();

    }


    interface Presenter {
        void login(CharSequence userName, CharSequence password);
    }
}
