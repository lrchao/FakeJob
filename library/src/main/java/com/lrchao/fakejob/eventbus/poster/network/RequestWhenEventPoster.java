package com.lrchao.fakejob.eventbus.poster.network;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Description: 请求时机的Event bus对象
 *
 * @author liuranchao
 * @date 16/3/21 上午9:32
 */
public class RequestWhenEventPoster extends NetworkEventPoster {

    /**
     * 开始请求
     */
    public static final int REQUEST_WHEN_BEGIN = 1;
    /**
     * 结束请求，时机早于ResponseEvent
     */
    public static final int REQUEST_WHEN_END = 2;

    @REQUEST_WHEN
    private int mWhen;

    public int getWhen() {
        return mWhen;
    }

    public void setWhen(int when) {
        mWhen = when;
    }

    @Override
    public String toString() {
        return "RequestWhenEvent{" +
                "mWhen=" + mWhen +
                '}';
    }

    @Override
    public void clear() {

    }

    @IntDef({RequestWhenEventPoster.REQUEST_WHEN_BEGIN,
            RequestWhenEventPoster.REQUEST_WHEN_END
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface REQUEST_WHEN {
    }
}
