package com.ant.jobgod.jobgod.module.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusFragment;

/**
 * Created by Mr.Jude on 2015/7/10.
 */
@RequiresPresenter(BBSPresenter.class)
public class BBSFragment extends NucleusFragment<BBSPresenter> {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView rootView = new TextView(getActivity());
        rootView.setText("未接入");
        return rootView;
    }
}
