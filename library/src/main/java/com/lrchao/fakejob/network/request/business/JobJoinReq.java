package com.lrchao.fakejob.network.request.business;

import android.content.Intent;

import com.lrchao.fakejob.constant.BundleKey;
import com.lrchao.fakejob.model.json.EmptyModel;
import com.lrchao.fakejob.network.post_body.PostBody;
import com.lrchao.fakejob.network.post_body.PostJSONBody;
import com.lrchao.fakejob.network.request.DialogRequest;
import com.lrchao.fakejob.network.request.RequestMethod;


/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/7/2 下午6:59
 */

public class JobJoinReq extends DialogRequest<EmptyModel> {

    private int mId;

    @Override
    public void getParamsIntent(Intent intent) {
        super.getParamsIntent(intent);
        mId = intent.getIntExtra(BundleKey.INTENT_EXTRA_JOB_ID, 0);
    }

    @Override
    public String getMethod() {
        return RequestMethod.POST;
    }

    @Override
    protected String getUrlPath() {
        return "fake/wall/v1.0/job-join.json";
    }

    @Override
    public PostBody getPostBody() {
        PostJSONBody postFormBody = new PostJSONBody();
        postFormBody.put("id", mId);
        return postFormBody;
    }
}
