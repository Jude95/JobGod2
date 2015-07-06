package com.ant.jobgod.jobgod.module.setting;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.util.BaseViewHolder;

/**
 * Created by Mr.Jude on 2015/7/6.
 */
public class SettingViewHolder extends BaseViewHolder<SettingItem> {
    private TextView tvTitle;
    private TextView tvIntro;

    public SettingViewHolder(ViewGroup parent,int viewType) {
        super(parent, getResource(viewType));
        tvTitle = $(R.id.tvTitle);
        tvIntro = $(R.id.tvIntro);
    }

    private static int getResource(int viewType){
        switch (viewType){
            case SettingItem.TYPE_ITEM:
                return R.layout.setting_item_setting;
            case SettingItem.TYPE_TITLE:
                return R.layout.setting_item_title;
            default:
                return R.layout.setting_item_setting;
        }
    }

    @Override
    public void setData(SettingItem data) {
        if (data.getStyle() == SettingItem.TYPE_ITEM){
            tvTitle.setText(data.getTitle());
            if (data.getIntro() == null||data.getIntro().trim().isEmpty()){
                tvIntro.setVisibility(View.GONE);
            }else{
                tvIntro.setVisibility(View.VISIBLE);
                tvIntro.setText(data.getIntro());
            }

            if (data.getListener() != null)
            itemView.setOnClickListener(data.getListener());
        }else{
            tvTitle.setText(data.getTitle());
        }
    }
}
