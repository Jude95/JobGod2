package com.ant.jobgod.jobgod.module.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.view.jpagerview.JPagerAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by alien on 2015/7/7.
 */
public class HotJobAdapter extends JPagerAdapter {

    @InjectView(R.id.sdvTradeImg)
    SimpleDraweeView sdvTradeImg;
    @InjectView(R.id.tvTitle)
    TextView tvTitle;
    private Context context;

    public HotJobAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item0 = inflater.inflate(R.layout.main_item_trade, container, false);
        View item1 = inflater.inflate(R.layout.main_item_trade, container, false);
        View item2 = inflater.inflate(R.layout.main_item_trade, container, false);
        ButterKnife.inject(context, item0);
        ButterKnife.inject(context, item1);
        ButterKnife.inject(context, item2);
        item0.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        item1.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        item2.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        sdvTradeImg.setAspectRatio(1.5f);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.addView(item0);
        linearLayout.addView(item1);
        linearLayout.addView(item2);

        return linearLayout;
    }

    @Override
    public int getCount() {
        return 1;
    }
}
