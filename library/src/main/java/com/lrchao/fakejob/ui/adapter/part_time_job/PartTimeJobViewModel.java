package com.lrchao.fakejob.ui.adapter.part_time_job;

import com.lrchao.views.gridview.menu.MenuGridIconTextViewModel;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/23 下午3:04
 */

public class PartTimeJobViewModel implements MenuGridIconTextViewModel {

    private int mId;

    private CharSequence mTitle;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public void setTitle(CharSequence title) {
        mTitle = title;
    }

    @Override
    public CharSequence getTitle() {
        return mTitle;
    }

    @Override
    public int getIcon() {
        return 0;
    }
}
