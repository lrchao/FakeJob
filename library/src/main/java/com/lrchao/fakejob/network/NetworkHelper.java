package com.lrchao.fakejob.network;


import com.lrchao.fakejob.network.request.BaseRequest;
import com.lrchao.fakejob.network.request.RequestMethod;
import com.lrchao.fakejob.network.request_failure.LogoutRequestFailure;
import com.lrchao.fakejob.network.request_failure.NetworkRequestFailure;
import com.lrchao.fakejob.network.request_failure.ServerRequestFailure;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Description: 网络库的调用
 * <p/>
 * JSON转换
 * 设置超时
 * 错误重连
 * 配置 Access token Interceptor  addNetworkInterceptor
 * <p/>
 * <p/>
 * 移除请求
 *
 * @author liuranchao
 * @date 16/3/15 下午3:14
 */
public final class NetworkHelper {

    /**
     * 链接成功的状态码
     */
    public static final int RESPONSE_CODE_SUCCESS = 200;

    /**
     * 登出的状态码
     */
    public static final int RESPONSE_CODE_LOGOUT = 401;

    /**
     * Token失效的状态码
     */
    public static final int RESPONSE_CODE_TOKEN_INVALID = 407;

    /**
     * 链接超时时长
     */
    private static final long CONNECT_TIME_OUT = 10;
    /**
     * 写操作的链接超时时长
     */
    private static final long WRITE_TIME_OUT = 10;
    /**
     * 读操作的链接超时时长
     */
    private static final long READ_TIME_OUT = 30;

    private static NetworkHelper sInstance;

    /**
     * 需要加上cookie的URL集合
     */
    private Set<String> mNeedCookieURLs;

    /**
     * OkHttp client
     */
    private OkHttpClient mOKHttpClient;


    //================================================
    // 外部调用的
    //================================================

    //================================================
    // 内部的
    //================================================
    private NetworkHelper() {
        mNeedCookieURLs = new HashSet<>();

        mOKHttpClient = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 获取实例
     *
     * @return NetworkHelper
     */
    public static NetworkHelper getInstance() {
        synchronized (NetworkHelper.class) {
            if (sInstance == null) {
                sInstance = new NetworkHelper();
            }
        }
        return sInstance;
    }

    public void addInterceptor(Interceptor interceptor) {
        mOKHttpClient = mOKHttpClient.newBuilder().addInterceptor(interceptor).build();
    }

    /**
     * @return 需要Cookie的链接集合
     */
    public Set<String> getNeedCookieURLs() {
        return mNeedCookieURLs;
    }

    /**
     * 统一调用的请求的地方
     *
     * @param baseRequest BaseRequest
     */
    public void call(BaseRequest baseRequest) {

        try {
            Request.Builder builder = new Request.Builder();
            // 设置Header
            addRequestHeader(builder);
            // 设置URL
            builder.url(baseRequest.getUrl());

            // 是否为需要cookie的请求
            if (baseRequest.isNeedCookie()) {
                mNeedCookieURLs.add(baseRequest.getUrl());
            }

            // 如果是Post，则是Post body
            if (RequestMethod.POST.equals(baseRequest.getMethod())) {
                builder.post(baseRequest.getPostBody().get());
            }
            if (RequestMethod.PACTH.equals(baseRequest.getMethod())) {
                builder.patch(baseRequest.getPostBody().get());
            }

            // 生成OKHttp的request
            Request request = builder.build();

            // 获取 response
            Response response = mOKHttpClient.newCall(request).execute();

            // 网络请求成功
            if (response.code() == RESPONSE_CODE_SUCCESS) {

                // just call response.body().string() once
                baseRequest.onResponse(response.body().string());

            } else {
                // 请求失败，服务端错误 非200
                if (response.code() == RESPONSE_CODE_LOGOUT) {
                    setLogoutRequestFailure(baseRequest);
                } else {
                    setServerRequestFailure(baseRequest, baseRequest.getUrl(), response);
                }
            }

        } catch (IOException e) {
            // 网络异常
            setNetworkRequestFailure(baseRequest, e, baseRequest.getUrl());
        }
    }

    /**
     * 添加request header
     *
     * @param builder Request.Builder
     */
    private void addRequestHeader(Request.Builder builder) {
        HashMap<String, String> headers = RequestHeader.getInstance().getHeaders();
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                if (entry.getKey() != null && entry.getValue() != null) {
                    builder.addHeader(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    /**
     * 将网络异常返回给request
     *
     * @param baseRequest BaseRequest
     * @param e           Exception
     * @param url         请求的URL
     */
    private void setNetworkRequestFailure(BaseRequest baseRequest, Exception e, String url) {
        NetworkRequestFailure failure = new NetworkRequestFailure();
        failure.setUrl(url);
        failure.setExceptionMsg(e.getMessage());
        baseRequest.onFailure(failure);
    }

    /**
     * 将服务端异常返回给request
     *
     * @param baseRequest BaseRequest baseRequest
     * @param response    Response
     * @param url         请求的URL
     */
    private void setServerRequestFailure(BaseRequest baseRequest, String url, Response response) {
        ServerRequestFailure failure = new ServerRequestFailure();
        failure.setUrl(url);
        failure.setCode(response.code());
        failure.setMethod(response.request().method());
        failure.setMessage(response.message());

        if (baseRequest.getPostBody() != null) {
            failure.setPostParams(baseRequest.getPostBody().toString());
        }

        baseRequest.onFailure(failure);
    }

    /**
     * 登陆失效错误
     *
     * @param baseRequest BaseRequest
     */
    private void setLogoutRequestFailure(BaseRequest baseRequest) {
        LogoutRequestFailure failure = new LogoutRequestFailure();
        baseRequest.onFailure(failure);
    }

}
