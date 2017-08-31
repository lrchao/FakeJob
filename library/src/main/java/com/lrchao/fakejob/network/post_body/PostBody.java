package com.lrchao.fakejob.network.post_body;

import okhttp3.RequestBody;

/**
 * Description: Post请求的Body基类
 *
 * @author liuranchao
 * @date 16/3/16 下午6:07
 */
public interface PostBody {

    RequestBody get();
}
