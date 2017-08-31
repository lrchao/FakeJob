package com.lrchao.fakejob.model.json.home;

import com.google.gson.annotations.SerializedName;
import com.lrchao.fakejob.model.BaseModel;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/21 下午3:38
 */

public class HomeBannerModel implements BaseModel {

    @SerializedName("id")
    private int mId;

    @SerializedName("img")
    private String mImg;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getImg() {
        return mImg;
    }

    public void setImg(String img) {
        mImg = img;
    }

    @Override
    public void clear() {

    }
}
