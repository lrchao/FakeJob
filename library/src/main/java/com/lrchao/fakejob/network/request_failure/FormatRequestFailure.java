package com.lrchao.fakejob.network.request_failure;


import com.lrchao.fakejob.R;
import com.lrchao.utils.ResourceUtils;

/**
 * Description: 返回的格式异常
 *
 * @author liuranchao
 * @date 16/3/20 上午12:36
 */
public class FormatRequestFailure extends RequestFailure {

    /**
     * response string
     */
    private String mResponse;

    /**
     * 自定义错误信息
     */
    private String mMessage;

    @Override
    public String getFriendlyMsg() {
        return ResourceUtils.getString(R.string.job_error_server);
    }


    public String getResponse() {
        return mResponse;
    }

    public void setResponse(String response) {
        this.mResponse = response;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        this.mMessage = message;
    }

    @Override
    public String toString() {
        return "FormatRequestFailure{" +
                "url=" + getUrl() +
                "mMethod='" + getMethod() + '\'' +
                ", mPostParams=" + getPostParams() +
                ", mResponse='" + mResponse + '\'' +
                ", mMessage='" + mMessage + '\'' +
                ", url='" + getUrl() + '\'' +
                '}';
    }
}
