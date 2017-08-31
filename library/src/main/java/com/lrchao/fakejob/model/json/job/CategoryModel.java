package com.lrchao.fakejob.model.json.job;

import com.google.gson.annotations.SerializedName;
import com.lrchao.fakejob.model.BaseModel;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/23 上午11:39
 */

public class CategoryModel implements BaseModel {

    @SerializedName("id")
    private int mId;

    @SerializedName("title")
    private String mTitle;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    @Override
    public void clear() {

    }
}
