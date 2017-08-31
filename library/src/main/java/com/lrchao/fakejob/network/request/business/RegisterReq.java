package com.lrchao.fakejob.network.request.business;

import android.content.Intent;

import com.lrchao.fakejob.constant.BundleKey;
import com.lrchao.fakejob.model.json.login.LoginModel;
import com.lrchao.fakejob.network.post_body.PostBody;
import com.lrchao.fakejob.network.post_body.PostJSONBody;
import com.lrchao.fakejob.network.request.DialogRequest;
import com.lrchao.fakejob.network.request.RequestMethod;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/26 下午3:55
 */

public class RegisterReq extends DialogRequest<LoginModel> {

    private String mUserName;
    private String mPassword;

    @Override
    public void getParamsIntent(Intent intent) {
        super.getParamsIntent(intent);
        mUserName = intent.getStringExtra(BundleKey.INTENT_EXTRA_PHONE_NUMBER);
        mPassword = intent.getStringExtra(BundleKey.INTENT_EXTRA_LOGIN_PASSWORD);
    }


    @Override
    public String getMethod() {
        return RequestMethod.POST;
    }

    @Override
    protected String getUrlPath() {
        return "fake/wall/v1.0/home-register.json";
    }

    @Override
    public PostBody getPostBody() {
        PostJSONBody postFormBody = new PostJSONBody();
        postFormBody.put("phone", mUserName);
        postFormBody.put("password", mPassword);
        return postFormBody;
    }
}
