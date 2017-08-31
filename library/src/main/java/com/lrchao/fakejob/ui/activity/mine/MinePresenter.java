package com.lrchao.fakejob.ui.activity.mine;

import android.content.SharedPreferences;

import com.lrchao.fakejob.constant.SharedPreferenceKey;
import com.lrchao.fakejob.manager.session.SessionManager;
import com.lrchao.fakejob.manager.shared_preference.CommonSharedPreference;
import com.lrchao.fakejob.manager.user.UserInfoManager;
import com.lrchao.fakejob.mvp.BasePresenter;
import com.lrchao.fakejob.network.request.BaseRequest;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/26 上午11:27
 */

public class MinePresenter extends BasePresenter<MineContract.View> implements
        MineContract.Presenter, SharedPreferences.OnSharedPreferenceChangeListener {


    @Override
    public void start() {
        super.start();
        CommonSharedPreference.getsInstance().registerOnChangedListener(this);

    }

    @Override
    public void end() {
        super.end();
        CommonSharedPreference.getsInstance().unregisterOnChangedListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (SharedPreferenceKey.PREF_FAKE_IS_LOGIN.getKey().equals(key)) {

            getMvpView().bindHeaderView(SessionManager.getInstance().isLogin());

        } else if (SharedPreferenceKey.PREF_FAKE_LOGIN_USER_NAME.getKey().equals(key)) {

            getMvpView().bindUserInfoView(UserInfoManager.getInstance().getUserName(),
                    "");

        }

    }

    @Override
    public void logout() {
        SessionManager.getInstance().logout();
    }

    @Override
    public void onRequestResultSuccess(BaseRequest request, Object model) {
        super.onRequestResultSuccess(request, model);
    }
}
