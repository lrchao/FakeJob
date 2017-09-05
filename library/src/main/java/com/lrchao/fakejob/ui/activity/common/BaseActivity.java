package com.lrchao.fakejob.ui.activity.common;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lrchao.fakejob.BuildConfig;
import com.lrchao.fakejob.R;
import com.lrchao.fakejob.exception.InitializationNotCompleteException;
import com.lrchao.fakejob.manager.session.SessionManager;
import com.lrchao.fakejob.mvp.BasePresenter;
import com.lrchao.fakejob.mvp.MvpView;
import com.lrchao.utils.LogUtils;
import com.lrchao.utils.OSUtils;
import com.lrchao.utils.ResourceUtils;
import com.lrchao.views.toolbar.LrchaoToolBar;
import com.lrchao.views.toolbar.OnNavigationClickListener;

import java.util.List;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;


/**
 * Description: Activity的基类
 * 滑动back
 *
 * @author liuranchao
 * @date 16/3/12 下午10:18
 */
public abstract class BaseActivity extends SwipeBackActivity {


    /**
     * 输出日志的TAG
     */
    protected String mTag = this.getClass().getSimpleName();

    /**
     * FragmentManager
     */
    protected FragmentManager mFragmentManager;
    /**
     * Presenter
     */
    protected BasePresenter mPresenter;
    /**
     * toolbar view
     */
    private LrchaoToolBar mToolbarView;
    /**
     * 当前Activity的window
     */
    private Window mWindow;
    /**
     * 广播接受，负责接受LocalBroadcastReceiver
     */
    private BroadcastReceiver mLocalBroadcastReceiver;

    //====================
    // 子类继承的方法
    //====================

    /**
     * 子类设置加载的View
     *
     * @return layout view
     */
    protected abstract int getLayoutViewId();

    /**
     * 初始化View的方法
     */
    protected abstract void initView();

    /**
     * 初始化数据
     *
     * @param intent Intent
     */
    protected void initData(Intent intent) {
    }

    /**
     * 延迟加载，等UI初始化好后调用
     */
    protected void onDelayLoad() {

    }

    /**
     * 子类实现，负责初始化Presenter
     */
    protected BasePresenter getPresenter() {
        return null;
    }

    //====================
    // 子类调用的的方法
    //====================

    /**
     * 设置状态栏的颜色
     *
     * @param colorResId colors.xml
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected final void setStatusBarColor(@ColorRes int colorResId) {
        if (OSUtils.hasLollipop()) {
            mWindow.setStatusBarColor(ResourceUtils.getColor(colorResId));
        }
    }

    /**
     * 设置左边的back按钮,及点击事件
     */
//    protected final void setToolbarBackView(final ToolbarView.OnToolbarLeftClickListener listener) {
//        setToolbarLeft(R.drawable.toolbar_arrow_left, new ToolbarView.OnToolbarLeftClickListener() {
//            @Override
//            public void onToolbarLeftClick() {
//                if (listener != null) {
//                    listener.onToolbarLeftClick();
//                }
//            }
//        });
//    }

    /**
     * 设置左边的back按钮
     */
    protected final void setToolbarBackView() {
        initToolbar();
        mToolbarView.setNavigationIcon(R.drawable.job_toolbar_back);
        mToolbarView.setOnNavigationClickListener(new OnNavigationClickListener() {
            @Override
            public void onNavigationClick() {
                finish();
            }
        });
    }

    /**
     * 设置back
     *
     * @param drawableId drawable
     */
//    protected final void setToolbarBackView(@DrawableRes int drawableId) {
//        setToolbarLeft(drawableId, new ToolbarView.OnToolbarLeftClickListener() {
//            @Override
//            public void onToolbarLeftClick() {
//                finish();
//            }
//        });
//    }

    /**
     * 设置toolbar的标题
     *
     * @param strResId strings.xml的ID
     */
    protected final void setToolbarTitle(@StringRes int strResId) {
        initToolbar();
        mToolbarView.setCenterTitleText(ResourceUtils.getString(strResId));
        mToolbarView.setVisibility(View.VISIBLE);
        setTitle(ResourceUtils.getString(strResId));
    }

    /**
     * 设置toolbar的标题
     *
     * @param strTitle strings
     */
    protected final void setToolbarTitle(CharSequence strTitle) {
        initToolbar();
        mToolbarView.setCenterTitleText(strTitle);
        mToolbarView.setVisibility(View.VISIBLE);
        setTitle(strTitle);
    }

    /**
     * 设置toolbar的标题颜色
     */
    protected final void setToolbarTitleColor(@ColorRes int color) {
        initToolbar();
        mToolbarView.setCenterTitleColor(color);
        mToolbarView.setVisibility(View.VISIBLE);
    }

    /**
     * 设置toolbar的颜色
     *
     * @param colorId colors.xml的ID
     */
//    protected final void setToolbarBackgroundColor(@ColorRes int colorId) {
//        initToolbar();
//        mToolbarView.setBgColor(colorId);
//        mToolbarView.setVisibility(View.VISIBLE);
//    }

    /**
     * 设置toolbar的左边
     *
     * @param resId    资源ID
     * @param listener ToolbarView.OnToolbarLeftClickListener
     */
//    protected final void setToolbarLeft(@DrawableRes int resId, ToolbarView.OnToolbarLeftClickListener listener) {
//        initToolbar();
//        mToolbarView.setLeftRes(resId);
//        mToolbarView.setOnToolbarLeftClickListener(listener);
//        mToolbarView.setVisibility(View.VISIBLE);
//    }

    /**
     * 隐藏toolbar 分割线
     */
//    protected final void setToolbarLineGone() {
//        initToolbar();
//        mToolbarView.setLineGone();
//    }

    /**
     * 设置toolbar的右边
     *
     * @param resId    资源ID
     * @param listener ToolbarView.OnToolbarRightClickListener
     */
//    protected final void setToolbarRight(@DrawableRes int resId,
//                                         ToolbarView.OnToolbarRightClickListener listener) {
//        initToolbar();
//        mToolbarView.setRightRes(resId);
//        mToolbarView.setOnToolbarRightClickListener(listener);
//        mToolbarView.setVisibility(View.VISIBLE);
//    }


    /**
     * 设置隐藏toolbar
     */
    protected final void setGoneToolbar() {
        initToolbar();
        mToolbarView.setVisibility(View.GONE);
    }

//    protected final void setToolbarRightGone() {
//        initToolbar();
//        mToolbarView.setToolbarRightGone();
//    }

    /**
     * @author Kevin Liu
     * 显示Fragment
     */
    protected final void showFragment(Fragment fragment) {

        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        transaction.replace(R.id.job_fragment_container, fragment);

        if (!mFragmentManager.isDestroyed() && !fragment.isAdded()) {
            transaction.commitAllowingStateLoss();
        }
    }

    /**
     * fragment 切换
     *
     * @param from 当前是哪个fragment
     * @param to   要跳转到哪个fragment
     */
    protected void switchContent(Fragment from, Fragment to) {
        switchContent(from, to, null);
    }

    /**
     * fragment 切换
     *
     * @param from   当前是哪个fragment
     * @param to     要跳转到哪个fragment
     * @param bundle 携带的数据
     */
    protected void switchContent(Fragment from, Fragment to, Bundle bundle) {

        if (to != null) {
            SessionManager.getInstance().setCurrentPageName(to.getClass().getSimpleName());
        }

        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        // 如果当前的fragment为null,则直接显示to fragment
        if (from == null) {
            showFragment(to);
            return;
        }

        // 如果from已经添加 则隐藏
        if (from.isAdded()) {
            transaction.hide(from);
        }
        // 如果to 已经添加 则显示
        if (to.isAdded()) {
            transaction.show(to);
        } else {
            // 如果TO未添加则添加
            to.setArguments(bundle);
            transaction.add(R.id.job_fragment_container, to);
        }
        if (!mFragmentManager.isDestroyed()) {
            transaction.commitAllowingStateLoss();
        }
    }

    /**
     * 注册广播
     */
    protected String[] createLocalBroadcastReceiverActions() {
        return null;
    }


    /**
     * 创建页面广播接收器（子类有需要进行继承）
     *
     * @return BroadcastReceiver
     */
    protected BroadcastReceiver createBroadcastReceiver() {
        return null;
    }

    //====================
    // 私有的方法
    //====================

    @SuppressWarnings("InlinedApi")
    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (BuildConfig.DEBUG) {
            // Utils.enableStrictMode(this.getClass());
        }
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayoutViewId());
        mFragmentManager = getSupportFragmentManager();
        initData(getIntent());
        //
        //NavManager.getInstance().handleIntent(getIntent());
        initPresenter();

        mWindow = getWindow();
        if (OSUtils.hasLollipop()) {
            mWindow.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }

        if (OSUtils.hasKITKAT()) {
            mWindow.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        initView();
        // 用于延迟加载
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                onDelayLoad();
            }
        });
        LogUtils.d(mTag, "onCreate");
    }

    @CallSuper
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //NavManager.getInstance().handleIntent(intent);
    }

    @CallSuper
    @Override
    protected void onStart() {
        super.onStart();
        registerLocalReceiver();
    }

    @CallSuper
    @Override
    protected void onResume() {
        super.onResume();
    }

    @CallSuper
    @Override
    protected void onPause() {
        super.onPause();
    }

    @CallSuper
    @Override
    protected void onStop() {
        super.onStop();
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterLocalReceiver();
        if (mPresenter != null) {
            mPresenter.end();
            mPresenter.detachView();
        }

    }

    /**
     * for fix  java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
     * 这个没效果
     */
    /**
     * ava.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
     * at android.support.v4.app.FragmentManagerImpl.checkStateLoss(FragmentManager.java:1489)
     * at android.support.v4.app.FragmentManagerImpl.enqueueAction(FragmentManager.java:1507)
     * at android.support.v4.app.BackStackRecord.commitInternal(BackStackRecord.java:634)
     * at android.support.v4.app.BackStackRecord.commit(BackStackRecord.java:613)
     * at android.support.v4.app.FragmentTabHost.onAttachedToWindow(FragmentTabHost.java:282)
     * at android.view.View.dispatchAttachedToWindow(View.java:13525)
     * at android.view.ViewGroup.dispatchAttachedToWindow(ViewGroup.java:2688)
     * at android.view.ViewGroup.dispatchAttachedToWindow(ViewGroup.java:2695)
     * at android.view.ViewGroup.dispatchAttachedToWindow(ViewGroup.java:2695)
     * at android.view.ViewGroup.dispatchAttachedToWindow(ViewGroup.java:2695)
     * at android.view.ViewGroup.dispatchAttachedToWindow(ViewGroup.java:2695)
     * at android.view.ViewGroup.dispatchAttachedToWindow(ViewGroup.java:2695)
     * at android.view.ViewGroup.dispatchAttachedToWindow(ViewGroup.java:2695)
     * at android.view.ViewRootImpl.performTraversals(ViewRootImpl.java:1299)
     * at android.view.ViewRootImpl.doTraversal(ViewRootImpl.java:1061)
     * at android.view.ViewRootImpl$TraversalRunnable.run(ViewRootImpl.java:5885)
     * at android.view.Choreographer$CallbackRecord.run(Choreographer.java:767)
     * at android.view.Choreographer.doCallbacks(Choreographer.java:580)
     * at android.view.Choreographer.doFrame(Choreographer.java:550)
     * at android.view.Choreographer$FrameDisplayEventReceiver.run(Choreographer.java:753)
     * at android.os.Handler.handleCallback(Handler.java:739)
     * at android.os.Handler.dispatchMessage(Handler.java:95)
     * at android.os.Looper.loop(Looper.java:135)
     * at android.app.ActivityThread.main(ActivityThread.java:5254)
     * at java.lang.reflect.Method.invoke(Native Method)
     * at java.lang.reflect.Method.invoke(Method.java:372)
     * at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:903)
     * at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:698)
     */
    @CallSuper
    @Override
    protected void onSaveInstanceState(Bundle outState) {

    }

    /**
     * 注册广播，在onStart and onRestart时调用
     */
    private void registerLocalReceiver() {

        // 注册弹出订单的Fragment的broadcast

        if (mLocalBroadcastReceiver == null) {

            mLocalBroadcastReceiver = createBroadcastReceiver();

            if (mLocalBroadcastReceiver == null) {
                return;
            }
        }

        IntentFilter filter = new IntentFilter();

        String[] filterActionArr = createLocalBroadcastReceiverActions();

        if (filterActionArr != null && filterActionArr.length > 0) {
            for (String filterAction : filterActionArr) {
                filter.addAction(filterAction);
            }
        }

        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(mLocalBroadcastReceiver, filter);
    }

    /**
     * 反组册广播，在OnStop时调用
     */
    private void unregisterLocalReceiver() {
        if (mLocalBroadcastReceiver != null) {
            LocalBroadcastManager.getInstance(getApplicationContext())
                    .unregisterReceiver(mLocalBroadcastReceiver);
        }
    }

    private void initToolbar() {
        if (mToolbarView == null) {
            mToolbarView = (LrchaoToolBar) findViewById(R.id.job_toolbar_lrchao);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<Fragment> fragmentList = mFragmentManager.getFragments();
        if (fragmentList != null && fragmentList.size() > 0) {
            for (Fragment fragment : fragmentList) {
                if (fragment != null) {
                    fragment.onActivityResult(requestCode, resultCode, data);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        List<Fragment> fragmentList = mFragmentManager.getFragments();
        if (fragmentList != null && fragmentList.size() > 0) {
            for (Fragment fragment : fragmentList) {
                if (fragment != null) {
                    fragment.onCreateOptionsMenu(menu, getMenuInflater());
                }
            }
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<Fragment> fragmentList = mFragmentManager.getFragments();
        if (fragmentList != null && fragmentList.size() > 0) {
            for (Fragment fragment : fragmentList) {
                if (fragment != null) {
                    fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
                }
            }
        }
    }

    /**
     * 初始化Presenter
     */
    private void initPresenter() {
        // 设置Presenter
        mPresenter = getPresenter();
        if (mPresenter != null) {
            if (this instanceof MvpView) {
                mPresenter.attachView((MvpView) this);
                mPresenter.start();
            } else {
                throw new InitializationNotCompleteException(getClass().getSimpleName() +
                        " must implements XxxContract.View");
            }
        }
    }


}
