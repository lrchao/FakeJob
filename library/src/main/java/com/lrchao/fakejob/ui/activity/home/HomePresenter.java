package com.lrchao.fakejob.ui.activity.home;


import com.lrchao.fakejob.model.json.home.HomeModel;
import com.lrchao.fakejob.mvp.BasePresenter;
import com.lrchao.fakejob.network.RequestIntentFactory;
import com.lrchao.fakejob.network.request.BaseRequest;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/21 下午3:27
 */

public class HomePresenter extends BasePresenter<HomeContract.View> implements
        HomeContract.Presenter {
    @Override
    public void load() {
        sendRequest(RequestIntentFactory.getHome());
    }

    @Override
    public boolean isAutoLoadPage() {
        return true;
    }

    @Override
    public void onRequestResultSuccess(BaseRequest request, Object model) {
        super.onRequestResultSuccess(request, model);

        HomeModel fakeHomeModel = (HomeModel) model;

        if (fakeHomeModel.getBannerList() != null && fakeHomeModel.getBannerList().size() > 0) {
            getMvpView().bindBannerView(fakeHomeModel.getBannerList());
        }

        if (fakeHomeModel.getJobList() != null && fakeHomeModel.getJobList().size() > 0) {
            getMvpView().bindJobListView(fakeHomeModel.getJobList());

        }


    }
}
