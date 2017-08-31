package com.lrchao.fakejob.network.post_body;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Description: 上传文件的请求体
 *
 * @author liuranchao
 * @date 16/3/17 上午8:49
 */
public class PostFileBody implements PostBody {

    public static final MediaType MEDIA_TYPE_MARKDOWN =
            MediaType.parse("text/x-markdown; charset=utf-8");
    private File mFile;

    public void setFile(File file) {
        mFile = file;
    }

    @Override
    public RequestBody get() {
        return RequestBody.create(MEDIA_TYPE_MARKDOWN, mFile);
    }

    @Override
    public String toString() {
        return "File:" + mFile.getAbsolutePath();
    }
}
