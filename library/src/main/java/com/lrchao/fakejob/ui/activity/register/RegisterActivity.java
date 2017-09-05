package com.lrchao.fakejob.ui.activity.register;

import android.content.Context;
import android.content.Intent;

import com.lrchao.fakejob.R;
import com.lrchao.fakejob.ui.activity.common.BaseFragment;
import com.lrchao.fakejob.ui.activity.common.CommonActivity;


/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/23 下午5:22
 */

public class RegisterActivity extends CommonActivity {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, RegisterActivity.class);
    }

    @Override
    protected BaseFragment getShowFragment() {
        return RegisterFragment.getInstance();
    }

    @Override
    protected void initView() {
        setToolbarTitle(R.string.job_toolbar_register);
        setToolbarTitleColor(R.color.white);
        setToolbarBackView();
    }
}
