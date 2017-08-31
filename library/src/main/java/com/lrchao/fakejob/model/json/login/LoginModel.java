package com.lrchao.fakejob.model.json.login;

import com.google.gson.annotations.SerializedName;
import com.lrchao.fakejob.model.BaseModel;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/26 上午11:14
 */

public class LoginModel implements BaseModel {

    @SerializedName("nick")
    private String mUserName;

    @Override
    public void clear() {

    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

}
