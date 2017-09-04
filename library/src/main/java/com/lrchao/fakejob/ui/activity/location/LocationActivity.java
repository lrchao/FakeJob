package com.lrchao.fakejob.ui.activity.location;

import android.content.Context;
import android.content.Intent;

import com.lrchao.fakejob.ui.activity.common.BaseFragment;
import com.lrchao.fakejob.ui.activity.common.CommonActivity;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/9/3 下午3:48
 */

public class LocationActivity extends CommonActivity{

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, LocationActivity.class);
    }

    @Override
    protected BaseFragment getShowFragment() {
        return LocationFragment.getInstance();
    }

    @Override
    protected void initView() {
        setGoneToolbar();
    }
}
