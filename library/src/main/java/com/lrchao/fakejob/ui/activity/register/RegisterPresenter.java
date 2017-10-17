package com.lrchao.fakejob.ui.activity.register;

import android.text.TextUtils;

import com.lrchao.fakejob.eventbus.poster.RegisterPoster;
import com.lrchao.fakejob.model.json.login.LoginModel;
import com.lrchao.fakejob.mvp.BasePresenter;
import com.lrchao.fakejob.network.RequestIntentFactory;
import com.lrchao.fakejob.network.request.BaseRequest;
import com.lrchao.fakejob.network.request.RequestActionKey;
import com.lrchao.utils.EventBusUtils;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/26 下午2:33
 */

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements
        RegisterContract.Presenter {

    @Override
    public void register(CharSequence phoneNumber, CharSequence password, CharSequence confirmPassword) {

        if (TextUtils.isEmpty(phoneNumber)) {
            getMvpView().showToast("请输入正确的手机号");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            getMvpView().showToast("请输入正确的密码");
            return;
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            getMvpView().showToast("请输入正确的确认密码");
            return;
        }

        if (!password.toString().equals(confirmPassword.toString())) {
            getMvpView().showToast("两次密码不一致");
            return;
        }

        sendRequest(RequestIntentFactory.getRegister(
                phoneNumber.toString().trim(),
                password.toString().trim()));


    }

    @Override
    public void onRequestResultSuccess(BaseRequest request, Object model) {
        super.onRequestResultSuccess(request, model);
        if (request.getAction() == RequestActionKey.ACTION_REGISTER) {

            LoginModel fakeLoginModel = (LoginModel) model;
            EventBusUtils.post(new RegisterPoster(fakeLoginModel));
            getMvpView().finishPage();

        }
    }
}
