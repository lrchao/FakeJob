package com.lrchao.fakejob.network.request.business;

import android.content.Intent;

import com.lrchao.fakejob.constant.BundleKey;
import com.lrchao.fakejob.model.json.home.HomeJobModel;
import com.lrchao.fakejob.network.request.PageRequest;
import com.lrchao.fakejob.network.request.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/22 上午11:08
 */

public class JobCategoryListReq extends PageRequest<List<HomeJobModel>> {

    private int mCategoryId;

    @Override
    public void getParamsIntent(Intent intent) {
        super.getParamsIntent(intent);
        mCategoryId = intent.getIntExtra(BundleKey.INTENT_EXTRA_CATEGORY_ID, 0);
    }

    @Override
    protected void getFilterParams(Map<String, Object> params) {
        super.getFilterParams(params);
        params.put("id", mCategoryId);
    }

    @Override
    public String getMethod() {
        return RequestMethod.GET;
    }

    @Override
    protected String getUrlPath() {
        return "fake/wall/v1.0/job-category.json";
    }
}
