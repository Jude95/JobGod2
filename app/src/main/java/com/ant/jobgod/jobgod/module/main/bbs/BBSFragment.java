package com.ant.jobgod.jobgod.module.main.bbs;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.module.main.AbsMenuFragment;

import nucleus.factory.RequiresPresenter;

/**
 * Created by Mr.Jude on 2015/7/10.
 */
@RequiresPresenter(BBSPresenter.class)
public class BBSFragment extends AbsMenuFragment<BBSPresenter> {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView rootView = new TextView(getActivity());
        rootView.setText("未接入");
        return rootView;
    }

    @Override
    public int getMenu() {
        return R.menu.menu_bbs;
    }

    @Override
    public void onMenuSelect(@IdRes int id) {

    }
}
