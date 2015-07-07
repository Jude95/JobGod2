package com.ant.jobgod.jobgod.module.main;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.bean.Trade;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.view.jpagerview.JPagerView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

/**
 * Created by zhuchenxi on 15/6/27.
 */
@RequiresPresenter(UserMainPresenter.class)
public class UserMainActivity extends BaseActivity<UserMainPresenter> {

    @InjectView(R.id.mDrawerLayout)
    DrawerLayout mDrawerLayout;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
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
    RecyclerView guessjob;

    private ActionBarDrawerToggle mDrawerToggle;

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
        gdTrade.setAdapter(new GridViewAdapter(this,R.layout.main_item_trade));
    }

    class GridViewAdapter extends ArrayAdapter<Trade> {

        public GridViewAdapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            View item=convertView;
            if(item==null){
                LayoutInflater inflater = LayoutInflater.from(UserMainActivity.this);
                item = inflater.inflate(R.layout.main_item_trade, null);
                holder=new ViewHolder(item);
                item.setTag(holder);
            }
            holder=(ViewHolder)item.getTag();
            holder.setData(JobModel.getInstance().getTrade()[position]);
            return item;
        }

        /**
         * This class contains all butterknife-injected Views & Layouts from layout file 'main_item_trade.xml'
         * for easy to all layout elements.
         *
         * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
         */
         class ViewHolder {
            @InjectView(R.id.sdvTradeImg)
            SimpleDraweeView sdvTradeImg;
            @InjectView(R.id.tvTitle)
            TextView tvTitle;

            ViewHolder(View view) {
                ButterKnife.inject(this, view);
            }
            public void setData(Trade trade){
                sdvTradeImg.setImageURI(Uri.parse(trade.getIcon()));
                tvTitle.setText(trade.getName());
            }
        }
    }
}
