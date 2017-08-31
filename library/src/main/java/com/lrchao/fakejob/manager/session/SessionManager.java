package com.lrchao.fakejob.manager.session;


import com.lrchao.fakejob.BuildConfig;
import com.lrchao.fakejob.constant.SharedPreferenceKey;
import com.lrchao.fakejob.manager.shared_preference.AccountSharedPreference;
import com.lrchao.fakejob.manager.shared_preference.CommonSharedPreference;

/**
 * Description: 管理会话类
 *
 * @author liuranchao
 * @date 16/3/31 下午4:38
 */
public final class SessionManager {

    private static SessionManager sInstance;

    /**
     * 当前的页面名称
     */
    private String mCurrentPageName;

    /**
     * 前一个页面的名称
     */
    private String mPrePageName;

    private SessionManager() {
    }

    public static SessionManager getInstance() {
        synchronized (SessionManager.class) {
            if (sInstance == null) {
                sInstance = new SessionManager();
            }
        }
        return sInstance;
    }

    public String getPrePageName() {
        return mPrePageName;
    }

    public void setPrePageName(String prePageName) {
        mPrePageName = prePageName;
    }

    public String getCurrentPageName() {
        return mCurrentPageName;
    }

    public void setCurrentPageName(String currentPageName) {
        mCurrentPageName = currentPageName;
    }

    public void init() {

    }

    /**
     * MainActivity时调用
     */
    public void startAtSplash() {

        /**
         * 检查版本升级
         */
        int currentVersionCode = CommonSharedPreference.getsInstance().getIntValue(
                SharedPreferenceKey.PREF_CURRENT_VERSION_CODE);

        // 判断是不是第一次安装
        if (currentVersionCode <= 0) {
            CommonSharedPreference.getsInstance().setValue(
                    SharedPreferenceKey.PREF_IS_FIRST_INSTALL, true);
        } else {
            CommonSharedPreference.getsInstance().setValue(
                    SharedPreferenceKey.PREF_IS_FIRST_INSTALL, false);
        }

        // 如果当前版本号 大于 缓存的版本号 则表示 为首次启动
        if (BuildConfig.VERSION_CODE > currentVersionCode) {
            CommonSharedPreference.getsInstance().setValue(
                    SharedPreferenceKey.PREF_IS_FIRST_LAUNCH, true);
        } else {
            CommonSharedPreference.getsInstance().setValue(
                    SharedPreferenceKey.PREF_IS_FIRST_LAUNCH, false);
        }

        // 修改当前的缓存版本号
        CommonSharedPreference.getsInstance().
                setValue(SharedPreferenceKey.PREF_CURRENT_VERSION_CODE, BuildConfig.VERSION_CODE);

        boolean isFirstLaunch = CommonSharedPreference.getsInstance().
                getBooleanValue(SharedPreferenceKey.PREF_IS_FIRST_LAUNCH);
        if (isFirstLaunch) {

            // 如果是第一次安装，则清空升级文件

//            FileUtils.delete(MyFileUtils.getUpgradeFile(
//                    UserApplication.getApplication()).getAbsolutePath());

            // 第一次启动，并且在不是debug的情况下，即 正式用户包
            if (!BuildConfig.DEBUG) {
                // 删除 崩溃日志
                //FileUtils.deleteDir(MyFileUtils.getCrashDir());
                // 删除log dir
                //FileUtils.deleteDir(MyFileUtils.getLogDir());
                // 删除Http
                //HttpCacheTable.delete();
            }
        }

        boolean isLogin = CommonSharedPreference.getsInstance().
                getBooleanValue(SharedPreferenceKey.PREF_FAKE_IS_LOGIN);
        // 如果是第一次启动则获取用户信息
        if (isLogin) {
//            UserApplication.getApplication().
//                    startService(RequestIntentFactory.getMyProfile(false));
        }

        /**
         * 刷新登录的token
         */
        //UserApplication.getApplication().startService(RequestIntentFactory.refreshToken());
    }

    /**
     * 本次会话结束处理
     */
    public void endAtMain() {
    }

    /**
     * Logout 处理
     */
    public void logout() {
        CommonSharedPreference.getsInstance().setValue(SharedPreferenceKey.PREF_FAKE_IS_LOGIN, false);
        AccountSharedPreference.getsInstance().clear();
    }

    /**
     * Login
     */
    public void login() {
        CommonSharedPreference.getsInstance().setValue(SharedPreferenceKey.PREF_FAKE_IS_LOGIN, true);
    }

    public boolean isLogin() {
        return CommonSharedPreference.getsInstance().getBooleanValue(SharedPreferenceKey.PREF_FAKE_IS_LOGIN);
    }

}
