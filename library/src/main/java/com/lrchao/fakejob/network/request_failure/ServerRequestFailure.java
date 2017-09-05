package com.lrchao.fakejob.network.request_failure;


import com.lrchao.fakejob.R;
import com.lrchao.utils.ResourceUtils;

/**
 * Description: 服务端异常，非200的情况
 *
 * @author liuranchao
 * @date 16/3/16 下午2:20
 */
public class ServerRequestFailure extends RequestFailure {

    /**
     * response 的code
     */
    private int mCode;

    /**
     * 请求失败的message
     */
    private String mMessage;


    @Override
    public String getFriendlyMsg() {
        return ResourceUtils.getString(R.string.job_error_server);
    }

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        this.mCode = code;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        this.mMessage = message;
    }

    @Override
    public String toString() {
        return "ServerRequestFailure{" +
                "mCode=" + mCode +
                ", mMessage='" + mMessage + '\'' +
                ", mMethod='" + getMethod() + '\'' +
                ", url='" + getUrl() + '\'' +
                ", mPostParams=" + getPostParams() +
                '}';
    }
}
