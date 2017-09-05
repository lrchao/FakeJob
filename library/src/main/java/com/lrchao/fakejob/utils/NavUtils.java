package com.lrchao.fakejob.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.lrchao.fakejob.JobApp;
import com.lrchao.fakejob.constant.BundleKey;
import com.lrchao.fakejob.constant.SharedPreferenceKey;
import com.lrchao.fakejob.manager.shared_preference.CommonSharedPreference;
import com.lrchao.fakejob.ui.activity.category.CategoryJobListActivity;
import com.lrchao.fakejob.ui.activity.favorites.MyFavoritesListActivity;
import com.lrchao.fakejob.ui.activity.job.JobDetailsActivity;
import com.lrchao.fakejob.ui.activity.job.MyJobListActivity;
import com.lrchao.fakejob.ui.activity.location.LocationActivity;
import com.lrchao.fakejob.ui.activity.login.LoginActivity;
import com.lrchao.fakejob.ui.activity.main.MainActivity;
import com.lrchao.fakejob.ui.activity.register.RegisterActivity;
import com.lrchao.fakejob.ui.activity.search.SearchActivity;

import java.util.HashMap;


/**
 * Description: 页面导航的帮助类
 *
 * @author liuranchao
 * @date 16/3/15 下午2:39
 */
public final class NavUtils {

    /**
     * 需要登陆的页面, 在进入目标界面前进入登陆页面
     */
    private static final Class[] NEED_LOGIN_CLASS = {

    };
    private static NavUtils sInstance;
    /**
     * 需要登陆的页面集合
     */
    private HashMap<String, Class> mNeedLoginMapping;

    private NavUtils() {
        mNeedLoginMapping = new HashMap<>();
        for (Class clazz : NEED_LOGIN_CLASS) {
            mNeedLoginMapping.put(clazz.getName(), clazz);
        }
    }

    /**
     * @return Navigator实例
     */
    public static NavUtils get() {
        synchronized (NavUtils.class) {
            if (sInstance == null) {
                sInstance = new NavUtils();
            }
        }
        return sInstance;
    }


    /**
     * 统一调用startActivity
     *
     * @param intent Intent
     */
    private void startActivity(Context context, Intent intent) {
        if (context != null) {
            if (context == JobApp.getInstance().getContext()) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }

            checkNeedLogin(context, intent);
            context.startActivity(intent);
        }
    }

    /**
     * 统一调用startActivityForResult
     *
     * @param context     Activity
     * @param intent      Intent
     * @param requestCode Intent request code
     */
    private void startActivityForResult(Activity context, Intent intent, int requestCode) {
        if (context != null) {
            checkNeedLogin(context, intent);
            context.startActivityForResult(intent, requestCode);
        }
    }

    /**
     * 检查目标界面是不是需要登陆
     *
     * @param context Context
     * @param intent  跳转Intent
     */
    private void checkNeedLogin(Context context, Intent intent) {
        if (intent.getComponent() != null) {
            String targetClassName = intent.getComponent().getClassName();
            boolean isLogin = CommonSharedPreference.getsInstance().
                    getBooleanValue(SharedPreferenceKey.PREF_IS_LOGIN);
            if (mNeedLoginMapping.containsKey(targetClassName) && !isLogin) {
                //intent.setClass(context, LoginActivity.class);
            }
        }
    }

    /**
     * 跳转到MainActivity
     */
    public void navToMainActivity(Context context) {
        if (context != null) {
            Intent i = MainActivity.getCallingIntent(context);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(context, i);
        }
    }

    public void navToCategory(Context context, String title, int categoryId) {
        if (context != null) {
            Intent intent = CategoryJobListActivity.getCallingIntent(context);
            intent.putExtra(BundleKey.INTENT_EXTRA_TOOLBAR_TITLE, title);
            intent.putExtra(BundleKey.INTENT_EXTRA_CATEGORY_ID, categoryId);
            startActivity(context, intent);
        }
    }


    public void navToJobDetails(Context context, int jobId) {
        if (context != null) {
            Intent intent = JobDetailsActivity.getCallingIntent(context);
            intent.putExtra(BundleKey.INTENT_EXTRA_JOB_ID, jobId);
            startActivity(context, intent);
        }
    }


    public void navToLogin(Context context) {
        if (context != null) {
            startActivity(context, LoginActivity.getCallingIntent(context));
        }
    }

    public void navToRegister(Context context) {
        if (context != null) {
            startActivity(context, RegisterActivity.getCallingIntent(context));
        }
    }

    public void navToMyJobListActivity(Context context) {
        if (context != null) {
            startActivity(context, MyJobListActivity.getCallingIntent(context));
        }
    }


    public void navToMyFavoritesListActivity(Context context) {
        if (context != null) {
            startActivity(context, MyFavoritesListActivity.getCallingIntent(context));
        }
    }

    public void navToLocation(Context context) {
        if (context != null) {
            startActivity(context, LocationActivity.getCallingIntent(context));
        }
    }

    public void navToSearch(Context context) {
        if (context != null) {
            startActivity(context, SearchActivity.getCallingIntent(context));
        }
    }


    //=====================================================================================================
    // Fake
    //=====================================================================================================

    /**
     * 跳转到MainActivity
     *
     * @param tabIndex 显示Tab的索引
     */
//    public void navToFakeMainActivity(Context context, int tabIndex) {
//        if (context != null) {
//            Intent i = FakeMainActivity.getCallingIntent(context, tabIndex);
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(context, i);
//        }
//    }
//


}
