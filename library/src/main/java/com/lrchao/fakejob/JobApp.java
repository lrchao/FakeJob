package com.lrchao.fakejob;

import android.content.Context;

import com.lrchao.utils.LrchaoUtils;
import com.lrchao.views.LrchaoViews;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/8/31 上午10:54
 */

public class JobApp {

    private static JobApp sInstance;

    private Context mContext;

    private String mHostUrl;

    public static JobApp getInstance() {
        synchronized (JobApp.class) {
            if (sInstance == null) {
                sInstance = new JobApp();
            }
        }
        return sInstance;
    }

    public Context getContext() {
        return mContext;
    }

    public void init(Context context) {
        mContext = context;
        LrchaoUtils.getInstance().init(context);
        LrchaoViews.getInstance().init(context);
    }

    public String getHostUrl() {
        return mHostUrl;
    }

    public void setUrlHost(String hostUrl) {
        mHostUrl = hostUrl;
    }
}
