package com.lrchao.fakejob.network.post_body;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Description: Post多个类型的body
 *
 * @author liuranchao
 * @date 16/3/17 上午8:50
 */
public class PostMultiPartBody implements PostBody {

    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    private List<String> mFilePathList = new ArrayList<>();

    public void addFilePath(ArrayList<String> filePathList) {
        mFilePathList = filePathList;
    }

    public void addFilePath(String filePath) {
        mFilePathList.add(filePath);
    }

    @Override
    public RequestBody get() {
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);

        if (mFilePathList != null && mFilePathList.size() > 0) {
            for (String filePath : mFilePathList) {

                File file = new File(filePath);
                builder.addFormDataPart("files", file.getName(),
                        RequestBody.create(MEDIA_TYPE_PNG, file));
            }
        }
        return builder.build();
    }

    @Override
    public String toString() {
        return "PostMultiPartBody{" +
                "mFilePathList=" + mFilePathList +
                '}';
    }
}
