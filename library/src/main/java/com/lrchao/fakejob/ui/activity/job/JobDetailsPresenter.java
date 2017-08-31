package com.lrchao.fakejob.ui.activity.job;

import com.lrchao.fakejob.model.json.job.JobDetailsModel;
import com.lrchao.fakejob.mvp.BasePresenter;
import com.lrchao.fakejob.network.RequestIntentFactory;
import com.lrchao.fakejob.network.request.BaseRequest;
import com.lrchao.fakejob.network.request.RequestActionKey;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/22 下午5:10
 */

public class JobDetailsPresenter extends BasePresenter<JobDetailsContract.View> implements
        JobDetailsContract.Presenter {

    private int mId;

    private JobDetailsModel mModel;

    @Override
    public void load() {
        sendRequest(RequestIntentFactory.getJobDetails(mId));
    }

    @Override
    public boolean isAutoLoadPage() {
        return true;
    }

    @Override
    public void setData(int id) {
        mId = id;
    }

    @Override
    public void join() {
        if (mModel != null && !mModel.isJoin()) {
            sendRequest(RequestIntentFactory.getJobJoin(mId));
        }
    }

    @Override
    public void onRequestResultSuccess(BaseRequest request, Object model) {
        super.onRequestResultSuccess(request, model);

        if (request.getAction() == RequestActionKey.ACTION_GET_JOB_DETAILS) {

            JobDetailsModel jobDetailsModel = (JobDetailsModel) model;
            mModel = jobDetailsModel;

            getMvpView().bindTopView(jobDetailsModel.getTitle(),
                                    jobDetailsModel.getActionType(),
                                    jobDetailsModel.getPeopleCount(),
                                    jobDetailsModel.getSettlementType(),
                                    jobDetailsModel.getPrice(),
                                    jobDetailsModel.getPriceUnit(),
                                    jobDetailsModel.isJoin()
            );


            getMvpView().bindDateView(jobDetailsModel.getDate(),
                    jobDetailsModel.getWorkTime(),
                    jobDetailsModel.getWorkAddress());


            getMvpView().bindSalaryView(jobDetailsModel.getSalary(),
                    jobDetailsModel.getDesc(),
                    jobDetailsModel.getExpirationDate());

            getMvpView().bindInterviewView(jobDetailsModel.getInterviewDate(),
                    jobDetailsModel.getInterviewAddress());

            getMvpView().bindCompanyView(jobDetailsModel.getCompanyInfo());

            getMvpView().bindOtherJobsView(jobDetailsModel.getOtherJobs());



        } else if (request.getAction() == RequestActionKey.ACTION_GET_JOB_JOIN) {
            getMvpView().bindJoinSuccessView();
        }
    }
}
