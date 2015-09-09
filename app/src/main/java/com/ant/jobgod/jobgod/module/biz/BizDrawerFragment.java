package com.ant.jobgod.jobgod.module.biz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.module.user.AroundFriendsActivity;
import com.ant.jobgod.jobgod.module.user.AttentionActivity;
import com.ant.jobgod.jobgod.module.user.ModifyFaceActivity;
import com.ant.jobgod.jobgod.util.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.beam.bijection.BeamFragment;
import com.jude.beam.bijection.RequiresPresenter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by alien on 2015/8/4.
 */
@RequiresPresenter(BizDrawerPresenter.class)
public class BizDrawerFragment extends BeamFragment<BizDrawerPresenter> {

    @InjectView(R.id.imgFace)
    SimpleDraweeView imgFace;
    @InjectView(R.id.tvName)
    TextView tvName;
    @InjectView(R.id.tvSign)
    TextView tvSign;
    @InjectView(R.id.viewAccount)
    LinearLayout viewAccount;
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
    @InjectView(R.id.viewJoin)
    FrameLayout viewJoin;
    @InjectView(R.id.viewSetting)
    FrameLayout viewSetting;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.biz_fragment_drawer, container, false);
        ButterKnife.inject(this, view);

        imgFace.setOnClickListener(v -> getPresenter().startActivity(ModifyFaceActivity.class));
        viewAccount.setOnClickListener(v -> getPresenter().checkLogin());
        viewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.Log(getClass().getName());
            }
        });
        viewSetting.setOnClickListener(v1 -> {
            Utils.Log(getClass().getName());
        });

        viewNearby.setOnClickListener(v -> getPresenter().startActivity(AroundFriendsActivity.class));
        viewAttention.setOnClickListener(v -> getPresenter().startActivity(AttentionActivity.class));
        viewJoin.setOnClickListener(v -> getPresenter().startActivity(ApplyListActivity.class));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
