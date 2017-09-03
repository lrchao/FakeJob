package com.lrchao.fakejob.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;

import com.lrchao.fakejob.R;
import com.lrchao.fakejob.manager.session.SessionManager;
import com.lrchao.fakejob.ui.activity.common.BaseActivity;
import com.lrchao.fakejob.ui.activity.common.BaseFragment;
import com.lrchao.fakejob.ui.activity.home.HomeFragment;
import com.lrchao.fakejob.ui.activity.mine.MineFragment;
import com.lrchao.fakejob.ui.activity.part_time_job.PartTimeJobFragment;
import com.lrchao.utils.ToastUtils;
import com.lrchao.views.tab.MainTabLayout;
import com.lrchao.views.toolbar.LrchaoToolBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 《马甲主页》
 *
 * @author lrc19860926@gmail.com
 * @date 2017/2/14 下午4:39
 */

public class MainActivity extends BaseActivity implements MainTabLayout.OnMainTabItemClickListener {

    /**
     * 点击退出生效间隔
     */
    private static final long BACK_QUIT_SPACE_TIME = 3000;

    private static final int POSITION_HOME = 0;
    private static final int POSITION_PART_TIME_JOB = 1;
    private static final int POSITION_MINE = 2;

    public MainTabLayout mMainTabLayout;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<BaseFragment> mFragments = new ArrayList<>();

    /**
     * 上次按下back按键的时间
     */
    private long mLastBackTime;

    /**
     * 从通知托盘点击进入主界面
     */
    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }


    @Override
    protected int getLayoutViewId() {
        return R.layout.job_activity_main;
    }

    @Override
    protected void initView() {
        setSwipeBackEnable(false);

        mMainTabLayout = (MainTabLayout) findViewById(R.id.main_tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        mMainTabLayout.addItem(
                R.drawable.main_tab_homepage_nor,
                R.drawable.main_tab_homepage_sel,
                R.string.main_tab_homepage,
                R.color.black,
                R.color.blue_389dbd,
                R.color.white,
                R.color.white);

        mMainTabLayout.addItem(
                R.drawable.main_tab_job_nor,
                R.drawable.main_tab_job_sel,
                R.string.main_tab_job,
                R.color.black,
                R.color.blue_389dbd,
                R.color.white,
                R.color.white);

        mMainTabLayout.addItem(
                R.drawable.main_tab_personal_nor,
                R.drawable.main_tab_personal_sel,
                R.string.main_tab_personal,
                R.color.black,
                R.color.blue_389dbd,
                R.color.white,
                R.color.white);

        mMainTabLayout.setOnMainTabItemClickListener(this);

        mFragments.add(HomeFragment.getInstance());
        mFragments.add(PartTimeJobFragment.getInstance());
        mFragments.add(MineFragment.getInstance());

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public BaseFragment getItem(int arg0) {
                return mFragments.get(arg0);
            }
        };

        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

                mMainTabLayout.clickItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {


            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

        mMainTabLayout.clickItem(POSITION_HOME);


    }

    @Override
    public void onMainTabItemClick(int position) {
        mViewPager.setCurrentItem(position);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showBackToast();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showBackToast() {
        long currentTime = System.currentTimeMillis();

        if (mLastBackTime != 0 && (currentTime - mLastBackTime) < BACK_QUIT_SPACE_TIME) {
            SessionManager.getInstance().endAtMain();
            this.finish();
        } else {
            ToastUtils.show(R.string.toast_quit_tip);
            mLastBackTime = currentTime;
        }
    }
}
