package com.lrchao.fakejob;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.lrchao.utils.LrchaoUtils;
import com.lrchao.views.LrchaoViews;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/8/31 上午10:54
 */

public class MyApplication extends Application {

    private static MyApplication sInstance;

    private String mHostUrl;

    public static MyApplication getApplication() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        LrchaoUtils.getInstance().init(this);
        LrchaoViews.getInstance().init(this);
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

    public String getHostUrl() {
        return mHostUrl;
    }

    public void init(String hostUrl) {
        mHostUrl = hostUrl;
    }
}
