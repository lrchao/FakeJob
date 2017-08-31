package com.lrchao.fakejob.eventbus.poster.network;


import com.lrchao.fakejob.model.BaseModel;
import com.lrchao.fakejob.network.request_failure.RequestFailure;

import java.util.List;

/**
 * Description: 请求相应的EventBus Message Object
 *
 * @author liuranchao
 * @date 16/1/7 下午5:06
 */
public class RequestResultEventPoster extends NetworkEventPoster {

    /**
     * 请求失败结果
     */
    private RequestFailure mRequestFailure;

    /**
     * 请求成功的结果对象
     */
    private Object mResultModel;

    /**
     * 服务端返回的Message, 用于提示用户
     */
    private String mResponseMessage;

    public String getResponseMessage() {
        return mResponseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        mResponseMessage = responseMessage;
    }

    public Object getResultModel() {
        return mResultModel;
    }

    public void setResultModel(Object resultModel) {
        mResultModel = resultModel;
    }

    public RequestFailure getRequestFailure() {
        return mRequestFailure;
    }

    public void setRequestFailure(RequestFailure requestFailure) {
        mRequestFailure = requestFailure;
    }

    @Override
    public void clear() {

        if (getResultModel() instanceof BaseModel) {
            // 清除对象数据
            BaseModel baseModel = (BaseModel) getResultModel();
            baseModel.clear();
        } else if (getResultModel() instanceof List) {
            // 清除数组数据
            List list = (List) getResultModel();
            for (Object obj : list) {
                if (obj instanceof BaseModel) {
                    BaseModel listItemModel = (BaseModel) obj;
                    listItemModel.clear();
                }
            }
            list.clear();
        }
    }
}

