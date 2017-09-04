package com.lrchao.fakejob.ui.activity.home;


import android.content.SharedPreferences;

import com.lrchao.fakejob.constant.SharedPreferenceKey;
import com.lrchao.fakejob.manager.shared_preference.CommonSharedPreference;
import com.lrchao.fakejob.model.json.home.HomeModel;
import com.lrchao.fakejob.mvp.BasePresenter;
import com.lrchao.fakejob.network.RequestIntentFactory;
import com.lrchao.fakejob.network.request.BaseRequest;
import com.lrchao.fakejob.utils.NavUtils;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/21 下午3:27
 */

public class HomePresenter extends BasePresenter<HomeContract.View> implements
        HomeContract.Presenter, SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void start() {
        super.start();
        CommonSharedPreference.getsInstance().registerOnChangedListener(this);
    }

    @Override
    public void end() {
        super.end();
        CommonSharedPreference.getsInstance().unregisterOnChangedListener(this);
    }

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

    @Override
    public void navToLocation() {
        NavUtils.get().navToLocation(getContext());
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (SharedPreferenceKey.PREF_CURRENT_LOCATION.getKey().equals(s)) {

            getMvpView().bindCurrentLocationView();
        }
    }
}
