package com.lrchao.fakejob.network.post_body;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * Description: 上传流的请求体
 *
 * @author liuranchao
 * @date 16/3/17 上午8:48
 */
public class PostStreamingBody implements PostBody {

    private static final MediaType MEDIA_TYPE_MARKDOWN =
            MediaType.parse("text/x-markdown; charset=utf-8");

    private static final long BYTE_SIZE = 997;

    @Override
    public RequestBody get() {
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return MEDIA_TYPE_MARKDOWN;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.writeUtf8("Numbers\n");
                sink.writeUtf8("-------\n");
                for (int i = 2; i <= BYTE_SIZE; i++) {
                    sink.writeUtf8(String.format(" * %s = %s\n", i, factor(i)));
                }
            }

            private String factor(int n) {
                for (int i = 2; i < n; i++) {
                    int x = n / i;
                    if (x * i == n) {
                        return factor(x) + " × " + i;
                    }
                }
                return Integer.toString(n);
            }
        };
    }
}
