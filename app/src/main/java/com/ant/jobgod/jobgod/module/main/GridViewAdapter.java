package com.ant.jobgod.jobgod.module.main;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.model.bean.Trade;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.ButterKnife;
import butterknife.InjectView;

class GridViewAdapter extends ArrayAdapter<Trade> {
    @InjectView(R.id.sdvTradeImg)
    SimpleDraweeView sdvTradeImg;
    @InjectView(R.id.tvTitle)
    TextView tvTitle;

    public GridViewAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.main_item_trade, null);
        ButterKnife.inject(this, item);
        if(getItem(position).getIcon()!=null){
            sdvTradeImg.setImageURI(Uri.parse(getItem(position).getIcon()));
        }
        tvTitle.setText(getItem(position).getName());
        return item;
    }


}