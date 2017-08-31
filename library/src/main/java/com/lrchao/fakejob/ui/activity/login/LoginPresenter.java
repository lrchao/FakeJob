package com.lrchao.fakejob.ui.activity.login;

import android.support.annotation.MainThread;
import android.text.TextUtils;

import com.lrchao.fakejob.R;
import com.lrchao.fakejob.eventbus.poster.RegisterPoster;
import com.lrchao.fakejob.manager.user.UserInfoManager;
import com.lrchao.fakejob.model.json.login.LoginModel;
import com.lrchao.fakejob.mvp.BasePresenter;
import com.lrchao.fakejob.network.RequestIntentFactory;
import com.lrchao.fakejob.network.request.BaseRequest;
import com.lrchao.utils.EventBusUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/26 上午11:03
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements
        LoginContract.Presenter {

    @Override
    public void start() {
        super.start();
        EventBusUtils.register(this);
    }

    @Override
    public void end() {
        super.end();
        EventBusUtils.unregister(this);
    }

    @MainThread
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RegisterPoster poster) {
        loginSuccess(poster.getFakeLoginModel());
    }

    private void loginSuccess(LoginModel fakeLoginModel) {
        UserInfoManager.getInstance().login(fakeLoginModel);
        getMvpView().finishPage();
    }

    @Override
    public void login(CharSequence userName, CharSequence password) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            getMvpView().showToast(R.string.toast_login_error);
            return;
        }

        sendRequest(RequestIntentFactory.getLogin(userName.toString().trim(),
                password.toString().trim()));
    }

    @Override
    public void onRequestResultSuccess(BaseRequest request, Object model) {
        super.onRequestResultSuccess(request, model);

        LoginModel fakeLoginModel = (LoginModel) model;
        loginSuccess(fakeLoginModel);

    }
}
