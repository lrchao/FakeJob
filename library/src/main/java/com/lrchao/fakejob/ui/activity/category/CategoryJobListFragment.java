package com.lrchao.fakejob.ui.activity.category;

import android.content.Intent;
import android.view.View;

import com.lrchao.fakejob.R;
import com.lrchao.fakejob.constant.BundleKey;
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
 * @date 2017/6/22 上午10:49
 */

public class CategoryJobListFragment extends PageNetworkFragment implements
        CategoryJobListContract.View {

    ItemGroupLayout mGroupLayout;

    CategoryJobListPresenter mPresenter;

    private int mCategoryId;

    @Override
    protected void initData(Intent intent) {
        super.initData(intent);
        mCategoryId = intent.getIntExtra(BundleKey.INTENT_EXTRA_CATEGORY_ID, 0);
    }

    public static CategoryJobListFragment getInstance() {
        return new CategoryJobListFragment();
    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.fragment_category_list;
    }

    @Override
    protected void initView(View parentView) {
        mGroupLayout = parentView.findViewById(R.id.layout_group);
    }

    @Override
    protected BasePresenter createPresenter() {
        mPresenter = new CategoryJobListPresenter();
        mPresenter.setInitDate(mCategoryId);
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
