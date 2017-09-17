package com.lrchao.fakejob.ui.activity.home;

import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.TextView;

import com.lrchao.fakejob.R;
import com.lrchao.fakejob.manager.location.MyLocationManager;
import com.lrchao.fakejob.model.json.home.HomeBannerModel;
import com.lrchao.fakejob.model.json.home.HomeJobModel;
import com.lrchao.fakejob.mvp.BasePresenter;
import com.lrchao.fakejob.ui.activity.common.SwipeRefreshFragment;
import com.lrchao.fakejob.ui.adapter.home.HomeCategoryViewModel;
import com.lrchao.fakejob.ui.views.JobItemView;
import com.lrchao.fakejob.utils.NavUtils;
import com.lrchao.fakejob.utils.PicassoImageLoader;
import com.lrchao.views.gridview.LrchaoGridAdapter;
import com.lrchao.views.gridview.LrchaoGridLayout;
import com.lrchao.views.gridview.LrchaoGridViewModel;
import com.lrchao.views.gridview.menu.MenuGridIconTextAdapter;
import com.lrchao.views.itemview.ItemGroupLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/21 上午11:20
 */

public class HomeFragment extends SwipeRefreshFragment implements
        LrchaoGridAdapter.OnLrchaoItemClickListener, HomeContract.View, View.OnClickListener {

    LrchaoGridLayout mGridLayout;

    Banner mBanner;

    ItemGroupLayout mGroupLayout;

    private HomePresenter mPresenter;

    private TextView mTvLocation;

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    public void showPageView(Object obj) {

    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.job_fragment_home;
    }

    @Override
    protected void initView(View parentView) {

        parentView.findViewById(R.id.iv_location).setOnClickListener(this);
        mTvLocation = parentView.findViewById(R.id.tv_location);
        mTvLocation.setOnClickListener(this);
        bindCurrentLocationView();

        parentView.findViewById(R.id.ll_search).setOnClickListener(this);

        mGridLayout = parentView.findViewById(R.id.layout_category);
        mBanner = parentView.findViewById(R.id.banner);
        mGroupLayout = parentView.findViewById(R.id.layout_group);

        List<LrchaoGridViewModel> data = new ArrayList<>();
        data.add(bindGridView(R.drawable.job_home_category_location, R.string.job_main_category_location, 1));
        data.add(bindGridView(R.drawable.job_home_category_newest, R.string.job_main_category_newest, 2));
        data.add(bindGridView(R.drawable.job_home_category_weekend, R.string.job_main_category_weekend, 3));
        data.add(bindGridView(R.drawable.job_home_category_student, R.string.job_main_category_student, 4));

        MenuGridIconTextAdapter categoryAdapter = new MenuGridIconTextAdapter(data);
        categoryAdapter.setLayoutResId(R.layout.job_home_category_item);
        categoryAdapter.setOnLrchaoItemClickListener(this);
        mGridLayout.setSpanCount(4);
        mGridLayout.setAdapter(categoryAdapter);

        parentView.findViewById(R.id.iv_safa).setOnClickListener(this);

        parentView.findViewById(R.id.iv_lie).setOnClickListener(this);
    }

    private HomeCategoryViewModel bindGridView(@DrawableRes int icon, int text, int id) {
        HomeCategoryViewModel model = new HomeCategoryViewModel();
        model.setId(id);
        model.setIcon(icon);
        model.setTitle(text);
        return model;
    }

    @Override
    protected BasePresenter createPresenter() {
        mPresenter = new HomePresenter();
        return mPresenter;
    }

    @Override
    public void onItemClick(LrchaoGridViewModel item, int itemPosition) {

        HomeCategoryViewModel fakeHomeCategoryViewModel = (HomeCategoryViewModel) item;
        NavUtils.get().navToCategory(getContext(), fakeHomeCategoryViewModel.getTitle().toString(), fakeHomeCategoryViewModel.getId());

    }

    @Override
    public void bindBannerView(final List<HomeBannerModel> bannerList) {

        List<Integer> urlList = new ArrayList<>();

        urlList.add(R.drawable.job_banner_1);
        urlList.add(R.drawable.job_banner_2);
        urlList.add(R.drawable.job_banner_3);


        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        mBanner.setImageLoader(new PicassoImageLoader());
        //设置图片集合
        mBanner.setImages(urlList);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
        //mBanner.setBannerTitles(titleList);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(5000);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                NavUtils.get().navToJobDetails(getActivity(), 3);
            }
        });
        mBanner.start();
    }

    @Override
    public void bindJobListView(List<HomeJobModel> jobList) {

        mGroupLayout.clear();

        for (HomeJobModel model : jobList) {
            JobItemView itemView = new JobItemView(getContext());
            itemView.bindView(model);
            mGroupLayout.addChildView(itemView);
        }

        mGroupLayout.setBottomLineHeight(2);
        mGroupLayout.build();
    }

    @Override
    public void bindCurrentLocationView() {
        mTvLocation.setText(MyLocationManager.getInstance().getCurrentLocation());
    }

    @Override
    public void onClick(View view) {
        if (R.id.iv_location == view.getId() || R.id.tv_location == view.getId()) {
            mPresenter.navToLocation();
        } else if (R.id.ll_search == view.getId()) {
            mPresenter.navToSearch();
        } else if (R.id.iv_safa == view.getId()) {
            mPresenter.navToSave();
        } else if (R.id.iv_lie == view.getId()) {
            mPresenter.navToLie();
        }
    }
}
