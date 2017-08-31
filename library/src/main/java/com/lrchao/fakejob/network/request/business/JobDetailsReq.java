package com.lrchao.fakejob.network.request.business;

import android.content.Intent;

import com.lrchao.fakejob.constant.BundleKey;
import com.lrchao.fakejob.model.json.job.JobDetailsModel;
import com.lrchao.fakejob.network.request.PageRequest;
import com.lrchao.fakejob.network.request.RequestMethod;

import java.util.Map;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/22 下午5:15
 */

public class JobDetailsReq extends PageRequest<JobDetailsModel> {

    private int mId;

    @Override
    public void getParamsIntent(Intent intent) {
        super.getParamsIntent(intent);
        mId = intent.getIntExtra(BundleKey.INTENT_EXTRA_JOB_ID, 0);
    }

    @Override
    protected void getFilterParams(Map<String, Object> params) {
        super.getFilterParams(params);
        params.put("id", mId);
    }

    @Override
    public String getMethod() {
        return RequestMethod.GET;
    }

    @Override
    protected String getUrlPath() {
        return "fake/wall/v1.0/job-info.json";
    }
}
