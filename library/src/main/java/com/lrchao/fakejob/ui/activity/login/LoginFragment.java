package com.lrchao.fakejob.ui.activity.login;

import android.view.View;
import android.widget.EditText;

import com.lrchao.fakejob.R;
import com.lrchao.fakejob.mvp.BasePresenter;
import com.lrchao.fakejob.ui.activity.common.BaseFragment;
import com.lrchao.fakejob.ui.activity.common.NetworkFragment;
import com.lrchao.fakejob.utils.NavUtils;
import com.lrchao.utils.ToastUtils;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/23 下午4:49
 */

public class LoginFragment extends NetworkFragment implements
        LoginContract.View {

    LoginPresenter mPresenter;

    private EditText mEtUserName;

    private EditText mEtPassword;

    public static BaseFragment getInstance() {
        return new LoginFragment();
    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView(View parentView) {
        mEtUserName = parentView.findViewById(R.id.et_user_name);
        mEtPassword = parentView.findViewById(R.id.et_password);
        parentView.findViewById(R.id.tv_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.login(mEtUserName.getText(), mEtPassword.getText());
            }
        });

        parentView.findViewById(R.id.tv_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavUtils.get().navToRegister(getActivity());
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        mPresenter = new LoginPresenter();
        return mPresenter;
    }

    @Override
    public void showToast(int toast) {
        ToastUtils.show(toast);
    }

    @Override
    public void finishPage() {
        finishActivity();
    }
}
