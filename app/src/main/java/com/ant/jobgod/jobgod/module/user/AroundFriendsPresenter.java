package com.ant.jobgod.jobgod.module.user;

import android.os.Bundle;

import com.ant.jobgod.jobgod.model.UserModel;
import com.ant.jobgod.jobgod.model.bean.AroundPersonBrief;
import com.ant.jobgod.jobgod.model.bean.AroundPersonBriefPage;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.jude.beam.expansion.list.BeamListActivityPresenter;

import java.util.ArrayList;

/**
 * Created by Mr.Jude on 2015/7/19.
 */
public class AroundFriendsPresenter extends BeamListActivityPresenter<AroundFriendsActivity,AroundPersonBrief> {

    private static final int PAGE_COUNT = 20;
    private static int page = 0;
    private ArrayList<AroundPersonBrief> arr = new ArrayList<>();

    @Override
    protected void onCreate(AroundFriendsActivity view,Bundle savedState) {
        super.onCreate(view, savedState);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        UserModel.getInstance().getAroundUsers(0, PAGE_COUNT, new DataCallback<AroundPersonBriefPage>() {
            @Override
            public void success(String info, AroundPersonBriefPage data) {
                getAdapter().clear();
                getAdapter().addAll(data.getPeople());
                page = 1;
            }

            @Override
            public void error(String errorInfo) {
                getView().showError();
            }
        });
    }

    @Override
    public void onLoadMore() {
        UserModel.getInstance().getAroundUsers(page + 1, PAGE_COUNT, new DataCallback<AroundPersonBriefPage>() {
            @Override
            public void success(String info, AroundPersonBriefPage data) {
                getAdapter().addAll(data.getPeople());
                page++;
            }

            @Override
            public void error(String errorInfo) {
                getAdapter().pauseMore();
            }
        });
    }

}
