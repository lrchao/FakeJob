package com.lrchao.fakejob.ui.activity.common;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lrchao.fakejob.JobApp;
import com.lrchao.fakejob.R;
import com.lrchao.fakejob.exception.InitializationNotCompleteException;
import com.lrchao.fakejob.manager.session.SessionManager;
import com.lrchao.fakejob.mvp.BasePresenter;
import com.lrchao.fakejob.mvp.MvpView;
import com.lrchao.fakejob.ui.dialog.LoadingDialogFragment;
import com.lrchao.utils.LogUtils;
import com.lrchao.views.dialog.BaseDialogFragment;

import java.lang.ref.WeakReference;


/**
 * Description: Fragment的基类
 *
 * @author liuranchao
 * @date 16/3/13 上午11:01
 */
public abstract class BaseFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<Object> {

    /**
     * 对应的Fragment Name
     */
    protected final String mTag = this.getClass().getSimpleName();

    /**
     * Fragment的根view
     */
    protected View mMainView;

    /**
     * Handler
     */
    protected MainHandler mMainHandler;
    /**
     * LoaderManager
     */
    protected LoaderManager mLoaderManager;
    /**
     * Presenter
     */
    protected BasePresenter mBasePresenter;
    /**
     * 加载中的Dialog
     */
    private BaseDialogFragment mLoadingDialog;

    /**
     * 广播接受，负责接受LocalBroadcastReceiver
     */
    private BroadcastReceiver mLocalBroadcastReceiver;

    /**
     * FragmentManager
     */
    private FragmentManager mFragmentManager;

    //====================================
    // 外部可调用
    //====================================

    public final BasePresenter getPresenter() {
        return mBasePresenter;
    }

    //====================================
    // 子类可调用的
    //====================================

    /**
     * fragment 切换
     *
     * @param from   当前是哪个fragment
     * @param to     要跳转到哪个fragment
     * @param bundle 携带的数据
     */
    protected void switchContent(Fragment from, Fragment to, Bundle bundle) {

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
            transaction.add(R.id.fragment_container, to);
        }
        if (!mFragmentManager.isDestroyed()) {
            transaction.commitAllowingStateLoss();
        }
    }

    /**
     * @author Kevin Liu
     * 显示Fragment
     */
    protected final void showFragment(Fragment fragment) {

        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        transaction.replace(R.id.fragment_container, fragment);

        if (!mFragmentManager.isDestroyed() && !fragment.isAdded()) {
            transaction.commitAllowingStateLoss();
        }
    }

    protected final void showFragmentAddStack(Fragment fragment) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.fragment_container, fragment);

        if (!mFragmentManager.isDestroyed() && !fragment.isAdded()) {
            transaction.commitAllowingStateLoss();
        }
    }

    protected final void popStack() {
        mFragmentManager.popBackStack();
    }

    /**
     * 创建
     *
     * @param message 加载的显示文本
     */
    protected final void showLoadingDialog(String message, boolean cancelable) {
        mLoadingDialog = LoadingDialogFragment.newInstance(message, cancelable);
        showDialog(mLoadingDialog);
        mMainHandler.sendEmptyMessage(1);
    }

    /**
     * Handler的回调方法
     *
     * @param msg Message
     */
    protected void handleMainMessage(Message msg) {
    }


    /**
     * 显示Dialog
     *
     * @param dialogFragment BaseDialogFragment
     */
    public final void showDialog(BaseDialogFragment dialogFragment) {

        try {
            String tag = dialogFragment.getClass().getSimpleName();

            FragmentTransaction ft = mFragmentManager.beginTransaction();
            Fragment prev = mFragmentManager.findFragmentByTag(tag);
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);

            // 检查 是否isAdded()
            if (prev == null || (
                    !prev.isAdded() &&
                            !prev.isVisible() &&
                            !prev.isRemoving())) {
                dialogFragment.show(ft, tag);
            }

        } catch (IllegalStateException e) {
            LogUtils.wtf(e);
        }
    }

    /**
     * 消失LoadingDialog
     */
    protected final void dismissLoadingDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismissDialog();
        }
    }


    /**
     * 关闭Activity
     */
    protected final void finishActivity() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    /**
     * 调用Activity的setResult()
     */
    protected final void setResultOK() {
        setResultOK(null);
    }

    /**
     * *
     * 调用Activity的setResult()
     *
     * @param intent 回传数据的Intent
     */
    protected final void setResultOK(Intent intent) {
        if (getActivity() != null) {
            getActivity().setResult(Activity.RESULT_OK, intent);
        }
    }


    protected FragmentManager getCurrentFragmentManager() {
        return mFragmentManager;
    }

    //====================================
    // 子类继承
    //====================================

    /**
     * 设置初始化的layout view
     *
     * @return Layout View
     */
    protected abstract int getLayoutViewId();

    /**
     * 初始化Fragment的view的方法
     *
     * @param parentView 根view
     */
    protected abstract void initView(View parentView);

    /**
     * 在initView(View parentView)后执行
     *
     * @param savedInstanceState Bundle
     */
    protected void onCreateView(Bundle savedInstanceState) {

    }

    /**
     * 扩展的fragment,嵌套fragment使用
     *
     * @param inflater     LayoutInflater
     * @param container    ViewGroup
     * @param attachToRoot boolean
     * @return 实际的view
     */
    protected View getLayoutView(LayoutInflater inflater, ViewGroup container, boolean attachToRoot) {
        return null;
    }

    /**
     * 初始化数据方法，如果Intent为null时，子类收不到调用
     *
     * @param intent Activity传过来的Intent
     */
    protected void initData(Intent intent) {
    }

    /**
     * Fragment获取getArguments()的参数
     *
     * @param bundle Bundle
     */
    protected void initArgumentsData(Bundle bundle) {
    }

    /**
     * 子类负责附加view
     *
     * @param viewGroup main view
     */
    protected void attachView(ViewGroup viewGroup) {
    }

    /**
     * 子类实现，负责初始化Presenter
     */
    protected abstract BasePresenter createPresenter();

    /**
     * super.initArgumentsData(bundle);
     * 延迟加载，等UI初始化好后调用
     */
    protected void onDelayLoad() {

    }

    /**
     * startActivityForResult回调
     * 只有RESULT_OK的情况下会调用
     *
     * @param requestCode Intent request code
     * @param data        Intent
     */
    protected void onMyActivityResult(int requestCode, int resultCode, Intent data) {

    }

    /**
     * 初始化Loader的数据
     */
    protected SparseArray<Bundle> initLoaderData() {
        return null;
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

    /**
     * 是否开启友盟页面统计,Tab的fragment，有可能不调用onResume onPause，导致不会调用此方法
     * 需要另外处理
     */
    protected boolean isOpenUmengPageStatistics() {
        return false;
    }

    /**
     * 子类设置图片picker
     */
//    protected ImageFilePicker getImageFilePicker() {
//        return null;
//    }

    //====================================
    // 内部方法
    //====================================
    private void initButterKnife(View mainView) {
    }

    @CallSuper
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //LogUtils.d("onCreate");
        mFragmentManager = getChildFragmentManager();
        initLoader();
        // 设置初始化参数的方法
        if (getArguments() != null || getActivity().getIntent().getExtras() != null) {
            Bundle bundle = new Bundle();
            if (getArguments() != null) {
                bundle.putAll(getArguments());
            }

            if (getActivity().getIntent().getExtras() != null) {
                bundle.putAll(getActivity().getIntent().getExtras());
            }
            initArgumentsData(bundle);
        }

        initData(getActivity().getIntent());

        initPresenter();

        //getImageFilePicker();

        // 用于延迟加载
        getActivity().getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                onDelayLoad();
            }
        });

        mMainHandler = new MainHandler(new WeakReference<>(this));
    }

    @CallSuper
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mMainView == null) {
            if (getLayoutView(inflater, container, false) != null) {
                mMainView = getLayoutView(inflater, container, false);
            } else {
                mMainView = inflater.inflate(getLayoutViewId(), container, false);
            }

            initButterKnife(mMainView);

            initView(mMainView);

            onCreateView(savedInstanceState);
        } else {
            ViewGroup parent = (ViewGroup) mMainView.getParent();
            if (null != parent) {
                parent.removeView(mMainView);
            }
        }
        attachView((ViewGroup) mMainView);
        return mMainView;
    }

    @CallSuper
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @CallSuper
    @Override
    public void onStart() {
        super.onStart();
        registerLocalReceiver();
    }

    @CallSuper
    @Override
    public void onResume() {
        super.onResume();
        onResumeSetCurrentPageName();
    }

    protected void onResumeSetCurrentPageName() {
        SessionManager.getInstance().setCurrentPageName(mTag);
    }


    @CallSuper
    @Override
    public void onPause() {
        super.onPause();
        onPauseSetPrePageName();
    }

    protected void onPauseSetPrePageName() {
        SessionManager.getInstance().setPrePageName(mTag);
    }

    @CallSuper
    @Override
    public void onStop() {
        super.onStop();
        unregisterLocalReceiver();
        if (mMainHandler != null) {
            mMainHandler.onDestroy();
        }
    }

    /**
     * 不一定会执行，所以不能清理 除了view 以外的东西
     */
    @CallSuper
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBasePresenter != null) {
            mBasePresenter.end();
            mBasePresenter.detachView();
        }
    }

    @CallSuper
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @CallSuper
    @Override
    public final void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (getImageFilePicker() != null) {
//            getImageFilePicker().onActivityResult(requestCode, resultCode, data);
//        }
        if (resultCode == Activity.RESULT_OK) {
            onMyActivityResult(requestCode, resultCode, data);
        }
    }


    /**
     * 初始化Loader
     */
    private void initLoader() {
        mLoaderManager = getLoaderManager();

        SparseArray<Bundle> initData = initLoaderData();

        if (initData == null || initData.size() <= 0) {
            return;
        }

        for (int i = 0; i < initData.size(); i++) {
            int loaderId = initData.keyAt(i);
            Bundle b = initData.valueAt(i);
            mLoaderManager.initLoader(loaderId, b, this);
        }
    }

    @CallSuper
    @Override
    public Loader<Object> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @CallSuper
    @Override
    public void onLoadFinished(Loader<Object> loader, Object data) {

    }

    @CallSuper
    @Override
    public void onLoaderReset(Loader<Object> loader) {

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (getImageFilePicker() != null) {
//            getImageFilePicker().onRequestPermissionsResult(requestCode, permissions, grantResults);
//        }
    }

    /**
     * 初始化Presenter
     */
    private void initPresenter() {
        // 设置Presenter
        mBasePresenter = createPresenter();

        if (mBasePresenter != null) {
            if (this instanceof MvpView) {
                mBasePresenter.attachView((MvpView) this);
                mBasePresenter.start();
            } else {
                throw new InitializationNotCompleteException(mTag +
                        " must implements XxxContract.View");
            }
        }
    }

    /**
     * 注册广播，在onStart and onRestart时调用
     */
    private void registerLocalReceiver() {
        registerChildLocalReceiver();
    }

    /**
     * 子类的广播注册
     */
    private void registerChildLocalReceiver() {
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

        LocalBroadcastManager.getInstance(JobApp.getInstance().getContext())
                .registerReceiver(mLocalBroadcastReceiver, filter);
    }

    /**
     * 反组册广播，在OnStop时调用
     */
    private void unregisterLocalReceiver() {
        unregisterChildLocalReceiver();
    }

    /**
     * 子类实现的取消注册
     */
    private void unregisterChildLocalReceiver() {
        if (mLocalBroadcastReceiver != null) {
            LocalBroadcastManager.getInstance(getContext())
                    .unregisterReceiver(mLocalBroadcastReceiver);
        }
    }

    private boolean isCurrentPage() {
        return mTag.equals(SessionManager.getInstance().getCurrentPageName());
    }


    /**
     * 全局静态的handler
     */
    protected static final class MainHandler extends Handler {

        private final WeakReference<BaseFragment> mOuterContext;

        public MainHandler(WeakReference<BaseFragment> context) {
            this.mOuterContext = context;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            BaseFragment fragment = mOuterContext.get();
            if (fragment != null) {
                fragment.handleMainMessage(msg);
            }

        }

        protected void onDestroy() {
            if (mOuterContext != null) {
                BaseFragment fragment = mOuterContext.get();
                fragment = null;
                mOuterContext.clear();
            }
        }
    }

}
