package com.lrchao.fakejob.ui.activity.login;

import android.content.Context;
import android.content.Intent;

import com.lrchao.fakejob.R;
import com.lrchao.fakejob.ui.activity.common.BaseFragment;
import com.lrchao.fakejob.ui.activity.common.CommonActivity;


/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/23 下午4:46
 */

public class LoginActivity extends CommonActivity {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected BaseFragment getShowFragment() {
        return LoginFragment.getInstance();
    }

    @Override
    protected void initView() {
        setToolbarTitle(R.string.job_toolbar_login);
        setToolbarTitleColor(R.color.white);

        setToolbarBackView();
    }
}
