package com.ant.jobgod.jobgod.module.main;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.module.main.bbs.BBSFragment;
import com.ant.jobgod.jobgod.module.main.joblist.JobListFragment;
import com.ant.jobgod.jobgod.module.main.recommend.RecommendFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

/**
 * Created by zhuchenxi on 15/6/27.
 */
@RequiresPresenter(UserMainPresenter.class)
public class UserMainActivity extends BaseActivity<UserMainPresenter> {

    @InjectView(R.id.viewPager)
    ViewPager viewpager;
    @InjectView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @InjectView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @InjectView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;
    @InjectView(R.id.tab_layout)
    TabLayout tabLayout;

    private ActionBarDrawerToggle mDrawerToggle;
    private MainPagerAdapter mMainPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_usermain);
        //setTransparentStatusBar();
        ButterKnife.inject(this);
        setSwipeBackEnable(false);
        tabLayout.setTabTextColors(getResources().getColor(R.color.WhiteTrans80), getResources().getColor(R.color.White));
        viewpager.setAdapter(mMainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewpager);
        mDrawerToggle = new ActionBarDrawerToggle(this
                , drawerLayout
                , getToolbar()
                , R.string.drawer_open
                , R.string.drawer_close);
        drawerLayout.post(() -> mDrawerToggle.syncState());
        drawerLayout.setDrawerListener(mDrawerToggle);
    }


    public class MainPagerAdapter extends FragmentStatePagerAdapter {

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new RecommendFragment();
                case 1:
                    return new JobListFragment();
                default:
                    return new BBSFragment();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "推荐";
                case 1:
                    return "兼职";
                default:
                    return "社区";
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    public void setTransparentStatusBar(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.setStatusBarColor(getResources().getColor(android.R.color.transparent));
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }
    }
}
