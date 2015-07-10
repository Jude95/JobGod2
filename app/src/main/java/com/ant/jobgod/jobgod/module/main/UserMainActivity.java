package com.ant.jobgod.jobgod.module.main;

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

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

/**
 * Created by zhuchenxi on 15/6/27.
 */
@RequiresPresenter(UserMainPresenter.class)
public class UserMainActivity extends BaseActivity<UserMainPresenter> {

    @InjectView(R.id.viewpager)
    ViewPager viewpager;
    @InjectView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @InjectView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @InjectView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;
    @InjectView(R.id.tabLayout)
    TabLayout tabLayout;

    private ActionBarDrawerToggle mDrawerToggle;
    private MainPagerAdapter mMainPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_usermain);
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

    public class MainPagerAdapter extends FragmentStatePagerAdapter{

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
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
            switch (position){
                case 0:
                    return "推荐";
                case 1:
                    return "兼职";
                default:
                    return "动态";
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }


}
