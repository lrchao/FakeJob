package com.lrchao.fakejob.ui.dialog;

import android.support.annotation.CallSuper;
import android.view.View;

import com.lrchao.views.dialog.BaseDialogFragment;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2016/12/5 下午4:36
 */

public abstract class MyBaseDialogFragment extends BaseDialogFragment {

    @Override
    protected void initViewInjection(View view) {
    }

    @CallSuper
    @Override
    public void onResume() {
        super.onResume();
    }

    @CallSuper
    @Override
    public void onPause() {
        super.onPause();
    }

    @CallSuper
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
