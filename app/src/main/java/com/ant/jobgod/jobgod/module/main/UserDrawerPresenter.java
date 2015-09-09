package com.ant.jobgod.jobgod.module.main;

import android.content.Intent;

import com.ant.jobgod.jobgod.model.AccountModel;
import com.ant.jobgod.jobgod.model.RongYunModel;
import com.ant.jobgod.jobgod.model.bean.AccountData;
import com.ant.jobgod.jobgod.module.launch.UserLoginActivity;
import com.jude.beam.bijection.Presenter;

import rx.Subscription;


/**
 * Created by Mr.Jude on 2015/7/1.
 */
public class UserDrawerPresenter extends Presenter<UserDrawerFragment> {


    private Subscription mAccountSubscription;
    private Subscription mMessageCountSubscription;
    @Override
    protected void onCreateView(UserDrawerFragment view) {
        super.onCreateView(view);
        AccountData info = AccountModel.getInstance().getAccount();
        if (info != null){
            getView().setAccount(info);
        }
        mAccountSubscription = AccountModel.getInstance().registerUserAccountUpdate(userAccountData -> getView().setAccount(userAccountData));
        mMessageCountSubscription = RongYunModel.getInstance().registerNotifyCount(count -> getView().setMessageCount(count));
    }

    @Override
    protected void onDestroyView() {
        super.onDestroyView();
        mAccountSubscription.unsubscribe();
        mMessageCountSubscription.unsubscribe();
    }

    public boolean checkLogin(){
        if(AccountModel.getInstance().getAccount() == null){
            getView().startActivity(new Intent(getView().getActivity(), UserLoginActivity.class));
            return false;
        }
        return true;
    }

    public void startActivity(Class<?> clazz){
        if (checkLogin())
        getView().startActivity(new Intent(getView().getActivity(), clazz));
    }

    public void startChatList(){
        if (checkLogin())
        RongYunModel.getInstance().chatList(getView().getActivity());
    }
}
