package com.lrchao.fakejob.ui.adapter.home;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.lrchao.utils.ResourceUtils;
import com.lrchao.views.gridview.menu.MenuGridIconTextViewModel;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/21 下午2:53
 */

public class HomeCategoryViewModel implements MenuGridIconTextViewModel {

    @StringRes
    private int mTitle;

    @DrawableRes
    private int mIcon;

    private int mId;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public void setTitle(int title) {
        mTitle = title;
    }

    public void setIcon(@DrawableRes int icon) {
        mIcon = icon;
    }

    @Override
    public CharSequence getTitle() {
        return ResourceUtils.getString(mTitle);
    }

    @Override
    public int getIcon() {
        return mIcon;
    }
}
