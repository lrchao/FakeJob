package com.lrchao.fakejob.ui.activity.job;


import com.lrchao.fakejob.model.json.home.HomeJobModel;
import com.lrchao.fakejob.mvp.MvpView;
import com.lrchao.fakejob.mvp.common.MvpRequestPagePresenter;

import java.util.List;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/22 下午5:11
 */

public interface JobDetailsContract {

    interface View extends MvpView {
        void bindTopView(String title, String actionType, int peopleCount, String settlementType, String price, String priceUnit, boolean join);

        void bindDateView(String date, String workTime, String workAddress);

        void bindSalaryView(String salary, String desc, String expirationDate);

        void bindInterviewView(String interviewDate, String interviewAddress);

        void bindCompanyView(String companyInfo);

        void bindOtherJobsView(List<HomeJobModel> otherJobs);

        void bindJoinSuccessView();
    }


    interface Presenter extends MvpRequestPagePresenter {
        void setData(int id);

        void join();
    }
}
