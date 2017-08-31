package com.lrchao.fakejob.utils;

import android.content.Context;
import android.widget.ImageView;

import com.lrchao.fakejob.R;
import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/21 下午3:22
 */

public class PicassoImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        if (path instanceof String) {
            Picasso.with(context).load((String) path).placeholder(R.drawable.default_banner).into(imageView);
        }

    }
}
