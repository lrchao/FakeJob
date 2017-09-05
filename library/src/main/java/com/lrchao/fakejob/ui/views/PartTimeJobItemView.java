package com.lrchao.fakejob.ui.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lrchao.fakejob.R;
import com.lrchao.fakejob.model.json.job.CategoryModel;
import com.lrchao.fakejob.model.json.job.PartTimeJobModel;
import com.lrchao.fakejob.ui.adapter.part_time_job.PartTimeJobViewModel;
import com.lrchao.fakejob.utils.NavUtils;
import com.lrchao.views.gridview.LrchaoGridAdapter;
import com.lrchao.views.gridview.LrchaoGridLayout;
import com.lrchao.views.gridview.LrchaoGridViewModel;
import com.lrchao.views.gridview.menu.MenuGridIconTextAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/23 上午11:08
 */

public class PartTimeJobItemView extends LinearLayout implements LrchaoGridAdapter.OnLrchaoItemClickListener {

    TextView mTvSubTitle;

    LrchaoGridLayout mGridLayout;

    public PartTimeJobItemView(Context context) {
        super(context);
        init(context);
    }

    public PartTimeJobItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.job_part_time_job_itme, this);
        mTvSubTitle = findViewById(R.id.tv_category_subtitle);
        mGridLayout = findViewById(R.id.layout_category);
    }


    public void bindView(PartTimeJobModel model) {
        mTvSubTitle.setText(model.getSubTitle());

        List<LrchaoGridViewModel> data = new ArrayList<>();

        for (CategoryModel categoryModel : model.getCategoryList()) {
            data.add(bindGridView(categoryModel));
        }

        MenuGridIconTextAdapter categoryAdapter = new MenuGridIconTextAdapter(data);
        categoryAdapter.setLayoutResId(R.layout.job_part_time_job_item);
        categoryAdapter.setOnLrchaoItemClickListener(this);
        mGridLayout.setSpanCount(3);
        mGridLayout.setAdapter(categoryAdapter);
    }

    private PartTimeJobViewModel bindGridView(CategoryModel categoryModel) {
        PartTimeJobViewModel model = new PartTimeJobViewModel();
        model.setId(categoryModel.getId());
        model.setTitle(categoryModel.getTitle());
        return model;
    }

    @Override
    public void onItemClick(LrchaoGridViewModel item, int itemPosition) {

        PartTimeJobViewModel model = (PartTimeJobViewModel) item;
        NavUtils.get().navToCategory(getContext(), model.getTitle().toString(), model.getId());

    }
}

