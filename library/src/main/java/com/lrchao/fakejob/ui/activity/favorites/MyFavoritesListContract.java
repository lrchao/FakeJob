package com.lrchao.fakejob.ui.activity.favorites;

import com.lrchao.fakejob.model.json.home.HomeJobModel;
import com.lrchao.fakejob.mvp.MvpView;
import com.lrchao.fakejob.mvp.common.MvpRequestPagePresenter;

import java.util.List;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/28 上午10:32
 */

public interface MyFavoritesListContract {

    interface View extends MvpView {
        void bindView(List<HomeJobModel> list);
    }


    interface Presenter extends MvpRequestPagePresenter {
    }
}
