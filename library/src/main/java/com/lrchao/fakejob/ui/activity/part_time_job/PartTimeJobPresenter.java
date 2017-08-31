package com.lrchao.fakejob.ui.activity.part_time_job;


import com.lrchao.fakejob.model.json.job.PartTimeJobModel;
import com.lrchao.fakejob.mvp.BasePresenter;
import com.lrchao.fakejob.network.RequestIntentFactory;
import com.lrchao.fakejob.network.request.BaseRequest;

import java.util.List;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/23 上午11:32
 */

public class PartTimeJobPresenter extends BasePresenter<PartTimeJobContract.View>
        implements PartTimeJobContract.Presenter {

    @Override
    public void load() {
        sendRequest(RequestIntentFactory.getPartTimeJob());
    }

    @Override
    public boolean isAutoLoadPage() {
        return true;
    }

    @Override
    public void onRequestResultSuccess(BaseRequest request, Object model) {
        super.onRequestResultSuccess(request, model);
        List<PartTimeJobModel> list = (List<PartTimeJobModel>) model;

        getMvpView().bindView(list);
    }
}
