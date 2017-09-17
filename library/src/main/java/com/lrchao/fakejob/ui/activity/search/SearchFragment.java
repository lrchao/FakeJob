package com.lrchao.fakejob.ui.activity.search;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lrchao.fakejob.R;
import com.lrchao.fakejob.mvp.BasePresenter;
import com.lrchao.fakejob.ui.activity.common.BaseFragment;
import com.lrchao.fakejob.ui.activity.common.NetworkFragment;
import com.lrchao.fakejob.ui.views.location.EditTextWithDel;
import com.lrchao.fakejob.utils.NavUtils;
import com.lrchao.utils.SoftKeyboardUtils;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/9/4 上午11:18
 */

public class SearchFragment extends NetworkFragment implements SearchContract.View, View.OnClickListener {

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

                    if (!TextUtils.isEmpty(mEditTextWithDel.getText())) {
                        NavUtils.get().navToCategory(getContext(), mEditTextWithDel.getText().toString(), 1);
                    }

                    return true;
                }
                return false;
            }
        });

        parentView.findViewById(R.id.tv_app).setOnClickListener(this);
        parentView.findViewById(R.id.tv_online).setOnClickListener(this);
        parentView.findViewById(R.id.tv_offline).setOnClickListener(this);
        parentView.findViewById(R.id.tv_kfc).setOnClickListener(this);
        parentView.findViewById(R.id.tv_mdl).setOnClickListener(this);
    }

    @Override
    protected BasePresenter createPresenter() {
        mPresenter = new SearchPresenter();
        return mPresenter;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_app) {

            NavUtils.get().navToCategory(getContext(), "APP", 2);

        } else if (view.getId() == R.id.tv_online) {

            NavUtils.get().navToCategory(getContext(), "线上兼职", 3);

        } else if (view.getId() == R.id.tv_offline) {

            NavUtils.get().navToCategory(getContext(), "线下兼职", 1);

        } else if (view.getId() == R.id.tv_kfc) {

            NavUtils.get().navToCategory(getContext(), "肯德基", 2);

        } else if (view.getId() == R.id.tv_mdl) {

            NavUtils.get().navToCategory(getContext(), "麦当劳", 3);

        }
    }
}
