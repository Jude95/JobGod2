package com.ant.jobgod.jobgod.app;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.util.Utils;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

import nucleus.manager.Presenter;
import swipebacklayout.app.SwipeBackActivity;

/**
 * Created by zhuchenxi on 15/6/7.
 */
public class BaseActivity<T extends Presenter> extends SwipeBackActivity<T> {


    private Toolbar toolbar;
    private MaterialDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PushAgent.getInstance(this).onAppStart();
        getSwipeBackLayout().setEdgeSize(Utils.getScreenWidth()/2);
    }

    protected void setToolBar(boolean returnAble){
        toolbar = $(R.id.toolbar);
        if (toolbar!=null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(returnAble);
        }
    }

    protected Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setToolBar(true);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        setToolBar(true);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        setToolBar(true);
    }

    public void showProgress(String title){
         dialog = new MaterialDialog.Builder(this)
                .title(title)
                .content("请稍候")
                .progress(true, 100)
                .cancelable(false)
                .show();
    }

    public void dismissProgress(){
        dialog.dismiss();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected final <E extends View> E $(View view,@IdRes int id){
        return (E)view.findViewById(id);
    }
    protected final <E extends View> E $(@IdRes int id){
        return (E)findViewById(id);
    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
