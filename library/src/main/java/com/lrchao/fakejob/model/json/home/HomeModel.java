package com.lrchao.fakejob.model.json.home;

import com.google.gson.annotations.SerializedName;
import com.lrchao.fakejob.model.BaseModel;

import java.util.List;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/21 下午3:33
 */

public class HomeModel implements BaseModel {

    @SerializedName("banner_list")
    private List<HomeBannerModel> mBannerList;


    @SerializedName("job_list")
    private List<HomeJobModel> mJobList;

    public List<HomeJobModel> getJobList() {
        return mJobList;
    }

    public void setJobList(List<HomeJobModel> jobList) {
        mJobList = jobList;
    }

    public List<HomeBannerModel> getBannerList() {
        return mBannerList;
    }

    public void setBannerList(List<HomeBannerModel> bannerList) {
        mBannerList = bannerList;
    }

    @Override
    public void clear() {

    }
}
