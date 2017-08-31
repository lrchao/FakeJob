package com.lrchao.fakejob.ui.activity.job;


import com.lrchao.fakejob.model.json.home.HomeJobModel;
import com.lrchao.fakejob.mvp.BasePresenter;
import com.lrchao.fakejob.network.RequestIntentFactory;
import com.lrchao.fakejob.network.request.BaseRequest;

import java.util.List;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/28 上午10:22
 */

public class MyJobListPresenter extends BasePresenter<MyJobListContract.View> implements
        MyJobListContract.Presenter {
    @Override
    public void load() {
        sendRequest(RequestIntentFactory.getJobCategoryList(5));
    }

    @Override
    public boolean isAutoLoadPage() {
        return true;
    }

    @Override
    public void onRequestResultSuccess(BaseRequest request, Object model) {
        super.onRequestResultSuccess(request, model);

        List<HomeJobModel> list = (List<HomeJobModel>) model;

        if (list != null && list.size() > 0) {

            getMvpView().bindView(list);
        }


    }
}
