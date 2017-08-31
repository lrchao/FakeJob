package com.lrchao.fakejob.model.json.home;

import com.google.gson.annotations.SerializedName;
import com.lrchao.fakejob.model.BaseModel;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/21 下午3:53
 */

public class HomeJobModel implements BaseModel {

    @SerializedName("id")
    private int mId;

    @SerializedName("iconUrl")
    private String mIconUrl;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("address")
    private String mAddress;

    @SerializedName("date")
    private String mDate;

    @SerializedName("price")
    private String mPrice;

    @SerializedName("price_unit")
    private String mPriceUnit;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getIconUrl() {
        return mIconUrl;
    }

    public void setIconUrl(String iconUrl) {
        mIconUrl = iconUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getPriceUnit() {
        return mPriceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        mPriceUnit = priceUnit;
    }

    @Override
    public void clear() {

    }
}
