package com.lrchao.fakejob.utils;

import android.text.TextUtils;
import android.widget.ImageView;

import com.lrchao.fakejob.R;
import com.lrchao.utils.ImageUtils;


/**
 * Description: 加载图片工具类
 *
 * @author lrc19860926@gmail.com
 * @date 2016/10/17 下午1:44
 */

public final class MyImageUtils {

    private MyImageUtils() {
    }

    /**
     * 设置app图标
     */
    public static void displayAppIcon(String imgUrl, ImageView imageView) {

        if (TextUtils.isEmpty(imgUrl)) {
            imgUrl = "null";
        }
        ImageUtils.display(imgUrl,
                imageView,
                R.drawable.job_default_app_icon,
                null,
                true,
                0,
                0,
                false,
                false);
    }

    /**
     * 设置显示头像
     *
     * @param imgUrl    头像的地址
     * @param imageView ImageView
     */
    public static void displayUserIcon(String imgUrl, ImageView imageView) {

        if (TextUtils.isEmpty(imgUrl)) {
            imgUrl = "null";
        }

        ImageUtils.display(imgUrl,
                imageView,
                R.drawable.job_default_user_icon,
                null,
                true,
                0,
                0,
                false,
                false);
    }


    public static void displayJobIcon(String imgUrl, ImageView imageView) {

        if (TextUtils.isEmpty(imgUrl)) {
            imgUrl = "null";
        }

        ImageUtils.display(imgUrl,
                imageView,
                R.drawable.job_default_app_icon,
                null,
                true,
                0,
                0,
                false,
                false);
    }


}
