package com.lrchao.fakejob.ui.activity.register;

import android.view.View;
import android.widget.EditText;

import com.lrchao.fakejob.R;
import com.lrchao.fakejob.mvp.BasePresenter;
import com.lrchao.fakejob.ui.activity.common.BaseFragment;
import com.lrchao.fakejob.ui.activity.common.NetworkFragment;
import com.lrchao.utils.ToastUtils;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/23 下午5:25
 */

public class RegisterFragment extends NetworkFragment implements
        RegisterContract.View {

    private RegisterPresenter mPresenter;

    EditText mEtPhoneNumber;

    EditText mEtPassword;

    EditText mEtConfirmPassword;


    public static BaseFragment getInstance() {
        return new RegisterFragment();
    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initView(View parentView) {
        mEtPhoneNumber = parentView.findViewById(R.id.et_phone_number);
        mEtPassword = parentView.findViewById(R.id.et_password);
        mEtConfirmPassword = parentView.findViewById(R.id.et_confirm_password);

        parentView.findViewById(R.id.tv_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.register(
                        mEtPhoneNumber.getText(),
                        mEtPassword.getText(),
                        mEtConfirmPassword.getText());
            }
        });

        parentView.findViewById(R.id.tv_forward_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishActivity();
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        mPresenter = new RegisterPresenter();
        return mPresenter;
    }

    @Override
    public void showToast(CharSequence s) {
        ToastUtils.show(s);
    }

    @Override
    public void finishPage() {
        finishActivity();
    }
}
