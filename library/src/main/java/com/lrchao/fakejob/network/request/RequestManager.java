package com.lrchao.fakejob.network.request;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description: 请求管理
 *
 * @author lrc19860926@gmail.com
 * @date 2016/12/12 下午4:37
 */

public final class RequestManager {

    private static RequestManager sInstance;

    private ExecutorService mCachedThreadPool;

    private RequestManager() {
        mCachedThreadPool = Executors.newCachedThreadPool();
    }

    public static RequestManager getInstance() {
        synchronized (RequestManager.class) {
            if (sInstance == null) {
                sInstance = new RequestManager();
            }
        }
        return sInstance;
    }

    public void send(Runnable sender) {
        mCachedThreadPool.execute(sender);
    }
}
