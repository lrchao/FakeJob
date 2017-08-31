package com.lrchao.fakejob.ui.activity.favorites;

import android.content.Context;
import android.content.Intent;

import com.lrchao.fakejob.R;
import com.lrchao.fakejob.ui.activity.common.BaseFragment;
import com.lrchao.fakejob.ui.activity.common.CommonActivity;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/28 上午10:27
 */

public class MyFavoritesListActivity extends CommonActivity {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, MyFavoritesListActivity.class);
    }

    @Override
    protected BaseFragment getShowFragment() {
        return MyFavoritesListFragment.getInstance();
    }

    @Override
    protected void initView() {
        setToolbarTitle("我的收藏");
        setToolbarTitleColor(R.color.white);
        setToolbarBackView();
    }
}
