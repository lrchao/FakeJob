package com.lrchao.fakejob.network.post_body;


import com.lrchao.utils.JsonUtils;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Description: Post json
 *
 * @author liuranchao
 * @date 16/3/16 下午8:35
 */
public class PostJSONBody implements PostBody {

    public static final MediaType JSON =
            MediaType.parse("application/json; charset=utf-8");

    private HashMap<String, Object> mJSONParams;

    public PostJSONBody() {
        mJSONParams = new HashMap<>();
    }

    public HashMap<String, Object> getJSONParams() {
        return mJSONParams;
    }

    public void put(String key, Object value) {
        mJSONParams.put(key, value);
    }

    @Override
    public RequestBody get() {
        return RequestBody.create(JSON, JsonUtils.write(mJSONParams));
    }

    @Override
    public String toString() {
        return "PostJSONBody{" +
                "mJSONParams=" + mJSONParams +
                '}';
    }
}
