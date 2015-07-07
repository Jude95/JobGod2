package com.ant.jobgod.jobgod.module.main;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.model.bean.Trade;
import com.ant.jobgod.jobgod.widget.LinearWrapContentRecyclerView;
import com.jude.view.jpagerview.JPagerView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

/**
 * Created by zhuchenxi on 15/6/27.
 */
@RequiresPresenter(UserMainPresenter.class)
public class UserMainActivity extends BaseActivity<UserMainPresenter> {


    @InjectView(R.id.pvAd)
    JPagerView pvAd;
    @InjectView(R.id.gdTrade)
    GridView gdTrade;
    @InjectView(R.id.tvHotJobMore)
    TextView tvHotJobMore;
    @InjectView(R.id.pagerview_recommend)
    JPagerView pagerviewRecommend;
    @InjectView(R.id.tvTopicMore)
    TextView tvTopicMore;
    @InjectView(R.id.guess_more)
    TextView guessMore;
    @InjectView(R.id.guessjob)
    LinearWrapContentRecyclerView guessjob;
    @InjectView(R.id.mDrawerLayout)
    DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayAdapter<Trade> tradeArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_usermain);
        ButterKnife.inject(this);
        setSwipeBackEnable(false);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, getToolbar(), R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(() -> mDrawerToggle.syncState());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        gdTrade.setAdapter(tradeArrayAdapter = new GridViewAdapter(this, R.layout.main_item_trade));
    }

    public void setTradeData(Trade[] t){
        tradeArrayAdapter.addAll(t);
    }

}
