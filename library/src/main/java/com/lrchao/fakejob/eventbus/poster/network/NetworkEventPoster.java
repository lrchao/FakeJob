package com.lrchao.fakejob.eventbus.poster.network;


import com.lrchao.fakejob.eventbus.poster.BaseEventPosterModel;
import com.lrchao.fakejob.model.BaseModel;
import com.lrchao.fakejob.network.request.BaseRequest;

/**
 * Description: EventBus的基类
 *
 * @author liuranchao
 * @date 16/3/19 下午8:33
 */
public abstract class NetworkEventPoster implements BaseEventPosterModel, BaseModel {

    /**
     * 请求对象
     */
    private BaseRequest mRequest;

    public BaseRequest getRequest() {
        return mRequest;
    }

    public void setRequest(BaseRequest baseRequest) {
        mRequest = baseRequest;
    }

}
