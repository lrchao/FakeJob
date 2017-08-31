package com.lrchao.fakejob.manager.user;


import com.lrchao.fakejob.constant.SharedPreferenceKey;
import com.lrchao.fakejob.manager.session.SessionManager;
import com.lrchao.fakejob.manager.shared_preference.CommonSharedPreference;
import com.lrchao.fakejob.model.json.login.LoginModel;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/26 上午11:18
 */

public class UserInfoManager {

    private static UserInfoManager sInstance;

    private UserInfoManager() {
    }

    public static UserInfoManager getInstance() {
        synchronized (UserInfoManager.class) {
            if (sInstance == null) {
                sInstance = new UserInfoManager();
            }
        }
        return sInstance;
    }

    public void login(LoginModel fakeLoginModel) {

        CommonSharedPreference.getsInstance().setValue(SharedPreferenceKey.PREF_FAKE_LOGIN_USER_NAME, fakeLoginModel.getUserName());

        SessionManager.getInstance().login();
    }

    public String getUserName() {
        return CommonSharedPreference.getsInstance().getStringValue(SharedPreferenceKey.PREF_FAKE_LOGIN_USER_NAME);
    }

}
