package com.lrchao.fakejob.service;

import android.app.IntentService;
import android.content.Intent;

import com.lrchao.fakejob.MyApplication;
import com.lrchao.fakejob.constant.BundleKey;
import com.lrchao.fakejob.network.request.RequestManager;
import com.lrchao.fakejob.network.request.RequestSender;

/**
 * Description: 发送请求的Service
 *
 * @author liuranchao
 * @date 16/3/12 下午10:19
 */
public class RequestService extends IntentService {

    public RequestService() {
        super("RequestService");
    }

    /**
     * 获取启动service的intent
     *
     * @param action request action
     * @return Intent
     */
    public static Intent newStartIntent(int action) {
        Intent newIntent = new Intent(MyApplication.getApplication(), RequestService.class);
        newIntent.putExtra(BundleKey.INTENT_EXTRA_REQUEST_ACTION, action);
        return newIntent;
    }

    @Override
    public void onHandleIntent(final Intent intent) {
        RequestSender sender = new RequestSender(intent);
        RequestManager.getInstance().send(sender);
    }
}
