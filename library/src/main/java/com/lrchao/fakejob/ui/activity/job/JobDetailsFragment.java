package com.lrchao.fakejob.ui.activity.job;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.lrchao.fakejob.R;
import com.lrchao.fakejob.constant.BundleKey;
import com.lrchao.fakejob.model.json.home.HomeJobModel;
import com.lrchao.fakejob.mvp.BasePresenter;
import com.lrchao.fakejob.ui.activity.common.BaseFragment;
import com.lrchao.fakejob.ui.activity.common.PageNetworkFragment;
import com.lrchao.fakejob.ui.views.JobItemView;
import com.lrchao.utils.ResourceUtils;
import com.lrchao.utils.ToastUtils;
import com.lrchao.views.itemview.ItemGroupLayout;

import java.util.List;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/22 下午2:42
 */

public class JobDetailsFragment extends PageNetworkFragment implements
        JobDetailsContract.View {

    private JobDetailsPresenter mPresenter;

    private int mId;

    private TextView mTvTitle;

    private TextView mTvActionType;

    private TextView mTvPeopleCount;

    private TextView mTvSettlementType;

    private TextView mTvPrice;

    private TextView mTvPriceUnit;

    private TextView mTvDate;

    private TextView mTvWorkTime;

    private TextView mTvWorkAddress;

    private TextView mTvSalary;

    private TextView mTvDesc;

    private TextView mTvExpirationDate;

    private TextView mTvInterviewDate;

    private TextView mTvInterviewAddress;

    private TextView mTvCompanyInfo;

    private ItemGroupLayout mLayoutOtherJobs;

    private TextView mTvJoin;

    @Override
    protected void initData(Intent intent) {
        super.initData(intent);
        mId = intent.getIntExtra(BundleKey.INTENT_EXTRA_JOB_ID, 0);
    }

    public static BaseFragment getInstance() {
        return new JobDetailsFragment();
    }


    @Override
    public void showPageView(Object obj) {

    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.job_fragment_job_details;
    }

    @Override
    protected void initView(View parentView) {
        if (mId <= 0) {
            finishActivity();
        }

        mTvTitle = parentView.findViewById(R.id.tv_title);
        mTvActionType = parentView.findViewById(R.id.tv_action_type);
        mTvPeopleCount = parentView.findViewById(R.id.tv_people_count);
        mTvSettlementType = parentView.findViewById(R.id.tv_settlement_type);
        mTvPrice = parentView.findViewById(R.id.tv_price);
        mTvPriceUnit = parentView.findViewById(R.id.tv_price_unit);
        mTvDate = parentView.findViewById(R.id.tv_date);
        mTvWorkTime = parentView.findViewById(R.id.tv_work_time);
        mTvWorkAddress = parentView.findViewById(R.id.tv_work_address);
        mTvSalary = parentView.findViewById(R.id.tv_salary);
        mTvDesc = parentView.findViewById(R.id.tv_desc);
        mTvExpirationDate = parentView.findViewById(R.id.tv_expiration_date);
        mTvInterviewDate = parentView.findViewById(R.id.tv_interview_date);
        mTvInterviewAddress = parentView.findViewById(R.id.tv_interview_address);
        mTvCompanyInfo = parentView.findViewById(R.id.tv_company_info);
        mLayoutOtherJobs = parentView.findViewById(R.id.layout_other_jobs);
        mTvJoin = parentView.findViewById(R.id.tv_join);
        mTvJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.join();
            }
        });

    }

    @Override
    protected BasePresenter createPresenter() {
        mPresenter = new JobDetailsPresenter();
        mPresenter.setData(mId);
        return mPresenter;
    }

    @Override
    public void bindTopView(String title, String actionType, int peopleCount, String settlementType, String price, String priceUnit, boolean join) {
        mTvTitle.setText(title);
        mTvActionType.setText(actionType);
        mTvPeopleCount.setText(ResourceUtils.getString(R.string.job_unit_people, peopleCount));
        mTvSettlementType.setText(settlementType);
        mTvPrice.setText(price);
        mTvPriceUnit.setText(priceUnit);
        if (join) {
            mTvJoin.setText("已报名");
        } else {
            mTvJoin.setText("报名参加");
        }
    }

    @Override
    public void bindDateView(String date, String workTime, String workAddress) {
        mTvDate.setText(date);
        mTvWorkTime.setText(workTime);
        mTvWorkAddress.setText(workAddress);
    }

    @Override
    public void bindSalaryView(String salary, String desc, String expirationDate) {
        mTvSalary.setText(salary);
        mTvDesc.setText(desc);
        mTvExpirationDate.setText(expirationDate);
    }

    @Override
    public void bindInterviewView(String interviewDate, String interviewAddress) {
        mTvInterviewDate.setText(interviewDate);
        mTvInterviewAddress.setText(interviewAddress);
    }

    @Override
    public void bindCompanyView(String companyInfo) {
        mTvCompanyInfo.setText(companyInfo);
    }

    @Override
    public void bindOtherJobsView(List<HomeJobModel> otherJobs) {
        mLayoutOtherJobs.clear();

        for (HomeJobModel model : otherJobs) {
            JobItemView itemView = new JobItemView(getContext());
            itemView.bindView(model);
            mLayoutOtherJobs.addChildView(itemView);
        }
        mLayoutOtherJobs.setBottomLineHeight(2);
        mLayoutOtherJobs.build();
    }

    @Override
    public void bindJoinSuccessView() {
        ToastUtils.show("恭喜你，报名成功～");
        finishActivity();
    }
}
