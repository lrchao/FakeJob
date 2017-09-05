package com.lrchao.fakejob.ui.activity.favorites;

import android.view.View;

import com.lrchao.fakejob.R;
import com.lrchao.fakejob.model.json.home.HomeJobModel;
import com.lrchao.fakejob.mvp.BasePresenter;
import com.lrchao.fakejob.ui.activity.common.PageNetworkFragment;
import com.lrchao.fakejob.ui.views.JobItemView;
import com.lrchao.views.itemview.ItemGroupLayout;

import java.util.List;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/28 上午10:29
 */

public class MyFavoritesListFragment extends PageNetworkFragment implements
        MyFavoritesListContract.View {

    ItemGroupLayout mGroupLayout;

    private MyFavoritesListPresenter mPresenter;

    public static MyFavoritesListFragment getInstance() {
        return new MyFavoritesListFragment();
    }

    @Override
    public void showPageView(Object obj) {

    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.job_fragment_my_fav_list;
    }

    @Override
    protected void initView(View parentView) {
        mGroupLayout = parentView.findViewById(R.id.layout_group);
    }

    @Override
    protected BasePresenter createPresenter() {
        mPresenter = new MyFavoritesListPresenter();
        return mPresenter;
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
