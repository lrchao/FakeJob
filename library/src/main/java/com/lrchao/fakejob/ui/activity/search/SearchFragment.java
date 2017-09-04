package com.lrchao.fakejob.ui.activity.search;

import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lrchao.fakejob.R;
import com.lrchao.fakejob.mvp.BasePresenter;
import com.lrchao.fakejob.ui.activity.common.BaseFragment;
import com.lrchao.fakejob.ui.activity.common.NetworkFragment;
import com.lrchao.fakejob.ui.views.location.EditTextWithDel;
import com.lrchao.utils.SoftKeyboardUtils;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/9/4 上午11:18
 */

public class SearchFragment extends NetworkFragment implements SearchContract.View {

    private EditTextWithDel mEditTextWithDel;

    private ViewGroup mLayoutRecommend;

    private SearchPresenter mPresenter;

    public static BaseFragment getInstance() {
        return new SearchFragment();
    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.job_fragment_search;
    }

    @Override
    protected void initView(View parentView) {

        parentView.findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishActivity();
            }
        });

        mLayoutRecommend = parentView.findViewById(R.id.ll_recommend);

        mEditTextWithDel = parentView.findViewById(R.id.et_search);

        mEditTextWithDel.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if ((keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) ||
                        (actionId == KeyEvent.KEYCODE_ENDCALL)) {
                    SoftKeyboardUtils.hideKeyboard(getContext(), mEditTextWithDel);
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    protected BasePresenter createPresenter() {
        mPresenter = new SearchPresenter();
        return mPresenter;
    }
}
