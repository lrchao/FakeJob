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
import com.lrchao.utils.LogUtils;


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

    ImageView mIvMark;

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
        mIvMark = findViewById(R.id.iv_mark);

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

        String idStr = String.valueOf(model.getId());

        char c = idStr.charAt(idStr.length() - 1);

        int m = Integer.valueOf(String.valueOf(c));
        if (m == 1) {

            mIvMark.setBackgroundResource(R.drawable.clear_month);
            mIvMark.setVisibility(View.VISIBLE);

        } else if (m == 3) {

            mIvMark.setBackgroundResource(R.drawable.clear_ohter);
            mIvMark.setVisibility(View.VISIBLE);

        } else if (m == 8) {

            mIvMark.setBackgroundResource(R.drawable.clear_over);
            mIvMark.setVisibility(View.VISIBLE);

        } else if (m == 6) {

            mIvMark.setBackgroundResource(R.drawable.clear_week);
            mIvMark.setVisibility(View.VISIBLE);

        } else {

            mIvMark.setVisibility(View.GONE);

        }

    }

    @Override
    public void onClick(View v) {

        if (mModel != null) {
            NavUtils.get().navToJobDetails(getContext(), mModel.getId());
        }
    }
}
