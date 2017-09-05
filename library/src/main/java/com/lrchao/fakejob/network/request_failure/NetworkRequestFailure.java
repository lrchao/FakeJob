package com.lrchao.fakejob.network.request_failure;

import com.lrchao.fakejob.R;
import com.lrchao.utils.ResourceUtils;

/**
 * Description: 网络异常
 *
 * @author liuranchao
 * @date 16/3/16 下午2:22
 */
public class NetworkRequestFailure extends RequestFailure {

    /**
     * 网络异常的Exception堆栈信息
     */
    private String mExceptionMsg;

    @Override
    public String getFriendlyMsg() {
        return ResourceUtils.getString(R.string.job_error_network);
    }


    public String getExceptionMsg() {
        return mExceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.mExceptionMsg = exceptionMsg;
    }

    @Override
    public String toString() {
        return "NetworkRequestFailure{" +
                "mExceptionMsg='" + mExceptionMsg + '\'' +
                ", url='" + getUrl() + '\'' +
                '}';
    }
}
