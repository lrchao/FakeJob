package com.lrchao.fakejob.eventbus.receiver.mvp;

import android.support.annotation.MainThread;

import com.lrchao.fakejob.eventbus.poster.network.RequestWhenEventPoster;
import com.lrchao.fakejob.eventbus.receiver.BaseEventReceiverModel;
import com.lrchao.fakejob.network.request.BaseRequest;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Description: 请求时机的Event Receiver
 *
 * @author liuranchao
 * @date 16/5/5 下午9:19
 */
public class RequestWhenEventReceiverMvp implements BaseEventReceiverModel {

    /**
     * 本页面发起的请求的action集合
     */
    private List<Integer> mRequestActionList;

    private OnRequestWhenListener mOnRequestWhenListener;

    public void setOnRequestWhenListener(OnRequestWhenListener onRequestWhenListener) {
        mOnRequestWhenListener = onRequestWhenListener;
    }

    public void setRequestActionList(List<Integer> requestActionList) {
        mRequestActionList = requestActionList;
    }

    @MainThread
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RequestWhenEventPoster event) {

        if (event != null && event.getRequest() != null &&
                mRequestActionList.contains(event.getRequest().getAction())) {
            int when = event.getWhen();
            switch (when) {
                case RequestWhenEventPoster.REQUEST_WHEN_BEGIN:
                    onRequestBegin(event.getRequest());
                    break;
                case RequestWhenEventPoster.REQUEST_WHEN_END:
                default:
                    onRequestEnd(event.getRequest());
                    break;
            }
        }
    }

    /**
     * onRequestEnd
     * 开始请求，子类实现具体操作
     */
    protected final void onRequestBegin(BaseRequest request) {
        if (mOnRequestWhenListener != null) {
            mOnRequestWhenListener.onRequestWhenStart(request);
        }
    }

    /**
     * 结束请求，子类实现具体操作
     */
    protected void onRequestEnd(BaseRequest request) {
        if (mOnRequestWhenListener != null) {
            mOnRequestWhenListener.onRequestWhenEnd(request);
        }
    }


    /**
     * 请求时机的监听
     */
    public interface OnRequestWhenListener {

        /**
         * 开始请求
         *
         * @param request BaseRequest
         */
        void onRequestWhenStart(BaseRequest request);

        /**
         * 请求结束
         *
         * @param request BaseRequest
         */
        void onRequestWhenEnd(BaseRequest request);

    }


}
