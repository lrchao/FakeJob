package com.lrchao.fakejob.ui.views.location;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;

import com.lrchao.fakejob.R;

import java.util.List;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/9/4 上午10:20
 */

public class CityAdapter extends ArrayAdapter<String> {

    /**
     * 需要渲染的item布局文件
     */
    private int resource;

    public CityAdapter(Context context, int textViewResourceId, List<String> objects) {
        super(context, textViewResourceId, objects);
        resource = textViewResourceId;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LinearLayout layout = null;
        if (convertView == null) {
            layout = (LinearLayout) LayoutInflater.from(getContext()).inflate(resource, null);
        } else {
            layout = (LinearLayout) convertView;
        }
        Button name = (Button) layout.findViewById(R.id.tv_city);
        name.setText(getItem(position));
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnCityItemClickListener != null) {
                    mOnCityItemClickListener.onCityItemClick(getItem(position));
                }
            }
        });
        return layout;
    }

    private OnCityItemClickListener mOnCityItemClickListener;

    public void setOnCityItemClickListener(OnCityItemClickListener onCityItemClickListener) {
        mOnCityItemClickListener = onCityItemClickListener;
    }

    public interface OnCityItemClickListener {

        void onCityItemClick(String name);
    }
}
