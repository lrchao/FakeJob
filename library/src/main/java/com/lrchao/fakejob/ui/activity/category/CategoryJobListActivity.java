package com.lrchao.fakejob.ui.activity.category;

import android.content.Context;
import android.content.Intent;

import com.lrchao.fakejob.R;
import com.lrchao.fakejob.constant.BundleKey;
import com.lrchao.fakejob.ui.activity.common.BaseFragment;
import com.lrchao.fakejob.ui.activity.common.CommonActivity;


/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/22 上午10:45
 */

public class CategoryJobListActivity extends CommonActivity {

    private String mToolbarTitle;

    @Override
    protected void initData(Intent intent) {
        super.initData(intent);
        mToolbarTitle = intent.getStringExtra(BundleKey.INTENT_EXTRA_TOOLBAR_TITLE);
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, CategoryJobListActivity.class);
    }

    @Override
    protected BaseFragment getShowFragment() {
        return CategoryJobListFragment.getInstance();
    }

    @Override
    protected void initView() {

        setToolbarTitle(mToolbarTitle);
        setToolbarTitleColor(R.color.white);
        setToolbarBackView();
    }
}
