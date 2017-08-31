package com.lrchao.fakejob.network.request_failure;

/**
 * Description: 请求的业务失败
 *
 * @author liuranchao
 * @date 16/3/20 上午12:15
 */
public class BusinessRequestFailure extends RequestFailure {

    /**
     * 业务的code
     */
    private int mBusinessCode;

    /**
     * 业务失败的提示
     */
    private String mMessage;

    @Override
    public String getFriendlyMsg() {
        return mMessage;
    }

    public void setBusinessCode(int businessCode) {
        this.mBusinessCode = businessCode;
    }

    public void setMessage(String message) {
        this.mMessage = message;
    }

    @Override
    public String toString() {
        return "BusinessRequestFailure{" +
                "mBusinessCode=" + mBusinessCode +
                ", mMessage='" + mMessage + '\'' +
                ", mMethod='" + getMethod() + '\'' +
                ", url='" + getUrl() + '\'' +
                ", mPostParams=" + getPostParams() +
                '}';
    }
}
