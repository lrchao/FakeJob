package com.lrchao.fakejob.model.json.job;

import com.google.gson.annotations.SerializedName;
import com.lrchao.fakejob.model.BaseModel;

import java.util.List;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/23 上午11:36
 */

public class PartTimeJobModel implements BaseModel {

    @SerializedName("subTitle")
    private String mSubTitle;

    @SerializedName("category_list")
    private List<CategoryModel> mCategoryList;

    public String getSubTitle() {
        return mSubTitle;
    }

    public void setSubTitle(String subTitle) {
        mSubTitle = subTitle;
    }

    public List<CategoryModel> getCategoryList() {
        return mCategoryList;
    }

    public void setCategoryList(List<CategoryModel> categoryList) {
        mCategoryList = categoryList;
    }

    @Override
    public void clear() {

    }
}
