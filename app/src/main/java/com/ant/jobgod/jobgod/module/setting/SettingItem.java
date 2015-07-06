package com.ant.jobgod.jobgod.module.setting;

import android.view.View;

/**
 * Created by Mr.Jude on 2015/7/6.
 */
public class SettingItem {
    public static final int TYPE_ITEM = 0;
    public static final int TYPE_TITLE = 1;
    private String title;
    private String intro;
    private View.OnClickListener listener;
    private int style;

    public SettingItem(String title, String intro, View.OnClickListener listener) {
        this.title = title;
        this.intro = intro;
        this.listener = listener;
        style = TYPE_ITEM;
    }

    public SettingItem(String title) {
        this.title = title;
        style = TYPE_TITLE;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }
}
