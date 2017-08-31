package com.lrchao.fakejob.ui.activity.category;


import com.lrchao.fakejob.model.json.home.HomeJobModel;
import com.lrchao.fakejob.mvp.BasePresenter;
import com.lrchao.fakejob.network.RequestIntentFactory;
import com.lrchao.fakejob.network.request.BaseRequest;

import java.util.List;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/22 上午11:14
 */

public class CategoryJobListPresenter extends BasePresenter<CategoryJobListContract.View> implements
        CategoryJobListContract.Presenter {

    private int mCategoryId;


    @Override
    public void load() {
        sendRequest(RequestIntentFactory.getJobCategoryList(mCategoryId));

    }

    @Override
    public boolean isAutoLoadPage() {
        return true;
    }

    @Override
    public void setInitDate(int categoryId) {
        mCategoryId = categoryId;
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
