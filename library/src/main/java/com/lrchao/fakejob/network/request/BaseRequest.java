package com.lrchao.fakejob.network.request;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.lrchao.fakejob.MyApplication;
import com.lrchao.fakejob.eventbus.poster.network.RequestResultEventPoster;
import com.lrchao.fakejob.eventbus.poster.network.RequestWhenEventPoster;
import com.lrchao.fakejob.model.json.EmptyModel;
import com.lrchao.fakejob.network.NetworkHelper;
import com.lrchao.fakejob.network.post_body.PostBody;
import com.lrchao.fakejob.network.request_failure.BusinessRequestFailure;
import com.lrchao.fakejob.network.request_failure.FormatRequestFailure;
import com.lrchao.fakejob.network.request_failure.RequestFailure;
import com.lrchao.utils.EventBusUtils;
import com.lrchao.utils.JsonUtils;
import com.lrchao.utils.LogUtils;
import com.lrchao.utils.Pair;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Description: 请求对象的基类
 * 1.子类必须设置泛型，T，如果不需要返回值则 设置 EmptyModel
 *
 * @author liuranchao
 * @date 16/3/15 下午3:37
 */
public abstract class BaseRequest<T> {

    /**
     * 服务端返回的字段名
     */
    private static final String JSON_FIELD_RESULT = "data"; // result 节点
    private static final String JSON_FIELD_MESSAGE = "message"; // message 节点
    private static final String JSON_FIELD_CODE = "code"; // code 节点

    /**
     * 当服务端返回code为1时 表示业务成功
     */
    private static final int BUSINESS_CODE_SUCCESS = 0;

    /**
     * 请求的动作
     */
    private int mAction;

    /**
     * 传递请求请求参数的Intent
     */
    private Intent mParamsIntent;
    /**
     * 过滤的参数集合
     */
    private HashMap<String, Object> mFilterParams;
    /**
     * 路径的参数
     */
    private ArrayList<String> mPathParams;

    /**
     * 请求的完整URL
     */
    private String mUrl;

    /**
     * 请求结果的Event对象
     */
    private RequestResultEventPoster mRequestResultEvent;

    /**
     * 请求时机的Event对象
     */
    private RequestWhenEventPoster mRequestWhenEvent;

    /**
     * 构造方法
     */
    public BaseRequest() {
        mFilterParams = new HashMap<>();
        mPathParams = new ArrayList<>();
        mRequestResultEvent = new RequestResultEventPoster();
        mRequestResultEvent.setRequest(this);
        mRequestWhenEvent = new RequestWhenEventPoster();
        mRequestWhenEvent.setRequest(this);
    }


    //==============================
    // 子类 重写的方法
    //==============================

    /**
     * 检查是否可以发送，检查失败 不发送，并且不会提示
     */
    protected boolean checkCanSend() {
        return true;
    }

    /**
     * 设置URL的HOST
     *
     * @return URL的host
     */
    protected String getHost() {
        return MyApplication.getApplication().getHostUrl();
    }

    /**
     * 设置 请求方式
     *
     * @return {@link RequestMethod}
     */
    public abstract String getMethod();

    /**
     * 设置URL的path部分
     *
     * @return URL的Path部分
     */
    protected abstract String getUrlPath();

    /**
     * 设置URL过滤的参数
     *
     * @param params Map<String, Object> Key:参数名 Value:参数值
     */
    protected void getFilterParams(Map<String, Object> params) {
    }

    /**
     * 设置URL path的参数
     *
     * @param params List<String>
     */
    protected void getPathParams(List<String> params) {

    }

    /**
     * 设置Post请求体
     *
     * @return PostBody
     */
    public PostBody getPostBody() {
        return null;
    }


    /**
     * 子类从Intent中获取请求需要的参数参数
     *
     * @param intent Intent
     */
    public void getParamsIntent(Intent intent) {
    }

    /**
     * 设置请求是否需要在request中加cookie
     */
    public boolean isNeedCookie() {
        return false;
    }

    /**
     * 返回对应解析对象
     * 此方执行结束后，才会把结果返回给UI
     *
     * @param responseModel 对应的解析对象
     */
    @WorkerThread
    protected void onResponseModelBefore(T responseModel) {
    }

    /**
     * 返回请求失败的信息
     * 此方执行结束后，才会把结果返回给UI
     */
    @WorkerThread
    protected void onRequestFailureBefore(RequestFailure requestFailure) {
    }

    /**
     * 返回对应解析对象
     * 把结果返回给UI,执行
     *
     * @param responseModel 对应的解析对象
     */
    @WorkerThread
    protected void onResponseModelAfter(T responseModel) {
    }

    /**
     * 返回请求失败的信息
     * 把结果返回给UI执行
     */
    @WorkerThread
    protected void onRequestFailureAfter(RequestFailure requestFailure) {

    }

    //==============================
    // 外部调用
    //==============================

    /**
     * 获取完整的URL
     *
     * @return 完成的URL
     */
    public final String getUrl() {
        return mUrl;
    }

    /**
     * 设置跳转到Request的Intent
     *
     * @param paramsIntent Intent
     */
    public final void setParamsIntent(Intent paramsIntent) {
        mParamsIntent = paramsIntent;
    }

    /**
     * 准备执行
     */
    public final void prepareExecute() {
        if (mParamsIntent != null) {
            getParamsIntent(mParamsIntent);
        }

        if (mFilterParams != null) {
            getFilterParams(mFilterParams);
        }

        if (mPathParams != null) {
            getPathParams(mPathParams);
        }

        mUrl = generateURL();
    }

    /**
     * 同步请求
     * 执行请求
     */
    public final void execute() {
        prepareExecute();
        boolean isOK = checkCanSend();
        if (isOK) {
            postRequestWhenBegin();
            NetworkHelper.getInstance().call(this);
        }
    }

    /**
     * 获取请求的action
     *
     * @return 请求的action
     */
    public final int getAction() {
        return mAction;
    }

    /**
     * 设置给request 对应的action
     *
     * @param action 请求的action
     */
    public final void setAction(int action) {
        mAction = action;
    }

    /**
     * @return 完整的URL
     */
    private String generateURL() {
        StringBuilder sb = new StringBuilder();
        sb.append(getHost());
        sb.append(getUrlPath());
        addPathParams(sb);
        addFilterParams(sb);
        LogUtils.d("url==" + sb.toString());
        return sb.toString();
    }

    //==============================
    // 内部
    //==============================

    /**
     * 添加路径的参数
     *
     * @param sb StringBuilder
     */
    private void addPathParams(StringBuilder sb) {
        if (mPathParams != null && mPathParams.size() > 0) {
            for (String pathParam : mPathParams) {
                sb.append("/");
                sb.append(pathParam);
            }
        }
    }

    /**
     * 追加过滤的参数
     *
     * @param sb StringBuilder
     */
    private void addFilterParams(StringBuilder sb) {
        if (mFilterParams != null && mFilterParams.size() > 0) {
            int index = 0;
            for (Map.Entry<String, Object> entry : mFilterParams.entrySet()) {
                String key = entry.getKey();

                Object value = entry.getValue();

                String valueStr = String.valueOf(value);

                try {
                    valueStr = URLEncoder.encode(valueStr, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                if (index == 0) {
                    sb.append("?");
                }
                sb.append(key);
                sb.append("=");
                sb.append(valueStr);
                if (mFilterParams.size() - 1 > index) {
                    sb.append("&");
                }
                index++;
            }
        }
    }

    /**
     * NetworkHelper传递来的200的情况
     * 解析服务端返回的JSON字符串
     *
     * @param responseStr 服务端返回的Json字符串
     */
    public final void onResponse(String responseStr) {
        LogUtils.json(responseStr);
        RequestFailure requestFailure;
        T t = null;
        String responseMsg = "";

        // 转换成JSON Object
        JSONObject jsonObject = JsonUtils.getJSONObject(responseStr);

        if (jsonObject != null) {

            int responseCode = JsonUtils.getJSONInt(jsonObject, JSON_FIELD_CODE);
            // 解析服务端返回的字段
            responseMsg = JsonUtils.getJSONString(jsonObject, JSON_FIELD_MESSAGE);

            if (BUSINESS_CODE_SUCCESS == responseCode) {
                Pair<T, RequestFailure> pair = parseResultNode(jsonObject, responseStr);
                t = pair.mFirst;
                requestFailure = pair.mSecond;

            } else {
                // 业务失败
                requestFailure = getBusinessFailure(responseCode, responseMsg);
            }

        } else {
            // 服务端返回的不是JSON格式
            requestFailure = getFormatFailure("response is not a json string", responseStr);
        }

        onBaseRequestFinished(t, requestFailure, responseMsg, responseStr);
    }

    /**
     * 解析response json 的 result节点
     */
    private Pair<T, RequestFailure> parseResultNode(@NonNull JSONObject jsonObject, String responseStr) {

        T t = null;

        RequestFailure requestFailure = null;

        if (TextUtils.isEmpty(responseStr)) {

            // response 为 null
            requestFailure = getFormatFailure("response string is null", responseStr);
        } else {

            Object responseResult = JsonUtils.getObject(jsonObject, JSON_FIELD_RESULT);

            if (responseResult == null) {
                // result节点有问题
                requestFailure = getFormatFailure("response " + JSON_FIELD_RESULT + " node is null", responseStr);
            } else {

                try {
                    // 如果是单个对象的话
                    if (responseResult instanceof JSONObject) {
                        // 解析result为对象的情况
                        t = parseJSONObjResult(responseResult.toString());

                    } else if (responseResult instanceof JSONArray) {
                        // 解析result为List的情况
                        t = parseJSONListResult(responseResult.toString());
                    }

                    // 解析正常
                    if (t == null) {
                        requestFailure = getFormatFailure("responseResult is not a JSONObject or JSONArray", responseStr);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    requestFailure = getFormatFailure(e.getMessage(), responseStr);
                }
            }
        }

        return new Pair(t, requestFailure);
    }

    /**
     * 解析result 为 ArrayList类型的情况
     *
     * @param resultJsonStr result json string
     */
    private T parseJSONListResult(String resultJsonStr) {
        // 当前类的类型com.jia.zxpt.user.network.BaseRequest<java.util.ArrayList<com.jia.zxpt.user.model.xxx.xxx>>
        Type classType = getClass().getGenericSuperclass();
        //参数的类型集合
        Type[] argTypes = ((ParameterizedType) classType).getActualTypeArguments();
        //ArrayList的Type java.util.ArrayList<com.jia.zxpt.user.xxx.xxx>
        Type arrayListTypes = argTypes[0];
        //
        ParameterizedType arrayListActualType = (ParameterizedType) arrayListTypes;
        //ArrayList的参数类型集合
        Type[] arrayListArgTypes = arrayListActualType.getActualTypeArguments();
        // 参数具体类型的class
        Class arrayListParamTypeClass = (Class) arrayListArgTypes[0];
        //
        List<T> data = JsonUtils.readListFromClass(resultJsonStr, arrayListParamTypeClass);
        return (T) data;
    }

    /**
     * 解析result 为 obj类型的情况
     *
     * @param resultJsonStr result json string
     */
    private T parseJSONObjResult(String resultJsonStr) {

        JSONObject jsonObject = JsonUtils.getJSONObject(resultJsonStr);
        // 判断睡否为 empty object
        if (jsonObject.length() <= 0) {
            return (T) new EmptyModel();
        }

        Class clazz = getClass();
        ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
        Type[] types = parameterizedType.getActualTypeArguments();
        Type types1 = types[0];

        if (types1 instanceof Class) {
            // 一层范型
            Class<T> classes = (Class<T>) types1;
            return JsonUtils.read(resultJsonStr, classes);
        } else {
            Gson gson = new Gson();
             return gson.fromJson(resultJsonStr, types1);
        }

    }

    /**
     * NetworkHelper传递来的失败情况
     *
     * @param requestFailure RequestFailure
     */
    public final void onFailure(RequestFailure requestFailure) {
        onBaseRequestFinished(null, requestFailure, "", "");
    }

    /**
     * 请求流程走完时调用，包括成功和失败
     */
    private void onBaseRequestFinished(T t, RequestFailure requestFailure, String responseMsg, String responseStr) {

        postRequestWhenEnd();

        // 成功的情况已经在parseResultNode成功的情况下调用子类的onResponseModel
        if (requestFailure != null) {
            LogUtils.e(requestFailure.toString());
            onBaseRequestFailed(requestFailure);
        } else {
            onBaseRequestSuccess(t, responseMsg);
        }

        if (isUIRequest()) {
            EventBusUtils.post(mRequestResultEvent);
        }

        // 传递给UI层后的处理
        if (requestFailure != null) {
            onRequestFailureAfter(requestFailure);
        } else {
            onResponseModelAfter(t);
        }

        saveLog(requestFailure, responseStr);
    }

    /**
     * 请求结束后保存Log
     *
     * @param requestFailure RequestFailure
     * @param responseMsg    response string
     */
    private void saveLog(RequestFailure requestFailure, String responseMsg) {

//        HttpLogModel model = new HttpLogModel();
//        model.setMethod(getMethod());
//        model.setUrl(generateURL());
//
//        if (requestFailure != null && requestFailure instanceof ServerRequestFailure) {
//            ServerRequestFailure serverRequestFailure = (ServerRequestFailure) requestFailure;
//            model.setCode(serverRequestFailure.getCode());
//        } else {
//            model.setCode(NetworkHelper.RESPONSE_CODE_SUCCESS);
//        }
//
//        model.setResponse(responseMsg);
//
//        if (getPostBody() != null && getPostBody() instanceof PostJSONBody) {
//            PostJSONBody postJSONBody = (PostJSONBody) getPostBody();
//            model.setParams(postJSONBody.getJSONParams());
//        }
//
//        model.setHeader(RequestHeader.getInstance().getHeaders());
    }

    /**
     * 生成返回的格式异常 FormatRequestFailure
     */
    private FormatRequestFailure getFormatFailure(String message, String responseStr) {
        FormatRequestFailure failure = new FormatRequestFailure();
        failure.setUrl(getUrl());
        failure.setMethod(getMethod());
        if (getPostBody() != null) {
            failure.setPostParams(getPostBody().toString());
        }

        failure.setMessage(message);
        failure.setResponse(responseStr);
        return failure;
    }

    /**
     * 生成返回的业务异常 BusinessRequestFailure
     *
     * @param businessCode 业务码
     * @param businessMsg  业务消息
     */
    private RequestFailure getBusinessFailure(int businessCode, String businessMsg) {
        BusinessRequestFailure failure = new BusinessRequestFailure();
        failure.setUrl(getUrl());
        failure.setMethod(getMethod());
        if (getPostBody() != null) {
            failure.setPostParams(getPostBody().toString());
        }

        failure.setBusinessCode(businessCode);
        failure.setMessage(businessMsg);
        return failure;
    }

    /**
     * 是否为UI的请求
     */
    private boolean isUIRequest() {
        // 为什么要区分 是否为UI的请求
        return /*this instanceof DialogRequest || this instanceof PageRequest*/true;
    }

    /**
     * 发送RequestWhenEvent 请求开始时机到UI层
     */
    private void postRequestWhenBegin() {
        if (isUIRequest()) {
            mRequestWhenEvent.setWhen(RequestWhenEventPoster.REQUEST_WHEN_BEGIN);
            EventBusUtils.post(mRequestWhenEvent);
        }
    }

    /**
     * 发送RequestWhenEvent 请求开始时机到UI层
     */
    private void postRequestWhenEnd() {
        if (isUIRequest()) {
            mRequestWhenEvent.setWhen(RequestWhenEventPoster.REQUEST_WHEN_END);
            EventBusUtils.post(mRequestWhenEvent);
        }
    }

    /**
     * 请求成功处理
     *
     * @param responseModel T
     */
    private void onBaseRequestSuccess(T responseModel, String responseMsg) {
        onResponseModelBefore(responseModel);
        mRequestResultEvent.setResultModel(responseModel);
        mRequestResultEvent.setResponseMessage(responseMsg);
    }

    /**
     * 请求失败处理
     *
     * @param requestFailure RequestFailure
     */
    private void onBaseRequestFailed(RequestFailure requestFailure) {
        onRequestFailureBefore(requestFailure);
        // 设置失败的
        mRequestResultEvent.setRequestFailure(requestFailure);
    }
}
