package com.lrchao.fakejob.ui.activity.mine;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lrchao.fakejob.R;
import com.lrchao.fakejob.manager.session.SessionManager;
import com.lrchao.fakejob.manager.user.UserInfoManager;
import com.lrchao.fakejob.mvp.BasePresenter;
import com.lrchao.fakejob.ui.activity.common.NetworkFragment;
import com.lrchao.fakejob.utils.MyImageUtils;
import com.lrchao.fakejob.utils.NavUtils;
import com.lrchao.utils.ToastUtils;
import com.lrchao.views.imageview.MyCircleImageView;
import com.lrchao.views.itemview.IconTextItemLayout;
import com.lrchao.views.itemview.ItemGroupLayout;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/23 下午3:48
 */

public class MineFragment extends NetworkFragment implements
        MineContract.View, View.OnClickListener {

    private MinePresenter mPresenter;

    MyCircleImageView mMyCircleImageView;

    TextView mTvUserName;

    ViewGroup mLayoutNotLogin;

    ViewGroup mLayoutAlreadyLogin;

    ItemGroupLayout mLayoutGroup;

    TextView mTvLogout;

    public static MineFragment getInstance() {
        return new MineFragment();
    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View parentView) {

        parentView.findViewById(R.id.ll_yibaoming).setOnClickListener(this);
        parentView.findViewById(R.id.ll_jinxingzhong).setOnClickListener(this);
        parentView.findViewById(R.id.ll_yiwancheng).setOnClickListener(this);
        parentView.findViewById(R.id.ll_quanbu).setOnClickListener(this);

        mMyCircleImageView = parentView.findViewById(R.id.iv_user_icon);
        mTvUserName = parentView.findViewById(R.id.tv_user_name);
        mLayoutNotLogin = parentView.findViewById(R.id.layout_not_login);
        mLayoutNotLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavUtils.get().navToLogin(getActivity());
            }
        });
        mLayoutAlreadyLogin = parentView.findViewById(R.id.layout_already_login);
        mLayoutGroup = parentView.findViewById(R.id.layout_group);
        mTvLogout = parentView.findViewById(R.id.tv_logout);
        mTvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.logout();
            }
        });


        bindUserInfoView(UserInfoManager.getInstance().getUserName(),
                "null");
        bindHeaderView(SessionManager.getInstance().isLogin());

        IconTextItemLayout itemMyJob = new IconTextItemLayout(getContext());
        itemMyJob.mTvTitle.setText("我的兼职");
        itemMyJob.mIvIcon.setBackgroundResource(R.drawable.ic_my_job);
        itemMyJob.setOnIconTextItemLayoutClickListener(new IconTextItemLayout.OnIconTextItemLayoutClickListener() {
            @Override
            public void onIconTextItemLayoutClick() {

                if (SessionManager.getInstance().isLogin()) {
                    NavUtils.get().navToMyJobListActivity(getActivity());
                } else {
                    ToastUtils.show("请先登陆");
                }

            }
        });

        IconTextItemLayout itemFav = new IconTextItemLayout(getContext());
        itemFav.mTvTitle.setText("我的收藏");
        itemFav.mIvIcon.setBackgroundResource(R.drawable.ic_my_fav);
        itemFav.setOnIconTextItemLayoutClickListener(new IconTextItemLayout.OnIconTextItemLayoutClickListener() {
            @Override
            public void onIconTextItemLayoutClick() {
                if (SessionManager.getInstance().isLogin()) {
                    NavUtils.get().navToMyFavoritesListActivity(getActivity());
                } else {
                    ToastUtils.show("请先登陆");
                }
            }
        });

        IconTextItemLayout itemService = new IconTextItemLayout(getContext());
        itemService.mTvTitle.setText("商家合作");
        itemService.mIvIcon.setBackgroundResource(R.drawable.ic_service);
        itemService.setOnIconTextItemLayoutClickListener(new IconTextItemLayout.OnIconTextItemLayoutClickListener() {
            @Override
            public void onIconTextItemLayoutClick() {

                ToastUtils.show("敬请期待～coming soon!");
//                try {
//                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:400800101"));
//                    startActivity(intent);
//                } catch (Exception e) {
//                    ToastUtils.show("无法呼叫电话");
//                }

            }
        });


        mLayoutGroup.addChildView(itemMyJob);
        mLayoutGroup.addChildView(itemFav);
        mLayoutGroup.addChildView(itemService);

        mLayoutGroup.setBottomLineHeight(2);
        mLayoutGroup.build();
    }

    @Override
    protected BasePresenter createPresenter() {
        mPresenter = new MinePresenter();
        return mPresenter;
    }

    @Override
    public void bindHeaderView(boolean login) {
        if (login) {
            mLayoutNotLogin.setVisibility(View.GONE);
            mLayoutAlreadyLogin.setVisibility(View.VISIBLE);
            mTvLogout.setVisibility(View.VISIBLE);
        } else {
            mLayoutNotLogin.setVisibility(View.VISIBLE);
            mLayoutAlreadyLogin.setVisibility(View.GONE);
            mTvLogout.setVisibility(View.GONE);
        }
    }

    @Override
    public void bindUserInfoView(String userName, String userIcon) {
        mTvUserName.setText(userName);
        MyImageUtils.displayUserIcon(userIcon, mMyCircleImageView);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ll_yibaoming ||
                view.getId() == R.id.ll_jinxingzhong ||
                view.getId() == R.id.ll_yiwancheng ||
                view.getId() == R.id.ll_quanbu) {

            if (SessionManager.getInstance().isLogin()) {
                NavUtils.get().navToCategory(getContext(), "", 1);
            } else {
                NavUtils.get().navToLogin(getContext());
            }

        }
    }
}
