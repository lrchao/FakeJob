package com.lrchao.fakejob.ui.activity.mine;


import com.lrchao.fakejob.mvp.MvpView;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/26 上午11:27
 */

public interface MineContract {

    interface View extends MvpView {

        void bindHeaderView(boolean login);

        void bindUserInfoView(String userName, String userIcon);
    }


    interface Presenter {
        void logout();
    }


}
