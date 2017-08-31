package com.lrchao.fakejob.network.request_failure;

/**
 * Description: 请求异常的基类
 *
 * @author liuranchao
 * @date 16/3/16 下午2:18
 */
public abstract class RequestFailure {

    /**
     * 请求的地址
     */
    private String mUrl;

    /**
     * Post的参数
     */
    private String mPostParams;

    /**
     * get or post
     */
    private String mMethod;

    /**
     * 友好提示用户的信息
     */
    public abstract String getFriendlyMsg();

    public String getMethod() {
        return mMethod;
    }

    public void setMethod(String method) {
        this.mMethod = method;
    }

    public String getPostParams() {
        return mPostParams;
    }

    public void setPostParams(String postParams) {
        mPostParams = postParams;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }
}
