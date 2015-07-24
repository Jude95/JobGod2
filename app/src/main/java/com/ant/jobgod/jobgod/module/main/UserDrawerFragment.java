package com.ant.jobgod.jobgod.module.main;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.model.RongYunModel;
import com.ant.jobgod.jobgod.model.bean.AccountData;
import com.ant.jobgod.jobgod.module.setting.SettingActivity;
import com.ant.jobgod.jobgod.module.user.AroundFriendsActivity;
import com.ant.jobgod.jobgod.module.user.AttentionActivity;
import com.ant.jobgod.jobgod.module.user.CollectActivity;
import com.ant.jobgod.jobgod.module.user.JoinActivity;
import com.ant.jobgod.jobgod.module.user.ModifyFaceActivity;
import com.ant.jobgod.jobgod.module.user.UserDataActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusFragment;

/**
 * Created by Mr.Jude on 2015/7/1.
 */
@RequiresPresenter(UserDrawerPresenter.class)
public class UserDrawerFragment extends NucleusFragment<UserDrawerPresenter> {


    @InjectView(R.id.imgFace)
    SimpleDraweeView imgFace;
    @InjectView(R.id.tvName)
    TextView tvName;
    @InjectView(R.id.tvSign)
    TextView tvSign;
    @InjectView(R.id.tvMessageCount)
    TextView tvMessageCount;
    @InjectView(R.id.viewMessage)
    RelativeLayout viewMessage;
    @InjectView(R.id.tvAttentionCount)
    TextView tvAttentionCount;
    @InjectView(R.id.viewAttention)
    RelativeLayout viewAttention;
    @InjectView(R.id.tvNearbyCount)
    TextView tvNearbyCount;
    @InjectView(R.id.viewNearby)
    RelativeLayout viewNearby;
    @InjectView(R.id.viewWallet)
    FrameLayout viewWallet;
    @InjectView(R.id.viewInformation)
    FrameLayout viewInformation;
    @InjectView(R.id.viewCollection)
    FrameLayout viewCollection;
    @InjectView(R.id.viewJoin)
    FrameLayout viewJoin;
    @InjectView(R.id.viewSetting)
    FrameLayout viewSetting;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_drawer, container, false);
        ButterKnife.inject(this, view);
        imgFace.setOnClickListener(v -> getPresenter().startActivity(ModifyFaceActivity.class));
        viewMessage.setOnClickListener((View) -> RongYunModel.getInstance().chatList(getActivity()));
        viewSetting.setOnClickListener((View) -> getPresenter().startActivity(SettingActivity.class));
        viewInformation.setOnClickListener(v -> getPresenter().startActivity(UserDataActivity.class));
        viewNearby.setOnClickListener(v->getPresenter().startActivity(AroundFriendsActivity.class));
        viewAttention.setOnClickListener(v -> getPresenter().startActivity(AttentionActivity.class));
        viewJoin.setOnClickListener(v -> getPresenter().startActivity(JoinActivity.class));
        viewCollection.setOnClickListener(v -> getPresenter().startActivity(CollectActivity.class));
        return view;
    }

    public void setMessageCount(int i){
        tvMessageCount.setVisibility(i==0?View.GONE: View.VISIBLE);
        tvMessageCount.setText(i+"");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    public void setAccount(AccountData info) {
        imgFace.setImageURI(Uri.parse(info.getFace()));
        tvName.setText(info.getName());
        tvSign.setText(info.getSign());
    }


}
