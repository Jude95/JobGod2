package com.ant.jobgod.jobgod.module.user;

import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.UserModel;
import com.ant.jobgod.jobgod.model.bean.AroundPersonBrief;
import com.ant.jobgod.jobgod.model.bean.AroundPersonBriefPage;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.facebook.common.internal.Lists;

import java.util.ArrayList;

/**
 * Created by Mr.Jude on 2015/7/19.
 */
public class AroundFriendsPresenter extends BasePresenter<AroundFriendsActivity> {

    private static final int PAGE_COUNT = 20;
    private static int page = 0;
    private ArrayList<AroundPersonBrief> arr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        UserModel.getInstance().getAroundUsers(0, PAGE_COUNT, new DataCallback<AroundPersonBriefPage>() {
            @Override
            public void success(String info, AroundPersonBriefPage data) {
                if (data.getTotalCount()==1){
                    getView().stopLoadMore();
                }
                getView().addDataWithRefresh(data.getPeople());
                arr.addAll(Lists.newArrayList(data.getPeople()));
            }
        });
    }

    @Override
    protected void onCreateView(AroundFriendsActivity view) {
        super.onCreateView(view);
        if (arr.size()>0)getView().addData(arr.toArray(new AroundPersonBrief[0]));
    }

    public void refresh(){
        UserModel.getInstance().getAroundUsers(0, PAGE_COUNT, new DataCallback<AroundPersonBriefPage>() {
            @Override
            public void success(String info, AroundPersonBriefPage data) {
                arr.clear();
                getView().addDataWithRefresh(data.getPeople());
                arr.addAll(Lists.newArrayList(data.getPeople()));
                page = 0;
                if (data.getTotalCount()==1) {
                    getView().stopLoadMore();
                }
            }
        });
    }

    public void loadMore(){
        UserModel.getInstance().getAroundUsers(page + 1, PAGE_COUNT, new DataCallback<AroundPersonBriefPage>() {
            @Override
            public void success(String info, AroundPersonBriefPage data) {
                if (data.getCurPage() == page + 1) {
                    getView().addData(data.getPeople());
                    arr.addAll(Lists.newArrayList(data.getPeople()));
                    if ((data.getTotalCount() - 1) / PAGE_COUNT <= page) {
                        getView().stopLoadMore();
                    }
                    page++;
                } else {
                }
            }
        });
    }
}
