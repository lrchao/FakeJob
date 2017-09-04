package com.lrchao.fakejob.ui.activity.search;

import android.content.Context;
import android.content.Intent;

import com.lrchao.fakejob.ui.activity.common.BaseFragment;
import com.lrchao.fakejob.ui.activity.common.CommonActivity;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/9/4 上午11:17
 */

public class SearchActivity extends CommonActivity {
    public static Intent getCallingIntent(Context context) {
        return new Intent(context, SearchActivity.class);
    }

    @Override
    protected BaseFragment getShowFragment() {
        return SearchFragment.getInstance();
    }

    @Override
    protected void initView() {
        setGoneToolbar();
    }
}
