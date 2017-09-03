package com.lrchao.fakejob.ui.activity.common;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lrchao.fakejob.R;
import com.lrchao.fakejob.exception.InitializationNotCompleteException;


/**
 * Description: 一般的fragment的activity
 * 1.默认的使用activity_common layout
 * 2.使用Presenter
 *
 * @author liuranchao
 * @date 16/4/19 下午3:20
 */
public abstract class CommonActivity extends BaseActivity {

    @Override
    protected final int getLayoutViewId() {
        return R.layout.job_activity_common;
    }

    /**
     * 设置Fragment
     */
    protected abstract BaseFragment getShowFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseFragment baseFragment = getShowFragment();
        if (baseFragment == null) {
            throw new InitializationNotCompleteException(getClass().getSimpleName() +
                    "  getShowFragment must be not null");
        }
        showFragment(baseFragment);
    }
}
