package com.lrchao.fakejob.ui.activity.part_time_job;

import android.view.View;

import com.lrchao.fakejob.R;
import com.lrchao.fakejob.model.json.job.PartTimeJobModel;
import com.lrchao.fakejob.mvp.BasePresenter;
import com.lrchao.fakejob.ui.activity.common.PageNetworkFragment;
import com.lrchao.fakejob.ui.views.PartTimeJobItemView;
import com.lrchao.fakejob.utils.NavUtils;
import com.lrchao.views.itemview.ItemGroupLayout;

import java.util.List;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/23 上午10:33
 */

public class PartTimeJobFragment extends PageNetworkFragment implements
        PartTimeJobContract.View, View.OnClickListener {

    ItemGroupLayout mItemGroupLayout;

    private PartTimeJobPresenter mPresenter;

    public static PartTimeJobFragment getInstance() {
        return new PartTimeJobFragment();
    }


    @Override
    protected int getLayoutViewId() {
        return R.layout.job_fragment_part_time_job;
    }

    @Override
    protected void initView(View parentView) {
        mItemGroupLayout = parentView.findViewById(R.id.layout_group);

        parentView.findViewById(R.id.ll_diaoyan).setOnClickListener(this);
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

    @Override
    public void onClick(View view) {

        String title = "";
        if (view.getId() == R.id.ll_diaoyan) {
            title = "调研";
        } else if (view.getId() == R.id.ll_tuiguang) {
            title = "推广";
        } else if (view.getId() == R.id.ll_liyi) {
            title = "礼仪";
        } else if (view.getId() == R.id.ll_peixun) {
            title = "培训";
        } else if (view.getId() == R.id.ll_daogou) {
            title = "导购";
        } else if (view.getId() == R.id.ll_zhongdiangong) {
            title = "钟点工";
        } else if (view.getId() == R.id.ll_other) {
            title = "其他";
        }

        NavUtils.get().navToCategory(getContext(), title, 2);
    }
}
