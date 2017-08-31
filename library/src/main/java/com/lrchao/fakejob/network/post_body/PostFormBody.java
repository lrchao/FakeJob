package com.lrchao.fakejob.network.post_body;

import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Description: 提交表单类型的请求请求体
 * key value
 *
 * @author liuranchao
 * @date 16/3/16 下午8:15
 */
public class PostFormBody implements PostBody {

    private HashMap<String, Object> mFormParams;

    public PostFormBody() {
        mFormParams = new HashMap<>();
    }

    public void put(String key, Object value) {
        mFormParams.put(key, value);
    }

    public HashMap<String, Object> getFormParams() {
        return mFormParams;
    }

    @Override
    public RequestBody get() {
        FormBody.Builder builder = new FormBody.Builder();
        if (mFormParams != null && mFormParams.size() > 0) {
            for (Map.Entry<String, Object> entry : mFormParams.entrySet()) {
                builder.add(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        return builder.build();
    }

    @Override
    public String toString() {
        return "PostFormBody{" +
                "mFormParams=" + mFormParams +
                '}';
    }
}
