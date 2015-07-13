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
import com.ant.jobgod.jobgod.model.bean.AccountInfo;
import com.ant.jobgod.jobgod.module.job.JobDetailManagerActivity;
import com.ant.jobgod.jobgod.module.setting.SettingActivity;
import com.ant.jobgod.jobgod.module.user.AttentionActivity;
import com.ant.jobgod.jobgod.module.user.CollectActivity;
import com.ant.jobgod.jobgod.module.user.UserDetailActivity;
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
    @InjectView(R.id.viewExperience)
    FrameLayout viewExperience;
    @InjectView(R.id.viewSetting)
    FrameLayout viewSetting;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_drawer, container, false);
        ButterKnife.inject(this, view);


        imgFace.setImageURI(Uri.parse("http://img.hb.aicdn.com/83baf35e3d9f9069db3d6bbe87358b877664425532114-BzeCQd_fw658"));
        viewMessage.setOnClickListener((View) -> getPresenter().startActivity(JobDetailManagerActivity.class));
        viewSetting.setOnClickListener((View) -> getPresenter().startActivity(SettingActivity.class));
        viewInformation.setOnClickListener(v -> getPresenter().startActivity(UserDetailActivity.class));

        viewAttention.setOnClickListener(v -> getPresenter().startActivity(AttentionActivity.class));
        viewExperience.setOnClickListener(v -> getPresenter().startActivity(PersonDetailAcitivity.class));
        viewCollection.setOnClickListener(v -> getPresenter().startActivity(CollectActivity.class));
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    public void setAccount(AccountInfo info) {
        imgFace.setImageURI(Uri.parse(info.getFace()));
        tvName.setText(info.getName());
        tvSign.setText(info.getSign());
    }


}
