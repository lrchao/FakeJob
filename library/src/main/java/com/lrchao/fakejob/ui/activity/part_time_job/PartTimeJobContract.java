package com.lrchao.fakejob.ui.activity.part_time_job;

import com.lrchao.fakejob.model.json.job.PartTimeJobModel;
import com.lrchao.fakejob.mvp.MvpView;
import com.lrchao.fakejob.mvp.common.MvpRequestPagePresenter;

import java.util.List;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/23 上午11:32
 */

public interface PartTimeJobContract {

    interface View extends MvpView {
        void bindView(List<PartTimeJobModel> list);
    }


    interface Presenter extends MvpRequestPagePresenter {
    }
}
