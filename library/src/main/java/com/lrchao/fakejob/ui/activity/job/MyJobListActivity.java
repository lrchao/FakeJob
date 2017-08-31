package com.lrchao.fakejob.ui.activity.job;

import android.content.Context;
import android.content.Intent;

import com.lrchao.fakejob.R;
import com.lrchao.fakejob.ui.activity.common.BaseFragment;
import com.lrchao.fakejob.ui.activity.common.CommonActivity;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/28 上午10:13
 */

public class MyJobListActivity extends CommonActivity {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, MyJobListActivity.class);
    }

    @Override
    protected BaseFragment getShowFragment() {
        return MyJobListFragment.getInstance();
    }

    @Override
    protected void initView() {
        setToolbarTitle(R.string.toolbar_my_job);
        setToolbarTitleColor(R.color.white);
        setToolbarBackView();
    }
}
