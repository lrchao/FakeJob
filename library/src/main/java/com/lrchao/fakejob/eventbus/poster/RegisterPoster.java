package com.lrchao.fakejob.eventbus.poster;


import com.lrchao.fakejob.model.json.login.LoginModel;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/26 下午4:03
 */

public class RegisterPoster {

    private LoginModel mFakeLoginModel;

    public RegisterPoster(LoginModel fakeLoginModel) {
        mFakeLoginModel = fakeLoginModel;
    }

    public LoginModel getFakeLoginModel() {
        return mFakeLoginModel;
    }
}
