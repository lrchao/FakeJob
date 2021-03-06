package com.lrchao.fakejob.ui.activity.location;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.lrchao.fakejob.R;
import com.lrchao.fakejob.constant.SharedPreferenceKey;
import com.lrchao.fakejob.manager.location.MyLocationManager;
import com.lrchao.fakejob.manager.shared_preference.CommonSharedPreference;
import com.lrchao.fakejob.mvp.BasePresenter;
import com.lrchao.fakejob.ui.activity.common.BaseFragment;
import com.lrchao.fakejob.ui.views.location.CityAdapter;
import com.lrchao.fakejob.ui.views.location.CitySortModel;
import com.lrchao.fakejob.ui.views.location.EditTextWithDel;
import com.lrchao.fakejob.ui.views.location.PinyinComparator;
import com.lrchao.fakejob.ui.views.location.PinyinUtils;
import com.lrchao.fakejob.ui.views.location.SideBar;
import com.lrchao.fakejob.ui.views.location.SortAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/9/3 下午3:50
 */

public class LocationFragment extends BaseFragment implements View.OnClickListener {

    private ListView mSortListView;
    private SideBar mSideBar;
    private TextView mTvDialog;
    private SortAdapter mAdapterSort;
    private EditTextWithDel mEtCityName;
    private List<CitySortModel> mSourceDateList;


    @Override
    protected int getLayoutViewId() {
        return R.layout.job_fragment_location;
    }

    @Override
    protected void initView(View parentView) {

        mEtCityName = parentView.findViewById(R.id.et_search);
        mSideBar = parentView.findViewById(R.id.sidrbar);
        mTvDialog = parentView.findViewById(R.id.dialog);
        mSortListView = parentView.findViewById(R.id.country_lvcountry);
        parentView.findViewById(R.id.layout_back).setOnClickListener(this);

        initDatas();
        initEvents();
        setAdapter();



    }

    private void initDatas() {
        mSideBar.setTextView(mTvDialog);
    }

    private void initEvents() {
        //设置右侧触摸监听
        mSideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = mAdapterSort.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    mSortListView.setSelection(position + 1);
                }
            }
        });

        //ListView的点击事件
        mSortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                CommonSharedPreference.getsInstance().setValue(SharedPreferenceKey.PREF_CURRENT_LOCATION,
                        ((CitySortModel) mAdapterSort.getItem(position - 1)).getName());

                finishActivity();
            }
        });

        //根据输入框输入值的改变来过滤搜索
        mEtCityName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setAdapter() {
        mSourceDateList = filledData(getResources().getStringArray(R.array.job_provinces));
        Collections.sort(mSourceDateList, new PinyinComparator());
        mAdapterSort = new SortAdapter(getContext(), mSourceDateList);
        mSortListView.addHeaderView(initHeadView());
        mSortListView.setAdapter(mAdapterSort);
    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr String
     */
    private void filterData(String filterStr) {
        List<CitySortModel> mSortList = new ArrayList<>();
        if (TextUtils.isEmpty(filterStr)) {
            mSortList = mSourceDateList;
        } else {
            mSortList.clear();
            for (CitySortModel sortModel : mSourceDateList) {
                String name = sortModel.getName();
                if (name.toUpperCase().indexOf(filterStr.toString().toUpperCase()) != -1 ||
                        PinyinUtils.getPingYin(name).toUpperCase().startsWith(filterStr.toString().toUpperCase())) {
                    mSortList.add(sortModel);
                }
            }
        }
        // 根据a-z进行排序
        Collections.sort(mSortList, new PinyinComparator());
        mAdapterSort.updateListView(mSortList);
    }

    private List<CitySortModel> filledData(String[] date) {
        List<CitySortModel> mSortList = new ArrayList<>();
        ArrayList<String> indexString = new ArrayList<>();

        for (int i = 0; i < date.length; i++) {
            CitySortModel sortModel = new CitySortModel();
            sortModel.setName(date[i]);
            String pinyin = PinyinUtils.getPingYin(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
                if (!indexString.contains(sortString)) {
                    indexString.add(sortString);
                }
            }
            mSortList.add(sortModel);
        }
        Collections.sort(indexString);
        mSideBar.setIndexText(indexString);
        return mSortList;
    }

    private View initHeadView() {
        View headView = LayoutInflater.from(getContext()).inflate(R.layout.job_headview, null);

        Button currentLocation = headView.findViewById(R.id.btn_city_name);
        currentLocation.setText(MyLocationManager.getInstance().getCurrentLocation());

        GridView gvCity = headView.findViewById(R.id.gv_hot_city);
        String[] datas = getResources().getStringArray(R.array.job_city);
        ArrayList<String> cityList = new ArrayList<>();
        for (int i = 0; i < datas.length; i++) {
            cityList.add(datas[i]);
        }
        final CityAdapter adapter = new CityAdapter(getContext(), R.layout.job_gridview_item, cityList);
        adapter.setOnCityItemClickListener(new CityAdapter.OnCityItemClickListener() {
            @Override
            public void onCityItemClick(String name) {
                CommonSharedPreference.getsInstance().setValue(SharedPreferenceKey.PREF_CURRENT_LOCATION,
                        name);

                finishActivity();
            }
        });
        gvCity.setAdapter(adapter);

        return headView;
    }


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    public static BaseFragment getInstance() {
        return new LocationFragment();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.layout_back) {
            finishActivity();
        }
    }
}
