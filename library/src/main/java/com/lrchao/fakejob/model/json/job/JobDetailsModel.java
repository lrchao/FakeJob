package com.lrchao.fakejob.model.json.job;

import com.google.gson.annotations.SerializedName;
import com.lrchao.fakejob.model.json.home.HomeJobModel;

import java.util.List;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/22 下午3:53
 */

public class JobDetailsModel extends HomeJobModel {

    /**
     * 行动方式
     */
    @SerializedName("action_type")
    private String mActionType;

    /**
     * 需要人数
     */
    @SerializedName("people_count")
    private int mPeopleCount;

    /**
     * 结算
     */
    @SerializedName("settlement_type")
    private String mSettlementType;

    /**
     * 工作时间  12:00 ~ 23:00
     */
    @SerializedName("work_time")
    private String mWorkTime;

    /**
     * 工作地点，
     */
    @SerializedName("work_address")
    private String mWorkAddress;

    /**
     * 薪资福利
     */
    @SerializedName("salary")
    private String mSalary;

    /**
     * 描述
     */
    @SerializedName("desc")
    private String mDesc;

    /**
     * 报名截止日期
     */
    @SerializedName("expiration_date")
    private String mExpirationDate;

    /**
     * 面试时间
     */
    @SerializedName("interview_date")
    private String mInterviewDate;

    /**
     * 面试地点
     */
    @SerializedName("interview_address")
    private String mInterviewAddress;

    /**
     * 公司信息
     */
    @SerializedName("company_info")
    private String mCompanyInfo;

    /**
     * 其他工作
     */
    @SerializedName("other_jobs")
    private List<HomeJobModel> mOtherJobs;

    @SerializedName("joined")
    private int mIsJoined;

    public boolean isJoin() {
        return getIsJoined() > 0;
    }

    public int getIsJoined() {
        return mIsJoined;
    }

    public void setIsJoined(int isJoined) {
        mIsJoined = isJoined;
    }

    public List<HomeJobModel> getOtherJobs() {
        return mOtherJobs;
    }

    public void setOtherJobs(List<HomeJobModel> otherJobs) {
        mOtherJobs = otherJobs;
    }

    public String getActionType() {
        return mActionType;
    }

    public void setActionType(String actionType) {
        mActionType = actionType;
    }

    public int getPeopleCount() {
        return mPeopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        mPeopleCount = peopleCount;
    }

    public String getSettlementType() {
        return mSettlementType;
    }

    public void setSettlementType(String settlementType) {
        mSettlementType = settlementType;
    }

    public String getWorkTime() {
        return mWorkTime;
    }

    public void setWorkTime(String workTime) {
        mWorkTime = workTime;
    }

    public String getWorkAddress() {
        return mWorkAddress;
    }

    public void setWorkAddress(String workAddress) {
        mWorkAddress = workAddress;
    }

    public String getSalary() {
        return mSalary;
    }

    public void setSalary(String salary) {
        mSalary = salary;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String desc) {
        mDesc = desc;
    }

    public String getExpirationDate() {
        return mExpirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        mExpirationDate = expirationDate;
    }

    public String getInterviewDate() {
        return mInterviewDate;
    }

    public void setInterviewDate(String interviewDate) {
        this.mInterviewDate = interviewDate;
    }

    public String getInterviewAddress() {
        return mInterviewAddress;
    }

    public void setInterviewAddress(String interviewAddress) {
        this.mInterviewAddress = interviewAddress;
    }

    public String getCompanyInfo() {
        return mCompanyInfo;
    }

    public void setCompanyInfo(String companyInfo) {
        mCompanyInfo = companyInfo;
    }
}
