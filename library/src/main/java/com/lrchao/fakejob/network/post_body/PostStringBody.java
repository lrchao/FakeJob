package com.lrchao.fakejob.network.post_body;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Description: Post string类型的 post body
 *
 * @author liuranchao
 * @date 16/3/16 下午6:07
 */
public class PostStringBody implements PostBody {

    public static final MediaType MEDIA_TYPE_MARKDOWN =
            MediaType.parse("text/x-markdown; charset=utf-8");
    /**
     * Post字符串的内容
     */
    private String mPostBody;

    public void setPostBody(String postBody) {
        mPostBody = postBody;
    }

    @Override
    public RequestBody get() {
        return RequestBody.create(MEDIA_TYPE_MARKDOWN, mPostBody);
    }

    @Override
    public String toString() {
        return "PostStringBody{" +
                "mPostBody='" + mPostBody + '\'' +
                '}';
    }
}
