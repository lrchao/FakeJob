package com.lrchao.fakejob.ui.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lrchao.fakejob.R;
import com.lrchao.fakejob.model.json.home.HomeJobModel;
import com.lrchao.fakejob.utils.MyImageUtils;
import com.lrchao.fakejob.utils.NavUtils;


/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/22 上午10:18
 */

public class JobItemView extends LinearLayout implements View.OnClickListener {

    ImageView mIvIcon;

    TextView mTvTitle;

    TextView mTvAddress;

    TextView mTvDate;

    TextView mTvPrice;

    TextView mTvPriceUnit;

    private HomeJobModel mModel;

    public JobItemView(Context context) {
        super(context);
        init(context);
    }

    public JobItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.home_job_list_item, this);

        mIvIcon = findViewById(R.id.iv_icon);
        mTvTitle = findViewById(R.id.tv_title);
        mTvAddress = findViewById(R.id.tv_address);
        mTvDate = findViewById(R.id.tv_date);
        mTvPrice = findViewById(R.id.tv_price);
        mTvPriceUnit = findViewById(R.id.tv_price_unit);

        setOnClickListener(this);
    }


    public void bindView(HomeJobModel model) {

        if (model == null) {
            return;
        }

        mModel = model;

        MyImageUtils.displayJobIcon(model.getIconUrl(), mIvIcon);

        mTvTitle.setText(model.getTitle());

        mTvAddress.setText(model.getAddress());

        mTvDate.setText(model.getDate());

        mTvPrice.setText(model.getPrice());

        mTvPriceUnit.setText(model.getPriceUnit());


    }

    @Override
    public void onClick(View v) {

        if (mModel != null) {
            NavUtils.get().navToJobDetails(getContext(), mModel.getId());
        }
    }
}
