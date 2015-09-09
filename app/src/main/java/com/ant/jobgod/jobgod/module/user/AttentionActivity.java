package com.ant.jobgod.jobgod.module.user;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.ant.jobgod.jobgod.R;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.swipbackhelper.SwipeBackHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;

@RequiresPresenter(AttentionPresenter.class)
public class AttentionActivity extends BeamBaseActivity<AttentionPresenter> {


    @InjectView(R.id.tabLayout)
    TabLayout tabLayout;
    @InjectView(R.id.viewPager)
    ViewPager viewpager;

    private AttentionFromMeFragment attentionOthersFragment;
    private AttentionToMeFragment othersAttentionMeFragment;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_attention);
        ButterKnife.inject(this);
        attentionOthersFragment=new AttentionFromMeFragment();
        othersAttentionMeFragment=new AttentionToMeFragment();
        adapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        viewpager.addOnPageChangeListener(new ChangeListener());
        tabLayout.setupWithViewPager(viewpager);
    }

    /**
     * viewpager适配器
     */
    class ViewPagerAdapter extends FragmentPagerAdapter{

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return attentionOthersFragment;
                case 1:
                    return othersAttentionMeFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "我关注的";
                case 1:
                    return "关注我的";
                default:
                    return "";
            }
        }
    }

    class ChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position==0){
                SwipeBackHelper.getCurrentPage(AttentionActivity.this).setSwipeBackEnable(true);
            }
            else
                SwipeBackHelper.getCurrentPage(AttentionActivity.this).setSwipeBackEnable(false);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}
