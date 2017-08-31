package com.lrchao.fakejob.network.post_body;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Description: 请求参数为 json 字符串的 request
 *
 * @author ouyangzx
 * @date 16/5/3 下午6:47
 */
public class PostJSONStrBody implements PostBody {
    public static final MediaType JSON =
            MediaType.parse("application/json; charset=utf-8");

    private String mJsonStr;

    public void setJsonStr(String jsonStr) {
        mJsonStr = jsonStr;
    }

    @Override
    public RequestBody get() {
        return RequestBody.create(JSON, mJsonStr);
    }

    @Override
    public String toString() {
        return "PostJSONStrBody{" +
                "mJsonStr='" + mJsonStr + '\'' +
                '}';
    }
}
