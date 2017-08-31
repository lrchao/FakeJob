package com.lrchao.fakejob.ui.activity.register;

import com.lrchao.fakejob.mvp.MvpView;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/26 下午2:34
 */

public interface RegisterContract {

    interface View extends MvpView {
        void showToast(CharSequence s);

        void finishPage();
    }


    interface Presenter {

        void register(CharSequence phoneNumber, CharSequence code, CharSequence password);
    }
}
