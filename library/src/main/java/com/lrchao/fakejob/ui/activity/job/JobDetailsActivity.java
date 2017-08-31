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
 * @date 2017/6/22 下午2:16
 */

public class JobDetailsActivity extends CommonActivity {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, JobDetailsActivity.class);
    }

    @Override
    protected BaseFragment getShowFragment() {
        return JobDetailsFragment.getInstance();
    }

    @Override
    protected void initView() {
        setToolbarTitle(R.string.toolbar_job_details);
        setToolbarTitleColor(R.color.white);
        setToolbarBackView();
    }
}
