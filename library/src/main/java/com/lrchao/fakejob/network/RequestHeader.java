package com.lrchao.fakejob.network;

import com.lrchao.fakejob.BuildConfig;
import com.lrchao.fakejob.constant.SharedPreferenceKey;
import com.lrchao.fakejob.manager.shared_preference.CommonSharedPreference;
import com.lrchao.utils.DeviceUtils;
import com.lrchao.utils.MD5Utils;

import java.util.HashMap;

/**
 * Description: 请求头
 *
 * @author liuranchao
 * @date 16/3/16 下午5:12
 */
public final class RequestHeader {
    private static final String HEADER_KEY_ACCEPT = "accept";
    private static final String HEADER_KEY_PLATFORM = "platform";
    private static final String HEADER_KEY_AUTH = "MAUTH";
    private static final String HEADER_KEY_TIME = "MTIME";
    private static final String HEADER_KEY_SIGN = "MSIGN";
    private static final String HEADER_KEY_DEVICE_ID = "mDeviceId";
    private static final String HEADER_KEY_CLIENT_VERSION_CODE = "mClientVersionCode";
    private static final String HEADER_KEY_CHANNEL_NAME = "mChannelName";

    private static RequestHeader sInstance;
    private HashMap<String, String> mHeaders;

    private RequestHeader() {
        mHeaders = new HashMap<>();
    }

    /**
     * @return RequestHeader
     */
    public static RequestHeader getInstance() {
        synchronized (RequestHeader.class) {
            if (sInstance == null) {
                sInstance = new RequestHeader();
            }
        }
        return sInstance;
    }

    public HashMap<String, String> getHeaders() {

        addHeader(HEADER_KEY_ACCEPT, "*/*");
        addHeader(HEADER_KEY_PLATFORM, "Android");

        // access token
        String accessToken = CommonSharedPreference.getsInstance().getStringValue(SharedPreferenceKey.PREF_ACCESS_TOKEN);
        addHeader(HEADER_KEY_AUTH, accessToken);

        long time = System.currentTimeMillis();
        addHeader(HEADER_KEY_TIME, String.valueOf(time));
        addHeader(HEADER_KEY_SIGN, getSign(time));
        addHeader(HEADER_KEY_DEVICE_ID, DeviceUtils.getUniqueID());
        addHeader(HEADER_KEY_CLIENT_VERSION_CODE, String.valueOf(BuildConfig.VERSION_CODE));
        addHeader(HEADER_KEY_CHANNEL_NAME, BuildConfig.FLAVOR);
        return mHeaders;
    }

    private String getSign(long time) {
        return MD5Utils.getMD5(0 + String.valueOf(time) + "1f3dg6B01mq9jAQn");
    }

    /**
     * 添加header到请求中
     *
     * @param key   Header key
     * @param value header value
     */
    private void addHeader(String key, String value) {
        mHeaders.put(key, value);
    }
}
