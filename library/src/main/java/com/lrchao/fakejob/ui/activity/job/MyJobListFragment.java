package com.lrchao.fakejob.ui.activity.job;

import android.view.View;

import com.lrchao.fakejob.R;
import com.lrchao.fakejob.model.json.home.HomeJobModel;
import com.lrchao.fakejob.mvp.BasePresenter;
import com.lrchao.fakejob.ui.activity.common.BaseFragment;
import com.lrchao.fakejob.ui.activity.common.PageNetworkFragment;
import com.lrchao.fakejob.ui.views.JobItemView;
import com.lrchao.views.itemview.ItemGroupLayout;

import java.util.List;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/28 上午10:16
 */

public class MyJobListFragment extends PageNetworkFragment implements MyJobListContract.View {

    ItemGroupLayout mGroupLayout;

    MyJobListPresenter mPresenter;

    public static BaseFragment getInstance() {
        return new MyJobListFragment();
    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.fragment_my_job_list;
    }

    @Override
    protected void initView(View parentView) {
        mGroupLayout = parentView.findViewById(R.id.layout_group);
    }

    @Override
    protected BasePresenter createPresenter() {
        mPresenter = new MyJobListPresenter();
        return mPresenter;
    }

    @Override
    public void showPageView(Object obj) {

    }

    @Override
    public void bindView(List<HomeJobModel> list) {
        mGroupLayout.clear();

        for (HomeJobModel model : list) {
            JobItemView itemView = new JobItemView(getContext());
            itemView.bindView(model);
            mGroupLayout.addChildView(itemView);
        }
        mGroupLayout.setBottomLineHeight(2);
        mGroupLayout.build();
    }
}
