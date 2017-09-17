package com.lrchao.fakejob.ui.activity.home;


import com.lrchao.fakejob.model.json.home.HomeBannerModel;
import com.lrchao.fakejob.model.json.home.HomeJobModel;
import com.lrchao.fakejob.mvp.MvpView;
import com.lrchao.fakejob.mvp.common.MvpRequestPagePresenter;

import java.util.List;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/21 下午3:29
 */

public interface HomeContract {

    interface View extends MvpView {
        void bindBannerView(List<HomeBannerModel> bannerList);

        void bindJobListView(List<HomeJobModel> jobList);

        void bindCurrentLocationView();
    }


    interface Presenter extends MvpRequestPagePresenter {
        void navToLocation();

        void navToSearch();

        void navToSave();

        void navToLie();
    }
}
