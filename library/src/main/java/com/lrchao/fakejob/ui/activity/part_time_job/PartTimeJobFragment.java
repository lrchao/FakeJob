package com.lrchao.fakejob.ui.activity.part_time_job;

import android.view.View;

import com.lrchao.fakejob.R;
import com.lrchao.fakejob.model.json.job.PartTimeJobModel;
import com.lrchao.fakejob.mvp.BasePresenter;
import com.lrchao.fakejob.ui.activity.common.PageNetworkFragment;
import com.lrchao.fakejob.ui.views.PartTimeJobItemView;
import com.lrchao.views.itemview.ItemGroupLayout;

import java.util.List;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/23 上午10:33
 */

public class PartTimeJobFragment extends PageNetworkFragment implements
        PartTimeJobContract.View {

    ItemGroupLayout mItemGroupLayout;

    private PartTimeJobPresenter mPresenter;

    public static PartTimeJobFragment getInstance() {
        return new PartTimeJobFragment();
    }


    @Override
    protected int getLayoutViewId() {
        return R.layout.fragment_part_time_job;
    }

    @Override
    protected void initView(View parentView) {
        mItemGroupLayout = parentView.findViewById(R.id.layout_group);
    }

    @Override
    protected BasePresenter createPresenter() {
        mPresenter = new PartTimeJobPresenter();
        return mPresenter;
    }

    @Override
    public void showPageView(Object obj) {

    }

    @Override
    public void bindView(List<PartTimeJobModel> list) {
        mItemGroupLayout.clear();

        for (PartTimeJobModel model : list) {
            PartTimeJobItemView itemView = new PartTimeJobItemView(getContext());
            itemView.bindView(model);
            mItemGroupLayout.addChildView(itemView);
        }
        mItemGroupLayout.setBottomLineHeight(2);
        mItemGroupLayout.build();
    }
}
